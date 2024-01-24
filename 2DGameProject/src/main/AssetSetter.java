package main;

import java.awt.geom.AffineTransform;

import data.Progress;
import entity.NPC_BigRock;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_Bat;
import monster.MON_Goblin;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import monster.MON_RedSlime;
import monster.MON_SkeletonLord;
import object.OBJ_Axe;
import object.OBJ_BattleAxe;
import object.OBJ_Boots;
import object.OBJ_Chalice;
import object.OBJ_Chest;
import object.OBJ_Coin_Gold;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Tent;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*41;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Axe(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*31;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*6;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*4;
		gp.obj[mapNum][i].worldY = gp.tileSize*46;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*41;
		i++;
		
		mapNum = 2;
		i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*13;
		gp.obj[mapNum][i].worldY = gp.tileSize*34;
		i++;
		
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*35;
		gp.obj[mapNum][i].worldY = gp.tileSize*13;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_BattleAxe(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*8;
		gp.obj[mapNum][i].worldY = gp.tileSize*19;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*42;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*32;
		gp.obj[mapNum][i].worldY = gp.tileSize*30;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize*9;
		gp.obj[mapNum][i].worldY = gp.tileSize*41;
		i++;
		
//		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize*18;
//		gp.obj[mapNum][i].worldY = gp.tileSize*23;
//		i++;
		
		mapNum = 3;
		i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*15;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chalice(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*8;
		i++;

	}
	public void setNPC() {
		
		int mapNum = 0;
		int i = 0;
		
		// MAP 0
		gp.npc[mapNum][i] = new NPC_OldMan(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*9;
		gp.npc[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		// MAP 1
		mapNum = 1;
		i = 0;
		
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*24;
		gp.npc[mapNum][i].worldY = gp.tileSize*17;
		i++;
		
		// DUNGEON 1
		mapNum = 2;
		i = 0;
		
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*20;
		gp.npc[mapNum][i].worldY = gp.tileSize*20;
		i++;
		
	}
	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*19;
		gp.monster[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*20;
		gp.monster[mapNum][i].worldY = gp.tileSize*26;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*4;
		gp.monster[mapNum][i].worldY = gp.tileSize*45;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*4;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*11;
		gp.monster[mapNum][i].worldY = gp.tileSize*39;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*12;
		gp.monster[mapNum][i].worldY = gp.tileSize*46;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*12;
		gp.monster[mapNum][i].worldY = gp.tileSize*7;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*9;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*20;
		gp.monster[mapNum][i].worldY = gp.tileSize*5;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*15;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*25;
		gp.monster[mapNum][i].worldY = gp.tileSize*15;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*35;
		gp.monster[mapNum][i].worldY = gp.tileSize*16;		
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*43;
		gp.monster[mapNum][i].worldY = gp.tileSize*12;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*43;
		gp.monster[mapNum][i].worldY = gp.tileSize*26;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*41;
		gp.monster[mapNum][i].worldY = gp.tileSize*18;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*22;
		gp.monster[mapNum][i].worldY = gp.tileSize*6;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*32;
		gp.monster[mapNum][i].worldY = gp.tileSize*36;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*42;
		gp.monster[mapNum][i].worldY = gp.tileSize*10;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*43;
		gp.monster[mapNum][i].worldY = gp.tileSize*17;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*28;
		gp.monster[mapNum][i].worldY = gp.tileSize*44;		
		i++;
		
//		mapNum = 1;
//		i = 0;
//		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
//		gp.monster[mapNum][i].worldX = gp.tileSize*38;
//		gp.monster[mapNum][i].worldY = gp.tileSize*42;		
//		i++;
		
		mapNum = 2;
		i = 0;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*33;
		gp.monster[mapNum][i].worldY = gp.tileSize*13;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*36;
		gp.monster[mapNum][i].worldY = gp.tileSize*15;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*29;
		gp.monster[mapNum][i].worldY = gp.tileSize*22;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*31;		
		i++;
		
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*22;
		gp.monster[mapNum][i].worldY = gp.tileSize*35;		
		i++;
		
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*13;		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*36;
		gp.monster[mapNum][i].worldY = gp.tileSize*17;		
		i++;
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*15;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*14;
		gp.monster[mapNum][i].worldY = gp.tileSize*7;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*8;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*35;
		gp.monster[mapNum][i].worldY = gp.tileSize*7;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*8;
		gp.monster[mapNum][i].worldY = gp.tileSize*13;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*16;
		gp.monster[mapNum][i].worldY = gp.tileSize*19;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*41;
		gp.monster[mapNum][i].worldY = gp.tileSize*33;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*35;
		gp.monster[mapNum][i].worldY = gp.tileSize*39;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*26;
		gp.monster[mapNum][i].worldY = gp.tileSize*24;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*25;
		gp.monster[mapNum][i].worldY = gp.tileSize*40;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*10;
		gp.monster[mapNum][i].worldY = gp.tileSize*40;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*19;
		gp.monster[mapNum][i].worldY = gp.tileSize*41;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*15;
		gp.monster[mapNum][i].worldY = gp.tileSize*16;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*19;
		gp.monster[mapNum][i].worldY = gp.tileSize*33;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*20;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*26;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*41;
		gp.monster[mapNum][i].worldY = gp.tileSize*21;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*40;
		gp.monster[mapNum][i].worldY = gp.tileSize*29;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*33;
		gp.monster[mapNum][i].worldY = gp.tileSize*26;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*30;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*39;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*22;
		gp.monster[mapNum][i].worldY = gp.tileSize*32;		
		i++;
		
		gp.monster[mapNum][i] = new MON_Goblin(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*13;
		gp.monster[mapNum][i].worldY = gp.tileSize*36;		
		i++;
		
		mapNum = 3;
		i = 0;
		
		if(Progress.skeletonLordDefeated == false) {
			gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
			gp.monster[mapNum][i].worldX = gp.tileSize*23;
			gp.monster[mapNum][i].worldY = gp.tileSize*16;		
			i++;
		}
	}
	public void setInteractiveTile() {

		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 19, 42);
		i++;
		gp.iTile[mapNum][i] = new IT_DryTree(gp, 37, 40);
		i++;

		
		// DUNGEON 1
		
		mapNum = 2;
		i = 0;
		
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 9);
		i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 9);
		i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 12, 11);
		i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 13, 12);
		i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 24, 16);
		i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 30, 16);
		i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 28, 24);
		i++;

	}

}





















