package com.TLAC.Mod;
/**
 * �༶�ࣻ
 * @author MISS
 *
 */
public class Classes {
	
	private String classId;	//�༶���
	private int classDay;	//ָ����ʱ
	
	public Classes(String classId,int classDay){
		this.classId = classId;
		this.classDay = classDay;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public int getClassDay() {
		return classDay;
	}

	public void setClassDay(int classDay) {
		this.classDay = classDay;
	}
	
	
}
