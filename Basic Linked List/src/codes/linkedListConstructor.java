package codes;

public class linkedListConstructor {
	
	private nodeConstructor first;
	private nodeConstructor last;
	private int length;
	public void linkedList() {		//creates linked list
		this.first = null;
		this.last = null;
		this.length = 0;
	}
	public void push(int data) {			//adds new node onto end of linked list
		nodeConstructor nodeMaker = new nodeConstructor();
		nodeMaker.node(data);
		if(first == null) {
			first = nodeMaker;
		}
		else {
			last.setNext(nodeMaker);
			nodeMaker.setPrev(last);
		}
		last = nodeMaker;
		length++;
	}
	public void pop() {						//removes end node of the linked list
		nodeConstructor endNode = last;
		if(first == last) {
			first = null;
			last = null;
		}
		else {
			endNode = endNode.getPrev();
			last = endNode;
			endNode.setNext(null);
		}
		System.out.println("End number was popped!");
	}
	public void pop(int data) {				//removes node with specified data in the linked list
		nodeConstructor scanner = first;
		while(scanner != null && scanner.getData() != data) {
			scanner = scanner.getNext();
		}
		if(scanner == null) {
			System.out.println("The number " + data + " is not found and cannot be popped");
		}
		else {
			if(scanner == first) {
				scanner = scanner.getNext();
				first = scanner;
				scanner.setPrev(null);
				System.out.println("The number " + data + " has been popped in location " + scanner);
			}
			else if(scanner == last) {
				scanner = scanner.getPrev();
				last = scanner;
				scanner.setNext(null);
				System.out.println("The number " + data + " has been popped in location " + scanner);
			}
			else {
				nodeConstructor nodePrev = scanner.getPrev();
				nodeConstructor nodeNext = scanner.getNext();
				scanner.setPrev(null);
				scanner.setNext(null);
				nodePrev.setNext(nodeNext);
				nodeNext.setPrev(nodePrev);
				System.out.println("The number " + data + " has been popped in location " + scanner);
			}
		}
	}
	public void display() {			//displays linked list
		nodeConstructor temp = first;
		while(temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getNext();
		}
		System.out.println("Forwards");
		temp = last;                              //goes backwards
		while(temp != null) {
			System.out.print(temp.getData() + " ");
			temp = temp.getPrev();
		}
		System.out.println("Backwards");
	}
	public void find(int data) {	//finds a number in the linked list
		nodeConstructor scanner = first;
		while(scanner != null && scanner.getData() != data) {
			scanner = scanner.getNext();
		}
		if(scanner == null) {
			System.out.println("The number " + data + " is not Found");
		}
		else {
			System.out.println("The number " + data + " is in position " + scanner);
		}
	}
	public void swap(int data1, int data2) {				//swaps two values in the list
		nodeConstructor scanner1 = first;							//scanner 1 scans for first data point
		while(scanner1 != null && scanner1.getData() != data1) {
			scanner1 = scanner1.getNext();
		}
		if(scanner1 == null) {
			System.out.println("The number " + data1 + " is not found and cannot be swapped");
		}
		else {
			nodeConstructor scanner2 = first;							//scanner2 scans for second data point
			boolean scannersSwitched = true;
			while(scanner2 != null && scanner2.getData() != data2) {
				if(scanner2 == scanner1)
					scannersSwitched = false;
				scanner2 = scanner2.getNext();
			}
			if(scanner2 == null) {
				System.out.println("The number " + data2 + " is not found and cannot be swapped");
			}
			else {
				nodeConstructor temp;
				if(scannersSwitched == true) {						//if scanner for second data point is closer to first than the second, scanner locations swap
					temp = scanner2;
					scanner2 = scanner1;
					scanner1 = temp;
				}
				if(scanner1.getNext() == scanner2) {			//if they are right beside each other
					if(scanner1 == first && scanner2 == last) {				//if scanners are in first and last positions
						first = scanner2;
						scanner2.setPrev(null);
						scanner1.setNext(null);
						last = scanner1;
						scanner2.setNext(scanner1);
						scanner1.setPrev(scanner2);
					}
					else if(scanner1 == first) {								//if a scanner is first node
						first = scanner2;
						nodeConstructor s2Next = scanner2.getNext();
						scanner1.setNext(s2Next);
						scanner1.setPrev(scanner2);
						scanner2.setNext(scanner1);
						scanner2.setPrev(null);
						s2Next.setPrev(scanner1);
					}
					else if(scanner2 == last) {							//if a scanner is last node
						last = scanner1;
						nodeConstructor s1Prev = scanner1.getPrev();
						scanner2.setPrev(s1Prev);
						scanner2.setNext(scanner1);
						scanner1.setPrev(scanner2);
						scanner1.setNext(null);
						s1Prev.setNext(scanner2);
					}
					else {													//all other cases
						nodeConstructor s1Prev = scanner1.getPrev();
						nodeConstructor s2Next = scanner2.getNext();
						s1Prev.setNext(scanner2);
						scanner2.setPrev(s1Prev);
						scanner1.setPrev(scanner2);
						scanner2.setNext(scanner1);
						scanner1.setNext(s2Next);
						s2Next.setPrev(scanner1);
					}
				}
				else if(scanner1.getNext() == scanner2.getPrev()) {				//if they are separated by one other node
					if(scanner1 == first && scanner2 == last) {					//if scanners are in first and last positions
						nodeConstructor s1Next = scanner1.getNext();
						first = scanner2;
						scanner2.setPrev(null);
						scanner1.setNext(null);
						s1Next.setPrev(scanner2);
						s1Next.setNext(scanner1);
						last = scanner1;
						scanner1.setPrev(s1Next);
						scanner2.setNext(s1Next);
						
					}
					else if(first == scanner1) {								//if a scanner is first node
						nodeConstructor s1Next = scanner1.getNext();
						nodeConstructor s2Next = scanner2.getNext();
						first = scanner2;
						scanner2.setPrev(null);
						scanner1.setPrev(s1Next);
						s1Next.setNext(scanner1);
						scanner1.setNext(s2Next);
						s2Next.setPrev(scanner1);
						s1Next.setPrev(scanner2);
						scanner2.setNext(s1Next);
					}
					else if(last == scanner2) {							//if a scanner is last node
						nodeConstructor s1Prev = scanner1.getPrev();
						nodeConstructor s2Prev = scanner2.getPrev();
						last = scanner1;
						scanner1.setNext(null);
						scanner2.setNext(s2Prev);
						s2Prev.setPrev(scanner2);
						scanner2.setPrev(s1Prev);
						s1Prev.setNext(scanner2);
						s2Prev.setNext(scanner1);
						scanner1.setPrev(s2Prev);
					}
					else {											//all other cases
						nodeConstructor s1Prev = scanner1.getPrev();
						nodeConstructor s1Next = scanner1.getNext();
						nodeConstructor s2Next = scanner2.getNext();
						s1Prev.setNext(scanner2);
						scanner2.setPrev(s1Prev);
						scanner1.setPrev(s1Next);
						s1Next.setNext(scanner1);
						scanner1.setNext(s2Next);
						s2Next.setPrev(scanner1);
						s1Next.setPrev(scanner2);
						scanner2.setNext(s1Next);
					}
				}
				else {														//if they are separated by more than one node
					if(scanner1 == first && scanner2 == last) {				//if scanners are in first and last positions
						nodeConstructor s1Next = scanner1.getNext();
						nodeConstructor s2Prev = scanner2.getPrev();
						first = scanner2;
						scanner2.setPrev(null);
						scanner1.setPrev(s2Prev);
						s2Prev.setNext(scanner1);
						scanner1.setNext(null);
						last = scanner1;
						s1Next.setPrev(scanner2);
						scanner2.setNext(s1Next);
					}
					else if(first == scanner1) {								//if a scanner is first node
						nodeConstructor s1Next = scanner1.getNext();
						nodeConstructor s2Prev = scanner2.getPrev();
						nodeConstructor s2Next = scanner2.getNext();
						first = scanner2;
						scanner2.setPrev(null);
						scanner1.setPrev(s2Prev);
						s2Prev.setNext(scanner1);
						scanner1.setNext(s2Next);
						s2Next.setPrev(scanner1);
						s1Next.setPrev(scanner2);
						scanner2.setNext(s1Next);
					}
					else if(last == scanner2) {							//if a scanner is last node
						nodeConstructor s1Prev = scanner1.getPrev();
						nodeConstructor s1Next = scanner1.getNext();
						nodeConstructor s2Prev = scanner2.getPrev();
						last = scanner1;
						scanner1.setNext(null);
						scanner2.setNext(s1Next);
						s1Next.setPrev(scanner2);
						scanner2.setPrev(s1Prev);
						s1Prev.setNext(scanner2);
						s2Prev.setNext(scanner1);
						scanner1.setPrev(s2Prev);
					}	
					else {												//all other cases
						nodeConstructor s1Prev = scanner1.getPrev();
						nodeConstructor s1Next = scanner1.getNext();
						nodeConstructor s2Prev = scanner2.getPrev();
						nodeConstructor s2Next = scanner2.getNext();
						s1Prev.setNext(scanner2);
						scanner2.setPrev(s1Prev);
						scanner1.setPrev(s2Prev);
						s2Prev.setNext(scanner1);
						scanner1.setNext(s2Next);
						s2Next.setPrev(scanner1);
						s1Next.setPrev(scanner2);
						scanner2.setNext(s1Next);
					}
				}
				System.out.println("======" + data1 + " and " + data2 + " have been swapped======");
			}
		}
	}
	
}
