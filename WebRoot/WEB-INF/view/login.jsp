<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>登陆</title>
<link rel="stylesheet" href="css/index.css">
<style type="text/css">
  a{text-decoration:none;text-align:center;}
</style>
</head>
<body >

<div class="login-container">
	<h1>后台管理系统</h1>
	<div class="connect">
		<p>登录</p>
	</div>
	<form action="loginin" method="post" id="loginForm">
		<div>
			<input type="text" name="username" class="phone_number" placeholder="用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<button id="submit" type="submit">登 陆</button>
		<!--  <div style="width:100px;"><input type="checkbox" value="" style="width:50px;"/></div>
		 <div style="float:left;margin:-30px;0px;0px;60px;margin-top: -30px;margin-left: 60px;">记住我</div> -->
		 <br/>
		<!-- <a href="toRegister">注册</a>&nbsp;&nbsp;<a href="javascript:void(0)" style="margin-left: 50px;">忘记密码？</a> -->
		<p>${msg }</p>
	</form>
</div>

<div id="footer" style="font-size:12px;width:100%;bottom:30px;text-align:center;position:fixed;">
  版权所有 2016&nbsp;
</div>
</body>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/common.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<!--表单验证-->
<script src="js/jquery.validate.min.js?var1.14.0"></script>
<script type="text/javascript">
    function kickout(){
       var href=location.href;
       if(href.indexOf("kickout")>0){
           alert("您的账号在另一台设备上登录，您被挤下线，若不是您本人操作，请立即修改密码！");
       } 
    }
    window.onload=kickout(); 
</script>
</html>
