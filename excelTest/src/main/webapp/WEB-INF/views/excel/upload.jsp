<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
	<div class="container">

		<h1>액셀 업로드</h1>
		<hr />

		<form method="post" enctype="multipart/form-data">
			<input type="file" name="file"
				style="margin-bottom: 20px; width: 300px;" /><br />
			<button type="submit" class="btn btn-primary" name="cmd" value="upload">업로드</button>
		</form>
		<hr />

		<c:if test="${ not empty persons }">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>이름</th>
						<th>생일</th>
						<th>점수</th>
						<th>활성화</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="person" items="${ persons }">
						<tr>
							<td>${ person.id }</td>
							<td>${ person.name }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${ person.birthDate }" /></td>
							<td><fmt:formatNumber pattern="###.00"
									value="${ person.score }" /></td>
							<td>${ person.enabled }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

	</div>
</body>
</html>
