package com.mvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.entity.Captcha;
import com.mvc.repository.ICaptchaRepository;
import com.mvc.service.ICaptchaService;
@Service
public class CaptchaService implements ICaptchaService{
	@Resource
    private ICaptchaRepository captchaRepository;
	@Override
	public void addCatacha(Captcha captcha) {
		captchaRepository.addCatacha(captcha);
	}
	@Override
	public List<Captcha> findCaptcha(String email, String code,String type) {
		return captchaRepository.findCaptcha(email, code, type);
	}
	@Override
	public void updateCaptcha(Captcha captcha) {
		captchaRepository.updateCaptcha(captcha);
	}

}
