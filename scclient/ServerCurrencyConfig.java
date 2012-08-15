package net.minecraft.src;

import java.io.File;
import java.io.IOException;
 
import cpw.mods.fml.common.modloader.ModLoaderHelper;
 
import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.Configuration;


public class ServerCurrencyConfig 

{
	private static ServerCurrencyConfig instance;


//The forge / mod loader config object
private Configuration config;

//The Constructor for the Config object
//It is a private constructor this way only the Config object itself can
//Initialise itself
private ServerCurrencyConfig(String ServerCurrency)
{

	//First check to see if the config directory / file exists
	//We do this by trying to load the file and checking if it exists
	File configFile = new File(Minecraft.getMinecraftDir() + "/config/mod_ServerCurrency" + ServerCurrency +".cfg");

	//If the config file does not exist we will create one
	if(!configFile.exists())
	{
		try
		{
			configFile.createNewFile();
		}
		catch(IOException e)
		{
			System.out.println("Could not create configuration file for ServerCurrency" + ServerCurrency + ". Reason:");
			System.out.println(e);
			return;
		}
	}

	//Instance a forge config object and pass in the handle to our config file
	config = new Configuration(configFile);
	//load the data
	config.load();
}

//Starts up and initializes the our config object
public static void InitliazeConfig(String ServerCurrency)
{
	//Check to see if the config has already been initialized
	if(ServerCurrencyConfig.instance != null)
	{
		return;
	}

	//Initialize the config object
	ServerCurrencyConfig.instance = new ServerCurrencyConfig(ServerCurrency);
}
//Gets the Block ID for the given BlockName, you also pass a default ID
public static int GetBlockID(String coiner, int DefaultID) throws Exception
{
	if(ServerCurrencyConfig.instance == null)
	{
		throw new Exception("Server Currency Config has not be initalized!");
	}
	return ServerCurrencyConfig.instance.config.getOrCreateBlockIdProperty(coiner + ".id", DefaultID).getInt();
}

//Gets the Item ID for the given Item Name, you also pass a default ID
public static int GetItemID(String ItemName, int DefaultValue) throws Exception
{
	if(ServerCurrencyConfig.instance == null)
	{
		throw new Exception("Server Currency Config has not be initalized!");
	}
	return ServerCurrencyConfig.instance.config.getOrCreateIntProperty(ItemName + ".id", Configuration.CATEGORY_ITEM, DefaultValue).getInt();
}
//Saves the config file
public static void SaveConfig()
{
	ServerCurrencyConfig.instance.config.save();
}}