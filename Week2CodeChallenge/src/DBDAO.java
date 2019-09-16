import java.util.List;

public interface DBDAO {

	public List<DB> getDBs();
	public DB getDBById(int id);
	public void createDB(DB db);
	public void updateDB(DB db);
	public void deleteDB(DB db);
	public double averageDBDepartment(int id);
	public void raise(int id);
}
