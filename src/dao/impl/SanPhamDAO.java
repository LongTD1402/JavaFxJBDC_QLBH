package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ISanPhamDAO;
import model.HangSX;
import model.SanPham;

public class SanPhamDAO extends GenericDAO<SanPham> implements ISanPhamDAO {

	@Override
	protected Class<SanPham> clazz() {
		return SanPham.class;
	}

	private HangSXDAO hangSxDao = new HangSXDAO();
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SanPham> findResultSet(ResultSet rs) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		try {
			while (rs.next()) {
				SanPham newSP = new SanPham(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getString(5), rs.getBigDecimal(6), rs.getString(7), rs.getString(8));
				HangSX hang = hangSxDao.findByMa(newSP.getMaHangSX());
				newSP.setHangSX(hang);
				SanPham.curId = Integer.parseInt(rs.getString(1).replace("SP", ""));
				list.add(newSP);
			}
		} catch (SQLException e) {
			System.out.println("Lỗi:");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Object[] toObjectArr(SanPham sp) {
		Object[] obj = new Object[] { sp.getMaSp(), sp.getMaHangSX(), sp.getTenSP(), sp.getSoLuong(), sp.getMauSac(),
				sp.getGiaBan(), sp.getDvt(), sp.getMoTa() };
		return obj;
	}

	@Override
	public SanPham findByMa(String ma) {
		return null;
	}

	@Override
	public void insert(SanPham sanpham) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "insert into SanPham(MaSP,MaHangSX,TenSP,SoLuong,MauSac,GiaBan,DVT,MoTa) values(?,?,?,?,?,?,?,?)";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, sanpham.getMaSp());
			prstmt.setString(2, sanpham.getMaHangSX());
			prstmt.setString(3, sanpham.getTenSP());
			prstmt.setInt(4, sanpham.getSoLuong());
			prstmt.setString(5, sanpham.getMauSac());
			prstmt.setBigDecimal(6, sanpham.getGiaBan());
			prstmt.setString(7, sanpham.getDvt());
			prstmt.setString(8, sanpham.getMoTa());
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
	public void delete(SanPham sp) {
		String sql = "delete from SanPham where " + "MaSP='" + sp.getMaSp() + "'";
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
	public void update(SanPham sanpham) {
		Connection conn=null;
		java.sql.PreparedStatement prstmt=null;
		String sql = "update SanPham set MaSP=?, MaHangSX=?, TenSP=?, SoLuong=?,MauSac=?, GiaBan=?, DVT=?, MoTa=? where MaSP=?";
		try {
			conn = DatabaseInfo.getConnection();
			prstmt = conn.prepareStatement(sql);
			prstmt.setString(1, sanpham.getMaSp());
			prstmt.setString(2, sanpham.getMaHangSX());
			prstmt.setString(3, sanpham.getTenSP());
			prstmt.setInt(4, sanpham.getSoLuong());
			prstmt.setString(5, sanpham.getMauSac());
			prstmt.setBigDecimal(6, sanpham.getGiaBan());
			prstmt.setString(7, sanpham.getDvt());
			prstmt.setString(8, sanpham.getMoTa());
			prstmt.setString(9, sanpham.getMaSp());
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
	public List<SanPham> findByMaHang(String ma){
		String sql="Select *from SanPham Where MaHangSX=?";
		return query(sql, ma);
	}
}
