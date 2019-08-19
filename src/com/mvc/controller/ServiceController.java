package com.mvc.controller;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mvc.entity.Service;
import com.mvc.entity.User;
import com.mvc.entity.WorkImage;
import com.mvc.form.ServiceForm;
import com.mvc.service.IServiceService;
import com.mvc.service.IWorkImageService;
import com.mvc.util.UUIDUtil;
import com.mvc.vo.UserVo;

@Controller
public class ServiceController {
	@Resource
	private IWorkImageService workImageService;
	@Resource
	private IServiceService serviceService;
	@ResponseBody
	@RequestMapping(value = "/saveService", method = RequestMethod.POST)
	public UserVo saveService(@RequestParam MultipartFile[] files, @Valid ServiceForm serviceForm,
			HttpServletRequest request, BindingResult result) {
		UserVo userVo = new UserVo();
		if (result.hasErrors()) {
			userVo.setCode("0");
			userVo.setMsg("添加服务失败");
			userVo.setErrorInfo(result.getAllErrors().get(0).getDefaultMessage());
			return userVo;
		}
		Service service = new Service();
		User user = new User();
		user.setId(serviceForm.getUserId());
		service.setUser(user);
		service.setServiceType(serviceForm.getServiceType());
		service.setServiceTag(serviceForm.getServiceTag());
		service.setServicePrice(serviceForm.getServicePrice());
		service.setServiceTime(serviceForm.getServiceTime());
		service.setPhotoQuantity(serviceForm.getPhotoQuantity());
		service.setTruingPhotos(serviceForm.getTruingPhotos());
		service.setAppointmentTime(serviceForm.getAppointmentTime());
		service.setAdditionalInstructions(serviceForm.getAdditionalInstructions());
		service.setDetailedDescription(serviceForm.getDetailedDescription());
		Serializable id = serviceService.saveService(service);
		if (files != null && files.length > 0) {
			StringBuffer sb = new StringBuffer();
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "/upload/";
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String newFileName = UUIDUtil.createUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(path + newFileName);
				try {
					file.transferTo(newFile);
					sb.append(newFileName + ",");
					WorkImage workImage = new WorkImage();
					workImage.setUserId(serviceForm.getUserId());
					workImage.setImageName(newFileName);
					workImage.setCreateTime(new Date());
					workImage.setType("servicePhotos");
					workImage.setWorkId(Integer.parseInt(id + ""));
					workImageService.addWorkImage(workImage);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		userVo.setCode("1");
		userVo.setMsg("添加服务成功");
		userVo.setData(service);
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/findAllService")
	public UserVo findAllService() {
		List<Service> serviceList = serviceService.serviceList();
		UserVo userVo = new UserVo();
		userVo.setCode("1");
		userVo.setMsg("查询成功");
		userVo.setData(serviceList);
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/findServiceById", method = RequestMethod.POST)
	public UserVo findServiceById(long id) {
		UserVo userVo = new UserVo();
		Service service = serviceService.findById(id);
		if (service == null) {
			userVo.setCode("0");
			userVo.setMsg("查询服务失败");
			userVo.setErrorInfo("服务不存在");
			return userVo;
		}
		userVo.setCode("1");
		userVo.setMsg("查询服务成功");
		userVo.setData(service);
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/findMyService", method = RequestMethod.POST)
	public UserVo findMyService(String userId) {
		UserVo userVo = new UserVo();
		if (userId == "") {
			userVo.setCode("0");
			userVo.setMsg("查询我的服务失败");
			userVo.setErrorInfo("userId不能为空");
			return userVo;
		}
		List<Service> serviceList = serviceService.findMyService(userId);
		userVo.setCode("1");
		userVo.setMsg("查询我的服务成功");
		userVo.setData(serviceList);
		return userVo;
	}
}
