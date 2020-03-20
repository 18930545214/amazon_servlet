<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 登录</title>
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
			if (checkForm(document.getElementById("loginForm"))){
				login();
			}
		});

	});
	function checkForm(frm) {
		var els = frm.getElementsByTagName("input");
		var flag = true;
		for (var i = 0; i < els.length; i++) {
			if (!CheckItem(els[i]))
				flag = false;
		}
		return flag;
	}
	function checkValidateCode() {
		var flag = false;
		var veryCode=$("[name=veryCode]").val();
		$.ajaxSettings.async = false;
		$.post("user.do",{"param": "checkCode", "veryCode": veryCode},
				function (data) {
					if (data.info == "验证码正确") {
						flag = true;
					}else {
						var msgBox = document.getElementById("Code");
						msgBox.style.display = "inline";
						msgBox.innerHTML = "验证码错误";
						imgCode.src="code.jsp?timestamp="+new Date().getTime();
					}
				}, "json");
		return flag;
	}
	function login() {
		$.post("user.do",
				$('#loginForm').serialize(),
				function (data) {
					if (data.info == "登录成功") {
						window.location = "${pageContext.request.contextPath}/index.jsp";
					} else {
						var msgBox = document.getElementById("Code");
						msgBox.style.display = "inline";
						msgBox.innerHTML = data.info;
					}
				}, "json")

	}
	function CheckItem(obj) {
		obj.parentNode.parentNode.className = "";
		var msgBox = obj.parentNode.getElementsByTagName("span")[0];
		var regName = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
		var regPass = /^[a-zA-Z0-9]{4,10}$/;
		switch (obj.name) {
			case "userName":
				if (obj.value == "" || regName.test(obj.value) == false) {
					msgBox.innerHTML = "用户名不能为空并且只能是字母开头和字母数字结尾，长度在4-15之间";
					msgBox.className = "error";
					return false;
				}
				break;
			case "passWord":
				if (obj.value == "" || regPass.test(obj.value) == false) {
					msgBox.innerHTML = "密码不能为空并且不能含有非法字符，长度在4-10之间";
					msgBox.className = "error";
					return false;
				}
				break;
			case "veryCode":
				if (obj.value == "") {
					msgBox.innerHTML = "验证码不能为空";
					msgBox.className = "error";
					return false;
				} else {
					return checkValidateCode();
				}
				break;
		}
		return true;

	}
	var imgCode;
	window.onload=function (ev) {
		imgCode=document.getElementById("veryCode");
		imgCode.onclick=function (ev1) {
			//修改对象中的src属性值
			imgCode.src="code.jsp?timestamp="+new Date().getTime();
			return false;//阻止元素的默认行为
		}
	};
</script>
</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎回到亚马逊</h1>
			<form id="loginForm">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text userName" type="text" name="userName" onfocus="FocusItem(this)"  onblur="CheckItem(this)" /><span id="name"></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" name="passWord" onfocus="FocusItem(this)"  onblur="CheckItem(this)" /><span id="pwd"></span><a href="retrieve_password.jsp">忘记密码</a></td>
		
					</tr>
					<tr>           
						<td class="field">验证码：</td>
						<td><input class="text veryCode" type="text" name="veryCode" maxlength="4" onfocus="FocusItem(this)"  onblur="CheckItem(this)" /><a href="#"><img id="veryCode" src="code.jsp" /></a><span id="Code"></span></td>
					</tr>
					<tr>
						<td><input type="hidden" name="param" id="param" value="login"/></td>
						<td><label class="ui-green"><input type="button" name="submit" value="立即登录" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018  上海海文 All Rights Reserved
</div>
</body>
</html>
