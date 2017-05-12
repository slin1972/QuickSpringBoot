package com.zeroxy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
//@EnableDiscoveryClient  Use here when open eureka
public class UserApplication {

  /**
   * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
   * @return restTemplate
   */
  @Bean
 // @LoadBalanced
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    //restTemplate.getUriTemplateHandler().expand()
    //restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    return restTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
