<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

</head>

<body>
<shiro:user>
欢迎[<shiro:principal/>]登录， <a href="">退出</a>
</shiro:user>
<br/>
<shiro:hasPermission name="/userList">
 用户[<shiro:principal/>]拥有权限 /userList<br/>
</shiro:hasPermission>
<shiro:hasPermission name="/toRegister">
 用户[<shiro:principal/>]拥有权限 /toRegister<br/>
</shiro:hasPermission>
<shiro:hasPermission name="/toDeleteUser">
用户[<shiro:principal/>]拥有权限 /toDeleteUser<br/>
</shiro:hasPermission>

</body>

</html>
