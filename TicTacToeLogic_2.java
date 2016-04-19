import java.util.Scanner;

public class TicTacToeLogic_2 {
	final int BOARD_SIZE = 3;           // 3X3 board
	public boolean game = true;  		// false when game is over

	private int [][] board;             // board[row][column]
	private int turn = 1;               // Player plays an X = 1, O = 2, X goes first

	public TicTacToeLogic_2() {
		board = new int[BOARD_SIZE][BOARD_SIZE];   // Initialize board
	}

	public void printBoard() {
		// prints board
		for (int i = 0; i<BOARD_SIZE; i++) {
			for(int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public int getTurn() {
		return turn;
	}

	public void placePiece(int i, int j) {
		// Going to place a piece on the board depending on turn
		if(isValidMove(i,j)) {
			board[i][j] = turn;
			printBoard();						// REMOVE after set GUI
			if(!isWinner())
				changeTurn();
			else
				game = false;	
		}
	}

	private boolean isWinner() {
		// check if someone has won
		int iCounter = 0, jCounter = 0, dCounter1 = 0, dCounter2 = 0;  // counters for check wins
		for (int i = 0; i < BOARD_SIZE; i++) {
			if (dCounter1 == BOARD_SIZE || dCounter2 == BOARD_SIZE)
				return true;
	
			if (board[i][i] == turn)
				dCounter1++;
			if (board[i][BOARD_SIZE-1-i] == turn)
				dCounter2++;

			for(int j = 0; j < BOARD_SIZE; j++) {
				if (board[i][j] == turn)
					iCounter++;
				if (board[j][i] == turn)
					jCounter++;
			}

			if (iCounter == BOARD_SIZE || jCounter == BOARD_SIZE)
				return true;
			iCounter = 0;
			jCounter = 0;
		}

		return false;

	}

	private void changeTurn() {
		// changes the turn
		turn = ( turn == 1 ? 2 : 1 );
	}

	private boolean isValidMove(int i, int j) {
		// BEING CALLED BY placePiece to check if valid move
		return board[i][j]==0;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner( System.in );
		TicTacToeLogic_2 tic = new TicTacToeLogic_2();
		int x ,y;
		tic.printBoard();
		while(tic.game) {
			System.out.println("It is Player " + tic.getTurn() + "'s turn");
			System.out.print("Enter row #: ");
			x = in.nextInt();

			System.out.print("Enter column #: ");
			y = in.nextInt();

			tic.placePiece(x,y);
		}
		System.out.println("Winner is Player" + tic.getTurn()+ "!");
	}
}
