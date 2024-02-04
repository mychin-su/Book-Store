package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.KhachHang;

public class KhachHangDAO implements DAOInterface<KhachHang> {

	@Override
	public ArrayList<KhachHang> selectAll() {
		ArrayList<KhachHang> ketQua = new ArrayList<KhachHang>();
		try {
			// Buoc 1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc 2: tao ra doi tuong statement
			String sql = "SELECT * FROM KhachHang";
			PreparedStatement st = con.prepareStatement(sql);

			// Buoc3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Buoc 4:
			while (rs.next()) {
				String maKhachHang = rs.getString("maKhachHang");
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String hoVaTen = rs.getString("hoVaTen");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String diaChiMuaHang = rs.getString("diaChiMuaHang");
				Date ngaySinh = rs.getDate("ngaySinh");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");
				String dangKyNhanBangTin = rs.getString("dangKyNhanBangTin");
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi,
						diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);
				ketQua.add(kh);
			}
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public KhachHang selectById(KhachHang t) {
		KhachHang ketQua = null;
		try {
			// Buoc 1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc 2: tao ket noi den doi tuong statement
			String sql = "SELECT * FROM KhachHang WHERE maKhachHang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());

			// Buoc3: thuc thi cau lenh SQL
			System.out.println();
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				String maKhachHang = rs.getString("maKhachHang");
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String hoVaTen = rs.getString("hoVaTen");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String diaChiMuaHang = rs.getString("diaChiMuaHang");
				Date ngaySinh = rs.getDate("ngaySinh");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");
				String dangKyNhanBangTin = rs.getString("dangKyNhanBangTin");
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi,
						diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);
				ketQua = kh;
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(KhachHang t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "INSERT INTO KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin)"
					+ " VALUES(?, ?, ?, ?, ?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());
			st.setString(2, t.getTenDangNhap());
			st.setString(3, t.getMatKhau());
			st.setString(4, t.getHoVaTen());
			st.setString(5, t.getGioiTinh());
			st.setString(6, t.getDiaChi());
			st.setString(7, t.getDiaChiNhanHang());
			st.setString(8, t.getDiaChiMuaHang());
			st.setDate(9, t.getNgaySinh());
			st.setString(10, t.getSoDienThoai());
			st.setString(11, t.getEmail());
			st.setString(12, t.getDangKyNhanBangTin());

			// Buoc3: thuc thi cau lenh SQL
			ketQua = st.executeUpdate();

			// Buoc4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay dổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<KhachHang> arr) {
		int dem = 0;
		for (KhachHang khachHang : arr) {
			dem += this.insert(khachHang);
		}
		return dem;
	}

	@Override
	public int delete(KhachHang t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket not den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "DELETE FROM KhachHang " + " WHERE maKhachHang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaKhachHang());
			// Buoc3: thuc thi cau lenh SQL
			ketQua = st.executeUpdate();

			// Buoc4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dong bi thay doi");

			// Buoc5:
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<KhachHang> arr) {
		int dem = 0;
		for (KhachHang khachHang : arr) {
			dem += this.delete(khachHang);
		}
		return dem;
	}

	@Override
	public int update(KhachHang t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "UPDATE khachhang " + " SET " + " tendangnhap=?" + ", matkhau=?" + ", hoten=?" + ", gioitinh=?"
					+ ", diachi=?" + ", diachinhanhang=?" + ", diachimuahang=?" + ", ngaysinh=?" + ", sodienthoai=?"
					+ ", email=?" + ", dangkinhanbangtin=?" + " WHERE makhachhang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenDangNhap());
			st.setString(2, t.getMatKhau());
			st.setString(3, t.getHoVaTen());
			st.setString(4, t.getGioiTinh());
			st.setString(5, t.getDiaChi());
			st.setString(6, t.getDiaChiNhanHang());
			st.setString(7, t.getDiaChiMuaHang());
			st.setDate(8, t.getNgaySinh());
			st.setString(9, t.getSoDienThoai());
			st.setString(10, t.getEmail());
			st.setString(11, t.getDangKyNhanBangTin());
			st.setString(12, t.getMaKhachHang());

			// Buoc3: thuc thi cau lengh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Buoc4:
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketQua + " dong bi thay doi!");

			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean kiemTraTenDangNhap(String tenDangNhap) {
		boolean ketQua = false;
		try {
			// Buoc 1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc 2: tao ket noi den doi tuong statement
			String sql = "SELECT * FROM KhachHang WHERE tenDangNhap=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, tenDangNhap);

			// Buoc3: thuc thi cau lenh SQL
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				ketQua = true;
			}
			// Buoc5: Dong ket noi
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public KhachHang selectByUsernameAndPassWord(KhachHang t) {
		KhachHang ketQua = null;
		try {
			// Buoc 1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc 2: tao ket noi den doi tuong statement
			String sql = "SELECT * FROM KhachHang WHERE tenDangNhap=? and matKhau=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenDangNhap());
			st.setString(2, t.getMatKhau());

			// Buoc3: thuc thi cau lenh SQL
			System.out.println();
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				String maKhachHang = rs.getString("maKhachHang");
				String tenDangNhap = rs.getString("tenDangNhap");
				String matKhau = rs.getString("matKhau");
				String hoVaTen = rs.getString("hoVaTen");
				String gioiTinh = rs.getString("gioiTinh");
				String diaChi = rs.getString("diaChi");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String diaChiMuaHang = rs.getString("diaChiMuaHang");
				Date ngaySinh = rs.getDate("ngaySinh");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");
				String dangKyNhanBangTin = rs.getString("dangKyNhanBangTin");
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi,
						diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin);
				ketQua = kh;
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public boolean changePassWord(KhachHang t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "UPDATE KhachHang " + " SET " + " matKhau=? " + " WHERE maKhachHang = ?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMatKhau());
			st.setString(2, t.getMaKhachHang());

			// Buoc3: thuc thi cau lengh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Buoc4:
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketQua + " dong bi thay doi!");

			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua > 0;
	}

	public int updateInfo(KhachHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE KhachHang " + " SET " + " hoVaTen=?" + ", gioiTinh=?" + ", diaChi=?"
					+ ", diaChiNhanHang=?" + ", diaChiMuaHang=?" + ", ngaySinh=?" + ", soDienThoai=?" + ", email=?"
					+ ", dangKyNhanBangTin=?" + " WHERE maKhachHang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setString(2, t.getGioiTinh());
			st.setString(3, t.getDiaChi());
			st.setString(4, t.getDiaChiNhanHang());
			st.setString(5, t.getDiaChiMuaHang());
			st.setDate(6, t.getNgaySinh());
			st.setString(7, t.getSoDienThoai());
			st.setString(8, t.getEmail());
			st.setString(9, t.getDangKyNhanBangTin());
			st.setString(10, t.getMaKhachHang());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	public static void main(String[] args) {

	}
}
