package com.rabbit.dao;

import com.rabbit.entity.FaceSwipeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author:zhuzhou
 * @Date: 2019/9/12  11:10
 **/
@Repository
public interface FaceSwipeRecordDao extends JpaRepository<FaceSwipeRecord,String>, JpaSpecificationExecutor<FaceSwipeRecord> {
}
