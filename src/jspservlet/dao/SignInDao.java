package jspservlet.dao;

import jspservlet.vo.User;

public interface SignInDao {
	//public int SignIn(User user) throws Exception;
	
	public int Insert(User user,String newusername,String newpassword,String newemail, String newphone,String familyname)throws Exception; 
}
