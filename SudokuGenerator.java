/*Arbetstest åt iData Värnamo
 * Jonathan Persson
 * 2018-05-25
 * 
 * Works almost as intended. Does not always generate a solveable sudoku puzzle. However it does always fulfil the rules of sudoku. It never 
 * the same value in the same row, column or region. To solve this my first idea was to somehow add the values in each row and column together and
 * cross reference against possible moves that could be made. However; I did not have enough time to complete it.
 * 
 * */

import java.util.Random;

public class SudokuGenerator {
	
	static int rows = 4;
	static int columns = 4;
	
	static Random rn = new Random();
	
	public static void main(String [] args){
		
		
		int[][] sudoku = generateSudoku(rows,columns);  // Generating Sudoku.
		
		printSudoku(sudoku);
		
		System.out.println("\n\n" + "Row number " + "0 Correct?  " + isValidRow(sudoku,0));
		System.out.println("Row number " + "1 Correct?  " + isValidRow(sudoku,1));
		System.out.println("Row number " + "2 Correct?  " + isValidRow(sudoku,2));
		System.out.println("Row number " + "3 Correct?  " + isValidRow(sudoku,3) + "\n");
		System.out.println("Column number " + "0 Correct?   " + isValidColumn(sudoku,0));
		System.out.println("Column number " + "1 Correct?   " + isValidColumn(sudoku,1));
		System.out.println("Column number " + "2 Correct?   " + isValidColumn(sudoku,2));
		System.out.println("Column number " + "3 Correct?   " + isValidColumn(sudoku,3) + "\n");
		System.out.println("Region number " + "1 Correct?   " +isValidRegion(sudoku,1));
		System.out.println("Region number " + "2 Correct?   " +isValidRegion(sudoku,2));
		System.out.println("Region number " + "3 Correct?   " +isValidRegion(sudoku,3));
		System.out.println("Region number " + "4 Correct?   " +isValidRegion(sudoku,4));
		
	}
	
	public static int[][] generateSudoku(int rows, int columns){
		
		int[][] box = new int [rows][columns];
		
		/* Looping over all cells in the array, assigning a value 1-4 into each one provided they fulfil all rules.*/
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				box[i][j] = rn.nextInt(4) + 1;
				
				
				/* Quickly implemented code, had a problem with 2 values occurring at the same column. Had to do a quick-fix due to running out of time. 
				 * checks the current cell for equal values in the same column.*/
				
				
				if(i == 1){
					for (int k= 2; k < 4; k++) {
						if(box[i][j] == box[i][k]){
							box[i][j] = 0;
						}
						else if(box[i][j] == box[i][0]){
							box[i][j] = 0;
						}
					}
				}
				
				if(i == 2){
					for (int k = 3; k < 4; k++) {
						if(box[i][j] == box[i][k]){
							box[i][j] = 0;
						}
						else if(box[i][j] == box[i][0]){
							box[i][j] = 0;
						}
						else if(box[i][j] == box[i][1]){
							box[i][j] = 0;
						}
					}
				}
				
				if(i == 3){
					for (int k = 3; k <= 0; k--) {
						if(box[i][j] == box[i][k]){
							box[i][j] = 0;
						}
					}
				}
				
				if(!isValidRow(box,i) || !isValidColumn(box,j) || !isValidRegion(box,1) || !isValidRegion(box,2) || !isValidRegion(box,3) || !isValidRegion(box,4)){  //Checking booleans to make sure we don't add number at wrong space.
					box[i][j] = 0;
				}
			}
		}
		
		return box;
		
	}
	
	
	
	public static void printSudoku(int[][] a){
		
		/* Printing all cells, including some whitespace to make it easier to read.*/
		
	for (int i = 0; i < rows; i++) {
			
			if(i>0){
				System.out.println("\n"); 
				
				}
			
			for (int j = 0; j < columns; j++) {
				
				if(a[i][j] == 0){
					System.out.print('-' + "      ");
				}
				else{
				System.out.print(a[i][j] + "      ");
				}
			}
		}
	}
	
	public static boolean isValidRow(int[][] a, int rowNumber){
		
		
		/* Checks all rows for double values using bubble sort. */
		for (int i = 0; i < rows; i++) {
				for (int j = i+1; j < rows; j++) {
					if(a[rowNumber][i] == a[rowNumber][j]){
						if(a[rowNumber][i] == 0 && a[rowNumber][j] == 0) break;
					return false;
					}
				}
		}
		return true;
		
	}


	
	
	public static boolean isValidColumn(int[][] a, int columnNumber){
		
		/* Checks all columns for double values using bubble sort.*/
		for (int i = 0; i < columns; i++) {
				for (int j = i+1; j < columns; j++) {
					if(a[i][columnNumber] == a[j][columnNumber]){
						if(a[i][columnNumber] == 0 && a[j][columnNumber] == 0) break;
						return false;
					}
				}
		}
		return true;
		
	}
	
	public static boolean isValidRegion(int[][]a,int region){
		
		if(region < 0 || region > 4){
			System.out.println("Not a Valid Region.");
			return false;
		}
		
		/* One case for each region of the Sudoku to check that we don't have the same value twice in our 4 2x2 boxes. */
		
		switch(region){
		case 1: if(a[0][0] == a[0][1] || 
				   a[0][0] == a[1][0] ||
				   a[0][0] == a[1][1] ||
				   a[0][1] == a[1][0] || 
				   a[0][1] == a[1][1] || 
				   a[1][0] == a[1][1]){
			
				 if(a[0][0] == 0 && a[0][1] == 0 ||
					a[0][0] == 0 && a[1][0] == 0 ||
					a[0][0] == 0 && a[1][1] == 0 ||
					a[0][1] == 0 && a[1][0] == 0 ||
					a[0][1] == 0 && a[1][1] == 0 ||
					a[1][0] == 0 && a[1][1] == 0){
				return true;
			}
			return false;
		}
			break;
		case 2: if(a[0][2] == a[0][3] ||
				   a[0][2] == a[1][2] ||
				   a[0][2] == a[1][3] ||
				   a[0][3] == a[1][2] ||
				   a[0][3] == a[1][3] ||
			   	   a[1][2] == a[1][3]){
			
				 if(a[0][2] == 0 && a[0][3] == 0 ||
					a[0][2] == 0 && a[1][2] == 0 ||
					a[0][2] == 0 && a[1][3] == 0 ||
					a[0][3] == 0 && a[1][2] == 0 ||
					a[0][3] == 0 && a[1][3] == 0 ||
					a[1][2] == 0 && a[1][3] == 0){
				return true;
			}
			return false;
		}
			break;
			
		case 3: if(a[2][0] == a[2][1] ||
				   a[2][0] == a[3][0] ||
			       a[2][0] == a[3][1] ||
				   a[2][1] == a[3][0] ||
				   a[2][1] == a[3][1] ||
				   a[3][0] == a[3][1]){
			
				 if(a[2][0] == 0 && a[2][1] == 0 ||
					a[2][0] == 0 && a[3][0] == 0 ||
					a[2][0] == 0 && a[3][1] == 0 ||
					a[2][1] == 0 && a[3][0] == 0 ||
					a[2][1] == 0 && a[3][1] == 0 ||
					a[3][0] == 0 && a[3][1] == 0){
				return true;
			}
			return false;
		}
			break;
			
		case 4: if(a[2][2] == a[2][3] ||
				   a[2][2] == a[3][2] ||
				   a[2][2] == a[3][3] ||
				   a[2][3] == a[3][2] ||
				   a[2][3] == a[3][3] ||
				   a[3][2] == a[3][3]){
			
				 if(a[2][2] == 0 && a[2][3] == 0 ||
					a[2][2] == 0 && a[3][2] == 0 ||
					a[2][2] == 0 && a[3][3] == 0 ||
					a[2][3] == 0 && a[3][2] == 0 ||
					a[2][3] == 0 && a[3][3] == 0 ||
					a[3][2] == 0 && a[3][3] == 0){
				return true;
			}
			return false;
		}
			break;
		}
		
		return true;
		
	}
	

}
