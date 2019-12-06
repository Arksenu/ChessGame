
class Knight extends Piece {
	private String knight;
	private Boolean white;

	/**
	Instantiates Knight piece
	@param white The side its on
	**/
	public Knight(boolean white) {
		this.white = white;
		if (white)
			knight = "  H  ";
		else
			knight = "  h  ";
	}

	public String getName() {
		return knight;
	}

	public Boolean getSide() {
		return white;
	}
 }