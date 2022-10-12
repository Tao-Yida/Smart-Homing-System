package jspservlet.dao.impl;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspservlet.dao.PasswordDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.User;

public class PasswordDaoImpl implements PasswordDao {

	@Override
	public int changePassword(User user, String newPassword) throws Exception {
		int flag = 0;
		
		String sql = "select * from allusers where u_Name=?";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;   
        
        try {   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            pstmt.setString(1,user.getUsername()) ;   
            // 进行数据库查询操作   
            ResultSet rs = pstmt.executeQuery();
            System.out.println(user.getUsername());
            while(rs.next()) {
            	
            	if(rs.getInt("u_delete")==1) continue;
            	
            	if(rs.getString("u_Name").equals(user.getUsername()) && rs.getString("password").equals(user.getPassword())){
            		flag = 2;
            		Statement st = dbc.getConnection().createStatement();
            		String up = "update allusers set password='" + newPassword + "'  where u_Name='" + user.getUsername() + "'";
            		flag = st.executeUpdate(up);
            	}
            }
            rs.close() ; 
            pstmt.close() ;   
        }
        catch (SQLException e) {   
            System.out.println(e.getMessage());   
        }
        finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		
		return flag;
	}

}
