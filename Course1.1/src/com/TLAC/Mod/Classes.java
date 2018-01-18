package com.TLAC.Mod;
/**
 * 班级类；
 * @author MISS
 *
 */
public class Classes {
	
	private String classId;	//班级编号
	private int classDay;	//指定课时
	
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
