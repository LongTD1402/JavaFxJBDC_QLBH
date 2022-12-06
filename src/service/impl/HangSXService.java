package service.impl;

import java.util.ArrayList;
import java.util.List;

import check_exceptions.CheckListSP_HangSX;
import dao.IHangSXDAO;
import dao.ISanPhamDAO;
import dao.impl.HangSXDAO;
import dao.impl.SanPhamDAO;
import model.HangSX;
import model.SanPham;
import service.IHangSXService;

public class HangSXService extends BaseService<HangSX> implements IHangSXService{
	private IHangSXDAO hangSxDao;
	private ISanPhamDAO sanPhamDao;
	private List<HangSX> listHangSx;
	public HangSXService() {
		hangSxDao = new HangSXDAO();
		sanPhamDao= new SanPhamDAO();
	}
	public List<HangSX> findAll() {
		this.listHangSx= hangSxDao.findAll();
		for (HangSX hangSX : listHangSx) {
			hangSX.setListSp((ArrayList<SanPham>)sanPhamDao.findByMaHang(hangSX.getMaHangSX()));
		}
		return listHangSx;
	}
	public void insertNew(HangSX hangsx) {
		hangSxDao.insert(hangsx);
	}

	public void deleteOne(HangSX hangsx) throws CheckListSP_HangSX{
		HangSX hang =null;
		for (HangSX hangSX : findAll()) {
			if(hangsx.getMaHangSX().equals(hangSX.getMaHangSX())==true) {
				hang=hangSX;
			}
		}
		if(hang.getListSp().size()!=0) {
			throw new CheckListSP_HangSX("Hãng sản xuất đang tồn tại sản phẩm.Không thể xóa!");
		}
		else {
			hangSxDao.delete(hangsx);
		}
	}

	public void updateOne(HangSX hangsx) {
		hangSxDao.update(hangsx);
	}
	
	public HangSX findByMa(String ma) {
		HangSX hang= null;
		for (HangSX hangSX : findAll()) {
			if(ma.equals(hangSX.getMaHangSX())==true) {
				hang= hangSX;
				break;
			}
		}
		return hang;
	}
}
