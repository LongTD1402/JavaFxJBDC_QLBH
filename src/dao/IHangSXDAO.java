package dao;

import java.util.List;

import model.HangSX;

public interface IHangSXDAO extends IGenericDAO<HangSX> {
	void insert(HangSX hangsx);
	void delete(HangSX hangsx);
	void update(HangSX hangsx);
}
