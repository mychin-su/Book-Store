<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Successfully</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<%=request.getAttribute("baoLoi") + ""%>
	</div>
	<script>
		setTimeout(function() {
			window.location.href = 'index.jsp';
		}, 20000);
	</script>
	<jsp:include page="../footer.jsp" />
</body>
</html>