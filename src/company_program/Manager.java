package company_program;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Manager extends JFrame implements ActionListener {

	private JPanel contentPane;

	SelectList chk;
	DeptDao deptdao = DeptDao.getInstance();
	public static final String[] COL_NAMES = { "부서번호", "부서명", "위치" };
	private JScrollPane scrollPane;
	private JPanel panel;
	private JComboBox comboBox;
	private JLabel lblDeptNo;
	private JTextField tfDeptNo;
	private JLabel lblDeptName;
	private JTextField tfDeptName;
	private JLabel lblDeptFloor;
	private JTextField tfDeptFloor;
	private JPanel button;
	private JButton btnOk;
	private JButton btnCancel;
	private JTable table;

	public Manager() {

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 200, 800, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		comboBox = new JComboBox();
		comboBox.addActionListener(this);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "선택하세요", "추가", "수정", "삭제", "검색", "전체보기" }));
		panel.add(comboBox);

		lblDeptNo = new JLabel("부서번호");
		panel.add(lblDeptNo);

		tfDeptNo = new JTextField();
		tfDeptNo.setColumns(10);
		panel.add(tfDeptNo);

		lblDeptName = new JLabel("부서명");
		panel.add(lblDeptName);

		tfDeptName = new JTextField();
		tfDeptName.setColumns(10);
		panel.add(tfDeptName);

		lblDeptFloor = new JLabel("위치");
		panel.add(lblDeptFloor);

		tfDeptFloor = new JTextField();
		tfDeptFloor.setColumns(10);
		panel.add(tfDeptFloor);

		button = new JPanel();
		panel.add(button);
		button.setLayout(new GridLayout(0, 1, 0, 0));

		btnOk = new JButton("확인");
		btnOk.addActionListener(this);
		button.add(btnOk);

		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		button.add(btnCancel);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox) {
			do_comboBox_actionPerformed(e);
		}
		if (e.getSource() == btnOk) {
			do_btnOk_actionPerformed(e);
		}
		if (e.getSource() == btnCancel) {
			do_btnCancel_actionPerformed(e);
		}
	}

	private void do_btnOk_actionPerformed(ActionEvent e) {
		if (btnOk.getText().equals("추가")) {
			Department dept = new Department(Integer.parseInt(tfDeptNo.getText()), tfDeptName.getText(),
					Integer.parseInt(tfDeptFloor.getText()));
			deptdao.addDept(dept);
			do_btnCancel_actionPerformed(e);
			table.setModel(new DefaultTableModel(DeptDao.showDepartment(), COL_NAMES));
			scrollPane.setViewportView(table);
		}
		if (btnOk.getText().equals("수정")) {
			Department dept = new Department(Integer.parseInt(tfDeptNo.getText()), tfDeptName.getText(),
					Integer.parseInt(tfDeptFloor.getText()));
			deptdao.updateDept(dept);
			do_btnCancel_actionPerformed(e);
			table.setModel(new DefaultTableModel(DeptDao.showDepartment(), COL_NAMES));
			scrollPane.setViewportView(table);
		}
		if (btnOk.getText().equals("삭제")) {
			Department deldept = new Department(Integer.parseInt(tfDeptNo.getText()));
			deptdao.deleteDept(deldept);
			do_btnCancel_actionPerformed(e);
			table.setModel(new DefaultTableModel(DeptDao.showDepartment(), COL_NAMES));
			scrollPane.setViewportView(table);
		}
		if (btnOk.getText().equals("검색")) {
			Department dept = new Department(Integer.parseInt(tfDeptNo.getText()));
			deptdao.searchDept(dept);
			do_btnCancel_actionPerformed(e);

		}
	}

	private void do_btnCancel_actionPerformed(ActionEvent e) {
		tfDeptNo.setText("");
		tfDeptName.setText("");
		tfDeptFloor.setText("");
	}

	protected void do_comboBox_actionPerformed(ActionEvent e) {
		if (comboBox.getSelectedItem().equals("추가")) {
			btnOk.setText("추가");
			tfDeptName.setEnabled(true);
			tfDeptFloor.setEnabled(true);
		}
		if (comboBox.getSelectedItem().equals("수정")) {
			btnOk.setText("수정");
			tfDeptName.setEnabled(true);
			tfDeptFloor.setEnabled(true);
		}
		if (comboBox.getSelectedItem().equals("삭제")) {
			btnOk.setText("삭제");
			tfDeptName.setEnabled(false);
			tfDeptFloor.setEnabled(false);
		}
		if (comboBox.getSelectedItem().equals("검색")) {
			btnOk.setText("검색");
			tfDeptName.setEnabled(false);
			tfDeptFloor.setEnabled(false);
		}
		if (comboBox.getSelectedItem().equals("전체보기")) {
			table.setModel(new DefaultTableModel(DeptDao.showDepartment(), COL_NAMES));
			scrollPane.setViewportView(table);

		}

	}
}
