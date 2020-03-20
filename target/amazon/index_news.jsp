<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
<script>
	$(function () {
		$.post("news.do", {"param": "queryAll"},
				function (data) {
					var $ul = $(".newsList > ul");// 找到ul元素
					for (var i=0;i<data.length;i++){
						var news = data[i];//取出消息
						var $li = $("<li class='author'><a href='${pageContext.request.contextPath}/news.do?param=queryById&id="+news.id +"'>"+ news.title +"</a></li>");
						$ul.append($li);
					}
				}, "json")
			})
</script>
<style type="text/css">
.news-list ul li {
	
}
</style>
<div class="newsList">
	<h2>新闻动态</h2>
	<ul>
	<%--<li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>--%>
	</ul>
</div>
