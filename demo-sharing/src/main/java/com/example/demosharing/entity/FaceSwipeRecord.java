package com.example.demosharing.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 3.3.	刷脸流水表
 * @author LiaoYao
 * @date：2018年11月7日
 */
@Entity
public class FaceSwipeRecord implements Serializable{
	private static final long serialVersionUID = -4014462033898293137L;
	
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy="uuid")
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

	public Integer getIsStranger() {
		return isStranger;
	}

	public void setIsStranger(Integer isStranger) {
		this.isStranger = isStranger;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBaseDeviceCode() {
		return baseDeviceCode;
	}
	public void setBaseDeviceCode(String baseDeviceCode) {
		this.baseDeviceCode = baseDeviceCode;
	}
	public String getBaseAreaCode() {
		return baseAreaCode;
	}
	public void setBaseAreaCode(String baseAreaCode) {
		this.baseAreaCode = baseAreaCode;
	}
	public String getFaceImageId() {
		return faceImageId;
	}
	public void setFaceImageId(String faceImageId) {
		this.faceImageId = faceImageId;
	}
	
	public String getFacePersonCode() {
		return facePersonCode;
	}
	public void setFacePersonCode(String facePersonCode) {
		this.facePersonCode = facePersonCode;
	}
	
	public Integer getFacePersonSex() {
		return facePersonSex;
	}
	public void setFacePersonSex(Integer facePersonSex) {
		this.facePersonSex = facePersonSex;
	}
	public Integer getUpload() {
		return upload;
	}
	public void setUpload(Integer upload) {
		this.upload = upload;
	}
	public String getFaceLibraryId() {
		return faceLibraryId;
	}
	public void setFaceLibraryId(String faceLibraryId) {
		this.faceLibraryId = faceLibraryId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getFaceName() {
		return faceName;
	}
	public void setFaceName(String faceName) {
		this.faceName = faceName;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public Date getSwipeTime() {
		return swipeTime;
	}
	public void setSwipeTime(Date swipeTime) {
		this.swipeTime = swipeTime;
	}
	public String getLivePhotoPath() {
		return livePhotoPath;
	}
	public void setLivePhotoPath(String livePhotoPath) {
		this.livePhotoPath = livePhotoPath;
	}
	public String getRegistPhotoPath() {
		return registPhotoPath;
	}
	public void setRegistPhotoPath(String registPhotoPath) {
		this.registPhotoPath = registPhotoPath;
	}
	public Float getThreshold() {
		return threshold;
	}
	public void setThreshold(Float threshold) {
		this.threshold = threshold;
	}
	public Float getMatchingScore() {
		return matchingScore;
	}
	public void setMatchingScore(Float matchingScore) {
		this.matchingScore = matchingScore;
	}
	public Integer getPassStatus() {
		return passStatus;
	}
	public void setPassStatus(Integer passStatus) {
		this.passStatus = passStatus;
	}
	public Integer getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(Integer noPassReason) {
		this.noPassReason = noPassReason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getCodeType() {
		return codeType;
	}
	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
	@Override
	public String toString() {
		return "FaceSwipeRecord [id=" + id + ", baseDeviceCode=" + baseDeviceCode + ", baseAreaCode=" + baseAreaCode
				+ ", faceImageId=" + faceImageId + ", faceLibraryId=" + faceLibraryId + ", facePersonCode="
				+ facePersonCode + ", areaName=" + areaName + ", faceName=" + faceName + ", libraryName=" + libraryName
				+ ", swipeTime=" + swipeTime + ", livePhotoPath=" + livePhotoPath + ", threshold=" + threshold
				+ ", matchingScore=" + matchingScore + ", passStatus=" + passStatus 
				+ ", noPassReason=" + noPassReason + ", upload=" + upload + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}
