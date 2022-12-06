package jframes;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import check_exceptions.CheckListSP_HangSX;
import model.HangSX;
import model.SanPham;
import service.impl.HangSXService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class HangSXFrame extends JFrame {
	List<HangSX> listHangSX;
	HangSXService hangSxService;
	private JPanel contentPane;
	private JTable tableHangSX;
	private JButton btnThemHangSX;
	private DefaultTableModel defaultTable;
	private JButton btnHome;
	private JButton btnSua;

	public HangSXFrame() {
		hangSxService =new HangSXService();
		listHangSX = hangSxService.findAll();
		for (HangSX hangSX : listHangSX) {
			System.out.println(hangSX.toString());
			for (SanPham sp :hangSX.getListSp()) {
				System.out.print(sp.getTenSP()+" ;");
			}
			System.out.println("\n--------");
		}
		setTitle("Các hãng sản xuất");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPaneHangSX = new JScrollPane();
		scrollPaneHangSX.setBounds(10, 46, 664, 178);
		contentPane.add(scrollPaneHangSX);

		tableHangSX = new JTable();
		tableHangSX.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		defaultTable = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã hãng", "Tên hãng", "Địa chỉ", "SĐT", "Email" });
		setTableData(listHangSX);
		tableHangSX.setModel(defaultTable);
		scrollPaneHangSX.setViewportView(tableHangSX);

		btnThemHangSX = new JButton("Thêm mới");
		btnThemHangSX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemHangSXFrame().setVisible(true);
			}
		});
		btnThemHangSX.setBounds(145, 12, 106, 23);
		contentPane.add(btnThemHangSX);

		btnHome = new JButton("Trang chủ");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		btnHome.setBounds(10, 12, 106, 23);
		contentPane.add(btnHome);

		JButton btnRefresh = new JButton("Làm mới");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defaultTable.setRowCount(0);
				listHangSX = hangSxService.findAll();
				setTableData(listHangSX);
			}
		});
		btnRefresh.setBounds(585, 12, 89, 23);
		contentPane.add(btnRefresh);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableHangSX.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Không có hãng nào được chọn.");
				} else {
					int cf = JOptionPane.showConfirmDialog(contentPane, "Bạn chắc chắc muốn xóa?");
					String ma = String.valueOf(tableHangSX.getValueAt(row, 1));
					HangSX hang=hangSxService.findByMa(ma);
						
					if (cf == JOptionPane.YES_OPTION) {
						try {
							hangSxService.deleteOne(hang);
						} catch (CheckListSP_HangSX e1) {
							JOptionPane.showMessageDialog(contentPane, e1.getMessage());
						}
						defaultTable.setRowCount(0);
						listHangSX = hangSxService.findAll();
						setTableData(listHangSX);
					}
				}
			}
		});
		btnXoa.setBounds(396, 12, 89, 23);
		contentPane.add(btnXoa);

		btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableHangSX.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(contentPane, "Không có hãng nào được chọn.");
				} else {
					String ma = String.valueOf(tableHangSX.getValueAt(row, 1));
					HangSX hang = hangSxService.findByMa(ma);
					System.out.println(hang.toString());
					new SuaHangSXFrame(hang).setVisible(true);
					dispose();
				}
			}
		});
		btnSua.setBounds(280, 12, 89, 23);
		contentPane.add(btnSua);
	}

	public void setTableData(List<HangSX> list) {
		int count = 1;
		for (int i = 0; i < list.size(); i++) {
			defaultTable.addRow(new Object[] { count, list.get(i).getMaHangSX(), list.get(i).getTenHang(),
					list.get(i).getDiaChi(), list.get(i).getSdt(), list.get(i).getEmail() });
			count++;
		}
	}
}
