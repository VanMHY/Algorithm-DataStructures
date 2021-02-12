package linkedlist;

public class SingleLinkedListDemo {

	public static void main(String[] args) {
		//测试，创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨" );
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟" );
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星" );
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头" );
		//创建给链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		//加入
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);
//		singleLinkedList.add(hero4);
		//按编号顺序加入
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero2);
		//显示
				singleLinkedList.list();
		//测试修改节点的代码
		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
		singleLinkedList.update(newHeroNode);
		//显示
		System.out.println("修改后的链表情况~~");
		singleLinkedList.list();
		//删除一个节点
		singleLinkedList.del(1);
		singleLinkedList.del(4);
		singleLinkedList.del(3);
		singleLinkedList.del(2);
		System.out.println("删除后的链表情况~~");
		singleLinkedList.list();
	}

}
//定义SinglelLinkedList管理英雄
class SingleLinkedList{
	//先初始化头节点，头节点不要动，	不存放具体的数据
	private HeroNode head = new HeroNode(0, "", "");
	//添加新的结点到单向链表
	//思路：当不考虑编号顺序时
	//1.找到当前链表的最后结点
	//2.将最后这个结点的next指向新的结点
	public void add(HeroNode heroNode) {
		//因为head结点不能动，需要需要一个辅助遍历temp
		HeroNode temp = head;
		//遍历链表，找到最后
		while(true) {
			//找到链表的最后
			if(temp.next == null) {
				break;
			}
			//如果没有找到最后,就将temp后移
			temp = temp.next;
		}
		//当退出while循环时，temp就指向了链表的最后
		temp.next = heroNode;
	}
	//第二种方式在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）
	public void addByOrder(HeroNode heroNode) {
		//因为头节点不能动，所有仍需要一个辅助指针（变量）来帮助找到添加位置
		//因为单链表，我们找的temp是位于添加位置的前一节点，否则插入不了
		HeroNode temp = head;
		boolean flag = false;//标识添加的编号是否存在，默认为false
		while(true) {
			if(temp.next == null) {//说明temp已经在链表最后
				break;
			}
			if(temp.next.no > heroNode.no) {//位置找到，就这temp的后面插入
				break;
			}else if(temp.next.no == heroNode.no){//说明添加heroNode的编号已经存在
				flag = true;//说明编号已经存在
				break;
			}
			temp = temp.next;//后移，遍历当前链表
		}
		//判断flag的值
		if(flag) {//不能添加，说明编号已经存在
			System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.no);
		}else {//插入到链表中，temp后面
				heroNode.next = temp.next;
				temp.next = heroNode;
		}
	}
	//修改节点信息，根据no编号来修改，即no编号不用改
	//根据newHeroNode的no来修改
	public void update(HeroNode newHeroNode) {
		//判断是否为空
		if(head.next == null) {
			System.out.println("链表为空~");
			return;
		}
		//找到需要修改的节点，根据no编号
		//定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false;//表示是否找到节点
		while(true) {
			if(temp == null) {
				break;//已经遍历完链表
			}
			if(temp.no == newHeroNode.no) {//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//根据flag判断是否找到要修改的节点
		if(flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		}else {//没有找到
			System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
		}
	}
	//删除节点
	//head不能动，所有需要一个temp辅助节点找到待删除节点的前一个节点
	//在比较时，是让temp.next.no和需要删除的节点的no比较
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;//标志是否找到待删除节点
		while(true) {
			if(temp.next == null) {
				break;
			}
			if(temp.next.no == no) {//找到待删除节点的前一个节点temp	
				flag = true;
				break;
			}
			temp = temp.next;//temp后移
		}
		//判断flag
		if(flag) {//找到，可以删除
			temp.next = temp.next.next;
		}else {
			System.out.printf("要删除的%d节点不存在", no);
		}
	}
	//显示链表[遍历]
	public void list() {
		//判断链表是否为空
		if(head.next == null) {
			System.out.println("链表为空");
			return;
		}
		//因为头节点不能动，使用需要一个辅助变量遍历
		HeroNode temp = head.next;
		while(true) {
			//判断链表是否到最后
			if(temp == null) {
				break;
			}
			//输出节点信息
			System.out.println(temp);
			//temp后移，不然死循环
			temp = temp.next;
		}
	}
}
//定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;//指向下一个节点
	//构造器
	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}
	//为了显示方法，重新toString
	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name +", nickname=" + nickname +"]";
	}
	
}
