package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity{
	
	public static final String objName = "Blue Iron Shield";

	public OBJ_Shield_Blue(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = objName;
		down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
		defenseValue = 2;
		description = "[" + name + "]\nA new shiny blue shield.\n\n+2 Defense";
		price = 45;

	}

}
