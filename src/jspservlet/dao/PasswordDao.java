package jspservlet.dao;

import jspservlet.vo.User;

public interface PasswordDao {
	public int changePassword(User user, String newPassword) throws Exception;
	
}
