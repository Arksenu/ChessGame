
abstract class Piece {
	/**
	@return Returns the name of a piece
	**/
	abstract String getName();
	/**
	@return Returns which side the piece is on
	**/
	abstract Boolean getSide();

	//For pawns only
	/**
	@return Returns whether or not this is a pawns first move or not
	**/
	abstract Boolean getFirstMove();
	/**
	Makes it so that a pawn can no longer move two spots if it isnt its first time moving
	**/
	abstract void setFirstMoveFalse();
}	