public class Main
{
	public static void main(String[] args)
	{

		CRUD mySqlOperations = new MySqlOperations();
		mySqlOperations.select();

		User user = new User(3, "bilal","bilal@gmail.com", "M", false);

		System.out.println("\nupdated record:");
		mySqlOperations.select();

	}
}