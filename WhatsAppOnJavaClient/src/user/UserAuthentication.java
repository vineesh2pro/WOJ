package user;

public class UserAuthentication 
{
	UserFunctions uf;
	public UserAuthentication(UserFunctions uf)
	{
		this.uf = uf;
	}
	public UserDetails checkLoginInfo(String number)
	{ 
		for(UserDetails ud: uf.getAllUsers())
		{
			if(number.equals(ud.getNumber()))
			{
				return ud;
			}				
		}
	return null;
	}
}
