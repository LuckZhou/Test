package com.mvc.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.Captcha;
import com.mvc.repository.ICaptchaRepository;
@Repository
public class CaptchaRepository extends BaseRepository<Captcha> implements ICaptchaRepository{
	@Override
	public void addCatacha(Captcha captcha) {
		save(captcha);
	}
	@Override
	public List<Captcha> findCaptcha(String email, String code,String type) {
		StringBuffer hql=new StringBuffer();
		hql.append("from captcha where email='");
		hql.append(email);
		hql.append("' and code='");
		hql.append(code);
		hql.append("' and type='");
		hql.append(type);
		hql.append("'");
		return find(hql.toString());
	}
	@Override
	public void updateCaptcha(Captcha captcha) {
		update(captcha);
	}

}
