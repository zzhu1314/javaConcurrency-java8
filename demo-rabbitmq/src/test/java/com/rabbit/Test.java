package com.rabbit;

import com.rabbit.dao.FaceSwipeRecordDao;
import com.rabbit.entity.FaceSwipeRecord;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/9  13:06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private FaceSwipeRecordDao faceSwipeRecordDao;
   @Autowired
   @Qualifier("platform-redis")
   private RedisTemplate redisTemplate;
    @Autowired
    private FaceSwipeRecordDao swipeRecordDao;
    @org.junit.Test
    public void test() throws ParseException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FaceSwipeRecord> query = criteriaBuilder.createQuery(FaceSwipeRecord.class);
        Root<FaceSwipeRecord> root = query.from(FaceSwipeRecord.class);
        TypedQuery<FaceSwipeRecord> query1 = entityManager.createQuery(query);
        query1.setFirstResult(1);
        query1.setMaxResults(3);
        List<FaceSwipeRecord> resultList = query1.getResultList();
        System.out.println(resultList);

    }

    @org.junit.Test
    public void test01 (){
        List<FaceSwipeRecord> all = faceSwipeRecordDao.findAll((Specification<FaceSwipeRecord>) (root, criteriaQuery, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(root.get("areaName").as(String.class), "%" + "总工会" + "%"));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        System.out.println(all);
    }
}
