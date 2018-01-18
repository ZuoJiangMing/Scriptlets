package com.TLAC.Rank;

import com.TLAC.Mod.*;
import java.util.*;

public class Rank {

	private Integer[] DayIndex; // 随机散入序列，此序列用于定位天在周集合中的位置
	private List<Day> WeekList = new ArrayList<Day>(); // 周 集合,包含了(上午\下午)*5天
	public List<WeekPlan> weekplanlist; // 整合后的显示信息集合

	// 条件设定
	int Date; // 每周上课天数
	int Day; // 班级一周上课次数

	public List<WeekPlan> rank(List<Teacher> SourceList_Temp,List<String> ClassRooms_Temp, int date) {
		this.Date = date;
	 try{
		// 周集合实例化并开辟空间，便于散入元素
		for (int f = 0; f < Date; f++) {
			Day TempDays = new Day();
			TempDays.setListAM(new HashMap<String, String>());
			TempDays.setListPM(new HashMap<String, String>());
			WeekList.add(TempDays);
		}
		List<String> AmValues = null; // 存储 上午课程 listAM.Values(教师)
		List<String> PmValues = null; // 存储 下午课程 listPM.Values(教师)
		// 排课操作操作开始
		for (int i = 0; i < SourceList_Temp.size(); i++) {
			Teacher teacher = SourceList_Temp.get(i); // 提取教师对象
			// 操作教师教授班级
			for (int j = 0; teacher.getTeachClasses().size() > j;) {
				Classes classes = teacher.getTeachClasses().get(j);
				String classesid = classes.getClassId();
				Day = classes.getClassDay(); // 班级一周上课次数
				// 生成散入序列
				DayIndex = setIndex(Day);
				
				// 将课程散入到周集合
				Boolean Condition1;
				Boolean Condition2;

				for (int x = 0; x < DayIndex.length; x++) {
					Condition1 = false;
					Condition2 = false;
					int index = DayIndex[x].intValue();
					// 获取上下午教师信息.
					// 判断上下午教师是否已经存在
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
				
					// 随机散入 上午或下午
					Random random = new Random();
					// 都不存在
					if (Condition1 == false && Condition2 == false) {
						try {
							// 随机散入
							Thread.sleep(20); // 延迟生成随机数，以解决伪随机问题
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
					// 不存在于上午
					else if (Condition1 == false && Condition2 == true) {
						WeekList.get(index).getListAM().put(classesid,teacher.getTeacherId());
					}
					// 不存在于下午
					else if (Condition2 == false && Condition1 == true) {
						WeekList.get(index).getListPM().put(classesid,teacher.getTeacherId());
					}
					// 都存在，重新计算散入序列
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
		// 排课操作操作结束

		// 拆封操作开始
		weekplanlist = new ArrayList<WeekPlan>(); // 初始化要提交给UI层的显示集合
		List<String> ClassRooms = ClassRooms_Temp; // 接受教室信息集合
		// 以教室数量为参照，为显示集合声明初始空间
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

		// 整合上午课程
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
		// 整合下午课程
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
		// 拆封操作结束
		return weekplanlist;
	  }catch(Exception e){
		  throw e;
	  }
	}

	// 生成随机序列数组
	private Integer[] setIndex(int day) {
		Integer[] dayindex = new Integer[day];
		Random random = new Random();
		// 生成随机散入序列
		for (int k = 0; k < dayindex.length; k++) {
			try {
				Thread.sleep(20);
				int temp = random.nextInt(Date);
				// 判断随机元素是否在数组中已经存在
				int exists = selectElement(dayindex,temp);
				// 如果不存在，则录入随机元素到数组
				if (exists == -1) {
					if (dayindex[k] == null) {
						dayindex[k] = temp;
					} else {
						continue;
					}
				}
				// 如果存在，则本次循环作废，重新开始本次循环
				else {
					k--; // 本次循环作废，条件回撤
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dayindex;
	}
	
	//查找元素
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
