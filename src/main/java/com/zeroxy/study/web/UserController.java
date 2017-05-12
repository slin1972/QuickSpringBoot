package com.zeroxy.study.web;

import com.zeroxy.CommonResult;
import com.zeroxy.study.service.SMSHystrixService;
import com.zeroxy.study.repository.UserRepository;
import com.zeroxy.study.result.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  private SMSHystrixService smsHystrixService ;

  public UserController() {
  }

  @GetMapping("/validate")
  public CommonResult validate(@RequestParam String phone, @RequestParam String areaCode,
                               @RequestParam(required = false) String r,
                               @RequestParam(required = false) String invite) {

    SMSHystrixService.SMSContent smsContent = new SMSHystrixService.SMSContent();
    smsContent.setAreacode(areaCode);
    smsContent.setPhone(phone);
    smsContent.setContent("您的验证码是：1312。如需帮助请联系客服。");
    CommonResult result = smsHystrixService.send("76706e343f10156e7bf450b443fbb50", smsContent);


    return result;
  }

}
