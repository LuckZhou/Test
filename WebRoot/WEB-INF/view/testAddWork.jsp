<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试上传作品</title>
</head>
<body>
  <form method="post" action="/myPhoto/addWork" enctype="multipart/form-data">
     <input type="text" name="userId"/><br/>
     <input type="text" name="keywords"/><br/>
     <input type="text" name="workDescribe"/><br/>
     <input type="file" name="files"/><br/>
      <input type="file" name="files"/><br/>
      <input type="file" name="files"/><br/>
     <input type="submit" value="提交"/>
  </form>
</body>
</html>