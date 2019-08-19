package com.mvc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="work")
public class Work implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String keywords;
	private String workDescribe;
	private String userId;
	private String nickName;
	private String headImage;
    private String workType;
	private List<WorkImage> workImage;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkDescribe() {
		return workDescribe;
	}

	public void setWorkDescribe(String workDescribe) {
		this.workDescribe = workDescribe;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "workId")
	public List<WorkImage> getWorkImage() {
		return workImage;
	}

	public void setWorkImage(List<WorkImage> workImage) {
		this.workImage = workImage;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

}
