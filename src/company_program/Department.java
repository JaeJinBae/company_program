package company_program;

public class Department {
	private int deptNo;
	private String deptName;
	private int floor;

	public Department(int deptNo) {
		this.deptNo = deptNo;
	}

	public Department(int deptNo, String deptName, int floor) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s\n", deptNo, deptName, floor);
	}
	
	
	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Object[] toArry() {
		return new Object[] { deptNo, deptName, floor };
	}
}
