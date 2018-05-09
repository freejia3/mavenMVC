<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp</title>
</head>
<body>
<table border="">
	<tr>
		<td colspan="2">
		<!-- jsp는 현재페이지를 기준으로. html은 url을 기준으로 패스 잡음. -->
			<jsp:include page="inc/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td><jsp:include page="inc/menu.jsp"/></td>
		<td><jsp:include page="bb/${main }"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="inc/bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>