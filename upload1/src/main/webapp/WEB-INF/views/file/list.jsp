<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h1> 파일 업로드 테스트 </h1>
	
	<table class="table table-boardered table-condensed" style="width: auto">
		<c:forEach var="file" items="${ files }">
			<tr>
				<td style="min-width:200px"><a href="${R}file/download?id=${file.id}">${ file.fileName }</a></td>
				<td class="text-right">
					<fmt:formatNumber type="number" value="${ file.fileSize }" /> bytes
				</td>
				<td>
					<fmt:formatDate pattern="yyy-MM-dd HH:mm:ss" value="${ file.fileTime }" />
				</td>
				<td>  
					<a class="btn btn-default btn-xs" href="${R}file/delete?id=${file.id}">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<form method="post" enctype="multipart/form-data" action="${R}file/upload">
		<input type="file" name="fileUpload" style="width: 600px; margin: 10px;" multiple /> <br/>
		<button type="submit" class="btn btn-primary">업로드</button>
	</form>
</div>
</body>
</html>