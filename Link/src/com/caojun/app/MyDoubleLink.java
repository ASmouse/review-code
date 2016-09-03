package com.caojun.app;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyDoubleLink<T> implements Iterable<T>{
	
	//添加堆栈的功能
	public void push(T data){
		add(data);
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public T pop(){
		T data = null;
		//从尾节点开始取
		if(rear == null){
			throw new ConcurrentModificationException();
		}else{
			data = rear.data;
			//防止有重复数据自己写移除算法
			if(rear.prev != null){
				rear= rear.prev;
				rear.next = null;
				
			}else {
				//前面无数据
				rear = null;
				head = null;
			}
		}
		return data;
	}
	
	
	public class Node{
		Node prev;	//上一个节点的地址
		T data;
		Node next;	//下一个节点的地址
	}
	
	private Node head;//指向头结点
	private Node rear;//指向尾节点
	
	private int modeCount = 0;
	
	
	/**添加数据  尾部添加
	 * @param data
	 */
	public void add(T data){
		
		//1.创建节点
		Node node = new Node();
		//2.放数据
		node.data = data;
		//3.连接到链表中
		if(head == null){
			//没有数据
			//链表的第一个节点
			head = node;
			rear = node;
		}else{
			//1.尾部添加
			//原来的尾节点的next属性指向新节点
			rear.next = node;
			//新节点的prev属性指向原来的尾结点
			node.prev = rear;
			//新节点成为新的尾节点
			rear = node;
			
		}
		modeCount++;
	}
	
	public Node find(T data){
		//从头节点开始遍历
		Node temp = head;
		while(temp != null ){
			//判断数据是否一致的标准,equals 和hashcode
			if(temp.data.equals(data) && temp.data.hashCode() == data.hashCode()){
				//找到数据
				break;
			}else{
				//继续查找下一个节点
				temp = temp.next;
			}
		}
		//找到数据所在的节点
		return temp;
	}
	
	/**删除数据
	 * @param data
	 */
	public void remove(T data){
		//删除节点  1.head 2.rear  3.middle  4.head&&rear
		//1.找到数据所在的节点
		Node delete = find(data);
		if(delete != null){
			if(delete ==head && delete == rear){
				//1.head && rear
				head =null;
				rear =null;
			}else if(delete == head){
				//2. head,后面肯定有节点
				head = head.next;//第二个节点成为头结点
				head.prev = null;
				delete.next = null; //delete对象回收时,它的属性也会被回收,所以这个可以省略
				
			}else if(delete == rear){
				//3.rear
				rear = rear.prev;
				rear.next = null;
				
			}else{
				//middle
				//删除节点的前一个节点的next属性指向删除节点的后一个节点
				delete.prev.next =delete.next;
				//删除节点的后一个节点的prev属性指向删除节点的前一个节点
				delete.next.prev = delete.prev;
			}
			
			//
			modeCount++;
		}
	}
	
	/**容器中是否包含data
	 * @param data
	 * @return
	 */
	public boolean contains(T data){
		Node node = find(data);
		
		return node != null;
	}
	/**更新
	 * @param oldData
	 * @param newdata
	 */
	public void update(T oldData , T newdata){
		//1.先找到老数据所在的节点
		Node node = find(oldData);
		if(node != null){
			//2.把节点data属性指向newData
			node.data = newdata;
			modeCount++;
		}
		
	}
	
	public String toString() {
		
		StringBuilder mess = new StringBuilder("[");
		//从头节点遍历所有的数据
		Node temp = head;
		while(temp !=null){
			//判断是不是最后一个节点
			if(temp != rear){
				//不是最后一个节点
				mess.append(temp.data+", ");
			}else{
				//是最后一个节点
				mess.append(temp.data+"]");
			}
			
			temp = temp.next;
		}
		return mess+"";
	}

	

	public Iterator<T> iterator() {
		// 匿名类对象
		return new Iterator<T>(){
			private Node temp = head;	
			private int currentTag = modeCount;
					
			public boolean hasNext() {
				if(temp !=null){
					return true;
				}
				return false;
			}

			public T next() {
				//返回数据
				if(currentTag != modeCount){
					//迭代数据过程中,容器做了增删改操作
					throw new ConcurrentModificationException();
				}
				T data = temp.data;
				temp = temp.next;//temp指向下一个节点
				return data;
			}

			public void remove() {
				// TODO Auto-generated method stub
				
			}};
		
		
		
	}

	
}
