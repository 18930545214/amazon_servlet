<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 新闻显示</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
<script>


</script>


</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="position" class="wrap">
	您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; 阅读新闻
</div>
<div id="main" class="wrap">
	<div class="left-side">
		<%@ include file="index_news.jsp" %>
	</div>
		<div id="news" class="right-main">
				<h1>标题:${requestScope.news.title}</h1>
				<div class="content">
					<p style="text-align: right;">创建时间：${requestScope.news.create_Time}</p>
					内容：${requestScope.news.content}
				</div>
		</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved.
</div>
</body>
</html>

