<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng ký</title>
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
	<%
	String baoLoi = request.getAttribute("baoLoi") + "";
	baoLoi = (baoLoi.equals("null") ? "" : baoLoi); // so sanh voi "null" vì hàng ở trên chúng ta đã cộng nó với ""

	String tenDangNhap = request.getAttribute("tenDangNhap") + "";
	tenDangNhap = (tenDangNhap.equals("null")) ? "" : tenDangNhap;

	String hoVaTen = request.getAttribute("hoVaTen") + "";
	hoVaTen = (hoVaTen.equals("null")) ? "" : hoVaTen;

	String gioiTinh = request.getAttribute("gioiTinh") + "";
	gioiTinh = (gioiTinh.equals("null")) ? "" : gioiTinh;

	String ngaySinh = request.getAttribute("ngaySinh") + "";
	ngaySinh = (ngaySinh.equals("null")) ? "" : ngaySinh;

	String diaChiKhachHang = request.getAttribute("diaChiKhachHang") + "";
	diaChiKhachHang = (diaChiKhachHang.equals("null")) ? "" : diaChiKhachHang;

	String diaChiMuaHang = request.getAttribute("diaChiMuaHang") + "";
	diaChiMuaHang = (diaChiMuaHang.equals("null")) ? "" : diaChiMuaHang;

	String diaChiNhanHang = request.getAttribute("diaChiNhanHang") + "";
	diaChiNhanHang = (diaChiNhanHang.equals("null")) ? "" : diaChiNhanHang;

	String dienThoai = request.getAttribute("dienThoai") + "";
	dienThoai = (dienThoai.equals("null")) ? "" : dienThoai;

	String email = request.getAttribute("email") + "";
	email = (email.equals("null")) ? "" : email;

	String dongYNhanMail = request.getAttribute("dongYNhanMail") + "";
	dongYNhanMail = (dongYNhanMail.equals("null")) ? "" : dongYNhanMail;
	%>
	<div class="container">
		<div class="text-center">
			<h1>ĐĂNG KÝ TÀI KHOẢN</h1>
		</div>
		<div class="red" id="'baoLoi">
			<%=baoLoi%>
		</div>
		<div class="red" id="baoLoi"></div>
		<form class="form" action="do-register" method="Post">
			<div class="row">
				<div class="col-md-6">
					<h3>Tài khoản</h3>
					<div class="mb-3">
						<label for="tenDangNhap" class="form-label">Tên đăng nhập<span
							class="red">*</span></label> <input type="text" class="form-coltrol"
							id="tenDangNhap" name="tenDangNhap" required="required"
							value="<%=tenDangNhap%>"></input>
					</div>
					<div class="mb-3">
						<label for="matKhau" class="form-label">Mật khẩu</label> <input
							type="password" class="form-coltrol" id="matKhau" name="matKhau"
							required="required" onchange="kiemTraMatKhau()"></input>
						<p id="msg1" class="red"></p>
					</div>
					<div class="mb-3">
						<label for="matKhauNhapLai" class="form-label">Nhập lại
							mật khẩu</label> <input type="password" class="form-coltrol"
							id="matKhauNhapLai" name="matKhauNhapLai" required="required"
							onchange="kiemTraNhapLaiMatKhau()"></input>
						<p id="msg" class="red"></p>
					</div>
					<hr />
					<h2>Thông Tin Khách Hàng</h2>
					<div class="mb-3">
						<label for="hoVaTen" class="form-label">Họ và tên</label> <input
							type="text" class="form-control" id="hoVaTen"
							placeholder="Họ Và Tên..." name="hoVaTen" value="<%=hoVaTen%>">
					</div>
					<div class="mb-3">
						<label for="gioiTinh" class="form-label">Giới tính</label> <select
							class="form-coltrol" id="gioiTinh" name="gioiTinh">
							<option>--Select--</option>
							<option value="Nam"
								<%=(gioiTinh.equals("Nam")) ? "selected='selected'" : ""%>>Nam</option>
							<option value="Nữ"
								<%=(gioiTinh.equals("Nữ")) ? "selected='selected'" : ""%>>Nữ</option>
							<option value="Khác"
								<%=(gioiTinh.equals("Khác")) ? "selected='selected'" : ""%>>Khác</option>
						</select>
					</div>
					<div class="mb-3">
						<label for="ngaySinh" class="form-label">Ngày Sinh</label> <input
							type="date" class="form-control" id="ngaySinh"
							placeholder="Ngày Sinh..." name="ngaySinh" value="<%=ngaySinh%>">
					</div>

					<hr />
				</div>

				<div class="col-md-6">
					<h3>Địa chỉ</h3>
					<div class="mb-3">
						<label for="diaChiKhachHang" class="form-label">Địa chỉ
							khách hàng</label> <input type="text" class="form-control"
							id="diaChiKhachHang" placeholder="Địa chỉ khách hàng..."
							name="diaChiKhachHang" value="<%=diaChiKhachHang%>">
					</div>
					<div class="mb-3">
						<label for="diaChiMuaHang" class="form-label">Địa chỉ mua
							hàng</label> <input type="text" class="form-control" id="diaChiMuaHang"
							placeholder="Địa chỉ mua hang..." name="diaChiMuaHang"
							value="<%=diaChiMuaHang%>">
					</div>
					<div class="mb-3">
						<label for="diaChiNhanHang" class="form-label">Địa chỉ
							nhận hàng</label> <input type="text" class="form-control"
							id="diaChiNhanHang" placeholder="Địa chỉ nhận hàng..."
							name="diaChiNhanHang" value="<%=diaChiNhanHang%>">
					</div>
					<div class="mb-3">
						<label for="dienThoai" class="form-label">Điện thoại </label> <input
							type="tel" class="form-control" id="dienThoai"
							placeholder="Điện thoại..." name="dienThoai"
							value="<%=dienThoai%>">
					</div>
					<div class="mb-3">
						<label for="email" class="form-label">Email </label> <input
							type="email" class="form-control" id="email"
							placeholder="Email..." name="email" value="<%=email%>">
					</div>
					<hr />
					<div class="mb-3">
						<label for="dongYDieuKhoan" class="form-label">Đồng ý với
							<a href="dieuKhoangCongTy">điều khoản của công ty</a><span
							class="red">*</span>
						</label> <input type="checkbox" class="form-check-input"
							id="dongYDieuKhoan" name="dongYDieuKhoan" required="required"
							onchange="xuLyChonDongY()" />
					</div>
					<div class="mb-3">
						<label for="dongYNhanMail" class="form-label">Đồng ý nhận
							email</label> <input type="checkbox" class="form-check-input"
							id="dongYNhanMail" name="dongYNhanMail"
							<%=dongYNhanMail.equals("on") ? "checked='checked'" : ""%> />

					</div>
					<input class="btn btn-primary form-control" type="submit"
						value="Đăng ký" name="submit" id="submit"
						style="visibility: hidden;" />
				</div>
			</div>
		</form>
	</div>
</body>

<script>
	function kiemTraMatKhau() {
		password = document.getElementById("matKhau").value;
		let isLower = false, isUpper = false, isDigit = false, isSpecial = false;
		let start = '_';
		for (let i = 0; i < password.length; i++) {
			let c = password.charAt(i);
			if (start == c) {
				document.getElementById("msg1").innerHTML = "*Password does not contain 2 of the same character in adjacent positions"
				return false;
			}
			start = c;
			if (c >= 'a' && c <= 'z') {
				isLower = true;
			} else if (c >= 'A' && c <= 'Z') {
				isUpper = true;
			} else if (c >= '0' && c <= '9') {
				isDigit = true;
			} else {
				isSpecial = true;
			}
		}
		if (isLower == false && isUpper == false && isDigit == false
				&& isSpecial == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least one lowercase, uppercase, digit, special"
			return false;
		} else if (isLower == false && isUpper == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least one lowercase, uppercase"
			return false;
		} else if (isLower == false && isDigit == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least one lowercase, digit"
			return false;
		} else if (isLower == false && isSpecial == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least one lowercase, special"
			return false;
		} else if (isUpper == false && isDigit == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least one uppercase, digit"
			return false;
		} else if (isDigit == false && isSpecial == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least one digit, special"
			return false;
		} else if (isLower == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least lowercase"
			return false;
		} else if (isUpper == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least uppercase"
			return false;
		} else if (isDigit == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least digit"
			return false;
		} else if (isSpecial == false) {
			document.getElementById("msg1").innerHTML = "*Password contains at least special"
			return false;
		}
		document.getElementById("msg1").innerHTML = "";
		return true;
	}

	function kiemTraNhapLaiMatKhau() {
		matKhau = document.getElementById("matKhau").value;
		matKhauNhapLai = document.getElementById("matKhauNhapLai").value;
		if (matKhau != matKhauNhapLai) {
			document.getElementById("msg").innerHTML = "*Mật khẩu nhập lại không chính xác!"
			return false;
		} else {
			document.getElementById("msg").innerHTML = "";
			return true;
		}
	}

	function xuLyChonDongY() {
		dongYDieuKhoan = document.getElementById("dongYDieuKhoan");
		if (dongYDieuKhoan.checked == true) {
			document.getElementById("submit").style.visibility = "visible";
		} else {
			document.getElementById("submit").style.visibility = "hidden";
		}

	}
</script>
</html>