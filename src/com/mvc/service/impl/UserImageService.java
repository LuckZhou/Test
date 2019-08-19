package com.mvc.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.entity.UserImage;
import com.mvc.repository.IUserImageRepository;
import com.mvc.service.IUserImageService;
@Service
public class UserImageService implements IUserImageService{
	@Resource
    private IUserImageRepository userImageRepository;
	@Override
	public Serializable addUserImage(UserImage userImage) {
		return userImageRepository.addUserImage(userImage);
	}

}
