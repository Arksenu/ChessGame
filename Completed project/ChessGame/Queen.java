
class Queen extends Piece {
	private String queen;
	private Boolean white;

	/**
	Instantiates queen piece
	@param white The side its on
	**/
	public Queen(boolean white) {
		this.white = white;
		if (white)
			queen = "  Q  ";
		else
			queen = "  q  ";
	}

	public String getName() {
		return queen;
	}

	public Boolean getSide() {
		return white;
	}
}