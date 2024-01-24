package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
	
	public static final String objName = "Old Sword";

	public OBJ_Sword_Normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = objName;
		down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = 32;
		attackArea.height = 40;
		description = "[" + name + "]\nAn old sword. \n\n+1 Attack";
		price = 5;
		knockBackPower = 2;
		motion1_duration = 5;
		motion2_duration = 25;
	}

}
