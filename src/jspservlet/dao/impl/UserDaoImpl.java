package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspservlet.dao.UserDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.User;

public class UserDaoImpl implements UserDao {

	@Override
	public int queryByUsername(User user) throws Exception {
		
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
            	
            	if(rs.getString("u_Name").equals(user.getUsername())){
            		flag = 2;
            		System.out.println(rs.getString("password"));
            		if(rs.getString("password").equals(user.getPassword())){
                    	flag = 1;
                    	break;
                    }
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
