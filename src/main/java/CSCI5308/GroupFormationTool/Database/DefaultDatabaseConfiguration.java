package CSCI5308.GroupFormationTool.Database;

public class DefaultDatabaseConfiguration implements IDatabaseConfiguration
{
	private static final String URL = System.getenv("DB");
	private static final String USER = System.getenv("USER");
	private static final String PASSWORD = System.getenv("PASSWORD");


	public String getDatabaseUserName()
	{
    	System.out.println(USER);
		return USER;
	}

	public String getDatabasePassword()
	{
		System.out.println(PASSWORD);
		return PASSWORD;
	}

	public String getDatabaseURL()
	{
		System.out.println(URL);
		return URL;
	}
}
