
public class Test {
	public static void main(String[] args) {
		String username = "Mike.Mike";
		String password = "fasifhalskd";
		
		String sql = "SELECT * FROM admin WHERE username = '";
		   sql += username + "' AND userPassword = '";
		   sql += password + "'";
		   
		 System.out.println(sql);

	}
}
