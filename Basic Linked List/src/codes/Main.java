package codes;

public class Main {

	public static void main(String[] args) {
		linkedListConstructor LL = new linkedListConstructor();
		LL.linkedList();
		System.out.println("-----------------------List is made with the numbers 1-7 in it-----------------------");
		System.out.println();
		LL.push(0);
		LL.push(1);
		LL.push(2);
		LL.push(3);
		LL.push(4);
		LL.push(5);
		LL.push(6);
		LL.push(7);
		LL.push(8);
		LL.display();
		System.out.println();
		System.out.println("-------------------Numbers 3, 0, 8 and 232 are searched in the list------------------");
		System.out.println();
		LL.find(3);
		LL.find(0);
		LL.find(8);
		LL.find(232);
		System.out.println();
		System.out.println("--------------------------Numbers 3 is popped from the list--------------------------");
		System.out.println();
		LL.pop(3);
		LL.display();
		LL.pop(0);
		LL.display();
		LL.pop(8);
		LL.display();
		System.out.println();
		System.out.println("-----------------------Various numbers in the list are swapped-----------------------");
		System.out.println();
		LL.swap(1,2);					//each is a specific case
		LL.display();
		LL.swap(1,2);
		LL.display();
		LL.swap(6,7);
		LL.display();
		LL.swap(6,7);
		LL.display();
		LL.swap(4,5);
		LL.display();
		LL.swap(4,5);
		LL.display();
		LL.swap(1,4);
		LL.display();
		LL.swap(1,4);
		LL.display();
		LL.swap(5,7);
		LL.display();
		LL.swap(5,7);
		LL.display();
		LL.swap(2,5);
		LL.display();
		LL.swap(2,5);
		LL.display();
		LL.swap(1,5);
		LL.display();
		LL.swap(1,5);
		LL.display();
		LL.swap(4,7);
		LL.display();
		LL.swap(4,7);
		LL.display();
		LL.swap(2,6);
		LL.display();
		LL.swap(2,6);
		LL.display();
		LL.swap(1,7);
		LL.display();
		LL.swap(1,7);
		LL.display();
		LL.pop();
		LL.pop();
		LL.pop();
		LL.swap(1,4);
		LL.display();
		LL.swap(1,4);
		LL.display();
		LL.pop();
		LL.swap(1,2);
		LL.display();
		LL.swap(1,2);
		LL.display();
		System.out.println();
		System.out.println("-------------------------------Bubble Sort is tested-------------------------------");
		System.out.println();
		linkedListConstructor bigLinkedList = new linkedListConstructor();
		for(int x = 1; x <= 100; x++) {
			bigLinkedList.push(x);
		}
		for(int x = 0; x < 100; x++) {
			for(int y = x; y < 100; y++) {
				
			}
		}
	}

}
