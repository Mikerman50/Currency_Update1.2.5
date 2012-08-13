// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Slot, EntityPlayer, IInventory, ItemStack

public class SlotCoiner extends Slot
{

    private EntityPlayer thePlayer;

    public SlotCoiner(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
    {
        super(iinventory, i, j, k);
        thePlayer = entityplayer;
    }

    public boolean isItemValid(ItemStack itemstack)
    {
        return false;
    }
}
