package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.SanPham;
import model.TacGia;
import model.TheLoai;

public class SanPhamDAO implements DAOInterface<SanPham> {

	@Override
	public ArrayList<SanPham> selectAll() {
		ArrayList<SanPham> ketQua = new ArrayList<SanPham>();
		try {
			// Buoc1: Tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao doi tuong Statement
			String sql = "SELECT * FROM SanPham";
			PreparedStatement st = con.prepareStatement(sql);

			// Thuc thi cau lenh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maSanPham = rs.getString("maSanPham");
				String tenSanPham = rs.getString("tenSanPham");
				String maTacGia = rs.getString("maTacGia");
				int namXuatBan = rs.getInt("namXuatBan");
				double giaNhap = rs.getDouble("giaNhap");
				double giaGoc = rs.getDouble("giaGoc");
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				String maTheLoai = rs.getString("maTheLoai");
				String ngonNgu = rs.getString("ngonNgu");
				String moTa = rs.getString("moTa");

				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(maTacGia, "", null, "")));
				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(maTheLoai, "")));

				SanPham sp = new SanPham(maSanPham, tenSanPham, tacGia, namXuatBan, giaNhap, giaGoc, giaBan, soLuong,
						theLoai, ngonNgu, moTa);
				ketQua.add(sp);
			}
			// Buoc5: Dong ket noi
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public SanPham selectById(SanPham t) {
		SanPham ketQua = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM SanPham WHERE maSanPham=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());

			// Buoc3: thuc thi cau lenh SQL
			System.out.println("Cau lenh thuc thi: " + sql);
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				String maSanPham = rs.getString("maSanPham");
				String tenSanPham = rs.getString("tenSanPham");
				String maTacGia = rs.getString("maTacGia");
				int namXuatBan = rs.getInt("namXuatBan");
				double giaNhap = rs.getDouble("giaNhap");
				double giaGoc = rs.getDouble("giaGoc");
				double giaBan = rs.getDouble("giaBan");
				int soLuong = rs.getInt("soLuong");
				String maTheLoai = rs.getString("maTheLoai");
				String ngonNgu = rs.getString("ngonNgu");
				String moTa = rs.getString("moTa");

				TacGia tacGia = (new TacGiaDAO().selectById(new TacGia(maTacGia, "", null, "")));
				TheLoai theLoai = (new TheLoaiDAO().selectById(new TheLoai(maTheLoai, "")));

				SanPham sp = new SanPham(maSanPham, tenSanPham, tacGia, namXuatBan, giaNhap, giaGoc, giaBan, soLuong,
						theLoai, ngonNgu, moTa);
				ketQua = sp;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(SanPham t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO sanpham (masanpham,tensanpham, matacgia, namxuatban,"
					+ " gianhap, giagoc, giaban, soluong, matheloai, ngonngu, mota) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());
			st.setString(2, t.getTenSanPham());
			st.setString(3, t.getTacGia().getMaTacGia());
			st.setInt(4, t.getNamXuatBan());
			st.setDouble(5, t.getGiaNhap());
			st.setDouble(6, t.getGiaGoc());
			st.setDouble(7, t.getGiaBan());
			st.setInt(8, t.getSoLuong());
			st.setString(9, t.getTheLoai().getMaTheLoai());
			st.setString(10, t.getNgonNgu());
			st.setString(11, t.getMoTa());

			ketQua = st.executeUpdate();
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co: " + ketQua + " dong bi thay doi!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<SanPham> arr) {
		int count = 0;
		for (SanPham sp : arr) {
			count += insert(sp);
		}
		return count;
	}

	@Override
	public int delete(SanPham t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM SanPham " + " WHERE maSanPham=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());

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
	public int deleteAll(ArrayList<SanPham> arr) {
		int count = 0;
		for (SanPham sp : arr) {
			count += this.delete(sp);
		}
		return count;
	}

	@Override
	public int update(SanPham t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE sanpham " + " SET " + "tensanpham=?, matacgia=?, namxuatban=?, gianhap=?, giagoc=?, "
					+ "giaban=?, soluong=?, matheloai=?, ngonngu=?, mota=?" + " WHERE masanpham=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaSanPham());
			st.setString(2, t.getTenSanPham());
			st.setString(3, t.getTacGia().getMaTacGia());
			st.setInt(4, t.getNamXuatBan());
			st.setDouble(5, t.getGiaNhap());
			st.setDouble(6, t.getGiaGoc());
			st.setDouble(7, t.getGiaBan());
			st.setInt(8, t.getSoLuong());
			st.setString(9, t.getTheLoai().getMaTheLoai());
			st.setString(10, t.getNgonNgu());
			st.setString(11, t.getMoTa());

			System.out.println(sql);
			ketQua = st.executeUpdate();

			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketQua + " dong bi thay doi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}
}
