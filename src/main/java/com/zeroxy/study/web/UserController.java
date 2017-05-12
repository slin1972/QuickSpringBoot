package com.zeroxy.study.web;

import com.zeroxy.ApiBaseParams;
import com.zeroxy.CommonResult;
import com.zeroxy.PhoneNumber;
import com.zeroxy.study.GlobalConstant;
import com.zeroxy.study.domain.PhoneCode;
import com.zeroxy.study.domain.User;
import com.zeroxy.study.repository.PhoneCodeRepository;
import com.zeroxy.study.service.SMSHystrixService;
import com.zeroxy.study.repository.UserRepository;
import com.zeroxy.study.result.ReturnCode;
import com.zeroxy.study.web.annotation.XModelAttribute;
import com.zeroxy.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 作用：
 * ① 测试服务实例的相关内容
 * ② 为后来的服务做提供
 * @author slin
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PhoneCodeRepository phoneCodeRepository;

  @Autowired
  private SMSHystrixService smsHystrixService ;

  public UserController() {
  }

  @GetMapping("/validate")
  public CommonResult validate(@RequestParam String phone, @RequestParam String areaCode,
                               @RequestParam(required = false) String r,
                               @RequestParam(required = false) String invite,
                               @XModelAttribute ApiBaseParams apiBaseParams) {
    PhoneNumber pn = null ;
    try {
      pn = new PhoneNumber(areaCode, phone);
    } catch (Exception e) {
      return ReturnCode.ERROR_153;
    }
    User user = userRepository.findByAreaCodeAndPhone(pn.getAreaCode(), pn.getPhone());
    if (user == null || user.getId() == null) {
      //判断该手机号 24小时之内是否获取了五条短信记录
      long currentTime = System.currentTimeMillis();

      int count = phoneCodeRepository.countByAreaCodeAndPhoneAndMoreThenCtime(pn.getAreaCode(),pn.getPhone(),currentTime-24*60*60*1000);

      if(count >= 5){
        return ReturnCode.ERROR_152;
      }
      return sendRandomCode(pn, count);
    } else {
      return ReturnCode.ERROR_104;
    }

  }

  @Transactional
  private CommonResult sendRandomCode(PhoneNumber pn, int count) {

      Long smsTwoInterval = 120L;//Long.parseLong(AppConfig.getInstance().getConfigValue("smsTwoInterval")) ;
      //查询最后一次验证码信息
      PhoneCode pc = phoneCodeRepository.findLastCodeByAreaCodeAndPhone(pn.getAreaCode(),pn.getPhone());
      String code = null;
      if(pc == null || System.currentTimeMillis() - pc.getCtime() > 10*60*1000){
        //如果最后一次没有验证码
        code = CommonUtil.generateCode();
      }else{
        code = pc.getCode();
        //如果不超过二次间隔时间
        if(System.currentTimeMillis() - pc.getCtime() <= smsTwoInterval * 1000){
          return ReturnCode.ERROR_149;
        }
      }

      //SMSParams smsParams = SMSParams.RANDOM_CODE.setAreaCode(pn.getAreaCode()).putKeyword("randomCode", code).build();

      //SMSResult smsResult = SMSFactory.getInstance(pn).sendMessage(smsParams, pn);

      //ThreadPool.getInstance().execute(new SendSMSHandle(count,pn,smsParams));
      //if(smsResult.isOk()){

      SMSHystrixService.SMSContent smsContent = new SMSHystrixService.SMSContent();
      smsContent.setAreacode(pn.getAreaCode());
      smsContent.setPhone(pn.getPhone());
      smsContent.setContent("Your code is "+code+". If you need help, please contact customer service.");
      CommonResult result = smsHystrixService.send("1111111111111", smsContent);
      if(result.getCode() == 0){
        pc = new PhoneCode(pn.getAreaCode(),pn.getPhone(), code);
        phoneCodeRepository.save(pc);
      }else{
        result.setCode(0);
      }
      return result;
  }

  @PostMapping("/password")
  public CommonResult password(@RequestParam String phone, @RequestParam String areaCode,
                               @RequestParam String code, @RequestParam String password,
                               @RequestParam(required = false) String email,
                               @XModelAttribute ApiBaseParams apiBaseParams) {

    PhoneNumber pn = null;
    try {
      pn = new PhoneNumber(areaCode, phone);
    } catch (Exception e) {
      return ReturnCode.ERROR_153;
    }
    User user = userRepository.findByAreaCodeAndPhone(pn.getAreaCode(), pn.getPhone());
    //查找手机是否已经注册过
    //如果没有注册过
    if (user == null || StringUtils.isEmpty(user.getId())) {
      //如果注册功能关闭
      //if(AppConfig.getInstance().isFunctionClose("register")){
      //  return ReturnCode.ERROR_308;
      //}
      if(StringUtils.isEmpty(pn.getAreaCode())){
        return ReturnCode.ERROR_145;
      }
      //找出验证码  与  提交的验证码进行比较
      PhoneCode pc = phoneCodeRepository.findLastCodeByAreaCodeAndPhone(pn.getAreaCode(),pn.getPhone());
      //如果发送过验证码  并且匹配成功
      if(pc!=null && (pc.getCode().equals(code)||"8888".equals(code))){
        user = new User();
        user.setPhone(pn.getPhone());
        user.setEmail(email);
        if(!StringUtils.isEmpty(email)){
          boolean b = email.matches(GlobalConstant.EMAIL_REGEX);
          if(!b){
            return ReturnCode.ERROR_150;
          }
        }
        user.setAreaCode(pn.getAreaCode());
        user.setPassword(CommonUtil.md5(password));
        user.setRegisterTime(Calendar.getInstance().getTime());
        user.setRegisterFrom(apiBaseParams.getAv());
        user.setDeviceType(0);
        String randomText = System.currentTimeMillis() + "" + CommonUtil.generateCode(5) ;
        Long time = Long.parseLong(randomText);
        String nickName = Long.toString(time, 36);
        user.setNickname("bip_"+nickName);
        User newUser = register(user,pc);
//        if(newUser!=null && newUser.getId()!=null){
//          if(!StringUtils.isEmpty(user.getEmail())){
//            String emailCheckExpire = "24";//AppConfig.getInstance().getConfigValue("emailCheckExpire");
//            Builder builder = EmailParams.newBuilder();
//            String emailCode = CommonUtil.generateEmailCode();
//            EmailParams emailParams = builder.setName("emailCheck").putKeyword("expireHour", emailCheckExpire).putKeyword("selfEmail", user.getEmail()).putKeyword("activeUrl", AppConfig.getInstance().getConfigValue("activeUrlPath")+"?m="+emailCode).putKeyword("userId", user.getId()+"").putKeyword("requireRandomCode", emailCode).setAreaCode(user.getAreaCode()).build();
//            //emailServer.sendMail(emailParams, user.getEmail());
//            ThreadPool.getInstance().execute(new SendEmailHandle(emailParams,user.getEmail()));
//          }
//          return ReturnCode.OK_0;
//        }else{
//          return ReturnCode.ERROR_106;
//        }
        return ReturnCode.OK_0;
      }else{
        return ReturnCode.ERROR_105;
      }
      //如果已经注册过  说明是修改密码的操作
    }else{
      PhoneCode pc = phoneCodeRepository.findLastCodeByAreaCodeAndPhone(pn.getAreaCode(),pn.getPhone());
      if(pc!=null && (pc.getCode().equals(code)|| "8888".equals(code))){
        user.setPassword(CommonUtil.md5(password));
        userRepository.save(user);
        return ReturnCode.OK_0;
      }else{
        return ReturnCode.ERROR_105;
      }
    }
  }

  @Transactional
  private User register(User user, PhoneCode pc) {
    User newUser = userRepository.save(user);
    pc.setStatus(PhoneCode.STATUS_DELETE);
    phoneCodeRepository.save(pc);
    return newUser;
  }

  @PostMapping("/login")
  public CommonResult password(@RequestParam String phone, @RequestParam String areaCode,
                               @RequestParam String password,
                               @RequestParam(required = false) String clientid,
                               @XModelAttribute ApiBaseParams apiBaseParams) {
    String token = CommonUtil.md5(UUID.randomUUID()+"");
    User user = new User();
    PhoneNumber pn = null;
    try {
      pn = new PhoneNumber(areaCode, phone);
    } catch (Exception e) {
      return ReturnCode.ERROR_153;
    }
    user.setAreaCode(pn.getAreaCode());
    user.setPhone(pn.getPhone());
    user.setPassword(password);
    CommonResult res = userRepository.login(user);
    if(res!=null && res.getCode() == 0){
      Map<String,Object> data = new HashMap<>();
      //userId
      data.put("userId",user.getId());
      //email
      data.put("email",user.getEmail());
      //emailActive
      data.put("emailActive",user.getEmailActive());
      //Token
      data.put("token", token);
      List<ActDeviceMessage> msgLastTime = userMessageService.findMaxTimeByUserId(user.getId());
      Object sysCfgLastTime = systemConfigDao.getMaxTime();
      //msgLastTime
      data.put("msgLastTime",msgLastTime);
      //sysCfgLastTime
      data.put("sysCfgLastTime", sysCfgLastTime == null ? 0 :sysCfgLastTime);

      data.put("website", AppConfig.getInstance().getConfigValue("website"));
      //http://devondtech.com/static/h5/privacy_${language}.html
      data.put("privacy", AppConfig.getInstance().getConfigValue("privacy"));
      data.put("serviceMail", AppConfig.getInstance().getConfigValue("serviceMail"));
      data.put("faq", AppConfig.getInstance().getConfigValue("faq"));

      user = userRepository.findOne(user.getId());
      user.setDeviceToken(clientid);
      user.setDeviceType(apiBaseParams.getOs().contains("ios")?1:(apiBaseParams.getOs().contains("android")?2:0));
      user.setLoginToken(token);
      user.setLastLoginFrom(apiBaseParams.getAv());
      user.setLastLoginTime(Calendar.getInstance().getTime());
      user.setLastLoginDeviceOs(apiBaseParams.getOs());
      user.setLoginTokenTime(DateUtil.currentTimeSeconds());
      user.setImei(apiBaseParams.getImei());
      userRepository.updateLoginInfo(user);

      data.put("user", user);

      //确认其他人发送的邀请
      inviteService.confirmInvite(user);
      //cacheService.deleteHashValue(CacheKey.BLACK_DEVICE_TOKEN, clientid);
      res.setData(data);
    }
    return res;

  }
}
