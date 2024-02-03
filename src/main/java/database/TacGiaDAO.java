package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.TacGia;

public class TacGiaDAO implements DAOInterface<TacGia> {

	@Override
	public ArrayList<TacGia> selectAll() {
		ArrayList<TacGia> ketQua = new ArrayList<TacGia>();
		try {
			// Buoc 1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc 2: tao ra doi tuong statement
			String sql = "SELECT * FROM dbo.TacGia";
			PreparedStatement st = con.prepareStatement(sql);

			// Buoc3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			System.out.println("123");
			// Buoc 4:
			while (rs.next()) {
				String maTacGia = rs.getString("maTacGia");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String tieuSu = rs.getString("tieuSu");

				TacGia tg = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
				ketQua.add(tg);
			}
			// Ngắt kết nối với CSDL
			JDBCUtil.closeConnection(con);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public TacGia selectById(TacGia t) {
		TacGia ketQua = null;
		try {
			// Buoc 1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc 2: tao ket noi den doi tuong statement
			String sql = "SELECT * FROM TacGia WHERE maTacGia=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());

			// Buoc3: thuc thi cau lenh SQL
			System.out.println();
			ResultSet rs = st.executeQuery();

			// Buoc4:
			while (rs.next()) {
				String maTacGia = rs.getString("maTacGia");
				String hoVaTen = rs.getString("hoVaTen");
				Date ngaySinh = rs.getDate("ngaySinh");
				String tieuSu = rs.getString("tieuSu");

				ketQua = new TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu);
				break;
			}
			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(TacGia t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "INSERT INTO TacGia(maTacGia, hoVaTen, ngaySinh, tieuSu)" + " VALUES(?, ?, ?, ?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
			st.setString(2, t.getHoVaTen());
			st.setDate(3, t.getNgaySinh());
			st.setString(4, t.getTieuSu());

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
	public int insertAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem += this.insert(tacGia);
		}
		return dem;
	}

	@Override
	public int delete(TacGia t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket not den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "DELETE FROM TacGia " + " WHERE maTacGia=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaTacGia());
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
	public int deleteAll(ArrayList<TacGia> arr) {
		int dem = 0;
		for (TacGia tacGia : arr) {
			dem += this.delete(tacGia);
		}
		return dem;
	}

	@Override
	public int update(TacGia t) {
		int ketQua = 0;
		try {
			// Buoc1: tao ket noi den CSDL
			Connection con = JDBCUtil.getConnection();

			// Buoc2: tao ra doi tuong statement
			String sql = "UPDATE TacGia" + " SET " + " hoVaTen=?" + ", ngaySinh=?" + ", tieuSu =?"
					+ " WHERE maTacGia=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getHoVaTen());
			st.setDate(2, t.getNgaySinh());
			st.setString(3, t.getTieuSu());
			st.setString(4, t.getMaTacGia());

			// Buoc3: thuc thi cau lengh SQL
			ketQua = st.executeUpdate();

			// Buoc4:
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co" + ketQua + " dong bi thay doi!");

			JDBCUtil.closeConnection(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ketQua;
	}

	public static void main(String[] args) {
		TacGiaDAO tgd = new TacGiaDAO();
		TacGia tg_new = new TacGia("TG1", "Nguyen Van A", new Date(2000-10-15), "");
		System.out.println(tgd.update(tg_new));
	}

}
