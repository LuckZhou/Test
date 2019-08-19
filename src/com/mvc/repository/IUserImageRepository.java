package com.mvc.repository;

import java.io.Serializable;

import com.mvc.entity.UserImage;

public interface IUserImageRepository {
	public Serializable addUserImage(UserImage userImage);
}
