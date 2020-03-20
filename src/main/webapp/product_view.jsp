<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 产品显示</title>
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
	<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
	<script>
		function doBuy(pid) {
			var count=parseInt($("#count").val());
			var price=$("#price").html();
			if(CheckStock()){
				if (${sessionScope.user!=null}){
					$.post("order.do",
							{"param":"doBuy",
								"pid":pid,
								"price":price.substring(1),
								"count":count},
							function (data) {
						if (data>0){
							window.location = "${pageContext.request.contextPath}/shopping-result.jsp";
						}else {
							alert("购买失败")
						}
							}, "json")
				}else {
					alert("请先登录亚马逊！");
					window.location.href="login.jsp"
				}
			}

		}
		function addToCart(pid) {
			var count=parseInt($("#count").val());
			if(CheckStock()){
				if (${sessionScope.user!=null}){
					$.post("shopCart.do",
							{"param":"add",
							"pid":pid,
							"count":count},
							function (data) {
								if (data>0) {
									alert("添加成功")
								} else {
									alert("添加失败")
								}
							}, "json")

				}else {
					alert("请先登录亚马逊！");
					window.location.href="login.jsp"
				}
			}
		}
		function CheckStock(){
			var flag = true;
			var stock=parseInt($("#stock").html());
			var count=parseInt($("#count").val());
			if(count<1){
				alert("您选择的数量不能低于0!");
				$("#count").val(1)
				flag = false;
			}else if(count>stock){
				alert("您选择的数量超过库存!");
				$("#count").val(stock);
				flag = false;
			}
			return flag;
		}
		//-按钮事件
		function minus(){
			var stock=parseInt($("#stock").html());
			var count=parseInt($("#count").val());
			if(count<1){
				alert("您选择的数量不能低于0!");
				$("#count").val(1)
			}else if(count>=stock){
				$("#count").val(count)
			}else {
				$("#count").val(count-1)
			}
		}
		//+按钮事件
		function add(){
			var stock=parseInt($("#stock").html());
			var count=parseInt($("#count").val());
			if(count<stock){
				$("#count").val(count+1)
			}else if(count<1){
				$("#count").val(1)
			}else {
				alert("您选择的数量超过库存!")
			}
		}
	</script>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="position" class="wrap">
		您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; <a href=""></a> &gt; <a
			href=""></a>
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%@ include file="index_product_sort.jsp"%>
		</div>
		<div id="product" class="main">
			<h1>商品名称:${requestScope.product.name}</h1>
			<div class="infos">
				<div class="thumb">
					<img style="width: 100px; height: 100px;" src="${requestScope.product.img_Source}" />
				</div>
				<div class="buy">
					<p>
						商城价：<span class="price" id="price">￥${requestScope.product.price}</span>
					</p>
					<c:if test="${requestScope.product.stock<=0}">
						<p>
							库 存：无货
						</p>
					</c:if>
					<c:if test="${requestScope.product.stock>0}">
						<p>
							库 存：<span id="stock">${requestScope.product.stock}</span>(有货)
						</p>
						<input type="button" value=" - " width="3px" onclick="minus()">
						<input type="text" id="count" name="count" value="1" maxlength="5" size="2" onblur="CheckStock()" style="text-align:center;vertical-align: middle">
						<input type="button" value=" + " width="2px" onclick="add()">

						<div class="button">
							<input type="button" onclick="doBuy(${requestScope.product.id})" value="" style="background: url(images/buyNow.png)" />
							<input type="image" name="imageField" src="images/cartbutton.png" onclick="addToCart(${requestScope.product.id})"/>
						</div>
					</c:if>
				</div>
				<div class="clear"></div>
			</div>
			<div class="introduce">
				<h2>
					<strong>商品详情</strong>
				</h2>
				<div class="text">
					商品名字：${requestScope.product.name} <br /> 商品描述：${requestScope.product.description} <br />
					商品价格：￥${requestScope.product.price} <br /> 商品库存：${requestScope.product.stock} <br />
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2018 上海海文 All Rights Reserved.
	</div>
</body>
</html>

