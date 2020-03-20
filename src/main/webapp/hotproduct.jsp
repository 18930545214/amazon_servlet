<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
<script>
	$(function () {
		$.post("Product.do",
				{"param":"queryAllHot"},
				function (data) {
					var ul = $(".hot_sale > ul ");
					for (var i=0;i<data.length;i++) {
						var product = data[i];
						var li = $("<li></li>");
						var dl = $("<dl></dl>");
						var dt = $("<dt><a href='Product.do?param=queryById&id=" + product.id + "' target='_self'><img src='" + product.img_Source + "' /></a></dt>");
						var dd1 = $("<dd class='p_name'><a href='Product.do?param=queryById&id=" + product.id + "' target='_self'>" + product.name + "</a></dd>");
						var dd2 = $("<dd class='price'>" + "￥" + product.price + "</dd>");
						dl.append(dt).append(dd1).append(dd2);
						li.append(dl);
						ul.append(li);
					}
				}, "json")

	});
</script>
<div class="hot_sale">
	<h2>热卖推荐</h2>
	<ul>
		<%--<li>
			<dl>
				<dt>
					<a href="" target="_self"> <img src="" /></a>
				</dt>
				<dd class="p_name">
					<a href="" target="_self"></a>
				</dd>
				<dd class="price">￥</dd>
			</dl>
		</li>--%>
	</ul>
</div>
