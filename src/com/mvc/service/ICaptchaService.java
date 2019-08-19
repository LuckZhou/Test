package com.mvc.service;

import java.util.List;

import com.mvc.entity.Captcha;

public interface ICaptchaService {
   public void addCatacha(Captcha captcha);
   public List<Captcha> findCaptcha(String email, String code,String type);
   public void updateCaptcha(Captcha captcha);
}
