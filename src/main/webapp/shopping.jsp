<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊- 购物车</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
	<script>
		$(function () {
			$.post("shopCart.do",
					{"param":"queryAll",
						"uid":${sessionScope.user.id}},
					function (data) {
						var table = $("#sp");
						for (var i=0;i<data.length;i++){
							var product = data[i];
							var tr = $("<tr id='doBuy'></tr>");
							var td1 = "<td class='thumb'><img style='width:100px;height:100px;' src='"+ product.img_Source +"' /><a href='Product.do?param=queryById&id="+product.id +"'>"+ product.name +"</a></td>"
							var td2 = "<td class='price' id='price_id_1'>\n" +
									"\t\t\t\t\t\t￥<span id='"+"price"+ product.id +"'>"+ product.price +"</span>\n" +
									"\t\t\t\t\t</td>"
							var td3 = "<td class='number'>\n" +
									"<p>库 存：<span id='"+"stock"+ product.id +"'>"+ product.stock +"</span></p>\n" +
									"\t\t\t\t\t\t\t<input type='button' onclick='minus("+ product.id +")' value=' - ' width='3px' name='minusButton' >\n" +
									"\t\t\t\t\t\t\t<input type='text' name='number' id='"+"number"+product.id +"'value='"+ product.minor_Id +"' maxlength='5'\n" +
									"\t\t\t\t\t\t\tsize='1'  style='text-align:center; vertical-align:middle' onblur='CheckStock("+ product.id +")'/>\n" +
									"\t\t\t\t\t\t\t<input type='button' onclick='add("+ product.id +")' value=' + ' width='2px' name='addButton'>\t\t\n" +
									"\t\t\t\t\t</td>";
							var td4 = "<td class='price poo' id='price_id_2'>\n" +
									"\t\t\t\t\t\t￥<span  id='"+"money"+ product.id +"' >"+ product.price*product.minor_Id +"</span>\n" +
									"\t\t\t\t\t\t<input type='hidden'  value='"+ product.id +"' name='pId'/>\n" +
									"\t\t\t\t\t</td>"
							var td5 = "<td class='delete'><a href='javascript:del("+ product.id +")'>删除</a></td>";
							tr.append(td1).append(td2).append(td3).append(td4).append(td5);
							table.append(tr);
						}
						addMoney();
					}, "json")
		});
		function Go() {
			var price = "price";
			var count = "count";
			var pid = "pid";
			var money = $("#money22").html();
			//for循环取每一项值,拼接字符串:pid-2-3-4;
			$($(".poo > span").each(function () {
				var value = $(this).text();
				price = price+"-"+value;
			}));
			$($("input[name='number']").each(function () {
				var value = $(this).val();
				count = count+"-"+value;
			}));
			$($("input[name='pId']").each(function () {
				var value = $(this).val();
				pid = pid+"-"+value;
			}));
			if (CheckStock()){
			$.post("order.do",
					{"param":"ByShop",
						"pid":pid,
						"count":count,
						"price":price,
						"money":money
					},
					function (data) {
						if (data>0){
							window.location = "${pageContext.request.contextPath}/shopping-result.jsp";
						}else {
							alert("提交失败");
						}
					}, "json")
			}
		}
		//总金额
		function addMoney() {
			var money = 0;
			var span = $(".poo > span");
			$(span.each(function () {
				var value = $(this).text();
				money = parseInt(money)+parseInt(value);

			}));
			$("#money22").html(money);
		}
		function CheckStock(id){
			var flag = true;
			var price=parseInt($("#price"+id).html());
			var number=$("#number"+id).val();
			var stock=$("#stock"+id).html();
			if (number>parseInt(stock)){
				alert("您选择的数量超过库存!")
				$("#number"+id).val(stock);
				$("#money"+id).html(stock*price);
				flag = false;
			}else if (number<1){
				alert("您选择的数量不能低于0!")
				$("#number"+id).val(1);
				$("#money"+id).html(price);
				flag = false;
			}else {
				$("#money"+id).html(number*price);
			}
			addMoney();
			return flag;

		}
		function del(id) {
			$.post("shopCart.do",
					{"param":"delete",
					"id":id},
					function (data) {
						if (data>0) {
							alert("删除成功");
							window.location = "${pageContext.request.contextPath}/shopping.jsp";
						} else {
							alert("删除失败");
							window.location = "${pageContext.request.contextPath}/shopping.jsp";
						}
					}, "json")
		}
		//-按钮事件
		function minus(id){
			var number=parseInt($("#number"+id).val());
			var price=parseInt($("#price"+id).html());
			var stock=parseInt($("#stock"+id).html());
			if (number>1){
				$("#number"+id).val(number-1);
				$("#money"+id).html((number-1)*price);
			}else {
				alert("您选择的数量不能低于0!")
			}
			addMoney();
		}
		//+按钮事件
		function add(id){
			var number=parseInt($("#number"+id).val());
			var price=parseInt($("#price"+id).html());
			var stock=parseInt($("#stock"+id).html());
			if (number<stock){
				$("#number"+id).val(number+1);
				$("#money"+id).html((number+1)*price);
			}else {
				alert("您选择的数量超过库存!")
			}
			addMoney();
		}
	</script>

<script type="text/javascript" src="scripts/shopping.js"></script>
</head>
<body>
<%@ include file="index_top.jsp"  %>

<div id="position" class="wrap">
	您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="doBuy" method="post">
			<table id="sp">
				<tr>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>购买数量</th>
					<th>商品价格</th>
					<th>操作</th>
				</tr>
				
				
				<!-- 根据用户购物车生成列表 -->
				<%--<tr id="product_id_1">
					<td class="thumb"><img style="width: 100px; height: 100px;" src="images/spriter.png" /><a href=""></a></td>
					<td class="price" id="price_id_1">
						￥<span id="span_1"></span>
						<input type="hidden" id="subPrice" value="" name="sumPrice"/>
						<input type="hidden"  value="" name="pId"/>
						<input type="hidden"  value="" name="hpStock" id="ap"/>
					</td>
					<td class="number">
					<p>
							库 存：<span id="stock">${requestScope.product.stock}</span>(有货)
						</p>
							<input type="button" id="minus"value=" - " width="3px" name="minusButton">
							<input id="ad" type="text" name="number" value="" maxlength="5"
							size="1"  style="text-align:center; vertical-align:middle" onblur="checkStock(${hcid })"/>
							<input type="button" id="add" value=" + " width="2px" name="addButton">		
					</td>
					<td class="delete"><a href="">删除</a></td>
				</tr>--%>
			</table>
			<div class="button"><span style="font-size: 30px;color: red;" ><span>商品总价:￥</span><span id="money22">1280</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span><input type="button" value="" onclick="Go()"/></div>
		</form>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved.
</div>
</body>
</html>

