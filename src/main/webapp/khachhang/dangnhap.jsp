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

body {
	display: -ms-flexbox;
	display: -webkit-box;
	display: flex;
	-ms-flex-align: center;
	-ms-flex-pack: center;
	-webkit-box-align: center;
	align-items: center;
	-webkit-box-pack: center;
	justify-content: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-control {
	position: relative;
	box-sizing: border-box;
	height: auto;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.red {
	color: red;
}
</style>
<body>

	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	String baoLoi = request.getAttribute("baoLoi") + "";
	baoLoi = baoLoi.equals("null") ? "" : baoLoi;
	%>
	<div>
		<form class="form-signin" action="<%=url%>/khach-hang" method="Post">
			<input type="hidden" name="hanhDong" value="dang-nhap" />
			<h1 class="h3 mb-3 font-weight-normal">Đăng nhập</h1>
			<div class="text-center">
				<span class="red"><%=baoLoi%></span>
			</div>
			<label for="tenDangNhap" class="sr-only">Tên đăng nhập</label> <input
				type="text" id="tenDangNhap" class="form-control mb-3"
				placeholder="Tên đăng nhập..." required autofocus name="tenDangNhap">
			<label for="password" class="sr-only">Password</label> <input
				type="password" id="password" class="form-control"
				placeholder="Password" required name="matKhau">
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
</body>
</html>