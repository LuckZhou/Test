<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试</title>
</head>
<body>
  <form action="/myPhoto/uploadUserImage" method="post" enctype="multipart/form-data">
     <table>
        <tr>
           <td>图片</td>
           <td><input type="file" name="files"/></td>
        </tr>
         <tr>
           <td>用户id</td>
           <td><input  name="userId"/></td>
        </tr>
         <tr>
           <td>类型</td>
           <td><input  name="type"/></td>
        </tr>
        <tr>
          <td><input type="submit" value="提交"/></td>
        </tr>
     </table>
  </form>
</body>
</html>