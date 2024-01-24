package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;

public class MON_Orc extends Entity{
	
	GamePanel gp;

	public MON_Orc(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Orc";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 50;
		life = maxLife;
		attack = 9;
		defense = 3;
		exp = 50;
		knockBackPower = 4;
		
		solidArea.x = 4;
		solidArea.y = 4;
		solidArea.width = 76;
		solidArea.height = 82;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 84;
		attackArea.height = 84;
		motion1_duration = 35;
		motion2_duration = 70;
				
				
		
		
		getImage();
		getAttackImage();
	}
	public void getImage() {
		
		int i = 2;
		
		up1 = setup("/monster/orc_up_1",gp.tileSize*i,gp.tileSize*i);
		up2 = setup("/monster/orc_up_2",gp.tileSize*i,gp.tileSize*i);
		down1 = setup("/monster/orc_down_1",gp.tileSize*i,gp.tileSize*i);
		down2 = setup("/monster/orc_down_2",gp.tileSize*i,gp.tileSize*i);
		left1 = setup("/monster/orc_left_1",gp.tileSize*i,gp.tileSize*i);
		left2 = setup("/monster/orc_left_2",gp.tileSize*i,gp.tileSize*i);
		right1 = setup("/monster/orc_right_1",gp.tileSize*i,gp.tileSize*i);
		right2 = setup("/monster/orc_right_2",gp.tileSize*i,gp.tileSize*i);
	}
	public void getAttackImage() {
		
		int i = 2;
		
		attackUp1 = setup("/monster/orc_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
		attackUp2 = setup("/monster/orc_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
		attackDown1 = setup("/monster/orc_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
		attackDown2 = setup("/monster/orc_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
		attackLeft1 = setup("/monster/orc_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
		attackLeft2 = setup("/monster/orc_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
		attackRight1 = setup("/monster/orc_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
		attackRight2 = setup("/monster/orc_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
	}

	public void setAction() {
		
//		if(onPath == true) {
//			
//			// STOP CHASES IF 15 TILES AWAY
//			checkStopChasingOrNot(gp.player, 15, 100);
//			
//			// Search for the direction	
//			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
//			
//		}
		if(getTileDistance(gp.player) < 10 ) {
			
			moveTowardPlayer(60);
		}
		else {
			// Check if it starts chasing
//			checkStartChasingOrNot(gp.player, 50, 100); 
			
			// Get a random direction
			getRandomDirection(100);
		}
		
		// Check if it attacks
		if(attacking == false) {
			checkAttackOrNot(10, gp.tileSize*4, gp.tileSize*2);
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
		if(i<40) {
			dropCoins(2);
		}
		if(i>40 && i < 80) {
			dropCoins(3);
		}
		if(i>80 && i < 100) {
			dropItem(new OBJ_Potion_Red(gp));
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

