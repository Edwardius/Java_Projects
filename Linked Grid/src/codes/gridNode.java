package codes;

public class gridNode {
	
	private int data;
	gridNode above;
	gridNode below;
	gridNode next;
	gridNode prev;
	gridNode path1;
	gridNode path2;
	gridNode path3;
	gridNode path4;
	
	public gridNode getPath1() {			//paths getters and setters
		return path1;
	}
	public void setPath1(gridNode path1) {
		this.path1 = path1;
	}
	public gridNode getPath2() {
		return path2;
	}
	public void setPath2(gridNode path2) {
		this.path2 = path2;
	}
	public gridNode getPath3() {
		return path3;
	}
	public void setPath3(gridNode path3) {
		this.path3 = path3;
	}
	public gridNode getPath4() {
		return path4;
	}
	public void setPath4(gridNode path4) {
		this.path4 = path4;
	}
	public gridNode getPath5() {
		return path5;
	}
	public void setPath5(gridNode path5) {
		this.path5 = path5;
	}
	public gridNode getPath6() {
		return path6;
	}
	public void setPath6(gridNode path6) {
		this.path6 = path6;
	}
	public gridNode getPath7() {
		return path7;
	}
	public void setPath7(gridNode path7) {
		this.path7 = path7;
	}
	public gridNode getPath8() {
		return path8;
	}
	public void setPath8(gridNode path8) {
		this.path8 = path8;
	}
	gridNode path5;
	gridNode path6;
	gridNode path7;
	gridNode path8;
	
	public void node() {						//no data set in new node
		data = 0;
		above = null;
		below = null;
		next = null;
		prev = null;
	}
	public void node(int data) {				//data set in new node
		this.data = data;
		above = null;
		below = null;
		next = null;
		prev = null;
	}
	public gridNode getAbove() {				//getters and setters
		return above;
	}
	public void setAbove(gridNode above) {
		this.above = above;
	}
	public gridNode getBelow() {
		return below;
	}
	public void setBelow(gridNode below) {
		this.below = below;
	}
	public gridNode getNext() {
		return next;
	}
	public void setNext(gridNode next) {
		this.next = next;
	}
	public gridNode getPrev() {
		return prev;
	}
	public void setPrev(gridNode prev) {
		this.prev = prev;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
}
