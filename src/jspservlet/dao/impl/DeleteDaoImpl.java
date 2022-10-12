package jspservlet.dao.impl;

import java.sql.PreparedStatement;

import jspservlet.dao.DeleteDao;
import jspservlet.db.DBConnect;

public class DeleteDaoImpl implements DeleteDao {

	public int DeleteDevice(int ID) {
		// TODO Auto-generated method stub
		
		
			String sql1 = "update devices set d_delete = 1 where d_ID="+ID+"";
	        PreparedStatement pstmt1 = null ;  
	        DBConnect dbc = null;
	        
	        int flag=0;
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
