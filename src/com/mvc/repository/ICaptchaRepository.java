package com.mvc.repository;

import java.util.List;

import com.mvc.entity.Captcha;

public interface ICaptchaRepository {
	public void addCatacha(Captcha captcha); 
	public List<Captcha> findCaptcha(String email, String code,String type);
	public void updateCaptcha(Captcha captcha);
}
