package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

import check_exceptions.EmailInputException;
import check_exceptions.NullFieldException;
import check_exceptions.PhoneNumberException;

public class HangSX {
	public static int curId = 0;
	private String maHangSX;
	private String tenHang;
	private String diaChi;
	private String sdt;
	private String email;
	private ArrayList<SanPham> listSp=new ArrayList<SanPham>();
	
	public HangSX() {
		curId++;
		this.maHangSX= "MH" + String.format("%03d", curId);
		updateCurr(maHangSX);
	}
	public HangSX(String tenHang, String diaChi, String sdt, String email) {
		curId++;
		this.maHangSX = "MH" + String.format("%03d", curId);
		this.tenHang = tenHang;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		updateCurr(maHangSX);
	}

	public HangSX(String maHangSX, String tenHang, String diaChi, String sdt, String email) {
		curId++;
		this.maHangSX = maHangSX;
		this.tenHang = tenHang;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		updateCurr(maHangSX);
	}
	private void updateCurr(String ma){
		String numb = ma.replace("MH", "");
		try {
			int n = Integer.parseInt(numb);
			if(n > curId) curId = n;
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
	}
	public String getMaHangSX() {
		return maHangSX;
	}

	public void setMaHangSX(String maHangSX) {
		this.maHangSX = maHangSX;
	}

	public String getTenHang() {
		return tenHang;
	}

	public void setTenHang(String tenHang) throws NullFieldException{
		if (tenHang.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Tên hãng không được để trống!");
		}
		this.tenHang = tenHang;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) throws NullFieldException{
		if (diaChi.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Địa chỉ không được để trống!");
		}
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) throws PhoneNumberException,NullFieldException{
		if(Pattern.matches("[0-9]*", sdt)==false) {
			throw new PhoneNumberException("Số điện thoại không hợp lệ!");
		}
		if (sdt.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Số điện thoại không được để trống!");
		}
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws EmailInputException{
		String regex="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		if(Pattern.matches(regex, email)==false) {
			throw new EmailInputException("Email không hợp lệ! (e.g: username@domain.com)");
		}
		this.email = email;
	}

	public ArrayList<SanPham> getListSp() {
		return listSp;
	}

	public void setListSp(ArrayList<SanPham> listSp) {
		this.listSp = listSp;
	}

	@Override
	public String toString() {
		return "HangSX [maHangSX=" + maHangSX + ", tenHang=" + tenHang + ", diaChi=" + diaChi + ", sdt=" + sdt
				+ ", email=" + email + ", listSp=" + listSp + "]";
	}
}
