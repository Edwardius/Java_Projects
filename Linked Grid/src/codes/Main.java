package codes;

public class Main {
	public static void main(String[] args) throws Exception{
		linkedGridConstructor LG = new linkedGridConstructor();
		LG.linkedGrid(5);
		LG.display();
		LG.knightsTour(LG.getRoot(), 1);
	}
}
