package com.TLAC.Test;
import com.TLAC.Mod.*;
import com.TLAC.Rank.*;

import java.util.*;

public class ConsoleTestUI {
	public static void main(String[] args){
		
		//教师 班级参数集合
		 List<Teacher> temp = new ArrayList<Teacher>();
		 
		 //教师一
         Teacher t1 = new Teacher();
         	t1.setTeacherId("001");
         	List<Classes> teachClasses1 = new ArrayList<Classes>();
         		teachClasses1.add(new Classes("A", 7));
         		teachClasses1.add(new Classes("B", 7));
         	t1.setTeachClasses(teachClasses1);
         	
       	 //教师二
            Teacher t2 = new Teacher();
            	t2.setTeacherId("002");
            	List<Classes> teachClasses2 = new ArrayList<Classes>();
            		teachClasses2.add(new Classes("C", 7));
            		teachClasses2.add(new Classes("D", 7));
            	t2.setTeachClasses(teachClasses2);
            	
       	 //教师三
            Teacher t3 = new Teacher();
            	t3.setTeacherId("003");
            	List<Classes> teachClasses3 = new ArrayList<Classes>();
            		teachClasses3.add(new Classes("E", 7));
            		teachClasses3.add(new Classes("F", 7));
            	t3.setTeachClasses(teachClasses3);
                	
         //教师四
	        Teacher t4 = new Teacher();
	        	t4.setTeacherId("004");
	        	List<Classes> teachClasses4 = new ArrayList<Classes>();
	        		teachClasses4.add(new Classes("G", 7));
	        		teachClasses4.add(new Classes("H", 7));
	        	t4.setTeachClasses(teachClasses4);
            	
        //教师四
        Teacher t5 = new Teacher();
        	t5.setTeacherId("005");
        	List<Classes> teachClasses5 = new ArrayList<Classes>();
        		teachClasses5.add(new Classes("J", 7));
        		teachClasses5.add(new Classes("K", 7));
        	t5.setTeachClasses(teachClasses5);
        	
	     temp.add(t1);
         temp.add(t2);
         temp.add(t3);
         temp.add(t4);
         temp.add(t5);
	       
         //教室集合
         List<String> room = new ArrayList<String>();
         room.add("教室一");
         room.add("教室二");
         room.add("教室三");
         room.add("教室四");
         room.add("教室五");
		
		Rank rank = new Rank();
		long startTime = System.currentTimeMillis();	//获取开始时间
		//执行排课
		List<WeekPlan> arrayList = rank.rank(temp, room, 7);
		long endTime = System.currentTimeMillis();	  //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "s");	//输出程序运行时间
		for (WeekPlan weekPlan : arrayList) {
			String reString = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
								weekPlan.getClassRoomId(), weekPlan.getMonday(),weekPlan.getTuesday(),weekPlan.getWednesday(),
								weekPlan.getThursday(),weekPlan.getFriday(),weekPlan.getSaturday(),weekPlan.getSunday() );
			System.out.println(reString);
		}
		System.out.println("结束");
	}
}
