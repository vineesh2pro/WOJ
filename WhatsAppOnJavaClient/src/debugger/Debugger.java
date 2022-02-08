package debugger;

public class Debugger
{
	private static Boolean testingEnvironment = false;
	
	public static void enableTestingMode()
	{
		testingEnvironment=true;
	}
	
	public static Boolean testingMode()
	{
		return testingEnvironment;
	}

}
