package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Coin_Silver;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;

public class MON_Goblin extends Entity{
	
	GamePanel gp;

	public MON_Goblin(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Goblin";
		defaultSpeed = 3;
		speed = defaultSpeed;
		maxLife = 12;
		life = maxLife;
		attack = 4;
		defense = 1;
		exp = 20;
		knockBackPower = 1;
		
		solidArea.x = 5;
		solidArea.y = 18;
		solidArea.width = 30;
		solidArea.height = 40;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 30;
		attackArea.height = 40;
		motion1_duration = 5;
		motion2_duration = 25;
				
					
		getImage();
		getAttackImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/goblin_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/monster/goblin_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/monster/goblin_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/monster/goblin_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/monster/goblin_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/monster/goblin_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/monster/goblin_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/monster/goblin_right_2",gp.tileSize,gp.tileSize);
	}
	public void getAttackImage() {
		
		attackUp1 = setup("/monster/goblin_attack_up_1",gp.tileSize,gp.tileSize*2);
		attackUp2 = setup("/monster/goblin_attack_up_2",gp.tileSize,gp.tileSize*2);
		attackDown1 = setup("/monster/goblin_attack_down_1",gp.tileSize,gp.tileSize*2);
		attackDown2 = setup("/monster/goblin_attack_down_2",gp.tileSize,gp.tileSize*2);
		attackLeft1 = setup("/monster/goblin_attack_left_1",gp.tileSize*2,gp.tileSize);
		attackLeft2 = setup("/monster/goblin_attack_left_2",gp.tileSize*2,gp.tileSize);
		attackRight1 = setup("/monster/goblin_attack_right_1",gp.tileSize*2,gp.tileSize);
		attackRight2 = setup("/monster/goblin_attack_right_2",gp.tileSize*2,gp.tileSize);
	}
	public void setAction() {
		
		if(onPath == true) {
			
			// STOP CHASES IF 15 TILES AWAY
			checkStopChasingOrNot(gp.player, 10, 100);
			
			// Search for the direction	
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
			
		}
		else {
			// Check if it starts chasing
			checkStartChasingOrNot(gp.player, 10, 100); 
			
			// Get a random direction
			getRandomDirection(120);
		}
		if(attacking == false) {
			checkAttackOrNot(6, gp.tileSize*4, gp.tileSize*2);
		}
	}	
	public void damageReaction() {
		
		actionLockCounter = 0;
//		direction = gp.player.direction; // Move away from player
		onPath = true;
	}
	public void checkDrop() {
		
		// DROP BASED OFF OF NUMBER
		int i = new Random().nextInt(100)+1;
		
		// SET THE MONSTER DROP
		if(i<50) {
			dropCoins(1);
		}
		if(i>50 && i < 80) {
			dropCoins(2);
		}
		if(i>80 && i < 90) {
			dropItem(new OBJ_Potion_Red(gp));
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
	        dropItem(new OBJ_Coin_Silver(gp)); // Drop a silver coin
	        
	    }
	}
}
	