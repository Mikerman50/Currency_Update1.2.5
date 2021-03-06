// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.*;
import net.minecraft.src.forge.Configuration;
import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.io.IOException;
import cpw.mods.fml.common.modloader.ModLoaderHelper;
import java.lang.reflect.Field;
import java.net.*;




public class mod_ServerCurrency extends NetworkMod
{
    public static String modName = "Server Currency";
    public static String versionNumber = "0.52 MCPC";
    public static Logger logger = Logger.getLogger("Minecraft");
    public static mod_ServerCurrency instance;
    public static int stoneBlankID;
    public static int ironBlockID;
    public static int goldBlankID;
    public static int quincunxID;
    public static int denariID;
    public static int aureusID;
    public static int woodDieID;
    public static int stoneDieID;
    public static int ironDieID;
    public static int diamondDieID;
    public static int coinerID = 200;
 //   public static int coinerGUI = 98;
    public static final Item stoneBlank;
    public static final Item ironBlank;
    public static final Item goldBlank;
    public static final Item dupondius;
    public static final Item denarius;
    public static final Item aureus;
    public static final Item woodDie;
    public static final Item stoneDie;
    public static final Item ironDie;
    public static final Item diamondDie;
    public static final Block coiner;
    
    public String Version()
    {
        return versionNumber;
    }
    public static final Achievement currency;
    public static int coinfront = ModLoader.addOverride("/terrain.png", "/ServerCurrency/Coiner/coinidle.png");
        
      	public mod_ServerCurrency()
        {
         instance = this;
        }
      	public void load()
      	{
      
    {
        ModLoader.registerBlock(coiner);
        ModLoader.addName(dupondius, "Quincunx");
        ModLoader.addName(denarius, "Denarii");
        ModLoader.addName(aureus, "Aureus");
        ModLoader.addName(stoneBlank, "Stone Blank");
        ModLoader.addName(ironBlank, "Iron Blank");
        ModLoader.addName(goldBlank, "Gold Blank");
        ModLoader.addName(woodDie, "Wooden Die");
        ModLoader.addName(stoneDie, "Stone Die");
        ModLoader.addName(ironDie, "Iron Die");
        ModLoader.addName(diamondDie, "Diamond Die");
        ModLoader.addName(coiner, "Coining Mint");
        stoneBlank.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/blankbronze.png");
        ironBlank.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/blankiron.png");
        goldBlank.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/blankgold.png");
        dupondius.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/dupondius.png");
        denarius.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/denarius.png");
        aureus.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/aureus.png");
        woodDie.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/wooddie.png");
        stoneDie.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/stonedie.png");
        ironDie.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/irondie.png");
        diamondDie.iconIndex = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/diamonddie.png");
        ModLoader.addRecipe(new ItemStack(coiner, 1), new Object[] {
            "WDW", "IPI", "III", Character.valueOf('I'), Item.ingotIron, Character.valueOf('W'), Block.planks, Character.valueOf('P'), Block.pistonBase, Character.valueOf('D'), 
            ironDie
        });
        ModLoader.addRecipe(new ItemStack(stoneBlank, 1), new Object[] {
            " C ", "C C", " C ", Character.valueOf('C'), Block.cobblestone
        });
        ModLoader.addRecipe(new ItemStack(ironBlank, 1), new Object[] {
            " I ", "I I", " I ", Character.valueOf('I'), Item.ingotIron
        });
        ModLoader.addRecipe(new ItemStack(goldBlank, 1), new Object[] {
            " G ", "G G", " G ", Character.valueOf('G'), Item.ingotGold
        });
        ModLoader.addRecipe(new ItemStack(woodDie, 1), new Object[] {
            "# #", " # ", "# #", Character.valueOf('#'), Block.planks
        });
        ModLoader.addRecipe(new ItemStack(stoneDie, 1), new Object[] {
            "# #", " # ", "# #", Character.valueOf('#'), Block.cobblestone
        });
        ModLoader.addRecipe(new ItemStack(stoneDie, 3), new Object[] {
            "# #", " # ", "# #", Character.valueOf('#'), Block.stone
        });
        ModLoader.addRecipe(new ItemStack(ironDie, 1), new Object[] {
            "   ", "###", "   ", Character.valueOf('#'), Item.ingotIron
        });
        ModLoader.addRecipe(new ItemStack(diamondDie, 1), new Object[] {
            "# #", " # ", "# #", Character.valueOf('#'), Item.diamond
        });
        ModLoader.addAchievementDesc(currency, "SMP Currency!", "Get Paid.");
        ModLoader.registerTileEntity(TileEntityCoiner.class, "Coiner");
        MinecraftForge.setGuiHandler(instance, new GuiHandlerCurrency());}
 //       ModLoader.RegisterGUI(this, coinerGUI);
    }

    public void OnItemPickup(EntityPlayer entityplayer, ItemStack itemstack)
    {
        if(itemstack.itemID == dupondius.shiftedIndex || itemstack.itemID == denarius.shiftedIndex || itemstack.itemID == aureus.shiftedIndex)
        {
            entityplayer.addStat(currency, 1);
        }
    }

    public static ItemStack getOutPut(int i, int j)
    {
        if(i == stoneBlank.shiftedIndex && (j == woodDie.shiftedIndex || j == stoneDie.shiftedIndex || j == ironDie.shiftedIndex || j == diamondDie.shiftedIndex))
        {
            return new ItemStack(dupondius, 1);
        } else
        {
            return null;
        }
    }


    public String getVersion()
    {
        return versionNumber;
    }

    static 
    {
        stoneBlankID = 10800;
        ironBlockID = 10810;
        goldBlankID = 10820;
        quincunxID = 10830;
        denariID = 10840;
        aureusID = 10850;
        woodDieID = 10860;
        stoneDieID = 10870;
        ironDieID = 10880;
        diamondDieID = 10890;
        stoneBlank = (new Item(stoneBlankID + 256)).setItemName("stoneBlank");
        ironBlank = (new Item(ironBlockID + 256)).setItemName("ironBlank");
        goldBlank = (new Item(goldBlankID + 256)).setItemName("goldBlank");
        dupondius = (new Item(quincunxID + 256)).setItemName("dupondius");
        denarius = (new Item(denariID + 256)).setItemName("denarius");
        aureus = (new Item(aureusID + 256)).setItemName("aureus");
        woodDie = (new Item(woodDieID + 256)).setItemName("woodDie").setMaxStackSize(1).setMaxDamage(7);
        stoneDie = (new Item(stoneDieID + 256)).setItemName("stoneDie").setMaxStackSize(1).setMaxDamage(15);
        ironDie = (new Item(ironDieID + 256)).setItemName("ironDie").setMaxStackSize(1).setMaxDamage(31);
        diamondDie = (new Item(diamondDieID + 256)).setItemName("diamondDie").setMaxStackSize(1).setMaxDamage(127);
        currency = (new Achievement(3001, "currency", -3, 1, aureus, null)).registerAchievement();
        coiner = (new BlockCoiner(200, false)).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setBlockName("coiner");
    }
public boolean clientSideRequired() 
{
return true;
}

public boolean serverSideRequired() 
{
return false;
}}
