package codes;

import java.io.File;
import java.util.Scanner;

public class board{

	/*The Sudoku Board is made of 9x9 cells for a total of 81 cells.
	 * In this program we will be representing the Board using a 2D Array of cells.
	 *
	 */

	private Cell[][] board = new Cell[9][9];

	//The variable "level" records the level of the puzzle being solved.
	private String level = "";

	/*At some point in the solving process, the algorithm will need to make a guess. LastGuess records the last guess that was made.
	 * There may need to be more than 1 guess - that's why this is an array.  At most, there would only ever be 81 guesses (one guess
	 * per cell).
	 */
	private int[] lastGuess = new int[81];
	private int[] lastGuessX = new int[81];
	private int[] lastGuessY = new int[81];
	private int numberOfGuesses = 0;

	///TODO: CONSTRUCTOR
	//This must initialize every cell on the board with a generic cell.  It must also assign all of the boxIDs to the cells
	public board()
	{
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++) {
				Cell cell = new Cell();
				if(x < 3 && y < 3)
					cell.setBoxID(1);
				
				else if(x < 6 && y < 3)
					cell.setBoxID(2);
				
				else if(x < 9 && y < 3)
					cell.setBoxID(3);
				
				else if(x < 3 && y < 6)
					cell.setBoxID(4);
				
				else if(x < 6 && y < 6)
					cell.setBoxID(5);
				
				else if(x < 9 && y < 6)
					cell.setBoxID(6);
				
				else if(x < 3 && y < 9)
					cell.setBoxID(7);
				
				else if(x < 6 && y < 9)
					cell.setBoxID(8);
				
				else if(x < 9 && y < 9)
					cell.setBoxID(9);
				
				board[x][y] = cell;
			}
		}
	}



	///TODO: loadPuzzle
	/*This method will take a single String as a parameter.  The String must be either "easy", "medium" or "hard"
	 * If it is none of these, the method will set the String to "easy".  The method will set each of the 9x9 grid
	 * of cells by accessing either "easyPuzzle.txt", "mediumPuzzle.txt" or "hardPuzzle.txt" and setting the Cell.number to
	 * the number given in the file.
	 *
	 * This must also set the "level" variable
	 * TIP: Remember that setting a cell's number affects the other cells on the board.
	 */
	public void loadPuzzle(String level) throws Exception
	{
		int counterY = 0;
		int counterX = 0;
		if(level == "medium") {
			Scanner scanner = new Scanner(new File("C:\\Users\\702189002\\Documents\\Sudoku Puzzles\\mediumPuzzle.txt"));
			while (scanner.hasNextInt()) {
				for(int y=0;y<9;y++)
				{
					for(int x = 0;x<9;x++)
					{
						int cellNumber = scanner.nextInt();
						board[x][y].setNumber(cellNumber);
					}
				}

			}
		}
		else if(level == "hard") {
			Scanner scanner = new Scanner(new File("C:\\Users\\702189002\\Documents\\Sudoku Puzzles\\hardPuzzle.txt"));
			while (scanner.hasNextInt()) {
				while (scanner.hasNextInt()) {
					for(int y=0;y<9;y++)
					{
						for(int x = 0;x<9;x++)
						{
							int cellNumber = scanner.nextInt();
							board[x][y].setNumber(cellNumber);
						}
					}

				}
			}
		}
		else {
			Scanner scanner = new Scanner(new File("C:\\Users\\702189002\\Documents\\Sudoku Puzzles\\easyPuzzle.txt"));
			while (scanner.hasNextInt()) {
				for(int y=0;y<9;y++)
				{
					for(int x = 0;x<9;x++)
					{
						int cellNumber = scanner.nextInt();
						board[x][y].setNumber(cellNumber);
					}
				}

			}
		}
	}

	///TODO: isSolved
	/*This method scans the board to and returns TRUE if every cell has been solved.  Otherwise it returns FALSE
	 *
	 */
	public boolean isSolved()
	{
		for(int x = 0; x < board.length; x++) 
			for(int y = 0; y < board.length; y++) 
				if(board[x][y].getNumber() == 0)
					return false;

		return true;
	}


	///TODO: DISPLAY
	/*This method displays the board neatly to the screen.  It must have dividing lines to show where the box boundaries are
	 * as well as lines indicating the outer border of the puzzle
	 */
	public void display()
	{
		for(int y = 0; y < board.length; y++) {
			for(int x = 0; x < board.length; x++) {
				System.out.print(board[x][y].getBoxID());
			}
				
			System.out.println("");
		}
	}

	///TODO: solve
	/*This method solves a single cell at x,y for number.  It also must adjust the potentials of the remaining cells in the same row,
	 * column, and box.
	 */
	public void solve(int x, int y, int number)
	{
		board[x][y].setNumber(number);
		
		for(int h = x+1; h < board.length; h++) 
			board[h][y].cantBe(number);

		for(int h = x+1; h < board.length; h++)
			board[x][h].cantBe(number);

		for(int h = x-1; h > 0; h--) 
			board[h][y].cantBe(number);

		for(int h = x-1; h > 0; h--) 
			board[x][h].cantBe(number);

	}


	//logicCycles() continuously cycles through the different logic algorithms until no more changes are being made.
	public void logicCycles()throws Exception
	{

		while(isSolved() == false)
		{
			int changesMade = 0;
			do
			{
				changesMade = 0;
				changesMade += logic1();
				changesMade += logic2();
				changesMade += logic3();
				changesMade += logic4();
				if(errorFound())
					break;
			}while(changesMade != 0);

			if(!errorFound())
			{
				if(isSolved() == false)
					makeGuess();
			}

			if(errorFound() && numberOfGuesses > 0)
			{
				revert();
			}

		}
	}


	///TODO: logic1
	/*This method searches each row of the puzzle and looks for cells that only have one potential.  If it finds a cell like this, it solves the cell
	 * for that number. This also tracks the number of cells that it solved as it traversed the board and returns that number.
	 */
	public int logic1()
	{
		int changesMade = 0;

		//code goes here
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++) {
				int potentialcounter = 0;

				for(int z = 0; z < 10; z++) 
					if(board[x][y].canBe(z)) 
						potentialcounter++;
							
				if(potentialcounter == 1) {
					for(int z = 0; z < 10; z++) 
						if(board[x][y].canBe(z))
							solve(x,y,z);
				}
			}
		}
		
		return changesMade;

	}

	///TODO: logic2
	/*This method searches each row for a cell that is the only cell that has the potential to be a given number.  If it finds such a cell and it
	 * is not already solved, it solves the cell.  It then does the same thing for the columns.This also tracks the number of cells that
	 * it solved as it traversed the board and returns that number.
	 */

	public int logic2()
	{
		int changesMade = 0;

		int[] possibleoccur = new int[10];
		//code goes here
		//checking rows
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++)
				for(int z = 0; z < possibleoccur.length; z++)//checking for occurrences of different possibilities in a row
					if(board[x][y].canBe(z))
						possibleoccur[z] += 1;
			
			for(int z = 0; z < possibleoccur.length; z++)
				if(possibleoccur[z] == 1) {//if one possibility only occurs once
					changesMade += 1;
					for(int y = 0; y < board.length; y++)
						if(board[x][y].canBe(z))
							solve(x,y,z);
				}
		}
		
		//checking columns
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++)
				for(int z = 0; z < possibleoccur.length; z++)//checking for occurrences of different possibilities in a column
					if(board[y][x].canBe(z))
						possibleoccur[z] += 1;
			
			for(int z = 0; z < possibleoccur.length; z++)
				if(possibleoccur[z] == 1) {//if one possibility only occurs once
					changesMade += 1;
					for(int y = 0; y < board.length; y++)
						if(board[y][x].canBe(z))
							solve(y,x,z);
				}
		}
		return changesMade;
	}

	///TODO: logic3
	/*This method searches each box for a cell that is the only cell that has the potential to be a given number.  If it finds such a cell and it
	 * is not already solved, it solves the cell. This also tracks the number of cells that it solved as it traversed the board and returns that number.
	 */
	public int logic3()
	{

		int changesMade = 0;

		int[] possibleoccur = new int[10];

		//code goes here
		for(int i = 0; i < 9; i++) {
			for(int x = 0; x < board.length; x++) {
				for(int y = 0; y < board.length; y++) {
					if(board[x][y].getBoxID() == i+1) {
						for(int z = 0; z < possibleoccur.length; z++) {
							if(board[y][x].canBe(z))
								possibleoccur[z] += 1;
						}
					}
				}
			}
			
			for(int z = 0; z < possibleoccur.length; z++)
				if(possibleoccur[z] == 1) {//if one possibility only occurs once
					changesMade += 1;
					for(int x = 0; x < board.length; x++)
						for(int y = 0; y < board.length; y++)
							if(board[x][y].canBe(z))
								solve(x,y,z);
			}
		}
		
		return changesMade;
	}


	///TODO: logic4
		/*This method searches each row for the following conditions:
		 * 1. There are two unsolved cells that only have two potential numbers that they can be
		 * 2. These two cells have the same two potentials (They can't be anything else)
		 *
		 * Once this occurs, all of the other cells in the row cannot have these two potentials.  Write an algorithm to set these two potentials to be false
		 * for all other cells in the row.
		 *
		 * Repeat this process for columns and boxes.
		 *
		 * This also tracks the number of cells that it solved as it traversed the board and returns that number.
		 */
	public int logic4()
	{
		int changesMade = 0;

		//code goes here

		return changesMade;
	}


	///TODO: errorFound
	/*This method scans the board to see if any logical errors have been made.  It can detect this by looking for a cell that no longer has the potential to be
	 * any number.
	 */
	public boolean errorFound()
	{
		for(int x = 0; x < board.length; x++)
			for(int y = 0; y < board.length; y++)
				if(board[x][y].numberOfPotentials() == 0)
					return true;

		return false;

	}

	///TODO: makeGuess()
	/*This method looks for the first unsolved cell, and solves it for the first potential number that it could be
	 * It also records the location and the number of the guess.
	 */
		public void makeGuess()
	{
		boolean madeguess = false;
			for(int x = 0; x < board.length; x++) {
				for(int y = 0; y < board.length; y++)
					if(board[x][y].numberOfPotentials() >= 2) {
						solve(x,y,board[x][y].getFirstPotential());
						
						lastGuess[numberOfGuesses] = board[x][y].getFirstPotential();
						lastGuessX[numberOfGuesses] = x;
						lastGuessY[numberOfGuesses] = y;
						numberOfGuesses++;
						madeguess = true;
						break;
					}
				
				if(madeguess)
					break;
			}
	}



	///TODO: revert()
	/*This method resets the board to the state that existed right before the last guess was made.  This includes
	 * previous guesses but not the most recent guess.  It must bring all the potentials of the cells to the state
	 * that existed before the guess was made.  Finally, it must set the potential of the last cell's guess to be
	 * FALSE so that the same guess would not be made again. It also must decrement the number of guesses that were
	 * made on the numberOfGuesses variable.
	 *
	 */
	public void revert()throws Exception
	{

	}



}



