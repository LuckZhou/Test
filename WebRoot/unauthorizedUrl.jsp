<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> 您无权访问此页面</title>
    

  </head>
  
  <body>
   抱歉， 您无权访问此页面！<br>
  <a href="toLogin">重新登录</a> 
  </body>
</html>
