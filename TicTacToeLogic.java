import java.util.Scanner;

public class TicTacToeLogic
{
	final int BOARD_SIZE = 3;           // 3X3 board
	public boolean game = true;  		// false when game is over

	private int [][] board;            // board[row][column]
	private int turn = 1;              // Player plays an X = 1, O = 2, X goes first

	public TicTacToeLogic()
	{
		board = new int[BOARD_SIZE][BOARD_SIZE];   // Initialize board
	}

	public void printBoard()
	{
		// prints board
		for (int i = 0; i<BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public int getTurn()
	{
		return turn;
	}

	public void placePiece(int i, int j)
	{
		// Going to place a piece on the board depending on turn
		if(isValidMove(i,j)) 
		{
			board[i][j] = turn;
			printBoard();						// REMOVE after set GUI
			if(!isWinner())
				changeTurn();
			else
				game = false;
		}
	}

	private boolean isWinner()
	{
		// check if someone has won
		return checkDiagonal() || checkHorizontal() || checkVertiacal();

	}

	private void changeTurn()
	{
		// changes the turn
		turn = ( turn == 1 ? 2 : 1 );
	}

	private boolean isValidMove(int i, int j)
	{
		// BEING CALLED BY placePiece to check if valid move
		return board[i][j]==0;
	}

	private boolean checkDiagonal()
	{
		// checks if anyone won diagonally
		return ( (board[0][0] == turn && board [1][1] == turn && board[2][2] == turn) ||  
			(board[0][2] == turn && board [1][1] == turn && board[2][0] == turn) );
		
	}

	private boolean checkHorizontal()
	{
		// checks if anyone won horizontally
		return ( (board[0][0] == turn && board [0][1] == turn && board[0][2] == turn) ||
			   (board[1][0] == turn && board [1][1] == turn && board[1][2] == turn) || 
			(board[2][0] == turn && board [2][1] == turn && board[2][2] == turn) );
	}

	private boolean checkVertiacal()
	{
		// checks if anyone won vertically
		return ( (board[0][0] == turn && board [1][0] == turn && board[2][0] == turn) ||
			   (board[0][1] == turn && board [1][1] == turn && board[2][1] == turn) || 
			(board[0][2] == turn && board [1][2] == turn && board[2][2] == turn) );
	}


	public static void main( String args[] )
	{
		Scanner in = new Scanner( System.in );
		TicTacToeLogic tic = new TicTacToeLogic();
		int x ,y;
		tic.printBoard();
		while(tic.game)
		{
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
