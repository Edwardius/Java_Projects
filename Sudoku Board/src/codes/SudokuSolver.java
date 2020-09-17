package codes;

public class SudokuSolver {

	public static void main(String[] args)throws Exception {

		board puzzle = new board();
		puzzle.loadPuzzle("hard");
		puzzle.display();
		puzzle.logicCycles();
		puzzle.display();



	}

}
