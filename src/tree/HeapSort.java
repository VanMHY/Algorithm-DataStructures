package tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

	public static void main(String[] args) {
		//int[] arr = { 4, 6, 8, 5, 9 };
		int[] arr = new int[8000000];
		for(int i = 0; i < 8000000; i++) {
			arr[i] = (int)(Math.random()*8000000);//生成[0~80000）的随机数
		}
		Date data1 = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1Str = simpleDateFormat.format(data1);
		System.out.println("排序前的时间=" + date1Str);
		heapSort(arr);
		Date data2 = new Date();
		String date2Str = simpleDateFormat.format(data2);
		System.out.println("排序后的时间=" + date2Str);
		
	}
	//编写一个堆排序的方法
	public static void heapSort(int arr[]) {
		int temp = 0;
		System.out.println("堆排序");
		//第一次调整
//		adjustHeap(arr, 1, arr.length);
//		System.out.println("第一次" + Arrays.toString(arr));
//		adjustHeap(arr, 0, arr.length);
//		System.out.println("第二次" + Arrays.toString(arr));
		//将无序序列构成一个大顶堆
		for(int i = arr.length/2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		//将堆顶元素与末尾元素交换，将最大元素沉到数组末端
		//重新调整结构，使其满足堆定义，继续交换堆顶元素与末尾元素，反复执行，直到整个序列有效
		for(int j = arr.length - 1; j > 0; j--) {
			//交换
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
		//System.out.println("数组=" + Arrays.toString(arr));
	}
	
	//将一个数组（二叉树）调整为大顶堆
	/**
	 * 功能：完成将以i对应的非叶子节点的树调整为大顶堆
	 * @param arr  待调整的数组
	 * @param i 表示非叶子节点的索引
	 * @param lenght 表示多少个元素调整，在逐渐减少
	 */
	public static void adjustHeap(int arr[], int i, int lenght) {
		int temp = arr[i];//先取出当前元素，保存在临时变量
		//开始调整
		//k = i * 2 + 1是i这个节点的左子节点
		for(int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
			if(k + 1 < lenght && arr[k] < arr[k+1]) {//说明左子节点的值小于右子节点
				k++;//k指向右子节点
			}
			if(arr[k] > temp) {//如果子节点大于父节点
				arr[i] = arr[k];//把较大的值赋给当前节点
				i = k;//！！！！i 指向 k 继续循环比较
			}else {
				break;
			}
		}
		//当for循环结束后，就已经把以i为父节点的树的最大值，放在了顶部（局部）
		arr[i] = temp;//将temp的值放在调整后的位置
	}
	
}
