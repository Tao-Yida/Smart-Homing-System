package jspservlet.dao;

import java.util.ArrayList;

import jspservlet.vo.Equipment;
import jspservlet.vo.User;

public interface ShowDao {
	public ArrayList<Equipment> showEquipment(User user);
	
}
