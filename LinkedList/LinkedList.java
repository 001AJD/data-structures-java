// implmenting doubly linked list
public class LinkedList<T> 
{
	private Node<T> head = null;
	private Node<T> tail = null;
	private class Node<T>{
		// internal private class for node structure
		T data;
		Node<T> next;
		Node<T> prev;
		public Node(T data, Node<T> next, Node<T> prev){
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
}