
class Bishop extends Piece {
	private String bishop;
	private Boolean white;

	/**
	Instantiates Bishop piece
	@param white The side its on
	**/
	public Bishop(boolean white) {
		this.white = white;
		if (white)
			bishop = "  B  ";
		else
			bishop = "  b  ";
	}

	public String getName() {
		return bishop;
	}

	public Boolean getSide() {
		return white;
	}
}