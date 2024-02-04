package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;
import util.Encode;

/**
 * Servlet implementation class doimatkhau
 */
@WebServlet("/doi-mat-khau")
public class doimatkhau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public doimatkhau() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tenDangNhap = request.getParameter("tenDangNhap");
		String matKhauCu = request.getParameter("matKhauHienTai");
		String matKhauMoi = request.getParameter("matKhauMoi");
		String nhapLaiMatKhauMoi = request.getParameter("nhapLaiMatKhauMoi");
		System.out.println(matKhauMoi);
		System.out.println(nhapLaiMatKhauMoi);

		String matKhauHienTaiSha1 = Encode.toSHA1(matKhauCu);
		String baoLoi = "";
		String url = "/doimatkhau.jsp";
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
			url = "/dangnhap.jsp";
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
