<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 订单页</title>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="scripts/shopping.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
	<script>
		$(function () {
			$.post("order.do",
					{"param":"queryByUid"},
					function (data) {
					var table = $("#oderview");
						var tr1 = $("<tr></tr>");
						var th1 = $("<th>" + "订单时间:" + data.create_Time + "</th>");
						var th2 = $("<th>" + "订单号:" + data.id + "</th>");
						var th3 = $("<th>" + "订单总额:" + data.money + "</th>");
						var th4 = $("<th>" + "订单状态:" + (data.status=1?'正在发货':'已发货') + "</th>");
						var th5 = $("<th></th>");
						tr1.append(th1).append(th2).append(th3).append(th4).append(th5);
						table.append(tr1);

					for (var i=0;i<data.products.length;i++){
						var tr2 = $("<tr id='product_id_1'></tr>");
						var product = data.products[i];
						var order = data.orderDetails[i];
						var td1 = $("<td class='thumb'><img style='width: 100px; height: 100px;' src='" + product.img_Source + "' /><a href='Product.do?param=queryById&id="+product.id +"'>"+ product.name + "</a></td>");
						var td2 = $("<td class='price'>￥<span>单价：" + product.price + "</span></td>");
						var td3 = $("<td class='number'><span>数量:" + order.quantity + "</span></td>");
						var td4 = $("<td class='price'>￥<span>总价：" + order.money + "</span></td>");
						tr2.append(td1).append(td2).append(td3).append(td4);
						table.append(tr2);
					}

					}, "json")
		});
	</script>
</head>
<body>
	<%@ include file="index_top.jsp"%>

	<div id="position" class="wrap">
		您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; 最新订单
	</div>
	<div class="wrap">
		<div id="shopping">
			<form action="doBuy" method="post">
				<table id="oderview" style="font-size: 14px">
					<%--<tr>
						<th>订单时间:</th>
						<th>订单号:</th>
						<th>订单总额:</th>
						<th>
						<th>
					</tr>
					<!-- 根据用户购物车生成列表 -->
					<tr>
						<td class="thumb"><img style="width: 100px; height: 100px;" src="" /><a href=""></a></td>
						<td class="price">￥<span>单价：</span></td>
						<td class="number"><span>X</span></td>
						<td class="delete">正在发货</td>
					</tr>--%>
				</table>
			</form>
		</div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海海文 All Rights Reserved.
	</div>
</body>
</html>

