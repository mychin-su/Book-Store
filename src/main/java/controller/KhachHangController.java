package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import database.KhachHangDAO;
import model.KhachHang;
import util.CreateRandomNumber;
import util.Email;
import util.Encode;

/**
 * Servlet implementation class KhachHang
 */
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KhachHangController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hanhDong = request.getParameter("hanhDong");
		System.out.println(hanhDong);
		if (hanhDong.equals("dang-nhap")) {
			dangNhap(request, response);
		} else if (hanhDong.equals("dang-xuat")) {
			dangXuat(request, response);
		} else if (hanhDong.equals("dang-ky")) {
			dangKy(request, response);
		} else if (hanhDong.equals("doi-mat-khau")) {
			doimatkhau(request, response);
		} else if (hanhDong.equals("thay-doi-thong-tin")) {
			thaydoithongtin(request, response);
		} else if (hanhDong.equals("xac-thuc")) {
			xacThuc(request, response);
		} else if (hanhDong.equals("thay-doi-anh")) {
			thayDoiAnh(request, response);
		}

	}

	private void dangNhap(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			matKhau = Encode.toSHA1(matKhau);

			KhachHang kh = new KhachHang();
			kh.setTenDangNhap(tenDangNhap);
			kh.setMatKhau(matKhau);
			KhachHangDAO khd = new KhachHangDAO();
			KhachHang khachHang = khd.selectByUsernameAndPassWord(kh);
			String url = "";
			if (khachHang != null && khachHang.isTrangThaiXacThuc() == true) {

				HttpSession session = request.getSession();
				session.setAttribute("khachHang", khachHang);
				url = "/index.jsp";
			} else {
				request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không chính xác hoặc chưa xác thực");
				url = "/khachhang/dangnhap.jsp";
			}
			System.out.println(khachHang.isTrangThaiXacThuc());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dangXuat(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			// Huy bo session
			session.invalidate();
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();

			response.sendRedirect(url + "/index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dangKy(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			String matKhauNhapLai = request.getParameter("matKhauNhapLai");
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");

			request.setAttribute("tenDangNhap", tenDangNhap);
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("email", email);
			request.setAttribute("dongYNhanMail", dongYNhanMail);

			String url = "";

			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();

			if (khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
				baoLoi += "Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
			}

			if (!matKhau.equals(matKhauNhapLai)) {
				baoLoi += "Mẫu khẩu không khớp.<br/>";
			} else {
				matKhau = Encode.toSHA1(matKhau);
			}

			request.setAttribute("baoLoi", baoLoi);

			if (baoLoi.length() > 0) {
				url = "/khachhang/dangky.jsp";
			} else {
				Random rd = new Random();
				String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + "";
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang,
						diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email,
						dongYNhanMail != null ? "YES" : "NO");
				if (khachHangDAO.insert(kh) > 0) {
					// Day si xac thuc
					String soNgauNhien = CreateRandomNumber.getSoNgauNhien();

					// Quy dinh thoi gian hieu luc
					Date todaysDate = new Date(new java.util.Date().getTime());
					Calendar c = Calendar.getInstance();
					c.setTime(todaysDate);
					c.add(Calendar.DATE, 1); // thoi gian 1 ngay
					Date thoiGianHieuLucXacThuc = new Date(c.getTimeInMillis()); // lay ra so giay trong 1 ngay

					// Trang thai xac thuc = false
					boolean trangThaiXacThuc = false;

					kh.setMaXacThuc(soNgauNhien);
					kh.setThoiGianHieuLucCuaMaXacThuc(thoiGianHieuLucXacThuc);
					kh.setTrangThaiXacThuc(trangThaiXacThuc);

					if (khachHangDAO.updateVerifyInfomation(kh) > 0) {
//						 Gui email cho khach hang
						Email.sendEmail(kh.getEmail(), "Xác thực tài khoản tại TITV.vn", getNoiDung(kh));
					}
				}
				url = "/khachhang/thanhcong.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doimatkhau(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhauCu = request.getParameter("matKhauHienTai");
			String matKhauMoi = request.getParameter("matKhauMoi");
			String nhapLaiMatKhauMoi = request.getParameter("nhapLaiMatKhauMoi");
			System.out.println(matKhauMoi);
			System.out.println(nhapLaiMatKhauMoi);

			String matKhauHienTaiSha1 = Encode.toSHA1(matKhauCu);
			String baoLoi = "";
			String url = "/khachhang/doimatkhau.jsp";
			// Kiem tra mat khau cu co giong hay khong , mat khau cu la khach hang dang dang
			// nhap thanh cong
			// Tu session de lay ra khach hang va kiem tra mat khau co giong mat khau nhap
			// lai trong doi mat khau khong
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("khachHang");
			KhachHang khachHang = null;
			if (obj != null) {
				khachHang = (KhachHang) obj;
			}
			if (khachHang == null) {
				baoLoi = "Bạn chưa đăng nhập vào hệ thống";
				url = "/khachhang/dangnhap.jsp";
			} else {
				if (!matKhauHienTaiSha1.equals(khachHang.getMatKhau())) {
					baoLoi = "Mật khẩu hiện tại không chính xác";
				} else {
					if (!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
						baoLoi = "Mật khẩu nhập lại không khớp!";
					} else {
						String matKhau_Sha1 = Encode.toSHA1(matKhauMoi);
						khachHang.setMatKhau(matKhau_Sha1);
						KhachHangDAO khd = new KhachHangDAO();
						if (khd.changePassWord(khachHang)) {
							baoLoi = "Mật khẩu đã thay đổi thành công!";
						} else {
							baoLoi = "Quá trình thay đổi mật khẩu không thành công";
						}
					}
				}

			}
			request.setAttribute("baoLoi", baoLoi);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void thaydoithongtin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanEmail");
			String url = "";

			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();

			request.setAttribute("baoLoi", baoLoi);
			if (baoLoi.length() > 0) {
				url = "/khachhang/dangky.jsp";
			} else {
				Object obj = request.getSession().getAttribute("khachHang");
				KhachHang khachHang = null;
				if (obj != null) {
					khachHang = (KhachHang) obj;
					if (khachHang != null) {
						String maKhachHang = khachHang.getMaKhachHang(); // lay ma khach hàng đang là phiên đăng nhập
						KhachHang kh = new KhachHang(maKhachHang, "", "", hoVaTen, gioiTinh, diaChiKhachHang,
								diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email,
								dongYNhanMail != null ? "YES" : "NO");
						System.out.println(kh.toString());
						khachHangDAO.updateInfo(kh);
						KhachHang kh2 = khachHangDAO.selectById(kh);
						request.getSession().setAttribute("khachHang", kh2);
						url = "/khachhang/thanhcong.jsp";
					}
				}
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void xacThuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			String maKhachHang = request.getParameter("maKhachHang");
			String maXacThuc = request.getParameter("maXacThuc");

			KhachHangDAO khachHangDAO = new KhachHangDAO();

			KhachHang kh = new KhachHang();
			kh.setMaKhachHang(maKhachHang);
			KhachHang khachHang = khachHangDAO.selectById(kh); // lay thong tin cua khach hang có thông tin đang xác
																// thực

			String msg = "";
			if (khachHang != null) {
				// Kiem tra ma xac thuc co giong nhau hay khong? // Kiem tra xem ma xac thuc con
				// hieu luc hay khong?
				if (khachHang.getMaXacThuc().equals(maXacThuc)) {
					// Thanh Cong
					khachHang.setTrangThaiXacThuc(true);
					khachHangDAO.updateVerifyInfomation(khachHang);
					msg = "Xác thực thành công!";

				} else {
					// That Bai
					msg = "Xác thực không thành công!";
				}
			} else {
				msg = "Tài khoản không tồn tại!";
			}
			System.out.println(msg);
			String url = "/khachhang/thongbao.jsp";
			request.setAttribute("baoLoi", msg);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getNoiDung(KhachHang kh) {
		String link = "http://localhost:8080/JSP_Servlet_Built_App_Website_Online_/khach-hang?hanhDong=xac-thuc&maKhachHang="
				+ kh.getMaKhachHang() + "&maXacThuc=" + kh.getMaXacThuc();
		String noiDung = "<p>TOMCAT.vn xin ch&agrave;o bạn <strong>" + kh.getHoVaTen() + "</strong>,</p>\r\n"
				+ "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"
				+ kh.getMaXacThuc() + "</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
				+ "<p><a href=\"" + link + "\">" + link + "</a></p>\r\n"
				+ "<p>Đ&acirc;y l&agrave; email tự động, vui l&ograve;ng kh&ocirc;ng phản hồi email n&agrave;y.</p>\r\n"
				+ "<p>Tr&acirc;n trọng cảm ơn.</p>";
		return noiDung;
	}

	private void thayDoiAnh(HttpServletRequest request, HttpServletResponse response) {
		Object obj = request.getSession().getAttribute("khachHang");
		KhachHang khachHang = null;
		if (obj != null) {
			khachHang = (KhachHang) obj;
		}
		if (khachHang != null) {
			try {
				String folder = getServletContext().getRealPath(getInitParameter("avatar"));
				File file;
				int maxFileSize = 5000 * 1024; // 5GB
				int maxMeSize = 5000 * 1024; // 5GB

				String contentType = request.getContentType();

				if (contentType.indexOf(contentType) >= 0) {
					DiskFileItemFactory factory = new DiskFileItemFactory();

					// Quy dinh dung luong toi da cho 1 file
					factory.setSizeThreshold(maxMeSize);

					// Tao file upload
					ServletFileUpload upload = new ServletFileUpload(factory);

					upload.setSizeMax(maxFileSize);

					List<FileItem> files = upload.parseRequest((RequestContext) request);

					for (FileItem fileItem : files) {
						// tem file
						String fileName = System.currentTimeMillis() + fileItem.getName();
						// duong dan
						String path = folder + "\\" + fileName;
						file = new File(path);

						fileItem.write(file);

						khachHang.setDuongDanAnh(fileName);
						KhachHangDAO khachHangDAO = new KhachHangDAO();
						khachHangDAO.update(khachHang);
						khachHang = khachHangDAO.selectById(khachHang);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
