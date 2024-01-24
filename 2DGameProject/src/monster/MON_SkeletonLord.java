package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Gold;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_SkeletonLord extends Entity{
	
	GamePanel gp;
	public static final String monName = "Death Knight";

	public MON_SkeletonLord(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		boss = true;
		name = monName;
		defaultSpeed = 1; 
		speed = defaultSpeed;
		maxLife = 250;
		life = maxLife;
		attack = 14;
		defense = 4;
		exp = 100;
		knockBackPower = 5;
		sleep = true;
		
		
		int size = gp.tileSize*5;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 170;
		attackArea.height = 170;
		
		motion1_duration = 25;
		motion2_duration = 50;
						
		
		getImage();
		getAttackImage();
		setDialogue();

	}
	public void getImage() {
		
		int i = 5;
		
		if(inRage == false) {
			
			up1 = setup("/monster/skeletonlord_up_1",gp.tileSize*i,gp.tileSize*i);
			up2 = setup("/monster/skeletonlord_up_2",gp.tileSize*i,gp.tileSize*i);
			down1 = setup("/monster/skeletonlord_down_1",gp.tileSize*i,gp.tileSize*i);
			down2 = setup("/monster/skeletonlord_down_2",gp.tileSize*i,gp.tileSize*i);
			left1 = setup("/monster/skeletonlord_left_1",gp.tileSize*i,gp.tileSize*i);
			left2 = setup("/monster/skeletonlord_left_2",gp.tileSize*i,gp.tileSize*i);
			right1 = setup("/monster/skeletonlord_right_1",gp.tileSize*i,gp.tileSize*i);
			right2 = setup("/monster/skeletonlord_right_2",gp.tileSize*i,gp.tileSize*i);
		}
		if(inRage == true) {
			
			up1 = setup("/monster/skeletonlord_phase2_up_1",gp.tileSize*i,gp.tileSize*i);
			up2 = setup("/monster/skeletonlord_phase2_up_2",gp.tileSize*i,gp.tileSize*i);
			down1 = setup("/monster/skeletonlord_phase2_down_1",gp.tileSize*i,gp.tileSize*i);
			down2 = setup("/monster/skeletonlord_phase2_down_2",gp.tileSize*i,gp.tileSize*i);
			left1 = setup("/monster/skeletonlord_phase2_left_1",gp.tileSize*i,gp.tileSize*i);
			left2 = setup("/monster/skeletonlord_phase2_left_2",gp.tileSize*i,gp.tileSize*i);
			right1 = setup("/monster/skeletonlord_phase2_right_1",gp.tileSize*i,gp.tileSize*i);
			right2 = setup("/monster/skeletonlord_phase2_right_2",gp.tileSize*i,gp.tileSize*i);
		}
	}
	
	public void getAttackImage() {
		
		int i = 5;
		
		if(inRage == false) {
			
			attackUp1 = setup("/monster/skeletonlord_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
			attackUp2 = setup("/monster/skeletonlord_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
			attackDown1 = setup("/monster/skeletonlord_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
			attackDown2 = setup("/monster/skeletonlord_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
			attackLeft1 = setup("/monster/skeletonlord_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
			attackLeft2 = setup("/monster/skeletonlord_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
			attackRight1 = setup("/monster/skeletonlord_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
			attackRight2 = setup("/monster/skeletonlord_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
		}
		if(inRage == true) {
			
			attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1",gp.tileSize*i,gp.tileSize*i*2);
			attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2",gp.tileSize*i,gp.tileSize*i*2);
			attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1",gp.tileSize*i,gp.tileSize*i*2);
			attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2",gp.tileSize*i,gp.tileSize*i*2);
			attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1",gp.tileSize*i*2,gp.tileSize*i);
			attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2",gp.tileSize*i*2,gp.tileSize*i);
			attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1",gp.tileSize*i*2,gp.tileSize*i);
			attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2",gp.tileSize*i*2,gp.tileSize*i);
		}
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Foolish mortal! Do you really believe you could challenge my\nmight?";
		dialogues[0][1] = "Tremble before my eternal power!";
		dialogues[0][2] = "Witness the futility of your resistance!";
	}

	public void setAction() {
		
		if(inRage == false && life < maxLife/2) {
			inRage = true;
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack += 6;
			gp.stopMusic();
			gp.playMusic(24);
			
			
		}
		
		if(getTileDistance(gp.player) < 10 ) {
			
			moveTowardPlayer(60);
		}
		else {
			// Get a random direction
			getRandomDirection(100);
		}
		
		// Check if it attacks
		if(attacking == false) {
			checkAttackOrNot(60, gp.tileSize*10, gp.tileSize*5);
		}
	}	
	public void damageReaction() {
		
		actionLockCounter = 0;

	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.skeletonLordDefeated = true;
		
		// Restore the previous music
		gp.stopMusic();
		gp.playMusic(20);
		
		// Remove the iron doors
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
				gp.playSE(22);
				gp.obj[gp.currentMap][i] = null;
			}
		}
		
	}

}
