package jspservlet.dao.impl;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspservlet.dao.LightDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.*;

public class LightDaoImpl implements LightDao {

	@Override
	public int controlLight(String bright, String l_state, int ID) {
		
		int flag=0;
		String sql1 = "update lights set bright="+bright+" where Devices_d_ID="+ID+"";
		String sql2 = "update lights set l_state='"+l_state+"' where Devices_d_ID="+ID+"";
        PreparedStatement pstmt1 = null ;  
        PreparedStatement pstmt2 = null ; 
        DBConnect dbc = null;
        
        try {
        	 dbc = new DBConnect() ;
        	 pstmt1 = dbc.getConnection().prepareStatement(sql1) ; 
        	 pstmt2= dbc.getConnection().prepareStatement(sql2) ;
        	 flag = pstmt1.executeUpdate();
        	 flag = pstmt2.executeUpdate();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		finally {
			dbc.close() ; 
		}
        
		return flag;
	}

}
