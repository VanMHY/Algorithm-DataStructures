package tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
		//int[] arr = { 4, 6, 8, 5, 9 };
		int[] arr = new int[8000000];
		for(int i = 0; i < 8000000; i++) {
			arr[i] = (int)(Math.random()*8000000);//����[0~80000���������
		}
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("����ǰ��ʱ��=" + date1Str);
		heapSort(arr);
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("������ʱ��=" + date2Str);
		
	}
	//��дһ��������ķ���
	public static void heapSort(int arr[]) {
		int temp = 0;
		System.out.println("������");
		//��һ�ε���
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("��һ��" + Arrays.toString(arr));
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("�ڶ���" + Arrays.toString(arr));
		//���������й���һ���󶥶�
		for(int i = arr.length/2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		//���Ѷ�Ԫ����ĩβԪ�ؽ����������Ԫ�س�������ĩ��
		//���µ����ṹ��ʹ������Ѷ��壬���������Ѷ�Ԫ����ĩβԪ�أ�����ִ�У�ֱ������������Ч
		for(int j = arr.length - 1; j > 0; j--) {
			//����
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
		//System.out.println("����=" + Arrays.toString(arr));
	}
	
	//��һ�����飨������������Ϊ�󶥶�
	/**
	 * ���ܣ���ɽ���i��Ӧ�ķ�Ҷ�ӽڵ��������Ϊ�󶥶�
	 * @param arr  ������������
	 * @param i ��ʾ��Ҷ�ӽڵ������
	 * @param lenght ��ʾ���ٸ�Ԫ�ص��������𽥼���
	 */
	public static void adjustHeap(int arr[], int i, int lenght) {
		int temp = arr[i];//��ȡ����ǰԪ�أ���������ʱ����
		//��ʼ����
		//k = i * 2 + 1��i����ڵ�����ӽڵ�
		for(int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
			if(k + 1 < lenght && arr[k] < arr[k+1]) {//˵�����ӽڵ��ֵС�����ӽڵ�
				k++;//kָ�����ӽڵ�
			}
			if(arr[k] > temp) {//����ӽڵ���ڸ��ڵ�
				arr[i] = arr[k];//�ѽϴ��ֵ������ǰ�ڵ�
				i = k;//��������i ָ�� k ����ѭ���Ƚ�
			}else {
				break;
			}
		}
		//��forѭ�������󣬾��Ѿ�����iΪ���ڵ���������ֵ�������˶������ֲ���
		arr[i] = temp;//��temp��ֵ���ڵ������λ��
	}
	
}
