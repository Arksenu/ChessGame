
class Board {

	public static Spot[][] board = new Spot[8][8];
	private static String emptySpace = " [ ] ";

	/**
	Prints the board and instantiates all chess pieces onto it
	**/
	public static void printBoard() {
		System.out.println("    0    1    2    3    4    5    6    7");
		for (int row = 0; row <= 7; row++) {
			System.out.print(row + " ");
			for (int col = 0; col <= 7; col++) {
				if (setPieces(row, col) == null) {
					board[row][col] = new Spot();
					System.out.print(board[row][col].getEmptySpace());
				}
				else {
					board[row][col] = setPieces(row, col);
					System.out.print(board[row][col].getPiece().getName());
				}

			}
			System.out.println();
		}
	} 
	/**
	Updates the board after a move is made to graphically represent the new move
	**/
	public static void update() {
		System.out.println("\n___________________");
		System.out.println("    0    1    2    3    4    5    6    7");
		for (int row = 0; row <= 7; row++) {
			System.out.print(row + " ");
			for (int col = 0; col <= 7; col++) {
				if (board[row][col].getEmptySpace() == null) {
					System.out.print(board[row][col].getPiece().getName());
				}
				else {
					System.out.print(board[row][col].getEmptySpace());
				}
			}
			System.out.println();
		}
	}

	/**
	Instantiates chess pieces
	@param row The row in which the piece will be set
	@param col The column in which the piece will be set
	**/
	public static Spot setPieces(int row, int col) {
		if (row == 1)
			return new Spot(new Pawn(true));
		if (row == 6)
			return new Spot(new Pawn(false));
		if (row == 0 && col == 4)
			return new Spot(new King(true));
		if (row == 7 && col == 4)
			return new Spot(new King(false));
		if (row == 0 && col == 3)
			return new Spot(new Queen(true));
		if (row == 7 && col == 3)
			return new Spot(new Queen(false));
		if (row == 0 && (col == 0 || col == 7))
			return new Spot(new Rook(true));
		if (row == 7 && (col == 0 || col == 7))
			return new Spot(new Rook(false));
		if (row == 0 && (col == 1 || col == 6))
			return new Spot(new Knight(true));
		if (row == 7 && (col == 1 || col == 6))
			return new Spot(new Knight(false));
		if (row == 0 && (col == 2 || col == 5))
			return new Spot(new Bishop(true));
		if (row == 7 && (col == 2 || col == 5))
			return new Spot(new Bishop(false));

		return null;
	}

}