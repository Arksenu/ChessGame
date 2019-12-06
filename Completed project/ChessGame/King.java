
class King extends Piece {
	private String king;
	private Boolean white;

	/**
	Instantiates King piece
	@param white The side its on
	**/
	public King(boolean white) {
		this.white = white;
		if (white) {
			king = "  K  ";
		}
		else {
			king = "  k  ";
		}
	}

	public String getName() {
		return king;
	}

	public Boolean getSide() {
		return white;
	}
}