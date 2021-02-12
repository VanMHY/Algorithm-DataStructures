package linkedlist;

public class DoubleLinkedListDemo {

	public static void main(String[] args) {
		//����
		System.out.println("˫������Ĳ���");
		//�ȴ����ڵ�
		HeroNode2 hero1 = new HeroNode2(1, "�ν�", "��ʱ��" );
		HeroNode2 hero2 = new HeroNode2(2, "¬����", "������" );
		HeroNode2 hero3 = new HeroNode2(3, "����", "�Ƕ���" );
		HeroNode2 hero4 = new HeroNode2(4, "�ֳ�", "����ͷ" );
		//����һ��˫������
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.add(hero1);
		doubleLinkedList.add(hero2);
		doubleLinkedList.add(hero3);
		doubleLinkedList.add(hero4);
		doubleLinkedList.list();
		//�޸�
		HeroNode2 newHeroNode = new HeroNode2(4, "����ʤ", "������");
		doubleLinkedList.update(newHeroNode);
		System.out.println("�޸ĺ���������");
		doubleLinkedList.list();
		//ɾ��
		doubleLinkedList.del(3);
		System.out.println("ɾ������������");
		doubleLinkedList.list();
	}

}
//����һ��˫���������
class DoubleLinkedList{
	//�ȳ�ʼ��ͷ�ڵ㣬ͷ�ڵ㲻Ҫ����	����ž��������
	private HeroNode2 head = new HeroNode2(0, "", "");
	//����ͷ�ڵ�
	public HeroNode2 getHead() {
		return head;
	}
	//����˫������ķ���
	public void list() {
		//�ж������Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		//��Ϊͷ�ڵ㲻�ܶ���ʹ����Ҫһ��������������
		HeroNode2 temp = head.next;
		while(true) {
			//�ж������Ƿ����
			if(temp == null) {
				break;
			}
			//����ڵ���Ϣ
			System.out.println(temp);
			//temp���ƣ���Ȼ��ѭ��
			temp = temp.next;
		}
	}
	//���һ���ڵ㵽˫������
	public void add(HeroNode2 heroNode) {
		//��Ϊhead��㲻�ܶ�����Ҫ��Ҫһ����������temp
		HeroNode2 temp = head;
		//���������ҵ����
		while(true) {
			//�ҵ���������
			if(temp.next == null) {
				break;
			}
			//���û���ҵ����,�ͽ�temp����
			temp = temp.next;
		}
		//���˳�whileѭ��ʱ��temp��ָ�������������γ�һ��˫������
		temp.next = heroNode;
		heroNode.pre = temp;
	}
	//�޸�һ���ڵ�����ݣ�˫������͵��������޸�һ��
	public void update(HeroNode2 newHeroNode) {
		//�ж��Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("����Ϊ��~");
			return;
		}
		//�ҵ���Ҫ�޸ĵĽڵ㣬����no���
		//����һ����������
		HeroNode2 temp = head.next;
		boolean flag = false;//��ʾ�Ƿ��ҵ��ڵ�
		while(true) {
			if(temp == null) {
				break;//�Ѿ�����������
			}
			if(temp.no == newHeroNode.no) {//�ҵ�
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//����flag�ж��Ƿ��ҵ�Ҫ�޸ĵĽڵ�
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		}else {//û���ҵ�
			System.out.printf("û���ҵ����%d�Ľڵ㣬�����޸�\n", newHeroNode.no);
		}
	}
	//��˫��������ɾ��һ���ڵ�
	//����˫������ֱ���ҵ�Ҫɾ���ڵ������ڵ�
	//�ҵ�������ɾ������
	public void del(int no) {
		//�жϵ�ǰ�����Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("����Ϊ�գ��޷�ɾ��");
			return;
		}
		HeroNode2 temp = head.next;//��������
		boolean flag = false;//��־�Ƿ��ҵ���ɾ���ڵ�
		while(true) {
			if(temp == null) {
				break;
			}
			if(temp.no == no) {//�ҵ���ɾ���ڵ��ǰһ���ڵ�temp	
				flag = true;
				break;
			}
			temp = temp.next;//temp����
		}
		//�ж�flag
		if(flag) {//�ҵ�������ɾ��
//			temp.next = temp.next.next;[��������]
			temp.pre.next = temp.next;
			//��������һ���ڵ㣬�ͻ��ָ��
			if(temp.next != null) {
				temp.next.pre = temp.pre;
			}
		}else {
			System.out.printf("Ҫɾ����%d�ڵ㲻����", no);
		}
	}
}
//����һ��HeroNode2��ÿ��HeroNode2�������һ���ڵ�
class HeroNode2{
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;//ָ����һ���ڵ�Ĭ��Ϊnull
	public HeroNode2 pre;//ָ��ǰһ���ڵ�Ĭ��Ϊnull
	//������
	public HeroNode2(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	//Ϊ����ʾ����������toString
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name +", nickname=" + nickname +"]";
	}
}
