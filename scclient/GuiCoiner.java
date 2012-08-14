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
        this.fontRenderer.drawString("Coining Mint", 60, 6, 0x404040);
        this.fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int var4 = this.mc.renderEngine.getTexture("/gui/coiner.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;
        if(coinerInventory.isBurning())
        {
            var7 = coinerInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 57, var6 + 45 + 3 - var7, 176, 12 - var7, 14, var7 + 2);
        }
        var7 = coinerInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(var5 + 79, var6 + 34, 176, 14, var7 + 1, 16);
    }}