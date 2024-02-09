<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Thay đổi ảnh</title>
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
<style>
.red {
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<%
	String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
	%>
	<%
	Object obj = session.getAttribute("khachHang");
	KhachHang khachHang = null;
	if (obj != null)
		khachHang = (KhachHang) obj;
	if (khachHang == null) {
	%>
	<h1>Bạn chưa đăng nhập vào hệ thống. Vui lòng quay lại trang chủ!</h1>
	<%
	} else {
	%>
	<div class="container">
		<%
		String baoLoi = request.getAttribute("baoLoi") + "";
		baoLoi = (baoLoi.equals("null")) ? "" : baoLoi;
		String duongDanAnh = khachHang.getDuongDanAnh();
		%>
	</div>
	<div class="container">
		<div class="text-center">
			<h1>THÔNG TIN TÀI KHOẢN</h1>
		</div>
		<div class="red" id="baoLoi"><%=baoLoi%></div>
		<form class="form" action="<%=url%>/khach-hang-thay-doi-anh"
			method="Post" enctype="multipart/form-data">
			<!-- Upload file phai them encrypt="multipart/form-data" -->
			<input type="hidden" name="hanhDong" value="thay-doi-anh" />
			<div class="row">
				<div class="col-md-6">
					<h3>Thông tin khách hàng</h3>
					<img src="<%=url%>/avatar/<%=duongDanAnh%>" alt="Ảnh Avatar" />
					<div class="mb-3">
						<label for="duongDanAnh" class="form-label">Đường dẫn ảnh</label>
						<input type="file" class="form-control" id="duongDanAnh"
							name="duongDanAnh" />
					</div>
					<input class="btn btn-primary form control" type="submit"
						value="Lưu thông tin" name="submit" id="submit" />
				</div>
			</div>
		</form>
	</div>
	<%
	}
	%>
	<jsp:include page="../footer.jsp" />
</body>
</html>