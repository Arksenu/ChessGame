
class Rook extends Piece {
	private String rook;
	private Boolean white;

	/**
	Instantiates Rook piece
	@param white The side its on
	**/
	public Rook(boolean white) {
		this.white = white;
		if (white)
			rook = "  R  ";
		else
			rook = "  r  ";
	}

	public String getName() {
		return rook;
	}

	public Boolean getSide() {
		return white;
	}
}