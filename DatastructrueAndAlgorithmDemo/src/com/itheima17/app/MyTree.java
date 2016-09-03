package com.itheima17.app;

/**
 * @author Administrator
 * @date 2015-12-7
 * @pagename com.itheima17.app
 * @desc 二叉树
 * 
 * @svn author $Author$
 * @svn date $Date$
 * @Id $Id$
 * @version $Rev$
 * @url $URL$
 * 
 */
public class MyTree {

	private class Node {
		Object data;// 数据
		Node parrent;// 父节点
		Node left;// 左儿子
		Node right;// 右儿子
	}

	private Node root;// 树根

	/**
	 * 二叉树中是否包含了data
	 * 
	 * @param data
	 * @return
	 */
	public boolean contains(Object data) {
		boolean exists = false;
		// 从根节点遍历所有节点
		Node node = findNode(data);
		// exists = node != null;
		if (node != null) {
			exists = true;
		}

		return exists;
	}

	private Node findParrent(Object data, Node fromNode) {
		Node parrent = null;
		// 从根节点去找data的父节点
		Node temp = fromNode;
		while (temp != null) {

			// 记录可能是的父节点
			parrent = temp;
			// 有节点
			// 比较大小
			if (compare(data, temp.data)) {
				// true
				// data > temp.data
				// right
				temp = temp.right;
			} else {
				// left
				temp = temp.left;
			}
		}
		return parrent;
	}

	/**
	 * @param data1
	 * @param data2
	 * @return data1 > data2 true data1 < data2 false
	 */
	private boolean compare(Object data1, Object data2) {

		Comparable c1 = null;
		Comparable c2 = null;
		// 判断data 是否实现了比较器
		if (data1 instanceof Comparable) {// data2 同种类型数据
			c1 = (Comparable) data1;
			c2 = (Comparable) data2;
		} else {
			c1 = data1.toString();// String -> Comparable
			c2 = data2.toString();
		}

		if (c1.compareTo(c2) > 0) {
			return true;
		}
		return false;
	}

	public void add(Object data) {

		// 0. data是否存在
		if (contains(data)) {// 数据已经存在
			return;
		}
		// 1. 创建节点
		Node node = new Node();
		// 2. 把数据放到节点中
		node.data = data;
		// 3. 把节点放到二叉树中
		if (root == null) {
			root = node;// 根节点，第一个节点
		} else {
			// 添加子节点
			// 找爹
			Node parrent = findParrent(data, root);
			// 添加
			node.parrent = parrent;// 设置新节点的父节点
			// 在父节点的左右 判断
			if (compare(data, parrent.data)) {
				// data > parrent.data true
				// right
				parrent.right = node;
			} else {
				// data < parrent.data false
				parrent.left = node;
			}
		}

	}

	/**
	 * @param data
	 * @return 数据所在的节点
	 */
	private Node findNode(Object data) {
		Node node = root;// 从根节点遍历
		while (node != null) {
			// 有数据
			// 判断是否一致
			if (node.data.equals(data)
					&& node.data.hashCode() == data.hashCode()) {
				break;// 一致 终止
			} else {

				// 比较大小
				if (compare(data, node.data)) {
					// right
					node = node.right;
				} else {
					// left
					node = node.left;
				}

			}
		}

		return node;
	}

	/**
	 * @param data1 
	 *       old data
	 * @param data2
	 *       new data
	 */
	public void update(Object data1, Object data2) {
		if (contains(data1)) {
			remove(data1);//删除老的数据
			add(data2);//增加的新的数据
		}
	}

	public void remove(Object data) {
		// 查找数据所在的节点
		Node node = findNode(data);

		if (node != null) {
			// 删除的数据存在

			// 1. 根节点
			if (node == root) {

				if (node.left == null && node.right == null) {
					// 1. 没有子节点 （数中只有一个节点 ，删除）
					root = null;// 空
				} else if (node.right == null) {
					// 2. 只有left
					root = root.left;
					root.parrent = null;
				} else if (node.left == null) {
					// 3. 只有right
					root = root.right;
					root.parrent = null;
				} else {
					// 4. left,right都有
					Node left = split(node);
					root = left;
					root.parrent = null;
				}

			} else {// 2. 非根节点(处理父节点)

				if (node.left == null && node.right == null) {
					// 1. 没有子节点
					// 判断删除节点在父节点left还是right
					if (compare(node.data, node.parrent.data)) {
						// true 删除节点在父节点的right
						node.parrent.right = null;
					} else {
						// left
						node.parrent.left = null;
					}
				} else if (node.right == null) {
					// 2. 只有left
					if (compare(node.data, node.parrent.data)) {
						// true 删除节点在父节点的right
						node.parrent.right = node.left;
					} else {
						// left
						node.parrent.left = node.left;
					}

					node.left.parrent = node.parrent;// 指定新父节点
				} else if (node.left == null) {
					// 3. 只有right
					if (compare(node.data, node.parrent.data)) {
						// true 删除节点在父节点的right
						node.parrent.right = node.right;
					} else {
						// left
						node.parrent.left = node.right;
					}

					node.right.parrent = node.parrent;// 指定新父节点
				} else {
					// 4. left,right都有
					Node left = split(node);

					if (compare(node.data, node.parrent.data)) {
						// true 删除节点在父节点的right
						node.parrent.right = left;
					} else {
						// left
						node.parrent.left = left;
					}
					left.parrent = node.parrent;// 指定新父节点
				}
			}
		}
	}

	private Node split(Node node) {
		Node left = node.left;// 保留分裂节点的left，right放到left的最右边
		// 从left开始找 最右边的节点 成right的parrent
		Node parrent = findParrent(node.right.data, left);
		node.right.parrent = parrent;// 设置删除节点right的新父节点
		parrent.right = node.right;
		return left;
	}

	public void print() {
		// 遍历
		see(root);
	}

	private void see(Node node) {
		if (node != null) {
			// 左中右
			see(node.left);
			System.out.println(node.data);
			see(node.right);
		}
	}

}
