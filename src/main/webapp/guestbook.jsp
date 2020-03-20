<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 留言</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
	<script>
		function commentCheck(){
			if($("[name=guestName]").val()==""){
				alert("请输入昵称！")
				return false;
			}
			if($("[name=guestTitle]").val()==""){
				alert("请输入标题！")
				return false;
			}
			if($("[name=guestContent]").val()==""){
				alert("请输入留言内容！")
				return false;
			}
			return true;
		}
		$(function () {
		    showComment(1,2);
			$(".ui-blue").click(function () {
				if (commentCheck()){
				$.post("comment.do",
					$('#addComment').serialize(),
				 function (data) {
					if (data.info == "success") {
						window.location = "${pageContext.request.contextPath}/guestbook.jsp";
						alert("提交成功")
					} else {
						alert(data.info);
					}
				},"json")
				}
			});

		})
        function showComment(pageNo,pageSize) {
            $.post("comment.do", {
						"param": "queryAll",
						"pageNo":pageNo,
						"pageSize":pageSize
					},
                function (data) {
                        var $dl = $(".guestbook > ul >li >dl");// 找到dl元素
                        $dl.empty();// 清空ul元素中的内容
                        // 循环创建dd元素
                        for (var i = 0; i < data.list.length; i++) {
                            var comment = data.list[i];//取出消息
                            <%--<dt>内容：</dt>
                            <dd class="author"><span>作者：</span></dd>
                            <dd>评论时间：</dd>
                            <dd>回复：</dd>
                            <dd>回复时间：</dd>--%>
                            var $dt = $("<dt>" +"内容"+ comment.content + "</dt>");
                            var $dd1 = $("<dd class='author'><span>"+ "作者:"+comment.nick_Name +"</span></dd>");
                            var $dd2 = $("<dd>" + "评论时间:"+ comment.create_Time + "</dd>");
                            var $dd3 = $("<dd>" + "回复: " + comment.reply+ "</dd>");
                            var $dd4 = $("<dd>" + "回复时间:"+ comment.reply_Time + "</dd>");
                            $dl.append($dt).append($dd1).append($dd2).append($dd3).append($dd4);
                        }
					var $a = $("#btns > a");
					$a.eq(0).attr("href", "javascript:showComment('" + 1 + "','" + pageSize + "')");
					$a.eq(1).attr("href", "javascript:showComment('" + data.prePage + "','" + pageSize + "')");
					$a.eq(2).attr("href", "javascript:showComment('" + data.nextPage + "','" + pageSize + "')");
					$a.eq(3).attr("href", "javascript:showComment('" + data.totalPage + "','" + pageSize + "')");

					var $span = $("#btns > span");
					$span.eq(0).html(data.pageNo);
					$span.eq(1).html(data.totalPage);
                },"json")
        }

	</script>
</head>
<body>
<%@ include file="index_top.jsp"  %>
<div id="position" class="wrap">
	您现在的位置：<a href="${pageContext.request.contextPath}/index.jsp">亚马逊</a> &gt; 在线留言
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<%@ include file="index_product_sort.jsp" %>
	</div>
	<div class="main">
		<div class="guestbook">
			<h2>全部留言</h2>
			<ul>
				<li>
					<dl>

					</dl>
				</li>
			</ul>
			<div align="center" style="margin-top:10px" id="btns">
				<a href="#">首页</a>&nbsp;&nbsp;&nbsp;
				<a href="#">上一页</a>&nbsp;&nbsp;
				<span></span>&nbsp;/<span>${requestScope.pageEntity.totalPage}</span>&nbsp;&nbsp;
				<a href="#">下一页</a>&nbsp;&nbsp;
				<a href="#">最后一页</a>
			</div>

			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
				</ul>
			</div>
			
			<div id="reply-box">
				<form id="addComment" >
					<table>
						<tr>
							<td class="field">昵称：</td>
							<td><input class="text" type="text" name="guestName" /></td>
						</tr>
						<tr>
							<td class="field">留言标题：</td>
							<td><input class="text" type="text" name="guestTitle" /></td>
						</tr>
						<tr>
							<td class="field">留言内容：</td>
							<td><textarea name="guestContent"></textarea></td>
						</tr>
						<tr>
							<td><input type="hidden" name="param" id="param" value="comment"/></td>
							<td><label class="ui-blue"><input type="button" name="submit" value="提交留言" /></label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 上海海文 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
