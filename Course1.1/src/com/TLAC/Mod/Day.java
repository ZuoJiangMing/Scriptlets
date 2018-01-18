package com.TLAC.Mod;
import java.util.*;
/**
 * 每天课程；
 * @author MISS
 *
 */
public class Day {
	
	private HashMap<String, String> listAM;	//上午课程
	private HashMap<String, String> listPM;	//下午课程
	public HashMap<String, String> getListAM() {
		return listAM;
	}
	public void setListAM(HashMap<String, String> listAM) {
		this.listAM = listAM;
	}
	public HashMap<String, String> getListPM() {
		return listPM;
	}
	public void setListPM(HashMap<String, String> listPM) {
		this.listPM = listPM;
	}
	
	
}
