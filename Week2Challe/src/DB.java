public class DB {
	//CONSTRUCTOR
	public DB() {
		super();
	}
	public DB(int id, String fname, String lname, int did, double salary, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.did = did;
		this.salary = salary;
		this.email = email;
	}
	//STATE
	private int id;
	private String fname;
	private String lname;
	private int did;
	private double salary;
	private String email;
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//OVERRIDE BEHAVIOR
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + fname + " " + lname + ", department id= " + did + ", salary= " + salary + ", email= " + email + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DB other = (DB) obj;
		if (id != other.id)
			return false;
		if (id != other.id)
			return false;
		if (fname == null || lname == null) {
			if (other.fname != null || other.lname != null)
				return false;
		} else if (!(fname.equals(other.fname) || lname.equals(other.lname)))
			return false;
		return true;
	}

}