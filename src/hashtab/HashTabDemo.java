package hashtab;

import java.util.Scanner;

public class HashTabDemo {

	public static void main(String[] args) {
		//创建哈希表
		HashTab hashTab = new HashTab(7);
		//写一个菜单
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("add:添加雇员");
			System.out.println("list:显示雇员");
			System.out.println("find:查找雇员");
			System.out.println("exit:退出系统");
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("输入id");
				int id = scanner.nextInt();
				System.out.println("输入名字");
				String name = scanner.next();
				//创建雇员
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "find":
				System.out.println("请输入要查找的id");
				id = scanner.nextInt();
				hashTab.findEmpById(id);
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			default:
				break;
			}
		}
	}

}
//创建HashTab 管理多条链表
class HashTab{
	private EmpLinkedList[] empLinkedListArray;
	private int size;//表示有多少条链表
	//构造器
	public HashTab(int size) {
		this.size = size;
		//初始化empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		//必须初始化每个链表
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
	}
	//添加雇员
	public void add(Emp emp) {
		//根据员工id
		int empLinkedListNO = hashFun(emp.id);
		//将emp加入到对应的链表中
		empLinkedListArray[empLinkedListNO].add(emp);
	}
	//遍历所有链表，遍历hashtal
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}
	//根据输入的id，查找雇员
	public void findEmpById(int id) {
		//使用散列方法确定到哪个链表查找
		int empLinkedListNO = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if(emp != null) {
			System.out.printf("在第%d条链表中找到雇员id = %d\n", (empLinkedListNO+1), id);
		}else {
			System.out.println("在哈希表中，没有找到该雇员~~");
		}
	}
	//编写散列方法，取模法
	public int hashFun(int id) {
		return id % size;
	}
}
//表示一个雇员
class Emp{
	public int id;
	public String name;
	public Emp next;//默认为null
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
//创建EmpLinkedList，表示链表
class EmpLinkedList{
	//头指针，指向第一个Emp，所有这个链表的head是指向第一个Emp
	private Emp head;//默认为null
	//添加雇员到链表
	//1.假设：id从小到大分配，所有把雇员直接加入到链表最后即可
	public void add(Emp emp) {
		//如果是第一个雇员
		if(head == null) {
			head = emp;
			return;
		}
		//如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
		Emp curEmp = head;
		while(true) {
			if(curEmp.next == null) {//说明到最后
				break;
			}
			curEmp = curEmp.next;//后移
		}
		//退出时直接将emp加入链表
		curEmp.next = emp;
	}
	//遍历雇员信息
	public void list(int no) {
		if (head == null) {// 链表为空
			System.out.println("第" + (no + 1) + "条链表为空");
			return;
		}
		System.out.println("第" + (no + 1) + "条链表信息为");
		Emp curEmp = head;// 辅助指针
		while (true) {
			System.out.printf("=>id=%d name=%s\t", curEmp.id, curEmp.name);
			if (curEmp.next == null) {// curEmp是最后结点
				break;
			}
			curEmp = curEmp.next;// 后移，遍历
		}
		System.out.println();
	}
	//根据id查找雇员
	public Emp findEmpById(int id) {
		//判断链表是否为空
		if(head == null) {
			System.out.println("链表为空");
			return null;
		}
		Emp curEmp = head;//辅助指针
		while(true) {
			if(curEmp.id == id) {//找到
				break;//这是curEmp就指向要查找的雇员
			}
			//退出
			if(curEmp.next == null) {//说明遍历链表没有找到该雇员
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;//后移
		}
		return curEmp;
	}
}
