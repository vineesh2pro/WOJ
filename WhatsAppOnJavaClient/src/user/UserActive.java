package user;

public class UserActive 
{
	UserDetails userActive;
	
	public void checkUserActive(Object o)
	{
		if(o instanceof UserDetails)
		{
			userActive = (UserDetails)o;
			System.out.println(userActive.getName()+ " is Active Now");
		}
		else
		{
			System.out.println("No Active User");
		}
	}
	
	public void setUserActive(UserDetails userActive)
	{
		this.userActive = userActive;
	}
	
	public UserDetails getUserActive()
	{
		return userActive;
	}
}
