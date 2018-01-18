package com.TLAC.Mod;
import java.util.*;
/**
 * 教师类；
 * @author MISS
 *
 */
public class Teacher {
	
	private String TeacherId ;   //教师ID
    private List<Classes> TeachClasses;	//所教授的班级
	 
	public String getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}
	public List<Classes> getTeachClasses() {
		return TeachClasses;
	}
	public void setTeachClasses(List<Classes> teachClasses) {
		TeachClasses = teachClasses;
	}
	
}
