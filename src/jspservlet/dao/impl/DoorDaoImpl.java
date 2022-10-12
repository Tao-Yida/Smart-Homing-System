package jspservlet.dao.impl;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jspservlet.dao.DoorDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.*;

public class DoorDaoImpl implements DoorDao {

	@Override
	public int controlDoor(String d_state, int ID) {
		int flag=0;
		String sql1 = "update doors set d_state='"+d_state+"' where Devices_d_ID="+ID+"";
        PreparedStatement pstmt1 = null ;  
        DBConnect dbc = null;
        
        try {
        	 dbc = new DBConnect() ;
        	 pstmt1 = dbc.getConnection().prepareStatement(sql1) ; 
        	 flag = pstmt1.executeUpdate();
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
