package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DonHang;
import model.KhachHang;

public class DonHangDAO implements DAOInterface<DonHang> {

	@Override
	public ArrayList<DonHang> selectAll() {
		ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
		try {
			// Buoc1: Tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao doi tuong Statement
			String sql = "SELECT * FROM donHang";
			PreparedStatement st = con.prepareStatement(sql);

			// Thuc thi cau lenh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maDonHang = rs.getString("maDonHang");
				String maKhachhHang = rs.getString("maKhachHang");
				String diaChiMuaHang = rs.getString("diaChiNguoiMua");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
				String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
				double soTienDaThanhToan = rs.getDouble("soTienDaThanhToan");
				double soTienConThieu = rs.getDouble("soTienConThieu");
				Date ngayDatHang = rs.getDate("ngayDatHang");
				Date ngayGiaoHang = rs.getDate("ngayGiaoHang");
				KhachHang khachHang = new KhachHangDAO()
						.selectById(new KhachHang(maKhachhHang, "", "", "", "", "", "", "", null, "", "", ""));
				DonHang donHang = new DonHang(maDonHang, khachHang, diaChiMuaHang, diaChiNhanHang, hinhThucThanhToan,
						trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);
				ketQua.add(donHang);
			}

			// Buoc5: Dong ket noi
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public DonHang selectById(DonHang t) {
		DonHang ketQua = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM donHang WHERE maDonHang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());

			// Buoc3: thuc thi cau lenh SQL
			System.out.println("Cau lenh thuc thi: " + sql);
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				String maDonHang = rs.getString("maDonHang");
				String maKhachhHang = rs.getString("maKhachHang");
				String diaChiMuaHang = rs.getString("diaChiMuaHang");
				String diaChiNhanHang = rs.getString("diaChiNhanHang");
				String hinhThucThanhToan = rs.getString("hinhThucThanhToan");
				String trangThaiThanhToan = rs.getString("trangThaiThanhToan");
				double soTienDaThanhToan = rs.getDouble("soTienDaThanhToan");
				double soTienConThieu = rs.getDouble("soTienConThieu");
				Date ngayDatHang = rs.getDate("ngayDatHang");
				Date ngayGiaoHang = rs.getDate("ngayGiaoHang");
				KhachHang khachHang = new KhachHangDAO()
						.selectById(new KhachHang(maKhachhHang, "", "", "", "", "", "", "", null, "", "", ""));
				DonHang donHang = new DonHang(maDonHang, khachHang, diaChiMuaHang, diaChiNhanHang, hinhThucThanhToan,
						trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);
				ketQua = donHang;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(DonHang t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO donHang(maDonHang, maKhachhHang, diaChiMuaHang,diaChiNhanHang,hinhThucThanhToan, trangThaiThanhToan,soTienDaThanhToan,soTienDaThanhToan,soTienConThieu, ngayDatHang,ngayGiaoHang)"
					+ (" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			st.setString(2, t.getKhachHang().getMaKhachHang());
			st.setString(3, t.getDiaChiMuaHang());
			st.setString(4, t.getDiaChiNhanHang());
			st.setString(5, t.getHinhThucThanhToan());
			st.setString(6, t.getTrangThaiThanhToan());
			st.setDouble(7, t.getSoTienDaThanhToan());
			st.setDouble(8, t.getSoTienConThieu());
			st.setDate(9, t.getNgayDatHang());
			st.setDate(10, t.getNgayGiaoHang());

			ketQua = st.executeUpdate();
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co: " + ketQua + " dong bi thay doi!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<DonHang> arr) {
		int count = 0;
		for (DonHang tl : arr) {
			count += insert(tl);
		}
		return count;
	}

	@Override
	public int delete(DonHang t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM donHang " + " WHERE maDonHang=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());

			System.out.println(sql);
			ketQua = st.executeUpdate();

			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketQua + " dong bi thay doi!");
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ketQua;
	}

	@Override
	public int deleteAll(ArrayList<DonHang> arr) {
		int count = 0;
		for (DonHang dh : arr) {
			count += this.delete(dh);
		}
		return count;
	}

	@Override
	public int update(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();

		String sql = "UPDATE donHang" + " SET " + "khachhang=?" + ", diachinguoimua=?" + ",diachinguoinhan=?"
				+ ",trangthai=?" + ",thanhtoan=?" + ",tienthanhtoan=?" + ",tienthieu=?" + ",ngaydathang=?"
				+ ",ngaygiaohang=?" + " WHERE madonhang=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getKhachHang().getMaKhachHang());
			st.setString(2, t.getDiaChiMuaHang());
			st.setString(3, t.getDiaChiNhanHang());
			st.setString(4, t.getHinhThucThanhToan());
			st.setDouble(5, t.getSoTienDaThanhToan());
			st.setString(6, t.getTrangThaiThanhToan());
			st.setDouble(7, t.getSoTienConThieu());
			st.setDate(8, t.getNgayDatHang());
			st.setDate(9, t.getNgayGiaoHang());
			st.setString(10, t.getMaDonHang());

			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}

}
