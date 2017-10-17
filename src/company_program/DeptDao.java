package company_program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import company_program.DBCon;
import company_program.JdbcUtil;
import company_program.Department;

public class DeptDao {
	private static final DeptDao instance = new DeptDao();

	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;

	static List<Department> lists;
	Manager manager = new Manager();

	private DeptDao() {
	}

	public static DeptDao getInstance() {
		return instance;
	}

	public static Object[][] showDepartment() {
		List<Department> li = instance.selectDepartmentByAll();
		Object[][] datas = new Object[li.size()][];
		int i = 0;
		for (Department d : li) {
			Object[] arr = new Object[3];
			Object[] dept = d.toArry();
			System.arraycopy(dept, 0, arr, 0, dept.length);
			datas[i] = arr;
			i++;
		}
		return datas;

	}

	public Department searchDept(Department dept) {
		Department department = null;
		sql = "select * from department where deptno=?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				department = new Department(rs.getInt("deptno"), rs.getString("deptname"), rs.getInt("floor"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return department;
	}

	public List<Department> selectDepartmentByAll() {
		lists = new ArrayList<>();

		sql = "select deptno, deptname, floor from department";
		con = DBCon.getInstance().getConn();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int deptNo = rs.getInt("deptno");
				String deptName = rs.getString("deptname");
				int floor = rs.getInt("floor");
				lists.add(new Department(deptNo, deptName, floor));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return lists;
	}

	public void addDept(Department dept) {
		sql = "insert into department values(?,?,?)";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptNo());
			pstmt.setString(2, dept.getDeptName());
			pstmt.setInt(3, dept.getFloor());

			int res = pstmt.executeUpdate();
			if (res < 0) {
				System.out.println("삽입 실패");
				return;
			}
			JOptionPane.showMessageDialog(null, dept + "추가하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void updateDept(Department dept) {
		sql = "update department set deptName=?, floor=? where deptno=?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setString(1, dept.getDeptName());
			pstmt.setInt(2, dept.getFloor());
			pstmt.setInt(3, dept.getDeptNo());

			int res = pstmt.executeUpdate();
			if (res < 0) {
				System.out.println("수정 실패");
				return;
			}
			JOptionPane.showMessageDialog(null, dept + "를 수성하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void deleteDept(Department deldept) {
		sql = "delete from department where deptno=?";
		try {
			pstmt = DBCon.getInstance().getConn().prepareStatement(sql);
			pstmt.setInt(1, deldept.getDeptNo());
			int res = pstmt.executeUpdate();
			if (res < 0) {
				System.out.println("삭제 실패");
				return;
			}
			JOptionPane.showMessageDialog(null, deldept.getDeptNo() + "번 부서를 삭제하였습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

}
