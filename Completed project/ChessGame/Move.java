
import java.lang.Math;
class Move {
	private static Boolean whiteTurn = true;

	/**
	Checks if piece exists at given coordinates, and if it can move to given coordinates
	@param player The player that is making the move
	@param startRow The row of the piece that will be moved
	@param startCol The column of the piece that will be moved
	@param endRow The row that the piece will be moved to
	@param endCol The column that the piece will be moved to
	@return Returns if the piece can move to the given coordinates
	**/
	public static Boolean move(Player player, int startRow, int startCol, int endRow, int endCol) {
		if (whiteTurn == player.getSide() && checkMove(startRow, startCol, endRow, endCol)) {
			Spot target = Board.board[startRow][startCol];
			Spot enemy = Board.board[endRow][endCol];
			Board.board[startRow][startCol] = new Spot();
			Board.board[endRow][endCol] = target;
			whiteTurn = !whiteTurn;
			Board.update();
			//checks if king is dead
			if(enemy.getPiece() != null)
				if (enemy.getPiece().getName().equals("  k  ") || enemy.getPiece().getName().equals("  K  ")) {
					System.out.println("GAME OVER!");
					System.exit(1);
				}
			return true;
		}
		else {
			Board.update();
			System.out.println("This move is not allowed");
			return false;
		}
	}

	/**
	Checks movement for each kind of piece. Pawns,rooks,bishops etc all have different movement mechanics
	@param startRow The row of the piece that will be moved
	@param startCol The column of the piece that will be moved
	@param endRow The row that the piece will be moved to
	@param endCol The column that the piece will be moved to
	@return Returns if the move is valid for the given piece
	**/
	public static Boolean checkMove(int startRow, int startCol, int endRow, int endCol) {
		if (startRow > 7 || endRow > 7 || startCol > 7 || endCol > 7)
			return false;
		if (Board.board[startRow][startCol].getPiece() == null)
			return false;
		if (startRow == endRow && startCol == endRow)
			return false;
		if (checkAttack(startRow, startCol, endRow, endCol))
			return true;
		Spot piece = Board.board[startRow][startCol];
		switch (piece.getPiece().getName().toLowerCase()) {
			case "  p  ": 
			//if statements check if the movement is correct for each side, lower side pawns should not be able to move down, upper cannot move up
			if (whiteTurn && endRow < startRow)
				return false;
			if (!whiteTurn && endRow > startRow)
				return false;
			if (piece.getPiece().getFirstMove() && adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 2, 0)) {
				piece.getPiece().setFirstMoveFalse();
				return true;
			}
			else if(adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 1, 0)) {
				return true;
			}
			break;
			case "  r  ":
			//makes sure rook can only move either horizontally or vertically
				if (startRow==endRow && adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 0, 8))
					return true;
				else if(startCol==endCol && adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 8, 0))
					return true;
				break;
			case "  h  ":
			//knights hacve special movement function because their movement is weird
			if (knightOnlyCheck(whiteTurn, startRow, startCol, endRow, endCol))
				return true;
			break;	
			case "  b  ":
			//makes sure that neither the row or column are the same after bishop moves, also makes sure the difference between new rows and columns are
			//the same, to ensure diagnol movement
				if (startRow != endRow && startCol != endCol 
					&& adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 8, 8)
					&& Math.abs(startRow-endRow) == Math.abs(startCol-endCol))
					return true;
				break;	
			case "  q  ":
			//every movement except knight combined for queen
				if (startRow==endRow && adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 0, 8))
					return true;
				else if(startCol==endCol && adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 8, 0))
					return true;
				else if (startRow != endRow && startCol != endCol 
					&& adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 8, 8)
					&& Math.abs(startRow-endRow) == Math.abs(startCol-endCol))
					return true;
				break;
			case "  k  ":
			//allows movement in any direction, but only one spot
				if (adjustAndCheck(whiteTurn, startRow, startCol, endRow, endCol, 1, 1))
					return true;
				break;
		}
		return false;
	}
	/**
	Checks if the difference between the start and end coordinates comply with movement constraints for pieces
	@param white The side the piece is on
	@param startRow The row of the piece that will be moved
	@param startCol The column of the piece that will be moved
	@param endRow The row that the piece will be moved to
	@param endCol The column that the piece will be moved to
	@param rowAdjust Restricts row movement to this value
	@param colAdjust Restricts column movement to this value
	@return Returns if the movement is within the given bounds
	**/
	private static Boolean adjustAndCheck(boolean white, int startRow, int startCol, int endRow, int endCol, int rowAdjust, int colAdjust) {
		if (white && (endRow)-(startRow)<=rowAdjust && Math.abs((startCol)-(endCol))<=colAdjust && canMove(white, startRow, startCol, endRow, endCol)) {
			return true;
		}
		else if (!white && (startRow)-(endRow)<=rowAdjust && Math.abs((startCol)-(endCol))<=colAdjust && canMove(white, startRow, startCol, endRow, endCol)) {	
			return true;
		}
		return false;
	}

	/**
	Checks knight movement, and if the end target is a teammate, restrict movement
	@param white The side the piece is on
	@param startRow The row of the piece that will be moved
	@param startCol The column of the piece that will be moved
	@param endRow The row that the piece will be moved to
	@param endCol The column that the piece will be moved to
	@return Returns if the knight can move to the end location
	**/
	private static Boolean knightOnlyCheck(boolean whiteTurn, int startRow, int startCol, int endRow, int endCol) {
		Spot possibleTarget = Board.board[endRow][endCol];
		if (possibleTarget.getPiece() != null)
			if (possibleTarget.getPiece().getSide() == whiteTurn)
				return false;
		if ((Math.abs(startRow-endRow) == 2 && Math.abs(startCol-endCol) == 1) || (Math.abs(startRow-endRow)==1 && Math.abs(startCol-endCol) == 2))
			return true;
		return false;
	}
	/**
	Checks if attack is available for pawn only since pawns attack differs from their movement.
	@param startRow The row of the piece that will be moved
	@param startCol The column of the piece that will be moved
	@param endRow The row that the piece will be moved to
	@param endCol The column that the piece will be moved to
	**/
	private static Boolean checkAttack(int startRow, int startCol, int endRow, int endCol) {
		Spot piece = Board.board[startRow][startCol];
		Spot target = Board.board[endRow][endCol];
		if (target.getPiece() == null)
			return false;
		if (piece.getPiece() == null)
			return false;
		switch (piece.getPiece().getName().toLowerCase()) {
			case "  p  ":
				if (whiteTurn && endRow < startRow)
					return false;
				if (!whiteTurn && endRow > startRow)
					return false;
				if (whiteTurn && endRow == startRow + 1 && (endCol == startCol + 1 || endCol == startCol - 1))
					return true;
				else if (!whiteTurn && endRow == startRow - 1 && (endCol == startCol + 1 || endCol == startCol - 1))
					return true;
			break;
		}
	return false;
	
	}	
	/**
	Checks if the end coordinates are blocked with teammate, otherwise allow movement (if enemy is in the way, it kills it)
	@param white The side the piece is on
	@param startRow The row of the piece that will be moved
	@param startCol The column of the piece that will be moved
	@param endRow The row that the piece will be moved to
	@param endCol The column that the piece will be moved to
	**/
	private static Boolean canMove(boolean white, int startRow, int startCol, int endRow, int endCol) {
		if (Board.board[endRow][endCol].getPiece() != null)
			if (Board.board[endRow][endCol].getPiece().getSide() == white)
				return false;
		return true;
	}
}