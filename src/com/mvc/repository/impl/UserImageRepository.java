package com.mvc.repository.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.UserImage;
import com.mvc.repository.IUserImageRepository;
@Repository
public class UserImageRepository extends BaseRepository<UserImage> implements IUserImageRepository{
	@Override
	public Serializable addUserImage(UserImage userImage) {
		return save(userImage);
	}

}
