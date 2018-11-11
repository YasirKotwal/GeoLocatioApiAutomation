package globalUtilities;

public class Base {

	
	public static String serverName;
	public static String key;
	
	static{
		serverName = System.getProperty("server");
		key = System.getProperty("key");
		
	}
}
