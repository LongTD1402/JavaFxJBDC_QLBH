package service;

import java.util.List;

import check_exceptions.CheckListSP_HangSX;
import model.HangSX;

public interface IHangSXService extends IBaseService<HangSX>{
	void insertNew(HangSX hangsx);
	void deleteOne(HangSX hangsx) throws CheckListSP_HangSX;
	void updateOne(HangSX hangsx);
	List<HangSX> findAll();
	HangSX findByMa(String ma);
}
