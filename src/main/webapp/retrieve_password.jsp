
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
	<script>
		$(function () {
			$(".ui-green").click(function () {
				$.post("user.do",
						{"param":"retrievePwd",
						"userName":$("#userName").val(),
						"idCard":$("#idCard").val(),
						"email":$("#email").val()
						}
						, function (data) {
							if (data.info == "找回成功") {
								window.location = "${pageContext.request.contextPath}/back.jsp";
							} else {
								alert("找回失败")
							}
						}, "json")

			})
		});
	</script>
</head>
<body>
<%@ include file="index_top.jsp" %>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>请键入找回密码的相关信息：</h1>
			<form id="loginForm">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" id="userName" name="userName"  /><span></span></td>
					</tr>
					<tr>
						<td class="field">证件号：</td>
						<td><input class="text" type="text" id="idCard" name="idCard"  /><span></span></td>
					</tr>
					<tr>
						<td class="field">邮箱：</td>
						<td><input class="text" type="text" id="email" name="email"  /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="button" name="submit" value="提交" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved. 
</div>
</body>
</html>
