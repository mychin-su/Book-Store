package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.KhachHangDAO;
import model.KhachHang;

/**
 * Servlet implementation class thaydoithongtin
 */
@WebServlet("/thay-doi-thong-tin")
public class thaydoithongtin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public thaydoithongtin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			url = "/dangky.jsp";
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
					url = "/thanhcong.jsp";
				}
			}
		}
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
