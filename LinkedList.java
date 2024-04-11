package LinkedList_01;

public class LinkedList<E> implements LinkedListInterface<E> {
   
   Node<E> head ; // Node 객체의 주소값 head를 생성. 다만 초기화를 안해줬으므로 아무런 주소도 가르키지않는 상태(NULL)
   int numItems; //리스트 개수(리스트 값의 개수)
   
   //주석에서 ->는 주소가 가르킨다는 의미로 사용(c언어의 포인터같은 느낌으로 사용했습니다.)
   

   public LinkedList() { //생성자
      head = new Node<>(null, null);//head(주소)->|NULL(값)||NULL(주소)|, head는 node|NULL||NULL|을 가르킨다.
      numItems =0;//리스트를 막 생성했으므로 리스크 개수는 0개
   }
   

   @Override
   public void add(int index, E x){//index위치에 값(item)이 x인 노드를 추가
       if(index >=0 && index <= numItems) { //index가 유효범위인 경우
         Node<E> newNode = new Node<>(x, null);//newNode->|x||Null| 값이 x고 주소가 NULL인 노드를 가르키는 newNode(새로운 노드 생성)
         Node<E> prevNode = getNode(index -1);//index 이전 객체의 주소를 가르키는 prevNode(getNode는 바로 아래에 구현)
          
         newNode.next = prevNode.next ;//newNode -> |x||NULL|에서 NULL에 PrevNode -> |item||next|에서의 next를 대입
         prevNode.next = newNode;//prevNode의 next가 새로운 노드의 주소를 가르킴.
         numItems++;//리스트 개수 1증가
         
      }else {//index가 유효범위가 아닐경우 오류 출력
         System.out.println("out of range");
      }
       
   }   
      
    
   public Node<E> getNode(int index){//index위치의 노드를 리턴
      Node<E> currentNode = head; //currentNode가 head노드를 가르킴.
      
      if(index >=-1 && index <= numItems-1) // index가 -1~리스트의마지막 일경우(index가 유효조건일 경우), index가 -1일떄는 head를 가르
      {                            
         for(int i = 0; i<=index ; i++) {  //헤더~index 노드나올떄까지 반복
           currentNode = currentNode.next;//currentNode가 다음 노드를 가르킴
        }
         return currentNode;//currentNode(index번째 노드)반환
      }
      return null;//index가 유효범위가 아닐경우 NULL리턴
      
   }
   
   
   public void printAll() {//모든 노드의 item값을 출력
      Node<E> n; //for문에서 사용하기위해 임의의 n 생성 n->NULL
      System.out.print("\n print all elements (#items : "+numItems+" )");//"모든 item값을 출력: (item은  몇개(numItem값을 통해))" 를 출력
   
      for(n = head.next ; n!=null ; n = n.next) {//n은 index0번쨰 node를 가르킨다; 마지막 노드가 나올때까지(NULL이면 마지막이라는 뜻이므로); 하나씩 다음 노드를 가르킨다.
 
         System.out.print(" "+ n.item); //마지막 노드가 나올때까지의 모든 노드의 item값을 출력
      }
   }
   
   @Override
   public void append(E x) { //item값이 x인 노드를 마지막에 추가
      Node<E> prevNode = head;//prevNode가 헤더노드를 가르킴
      Node<E> newNode = new Node<>(x, null);//마지막에 추가될 새로운노드(newNode) 생성(item은 x 주소는 NULL)
      
      
      while(prevNode.next != null) {//마지막 Node의 주소는 NULL이므로 조건문의 뜻은 마지막 노드가 나올떄까지 라는 뜻
          prevNode = prevNode.next;//prevNode는 한칸씩 노드를 가르키다가 마지막노드를 가르키고 반복문을 탈출한다.
      }
      prevNode.next = newNode;//현재 마지막 노드의 next값에 새로운 노드의 주소를 넣어준다(마지막에 노드(newNode) 하나 추가)
      numItems++;//리스트 개수 증가 
      
   }
   
   @Override
   public E remove(int index) {// index위치의 Node를 제거, Node의 item값을 리턴
      
      if(index >=0 && index <=numItems -1) {//index 0~마지막노드 범위 내라면
         
         Node<E> prevNode = getNode(index -1);//prevNode는 삭제할 index위치의 전 노드를 가르킴
         Node<E> currentNode = prevNode.next;//currentNode는 index위치의 노드를 가르킴
         
         prevNode.next= currentNode.next;//index-1번째 노드의 next가 index +1번쨰 노드의 주소를 가르킴.
         //index-1번째 노드의 next는 원래 index의 노드의 주소를 가르켰지만, index+1번째 노드의 주소를 가르키게 하면서 연결을 끊어버렸고 index-1 -> index+1로 되게만듬
         numItems--;//리스트 개수 감소
         
         return  currentNode.item;//제거한 Node의 item 반환
      }else {//index가 유효 범위가 아닐때 오류 출력
         System.out.println(" out of range");
         
      }
      return null;//index가 유효범위가 아닐때 null 리턴
   }

   //@Override
   public boolean removeItem(E x) {//item값이 x인 노드 삭제, 삭제 성공했음 true 못했음 false 리턴
      
      Node<E> prevNode ;//이전 노드를 가르킬 prevNode 생성(NULL)
      Node<E> currentNode  = head; //currentNode는 head를 가르킴
   
       for(int index =0 ; index < numItems ; index++) {//리스트의 처음~끝까지 반복문
          prevNode=currentNode;//prevNode가 currentNode를 가르킨다. 
          					   //currentNode는 head를 가르키므로 head부터 바로 한줄 아래에 있는 코드를 통해 하나씩 이동해서 head부터 마지막까지 검사하게된다.
          
          currentNode=currentNode.next;//다음 노드로 계속 이동
          
            if(((Comparable)(currentNode.item)).compareTo(x) == 0) { //만약 currentNode의 item값이 x와 같다면
                    prevNode.next  = currentNode.next;//remove에서의 코드와 동일 x값과 동일한 node(index)의 전 node(index-1)와 후 node(index+1)를 연결해서 동일한 node를 삭제하는 방식
                    numItems--;// 리스트 감소
                    return true; //삭제 성공 true 리턴
            }
       }   
       return false;//삭제 실패 false 리턴
   } 


   @Override
   public E get(int index) {//index번째의 item값을 반환
      if(index>=0 && index<numItems) {//index가 유효 범위 이내일때
         return getNode(index).item;//getNode(index번째의 주소을 찾는 메소드)의 item값을 리턴
      }
      else{//index가 유효범위가 아닐때 오류 출력, NULL 리턴
         System.out.println("out of range");
         return null;
      }
   }
   
   @Override
   public void set(int index, E x) {//index위치의 node의 item값을 x로 변경
      if(index>=0 && index<numItems) {//index가 유효 범위 내에 있을떄
         getNode(index).item=x;//index위치 노드의 item을 x로 재설정
      }
      else//index의 유효범위가 아닐경우 오류 출력
         System.out.println("out of range");
   }
   
   public final int NOT_FOUND = -12345;//indexOf에서 쓸 상수 NOF)FOUND = -12345 선언
   @Override
   public int indexOf(E x) {//Node의 item이 x인 Node의 index 찾기
      Node<E> currentNode = head;//currentNode는 head를 가르킴
      
      for(int i = 0 ; i<= numItems -1 ; i++) {//리스트 처음~마지막까지 반복
         currentNode = currentNode.next;//currentNode는 다음노드를 순차적으로 가르킴
          if(((Comparable)currentNode.item).compareTo(x) ==0 ){//만약 currentNode의 item값이 x와 같다면
                 return  i ;  //i 반환(i는 index)
          } 
      }
      return NOT_FOUND;//item값이 x인 노드를 못찾았을 경우 위에 선언해뒀던 상수 리턴
   }

   @Override
   public boolean isEmpty() {//리스트가 비었는지 확인
      return  numItems==0;//numItems은 리스트의 개수이므로 개수가 0이라면 true 출력
   }

   @Override
   public void clear() {//리스트 초기화
      // TODO Auto-generated method stub
          
	   numItems =0;//리스트개수를 0으로 재설정
	   head = new Node<>(null, null);//head의 주소값을 NULL 초기화시켜 기존 리스트와의 모든연결을 끊는다.(삭제한다)      
   }
   
    public int len(){//리스트 길이 리턴
       return numItems;//리스트 길이는 리스트 개수와 같으므로 numItems 리턴
   }
}