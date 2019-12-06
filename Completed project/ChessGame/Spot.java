
class Spot {
	private Piece piece;
	private Boolean empty;
	private String emptySpace;
	/**
	Instantiates a new spot that holds no pieces
	**/
	public Spot() { 
		emptySpace = " [ ] ";
		empty = true;
		piece = null;
	}
	/**
	Instantiates a spot that has a piece on it
	@param piece The piece in that spot
	**/
	public Spot(Piece piece) {
		this.piece = piece;
	}
	/**
	Sets a piece in a specific spot
	@param spot The spot in which the piece will be set
	**/
	public void setPiece(Piece spot) {
		this.piece = piece;
	}
	/**
	Sets the spot to either empty or false
	@param status True = empty, false = not empty
	**/
	public void setEmpty(Boolean status) {
		empty = status;
	}
	/**
	@return Returns the piece object in the spot
	**/
	public Piece getPiece() {
		return piece;
	}
	/**
	@return Returns if the spot is empty or not
	**/
	public Boolean getEmpty() {
		return empty;
	}
	/**
	@return Returns the empty space String
	**/
	public String getEmptySpace() {
		return emptySpace;
	}

}