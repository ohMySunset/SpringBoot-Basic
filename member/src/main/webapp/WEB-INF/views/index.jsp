<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- css파일을 url경로로 어플리케이션 속성에 저장한다 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/css/default.css"> --%>
<%-- <link rel="stylesheet" href=" <c:url value="/css/default.css"/>"> --%>
<%@ include file="/WEB-INF/views/include/basicset.jsp"%>
<style>
</style>
</head>
<body>

	<%@ include file="/WEB-INF/views/include/header.jsp"%>

	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<main class="container">

		<div
			class="d-flex align-items-center p-3 my-3 text-white bg-purple shadow-sm">
			<div class="lh-1">
				<h1 class="h6 mb-0 text-white lh-1">Index</h1>
			</div>
		</div>

		<div class="my-3 p-3 bg-white rounded shadow-sm">
			<h3 class="border-bottom pb-2 mb-0">Recent Updates</h3>
			<div class="d-flex text-muted pt-3">Index</div>

		</div>

	</main>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

	<script>
		<c:if test="${type eq 'delete'}">

		<c:if test="${reault eq 'ok'}">
		alert("로그아웃되었습니다.");
		</c:if>
		<c:if test="${result ne 'ok'}">
		alert("로그아웃이 정상 처리되지 않았습니다.")
		</c:if>
		</c:if>
	</script>

</body>
</html>