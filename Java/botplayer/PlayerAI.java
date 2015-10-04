import java.util.*;
import java.awt.Point;

public class PlayerAI extends ClientAI {
	int number;
	int counter;
	int[] point = new int[2];
	public PlayerAI() {
		// Write your initialization here
	}

	@Override
	public Move getMove(Gameboard gameboard, Opponent opponent, Player player)
			throws NoItemException, MapOutOfBoundsException {
		number = 0;
		counter = 0;
		
		int x = player.getX();
		int y = player.getY();
		
		
		if (ToMove(gameboard, opponent, player, x, y)) {
			point[0]=x;
			point[1]=y;
			
			if (number == 1) {
				if (player.getDirection() == Direction.RIGHT) {
					return Move.FORWARD; }
				else
					return Move.FACE_RIGHT;
			}
			if (number == 2) {
				if (player.getDirection() == Direction.LEFT){
					point[0]=x;
					point[1]=y;
					return Move.FORWARD;
				}
				else
					return Move.FACE_LEFT;
			}
			if (number == 3) {
				if (player.getDirection() == Direction.UP) {
					return Move.FORWARD;}
				else
					return Move.FACE_UP;
			}
			if (number == 4) {
				if (player.getDirection() == Direction.DOWN) {
					return Move.FORWARD;}
				else
					return Move.FACE_DOWN;
			}
			return Move.FORWARD;
			
		}
		return Move.FORWARD;
	}

	public boolean ToMove(Gameboard gameboard, Opponent opponent,
			Player player, int x, int y) throws NoItemException,
			MapOutOfBoundsException {
		
		if(counter >0){
			if (gameboard.isWallAtTile(x, y) == false &&gameboard.areBulletsAtTile(x, y) == false &&NoLaserRisk(gameboard, x, y) == true)
				return true;
			else 
				return false;
		}
		
		counter++;
		 if (ToMove(gameboard, opponent, player, player.getX() + 1,
				player.getY())) {
			 if(player.getX()+1 == point[0]&& player.getY() == point[1])
				 return false;
			 else{
				 	number = 1;
				 	return true;
			 }
		}
		 else if (ToMove(gameboard, opponent, player, player.getX(),
					player.getY() + 1)) {
			 if(player.getX() == point[0]&& player.getY()+1 == point[1])
				 return false;
			 else{
				 	number = 4;
				 	return true;
			}
		 }
		 
		 else  if (ToMove(gameboard, opponent, player, player.getX() - 1,
				player.getY())) {
			 if(player.getX()-1 == point[0]&& player.getY() == point[1])
				 return false;
			 else{
				 	number = 2;
					return true;
			 }
		}
		 else if (ToMove(gameboard, opponent, player, player.getX(),
				player.getY() - 1)) {
			 if(player.getX() == point[0]&& player.getY()-1 == point[1])
				 return false;
			 else{
				 	number = 3;
					return true;
			 }
		}
		
		return true;
	}

	public boolean NoLaserRisk(Gameboard gameboard, int x, int y) {
		ArrayList<Turret> turrets = gameboard.getTurrets();
		for (int i = 0; i < turrets.size(); i++) {
			if (x == turrets.get(i).getX() || y == turrets.get(i).getY()) {
				if (turrets.get(i).didFire() || turrets.get(i).isFiringNextTurn()){
						if(turrets.get(i).getCooldownTime()>1)
								return false;
						else
								return true;
			}
		}
	}
		return true;
	}
}

/*
 * else if(gameboard.getBullets() != null){ if(player.getDirection() ==
 * gameboard.getBullets().get(0).getDirection()) return false;
 * 
 * } if(player.getDirection() == opponent.getDirection())//need to modify later
 * return false; if(gameboard.isTurretAtTile(x,y)) return false;
 */

/*else if (player.getDirection() == gameboard.getBullets().get(0)
.getDirection())
return false;
}*/

/*	if (player.getY() - 1 < 0)
		{
			return false;
		}
		
		if (player.getX() + 1 >= gameboard.getWidth())
		{
			return false;
		}*/
