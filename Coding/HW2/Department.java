public class Department {
    private String deptName;
    private int deptNumber;

    public Department(){}
    public Department(String name, int number){
        this.deptName = name;
        this.deptNumber = number;
    }

    public String getDeptName() {
        return deptName;
    }

    public int getDeptNumber() {
        return deptNumber;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setDeptNumber(int deptNumber) {
        this.deptNumber = deptNumber;
    }

    
}


