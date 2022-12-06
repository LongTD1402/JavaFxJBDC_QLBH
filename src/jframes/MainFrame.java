package jframes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.impl.GenericDAO;
import dao.impl.HangSXDAO;
import dao.impl.SanPhamDAO;
import model.HangSX;
import model.SanPham;
import service.impl.HangSXService;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		setTitle("Quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 416);
		getContentPane().setLayout(null);

		JButton btnHangSXButton = new JButton("Hãng sản xuất");
		btnHangSXButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HangSXFrame().setVisible(true);
				dispose();
			}
		});
		btnHangSXButton.setBounds(10, 23, 134, 23);
		getContentPane().add(btnHangSXButton);
		
		JButton btn_SanPham = new JButton("Sản phẩm");
		btn_SanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SanPhamFrame().setVisible(true);
				dispose();
			}
		});
		btn_SanPham.setBounds(195, 23, 134, 23);
		getContentPane().add(btn_SanPham);

		// scrollPane1.setViewportView(createTable(proDao));
	}
}
