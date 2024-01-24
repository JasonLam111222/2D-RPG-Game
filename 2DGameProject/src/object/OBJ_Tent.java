package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	
	public static final String objName = "Tent";
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[Tent]\nYou sleep through the\nwhole night. Restores\n8 HP & 4 Mana.\n\n*Single use*";
		price = 15;
		stackable = true;
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.sleepState;
		gp.playSE(15);
		gp.player.life = gp.player.life + 8;
		gp.player.mana = gp.player.mana + 4;
		gp.player.getSleepingImage(down1);
		return true;
	}

	GamePanel gp;

}
