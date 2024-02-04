<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
	integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
	crossorigin="anonymous"></script>
</head>
<body>
	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	Object obj = session.getAttribute("khachHang"); // Kiểm tra trong session có còn trong phiên đăng nhập hy không thì mới cho đổi mật khẩu
	KhachHang khachHang = null;
	if (obj != null)
		khachHang = (KhachHang) obj;
	if (khachHang == null) {
	%>
	<h1>Bạn chưa đăng nhập vào hệ thông. Vui lòng quay lại trang chủ</h1>

	<%
	} else {
	String baoLoi = request.getAttribute("baoLoi") + "";
	baoLoi = baoLoi.equals("null") ? "" : baoLoi;
	%>

	<div class="container">
		<h1>ĐỔI MẬT KHẨU</h1>
		<span> <%=baoLoi%>
		</span>
		<form action="<%=url%>/khach-hang" method="Post">
			<input type="hidden" name="hanhDong" value="doi-mat-khau" />
			<div class="form-group col-md-6">
				<label for="tenDangNhap">Tên đăng nhập</label> <input type="text"
					class="form-control" id="tenDangNhap" placeholder="User name..."
					name="tenDangNhap">
			</div>
			<div class="form-group col-md-6">
				<label for="matkKhauHienTai">Mật khẩu cũ</label> <input
					type="password" class="form-control" id="matKhauHienTai"
					placeholder="Password..." name="matKhauHienTai">
			</div>
			<div class="form-group col-md-6">
				<label for="matKhauMoi">Mật khẩu mới</label> <input type="password"
					class="form-control" id="matKhauMoi" placeholder="Password New..."
					name="matKhauMoi">
			</div>
			<div class="form-group col-md-6">
				<label for="nhapLaiMatKhauMoi">Nhập lại mật khẩu mới</label> <input
					type="password" class="form-control" id="nhapLaiMatKhauMoi"
					placeholder="Password New..." name="nhapLaiMatKhauMoi">
			</div>
			<button type="submit" class="btn btn-primary mt-3">Cập nhật</button>
		</form>
	</div>
	<%
	}
	%>

</body>
</html>