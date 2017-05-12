package com.zeroxy.study.repository;

import com.zeroxy.study.springcontext.ContextUtils;
import com.zeroxy.study.domain.PhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface PhoneCodeRepository extends JpaRepository<PhoneCode, Long> {
//
//    @PersistenceContext
//    EntityManager em;

    @Query("select count(c) from PhoneCode c where c.phone=:phone and c.areaCode=:areaCode and c.ctime > :ctime")
    int countByAreaCodeAndPhoneAndMoreThenCtime(@Param("areaCode")String areaCode, @Param("phone")String phone, @Param("ctime")long ctime);

    default PhoneCode findLastCodeByAreaCodeAndPhone(String areaCode, String phone){
        EntityManager em = ContextUtils.getBean(EntityManager.class);
        javax.persistence.Query query = em.createQuery("select c from PhoneCode c where c.phone = ?1 and c.status = 1 and c.ctime > ?2 order by id desc", PhoneCode.class).setParameter(1, phone).setParameter(2, System.currentTimeMillis()-24*60*60*1000);
        query.setFirstResult(0).setMaxResults(1);
        @SuppressWarnings("unchecked")
        List<PhoneCode> phoneCodes = query.getResultList();
        PhoneCode phoneCode = phoneCodes == null ? null :(phoneCodes.isEmpty()?null:phoneCodes.get(0));
        return phoneCode ;
    }
}
