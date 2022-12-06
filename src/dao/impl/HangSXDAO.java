package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.IHangSXDAO;
import model.HangSX;
import model.SanPham;


public class HangSXDAO extends GenericDAO<HangSX> implements IHangSXDAO {

	@Override
	protected Class<HangSX> clazz() {
		// TODO Auto-generated method stub
		return HangSX.class;
	}
	private SanPhamDAO sanPhamDAO;
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HangSX> findResultSet(java.sql.ResultSet rs) {
		try {
			ArrayList<HangSX> list = new ArrayList<HangSX>();
			while (rs.next()) {
				HangSX newH = new HangSX(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				HangSX.curId = Integer.parseInt(rs.getString(1).replace("MH", ""));
				list.add(newH);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("Lỗi:" + e.getMessage());
			return null;
		}
	}

	@Override
	public void insert(HangSX hangsx) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "insert into HangSX(MaHangSX,TenHang,DiaChi,SoDT,Email) values(?,?,?,?,?)";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, hangsx.getMaHangSX());
			prstmt.setString(2, hangsx.getTenHang());
			prstmt.setString(3, hangsx.getDiaChi());
			prstmt.setString(4, hangsx.getSdt());
			prstmt.setString(5, hangsx.getEmail());
			prstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prstmt != null) {
					prstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
			}
		}
	}

	@Override
	public void delete(HangSX hangsx) {
		String sql = "delete from HangSX where " + "MaHangSX='" + hangsx.getMaHangSX() + "'";
		Connection conn = null;
		java.sql.PreparedStatement prstmt = null;
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prstmt != null) {
					prstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
			}
		}
	}

	@Override
	public void update(HangSX hangsx) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "update HangSX set TenHang=?, DiaChi=?, SoDT=?, Email=? where MaHangSX=?";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, hangsx.getTenHang());
			prstmt.setString(2, hangsx.getDiaChi());
			prstmt.setString(3, hangsx.getSdt());
			prstmt.setString(4, hangsx.getEmail());
			prstmt.setString(5, hangsx.getMaHangSX());
			prstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (prstmt != null) {
					prstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("!!Lỗi:" + e.getMessage());
			}
		}
	}

	@Override
	public Object[] toObjectArr(HangSX hangsx) {
		Object[] obj = new Object[] { hangsx.getMaHangSX(), hangsx.getTenHang(), hangsx.getDiaChi(), hangsx.getSdt(),
				hangsx.getEmail() };
		return obj;
	}

	public HangSX findByMa(String ma) {
		String sql = " select* from HangSX where MaHangSX =?";
		return (HangSX) query(sql, ma).get(0);
	}

}
