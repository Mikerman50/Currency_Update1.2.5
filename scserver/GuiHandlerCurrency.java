package net.minecraft.src;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandlerCurrency implements IGuiHandler
{
    public Object getGuiElement(int var1, EntityPlayer player, World world, int i, int j, int k)
    {
             if (!world.blockExists(i, j, k))
             {
                 return null;
             }
             else
             {
                 switch (var1)
                 {
                     case 145:
                      TileEntityCoiner var12 = (TileEntityCoiner)world.getBlockTileEntity(i, j, k);
                         return new TileEntityCoiner(player.inventory, var12); //Problems with this line
                       

                     default:
                         return null;
                 }
             }
         }
}