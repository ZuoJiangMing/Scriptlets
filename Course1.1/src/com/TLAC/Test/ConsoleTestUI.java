package com.TLAC.Test;
import com.TLAC.Mod.*;
import com.TLAC.Rank.*;

import java.util.*;

public class ConsoleTestUI {
	public static void main(String[] args){
		
		//��ʦ �༶��������
		 List<Teacher> temp = new ArrayList<Teacher>();
		 
		 //��ʦһ
         Teacher t1 = new Teacher();
         	t1.setTeacherId("001");
         	List<Classes> teachClasses1 = new ArrayList<Classes>();
         		teachClasses1.add(new Classes("A", 7));
         		teachClasses1.add(new Classes("B", 7));
         	t1.setTeachClasses(teachClasses1);
         	
       	 //��ʦ��
            Teacher t2 = new Teacher();
            	t2.setTeacherId("002");
            	List<Classes> teachClasses2 = new ArrayList<Classes>();
            		teachClasses2.add(new Classes("C", 7));
            		teachClasses2.add(new Classes("D", 7));
            	t2.setTeachClasses(teachClasses2);
            	
       	 //��ʦ��
            Teacher t3 = new Teacher();
            	t3.setTeacherId("003");
            	List<Classes> teachClasses3 = new ArrayList<Classes>();
            		teachClasses3.add(new Classes("E", 7));
            		teachClasses3.add(new Classes("F", 7));
            	t3.setTeachClasses(teachClasses3);
                	
         //��ʦ��
	        Teacher t4 = new Teacher();
	        	t4.setTeacherId("004");
	        	List<Classes> teachClasses4 = new ArrayList<Classes>();
	        		teachClasses4.add(new Classes("G", 7));
	        		teachClasses4.add(new Classes("H", 7));
	        	t4.setTeachClasses(teachClasses4);
            	
        //��ʦ��
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
	       
         //���Ҽ���
         List<String> room = new ArrayList<String>();
         room.add("����һ");
         room.add("���Ҷ�");
         room.add("������");
         room.add("������");
         room.add("������");
		
		Rank rank = new Rank();
		long startTime = System.currentTimeMillis();	//��ȡ��ʼʱ��
		//ִ���ſ�
		List<WeekPlan> arrayList = rank.rank(temp, room, 7);
		long endTime = System.currentTimeMillis();	  //��ȡ����ʱ��
		System.out.println("��������ʱ�䣺" + (endTime - startTime)/1000 + "s");	//�����������ʱ��
		for (WeekPlan weekPlan : arrayList) {
			String reString = String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
								weekPlan.getClassRoomId(), weekPlan.getMonday(),weekPlan.getTuesday(),weekPlan.getWednesday(),
								weekPlan.getThursday(),weekPlan.getFriday(),weekPlan.getSaturday(),weekPlan.getSunday() );
			System.out.println(reString);
		}
		System.out.println("����");
	}
}
