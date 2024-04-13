package josephus;

import java.util.Scanner;

public class Josephus {
	int total;// 총인원수
	Node tail;// 마지막 노드
	Node start;// 시작노드
	
	public Josephus(){ // 생성자
		tail=new Node(0);//더미헤더생성
		tail.next=tail;//원형 순열임을 제시
		start=tail;//remove 시작위치
	}
	
	public void create(int n) { //n까지의 순열 생성
		Node prevNode=tail;//prevnode는 더미헤더, 즉 0번째이다
		for(int i=1; i<=n; i++) {// 1부터 n까지 노드 생성
			Node newNode=new Node(i,null);//노드생성
			newNode.next=prevNode.next;//newNode.next가 더미헤더를 가리키도록 업데이트
			prevNode.next=newNode;//이전 노드와 세노드를 연결
			prevNode=newNode;//이전 노드를 다음노드로 업데이트
		}
		tail=prevNode;//반복문의 끝났으면 prevnode가 마지막 노드이므로 tail로 업데이트
		total=n;// 인원수 저장
	}
	public void list(int k){
		int array[]=new int[total];
		int items=total;
		for(int i= 0;i<total;i++) {
			array[i]=remove(k);
			testPrint(--items);
		}
	}
	
	public int remove(int k) {
		Node prevNode=start;
		Node currentNode=start.next;
		for(int i=1; i<=k; i++) {
			currentNode=prevNode.next;
			prevNode=currentNode;
		}
		prevNode.next=currentNode.next;
		System.out.println(start.next.number);
		start=prevNode;
		return currentNode.number;
	}
	public Node getNode(int index) {
		Node currentNode=tail;
			if(index>=0 && index<=total) {
				for(int i = 0; i<=index; i++) {
					currentNode = currentNode.next;
		        }
		        return currentNode;
		    }
		    return null;
	}
	
	
	public void testPrint(int n) {//순열 출력
		Node currentNode =tail.next;//tail.next는 0번쨰 더미헤드,currentNode에 0번째 넣음
		System.out.print("현재 순열 : ");
		for(int i=1; i<=n; i++)//출력
		{
			currentNode=currentNode.next;
			System.out.print(currentNode.number+ " ");
		}
	}

	
	public static void main(String args[])
	{
		Josephus result= new Josephus();
		int n,k;
		Scanner a= new Scanner(System.in);
		System.out.print("N과 K 값을 입력하세요: ");
		n=a.nextInt();
		k=a.nextInt();
		result.create(n);//순열 생성
		result.testPrint(n);//순열이 제대로 생성됫는지 임시확인
		result.list(k);
		a.close();
		
	}
}
