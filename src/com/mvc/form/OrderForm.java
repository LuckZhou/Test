package com.mvc.form;



import org.hibernate.validator.constraints.NotBlank;

public class OrderForm {
	@NotBlank(message = "userId不能为空")
	private String userId;
	@NotBlank(message = "photographerId不能为空")
	private String photographerId;
	@NotBlank(message = "serviceDate不能为空且要大于今天")
	private String serviceDate;
	@NotBlank(message = "serviceId不能为空")
	private String serviceId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhotographerId() {
		return photographerId;
	}

	public void setPhotographerId(String photographerId) {
		this.photographerId = photographerId;
	}


	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


}
