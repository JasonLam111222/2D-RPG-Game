package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{
	
	public static final String objName = "Old Pickaxe";

	public OBJ_Pickaxe(GamePanel gp) {
		super(gp);
		
		type = type_pickaxe;
		name = objName;
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = 32;
		attackArea.height = 30;
		description = "[" + name + "]\nIt's rusty, but it still\nworks. Can be used to\nmine certain walls.\n\n+1 Attack";
		price = 15;
		knockBackPower = 1;
		motion1_duration = 10;
		motion2_duration = 20;
	}

}


