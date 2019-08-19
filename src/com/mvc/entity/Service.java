package com.mvc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="service")
public class Service implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "id", nullable = false)
    private long id;
    private String serviceType;
    private String serviceTag;
    private double servicePrice;
    private String serviceTime;
    private int photoQuantity;
    private int truingPhotos;
    private String appointmentTime;
    private String additionalInstructions;
    private String detailedDescription;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE}, optional = false)
	@JoinColumn(name="userId")
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "workId")
    private List<WorkImage> workImageList;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public double getServicePrice() {
		return servicePrice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public List<WorkImage> getWorkImageList() {
		return workImageList;
	}
	public void setWorkImageList(List<WorkImage> workImageList) {
		this.workImageList = workImageList;
	}
    
}
