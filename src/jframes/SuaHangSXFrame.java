package jframes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import check_exceptions.EmailInputException;
import check_exceptions.NullFieldException;
import check_exceptions.PhoneNumberException;
import dao.impl.HangSXDAO;
import model.HangSX;
import model.SanPham;
import service.impl.HangSXService;
import service.impl.SanPhamService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SuaHangSXFrame extends JFrame {
	private HangSX hangsx;
	HangSXService hangSXService;
	private JPanel contentPane;
	private JTextField txt_MaHangSX;
	private JTextField txt_TenHang;
	private JTextField txt_DiaChi;
	private JTextField txt_SoDT;
	private JTextField txt_Email;
	private JTextField textField_listSp;

	/**
	 * Create the frame.
	 */
	public SuaHangSXFrame(HangSX hang) {
		hangSXService = new HangSXService();
		hangsx = hang;
		String tx_listSp="Sp:";
		System.out.println(hangsx.getListSp().size());
		for (SanPham sp : hangsx.getListSp()) {
			tx_listSp=tx_listSp+ sp.getTenSP()+"; ";
		}
		System.out.println(tx_listSp);
		setTitle("Sửa Hãng sản xuất");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMaHangSXLabel = new JLabel("Mã hãng sản xuất *:");
		lblMaHangSXLabel.setBounds(36, 26, 109, 14);
		contentPane.add(lblMaHangSXLabel);

		txt_MaHangSX = new JTextField();
		txt_MaHangSX.setEditable(false);
		txt_MaHangSX.setColumns(10);
		txt_MaHangSX.setBounds(155, 23, 86, 20);
		contentPane.add(txt_MaHangSX);

		JLabel lblTenHangSXLabel = new JLabel("Tên hãng *:");
		lblTenHangSXLabel.setBounds(36, 62, 95, 14);
		contentPane.add(lblTenHangSXLabel);

		txt_TenHang = new JTextField();
		txt_TenHang.setColumns(10);
		txt_TenHang.setBounds(155, 59, 184, 20);
		contentPane.add(txt_TenHang);

		txt_DiaChi = new JTextField();
		txt_DiaChi.setColumns(10);
		txt_DiaChi.setBounds(155, 98, 184, 20);
		contentPane.add(txt_DiaChi);

		JLabel lblDiaChiLabel = new JLabel("Địa chỉ *:");
		lblDiaChiLabel.setBounds(36, 101, 95, 14);
		contentPane.add(lblDiaChiLabel);

		JLabel lblSDTLabel = new JLabel("Số điện thoại *:");
		lblSDTLabel.setBounds(36, 141, 95, 14);
		contentPane.add(lblSDTLabel);

		txt_SoDT = new JTextField();
		txt_SoDT.setColumns(10);
		txt_SoDT.setBounds(155, 138, 184, 20);
		contentPane.add(txt_SoDT);

		txt_Email = new JTextField();
		txt_Email.setColumns(10);
		txt_Email.setBounds(155, 174, 184, 20);
		contentPane.add(txt_Email);

		JLabel lblEmailLabel = new JLabel("Email *:");
		lblEmailLabel.setBounds(36, 177, 95, 14);
		contentPane.add(lblEmailLabel);
		
		textField_listSp = new JTextField();
		textField_listSp.setBounds(155, 205, 184, 20);
		contentPane.add(textField_listSp);
		textField_listSp.setColumns(10);
		
		textField_listSp.setText(tx_listSp);
		txt_MaHangSX.setText(hangsx.getMaHangSX());
		txt_TenHang.setText(hangsx.getTenHang());
		txt_DiaChi.setText(hangsx.getDiaChi());
		txt_SoDT.setText(hangsx.getSdt());
		txt_Email.setText(hangsx.getEmail());
		JButton btnButton_Back = new JButton("Trở về");
		btnButton_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HangSXFrame().setVisible(true);
				dispose();
			}
		});
		btnButton_Back.setBounds(152, 227, 89, 23);
		contentPane.add(btnButton_Back);

		JButton btnButton_Continue = new JButton("Tiếp tục");
		btnButton_Continue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hangsx.setTenHang(txt_TenHang.getText());
					hangsx.setDiaChi(txt_DiaChi.getText());
					hangsx.setSdt(txt_SoDT.getText());
					hangsx.setEmail(txt_Email.getText());
					hangSXService.updateOne(hangsx);
					JOptionPane.showMessageDialog(contentPane, "Sửa thành công!");
					new HangSXFrame().setVisible(true);
					dispose();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					if (e2 instanceof NullFieldException) {
						txt_TenHang.setText(hangsx.getTenHang());
						txt_DiaChi.setText(hangsx.getDiaChi());
					}
					if (e2 instanceof PhoneNumberException) {
						txt_SoDT.setText(hangsx.getSdt());
					}
					if (e2 instanceof EmailInputException) {
						txt_Email.setText(hangsx.getEmail());
					}
				}
			}
		});
		btnButton_Continue.setBounds(250, 227, 89, 23);
		contentPane.add(btnButton_Continue);
		
		

	}
}
