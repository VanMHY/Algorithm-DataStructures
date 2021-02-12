package linkedlist;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//���ԣ������ڵ�
		HeroNode hero1 = new HeroNode(1, "�ν�", "��ʱ��" );
		HeroNode hero2 = new HeroNode(2, "¬����", "������" );
		HeroNode hero3 = new HeroNode(3, "����", "�Ƕ���" );
		HeroNode hero4 = new HeroNode(4, "�ֳ�", "����ͷ" );
		//����������
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		//����
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);
//		singleLinkedList.add(hero4);
		//�����˳�����
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero2);
		//��ʾ
				singleLinkedList.list();
		//�����޸Ľڵ�Ĵ���
		HeroNode newHeroNode = new HeroNode(2, "С¬", "������~~");
		singleLinkedList.update(newHeroNode);
		//��ʾ
		System.out.println("�޸ĺ���������~~");
		singleLinkedList.list();
		//ɾ��һ���ڵ�
		singleLinkedList.del(1);
		singleLinkedList.del(4);
		singleLinkedList.del(3);
		singleLinkedList.del(2);
		System.out.println("ɾ������������~~");
		singleLinkedList.list();
	}

}
//����SinglelLinkedList����Ӣ��
class SingleLinkedList{
	//�ȳ�ʼ��ͷ�ڵ㣬ͷ�ڵ㲻Ҫ����	����ž��������
	private HeroNode head = new HeroNode(0, "", "");
	//����µĽ�㵽��������
	//˼·���������Ǳ��˳��ʱ
	//1.�ҵ���ǰ����������
	//2.������������nextָ���µĽ��
	public void add(HeroNode heroNode) {
		//��Ϊhead��㲻�ܶ�����Ҫ��Ҫһ����������temp
		HeroNode temp = head;
		//���������ҵ����
		while(true) {
			//�ҵ���������
			if(temp.next == null) {
				break;
			}
			//���û���ҵ����,�ͽ�temp����
			temp = temp.next;
		}
		//���˳�whileѭ��ʱ��temp��ָ������������
		temp.next = heroNode;
	}
	//�ڶ��ַ�ʽ�����Ӣ��ʱ������������Ӣ�۲��뵽ָ��λ�ã��������������������ʧ�ܣ���������ʾ��
	public void addByOrder(HeroNode heroNode) {
		//��Ϊͷ�ڵ㲻�ܶ�����������Ҫһ������ָ�루�������������ҵ����λ��
		//��Ϊ�����������ҵ�temp��λ�����λ�õ�ǰһ�ڵ㣬������벻��
		HeroNode temp = head;
		boolean flag = false;//��ʶ��ӵı���Ƿ���ڣ�Ĭ��Ϊfalse
		while(true) {
			if(temp.next == null) {//˵��temp�Ѿ����������
				break;
			}
			if(temp.next.no > heroNode.no) {//λ���ҵ�������temp�ĺ������
				break;
			}else if(temp.next.no == heroNode.no){//˵�����heroNode�ı���Ѿ�����
				flag = true;//˵������Ѿ�����
				break;
			}
			temp = temp.next;//���ƣ�������ǰ����
		}
		//�ж�flag��ֵ
		if(flag) {//������ӣ�˵������Ѿ�����
			System.out.printf("׼�������Ӣ�۵ı��%d�Ѿ����ڣ����ܼ���\n", heroNode.no);
		}else {//���뵽�����У�temp����
				heroNode.next = temp.next;
				temp.next = heroNode;
		}
	}
	//�޸Ľڵ���Ϣ������no������޸ģ���no��Ų��ø�
	//����newHeroNode��no���޸�
	public void update(HeroNode newHeroNode) {
		//�ж��Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("����Ϊ��~");
			return;
		}
		//�ҵ���Ҫ�޸ĵĽڵ㣬����no���
		//����һ����������
		HeroNode temp = head.next;
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
	//ɾ���ڵ�
	//head���ܶ���������Ҫһ��temp�����ڵ��ҵ���ɾ���ڵ��ǰһ���ڵ�
	//�ڱȽ�ʱ������temp.next.no����Ҫɾ���Ľڵ��no�Ƚ�
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;//��־�Ƿ��ҵ���ɾ���ڵ�
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.no == no) {//�ҵ���ɾ���ڵ��ǰһ���ڵ�temp	
				flag = true;
				break;
			}
			temp = temp.next;//temp����
		}
		//�ж�flag
		if(flag) {//�ҵ�������ɾ��
			temp.next = temp.next.next;
		}else {
			System.out.printf("Ҫɾ����%d�ڵ㲻����", no);
		}
	}
	//��ʾ����[����]
	public void list() {
		//�ж������Ƿ�Ϊ��
		if(head.next == null) {
			System.out.println("����Ϊ��");
			return;
		}
		//��Ϊͷ�ڵ㲻�ܶ���ʹ����Ҫһ��������������
		HeroNode temp = head.next;
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
}
//����һ��HeroNode��ÿ��HeroNode�������һ���ڵ�
class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;//ָ����һ���ڵ�
	//������
	public HeroNode(int no, String name, String nickname) {
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
