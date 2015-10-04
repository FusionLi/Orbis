import java.util.*;
import java.awt.Point;

public class PlayerAI extends ClientAI {
	public boolean [] optionState = new boolean[4];
	Arrays.fill(optionState, Boolean.FALSE);
	public PlayerAI() {

		// Write your initialization here
	}

	@Override
	public Move getMove(Gameboard gameboard, Opponent opponent, Player player)
			throws NoItemException, MapOutOfBoundsException {

		int boardHeight = gameboard.getHeight();
		int boardWidth = gameboard.getWidth();
		
		int x = player.getX();
		int y = player.getY();
		
		optionState[0] = isBarrier(gameboard, x + 1, y);
		optionState[1] = isBarrier(gameboard, x, y + 1);
		optionState[2] = isBarrier(gameboard, x - 1, y);
		optionState[3] = isBarrier(gameboard, x, y - 1);
		
		if (optionState[0] == true) {
			if (player.getDirection() == Direction.RIGHT) {
				Arrays.fill(optionState, Boolean.FALSE);
				return Move.FORWARD;
			} else
				return Move.FACE_RIGHT;
		}
		else if (optionState[1] == true) {
			if (player.getDirection() == Direction.DOWN) {
				Arrays.fill(optionState, Boolean.FALSE);
				return Move.FORWARD;
			}else
				return Move.FACE_DOWN;
		}
		else if (optionState[2] == true) {
			if (player.getDirection() == Direction.LEFT) {
				Arrays.fill(optionState, Boolean.FALSE);
				return Move.FORWARD;
			}else
				return Move.FACE_LEFT;
		}
		else if (optionState[3] == true) {
			if (player.getDirection() == Direction.UP) {
				Arrays.fill(optionState, Boolean.FALSE);
				return Move.FORWARD;
			}else
				return Move.FACE_UP;
		}
		
		return null;
	}
	
	boolean isBarrier(Gameboard gameboard, int x, int y) throws MapOutOfBoundsException {
		// out of bound
		if (x < 0 || y < 0 || x > gameboard.getWidth() || y > gameboard.getHeight()) {
			return false;
		}
		if (!gameboard.isWallAtTile(x,y) && !gameboard.isTurretAtTile(x,y))
			return true;
		else
			return false;
	}

}
