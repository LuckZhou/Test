package com.mvc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="photoOrder")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6731850865045922283L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = true)
	private long id;
	@ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REMOVE}, optional = false)
	@JoinColumn(name="serviceId")
	private Service service;
	private String userId;
	private Date serviceDate;
	private String  photographerId;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getPhotographerId() {
		return photographerId;
	}
	public void setPhotographerId(String photographerId) {
		this.photographerId = photographerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
