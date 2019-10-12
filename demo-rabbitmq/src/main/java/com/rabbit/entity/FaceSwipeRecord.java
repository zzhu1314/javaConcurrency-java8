package com.rabbit.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.3.	刷脸流水表
 * @author LiaoYao
 * @date：2018年11月7日
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class FaceSwipeRecord implements Serializable{
	private static final long serialVersionUID = -4014462033898293137L;
	
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Id
	@Column(length=32)
	private String id;
	@Column(nullable=true,length=32)
	private String baseDeviceCode;
	@Column(nullable=true,length=32)
	private String baseAreaCode;
	@Column(nullable=true,length=32)
	private String faceImageId;
	@Column(nullable=true,length=32)
	private String faceLibraryId;
	@Column(nullable=true,length=64)
	private String facePersonCode;
	@Column(nullable= true)
	private Integer codeType;
	@Column(nullable=true)
	private Integer facePersonSex;
	@Column(nullable=true,length=64)
	private String areaName;
	@Column(nullable=true,length=64)
	private String faceName;
	@Column(nullable=true,length=64)
	private String libraryName;
	@Column(nullable=true)
	private Date swipeTime;
	@Column(nullable=true,length=512)
	private String livePhotoPath;
	@Column(nullable=true,length=512)
	private String registPhotoPath;
	@Column(nullable=true)
	private Float threshold;
	@Column(nullable=true)
	private Float matchingScore;
	@Column(nullable=true)
	private Integer passStatus;
//	@Column(nullable=true)
//	private Date passTime;
	@Column(nullable=true)
	private Integer noPassReason;
	@Column(nullable=true)
	private Integer upload;
	@Temporal(TemporalType.TIMESTAMP)//自动格式化 按照yyyy-MM-dd HH:mm:ss
	@Column(updatable = false, name = "create_time")//updatable属性：使用update更新的时候 是否需要更新该字段的值
	@org.hibernate.annotations.CreationTimestamp//插入的时候 创建时间戳
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	@org.hibernate.annotations.UpdateTimestamp
	private Date updateTime;

	@Column
	private Integer isStranger;

public FaceSwipeRecord(){

}

}
