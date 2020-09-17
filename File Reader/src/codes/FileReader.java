package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("C:\\Users\\702189002\\Documents\\Sudoku Puzzles\\easyPuzzle.txt"));
			int counter = 0;
			while (scanner.hasNextInt()) {
				System.out.print(scanner.nextInt() + " ");
				counter++;
				if(counter % 9 == 0) {
					System.out.println();
				}
			}
			scanner.close();
			System.out.println(counter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
