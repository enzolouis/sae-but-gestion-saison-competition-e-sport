package DAOs;

public class SingletonDAO {

	private static SingletonDAO instance;
	
	protected SingletonDAO() {
		
	}
	
	public static synchronized SingletonDAO getInstance() {
		if (instance == null) {
			instance = new SingletonDAO();
		}
		return instance;
	}
}
