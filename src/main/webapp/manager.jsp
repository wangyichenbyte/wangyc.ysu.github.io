<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>消费记录管理</title>

	<style>
		.pagination-buttons {
			text-align: center;
			margin-top: 10px;
		}
	</style>

	<!-- 采用绝对路径导入css文件 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
	<!-- 采用绝对路径导入jquery文件 -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
	<script type="text/javascript">

		$(function () {
			//提示用户添加失败，删除失败，修改失败
			if(!${empty requestScope.msg}){
				alert("${requestScope.msg}");
			}
			//验证非空，并提交查询请求
			$("#submit").click(function () {
				//验证输入框是否为空
				var keyword = $("#keyword").val();
				if(keyword ==""){
					alert("请输入关键字");
					return false;
				}else {
					//javascript语言提供了一个location地址栏对象
					//它有一个属性href,可以获取浏览器中地址栏地址
					location.href="${pageContext.request.contextPath }/manager/homeCostServlet?action=query&keyword="+keyword;
				}

			});

			//删除提示
			$("a.deleteClass").click(function () {
				//在事件fuction函数中有一个this对象，即当前响应事件的dom对象

				/**
				 * confirm是确认提示框函数
				 * 参数是提示内容
				 * 两个按钮：确认和取消
				 * 返回true表示点击确认
				 */
				return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text()+"】?");
			});

		});
	</script>
	<!--分页处理js代码-->
	<script type="text/javascript">

		// ...
		// 当前显示第几页数据
		var currentPage = ${result.currentPage};
		// 一共多少页数据
		var totalPage = ${result.totalPage};

		// 第一页
		function firstPage() {
			if (currentPage == 1) {
				alert("已经是第一页数据了");
				return false;
			}
			else {
				location.href = "${pageContext.request.contextPath}/manager/homeCostServlet?action=list1&pageNum=1";
				return true;
			}
		}

		// 下一页
		function nextPage() {
			if (currentPage == totalPage) {
				alert("已经是最后一页数据");
				return false;
			} else {
				location.href = "${pageContext.request.contextPath}/manager/homeCostServlet?action=list1&pageNum=" + (currentPage + 1);
				return true;
			}
		}

		// 上一页
		function previousPage() {
			if (currentPage == 1) {
				alert("已经是第一页数据");
				return false;
			} else {
				location.href = "${pageContext.request.contextPath}/manager/homeCostServlet?action=list1&pageNum=" + (currentPage - 1);
				return true;
			}
		}

		// 尾页
		function lastPage() {
			if (currentPage == totalPage) {
				alert("已经是最后一页数据");
				return false;
			} else {
				location.href = "${pageContext.request.contextPath}/manager/homeCostServlet?action=list1&pageNum=" + totalPage;
				return true;
			}
		}

	</script>
</head>
<body>
<div id="header">
	<span class="wel_word">家庭记账本</span>
	<div>
		<a href="${pageContext.request.contextPath }/cost_edit.jsp">新增消费记录</a>
		<input style="margin-left:20px"id="keyword" name="keyword" type="text" placeholder="请输入关键字"value=""/>
		<input id="submit"type="submit" value="查询"/>
	</div>
</div>
<div id="main">
	<table style="margin-top:30px">
		<tr>
			<td class="costname" style="width:200px">消费名称</td>
			<td>消费金额</td>
			<td>累计消费</td>
			<td style="width:200px">登记日期</td>
			<td colspan="2">操作</td>
		</tr>
		<!-- 使用el表达式注意在jsp页面(如本页面第一行)导入相应的包 -->
		<c:forEach items="${requestScope.result.datalist}" var="item">
			<tr>
				<td>${item.name}</td>
				<td>${item.money}</td>
				<td>${item.sum}</td>
				<td>${item.date}</td>
				<td><a href="${pageContext.request.contextPath }/manager/homeCostServlet?action=getHomeCostById&id=${item.id}">详情信息</a></td>
				<td><a  class="deleteClass" href="${pageContext.request.contextPath }/manager/homeCostServlet?action=delete&id=${item.id}">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td colspan="2"><a href="${pageContext.request.contextPath }/cost_edit.jsp">新增</a></td>
		</tr>
		<tr>
			<!--td colspan="5">共有<c:out value="${fn:length(requestScope.result.datalist)}" />笔消费记录</td-->
			<td></td>
		</tr>
	</table>


	<div class="pagination-buttons">

	<button onclick="firstPage()">首页</button>
	<button onclick="previousPage()">上一页</button>
	<button onclick="nextPage()">下一页</button>
	<button onclick="lastPage()">尾页</button>


		<br/>
		第 ${result.currentPage} 页，共 ${result.totalPage} 页
		<br/>
	</div>
</div>
</body>
</html>
