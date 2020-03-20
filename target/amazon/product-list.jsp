<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 产品列表</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
	<link type="text/css" rel="stylesheet" href="css/index.css" />
<script type="text/javascript" src="scripts/function.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
	<script>
		$(function () {
			var param = window.location.href.split("=")[1].split("&")[0];
			if (param=="queryByName"){
				showProductByName(1,12)
			}else {
				showProduct(1,12);
			}
			showProductDet();
		});
		function showProductByName(pageNo,pageSize) {
			var param = window.location.href.split("=")[1].split("&")[0];
			var name = decodeURI(window.location.href.split("=")[2].split("&")[0]);
			$.ajaxSettings.async = false;
			$.post("Product.do",{
						"name":name,
						"pageNo":pageNo,
						"pageSize":pageSize,
						"param":param},
					function (data) {
						var ul = $(".product");
						ul.empty();
						for (var i=0;i<data.list.length;i++) {
							var product = data.list[i];
							var li = $("<li style=float:left;margin:10px;></li>");
							var dl = $("<dl></dl>");
							var dt = $("<dt><a href='Product.do?param=queryById&id=" + product.id + "' target='_self'><img src='" + product.img_Source + "' /></a></dt>");
							var dd1 = $("<dd class='title'><a href='Product.do?param=queryById&id=" + product.id + "' target='_self'>"  + product.name + "</a></dd>");
							var dd2 = $("<dd class='price'>" + "￥" + product.price + "</dd>");
							dl.append(dt).append(dd1).append(dd2);
							li.append(dl);
							ul.append(li);
						}

						var $a = $("#btns > a");
						$a.eq(0).attr("href", "javascript:showProductByName('" + 1 + "','" + pageSize + "')");
						$a.eq(1).attr("href", "javascript:showProductByName('" + data.prePage + "','" + pageSize + "')");
						$a.eq(2).attr("href", "javascript:showProductByName('" + data.nextPage + "','" + pageSize + "')");
						$a.eq(3).attr("href", "javascript:showProductByName('" + data.totalPage + "','" + pageSize + "')");
						var $span = $("#btns > span");
						$span.eq(0).html(data.pageNo);
						$span.eq(1).html(data.totalPage);
					},"json")
		}
		function showProduct(pageNo,pageSize) {
			var param = window.location.href.split("=")[1].split("&")[0];
			var id = window.location.href.split("=")[2];
			$.ajaxSettings.async = false;
			$.post("Product.do",{
						"id":id,
						"pageNo":pageNo,
						"pageSize":pageSize,
						"param":param},
					function (data) {
						var ul = $(".product");
						ul.empty();
				for (var i=0;i<data.list.length;i++) {
					var product = data.list[i];
					var li = $("<li style=float:left;margin:10px;></li>");
					var dl = $("<dl></dl>");
					var dt = $("<dt><a href='Product.do?param=queryById&id=" + product.id + "' target='_self'><img src='" + product.img_Source + "' /></a></dt>");
					var dd1 = $("<dd class='title'><a href='Product.do?param=queryById&id=" + product.id + "' target='_self'>"  + product.name + "</a></dd>");
					var dd2 = $("<dd class='price'>" + "￥" + product.price + "</dd>");
					dl.append(dt).append(dd1).append(dd2);
					li.append(dl);
					ul.append(li);
				}

						var $a = $("#btns > a");
						$a.eq(0).attr("href", "javascript:showProduct('" + 1 + "','" + pageSize + "')");
						$a.eq(1).attr("href", "javascript:showProduct('" + data.prePage + "','" + pageSize + "')");
						$a.eq(2).attr("href", "javascript:showProduct('" + data.nextPage + "','" + pageSize + "')");
						$a.eq(3).attr("href", "javascript:showProduct('" + data.totalPage + "','" + pageSize + "')");
						var $span = $("#btns > span");
						$span.eq(0).html(data.pageNo);
						$span.eq(1).html(data.totalPage);
					},"json")
		}
		function showProductDet() {
			$.post("Product.do",
					{"param":"showProductDetail"},
					function (data) {
						var dl = $("#detail");
						for (var i=0;i<data.length;i++){
							var product = data[i];
							var dt = $("<dt><img style='width:54px;height:54px;' src='"+product.img_Source+"' /></dt>");
							var dd = $("<dd class='price'><a href='Product.do?param=queryById&id="+product.id +"'>"+ product.name +"</a></dd>");
							dl.append(dt).append(dd);
						}
					}, "json");
		}
	</script>
</head>
<body>

	<%@ include file="index_top.jsp"%>
	<div id="position" class="wrap">
		您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; <a
			href="product-list.jsp">图书音像</a> &gt; 图书
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%@ include file="index_product_sort.jsp"%>
			<div class="spacer"></div>
			<div class="last-view">
				<h2>最近浏览</h2>
				<dl class="clearfix" id="detail">

					<%--<dt>
						<img style="width: 54px; height: 54px;" src="images/product/0.jpg" />
					</dt>
					<dd>
						<a href="#">商品名称</a>
					</dd>--%>
				</dl>
			</div>
		</div>
		<div class="main">
			<div class="product-list">
				<h2>全部商品</h2>
				<div class="clear"></div>
				<ul class="product clearfix" style="font-size: 0">
					<%--<li style=float:left;margin:10px;>
						<dl>
							<dt>
								<a href="#" target="_self"><img src="images/product/0.jpg" /></a>
							</dt>
							<dd class="title">
								<a href="#" target="_self">商品名称</a>
							</dd>
							<dd class="price">￥12.34</dd>
						</dl>
					</li>--%>
				</ul>
				<div class="clear"></div>
				<div align="center" style="margin-top:10px" id="btns">
					<a href="#">首页</a>&nbsp;&nbsp;&nbsp;
					<a href="#">上一页</a>&nbsp;&nbsp;
					<span></span>&nbsp;/<span>1/12</span>&nbsp;&nbsp;
					<a href="#">下一页</a>&nbsp;&nbsp;
					<a href="#">最后一页</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海海文 All Rights Reserved.
		京ICP证1000001号</div>
</body>
</html>

