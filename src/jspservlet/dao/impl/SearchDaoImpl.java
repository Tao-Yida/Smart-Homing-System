package jspservlet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspservlet.dao.SearchDao;
import jspservlet.db.DBConnect;
import jspservlet.vo.Door;
import jspservlet.vo.Equipment;
import jspservlet.vo.Light;
import jspservlet.vo.User;
import jspservlet.vo.Window;

public class SearchDaoImpl implements SearchDao {

	@Override
	public ArrayList<Equipment> searchEquipment(User user, String name) {
		ArrayList<Equipment> equ = new ArrayList<Equipment>();
		String sql = "select * from allusers a, family f, users u, devices d where a.u_ID=u.AllUsers_u_ID and f.f_ID=u.Family_f_ID and d.Family_f_ID=f.f_ID and d.d_delete=0 and d.name LIKE '%"+ name +"%' and a.u_Name='"+user.getUsername()+"' order by u.Family_f_ID,d.d_ID";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;  
        
        try {
        	dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            //pstmt.setString(1,user.getUsername()) ;   
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Integer> list_d_ID = new ArrayList<Integer>();
            ArrayList<String> list_type = new ArrayList<String>();
            ArrayList<Integer> list_f_ID = new ArrayList<Integer>();
            ArrayList<String> list_name = new ArrayList<String>();
            ArrayList<String> list_information = new ArrayList<String>();
            ArrayList<String> list_f_Name = new ArrayList<String>();
            ArrayList<Integer> list_humidity = new ArrayList<Integer>();
            ArrayList<Integer> list_temperature = new ArrayList<Integer>();
            ArrayList<Integer> list_family = new ArrayList<Integer>();
            int max=0;
            while(rs.next()) {
            	
            	if(rs.getInt("d_delete")==1) continue;
            	
            	list_d_ID.add(rs.getInt("d_ID")); if(rs.getInt("d_ID")>max) max=rs.getInt("d_ID");
            	list_f_ID.add(rs.getInt("f_ID"));
            	if(!list_family.contains(rs.getInt("f_ID"))) list_family.add(rs.getInt("f_ID"));
            	list_name.add(rs.getString("name"));
            	list_information.add(rs.getString("information"));
            	list_f_Name.add(rs.getString("f_Name"));
            	list_humidity.add(rs.getInt("humidity"));
            	list_temperature.add(rs.getInt("temperature"));
            	System.out.println(rs.getInt("d_ID"));
            	/*if(type.equals("A"))
            	{
            		PreparedStatement find = dbc.getConnection().prepareStatement("select Aid,checkpoint from A where eid=" + eid);
            		ResultSet rs1 = find.executeQuery();
            		A e = new A();
            		e.setEid(rs1.getInt("Aid"));
            		e.setCheck(rs1.getInt("checkpoint"));
            		equ.add(e);
            	}*/
            }
            int count=0;
            int min=0;
            for(int i=0;i<list_family.size();i++)
            {
            	int x=list_family.get(i);
            	int count2=0;
            	String[] list_t = new String[1000];
            	PreparedStatement search = dbc.getConnection().prepareStatement("SELECT d.d_ID, 'light' AS type FROM devices d, lights l, family f WHERE d.d_ID = l.Devices_d_ID and f.f_ID = d.Family_f_ID and d.name LIKE '%"+name+"%' and d.d_delete=0 and f.f_ID = "+x+" UNION ALL SELECT d.d_ID, 'door' AS type FROM devices d, doors dr, family f WHERE d.d_ID = dr.Devices_d_ID and f.f_ID = d.Family_f_ID and d.name LIKE '%"+name+"%' and d.d_delete=0 and f.f_ID = "+x+" UNION ALL SELECT d.d_ID, 'window' AS type FROM devices d, windows w, family f WHERE d.d_ID = w.Devices_d_ID and f.f_ID = d.Family_f_ID and d.name LIKE '%"+name+"%' and d.d_delete=0 and f.f_ID = "+x+"");
            	rs = search.executeQuery();
            	while(rs.next())
            	{
            		for(int j=1;j<=max;j++)
            		{
            			if(rs.getInt("d_ID")==j) {
            				list_t[j]=rs.getString("type");
            			}
            		}
            		count2++;
            	}
            	for(int j=count;j<count+count2;j++) {
            		while(list_d_ID.get(j)-min>1) {list_type.add("?");min++;}
        			list_type.add(list_t[list_d_ID.get(j)]);
        			min++;
        		}
            	count=count+count2;
            }
            PreparedStatement find = null;
            ResultSet rss = null;
            int count3=0;
            for(int i=0;i<max;i++)
            {
            	if(list_type.get(i).equals("?")) {System.out.println("skip"+i);continue;}
            	else {
            	if(list_type.get(i).equals("light")) {
            		System.out.println(list_d_ID.get(count3));
            		Light e = new Light();
            		e.setD_ID(list_d_ID.get(count3));
            		e.setD_Name(list_name.get(count3));
            		e.setInformation(list_information.get(count3));
            		e.setF_ID(list_f_ID.get(count3));
            		e.setF_Name(list_f_Name.get(count3));
            		e.setHumidity(list_humidity.get(count3));
            		e.setTemperature(list_temperature.get(count3));
            		find = dbc.getConnection().prepareStatement("select * from lights where Devices_d_ID="+list_d_ID.get(count3)+"");
            		rss = find.executeQuery();
            		if (rss.next()) {
                		System.out.println(rss.getString("l_state"));
                		e.setBright(rss.getInt("bright"));
                		e.setL_state(rss.getString("l_state"));
                		System.out.println(e.getD_Name());
            		}
            		equ.add(e);
            	}
            	else if(list_type.get(i).equals("door")) {
            		System.out.println(list_d_ID.get(count3));
            		Door e = new Door();
            		e.setD_ID(list_d_ID.get(count3));
            		e.setD_Name(list_name.get(count3));
            		e.setInformation(list_information.get(count3));
            		e.setF_ID(list_f_ID.get(count3));
            		e.setF_Name(list_f_Name.get(count3));
            		e.setHumidity(list_humidity.get(count3));
            		e.setTemperature(list_temperature.get(count3));
            		find = dbc.getConnection().prepareStatement("select * from doors where Devices_d_ID="+list_d_ID.get(count3)+"");
            		rss = find.executeQuery();
            		if (rss.next()) {
            			System.out.println(rss.getString("d_state"));
                		e.setD_state(rss.getString("d_state"));
                		System.out.println(e.getD_Name());
            		}
            		equ.add(e);
            	}
            	else if(list_type.get(i).equals("window")) {
            		System.out.println(list_d_ID.get(count3));
            		Window e = new Window();
            		e.setD_ID(list_d_ID.get(count3));
            		e.setD_Name(list_name.get(count3));
            		e.setInformation(list_information.get(count3));
            		e.setF_ID(list_f_ID.get(count3));
            		e.setF_Name(list_f_Name.get(count3));
            		e.setHumidity(list_humidity.get(count3));
            		e.setTemperature(list_temperature.get(count3));
            		find = dbc.getConnection().prepareStatement("select * from windows where Devices_d_ID="+list_d_ID.get(count3)+"");
            		rss = find.executeQuery();
            		if (rss.next()) {
            			System.out.println(rss.getString("w_state"));
                		e.setW_state(rss.getString("w_state"));
                		System.out.println(e.getD_Name());
            		}
            		equ.add(e);
            	}
            	System.out.println("1time");
            	count3++;
            }
            }
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        finally {
        	dbc.close();
        	System.out.println("?????");
        }
        
		
		return equ;
	}
	@Override
	public ArrayList<Equipment> searchEquipmentAlter(User user, String type) {
		ArrayList<Equipment> equ = new ArrayList<Equipment>();
		String sql = "select * from allusers a, family f, users u, devices d where a.u_ID=u.AllUsers_u_ID and f.f_ID=u.Family_f_ID and d.Family_f_ID=f.f_ID and a.u_Name='"+user.getUsername()+"' order by u.Family_f_ID,d.d_ID";
        PreparedStatement pstmt = null ;   
        DBConnect dbc = null;  
        
        try {
        	dbc = new DBConnect() ;   
            pstmt = dbc.getConnection().prepareStatement(sql) ; 
            //pstmt.setString(1,user.getUsername()) ;   
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Integer> list_d_ID = new ArrayList<Integer>();
            ArrayList<String> list_type = new ArrayList<String>();
            ArrayList<Integer> list_f_ID = new ArrayList<Integer>();
            ArrayList<String> list_name = new ArrayList<String>();
            ArrayList<String> list_information = new ArrayList<String>();
            ArrayList<String> list_f_Name = new ArrayList<String>();
            ArrayList<Integer> list_humidity = new ArrayList<Integer>();
            ArrayList<Integer> list_temperature = new ArrayList<Integer>();
            ArrayList<Integer> list_family = new ArrayList<Integer>();
            int max=0;
            while(rs.next()) {
            	
            	if(rs.getInt("d_delete")==1) continue;
            	
            	list_d_ID.add(rs.getInt("d_ID")); if(rs.getInt("d_ID")>max) max=rs.getInt("d_ID");
            	list_f_ID.add(rs.getInt("f_ID"));
            	if(!list_family.contains(rs.getInt("f_ID"))) list_family.add(rs.getInt("f_ID"));
            	list_name.add(rs.getString("name"));
            	list_information.add(rs.getString("information"));
            	list_f_Name.add(rs.getString("f_Name"));
            	list_humidity.add(rs.getInt("humidity"));
            	list_temperature.add(rs.getInt("temperature"));
            	System.out.println(rs.getInt("d_ID"));
            	/*if(type.equals("A"))
            	{
            		PreparedStatement find = dbc.getConnection().prepareStatement("select Aid,checkpoint from A where eid=" + eid);
            		ResultSet rs1 = find.executeQuery();
            		A e = new A();
            		e.setEid(rs1.getInt("Aid"));
            		e.setCheck(rs1.getInt("checkpoint"));
            		equ.add(e);
            	}*/
            }
            int count=0;
            int min=0;
            for(int i=0;i<list_family.size();i++)
            {
            	int x=list_family.get(i);
            	int count2=0;
            	String[] list_t = new String[1000];
            	PreparedStatement search = dbc.getConnection().prepareStatement("SELECT d.d_ID, 'light' AS type FROM devices d, lights l, family f WHERE d.d_ID = l.Devices_d_ID and f.f_ID = d.Family_f_ID and d.d_delete=0 and f.f_ID = "+x+" UNION ALL SELECT d.d_ID, 'door' AS type FROM devices d, doors dr, family f WHERE d.d_ID = dr.Devices_d_ID and f.f_ID = d.Family_f_ID and d.d_delete=0 and f.f_ID = "+x+" UNION ALL SELECT d.d_ID, 'window' AS type FROM devices d, windows w, family f WHERE d.d_ID = w.Devices_d_ID and f.f_ID = d.Family_f_ID and d.d_delete=0 and f.f_ID = "+x+"");
            	rs = search.executeQuery();
            	while(rs.next())
            	{
            		for(int j=1;j<=max;j++)
            		{
            			if(rs.getInt("d_ID")==j) {
            				list_t[j]=rs.getString("type");
            			}
            		}
            		count2++;
            	}
            	for(int j=count;j<count+count2;j++) {
            		while(list_d_ID.get(j)-min>1) {list_type.add("?");min++;}
        			list_type.add(list_t[list_d_ID.get(j)]);
        			min++;
        		}
            	count=count+count2;
            }
            PreparedStatement find = null;
            ResultSet rss = null;
            int count3=0;
            for(int i=0;i<max;i++)
            {
            	if(list_type.get(i).equals("?")) {System.out.println("skip"+i);continue;}
            	else {
            	if(list_type.get(i).equals("light")&&type.equals("Light")) {
            		System.out.println(list_d_ID.get(count3));
            		Light e = new Light();
            		e.setD_ID(list_d_ID.get(count3));
            		e.setD_Name(list_name.get(count3));
            		e.setInformation(list_information.get(count3));
            		e.setF_ID(list_f_ID.get(count3));
            		e.setF_Name(list_f_Name.get(count3));
            		e.setHumidity(list_humidity.get(count3));
            		e.setTemperature(list_temperature.get(count3));
            		find = dbc.getConnection().prepareStatement("select * from lights where Devices_d_ID="+list_d_ID.get(count3)+"");
            		rss = find.executeQuery();
            		if (rss.next()) {
                		System.out.println(rss.getString("l_state"));
                		e.setBright(rss.getInt("bright"));
                		e.setL_state(rss.getString("l_state"));
                		System.out.println(e.getD_Name());
            		}
            		equ.add(e);
            	}
            	else if(list_type.get(i).equals("door")&&type.equals("Door")) {
            		System.out.println(list_d_ID.get(count3));
            		Door e = new Door();
            		e.setD_ID(list_d_ID.get(count3));
            		e.setD_Name(list_name.get(count3));
            		e.setInformation(list_information.get(count3));
            		e.setF_ID(list_f_ID.get(count3));
            		e.setF_Name(list_f_Name.get(count3));
            		e.setHumidity(list_humidity.get(count3));
            		e.setTemperature(list_temperature.get(count3));
            		find = dbc.getConnection().prepareStatement("select * from doors where Devices_d_ID="+list_d_ID.get(count3)+"");
            		rss = find.executeQuery();
            		if (rss.next()) {
            			System.out.println(rss.getString("d_state"));
                		e.setD_state(rss.getString("d_state"));
                		System.out.println(e.getD_Name());
            		}
            		equ.add(e);
            	}
            	else if(list_type.get(i).equals("window")&&type.equals("Window")) {
            		System.out.println(list_d_ID.get(count3));
            		Window e = new Window();
            		e.setD_ID(list_d_ID.get(count3));
            		e.setD_Name(list_name.get(count3));
            		e.setInformation(list_information.get(count3));
            		e.setF_ID(list_f_ID.get(count3));
            		e.setF_Name(list_f_Name.get(count3));
            		e.setHumidity(list_humidity.get(count3));
            		e.setTemperature(list_temperature.get(count3));
            		find = dbc.getConnection().prepareStatement("select * from windows where Devices_d_ID="+list_d_ID.get(count3)+"");
            		rss = find.executeQuery();
            		if (rss.next()) {
            			System.out.println(rss.getString("w_state"));
                		e.setW_state(rss.getString("w_state"));
                		System.out.println(e.getD_Name());
            		}
            		equ.add(e);
            	}
            	System.out.println("1time");
            	count3++;
            }
            }
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        finally {
        	dbc.close();
        	System.out.println("?????");
        }
        
		
		return equ;
	}
}
