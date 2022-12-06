package jframes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import check_exceptions.EmailInputException;
import check_exceptions.NullFieldException;
import check_exceptions.PhoneNumberException;
import dao.impl.HangSXDAO;
import model.HangSX;
import service.impl.HangSXService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ThemHangSXFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_MaHangSX;
	private JTextField textField_TenHang;
	private JTextField textField_DiaChi;
	private JTextField textField_SDT;
	private JTextField textField_Email;
	HangSX newHang=null;
	HangSXService hangSXService; 

	public ThemHangSXFrame() {
		hangSXService= new HangSXService();
		setTitle("Thêm hãng sản xuất mới");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMaHangSXLabel = new JLabel("Mã hãng sản xuất *:");
		lblMaHangSXLabel.setBounds(28, 11, 109, 14);
		contentPane.add(lblMaHangSXLabel);

		JLabel lblTenHangSXLabel = new JLabel("Tên hãng *:");
		lblTenHangSXLabel.setBounds(28, 47, 95, 14);
		contentPane.add(lblTenHangSXLabel);

		JLabel lblDiaChiLabel = new JLabel("Địa chỉ *:");
		lblDiaChiLabel.setBounds(28, 86, 95, 14);
		contentPane.add(lblDiaChiLabel);

		JLabel lblSDTLabel = new JLabel("Số điện thoại *:");
		lblSDTLabel.setBounds(28, 126, 95, 14);
		contentPane.add(lblSDTLabel);

		JLabel lblEmailLabel = new JLabel("Email *:");
		lblEmailLabel.setBounds(28, 162, 95, 14);
		contentPane.add(lblEmailLabel);

		textField_MaHangSX = new JTextField();
		textField_MaHangSX.setBounds(147, 8, 86, 20);
		contentPane.add(textField_MaHangSX);
		textField_MaHangSX.setColumns(10);

		textField_TenHang = new JTextField();
		textField_TenHang.setBounds(147, 44, 184, 20);
		contentPane.add(textField_TenHang);
		textField_TenHang.setColumns(10);

		textField_DiaChi = new JTextField();
		textField_DiaChi.setBounds(147, 83, 184, 20);
		contentPane.add(textField_DiaChi);
		textField_DiaChi.setColumns(10);

		textField_SDT = new JTextField();
		textField_SDT.setBounds(147, 123, 184, 20);
		contentPane.add(textField_SDT);
		textField_SDT.setColumns(10);

		textField_Email = new JTextField();
		textField_Email.setBounds(147, 159, 184, 20);
		contentPane.add(textField_Email);
		textField_Email.setColumns(10);
		JCheckBox chckbxCheckBox_Auto = new JCheckBox("Tự động");
		chckbxCheckBox_Auto.setSelected(true);
		chckbxCheckBox_Auto.setBounds(268, 7, 97, 23);
		contentPane.add(chckbxCheckBox_Auto);

		JButton btnButton_Continue = new JButton("Tiếp tục");
		btnButton_Continue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newHang=new HangSX();
					newHang.setTenHang(textField_TenHang.getText());
					newHang.setDiaChi(textField_DiaChi.getText());
					newHang.setSdt(textField_SDT.getText());
					newHang.setEmail(textField_Email.getText());
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
					newHang=null;
					HangSX.curId--;
				}
				if (newHang != null) {
					System.out.println(newHang.toString());
					hangSXService.insertNew(newHang);
					JOptionPane.showMessageDialog(contentPane, "Thêm thành công " + newHang.getTenHang());
					dispose();
				} else {
					System.out.println("null");
				}
			}
		});
		btnButton_Continue.setBounds(242, 212, 89, 23);
		contentPane.add(btnButton_Continue);
		textField_MaHangSX.setText("MH"+String.format("%03d", HangSX.curId+1));
		JButton btnButton_Refresh = new JButton("Làm mới");
		btnButton_Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_TenHang.setText(null);
				textField_DiaChi.setText(null);
				textField_SDT.setText(null);
				textField_Email.setText(null);

				if (chckbxCheckBox_Auto.isSelected() != true) {
					textField_MaHangSX.setText(null);
				} else {
					textField_MaHangSX.setText("MH"+String.format("%03d", HangSX.curId+1));
				}
			}
		});
		btnButton_Refresh.setBounds(28, 212, 89, 23);
		contentPane.add(btnButton_Refresh);

		JButton btnButton_Back = new JButton("Trở về");
		btnButton_Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HangSXFrame().setVisible(true);
				dispose();
			}
		});
		btnButton_Back.setBounds(144, 212, 89, 23);
		contentPane.add(btnButton_Back);

	}
}
