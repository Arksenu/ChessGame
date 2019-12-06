
class Pawn extends Piece {
	private String pawn;
	private Boolean white;
	private Boolean firstMove = true;

	/**
	Instantiates Pawn piece
	@param white The side its on
	**/
	public Pawn(boolean white) {
		this.white = white;
		if (white) {
			pawn = "  P  ";
		}
		else {
			pawn = "  p  ";
		}
	}

	public String getName() {
		return pawn;
	}

	public Boolean getSide() {
		return white;
	}

	public Boolean getFirstMove() {
		return firstMove;
	}

	public void setFirstMoveFalse() {
		firstMove = false;
	}
}
	