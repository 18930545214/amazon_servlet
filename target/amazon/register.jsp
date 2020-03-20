<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 注册页</title>
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
				if (checkForm(document.getElementById("regForm"))){
						$.post("user.do",
						$('#regForm').serialize()
						, function (data) {
							if (data.info == "注册成功") {
								window.location = "${pageContext.request.contextPath}/reg-result.jsp";
							} else {
								var msgBox = document.getElementById("Code");
								msgBox.style.display = "inline";
								msgBox.innerHTML = data.info;
							}
						}, "json")
				}
			})
		});

		function checkValidateCode() {
			var veryCode=$("[name=veryCode]").val();
			var flag = false;
			$.ajaxSettings.async = false;
			$.post("user.do",{"param": "checkCode", "veryCode": veryCode},
					function (data) {
						if (data.info == "验证码正确") {
							var msgBox = document.getElementById("Code");
							msgBox.style.display = "";
							msgBox.innerHTML = "";
							flag = true;
							return true;
						} else {
							imgCode.src="code.jsp?timestamp="+new Date().getTime();
							var msgBox = document.getElementById("Code");
							msgBox.style.display = "inline";
							msgBox.innerHTML = data.info;
							flag = false;
							return false
						}
					}, "json")
			return flag;
		}
		function checkUserName() {
			var userName = $("[name=userName]").val();
			var flag = false;
			$.ajaxSettings.async = false;
			$.post("user.do", {
						"param": "CheckUserName",
						"userName": userName},
					function (data) {
						if (data.info == "success") {
							var msgBox = document.getElementById("name");
							msgBox.style.display = "";
							msgBox.innerHTML = "";
							flag = true;
							return true;
						} else {
							var msgBox = document.getElementById("name");
							msgBox.style.display = "inline";
							msgBox.innerHTML = "用户名已存在！";
							flag = false;
							return false;
						}
					});
			return flag;
		}
		function emailExist() {
			var email=$("[name=email]").val();
			var flag = false;
			$.ajaxSettings.async = false;
			$.post("user.do", {
						"param": "CheckEmail",
						"email": email},
					function (data) {
						if (data.info == "success") {
							var msgBox = document.getElementById("err_email");
							msgBox.style.display = "";
							msgBox.innerHTML = "";
							flag = true;
							return true;
						} else {
							var msgBox = document.getElementById("err_email");
							msgBox.style.display = "inline";
							msgBox.innerHTML = "该邮箱已经注册！";
							flag = false;
							return false;
						}
					});
			return flag;
		}
		function CheckItem(obj) {
			obj.parentNode.parentNode.className = "";
			var msgBox = obj.parentNode.getElementsByTagName("span")[0];
			var regEmail = /^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
			var regIdentity = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
			var regMobile = /^1\d{10}$/;
			var regBirth = /^((19\d{2})|(200\d))-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2]\d|3[0-1])$/;
			var regName = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
			var regPass = /^[a-zA-Z0-9]{4,10}$/;
			switch (obj.name) {
				case "userName":
					if (obj.value == "" || regName.test(obj.value) == false) {
						msgBox.innerHTML = "用户名不能为空并且只能是字母开头和字母数字结尾，长度在4-15之间";
						msgBox.className = "error";
						return false;
					}else {
						return checkUserName();
					}
					break;
				case "passWord":
					if (obj.value == "" || regPass.test(obj.value) == false) {
						msgBox.innerHTML = "密码不能为空并且不能含有非法字符，长度在4-10之间";
						msgBox.className = "error";
						return false;
					}
					break;
				case "rePassWord":
					if (obj.value == "") {
						msgBox.innerHTML = "确认密码不能为空";
						msgBox.className = "error";
						return false;
					} else if (obj.value != document.getElementById("passWord").value) {
						msgBox.innerHTML = "两次输入的密码不相同";
						msgBox.className = "error";
						return false;
					}
					break;
				case "veryCode":
					if (obj.value == "") {
						msgBox.innerHTML = "验证码不能为空";
						msgBox.className = "error";
						return false;
					}else{
						return checkValidateCode();
					}
					break;
				case "birthday":
					if (obj.value == "" || regBirth.test(obj.value) == false) {
						msgBox.innerHTML = "出生日期不能空,格式为（1990-01-01）";
						msgBox.className = "error";
						return false;
					}
					break;
				case "identity":
					if (obj.value == "" || regIdentity.test(obj.value) == false) {
						msgBox.innerHTML = "输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X";
						msgBox.className = "error";
						return false;
					}
					break;
				case "email":
					if (obj.value == "" || regEmail.test(obj.value) == false) {
						msgBox.innerHTML = "电子邮件不能为空,格式为web@sohu.com";
						msgBox.className = "error";
						return false;
					}else{
						return emailExist();
					}
					break;
				case "mobile":
					if (regMobile.test(obj.value) == false) {
						msgBox.innerHTML = "手机不能为空必须为11位并且只能是数字";
						msgBox.className = "error";
						return false;
					}
					break;
				case "address":
					if (obj.value == "") {
						msgBox.innerHTML = "地址不能为空";
						msgBox.className = "error";
						return false;
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
		function checkForm(frm) {
			var els = frm.getElementsByTagName("input");
			var flag = true;
			for (var i = 0; i < els.length; i++) {
				if (!CheckItem(els[i]))
					flag = false;
			}
			return flag;
		}
	</script>
</head>
<body>
<%@ include file="index_top.jsp"  %>

<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册亚马逊</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input  class="text" type="text" id="userName" name="userName" onfocus="FocusItem(this)"  onblur="CheckItem(this)"/><span id="name"></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="passWord" onfocus="FocusItem(this)" onblur="CheckItem(this)" /><span id="pwd"></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" id="rePassWord" name="rePassWord" onfocus="FocusItem(this)" onblur="CheckItem(this)" /><span id="affirm"></span></td>
					</tr>
					<tr>
						<td class="field">性别：</td>
						<td ><input type="radio" name="sex" id="sex" style="border:0px;" checked="checked" value="男" />男<input type="radio" name="sex"  style="border:0px;" value="女"/>女<span></span></td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td><input class="text" type="text" name="birthday" id="birthday" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="err_birthday"></span></td>
					</tr>
					<tr>
						<td class="field">身份证：</td>
						<td><input class="text" type="text" name="identity" id="identity" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="idCard"></span></td>
					</tr>
					<tr>
						<td class="field">电子邮件：</td>
						<td><input class="text" type="text" name="email" id="email" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="err_email"></span></td>
					</tr>
					<tr>
						<td class="field">手机：</td>
						<td><input class="text" type="text" name="mobile" id="mobile" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="err_mobile"></span></td>
					</tr>
					<tr>
						<td class="field">地址：</td>
						<td><input class="text" type="text" name="address" id="address" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span id="err_address"></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text veryCode" type="text" name="veryCode" id="vCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" maxlength="4"/><a href=""><img id="veryCode" src="code.jsp" /></a><span id="Code"></span></td>
					</tr>
					<tr>
						<td><input type="hidden" name="param" id="param" value="register"/></td>
						<td><label class="ui-green"><input type="button" name="submit" value="提交注册" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>

