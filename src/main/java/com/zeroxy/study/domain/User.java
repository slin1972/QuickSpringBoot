package com.zeroxy.study.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "t_user")
public class User extends IdDomain implements Serializable{

  private static final long serialVersionUID = -1979129014781686560L;

  @JsonIgnore
  private String password;
  private String phone;
  private String email;
  private Boolean emailActive = false;
  private Date emailActiveTime;
  private String areaCode ;//区号
  @JsonIgnore
  private String registerFrom;
  @JsonIgnore
  private Date registerTime;
  @JsonIgnore
  private String lastLoginFrom;
  @JsonIgnore
  private Date lastLoginTime;
  @JsonIgnore
  private String lastLoginDeviceOs; //

  private String nickname;
  private String intro;//描述
  @JsonIgnore
  private String location;
  @JsonIgnore
  private String birthday;
  @Column(name="gender",columnDefinition="int")
  private Integer gender;//性别
  private String headImage ;
  private String HeadBackgroundImage;

  private String loginToken;
  @JsonIgnore
  private Long loginTokenTime;

  private String deviceToken ;
  @JsonIgnore
  private Integer deviceType; //1 ios 2 android
  @JsonIgnore
  private String imei ;

  public User(Long id, String loginToken,Long loginTokenTime,String imei) {
    super();
    this.id = id;
    this.loginToken = loginToken;
    this.loginTokenTime = loginTokenTime;
    this.imei = imei ;
  }
  public User(Long id, String deviceToken, Integer deviceType) {
    super();
    this.id = id;
    this.deviceToken = deviceToken;
    this.deviceType = deviceType;
  }

  public User(Long id, String deviceToken, Integer deviceType,String lastLoginDeviceOs,String lastLoginFrom) {
    super();
    this.id = id;
    this.deviceToken = deviceToken;
    this.lastLoginDeviceOs = lastLoginDeviceOs ;
    this.deviceType = deviceType;
    this.lastLoginFrom = lastLoginFrom;
  }
  public User() {
    super();
  }

  public Boolean getEmailActive() {
    return emailActive;
  }
  public void setEmailActive(Boolean emailActive) {
    this.emailActive = emailActive;
  }
  public String getAreaCode() {
    return areaCode;
  }
  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }
  public String getDeviceToken() {
    return deviceToken;
  }

  public void setDeviceToken(String deviceToken) {
    this.deviceToken = deviceToken;
  }

  public Integer getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(Integer deviceType) {
    this.deviceType = deviceType;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Date getEmailActiveTime() {
    return emailActiveTime;
  }
  public void setEmailActiveTime(Date emailActiveTime) {
    this.emailActiveTime = emailActiveTime;
  }
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRegisterFrom() {
    return registerFrom;
  }

  public void setRegisterFrom(String registerFrom) {
    this.registerFrom = registerFrom;
  }

  public Date getRegisterTime() {
    return registerTime;
  }

  public void setRegisterTime(Date registerTime) {
    this.registerTime = registerTime;
  }

  public String getLastLoginFrom() {
    return lastLoginFrom;
  }

  public void setLastLoginFrom(String lastLoginFrom) {
    this.lastLoginFrom = lastLoginFrom;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getLoginToken() {
    return loginToken;
  }

  public void setLoginToken(String loginToken) {
    this.loginToken = loginToken;
  }

  public Long getLoginTokenTime() {
    return loginTokenTime;
  }

  public void setLoginTokenTime(Long loginTokenTime) {
    this.loginTokenTime = loginTokenTime;
  }
  public String getImei() {
    return imei;
  }
  public void setImei(String imei) {
    this.imei = imei;
  }
  public String getLastLoginDeviceOs() {
    return lastLoginDeviceOs;
  }
  public void setLastLoginDeviceOs(String lastLoginDeviceOs) {
    this.lastLoginDeviceOs = lastLoginDeviceOs;
  }
  public String getHeadImage() {
    return headImage;
  }
  public void setHeadImage(String headImage) {
    this.headImage = headImage;
  }
  public String getHeadBackgroundImage() {
    return HeadBackgroundImage;
  }
  public void setHeadBackgroundImage(String headBackgroundImage) {
    HeadBackgroundImage = headBackgroundImage;
  }
  @Override
  public String toString() {
    return "User [id=" + id +  ", phone=" + phone
            + ", email=" + email + ", registerFrom=" + registerFrom
            + ", registerTime=" + registerTime + ", lastLoginFrom="
            + lastLoginFrom + ", lastLoginTime=" + lastLoginTime
            + ", nickname=" + nickname + ", intro=" + intro + ", location="
            + location + ", birthday=" + birthday + ", gender=" + gender
            + ", loginToken=" + loginToken + ", loginTokenTime="
            + loginTokenTime + ", deviceToken=" + deviceToken
            + ", deviceType=" + deviceType + "]";
  }

}
