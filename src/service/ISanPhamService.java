package service;

import java.util.List;

import model.SanPham;

public interface ISanPhamService extends IBaseService<SanPham> {

	void insertNew(SanPham e);

	void deleteOne(SanPham e) throws Exception;

	void updateOne(SanPham e);

	List<SanPham> findAll();

	SanPham findByMa(String ma);

}
