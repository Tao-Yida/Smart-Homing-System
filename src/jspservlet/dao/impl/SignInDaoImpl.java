package jspservlet.dao.impl;

import java.sql.*;


import jspservlet.dao.SignInDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.User;

public class SignInDaoImpl implements SignInDao {

	@Override
	public int Insert(User user,String newusername,String newpassword,String newemail,String newphone,String familyname)throws Exception{
		int flag = 0;
		
		String sql="select u_Name from allusers";
		String sql1="select f_ID from family";
		PreparedStatement pstmt1 = null ;
		PreparedStatement pstmt2 = null ;
		DBConnect dbc = null; 
		
		try {   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt1 = dbc.getConnection().prepareStatement(sql) ; 
            
            /*pstmt1.setString(1,newusername) ; */
           
           
            // 进行数据库查询操作   
            ResultSet rs1 = pstmt1.executeQuery();
            
            
           // System.out.println(user.getUsername());
           
            int count=1;
            while(rs1.next()) {
            	if(rs1.getString("u_Name").equals(newusername)) {
            		flag=2;
            	}
            	count++;
            }
            int count_f=1;
            
            pstmt2 = dbc.getConnection().prepareStatement(sql1) ; 
            ResultSet rs2 = pstmt2.executeQuery();
            
            while(rs2.next()) {
            	
            	count_f++;
            }
            
            System.out.println("flag="+flag);
            
            if(flag!=2) {
            	
            		flag = 1;//成功注册
            		
            		PreparedStatement st = dbc.getConnection().prepareStatement("insert into allusers values (?,?,?,?,?,?)");
            		
            		
            		
            		 st.setInt(1, count);
            		 st.setString(2, newusername);
            		 st.setString(3, newpassword);
            		 st.setString(5, newemail);
            		 st.setString(4, newphone);
            		 st.setInt(6, 0);
            		 
            		 int result=st.executeUpdate();
            		 System.out.println(result);
            		 
            		 PreparedStatement st1 = dbc.getConnection().prepareStatement("insert into family values (?,?,?,?,?)");
            		 st1.setInt(1, count_f);
            		 st1.setString(2, familyname);
            		 st1.setInt(3, 0);
            		 st1.setInt(4, 0);
            		 st1.setInt(5, 0);
            		 
            		 int result1=st1.executeUpdate();
            		 System.out.println(result1);
            		 
            		 PreparedStatement st2 = dbc.getConnection().prepareStatement("insert into users values (?,?)");
            		 st2.setInt(1, count_f);
            		 st2.setInt(2, count);
            		 
            		 int result2=st2.executeUpdate();
            		 System.out.println(result2);
            		 
            		 
            		 
            		           if(result==1 && result1==1 && result2==1)
            		             {
            		                 flag = 1;
            		             }
            		            else
            		            { 
            		                  flag =2;
            		            }
            		             
            		          }
            
            
            
            rs1.close() ; 
            pstmt1.close() ;  
            pstmt2.close() ; 
            
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
	/*@Override
	public int SignIn(User user) throws Exception {
		int flag = 0;
		
		
		String sql2="INSERT INTO user values(?,?)";
       
        PreparedStatement pstmt2 = null ;
        DBConnect dbc = null;   
        
        try {   
            // 连接数据库   
            dbc = new DBConnect() ;   
            
            pstmt2 = dbc.getConnection().prepareStatement(sql2) ;
           
           
            pstmt2.setString(1,user.getUsername());
            pstmt2.setString(2,user.getPassword());
            
            // 进行数据库查询操作   
           
            int rs2 = pstmt2.executeUpdate();
            
           if(rs2>0){
        	   flag = 2;
       		Statement st = dbc.getConnection().createStatement();
       		String up = "insert userinfo set password='" +  + "'  where username='" + user.getUsername() + "'";
       		flag = st.executeUpdate(up);
            	}
            
            
            
            pstmt2.close() ;   
        }
        catch (SQLException e) {   
            System.out.println(e.getMessage());   
        }
        finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		
		return flag;
	}*/

	

	

	
}


