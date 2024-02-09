<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
</head>

<style>
html, body {
	height: 100%;
}

.red {
	color: red;
}
</style>
<body>
	<jsp:include page="../header.jsp" />
	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	String baoLoi = request.getAttribute("baoLoi") + "";
	baoLoi = baoLoi.equals("null") ? "" : baoLoi;
	%>
	<div class="container">

		<form class="form-signin" action="<%=url%>/khach-hang" method="Post">
			<input type="hidden" name="hanhDong" value="dang-nhap" />
			<h1 class="h3 mb-3 font-weight-normal">Đăng nhập</h1>
			<div class="text-center">
				<span class="red"><%=baoLoi%></span>
			</div>
			<div class="">
				<label for="tenDangNhap" class="sr-only">Tên đăng nhập</label> <input
					type="text" id="tenDangNhap" class="form-control mb-3"
					placeholder="Tên đăng nhập..." required autofocus
					name="tenDangNhap" style="width: 500px">
			</div>
			<label for="password" class="sr-only">Password</label> <input
				type="password" id="password" class="form-control"
				placeholder="Password" required name="matKhau" style="width: 500px">
			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me">
					Ghi nhớ tài khoản này
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Đăng
				nhập</button>
			<div class="mt-3">
				<a href="dangky.jsp">Đăng kí tài khoản mới</a>
			</div>
			<p class="mt-5 mb-3 text-muted">&copy; 2017-2025</p>
		</form>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>