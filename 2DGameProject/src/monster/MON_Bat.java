package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_Bat extends Entity{
	
	GamePanel gp;

	public MON_Bat(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Bat";
		defaultSpeed = 4;
		speed = defaultSpeed;
		maxLife = 5;
		life = maxLife;
		attack = 6;
		defense = 0;
		exp = 15;
		
		solidArea.x = 3;
		solidArea.y = 15;
		solidArea.width = 42;
		solidArea.height = 21;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getImage() {
		
		up1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
		up2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
		down1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
		left2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
		right1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
		right2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
	}

	public void setAction() {
		
		if(onPath == true) {
//			
//			// STOP CHASES IF 15 TILES AWAY
//			checkStopChasingOrNot(gp.player, 25, 100);
//			
//			// Search for the direction	
//			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
//			
		}
		else {
//			// Check if it starts chasing
//			checkStartChasingOrNot(gp.player, 5, 100); 
			
			// Get a random direction
			getRandomDirection(12);
		}
	}	
	public void damageReaction() {
		
		actionLockCounter = 0;
//		direction = gp.player.direction; // Move away from player
//		onPath = true;
	}
	public void checkDrop() {
		
		// DROP BASED OFF OF NUMBER
		int i = new Random().nextInt(100)+1;
		
		// SET THE MONSTER DROP
		if(i<30) {
			dropCoins2(5);
		}
		if(i>30 && i < 80) {
			dropCoins(1);
		}
		if(i>80 && i < 90) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i>90 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
		
	}
	private void dropCoins(int quantity) {
	    for (int count = 0; count < quantity; count++) {
	        dropItem(new OBJ_Coin_Gold(gp)); // Drop a gold coin
	    }
	}
	private void dropCoins2(int quantity) {
	    for (int count = 0; count < quantity; count++) {
	        dropItem(new OBJ_Coin_Silver(gp)); // Drop a gold coin
	        
	    }
	}

}