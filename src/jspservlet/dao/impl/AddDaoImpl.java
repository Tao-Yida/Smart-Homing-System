package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspservlet.dao.AddDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.Door;
import jspservlet.vo.Equipment;
import jspservlet.vo.Light;
import jspservlet.vo.User;
import jspservlet.vo.Window;

public class AddDaoImpl implements AddDao{

	public int AddDevice(String type,Light l, Door d, Window w, String newname, String information, int Manufacturer_AllUsers_u_ID, int Family_f_ID)throws Exception
	{
		User user1 = new User();
		
		int flag = 0;
		
		String sql="select * from Devices";
		PreparedStatement pstmt1 = null ;
		DBConnect dbc = null; 
		
		try {   
            // 连接数据库   
            dbc = new DBConnect() ;   
            pstmt1 = dbc.getConnection().prepareStatement(sql) ; 
            ResultSet rs1 = pstmt1.executeQuery();
           
            
            ArrayList<Integer> list_f_ID1 = new ArrayList<Integer>();
            	 ArrayList<Integer> list_M_ID1 = new ArrayList<Integer>();
            	 list_f_ID1.add(Manufacturer_AllUsers_u_ID);
            	 list_M_ID1.add(Family_f_ID);
            
            
            
            /*pstmt1.setString(1,newusername) ; */
           
           
            // 进行数据库查询操作   
          
            
           // System.out.println(user.getUsername());
           
            int count=1;
            while(rs1.next()) {
            	//..............
            	 ArrayList<Integer> list_f_ID = new ArrayList<Integer>();
            	 ArrayList<Integer> list_M_ID = new ArrayList<Integer>();
            	 list_f_ID.add(rs1.getInt("Manufacturer_AllUsers_u_ID"));
            	 list_M_ID.add(rs1.getInt("Family_f_ID"));
            
            	 
            	
            	
            	for(int i = 0;i<=list_f_ID.size()-1;i++)
            	{	
            		
            	if(list_f_ID1.get(0)==list_f_ID.get(i)) {
            		flag=2;
            	}
            	
            	if(list_M_ID1.get(0)==list_M_ID.get(i)) {
            		flag=2;
            	}
            	
            	
            	count++;
            }}
            if(flag==2) {
            	int flag2=0;
            	
            	PreparedStatement st = dbc.getConnection().prepareStatement("insert into Devices values (?,?,?,?,?,?)");
        		
        		
        		
       		 st.setInt(1, count);
       		 st.setString(2, newname);
       		 st.setString(3, information);
       		 st.setInt(4,  Manufacturer_AllUsers_u_ID);
       		 st.setInt(5, Family_f_ID);
       		 st.setInt(6, 0);
       		 
       		 int result=st.executeUpdate();
            	
            		if(type.equals("Light")){
            			PreparedStatement st1 = dbc.getConnection().prepareStatement("insert into Lights values (?,?,?)");
            			st1.setInt(3, count);
            			st1.setString(2,"off");
            			st1.setInt(1,0);
            			System.out.println(count);
            			int result1=st1.executeUpdate();
            			if(result1==1) flag2=1;
            			}
            		if(type.equals("Door")){
            			PreparedStatement st1 = dbc.getConnection().prepareStatement("insert into Doors values (?,?)");
            			st1.setInt(2, count);
            			st1.setString(1,"close");
            			int result1=st1.executeUpdate();
            			if(result1==1) flag2=1;
            			}
            		if(type.equals("Window")){
            			PreparedStatement st1 = dbc.getConnection().prepareStatement("insert into Windows values (?,?)");
            			st1.setInt(2, count);
            			st1.setString(1,"close");
            			int result1=st1.executeUpdate();
            			if(result1==1) flag2=1;
            			}
            	
            		flag = 1;//成功注册
            		
            		
            		 //int result1=st1.executeUpdate();
            		           if(result==1&&flag2==1)
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
        }
        catch (SQLException e) {   
            System.out.println(e.getMessage());   
            e.printStackTrace();
        }
        finally{   
            // 关闭数据库连接   
            dbc.close() ;   
        }   
		
		return flag;
	}

}

	