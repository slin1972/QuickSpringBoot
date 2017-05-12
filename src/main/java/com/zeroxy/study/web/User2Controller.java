package com.zeroxy.study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zeroxy.study.domain.User;
import com.zeroxy.study.repository.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 作用：
 * ① 测试服务实例的相关内容
 * ② 为后来的服务做提供
 * @author slin
 */
@RestController
@RequestMapping("/user2")
public class User2Controller {
  @Autowired
  private UserRepository userRepository;

  /**
   * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
   * @RequestMapping(value = "/id", method = RequestMethod.GET)
   * 类似的注解还有@PostMapping等等
   * @param id
   * @return user信息
   */
  @GetMapping("/{id}")
  public User findById(@PathVariable Long id) {
    User findOne = this.userRepository.findOne(id);
    return findOne;
  }


    @GetMapping("/list")
    public ModelAndView view() {
      List<User> users = this.userRepository.findAll();
      ModelAndView modelAndView = new ModelAndView("list");
      modelAndView.addObject("users", users);
      return modelAndView;
    }

}
