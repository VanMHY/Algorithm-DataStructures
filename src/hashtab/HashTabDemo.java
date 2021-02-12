package hashtab;

import java.util.Scanner;

public class HashTabDemo {

	public static void main(String[] args) {
		//������ϣ��
		HashTab hashTab = new HashTab(7);
		//дһ���˵�
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("add:��ӹ�Ա");
			System.out.println("list:��ʾ��Ա");
			System.out.println("find:���ҹ�Ա");
			System.out.println("exit:�˳�ϵͳ");
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("����id");
				int id = scanner.nextInt();
				System.out.println("��������");
				String name = scanner.next();
				//������Ա
				Emp emp = new Emp(id, name);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "find":
				System.out.println("������Ҫ���ҵ�id");
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
//����HashTab �����������
class HashTab{
	private EmpLinkedList[] empLinkedListArray;
	private int size;//��ʾ�ж���������
	//������
	public HashTab(int size) {
		this.size = size;
		//��ʼ��empLinkedListArray
		empLinkedListArray = new EmpLinkedList[size];
		//�����ʼ��ÿ������
		for(int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
	}
	//��ӹ�Ա
	public void add(Emp emp) {
		//����Ա��id
		int empLinkedListNO = hashFun(emp.id);
		//��emp���뵽��Ӧ��������
		empLinkedListArray[empLinkedListNO].add(emp);
	}
	//����������������hashtal
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list(i);
		}
	}
	//���������id�����ҹ�Ա
	public void findEmpById(int id) {
		//ʹ��ɢ�з���ȷ�����ĸ��������
		int empLinkedListNO = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
		if(emp != null) {
			System.out.printf("�ڵ�%d���������ҵ���Աid = %d\n", (empLinkedListNO+1), id);
		}else {
			System.out.println("�ڹ�ϣ���У�û���ҵ��ù�Ա~~");
		}
	}
	//��дɢ�з�����ȡģ��
	public int hashFun(int id) {
		return id % size;
	}
}
//��ʾһ����Ա
class Emp{
	public int id;
	public String name;
	public Emp next;//Ĭ��Ϊnull
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
//����EmpLinkedList����ʾ����
class EmpLinkedList{
	//ͷָ�룬ָ���һ��Emp��������������head��ָ���һ��Emp
	private Emp head;//Ĭ��Ϊnull
	//��ӹ�Ա������
	//1.���裺id��С������䣬���аѹ�Աֱ�Ӽ��뵽������󼴿�
	public void add(Emp emp) {
		//����ǵ�һ����Ա
		if(head == null) {
			head = emp;
			return;
		}
		//������ǵ�һ����Ա����ʹ��һ������ָ�룬������λ�����
		Emp curEmp = head;
		while(true) {
			if(curEmp.next == null) {//˵�������
				break;
			}
			curEmp = curEmp.next;//����
		}
		//�˳�ʱֱ�ӽ�emp��������
		curEmp.next = emp;
	}
	//������Ա��Ϣ
	public void list(int no) {
		if (head == null) {// ����Ϊ��
			System.out.println("��" + (no + 1) + "������Ϊ��");
			return;
		}
		System.out.println("��" + (no + 1) + "��������ϢΪ");
		Emp curEmp = head;// ����ָ��
		while (true) {
			System.out.printf("=>id=%d name=%s\t", curEmp.id, curEmp.name);
			if (curEmp.next == null) {// curEmp�������
				break;
			}
			curEmp = curEmp.next;// ���ƣ�����
		}
		System.out.println();
	}
	//����id���ҹ�Ա
	public Emp findEmpById(int id) {
		//�ж������Ƿ�Ϊ��
		if(head == null) {
			System.out.println("����Ϊ��");
			return null;
		}
		Emp curEmp = head;//����ָ��
		while(true) {
			if(curEmp.id == id) {//�ҵ�
				break;//����curEmp��ָ��Ҫ���ҵĹ�Ա
			}
			//�˳�
			if(curEmp.next == null) {//˵����������û���ҵ��ù�Ա
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;//����
		}
		return curEmp;
	}
}
