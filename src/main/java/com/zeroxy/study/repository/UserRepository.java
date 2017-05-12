package com.zeroxy.study.repository;

import com.zeroxy.CommonResult;
import com.zeroxy.study.domain.User;
import com.zeroxy.study.result.ReturnCode;
import com.zeroxy.util.CommonUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByAreaCodeAndPhone(final String areaCode,final String phone);

    default CommonResult login(User user){
        CommonResult res = null;
        if (user == null || StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getPassword())) {
            return ReturnCode.ERROR_101;
        }

        User u = findByAreaCodeAndPhone(user.getAreaCode(),user.getPhone());
        if (u != null && u.getPhone().equals(user.getPhone())) {
            if (u.getPassword() != null
                    && u.getPassword().equals(CommonUtil.md5(user.getPassword()))) {
                res = new CommonResult(0, "OK");
                user.setId(u.getId());
                user.setEmail(u.getEmail());
                user.setEmailActive(u.getEmailActive());
            } else {
                res = ReturnCode.ERROR_103;
            }
        } else {
            res = ReturnCode.ERROR_102;
        }
        return res;
    }
}
