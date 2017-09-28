package company_program;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SelectList extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDepartment;
	private JButton btnEmployee;

	Manager manager;
	static int n = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectList frame = new SelectList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectList() {
		setTitle("회사관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 322, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		btnEmployee = new JButton("사원관리");
		btnEmployee.addActionListener(this);
		contentPane.add(btnEmployee);

		btnDepartment = new JButton("부서관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmployee) {
			do_btnEmployee_actionPerformed(e);
		}
		if (e.getSource() == btnDepartment) {
			do_btnDepartment_actionPerformed(e);
		}
	}

	protected void do_btnDepartment_actionPerformed(ActionEvent e) {
		n = 2;
		manager = new Manager();
		manager.setVisible(true);
	}

	protected void do_btnEmployee_actionPerformed(ActionEvent e) {
		n = 1;
		manager = new Manager();
		manager.setVisible(true);
	}
}
