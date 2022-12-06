package jframes;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.HangSX;
import model.SanPham;
import service.impl.HangSXService;
import service.impl.SanPhamService;

public class SanPhamFrame extends JFrame {
	List<SanPham> listSp;
	List<HangSX> listHang;
	SanPhamService sanPhamService;
	HangSXService hangSxService;
	private JPanel contentPane;
	private JTable table_SanPham;
	private DefaultTableModel defaultTable;

	/**
	 * Create the frame.
	 */
	public SanPhamFrame() {
		sanPhamService = new SanPhamService();
		hangSxService=new HangSXService();
		listSp = sanPhamService.findAll();
		listHang= hangSxService.findAll();
		setTitle("Sản phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnHome = new JButton("Trang chủ");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		btnHome.setBounds(10, 11, 106, 23);
		contentPane.add(btnHome);

		JButton btnThem = new JButton("Thêm mới");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemSanPhamFrame().setVisible(true);
			}
		});
		btnThem.setBounds(145, 11, 106, 23);
		contentPane.add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_SanPham.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Không có sản phẩm nào được chọn.");
				} else {
					String ma = String.valueOf(table_SanPham.getValueAt(row, 1));
					SanPham sp = sanPhamService.findByMa(ma);
					System.out.println(sp.getHangSX());
					new SuaSanPhamFrame(sp).setVisible(true);
					dispose();
				}
			}
		});
		btnSua.setBounds(280, 11, 89, 23);
		contentPane.add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_SanPham.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Không có sản phẩm nào được chọn.");
				} else {
					int cf = JOptionPane.showConfirmDialog(contentPane, "Bạn chắc chắc muốn xóa?");
					String ma = String.valueOf(table_SanPham.getValueAt(row, 1));
					SanPham sp = sanPhamService.findByMa(ma);
					System.out.println("-------------");
					if (cf == JOptionPane.YES_OPTION) {
						try {
							sanPhamService.deleteOne(sp);
							sp.getHangSX().getListSp().remove(sp);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage());
						}
						defaultTable.setRowCount(0);
						listSp = sanPhamService.findAll();
						setTableData(listSp);
					}
				}
			}
		});
		btnXoa.setBounds(396, 11, 89, 23);
		contentPane.add(btnXoa);

		JButton btnRefresh = new JButton("Làm mới");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable.setRowCount(0);
				listSp = sanPhamService.findAll();
				setTableData(listSp);
			}
		});
		btnRefresh.setBounds(585, 11, 89, 23);
		contentPane.add(btnRefresh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 784, 354);
		contentPane.add(scrollPane);

		table_SanPham = new JTable();
		table_SanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setColumnHeaderView(table_SanPham);
		defaultTable = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã sản phẩm", "Mã hãng",
				"Tên sản phẩm", "Số lượng", "Màu sắc", "Giá bán", "Đơn vị tính", "Mô tả" });
		setTableData(listSp);
		table_SanPham.setModel(defaultTable);
		scrollPane.setViewportView(table_SanPham);
	}

	private void setTableData(List<SanPham> list) {
		int count = 1;
		for (int i = 0; i < list.size(); i++) {
			defaultTable.addRow(new Object[] { count, list.get(i).getMaSp(), list.get(i).getMaHangSX(),
					list.get(i).getTenSP(), list.get(i).getSoLuong(), list.get(i).getMauSac(), list.get(i).getGiaBan(),
					list.get(i).getDvt(), list.get(i).getMoTa() });
			count++;
		}

	}
}
