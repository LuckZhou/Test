package com.mvc.form;

import org.hibernate.validator.constraints.NotBlank;

public class ServiceForm{
	@NotBlank(message="serviceType不能为空")  
	private String serviceType;
    @NotBlank(message="serviceTag不能为空")  
	private String serviceTag;
    @NotBlank(message="userId不能为空")  
	private String userId;
	private double servicePrice;
	private String serviceTime;
	private int photoQuantity;
	private int truingPhotos;
	private String appointmentTime;
	private String additionalInstructions;
	private String detailedDescription;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceTag() {
		return serviceTag;
	}

	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getPhotoQuantity() {
		return photoQuantity;
	}

	public void setPhotoQuantity(int photoQuantity) {
		this.photoQuantity = photoQuantity;
	}

	public int getTruingPhotos() {
		return truingPhotos;
	}

	public void setTruingPhotos(int truingPhotos) {
		this.truingPhotos = truingPhotos;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getAdditionalInstructions() {
		return additionalInstructions;
	}

	public void setAdditionalInstructions(String additionalInstructions) {
		this.additionalInstructions = additionalInstructions;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

}
