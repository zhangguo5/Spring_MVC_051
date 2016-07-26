<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/styles/main.css"/>"  type="text/css" rel="stylesheet" />
<title>产品管理</title>
</head>
<body>
	<div class="main">
		<h2 class="title"><span>产品管理</span></h2>
		<form method="get">
		   名称：<input type="text" name="searchKey" value="${searchKey}"/>
		   <input type="submit"  value="搜索" class="btn out"/>
		</form>
		<form action="deletes" method="post">
		<table border="1" width="100%" class="tab">
			<tr>
				<th><input type="checkbox" id="chbAll"></th>
				<th>编号</th>
				<th>产品名</th>
				<th>价格</th>
				<th>类型</th>
				<th>操作</th>
			</tr>
			<c:forEach var="product" items="${products}">
				<tr>
					<th><input type="checkbox" name="id" value="${product.id}"></th>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td>${product.productType.name}</td>
					<td>
					<a href="delete/${product.id}" class="abtn">删除</a>
					<a href="edit/${product.id}" class="abtn">编辑</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<p style="color: red">${message}</p>
		<p>
		    <a href="add" class="abtn out">添加</a>
		    <input type="submit"  value="批量删除" class="btn out"/>
		</p>
		<script type="text/javascript" src="<c:url value="/scripts/jQuery1.11.3/jquery-1.11.3.min.js"/>" ></script>
	</form>
	</div>
</body>
</html>