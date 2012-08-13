// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            Container, Slot, SlotCoiner, InventoryPlayer, 
//            TileEntityCoiner, ICrafting, ItemStack, EntityPlayer

public class ContainerCoiner extends Container
{

    private TileEntityCoiner coiner;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCoiner(InventoryPlayer inventoryplayer, TileEntityCoiner tileentitycoiner)
    {
        lastCookTime = 0;
        lastBurnTime = 0;
        lastItemBurnTime = 0;
        coiner = tileentitycoiner;
        addSlot(new Slot(tileentitycoiner, 0, 32, 35));
        addSlot(new Slot(tileentitycoiner, 1, 56, 16));
        addSlot(new Slot(tileentitycoiner, 2, 56, 54));
        addSlot(new SlotCoiner(inventoryplayer.player, tileentitycoiner, 3, 116, 35));
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    public void updateCraftingResults()
    {
        super.updateCraftingResults();
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);
            if(lastCookTime != coiner.coinerCookTime)
            {
                icrafting.updateCraftingInventoryInfo(this, 0, coiner.coinerCookTime);
            }
            if(lastBurnTime != coiner.coinerBurnTime)
            {
                icrafting.updateCraftingInventoryInfo(this, 1, coiner.coinerBurnTime);
            }
            if(lastItemBurnTime != coiner.currentItemBurnTime)
            {
                icrafting.updateCraftingInventoryInfo(this, 2, coiner.currentItemBurnTime);
            }
        }

        lastCookTime = coiner.coinerCookTime;
        lastBurnTime = coiner.coinerBurnTime;
        lastItemBurnTime = coiner.currentItemBurnTime;
    }

    public void updateProgressBar(int i, int j)
    {
        if(i == 0)
        {
            coiner.coinerCookTime = j;
        }
        if(i == 1)
        {
            coiner.coinerBurnTime = j;
        }
        if(i == 2)
        {
            coiner.currentItemBurnTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return coiner.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 2)
            {
                if(!mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }
            } else
            if(i >= 3 && i < 30)
            {
                if(!mergeItemStack(itemstack1, 30, 39, false))
                {
                    return null;
                }
            } else
            if(i >= 30 && i < 39)
            {
                if(!mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            } else
            if(!mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }
}
