<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    
    <form action="user/register.do" method="post" id="registerForm">
		<%--<div>
			<input type="text" name="email" class="email" placeholder="输入邮箱地址" οncοntextmenu="return false" οnpaste="return false" />
		</div>
		<div>
			<input type="text" name="username" class="username" placeholder="您的用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="password" class="password" placeholder="输入密码" οncοntextmenu="return false" οnpaste="return false" />
		</div>
		<div>
			<input type="password" name="confirm_password" class="confirm_password" placeholder="再次输入密码" οncοntextmenu="return false" οnpaste="return false" />
		</div>
		<div>
			<input type="text" name="mobilePhone" class="mobilePhone" placeholder="输入手机号码" autocomplete="off" id="number"/>
		</div>
		--%>
		<div>
			<input type="text"  class="kaptchaImage" name="kaptchaImage" style="width:150px;"
			 placeholder="输入验证码" autocomplete="off"/>
			 
			 <img src="image/captcha-image.do" width="110" height="40" id="kaptchaImage" 
			 	οnclick="changeValidateCode(this);" style="margin-bottom:-15px"/>   
		</div>
 
		<button id="submit" type="submit">注 册</button>
	</form>
  </body>
</html>
