package jspservlet.dao;

import java.util.ArrayList;

import jspservlet.vo.Equipment;
import jspservlet.vo.User;

public interface SearchDao {
	public ArrayList<Equipment> searchEquipment(User user, String name);
	public ArrayList<Equipment> searchEquipmentAlter(User user, String type);
}
