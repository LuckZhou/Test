package com.mvc.controller;

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.core.mail.IMailSender;
import com.core.mail.impl.MailSender;
import com.mvc.entity.Captcha;
import com.mvc.entity.User;
import com.mvc.entity.UserImage;
import com.mvc.form.UserForm;
import com.mvc.service.ICaptchaService;
import com.mvc.service.IUserImageService;
import com.mvc.service.IUserService;
import com.mvc.util.UUIDUtil;
import com.mvc.vo.UserVo;

@Controller
public class UserConstroller {
	@Resource
	private IUserService userService;
	@Resource
	private IUserImageService userImageService;
	@Resource
	private ICaptchaService captchaService;
	@ResponseBody
	@RequestMapping(value = "/loginIn")
	public UserVo loginIn(UserForm userform, HttpSession session) {
		UserVo userVo = new UserVo();
		if (userform.getPassword() == null || userform.getPassword() == "" || userform.getUserName() == null
				|| userform.getUserName() == "") {
			userVo.setCode("0");
			userVo.setMsg("登录失败");
			userVo.setErrorInfo("用户名或密码不能为空");
			return userVo;
		}
		List<User> list = userService.getUserList(userform.getUserName(), userform.getPassword());
		if (list != null && list.size() > 0) {
			userVo.setCode("1");
			userVo.setMsg("登录成功");
			userVo.setData(list.get(0));
			return userVo;
		} else {
			userVo.setCode("0");
			userVo.setMsg("登录失败");
			userVo.setErrorInfo("用户名或密码不正确");
			return userVo;
		}
	}

	@RequestMapping(value = "/toRegister")
	public String toRegister() {
		return "/register";
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public UserVo register(@Valid UserForm userform, BindingResult result) {
		UserVo userVo = new UserVo();
		if (result.hasErrors()) {
			userVo.setCode("0");
			userVo.setMsg("注册失败");
			userVo.setErrorInfo(result.getAllErrors().get(0).getDefaultMessage());
			return userVo;
		}
		User user = new User();
		String id = System.currentTimeMillis() + "";
		user.setNickName(userform.getNickName());
		user.setPassword(userform.getPassword());
		user.setUserName(userform.getUserName());
		user.setId(id);
		userService.addUser(user);
		userVo.setCode("1");
		userVo.setMsg("注册成功");
		userVo.setData(user);
		return userVo;
	}

	@RequestMapping(value = "/toUpload")
	public String toUpload() {
		return "/testUpload";
	}

	@ResponseBody
	@RequestMapping(value = "/updateUserInfo")
	public UserVo updateUserInfo(@Valid UserForm userform, BindingResult result) {
		UserVo userVo = new UserVo();
		if (result.hasErrors()) {
			userVo.setCode("0");
			userVo.setMsg("操作失败");
			userVo.setErrorInfo(result.getAllErrors().get(0).getDefaultMessage());
			return userVo;
		}
		User user = userService.findById(userform.getUserId());
		user.setNickName(userform.getNickName());
		user.setHostCity(userform.getHostCity());
		user.setWeixinId(userform.getWeixinId());
		user.setRemark(userform.getRemark());
		user.setSex(userform.getSex());
		userService.updateUser(user);
		userVo.setCode("1");
		userVo.setMsg("操作成功");
		userVo.setData(user);
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadUserImage")
	public UserVo uploadUserImage(@RequestParam MultipartFile[] files, HttpServletRequest request) {
		if (files != null && files.length > 0) {
			StringBuffer sb = new StringBuffer();
			String path = request.getSession().getServletContext().getRealPath("/");
			String userId = request.getParameter("userId");
			path = path + "/upload/";
			for (MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String newFileName = UUIDUtil.createUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(path + newFileName);
				try {
					file.transferTo(newFile);
					sb.append(newFileName + ",");
					UserImage userImage = new UserImage();
					userImage.setUserId(userId);
					userImage.setImageName(newFileName);
					userImage.setCreateTime(new Date());
					Serializable imageId=userImageService.addUserImage(userImage);
					User user = userService.findById(userId);
					user.setHeaderPhoto(newFileName+"");
					userService.updateUser(user);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		UserVo userVo = new UserVo();
		userVo.setCode("1");
		userVo.setMsg("上传成功");
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/sendCode")
	public UserVo sendCode(@RequestParam String email) {
		User user = userService.findById(email);
		UserVo userVo = new UserVo();
		if (user == null) {
			userVo.setCode("0");
			userVo.setMsg("邮件发送失败，用户不存在");
			return userVo;
		}
		if (user.getEmail() == null || user.getEmail() == "") {
			userVo.setCode("0");
			userVo.setMsg("邮件发送失败，用户不存在");
			return userVo;
		}
		IMailSender mailSender = new MailSender();
		Random random = new Random();
		int x = random.nextInt(899999) + 10000;
		mailSender.simpleMailSend(email, "myPhoto找回密码邮件", "忘记密码验证码" + x + "，验证码有效期为30分钟。如果非本人操作，请忽略此邮件！");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long now = System.currentTimeMillis() + 1800000;
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(now);
		Captcha captcha = new Captcha();
		captcha.setCode(x + "");
		captcha.setEmail(email);
		captcha.setType("findPassword");
		captcha.setUseStatus("0");
		captcha.setSendTime(new Date());
		captcha.setPassTime(calendar.getTime());
		captchaService.addCatacha(captcha);
		userVo.setCode("1");
		userVo.setMsg("邮件发送成功");
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/updatePassword")
	public UserVo updatePassword(@RequestParam String userId, @RequestParam String oldPassword,
			@RequestParam String newPassword) {
		UserVo userVo = new UserVo();
		if (userId == null || userId == "") {
			userVo.setCode("0");
			userVo.setMsg("修改密码失败");
			userVo.setErrorInfo("用户id不能为空");
			return userVo;
		}
		if (oldPassword == null || oldPassword == "") {
			userVo.setCode("0");
			userVo.setMsg("修改密码失败");
			userVo.setErrorInfo("旧密码不能为空");
			return userVo;
		}
		if (newPassword == null || newPassword == "") {
			userVo.setCode("0");
			userVo.setMsg("修改密码失败");
			userVo.setErrorInfo("新密码不能为空");
			return userVo;
		}
		List<User> list = userService.getUserList(userId, oldPassword);
		if (list.size() < 0) {
			userVo.setCode("0");
			userVo.setMsg("修改密码失败");
			userVo.setErrorInfo("旧密码不正确");
			return userVo;
		}
		User user = list.get(0);
		user.setPassword(newPassword);
		userService.updateUser(user);
		userVo.setCode("1");
		userVo.setMsg("修改密码成功");
		return userVo;
	}

	@ResponseBody
	@RequestMapping(value = "/findPassword")
	public UserVo findPassword(@RequestParam String email, @RequestParam String code,
			@RequestParam String newPassword) {
		UserVo userVo = new UserVo();
		List<Captcha> list = captchaService.findCaptcha(email, code, "findPassword");
		if (list.size() <= 0) {
			userVo.setCode("0");
			userVo.setMsg("修改密码失败");
			userVo.setErrorInfo("验证码不正确");
			return userVo;
		} else {
			Captcha captcha = list.get(0);
			if (captcha != null) {
				Date nowDate = new Date();
				Date passDate = captcha.getPassTime();
				if (passDate.before(nowDate) || captcha.getUseStatus().equals("1")) {
					userVo.setCode("0");
					userVo.setMsg("修改密码失败");
					userVo.setErrorInfo("验证码已失效");
					return userVo;
				}
				captcha.setUseStatus("1");
				captchaService.updateCaptcha(captcha);
				userVo.setCode("1");
				userVo.setMsg("找回密码成功");
				return userVo;
			}

		}
		return null;
	}
}
