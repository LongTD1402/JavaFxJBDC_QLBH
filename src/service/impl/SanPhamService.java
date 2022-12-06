package service.impl;

import java.util.List;

import dao.IHangSXDAO;
import dao.ISanPhamDAO;
import dao.impl.HangSXDAO;
import dao.impl.SanPhamDAO;
import model.SanPham;
import service.ISanPhamService;

public class SanPhamService extends BaseService<SanPham> implements ISanPhamService {
	private ISanPhamDAO sanPhamDao;
	private IHangSXDAO hangSXDAO;
	private List<SanPham> listSp;

	public SanPhamService() {
		sanPhamDao = new SanPhamDAO();
		hangSXDAO = new HangSXDAO();
	}

	@Override
	public void insertNew(SanPham e) {
		sanPhamDao.insert(e);
	}

	@Override
	public void deleteOne(SanPham e) throws Exception {
		sanPhamDao.delete(e);
	}

	@Override
	public void updateOne(SanPham e) {
		sanPhamDao.update(e);
	}

	@Override
	public List<SanPham> findAll() {
		this.listSp = sanPhamDao.findAll();
		for (SanPham sp : listSp) {
			sp.setHangSX(hangSXDAO.findByMa(sp.getMaHangSX()));
		}
		return listSp;
	}

	@Override
	public SanPham findByMa(String ma) {
		SanPham sp = null;
		for (SanPham sanPham : findAll()) {
			if (ma.equals(sanPham.getMaSp())==true ) {
				sp = sanPham;
				break;
			}
		}
		return sp;
	}
}
