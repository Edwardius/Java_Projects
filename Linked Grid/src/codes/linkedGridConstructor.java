package codes;

public class linkedGridConstructor {
	
	private gridNode root;
	private gridNode columnMarker;
	private gridNode rowMarker;
	private int dimension;
	private int path = 0;
	
	public void linkedGrid(int dimension) throws Exception {
		this.dimension = dimension;
		gridNode nodeMaker = new gridNode();
		nodeMaker.node();
		root = nodeMaker;
		rowMarker = nodeMaker;
		columnMarker = nodeMaker;
		for(int x = 0; x < dimension - 1; x++) {		//creates first row
			nodeMaker = new gridNode();
			nodeMaker.node();
			root = nodeMaker;
			nodeMaker.setNext(columnMarker);
			columnMarker.setPrev(nodeMaker);
			columnMarker = columnMarker.getPrev();
		}
		for(int y = 0; y < dimension - 1; y++) {		//creates remaining rows
			nodeMaker = new gridNode();
			nodeMaker.node();
			rowMarker = nodeMaker;
			nodeMaker.setAbove(columnMarker);
			columnMarker.setBelow(nodeMaker);
			columnMarker = nodeMaker;
			for(int x = 0; x < dimension - 1; x++) {
				nodeMaker = new gridNode();
				nodeMaker.node();
				nodeMaker.setPrev(columnMarker);
				columnMarker.setNext(nodeMaker);
				nodeMaker.setAbove(nodeMaker.getPrev().getAbove().getNext());
				gridNode tempRef = nodeMaker.getAbove();
				tempRef.setBelow(nodeMaker);
				columnMarker = columnMarker.getNext();
			}
			columnMarker = rowMarker; 
		}
		columnMarker = root;
		rowMarker = root;
		while(rowMarker != null) {								//try catch for all of the possible knight's paths
			while(columnMarker != null) {
				try {
					columnMarker.setPath1(columnMarker.getAbove().getAbove().getPrev());
				} catch (Exception e) {}
				try {
					columnMarker.setPath2(columnMarker.getAbove().getAbove().getNext());
				} catch (Exception e) {}
				try {
					columnMarker.setPath3(columnMarker.getNext().getNext().getAbove());
				} catch (Exception e) {}
				try {
					columnMarker.setPath4(columnMarker.getNext().getNext().getBelow());
				} catch (Exception e) {}
				try {
					columnMarker.setPath5(columnMarker.getBelow().getBelow().getNext());
				} catch (Exception e) {}
				try {
					columnMarker.setPath6(columnMarker.getBelow().getBelow().getPrev());
				} catch (Exception e) {}
				try {
					columnMarker.setPath7(columnMarker.getPrev().getPrev().getBelow());
				} catch (Exception e) {}
				try {
					columnMarker.setPath8(columnMarker.getPrev().getPrev().getAbove());
				} catch (Exception e) {}
				columnMarker = columnMarker.getNext();
			}
			rowMarker = rowMarker.getBelow();
			columnMarker = rowMarker;
		}
	}
	
	public void display() {
		columnMarker = root;	
		rowMarker = root;
		while(rowMarker != null) {
			columnMarker = rowMarker;
			while(columnMarker != null) {
				System.out.print(columnMarker.getData() + " ");
				columnMarker = columnMarker.getNext();
			}
			System.out.println();
			rowMarker = rowMarker.getBelow();
		}
		System.out.println();
		/*columnMarker = root;					//checks the vertical connections
		rowMarker = root;
		while(columnMarker != null) {
			rowMarker = columnMarker;
			while(rowMarker != null) {
				System.out.print(rowMarker.getData() + " ");
				if(rowMarker.getBelow() == null)
					break;
				rowMarker = rowMarker.getBelow();
			}
			System.out.println();
			if(columnMarker.getNext() == null)
				break;
			columnMarker = columnMarker.getNext();
		}*/
	}
	
	public void knightsTour(gridNode position, int counter) {
		position.setData(counter);
		if(counter == dimension*dimension) {					//if finish
			path++;
			System.out.println("Path Counter: " + path);
			display();
			position.setData(0);
		}														//searches for possible path
		if(position.getPath1() != null && position.getPath1().getData() == 0) 
			knightsTour(position.getPath1(), counter + 1);
		if(position.getPath2() != null && position.getPath2().getData() == 0) 
			knightsTour(position.getPath2(), counter + 1);
		if(position.getPath3() != null && position.getPath3().getData() == 0) 
			knightsTour(position.getPath3(), counter + 1);
		if(position.getPath4() != null && position.getPath4().getData() == 0) 
			knightsTour(position.getPath4(), counter + 1);
		if(position.getPath5() != null && position.getPath5().getData() == 0) 
			knightsTour(position.getPath5(), counter + 1);
		if(position.getPath6() != null && position.getPath6().getData() == 0) 
			knightsTour(position.getPath6(), counter + 1);
		if(position.getPath7() != null && position.getPath7().getData() == 0) 
			knightsTour(position.getPath7(), counter + 1);
		if(position.getPath8() != null && position.getPath8().getData() == 0) 
			knightsTour(position.getPath8(), counter + 1);
		position.setData(0);
	}

	public gridNode getRoot() {
		return root;
	}

	public void setRoot(gridNode root) {
		this.root = root;
	}
}
