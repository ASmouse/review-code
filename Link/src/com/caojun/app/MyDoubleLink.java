package com.caojun.app;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyDoubleLink<T> implements Iterable<T>{
	
	//��Ӷ�ջ�Ĺ���
	public void push(T data){
		add(data);
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public T pop(){
		T data = null;
		//��β�ڵ㿪ʼȡ
		if(rear == null){
			throw new ConcurrentModificationException();
		}else{
			data = rear.data;
			//��ֹ���ظ������Լ�д�Ƴ��㷨
			if(rear.prev != null){
				rear= rear.prev;
				rear.next = null;
				
			}else {
				//ǰ��������
				rear = null;
				head = null;
			}
		}
		return data;
	}
	
	
	public class Node{
		Node prev;	//��һ���ڵ�ĵ�ַ
		T data;
		Node next;	//��һ���ڵ�ĵ�ַ
	}
	
	private Node head;//ָ��ͷ���
	private Node rear;//ָ��β�ڵ�
	
	private int modeCount = 0;
	
	
	/**�������  β�����
	 * @param data
	 */
	public void add(T data){
		
		//1.�����ڵ�
		Node node = new Node();
		//2.������
		node.data = data;
		//3.���ӵ�������
		if(head == null){
			//û������
			//����ĵ�һ���ڵ�
			head = node;
			rear = node;
		}else{
			//1.β�����
			//ԭ����β�ڵ��next����ָ���½ڵ�
			rear.next = node;
			//�½ڵ��prev����ָ��ԭ����β���
			node.prev = rear;
			//�½ڵ��Ϊ�µ�β�ڵ�
			rear = node;
			
		}
		modeCount++;
	}
	
	public Node find(T data){
		//��ͷ�ڵ㿪ʼ����
		Node temp = head;
		while(temp != null ){
			//�ж������Ƿ�һ�µı�׼,equals ��hashcode
			if(temp.data.equals(data) && temp.data.hashCode() == data.hashCode()){
				//�ҵ�����
				break;
			}else{
				//����������һ���ڵ�
				temp = temp.next;
			}
		}
		//�ҵ��������ڵĽڵ�
		return temp;
	}
	
	/**ɾ������
	 * @param data
	 */
	public void remove(T data){
		//ɾ���ڵ�  1.head 2.rear  3.middle  4.head&&rear
		//1.�ҵ��������ڵĽڵ�
		Node delete = find(data);
		if(delete != null){
			if(delete ==head && delete == rear){
				//1.head && rear
				head =null;
				rear =null;
			}else if(delete == head){
				//2. head,����϶��нڵ�
				head = head.next;//�ڶ����ڵ��Ϊͷ���
				head.prev = null;
				delete.next = null; //delete�������ʱ,��������Ҳ�ᱻ����,�����������ʡ��
				
			}else if(delete == rear){
				//3.rear
				rear = rear.prev;
				rear.next = null;
				
			}else{
				//middle
				//ɾ���ڵ��ǰһ���ڵ��next����ָ��ɾ���ڵ�ĺ�һ���ڵ�
				delete.prev.next =delete.next;
				//ɾ���ڵ�ĺ�һ���ڵ��prev����ָ��ɾ���ڵ��ǰһ���ڵ�
				delete.next.prev = delete.prev;
			}
			
			//
			modeCount++;
		}
	}
	
	/**�������Ƿ����data
	 * @param data
	 * @return
	 */
	public boolean contains(T data){
		Node node = find(data);
		
		return node != null;
	}
	/**����
	 * @param oldData
	 * @param newdata
	 */
	public void update(T oldData , T newdata){
		//1.���ҵ����������ڵĽڵ�
		Node node = find(oldData);
		if(node != null){
			//2.�ѽڵ�data����ָ��newData
			node.data = newdata;
			modeCount++;
		}
		
	}
	
	public String toString() {
		
		StringBuilder mess = new StringBuilder("[");
		//��ͷ�ڵ�������е�����
		Node temp = head;
		while(temp !=null){
			//�ж��ǲ������һ���ڵ�
			if(temp != rear){
				//�������һ���ڵ�
				mess.append(temp.data+", ");
			}else{
				//�����һ���ڵ�
				mess.append(temp.data+"]");
			}
			
			temp = temp.next;
		}
		return mess+"";
	}

	

	public Iterator<T> iterator() {
		// ���������
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
				//��������
				if(currentTag != modeCount){
					//�������ݹ�����,����������ɾ�Ĳ���
					throw new ConcurrentModificationException();
				}
				T data = temp.data;
				temp = temp.next;//tempָ����һ���ڵ�
				return data;
			}

			public void remove() {
				// TODO Auto-generated method stub
				
			}};
		
		
		
	}

	
}
