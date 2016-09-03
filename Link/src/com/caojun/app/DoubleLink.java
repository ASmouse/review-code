package com.caojun.app;

public class DoubleLink {
	public class Node{
		Node prev;	//��һ���ڵ�ĵ�ַ
		Object data;
		Node next;	//��һ���ڵ�ĵ�ַ
	}
	
	private Node head;//ָ��ͷ���
	private Node rear;//ָ��β�ڵ�
	
	/**�������  β�����
	 * @param data
	 */
	public void add(Object data){
		
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
	
	}
	
	public Node find(Object data){
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
	public void remove(Object data){
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
			
		}
	}
	
	/**�������Ƿ����data
	 * @param data
	 * @return
	 */
	public boolean contains(Object data){
		Node node = find(data);
		
		return node != null;
	}
	/**����
	 * @param oldData
	 * @param newdata
	 */
	public void update(Object oldData , Object newdata){
		//1.���ҵ����������ڵĽڵ�
		Node node = find(oldData);
		if(node != null){
			//2.�ѽڵ�data����ָ��newData
			node.data = newdata;
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
	
	
}
