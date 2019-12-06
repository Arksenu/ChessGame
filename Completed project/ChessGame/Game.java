
import java.util.Scanner;
class Game {
	/**
	Runs the game
	**/
	public static void main(String[] args) {
		Board.printBoard();

		Player player = new Player(true);
		Player player2 = new Player(false);

		Scanner input = new Scanner(System.in);

		System.out.println("Type location of your piece (row, col) and the destination (row, col)");
		int i = 0;
		//loop goes indefinetly until king is dead and system exits
		//starts with uppercase letters, if move function returns true, increment I.
		//if I is even it allows uppercase letters to move, otherwise lowercase letters move.
		while (true) {
			if (i%2==0) {
				System.out.println("CAPITAL LETTER TURN");
				System.out.println("Enter row of piece: ");
				int row = input.nextInt();
				System.out.println("Enter col of piece: ");
				int col = input.nextInt();
				System.out.println("Enter row of new location: ");
				int endRow = input.nextInt();
				System.out.println("Enter col of new location: ");
				int endCol = input.nextInt();

				if (Move.move(player, row, col, endRow, endCol))
					i++;
			}

			if (i%2!=0) {
				System.out.println("LOWERCASE LETTER TURN");
				System.out.println("Enter row of piece: ");
				int row = input.nextInt();
				System.out.println("Enter col of piece: ");
				int col = input.nextInt();
				System.out.println("Enter row of new location: ");
				int endRow = input.nextInt();
				System.out.println("Enter col of new location: ");
				int endCol = input.nextInt();

				if (Move.move(player2, row, col, endRow, endCol))
					i++;
			}
		}

	}
}