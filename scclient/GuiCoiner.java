// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            GuiContainer, ContainerCoiner, FontRenderer, RenderEngine, 
//            TileEntityCoiner, InventoryPlayer

public class GuiCoiner extends GuiContainer
{

    private TileEntityCoiner coinerInventory;

    public GuiCoiner(InventoryPlayer inventoryplayer, TileEntityCoiner tileentitycoiner)
    {
        super(new ContainerCoiner(inventoryplayer, tileentitycoiner));
        coinerInventory = tileentitycoiner;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString("Coining Mint", 60, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        int k = mc.renderEngine.getTexture("/gui/coiner.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
        if(coinerInventory.isBurning())
        {
            int j1 = coinerInventory.getBurnTimeRemainingScaled(12);
            drawTexturedModalRect(j1 + 56, (i1 + 36 + 12) - j1, 176, 12 - j1, 14, j1 + 2);
        }
        int k1 = coinerInventory.getCookProgressScaled(24);
        drawTexturedModalRect(l + 79, i1 + 34, 176, 14, k1 + 1, 16);
    }
}
