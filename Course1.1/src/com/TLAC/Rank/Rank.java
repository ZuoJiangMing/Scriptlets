package com.TLAC.Rank;

import com.TLAC.Mod.*;
import java.util.*;

public class Rank {

	private Integer[] DayIndex; // ���ɢ�����У����������ڶ�λ�����ܼ����е�λ��
	private List<Day> WeekList = new ArrayList<Day>(); // �� ����,������(����\����)*5��
	public List<WeekPlan> weekplanlist; // ���Ϻ����ʾ��Ϣ����

	// �����趨
	int Date; // ÿ���Ͽ�����
	int Day; // �༶һ���Ͽδ���

	public List<WeekPlan> rank(List<Teacher> SourceList_Temp,List<String> ClassRooms_Temp, int date) {
		this.Date = date;
	 try{
		// �ܼ���ʵ���������ٿռ䣬����ɢ��Ԫ��
		for (int f = 0; f < Date; f++) {
			Day TempDays = new Day();
			TempDays.setListAM(new HashMap<String, String>());
			TempDays.setListPM(new HashMap<String, String>());
			WeekList.add(TempDays);
		}
		List<String> AmValues = null; // �洢 ����γ� listAM.Values(��ʦ)
		List<String> PmValues = null; // �洢 ����γ� listPM.Values(��ʦ)
		// �ſβ���������ʼ
		for (int i = 0; i < SourceList_Temp.size(); i++) {
			Teacher teacher = SourceList_Temp.get(i); // ��ȡ��ʦ����
			// ������ʦ���ڰ༶
			for (int j = 0; teacher.getTeachClasses().size() > j;) {
				Classes classes = teacher.getTeachClasses().get(j);
				String classesid = classes.getClassId();
				Day = classes.getClassDay(); // �༶һ���Ͽδ���
				// ����ɢ������
				DayIndex = setIndex(Day);
				
				// ���γ�ɢ�뵽�ܼ���
				Boolean Condition1;
				Boolean Condition2;

				for (int x = 0; x < DayIndex.length; x++) {
					Condition1 = false;
					Condition2 = false;
					int index = DayIndex[x].intValue();
					// ��ȡ�������ʦ��Ϣ.
					// �ж��������ʦ�Ƿ��Ѿ�����
					if (WeekList.get(index).getListAM() != null) {
						AmValues = new ArrayList<String>(WeekList.get(index).getListAM().values());
						Condition1 = AmValues.contains(teacher.getTeacherId());
					} else {
						Condition1 = false;
					}
					if (WeekList.get(index).getListPM() != null) {
						PmValues = new ArrayList<String>(WeekList.get(index).getListPM().values());
						Condition2 = PmValues.contains(teacher.getTeacherId());
					} else {
						Condition2 = false;
					}
				
					// ���ɢ�� ���������
					Random random = new Random();
					// ��������
					if (Condition1 == false && Condition2 == false) {
						try {
							// ���ɢ��
							Thread.sleep(20); // �ӳ�������������Խ��α�������
							int Condition = random.nextInt(8);
							switch (Condition) {
							case 0:
							case 1:
							case 3:
							case 5:
								WeekList.get(index).getListAM().put(classesid,teacher.getTeacherId());
								break;
							case 2:
							case 4:
							case 6:
							case 7:
								WeekList.get(index).getListPM().put(classesid,teacher.getTeacherId());
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// ������������
					else if (Condition1 == false && Condition2 == true) {
						WeekList.get(index).getListAM().put(classesid,teacher.getTeacherId());
					}
					// ������������
					else if (Condition2 == false && Condition1 == true) {
						WeekList.get(index).getListPM().put(classesid,teacher.getTeacherId());
					}
					// �����ڣ����¼���ɢ������
					else if (Condition1 == true && Condition2 == true) {
						Boolean Condition_Am = true;
						Boolean Condition_Pm = true;
						int T = 0;
						for (; T < Date; T++) {
							Condition_Am = true;
							Condition_Pm = true;
							Condition_Am = WeekList.get(T).getListAM().keySet().contains(classesid);
							Condition_Pm = WeekList.get(T).getListPM().keySet().contains(classesid);
							if (Condition_Am == false && Condition_Pm == false) {
								DayIndex[x] = T;
								T++;
								break;
							} else {
								continue;
							}
						}
						x--;
					}
				}
				teacher.getTeachClasses().remove(j);
			}
		}
		// �ſβ�����������

		// ��������ʼ
		weekplanlist = new ArrayList<WeekPlan>(); // ��ʼ��Ҫ�ύ��UI�����ʾ����
		List<String> ClassRooms = ClassRooms_Temp; // ���ܽ�����Ϣ����
		// �Խ�������Ϊ���գ�Ϊ��ʾ����������ʼ�ռ�
		for (int i = 0; i < ClassRooms.size(); i++) {
			weekplanlist.add(new WeekPlan());
			weekplanlist.get(i).setClassRoomId(ClassRooms.get(i));
		}
		int q = 0;
		for (int w = ClassRooms.size(); w < (ClassRooms.size()) * 2; w++) {
			weekplanlist.add(new WeekPlan());
			weekplanlist.get(w).setClassRoomId(ClassRooms.get(q));
			q++;
		}

		// ��������γ�
		for (int j = 0; j < WeekList.size(); j++) {
			List<String> Am = new ArrayList<String>(WeekList.get(j).getListAM().keySet());
			for (int i = 0; i < Am.size(); i++) {
				switch (j) {
				case 0:
					weekplanlist.get(i).setMonday(Am.get(i));
					break;
				case 1:
					weekplanlist.get(i).setTuesday(Am.get(i));
					break;
				case 2:
					weekplanlist.get(i).setWednesday(Am.get(i));
					break;
				case 3:
					weekplanlist.get(i).setThursday(Am.get(i));
					break;
				case 4:
					weekplanlist.get(i).setFriday(Am.get(i));
					break;
				case 5:
					weekplanlist.get(i).setSaturday(Am.get(i));
					break;
				case 6:
					weekplanlist.get(i).setSunday(Am.get(i));
					break;
				}
			}
		}
		// ��������γ�
		for (int j = 0; j < WeekList.size(); j++) {
			List<String> Pm = new ArrayList<String>(WeekList.get(j).getListPM().keySet());
			int e = ClassRooms.size();
			for (int i = 0; i < Pm.size(); i++) {
				switch (j) {
				case 0:
					weekplanlist.get(e).setMonday(Pm.get(i));
					break;
				case 1:
					weekplanlist.get(e).setTuesday(Pm.get(i));
					break;
				case 2:
					weekplanlist.get(e).setWednesday(Pm.get(i));
					break;
				case 3:
					weekplanlist.get(e).setThursday(Pm.get(i));
					break;
				case 4:
					weekplanlist.get(e).setFriday(Pm.get(i));
					break;
				case 5:
					weekplanlist.get(e).setSaturday(Pm.get(i));
					break;
				case 6:
					weekplanlist.get(e).setSunday(Pm.get(i));
					break;
				}
				e++;
			}
		}
		// ����������
		return weekplanlist;
	  }catch(Exception e){
		  throw e;
	  }
	}

	// ���������������
	private Integer[] setIndex(int day) {
		Integer[] dayindex = new Integer[day];
		Random random = new Random();
		// �������ɢ������
		for (int k = 0; k < dayindex.length; k++) {
			try {
				Thread.sleep(20);
				int temp = random.nextInt(Date);
				// �ж����Ԫ���Ƿ����������Ѿ�����
				int exists = selectElement(dayindex,temp);
				// ��������ڣ���¼�����Ԫ�ص�����
				if (exists == -1) {
					if (dayindex[k] == null) {
						dayindex[k] = temp;
					} else {
						continue;
					}
				}
				// ������ڣ��򱾴�ѭ�����ϣ����¿�ʼ����ѭ��
				else {
					k--; // ����ѭ�����ϣ������س�
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dayindex;
	}
	
	//����Ԫ��
	private int selectElement(Integer[] temp,int Element){
		for (int i = 0; i < temp.length; i++) {
			if(temp[i] != null){
				if(temp[i].intValue() == Element){
					return i;
				}
			}
		}
			return -1;
	} 

}
