package dao;

import java.util.List;

import model.SanPham;

public interface ISanPhamDAO extends IGenericDAO<SanPham> {
	void insert(SanPham hangsx);

	void delete(SanPham hangsx);

	void update(SanPham hangsx);

	List<SanPham> findByMaHang(String maHangSX);
}
