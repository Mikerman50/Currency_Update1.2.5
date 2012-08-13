package net.minecraft.src;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandlerCurrency implements IGuiHandler
{
    public Object getGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6)
    {
             if (!var3.blockExists(var4, var5, var6))
             {
                 return null;
             }
             else
             {
                 switch (var1)
                 {
                     case 145:
                      TileEntityCoiner var12 = (TileEntityCoiner)var3.getBlockTileEntity(var4, var5, var6);
                         return new GuiCoiner(var2.inventory, var12);
                       

                     default:
                         return null;
                 }
             }
         }
}