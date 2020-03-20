
<%@ page language="java" import="java.util.*" isELIgnored="false" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊-首页</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js"
	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js" type="text/javascript"></script>
	<script>
		$(function () {
			showProduct(1,12);
			showProductDetail();
		});
		function showProduct(pageNo,pageSize) {
			$.post("Product.do",{
						"param":"queryAll",
						"pageNo":pageNo,
						"pageSize":pageSize},
					function (data) {
						var ul = $(".product2");
						ul.empty();
						for (var i=0;i<data.list.length;i++){
							var product = data.list[i];
							var li = $("<li></li>");
							var dl = $("<dl></dl>");
							var dt = $("<dt><a href='Product.do?param=queryById&id="+product.id +"' target='_self'><img src='"+product.img_Source+"' />"+ product.name +"</a></dt>");
							var dd = $("<dd class='price'>"+"￥"+product.price +"</dd>");
							dl.append(dt).append(dd);
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
		function showProductDetail() {
			$.post("Product.do",
					{"param":"showProductDetail"},
					function (data) {
						var dl = $(".pre_look > dl");
						for (var i=0;i<data.length;i++){
							var product = data[i];
							var dt = $("<dt><a href='Product.do?param=queryById&id="+product.id +"'><img style='width:54px;height:54px;' src='"+product.img_Source+"' /></a></dt>");
							var dd = $("<dd class='price'><a href=''>"+ product.name +"</a><br><a href=''>"+"￥"+product.price +"</a></dd>");
							dl.append(dt).append(dd);
						}
					}, "json");
		}
	</script>

</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="middle">
		<div class="p_left">
			<!--商品分类-->
			<%@ include file="index_product_sort.jsp"%>
			<!--商品分类结束-->

			<div class="pre_look">
				<h3>最近浏览</h3>
				<dl>
					<%--<dt>
						<img style="width: 54px; height: 54px;" src="" />
					</dt>
					<dd>
						<a href="">￥222</a><br><a>上衣</a>
					</dd>--%>
				</dl>
			</div>
		</div>

		<div class="p_center">
			<div id="wrapper">
				<div id="focus">
					<ul>
						<li><a href="#"><img src="images/T1.jpg" /></a></li>
						<li><a href="#"><img src="images/T2.jpg" /></a></li>
						<li><a href="#"><img src="images/T3.jpg" /></a></li>
						<li><a href="#"><img src="images/T4.jpg" /></a></li>
						<li><a href="#"><img src="images/T5.jpg" /></a></li>
					</ul>
				</div>
			</div>
			<div class="p_list">
				<div class="p_info">
					<img src="images/icon_sale.png" />商品列表
				</div>
			</div >



			<ul class="product2">
				<%--<li>
					<dl>
						&lt;%&ndash;<dt>
                            <a href="" target="_self">
                                <img src="" />标题测试</a>
                        </dt>
                        <dd class="title">
                            <a href="" target="_self">描述测试</a>
                        </dd>
                        <dd class="price">价格测试</dd>&ndash;%&gt;
					</dl>

				</li>--%>
			</ul>

			<%--分页栏--%>

		</div>


		<div id="p_right">
			<%@ include file="index_news.jsp"%>
			<%@ include file="hotproduct.jsp"%>
		</div>
	</div>
	<div align="center" style="margin-top:10px" id="btns">
		<a href="#">首页</a>&nbsp;&nbsp;&nbsp;
		<a href="#">上一页</a>&nbsp;&nbsp;
		<span></span>&nbsp;/<span>1/12</span>&nbsp;&nbsp;
		<a href="#">下一页</a>&nbsp;&nbsp;
		<a href="#">最后一页</a>
	</div>
	<div id="foot">Copyright © 2018 上海海文 All Rights Reserved.</div>
</body>
</html>

