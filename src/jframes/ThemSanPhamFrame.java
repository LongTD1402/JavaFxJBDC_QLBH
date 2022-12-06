package jframes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.HangSX;
import model.SanPham;
import service.impl.HangSXService;
import service.impl.SanPhamService;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.util.List;

public class ThemSanPhamFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_maSp;
	private JTextField textField_tenSp;
	private JTextField textField_soLuong;
	private JTextField textField_mauSac;
	private JTextField textField_giaBan;
	SanPham newSp = null;
	SanPhamService sanPhamService;
	HangSXService hangSXService;


	/**
	 * Create the frame.
	 */
	public ThemSanPhamFrame() {
		sanPhamService = new SanPhamService();
		hangSXService = new HangSXService();
		setTitle("Thêm sản phẩm mới");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_maSp = new JLabel("Mã sản phẩm:");
		lbl_maSp.setBounds(41, 27, 93, 14);
		contentPane.add(lbl_maSp);

		JLabel lbl_hangSx = new JLabel("Hãng sản xuất:");
		lbl_hangSx.setBounds(328, 60, 86, 14);
		contentPane.add(lbl_hangSx);

		JLabel lbl_tenSp = new JLabel("Tên sản phẩm:");
		lbl_tenSp.setBounds(41, 60, 100, 14);
		contentPane.add(lbl_tenSp);

		JLabel lbl_soLuong = new JLabel("Số lượng:");
		lbl_soLuong.setBounds(41, 93, 93, 14);
		contentPane.add(lbl_soLuong);

		JLabel lbl_mauSac = new JLabel("Màu sắc:");
		lbl_mauSac.setBounds(41, 135, 100, 14);
		contentPane.add(lbl_mauSac);

		JLabel lbl_giaBan = new JLabel("Giá bán: ");
		lbl_giaBan.setBounds(41, 177, 72, 14);
		contentPane.add(lbl_giaBan);

		JLabel lbl_dvt = new JLabel("Đơn vị tính:");
		lbl_dvt.setBounds(328, 177, 72, 14);
		contentPane.add(lbl_dvt);

		JLabel lbl_moTa = new JLabel("Mô tả:");
		lbl_moTa.setBounds(41, 218, 46, 14);
		contentPane.add(lbl_moTa);

		textField_maSp = new JTextField();
		textField_maSp.setBounds(140, 24, 86, 20);
		contentPane.add(textField_maSp);
		textField_maSp.setColumns(10);
		textField_maSp.setText("SP" + String.format("%03d", SanPham.curId + 1));

		JComboBox<String> comboBox_hangSx = new JComboBox<String>();
		comboBox_hangSx.setBounds(419, 56, 140, 22);
		contentPane.add(comboBox_hangSx);
		List<HangSX> listHang = hangSXService.findAll();
		for (HangSX hangSX : listHang) {
			comboBox_hangSx.addItem(hangSX.getTenHang());
		}

		textField_tenSp = new JTextField();
		textField_tenSp.setBounds(140, 57, 178, 20);
		contentPane.add(textField_tenSp);
		textField_tenSp.setColumns(10);

		textField_soLuong = new JTextField();
		textField_soLuong.setBounds(140, 90, 86, 20);
		contentPane.add(textField_soLuong);
		textField_soLuong.setColumns(10);

		textField_mauSac = new JTextField();
		textField_mauSac.setBounds(140, 132, 86, 20);
		contentPane.add(textField_mauSac);
		textField_mauSac.setColumns(10);

		textField_giaBan = new JTextField();
		textField_giaBan.setBounds(140, 174, 140, 20);
		contentPane.add(textField_giaBan);
		textField_giaBan.setColumns(10);

		JComboBox<String> comboBox_dvt = new JComboBox<String>();
		comboBox_dvt.setBounds(419, 173, 100, 22);
		contentPane.add(comboBox_dvt);
		comboBox_dvt.addItem("VND");
		comboBox_dvt.addItem("USD");

		JTextArea textArea_moTa = new JTextArea();
		textArea_moTa.setBounds(140, 218, 419, 117);
		contentPane.add(textArea_moTa);

		JButton btn_lamMoi = new JButton("Làm mới");
		btn_lamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_tenSp.setText("");
				textArea_moTa.setText("");
				textField_giaBan.setText("");
				textField_mauSac.setText("");
				textField_soLuong.setText("");
				comboBox_hangSx.setSelectedIndex(0);
				comboBox_dvt.setSelectedIndex(0);
			}
		});
		btn_lamMoi.setBounds(137, 363, 89, 23);
		contentPane.add(btn_lamMoi);

		JButton btn_troVe = new JButton("Trở về");
		btn_troVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SanPhamFrame().setVisible(true);
				dispose();
			}
		});
		btn_troVe.setBounds(270, 363, 89, 23);
		contentPane.add(btn_troVe);

		JButton btn_tiepTuc = new JButton("Tiếp tục");
		btn_tiepTuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soLuong = 0;
				BigDecimal gia = null;
				String maH=null;
				try {
					if (textField_soLuong.getText().isEmpty()) {
						soLuong = 0;
					} else {
						soLuong = Integer.valueOf(textField_soLuong.getText());
					}
					if (textField_giaBan.getText().isEmpty()) {
						gia = new BigDecimal(0);
					} else {
						gia = new BigDecimal(textField_giaBan.getText());
					}
					for (HangSX hangSX : listHang) {
						if((String) comboBox_hangSx.getSelectedItem()==hangSX.getTenHang()) {
							maH=hangSX.getMaHangSX();
							break;
						}
					}
					newSp = new SanPham("");
					newSp.setMaSp(textField_maSp.getText());
					newSp.setMaHangSX(maH);
					newSp.setTenSP(textField_tenSp.getText());
					newSp.setSoLuong(soLuong);
					newSp.setMauSac(textField_mauSac.getText());
					newSp.setGiaBan(gia);
					newSp.setDvt((String) comboBox_dvt.getSelectedItem());
					newSp.setMoTa(textArea_moTa.getText());
					newSp.setHangSX(hangSXService.findByMa(maH));
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
					newSp = null;
					SanPham.curId--;
				}
				if (newSp != null) {
					System.out.println(newSp.toString());
					sanPhamService.insertNew(newSp);
					JOptionPane.showMessageDialog(contentPane,
							"Thêm thành công " + newSp.getTenSP() + "  Hãng: " + newSp.getHangSX().getTenHang());
					dispose();
				} else {
					System.out.println("null");
				}
			}
		});
		btn_tiepTuc.setBounds(470, 363, 89, 23);
		contentPane.add(btn_tiepTuc);

	}
}
