package com.TLAC.Mod;
import java.util.*;
/**
 * ��ʦ�ࣻ
 * @author MISS
 *
 */
public class Teacher {
	
	private String TeacherId ;   //��ʦID
    private List<Classes> TeachClasses;	//�����ڵİ༶
	 
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
