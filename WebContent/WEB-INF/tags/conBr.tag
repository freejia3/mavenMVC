<%@ tag body-content="scriptless" language="java" pageEncoding="UTF-8"%>
<jsp:doBody var="con" scope="page" />
<%
	String con =(String)jspContext.getAttribute("con");
	//엔터를 개행으로 바꿔준다
	String res = con.trim().replaceAll("\n", "<br>");

%>
<%=res %>
