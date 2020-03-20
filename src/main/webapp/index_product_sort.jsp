<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
<script>
	$(function () {
		$.post("Category.do", {"param": "queryAll"},
				function (data) {
					var $dl = $(".p_category > dl");// 找到dl元素
					  for (var i=0;i<data.length;i++){
						  var top = data[i];
						if (top.id==top.parent_Id) {
							var $dt = $("<dt id='" + top.parent_Id + "'><a href='${pageContext.request.contextPath}/product-list.jsp?param=queryByMajorId&id="+top.parent_Id +"'>" + top.name + "</a></dt>")
						}
						$dl.append($dt);
					  }
					for (var i=0;i<data.length;i++){
						var top = data[i];
						if (top.id!=top.parent_Id){
							var $dd = $("<dd><a href='${pageContext.request.contextPath}/product-list.jsp?param=queryByMinorId&id="+top.id +"'>" + top.name+ "</a></dd>")
						}
						$("#"+top.parent_Id).append($dd);
					}
				}, "json")
	})


</script>
<div class="p_category">
	<h2>商品分类</h2>
		<dl>

		</dl>
</div>

