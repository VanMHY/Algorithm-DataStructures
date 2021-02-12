package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	private ArrayList<String> vertexList;// 存储顶点集合
	private int[][] edges;// 存储图对应的邻接矩阵
	private int numOfEdges;// 表示边的数目
	//定义一个数组boolean[]，记录是否被访问
	private boolean[] isVisited;
	

	public static void main(String[] args) {
		// 测试
		int n = 5;
		String Vertexs[] = { "A", "B", "C", "D", "E" };
		//创建图对象
		Graph graph = new Graph(n);
		//循环的添加顶点
		for(String vertex : Vertexs) {
			graph.insertVertex(vertex);
		}
		//添加边
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		//显示邻接矩阵
		graph.showGraph();
		//测试dfs遍历
//		System.out.println("深度遍历");
//		graph.dfs();
		//广度优先
		System.out.println("广度优先");
		graph.bfs();
	}

	// 构造器
	public Graph(int n) {
		// 邻接矩阵和vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;// 默认为0，不写也为0
		//isVisited = new boolean[5];//使广度优先和深度优先同时有效到方法里面去初始化
	}
	//得到邻接节点的下标 w
	/**
	 * 
	 * @param index
	 * @return 如果存在，就返回对应的下标，否则返回-1
	 */
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (edges[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	//根据前一个邻接节点的下标来获取下一个邻接节点
	public int getNextNeighbor(int v1,int v2) {
		for(int j = v2 + 1; j< vertexList.size();j++) {
			if(edges[v1][j]>0) {
				return j;
			}
		}
		return -1;
	}
	//深度优先遍历算法
	private void dfs(boolean[] isVisited, int i) {
		//首先访问该节点,输出
		System.out.print(getvalueByIndex(i) + "->");
		//将节点设置成已经访问
		isVisited[i] = true;
		int w = getFirstNeighbor(i);
		while(w != -1) {//说明有
			if(!isVisited[w]) {
				dfs(isVisited, w);
			}
			//如果w节点已经被访问过
			w = getNextNeighbor(i, w);
		}
	}
	//对dfs进行重载,遍历所有的节点；并进行dfs
	public void dfs() {
		isVisited = new boolean[vertexList.size()];
		//遍历所有的节点，进行dfs[回溯]
		for(int i =0 ; i < getNumOfVertes(); i ++ ) {
			if(!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	// 对一个节点进行广度优先遍历的方法
	private void bfs(boolean[] isVisited, int i) {
		int u;//队列 的头结点下标
		int w;//邻接结点
		//队列，记录结点访问的顺序
		LinkedList queue = new LinkedList();
		//访问这个节点,输出节点信息
		System.out.print(getvalueByIndex(i) + "=>");
		//标记已访问
		isVisited[i] = true;
		//将节点加入队列
		queue.addLast(i);
		while( !queue.isEmpty()) {
			//取出队列的头节点下标
			u = (Integer)queue.removeFirst();
			//得到第一个邻接矩阵的下标w
			w = getFirstNeighbor(u);
			while(w != -1) {//找到
				//是否访问过
				if(!isVisited[w]) {
					System.out.print(getvalueByIndex(w) + "=>");
					//标记已访问
					isVisited[w] = true;
					//入队
					queue.addLast(w);
				}
				//以u为前驱点，找w后面的下一个邻接点
				w = getNextNeighbor(u, w);//体现出广度优先
				
			}
		}
	}
	//遍历所有的节点，都进行广度优先
	public void bfs() {
		isVisited = new boolean[vertexList.size()];
		for(int i = 0; i < getNumOfVertes(); i++) {
			if(!isVisited[i]) {
				bfs(isVisited,i);
			}
		}
	}
	// 图中常用的方法
	// 返回图的顶点个数
	public int getNumOfVertes() {
		return vertexList.size();
	}

	// 显示图的矩阵
	public void showGraph() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}

	// 得到边的数目
	public int getNumOfEdges() {
		return numOfEdges;
	}

	// 返回节点i对应的值
	public String getvalueByIndex(int i) {
		return vertexList.get(i);
	}

	// 返回v1v2权值
	public int getWight(int v1, int v2) {
		return edges[v1][v2];
	}

	// 插入节点
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	// 添加边
	/**
	 * 
	 * @param v1     表示点的下标即第几个顶点
	 * @param v2     表示第二个对应下标
	 * @param weight 表示权值
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
}
