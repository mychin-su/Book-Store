package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ChiTietDonHang;
import model.DonHang;
import model.SanPham;

public class ChiTietDonHangDAO implements DAOInterface<ChiTietDonHang> {

	@Override
	public ArrayList<ChiTietDonHang> selectAll() {
		ArrayList<ChiTietDonHang> ketQua = new ArrayList<ChiTietDonHang>();

		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM chiTietDonHang";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:

			while (rs.next()) {
				String maChiTietDonHang = rs.getString("maChiTietDonHang");
				String maDonHang = rs.getString("maDonHang");
				String maSanPham = rs.getString("sanPham");
				double soLuong = rs.getDouble("soLuong");
				double giaGoc = rs.getDouble("giaGoc");
				double giamGia = rs.getDouble("giamGia");
				double giaBan = rs.getDouble("giaBan");
				double thuevat = rs.getDouble("thueVAT");
				double tongTien = rs.getDouble("tongTien");

				DonHang donHang = new DonHangDAO()
						.selectById(new DonHang(maDonHang, null, "", "", "", "", 0, 0, null, null));
				SanPham sanPham = new SanPhamDAO()
						.selectById(new SanPham(maSanPham, "", null, 0, 0, 0, 0, 0, null, "", ""));

				ChiTietDonHang ctdh = new ChiTietDonHang(maChiTietDonHang, donHang, sanPham, soLuong, giaGoc, giamGia,
						giaBan, thuevat, tongTien);
				ketQua.add(ctdh);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public ChiTietDonHang selectById(ChiTietDonHang t) {
		ChiTietDonHang ketQua = null;
		try {
			Connection con = JDBCUtil.getConnection();

			String sql = "SELECT * FROM chiTietDonHang WHERE maChiTietDonHang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaChiTietDonHang());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maChiTietDonHang = rs.getString("maChiTietDonHang");
				String maDonHang = rs.getString("maDonHang");// o
				String maSanPham = rs.getString("maSanPham");// o
				double soLuong = rs.getDouble("soLuong");
				double giaGoc = rs.getDouble("giaGoc");
				double giamGia = rs.getDouble("giamGia");
				double giaBan = rs.getDouble("giaBan");
				double thueVAT = rs.getDouble("thueVAT");
				double tongTien = rs.getDouble("tongTien");

				DonHang dh = new DonHangDAO().selectById(new DonHang(maDonHang, null, "", "", "", "", 0, 0, null, null));
				SanPham sp = new SanPhamDAO().selectById(new SanPham(maSanPham, "", null, 0, 0, 0, 0, 0, null, "", ""));

				ketQua = new ChiTietDonHang(maChiTietDonHang, dh, sp, soLuong, giaGoc, giamGia, giaBan, thueVAT,
						tongTien);
				break;
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int insert(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO chiTietDonHang (maChiTietDonHang, maDonHang,maSanPham, soLuong, giaGoc,giamGia,giaBan,thueVat,tongTien) "
					+ " VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaChiTietDonHang());
			st.setString(2, t.getDonHang().getMaDonHang());
			st.setString(3, t.getSanPham().getMaSanPham());
			st.setDouble(4, t.getSoLuong());
			st.setDouble(5, t.getGiaGoc());
			st.setDouble(7, t.getGiamGia());
			st.setDouble(6, t.getGiaBan());
			st.setDouble(8, t.getThueVAT());
			st.setDouble(9, t.getTongTien());
			// Bước 3: thực thi câu lệnh SQL
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

	@Override
	public int insertAll(ArrayList<ChiTietDonHang> arr) {
		int dem = 0;
		for (ChiTietDonHang ChiTietDonHang : arr) {
			dem += this.insert(ChiTietDonHang);
		}
		return dem;
	}

	@Override
	public int delete(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from chiTietDonHang " + " WHERE maChiTietDonHang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaChiTietDonHang());

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

	@Override
	public int deleteAll(ArrayList<ChiTietDonHang> arr) {
		int dem = 0;
		for (ChiTietDonHang ChiTietDonHang : arr) {
			dem += this.delete(ChiTietDonHang);
		}
		return dem;
	}

	@Override
	public int update(ChiTietDonHang t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE chiTietDonHang SET maDonHang=?, maSanPham=?, soLuong=?, giaGoc=?, giamGia=?, giaBan=?, thueVAT=?, tongTien=?"
					+ " WHERE maChiTietDonHang=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getDonHang().getMaDonHang());
			st.setString(2, t.getSanPham().getMaSanPham());
			st.setDouble(3, t.getSoLuong());
			st.setDouble(4, t.getGiaGoc());
			st.setDouble(5, t.getGiamGia());
			st.setDouble(6, t.getGiaBan());
			st.setDouble(7, t.getThueVAT());
			st.setDouble(8, t.getTongTien());
			st.setString(9, t.getMaChiTietDonHang());

			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
}
