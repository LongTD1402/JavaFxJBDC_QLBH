package jframes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import check_exceptions.EmailInputException;
import check_exceptions.NullFieldException;
import check_exceptions.PhoneNumberException;
import model.HangSX;
import model.SanPham;
import service.impl.HangSXService;
import service.impl.SanPhamService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.awt.event.ActionEvent;

public class SuaSanPhamFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_tenSp;
	private JTextField textField_soLuong;
	private JTextField textField_mauSac;
	private JTextField textField_giaBan;
	SanPhamService sanPhamService;
	HangSXService hangSXService;

	/**
	 * Create the frame.
	 */
	public SuaSanPhamFrame(SanPham sp) {
		sanPhamService = new SanPhamService();
		hangSXService = new HangSXService();
		
		setTitle("Sửa sản phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_maSp = new JLabel("Mã sản phẩm:");
		lbl_maSp.setBounds(126, 14, 93, 14);
		contentPane.add(lbl_maSp);
		lbl_maSp.setText(sp.getMaSp());
		
		JLabel lbl_hangSx = new JLabel("Hãng sản xuất:");
		lbl_hangSx.setBounds(314, 47, 86, 14);
		contentPane.add(lbl_hangSx);
		
		JLabel lbl_tenSp = new JLabel("Tên sản phẩm:");
		lbl_tenSp.setBounds(27, 47, 100, 14);
		contentPane.add(lbl_tenSp);
		
		JLabel lbl_soLuong = new JLabel("Số lượng:");
		lbl_soLuong.setBounds(27, 80, 93, 14);
		contentPane.add(lbl_soLuong);
		
		JLabel lbl_mauSac = new JLabel("Màu sắc:");
		lbl_mauSac.setBounds(27, 122, 100, 14);
		contentPane.add(lbl_mauSac);
		
		JLabel lbl_giaBan = new JLabel("Giá bán: ");
		lbl_giaBan.setBounds(27, 164, 72, 14);
		contentPane.add(lbl_giaBan);
		
		JLabel lbl_dvt = new JLabel("Đơn vị tính:");
		lbl_dvt.setBounds(314, 164, 72, 14);
		contentPane.add(lbl_dvt);
		
		JLabel lbl_moTa = new JLabel("Mô tả:");
		lbl_moTa.setBounds(27, 205, 46, 14);
		contentPane.add(lbl_moTa);
		
		JComboBox<String> comboBox_hangSx = new JComboBox<String>();
		comboBox_hangSx.setBounds(405, 43, 140, 22);
		contentPane.add(comboBox_hangSx);
		List<HangSX> listHang = hangSXService.findAll();
		for (HangSX hangSX : listHang) {
			comboBox_hangSx.addItem(hangSX.getTenHang());
		}
		comboBox_hangSx.setSelectedItem(sp.getHangSX().getTenHang());
		
		textField_tenSp = new JTextField();
		textField_tenSp.setColumns(10);
		textField_tenSp.setBounds(126, 44, 178, 20);
		contentPane.add(textField_tenSp);
		textField_tenSp.setText(sp.getTenSP());
		
		textField_soLuong = new JTextField();
		textField_soLuong.setColumns(10);
		textField_soLuong.setBounds(126, 77, 86, 20);
		contentPane.add(textField_soLuong);
		textField_soLuong.setText(String.valueOf(sp.getSoLuong()));
		
		textField_mauSac = new JTextField();
		textField_mauSac.setColumns(10);
		textField_mauSac.setBounds(126, 119, 86, 20);
		contentPane.add(textField_mauSac);
		textField_mauSac.setText(sp.getMauSac());
		
		textField_giaBan = new JTextField();
		textField_giaBan.setColumns(10);
		textField_giaBan.setBounds(126, 161, 140, 20);
		contentPane.add(textField_giaBan);
		String gia=""+sp.getGiaBan();
		textField_giaBan.setText(gia);
		
		JComboBox<String> comboBox_dvt = new JComboBox<String>();
		comboBox_dvt.setBounds(405, 160, 100, 22);
		contentPane.add(comboBox_dvt);
		comboBox_dvt.addItem("VND");
		comboBox_dvt.addItem("USD");
		comboBox_dvt.setSelectedItem(sp.getDvt());
		
		
		JTextArea textArea_moTa = new JTextArea();
		textArea_moTa.setBounds(126, 205, 419, 117);
		contentPane.add(textArea_moTa);
		
		JButton btn_troVe = new JButton("Trở về");
		btn_troVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SanPhamFrame().setVisible(true);
				dispose();
			}
		});
		btn_troVe.setBounds(126, 350, 89, 23);
		contentPane.add(btn_troVe);
		
		JButton btn_tiepTuc = new JButton("Tiếp tục");
		btn_tiepTuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soLuong;
				BigDecimal gia=null;
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
					sp.setTenSP(textField_tenSp.getText());
					sp.setMaHangSX(maH);
					sp.setMauSac(textField_mauSac.getText());
					sp.setSoLuong(soLuong);
					sp.setGiaBan(gia);
					sp.setMoTa(textArea_moTa.getText());
					sp.setDvt((String) comboBox_dvt.getSelectedItem());
					sanPhamService.updateOne(sp);
					JOptionPane.showMessageDialog(contentPane, "Sửa thành công!");
					new SanPhamFrame().setVisible(true);
					dispose();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					if (e2 instanceof NullFieldException) {
						textField_tenSp.setText(sp.getTenSP());
					}
					if (e2 instanceof NumberFormatException) {
						textField_soLuong.setText(""+sp.getSoLuong());
					}
					if (e2 instanceof Exception) {
						textField_giaBan.setText(""+sp.getGiaBan());
						textArea_moTa.setText(sp.getMoTa());
						comboBox_hangSx.setSelectedItem(sp.getHangSX().getTenHang());
						comboBox_dvt.setSelectedItem(sp.getDvt());
						textField_mauSac.setText(sp.getMauSac());
					}
				}
			}
		});
		btn_tiepTuc.setBounds(456, 350, 89, 23);
		contentPane.add(btn_tiepTuc);
		
		JLabel lbl_MaSp = new JLabel("");
		lbl_MaSp.setBounds(130, 14, 46, 14);
		contentPane.add(lbl_MaSp);
		
		JLabel lblNewLabel = new JLabel("Mã sản phẩm:");
		lblNewLabel.setBounds(27, 14, 89, 14);
		contentPane.add(lblNewLabel);
	}
}
