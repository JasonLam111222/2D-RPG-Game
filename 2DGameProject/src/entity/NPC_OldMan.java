package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		// NPC HITBOX
		
		solidArea = new Rectangle(8, 16, 32, 32);		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y; 
		
		dialogueSet = -1;
				
		getImage();
		setDialogue();	
	}
	public void getImage() {
		
		up1 = setup("/npc/oldman_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/oldman_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/oldman_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/oldman_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/oldman_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/oldman_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/oldman_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/oldman_right_2",gp.tileSize,gp.tileSize);
		
	}
	public void setDialogue() {
		dialogues[0][0] = "Senile Sean: Why hello there young whippersnapper! Back\nin my day, the sky was green! Or was it my foot fungus...\n"
				+ "Hmmm... Aha! I remember! My foot fungus was purple!";
		dialogues[0][1] = "Senile Sean: HE HE HE HAW!";
		dialogues[0][2] = "Senile Sean: Praying at the shrines will give you a\n blessing from the Goddess!";
		
		dialogues[1][0] = "Senile Sean: You remind me of my pet rock. Great listener,\nthat rock. Never talked back, though.";
		
		dialogues[2][0] = "Senile Sean: Back in my day, we used to ride giant squirrels\n to school. Fast little critters, they were.";
		
		
	}
	public void setAction() {
		
		if(onPath == true) {
			
			// SET TARGET LOCATION
//			int goalCol = 12;
//			int goalRow = 9;
		
			// FOLLOW PLAYER
//			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
//			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
//			searchPath(goalCol, goalRow);
		}
		else {
			
			actionLockCounter ++;
			
			if(actionLockCounter == 120) {
				
				Random random = new Random();
				int i = random.nextInt(100)+1; // picks random number from 1-100
				
				if(i <= 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <- 75) {
					direction = "left";
					
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}
	}
	public void speak() {
			
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			
			dialogueSet--;
		}
		
		//onPath = true;
	}

}
