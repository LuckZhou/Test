package com.mvc.controller;
import javax.annotation.Resource;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.entity.Permission;
import com.mvc.service.IPermissionService;
import com.mvc.util.UUIDUtil;
import com.mvc.vo.UserVo;
@Controller
public class RoleController {
   @Resource
   private IPermissionService permissionService;
   @ResponseBody
   @RequestMapping(value = "/authorize", method = RequestMethod.POST)
   public UserVo authorize(String userId,String resourceIds){
	   Permission permission=new Permission();
	   permission.setId(UUIDUtil.createUUID());
	   permission.setUserName("15702945805");
	   permission.setUrl("/toDeleteUser");
	   permissionService.savePermission(permission);
	   UserVo userVo = new UserVo();
	   userVo.setCode("1");
	   userVo.setMsg("授权成功");
	   return userVo;
   }
}
