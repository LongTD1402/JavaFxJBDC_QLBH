package model;

import java.math.BigDecimal;

import check_exceptions.NullFieldException;

public class SanPham {
	public static int curId = 0;
	private String maSp;
	private String maHangSX;
	private HangSX hangSX;
	private String tenSP;
	private int SoLuong;
	private String mauSac;
	private BigDecimal giaBan;
	private String dvt;
	private String moTa;

	public SanPham(String tenSp) {
		curId++;
		this.maSp = "SP" + String.format("%03d", curId);
		this.tenSP=tenSp;
		updateCurr(maSp);
	}
	public SanPham(String maHangSX, String tenSP, int soLuong, String mauSac, BigDecimal giaBan, String dvt,
			String moTa) {
		curId++;
		this.maSp = "SP" + String.format("%03d", curId);
		this.maHangSX = maHangSX;
		this.tenSP = tenSP;
		SoLuong = soLuong;
		this.mauSac = mauSac;
		this.giaBan = giaBan;
		this.dvt = dvt;
		this.moTa = moTa;
		updateCurr(maSp);
	}

	public SanPham(String maSp, String maHangSX, String tenSP, int soLuong, String mauSac, BigDecimal giaBan,
			String dvt, String moTa) {
		curId++;
		this.maSp = maSp;
		this.maHangSX = maHangSX;
		this.tenSP = tenSP;
		this.SoLuong = soLuong;
		this.mauSac = mauSac;
		this.giaBan = giaBan;
		this.dvt = dvt;
		this.moTa = moTa;
		updateCurr(maSp);
	}

	public String getMaSp() {
		return maSp;
	}

	public void setMaSp(String maSp) {
		this.maSp = maSp;
	}

	public String getMaHangSX() {
		return maHangSX;
	}

	public void setMaHangSX(String maHangSX) {
		this.maHangSX = maHangSX;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) throws NullFieldException {
		if (tenSP.replaceAll(" ", "").isEmpty()) {
			throw new NullFieldException("Tên sản phẩm không được để trống!");
		}
		this.tenSP = tenSP;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) throws Exception, NumberFormatException {
		if (soLuong < 0) {
			throw new Exception("Số lượng >=0 !");
		}
		try {
			SoLuong = soLuong;
		} catch (NumberFormatException e) {
			System.out.println("[!!-Lỗi] :Số lượng không hợp lệ");
			throw new NumberFormatException("[!!-Lỗi] :Số lượng không hợp lệ");
		}

	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public BigDecimal getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(BigDecimal giaBan) throws Exception {
		try {
			this.giaBan = giaBan;
		} catch (Exception e) {
			System.out.println("[!!-Lỗi] :Giá bán không hợp lệ");
			throw new Exception("[!!-Lỗi] :Giá bán không hợp lệ");
		}

	}

	public String getDvt() {
		return dvt;
	}

	public void setDvt(String dvt) {
		this.dvt = dvt;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public HangSX getHangSX() {
		return hangSX;
	}

	public void setHangSX(HangSX hangSX) {
		this.hangSX = hangSX;
	}
	private void updateCurr(String ma){
		String numb = ma.replace("SP", "");
		try {
			int n = Integer.parseInt(numb);
			if(n > curId) curId = n;
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "SanPham [maSp=" + maSp + ", MaHangSX=" + maHangSX + ", tenSP=" + tenSP + ", SoLuong=" + SoLuong
				+ ", mauSac=" + mauSac + ", giaBan=" + giaBan + ", dvt=" + dvt + ", moTa=" + moTa + "]";
	}

}
