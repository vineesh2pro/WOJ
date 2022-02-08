package Tester;

public class ExtensionTest 
{
	static String fileName = "noob.(2).png";
	
	public static void checkExtension()
	{
		String extension = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
		    extension = fileName.substring(i+1);
		    System.out.println(extension);
		}
	}
	public static void main(String[] args) {
		String s="1 2";
		System.out.println(s.length());
	}

}
