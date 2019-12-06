
class Player {
	private Boolean white;
	/**
	Creates Player instance
	@param white True = 'white', False = 'black'
	**/
	public Player(Boolean white) {
		this.white = white;
	}

	public Boolean getSide() {
		return white;
	}
}