
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	double num = Math.random();
%>
<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
<script>
	$(function () {
		$(".query_button").click(function () {
			var qname = $("#qname").val();
			window.location.href = encodeURI("product-list.jsp?param=queryByName&name=" + qname);
		});

	});
	function tips() {
		if (${sessionScope.user!=null}){
			window.location.href = "shopping.jsp";
		}else {
			alert("请先登录亚马逊！");
			window.location.href="login.jsp"
		}
	}
</script>

<div id="header">s
	<div class="login_menu">
		<div class="login_container">
			<ul class="m_left">
				<c:if test="${sessionScope.user!=null}">
					<li><a href="orders_view.jsp">当前用户: </a><a class="c_red">${sessionScope.user.UName}</a>&nbsp;&nbsp;&nbsp;</li>
					<li><a href="${pageContext.request.contextPath}/user.do?param=logout">退出</a></li>
				</c:if>
				<c:if test="${sessionScope.user==null}">
					<li><a href="login.jsp" class="c_red">请登录</a>&nbsp;&nbsp;&nbsp;</li>
					<li><a href="register.jsp">请注册</a></li>
				</c:if>
			</ul>
			<ul class="m_right">
				<li><img src="images/icon_3.png"><a
					href="javascript:tips()" class="c_red">购物车</a></li>
				<li><img src="images/icon_4.png"><a
					href="javascript:AddFavorite('我的网站',location.href)">收藏</a></li>
				<li><img src="images/icon_2.png"><a href="guestbook.jsp">留言</a></li>
				<li><img src="images/icon_1.png"><a href="index.jsp">首页</a></li>
			</ul>
		</div>
	</div>
	<div class="logo_search">
		<div class="logo">amz_comment
			<img src="images/LOGO.png">
		</div>
		<div class="search">
			<input type="text" placeholder="输入宝贝" id="qname" />
			<button class="query_button" onclick="">搜索</button>
		</div>
	</div>
	<div class="nav_bar">
		<div class="nav_bar_container">
			<ul>
				<li><a href="#">商品名称</a></li>
				<li><a href="#">商品名称</a></li>
				<li><a href="#">商品名称</a></li>
				<li><a href="#">商品名称</a></li>
			</ul>
		</div>
	</div>
</div>
