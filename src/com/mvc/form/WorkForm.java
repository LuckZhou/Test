package com.mvc.form;

import org.hibernate.validator.constraints.NotBlank;

public class WorkForm {
	@NotBlank(message="userId不能为空")  
    private String userId;
	@NotBlank(message="keywords不能为空")  
    private String keywords;
    private String workDescribe;
    @NotBlank(message="workType不能为空")  
    private String workType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getWorkDescribe() {
		return workDescribe;
	}
	public void setWorkDescribe(String workDescribe) {
		this.workDescribe = workDescribe;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
    
}
