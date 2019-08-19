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

import com.mvc.entity.User;
import com.mvc.entity.Work;
import com.mvc.entity.WorkImage;
import com.mvc.form.WorkForm;
import com.mvc.service.IUserService;
import com.mvc.service.IWorkImageService;
import com.mvc.service.IWorkService;
import com.mvc.util.UUIDUtil;
import com.mvc.vo.UserVo;

@Controller
public class WorkController {
	@Resource
	private IWorkService workService;
	@Resource
	private IWorkImageService workImageService;
	@Resource
	private IUserService userService;
	@ResponseBody
	@RequestMapping(value = "/addWork", method = RequestMethod.POST)
	public UserVo addWork(@RequestParam MultipartFile[] files, @Valid WorkForm workform, HttpServletRequest request,
			BindingResult result) {
		UserVo userVo = new UserVo();
		if (result.hasErrors()) {
			userVo.setCode("0");
			userVo.setMsg("发布作品失败");
			userVo.setErrorInfo(result.getAllErrors().get(0).getDefaultMessage());
			return userVo;
		}
		User user = userService.findById(workform.getUserId());
		if (user == null) {
			userVo.setCode("0");
			userVo.setMsg("发布作品失败");
			userVo.setErrorInfo("用户不存在");
			return userVo;
		}
		Work work = new Work();
		work.setWorkDescribe(workform.getWorkDescribe());
		work.setKeywords(workform.getKeywords());
		work.setUserId(workform.getUserId());
		work.setNickName(user.getNickName());
		work.setHeadImage(user.getHeaderPhoto());
		work.setWorkType(workform.getWorkType());
		Serializable id = workService.saveWork(work);
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
					workImage.setUserId(workform.getUserId());
					workImage.setImageName(newFileName);
					workImage.setCreateTime(new Date());
					workImage.setWorkId(Integer.parseInt(id + ""));
					workImage.setType("workPhotos");
					workImageService.addWorkImage(workImage);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		userVo.setCode("1");
		userVo.setMsg("发布作品成功");
		userVo.setData(work);
		return userVo;
	}

	@RequestMapping(value = "/testAddWork")
	public String testAddWork() {
		return "/testAddWork";
	}

	@ResponseBody
	@RequestMapping(value = "/findAlldWork")
	public List<Work> findAll() {
		return workService.workList();
	}

	@ResponseBody
	@RequestMapping(value = "/findWorkById", method = RequestMethod.POST)
	public UserVo findWorkById(long id) {
		UserVo userVo = new UserVo();
		if (id == 0) {
			userVo.setCode("0");
			userVo.setMsg("查询作品失败");
			userVo.setErrorInfo("作品id不能为空");
			return userVo;
		}
		Work work = workService.findById(id);
		if (work == null) {
			userVo.setCode("0");
			userVo.setMsg("查询作品失败");
			userVo.setErrorInfo("作品不存在");
			return userVo;
		}
		userVo.setCode("1");
		userVo.setMsg("查询作品成功");
		userVo.setData(work);
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/findWorkByUserId", method = RequestMethod.POST)
	public UserVo findWorkByUserId(String userId) {
		UserVo userVo = new UserVo();
		if (userId == "") {
			userVo.setCode("0");
			userVo.setMsg("查询作品失败");
			userVo.setErrorInfo("userId不能为空");
			return userVo;
		}
		List<Work> workList = workService.findWorkByUserId(userId);
		userVo.setCode("1");
		userVo.setMsg("查询作品成功");
		userVo.setData(workList);
		return userVo;
	}
}
