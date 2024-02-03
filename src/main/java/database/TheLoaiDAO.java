package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TheLoai;

public class TheLoaiDAO implements DAOInterface<TheLoai> {

	@Override
	public ArrayList<TheLoai> selectAll() {
		ArrayList<TheLoai> ketQua = new ArrayList<TheLoai>();
		try {
			// Buoc1: Tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao doi tuong Statement
			String sql = "SELECT * FROM TheLoai";
			PreparedStatement st = con.prepareStatement(sql);

			// Thuc thi cau lenh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");

				TheLoai tl = new TheLoai(maTheLoai, tenTheLoai);
				ketQua.add(tl);
			}

			// Buoc5: Dong ket noi
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public TheLoai selectById(TheLoai t) {
		TheLoai ketQua = null;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM TheLoai WHERE maTheLoai=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());

			// Buoc3: thuc thi cau lenh SQL
			System.out.println("Cau lenh thuc thi: " + sql);
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				String maTheLoai = rs.getString("maTheLoai");
				String tenTheLoai = rs.getString("tenTheLoai");
				TheLoai tl = new TheLoai(maTheLoai, tenTheLoai);
				ketQua = tl;
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(TheLoai t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "INSERT INTO TheLoai(maTheLoai, tenTheLoai)" + (" VALUES(?, ?)");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());
			st.setString(2, t.getTenTheLoại());

			ketQua = st.executeUpdate();
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co: " + ketQua + " dong bi thay doi!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<TheLoai> arr) {
		int count = 0;
		for (TheLoai tl : arr) {
			count += insert(tl);
		}
		return count;
	}

	@Override
	public int delete(TheLoai t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "DELETE FROM TheLoai " + " WHERE maTheLoai=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTheLoai());

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
	public int deleteAll(ArrayList<TheLoai> arr) {
		int count = 0;
		for (TheLoai tl : arr) {
			count += this.delete(tl);
		}
		return count;
	}

	@Override
	public int update(TheLoai t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE TheLoai " + " SET " + " tenTheLoai=?" + " WHERE maTheLoai = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTenTheLoại());
			st.setString(2, t.getMaTheLoai());

			System.out.println(sql);
			ketQua = st.executeUpdate();

			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketQua + " dong bi thay doi");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}
	
	public static void main(String[] args) {
		TheLoaiDAO tld = new TheLoaiDAO();
		ArrayList<TheLoai> kq = tld.selectAll();
		for(TheLoai theLoai : kq) {
			System.out.println(theLoai.toString());
		}
		
	}
}
