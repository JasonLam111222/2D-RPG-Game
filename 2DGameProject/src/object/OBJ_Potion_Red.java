package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
	
	GamePanel gp;
	
	public static final String objName = "HP Potion";

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		value = 6;
		down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
		description = "[" + name + "]\nA potion that is more\nvaluable than you ever\nwill be\n\n+"+ value + "HP";
		price = 10;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You drank the "+ name + "!\n"
				+"Your HP has replenished!";
	}
	public boolean use(Entity entity) {
		
		startDialogue(this,0);
		entity.life += value;

		gp.playSE(2);
		return true;
	}

}
