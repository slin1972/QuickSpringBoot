package com.zeroxy.study.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zeroxy.CommonResult;
import com.zeroxy.study.result.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 使用@FeignClient注解的fallback属性，指定fallback类
 * @author eacdy
 */
@Service
public class SMSHystrixService {
  @Autowired
  private RestTemplate restTemplate;
  private static final Logger LOGGER = LoggerFactory.getLogger(SMSHystrixService.class);

  /**
   * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
   * @param key key
   * @return 发送短信
   */
  @HystrixCommand(fallbackMethod = "fallback")
  public CommonResult send(String key, SMSContent body) {
    CommonResult response = restTemplate.postForObject("http://<host>/v1/sms?key=" + key, body, CommonResult.class);
    LOGGER.info(response.toString());
    return response ;
  }

  /**
   * hystrix fallback方法
   * @param key key
   * @return 返回失败
   */
  public CommonResult fallback(String key, SMSContent body) {
    LOGGER.info("异常发生，进入fallback方法，接收的参数：key = {}, body = {}", key, body);
    return ResponseCode.ERROR_999;
  }

  public static class SMSContent{
    private String areacode ;
    private String phone ;
    private String content ;

    public String getAreacode() {
      return areacode;
    }

    public void setAreacode(String areacode) {
      this.areacode = areacode;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    @Override
    public String toString() {
      return "SMSContent{" +
              "areacode='" + areacode + '\'' +
              ", phone='" + phone + '\'' +
              ", content='" + content + '\'' +
              '}';
    }
  }
}