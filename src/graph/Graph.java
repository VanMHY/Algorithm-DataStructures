package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
	private ArrayList<String> vertexList;// �洢���㼯��
	private int[][] edges;// �洢ͼ��Ӧ���ڽӾ���
	private int numOfEdges;// ��ʾ�ߵ���Ŀ
	//����һ������boolean[]����¼�Ƿ񱻷���
	private boolean[] isVisited;
	

	public static void main(String[] args) {
		// ����
		int n = 5;
		String Vertexs[] = { "A", "B", "C", "D", "E" };
		//����ͼ����
		Graph graph = new Graph(n);
		//ѭ������Ӷ���
		for(String vertex : Vertexs) {
			graph.insertVertex(vertex);
		}
		//��ӱ�
		graph.insertEdge(0, 1, 1);
		graph.insertEdge(0, 2, 1);
		graph.insertEdge(1, 2, 1);
		graph.insertEdge(1, 3, 1);
		graph.insertEdge(1, 4, 1);
		//��ʾ�ڽӾ���
		graph.showGraph();
		//����dfs����
//		System.out.println("��ȱ���");
//		graph.dfs();
		//�������
		System.out.println("�������");
		graph.bfs();
	}

	// ������
	public Graph(int n) {
		// �ڽӾ����vertexList
		edges = new int[n][n];
		vertexList = new ArrayList<String>(n);
		numOfEdges = 0;// Ĭ��Ϊ0����дҲΪ0
		//isVisited = new boolean[5];//ʹ������Ⱥ��������ͬʱ��Ч����������ȥ��ʼ��
	}
	//�õ��ڽӽڵ���±� w
	/**
	 * 
	 * @param index
	 * @return ������ڣ��ͷ��ض�Ӧ���±꣬���򷵻�-1
	 */
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (edges[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}
	//����ǰһ���ڽӽڵ���±�����ȡ��һ���ڽӽڵ�
	public int getNextNeighbor(int v1,int v2) {
		for(int j = v2 + 1; j< vertexList.size();j++) {
			if(edges[v1][j]>0) {
				return j;
			}
		}
		return -1;
	}
	//������ȱ����㷨
	private void dfs(boolean[] isVisited, int i) {
		//���ȷ��ʸýڵ�,���
		System.out.print(getvalueByIndex(i) + "->");
		//���ڵ����ó��Ѿ�����
		isVisited[i] = true;
		int w = getFirstNeighbor(i);
		while(w != -1) {//˵����
			if(!isVisited[w]) {
				dfs(isVisited, w);
			}
			//���w�ڵ��Ѿ������ʹ�
			w = getNextNeighbor(i, w);
		}
	}
	//��dfs��������,�������еĽڵ㣻������dfs
	public void dfs() {
		isVisited = new boolean[vertexList.size()];
		//�������еĽڵ㣬����dfs[����]
		for(int i =0 ; i < getNumOfVertes(); i ++ ) {
			if(!isVisited[i]) {
				dfs(isVisited, i);
			}
		}
	}
	// ��һ���ڵ���й�����ȱ����ķ���
	private void bfs(boolean[] isVisited, int i) {
		int u;//���� ��ͷ����±�
		int w;//�ڽӽ��
		//���У���¼�����ʵ�˳��
		LinkedList queue = new LinkedList();
		//��������ڵ�,����ڵ���Ϣ
		System.out.print(getvalueByIndex(i) + "=>");
		//����ѷ���
		isVisited[i] = true;
		//���ڵ�������
		queue.addLast(i);
		while( !queue.isEmpty()) {
			//ȡ�����е�ͷ�ڵ��±�
			u = (Integer)queue.removeFirst();
			//�õ���һ���ڽӾ�����±�w
			w = getFirstNeighbor(u);
			while(w != -1) {//�ҵ�
				//�Ƿ���ʹ�
				if(!isVisited[w]) {
					System.out.print(getvalueByIndex(w) + "=>");
					//����ѷ���
					isVisited[w] = true;
					//���
					queue.addLast(w);
				}
				//��uΪǰ���㣬��w�������һ���ڽӵ�
				w = getNextNeighbor(u, w);//���ֳ��������
				
			}
		}
	}
	//�������еĽڵ㣬�����й������
	public void bfs() {
		isVisited = new boolean[vertexList.size()];
		for(int i = 0; i < getNumOfVertes(); i++) {
			if(!isVisited[i]) {
				bfs(isVisited,i);
			}
		}
	}
	// ͼ�г��õķ���
	// ����ͼ�Ķ������
	public int getNumOfVertes() {
		return vertexList.size();
	}

	// ��ʾͼ�ľ���
	public void showGraph() {
		for (int[] link : edges) {
			System.out.println(Arrays.toString(link));
		}
	}

	// �õ��ߵ���Ŀ
	public int getNumOfEdges() {
		return numOfEdges;
	}

	// ���ؽڵ�i��Ӧ��ֵ
	public String getvalueByIndex(int i) {
		return vertexList.get(i);
	}

	// ����v1v2Ȩֵ
	public int getWight(int v1, int v2) {
		return edges[v1][v2];
	}

	// ����ڵ�
	public void insertVertex(String vertex) {
		vertexList.add(vertex);
	}

	// ��ӱ�
	/**
	 * 
	 * @param v1     ��ʾ����±꼴�ڼ�������
	 * @param v2     ��ʾ�ڶ�����Ӧ�±�
	 * @param weight ��ʾȨֵ
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
		numOfEdges++;
	}
}
