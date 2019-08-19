package com.mvc.controller;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.entity.Order;
import com.mvc.entity.Service;
import com.mvc.form.OrderForm;
import com.mvc.service.IOrderService;
import com.mvc.vo.UserVo;
@Controller  
public class OrderController { 
	@Resource
	private IOrderService orderService;
	@ResponseBody
	@RequestMapping(value = "/addOrder")
	public UserVo addOrder(@Valid OrderForm orderForm, BindingResult result) throws Exception {
		UserVo userVo = new UserVo();
		if (result.hasErrors()) {
			userVo.setCode("0");
			userVo.setMsg("添加订单失败");
			userVo.setErrorInfo(result.getAllErrors().get(0).getDefaultMessage());
			return userVo;
		}
		Order order = new Order();
		order.setPhotographerId(orderForm.getPhotographerId());
		Service service = new Service();
		service.setId(Long.parseLong(orderForm.getServiceId()));
		order.setService(service);
		order.setStatus("待确认");
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		order.setServiceDate(sdf.parse(orderForm.getServiceDate()));
		order.setUserId(orderForm.getUserId());
		orderService.addOrder(order);
		userVo.setCode("1");
		userVo.setMsg("添加订单成功");
		userVo.setData(order);
		return userVo;
	}
	@ResponseBody
	@RequestMapping(value = "/myOrder")
	public UserVo myOrder(String userId){
		UserVo userVo = new UserVo();
		if (userId == "") {
			userVo.setCode("0");
			userVo.setMsg("查询我的订单失败");
			userVo.setErrorInfo("userId不能为空");
			return userVo;
		}
		List<Order> orderList = orderService.myOrder(userId);
		userVo.setCode("1");
		userVo.setMsg("查询我的订单成功");
		userVo.setData(orderList);
		return userVo;
	}
	@ResponseBody
	@RequestMapping(value = "/getOrder", method = RequestMethod.POST)
	public UserVo getOrder(long orderId){
		UserVo userVo = new UserVo();
		if (orderId == 0) {
			userVo.setCode("0");
			userVo.setMsg("查询我的订单详情失败");
			userVo.setErrorInfo("orderId不能为空");
			return userVo;
		}
		Order order= orderService.getOrder(orderId);
		userVo.setCode("1");
		userVo.setMsg("查询我的订单详情成功");
		userVo.setData(order);
		return userVo;
	}
	@ResponseBody
	@RequestMapping(value = "/photographerOrder", method = RequestMethod.POST)
	public UserVo photographerOrder(String photographerId){
		UserVo userVo = new UserVo();
		if (photographerId == "") {
			userVo.setCode("0");
			userVo.setMsg("查询我的订单失败");
			userVo.setErrorInfo("photographerId不能为空");
			return userVo;
		}
		List<Order> orderList = orderService.myOrder(photographerId);
		userVo.setCode("1");
		userVo.setMsg("查询我的订单成功");
		userVo.setData(orderList);
		return userVo;
	}
	@ResponseBody
	@RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
	public UserVo updateOrderStatus(long orderId){
		UserVo userVo = new UserVo();
		if (orderId == 0) {
			userVo.setCode("0");
			userVo.setMsg("更改订单状态失败");
			userVo.setErrorInfo("orderId不能为空");
			return userVo;
		}
		Order order=orderService.getOrder(orderId);
		if(order==null){
			userVo.setCode("0");
			userVo.setMsg("订单确认失败");
			userVo.setErrorInfo("订单不存在");
			return userVo;
		}
		order.setStatus("已确认");
		orderService.updateStatus(order);
		userVo.setCode("1");
		userVo.setMsg("确认订单成功");
		userVo.setData(order);
		return userVo;
	}
	@ResponseBody
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public UserVo cancelOrder(long orderId){
		UserVo userVo = new UserVo();
		if (orderId == 0) {
			userVo.setCode("0");
			userVo.setMsg("更改订单状态失败");
			userVo.setErrorInfo("orderId不能为空");
			return userVo;
		}
		Order order=orderService.getOrder(orderId);
		if(order==null){
			userVo.setCode("0");
			userVo.setMsg("订单确认失败");
			userVo.setErrorInfo("订单不存在");
			return userVo;
		}
		order.setStatus("已取消");
		orderService.updateStatus(order);
		userVo.setCode("1");
		userVo.setMsg("取消我的订单成功");
		userVo.setData(order);
		return userVo;
	}
	
}
