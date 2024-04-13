package josephus;

public class Node {
	int number;//순서
	Node next;
	
	public Node() {};
	
	public Node(int number){
		this.number=number;
		next=null;
	}
	public Node(int number, Node next) {
		this.number=number;
		this.next=next;
	}

}
