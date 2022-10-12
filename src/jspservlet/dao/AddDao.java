package jspservlet.dao;

import jspservlet.vo.Door;
import jspservlet.vo.Light;
import jspservlet.vo.Window;


public interface AddDao {
	
	public int AddDevice(String type, Light l, Door d, Window w, String newname, String information, int Manufacturer_AllUsers_u_ID, int Family_f_ID)throws Exception; 

}
