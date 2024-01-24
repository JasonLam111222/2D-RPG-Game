package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_BattleAxe;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity{

	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		// NPC HITBOX 	
		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y; 
		
		getImage();
		setDialogue();
		setItems();
	}
	public void getImage() {
		
		up1 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		up2 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		down1 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		down2 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		left1 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		left2 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		right1 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		right2 = setup("/npc/merchant",gp.tileSize,gp.tileSize);
		
	}
	public void setDialogue() {
		dialogues[0][0] = "Unknown: Oho, so you made it here. I have some pretty good\nitems for sale.";
		dialogues[1][0] = "See you later...";
		dialogues[2][0] = "You do not have enough gold!";
		dialogues[3][0] = "Your inventory is full!";
		dialogues[4][0] = "You cannot sell an equipped item!";
				
	}
	public void setItems() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Shield_Blue(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
		inventory.add(new OBJ_Lantern(gp));
		inventory.add(new OBJ_Tent(gp));
//		inventory.add(new OBJ_Pickaxe(gp));
	}
	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState; 
		gp.ui.npc = this;
	}
}
