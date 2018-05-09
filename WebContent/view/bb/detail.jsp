<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ct" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>detail</title>
</head>
<body>
<table border="">
		<tr>
			<td>id</td>
			<td>${data.id }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${data.title }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${data.cnt }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${data.pname }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><fmt:formatDate value="${data.reg_date }" pattern="yyyy-mm-dd HH:mm:ss"/></td>
		</tr>

<!-- db저장시 널일수도 글자가 없는걸로 저장될 수도 있다.
null이면 칸 자체가 안뜸 . data.upfile!=null 인 경우 ""로 바꾸도록  VO에서 처리해준다
이미지인지 확인여부는 커스텀태그에서 해도 되고 VO에서 해도됨-->
		<c:if test="${data.upfile!=''}">
		<tr>
			<td>파일</td>
			<td>
			<!-- isImg() 함수의 리턴값 검사 -->
			<c:choose>
				<c:when test="${data.img}">
					<img src="../up/${data.upfile}">
				</c:when>
				<c:otherwise>
					<a href="FileDown?file=${data.upfile}">${data.upfile}</a>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</c:if>
		<tr>
			<td>내용</td>
			<!-- ct:conBr 엔터를 개행으로 바꿔준다 -->
			<td><ct:conBr>${data.content }</ct:conBr></td>
		</tr>

		<tr>
			<td colspan="2" align="right">

				<a href="List?page=${param.page }">목록으로</a>
				<a href="DeleteForm?page=${param.page }&id=${data.id }">삭제</a>
				<a href="ModifyForm?page=${param.page }&id=${data.id }">수정</a>
				<a href="ReplyForm?page=${param.page }&id=${data.id }">답변</a>
			</td>
		</tr>
	</table>
</body>
</html>