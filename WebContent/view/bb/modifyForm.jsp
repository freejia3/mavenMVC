<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script>
<c:if test="${msg!=null }">
	alert("${msg }");
</c:if>

function fileDelete(){
	//컨펌 박스로 묻고 삭제하자
	if(confirm('파일을 삭제하시겠습니까?\n삭제된파일은 복구불가 입니다.')){
		/* 폼의 이름이 frm */
		var frm = document.frm;
		frm.action="FileDelete";
		frm.submit();
	}

}
</script>

<form name="frm" action="ModifyReg" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${data.id }">
	<input type="hidden" name="seq" value="${data.seq }">
	<input type="hidden" name="page" value="${param.page }">
	<table>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="${data.title }" /></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="pname" value="${data.pname }" /></td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="text" name="pw" /></td>
		</tr>


	<c:choose>

	<c:when test="${data.seq==0 }">
		<tr><!-- seq==0 의미는 원글이라는 뜻임 -->
			<td>파일</td>
			<td>
			<!-- 파일 있으면 'fileDelete() 스크립트'보이고,  파일 없으면 input칸 보임 -->
			<c:choose>
				<c:when test="${data.upfile!='' }">
					${data.upfile}
					<!-- request파라미터로 upfile을 보낸다 -->
					<input type="hidden" name="upfile"  value="${data.upfile }">
					<input type="button" value="파일삭제" onclick="fileDelete()">
				</c:when>
				<c:otherwise>
					<input type="file" name="upfile" />
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</c:when>
	<c:otherwise>
			<!-- 파일이 없음이라고 인지시킴 -->
			<input type="hidden" name="upfile"  value="">
	</c:otherwise>
	</c:choose>
		<tr>
			<td>내용</td>
			<td><textarea name="content" rows="5" cols="30">${data.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="수정" />
				<a href="ModifyForm?page=${param.page }&id=${data.id }">초기화</a>
				<a href="Detail?page=${param.page }&id=${data.id }">뒤로</a>
			</td>
		</tr>
	</table>
</form>