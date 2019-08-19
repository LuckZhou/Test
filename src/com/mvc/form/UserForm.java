package com.mvc.form;

import org.hibernate.validator.constraints.NotBlank;

public class UserForm {
	private String userId;
	@NotBlank(message = "userName不能为空")
	private String userName;
	private String password;
	@NotBlank(message = "nickName不能为空")
	private String nickName;
	private String headerPhoto;
	private String weixinId;
	private String sex;
	private String remark;
	private String hostCity;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeaderPhoto() {
		return headerPhoto;
	}

	public void setHeaderPhoto(String headerPhoto) {
		this.headerPhoto = headerPhoto;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHostCity() {
		return hostCity;
	}

	public void setHostCity(String hostCity) {
		this.hostCity = hostCity;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
