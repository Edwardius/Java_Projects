package codes;

public class nodeConstructor {
	
	private int data;
	nodeConstructor next;
	nodeConstructor prev;
	public void node(){						//no data in node
		data = 0;
		next = null;
		prev = null;
	}
	public void node(int data) {			//int data in node
		this.data = data;
		next = null;
		prev = null;
	}
	public int getData() {					//getters and setters
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public nodeConstructor getNext() {
		return next;
	}
	public void setNext(nodeConstructor next) {
		this.next = next;
	}
	public nodeConstructor getPrev() {
		return prev;
	}
	public void setPrev(nodeConstructor prev) {
		this.prev = prev;
	}
	
}

