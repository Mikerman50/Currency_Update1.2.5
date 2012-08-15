// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            TileEntity, IInventory, ItemStack, NBTTagCompound, 
//            NBTTagList, World, Item, CoinerRecipes, 
//            EntityLiving, Block, Material, EntityPlayer, 
//            Entity

public class TileEntityCoiner extends TileEntity
    implements IInventory
{

    private ItemStack coinerItemStacks[];
    public int coinerBurnTime;
    public int currentItemBurnTime;
    public int coinerCookTime;
    public Entity entity;
    public EntityPlayer player;

    public TileEntityCoiner()
    {
        coinerItemStacks = new ItemStack[4];
        coinerBurnTime = 0;
        currentItemBurnTime = 0;
        coinerCookTime = 0;
    }

    public int getSizeInventory()
    {
        return coinerItemStacks.length;
    }

    public ItemStack getStackInSlot(int i)
    {
        return coinerItemStacks[i];
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(coinerItemStacks[i] != null)
        {
            if(coinerItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = coinerItemStacks[i];
                coinerItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = coinerItemStacks[i].splitStack(j);
            if(coinerItemStacks[i].stackSize == 0)
            {
                coinerItemStacks[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        coinerItemStacks[i] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "Coiner";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        coinerItemStacks = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if(byte0 >= 0 && byte0 < coinerItemStacks.length)
            {
                coinerItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        coinerBurnTime = nbttagcompound.getShort("BurnTime");
        coinerCookTime = nbttagcompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(coinerItemStacks[2]);
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short)coinerBurnTime);
        nbttagcompound.setShort("CookTime", (short)coinerCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < coinerItemStacks.length; i++)
        {
            if(coinerItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                coinerItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int i)
    {
        return (coinerCookTime * i) / 200;
    }

    public int getBurnTimeRemainingScaled(int i)
    {
        if(currentItemBurnTime == 0)
        {
            currentItemBurnTime = 200;
        }
        return (coinerBurnTime * i) / currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return coinerBurnTime > 0;
    }

    public void updateEntity()
    {
        boolean flag = coinerBurnTime > 0;
        boolean flag1 = false;
        if(coinerBurnTime > 0)
        {
            coinerBurnTime--;
        }
        if(!worldObj.isRemote)
        {
            if(coinerBurnTime == 0 && canSmelt())
            {
                currentItemBurnTime = coinerBurnTime = getItemBurnTime(coinerItemStacks[2]);
                if(coinerBurnTime > 0)
                {
                    flag1 = true;
                    if(coinerItemStacks[2] != null)
                    {
                        if(coinerItemStacks[2].getItem().hasContainerItem())
                        {
                            coinerItemStacks[2] = new ItemStack(coinerItemStacks[2].getItem().getContainerItem());
                        } else
                        {
                            coinerItemStacks[2].stackSize--;
                        }
                        if(coinerItemStacks[2].stackSize == 0)
                        {
                            coinerItemStacks[2] = null;
                        }
                    }
                }
            }
            if(isBurning() && canSmelt())
            {
                coinerCookTime++;
                if(coinerCookTime == 200)
                {
                    coinerCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            } else
            {
                coinerCookTime = 0;
            }
            if(flag != (coinerBurnTime > 0))
            {
                flag1 = true;
            }
        }
        if(flag1)
        {
            onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if(coinerItemStacks[0] == null || coinerItemStacks[1] == null)
        {
            return false;
        }
        ItemStack itemstack = CoinerRecipes.getSmeltingResult(coinerItemStacks[0].getItem().shiftedIndex, coinerItemStacks[1].getItem().shiftedIndex);
        if(itemstack == null)
        {
            return false;
        }
        if(coinerItemStacks[3] == null)
        {
            return true;
        }
        if(!coinerItemStacks[3].isItemEqual(itemstack))
        {
            return false;
        }
        if(coinerItemStacks[3].stackSize < getInventoryStackLimit() && coinerItemStacks[3].stackSize < coinerItemStacks[1].getMaxStackSize())
        {
            return true;
        } else
        {
            return coinerItemStacks[3].stackSize < itemstack.getMaxStackSize();
        }
    }

    public void smeltItem()
    {
        if(!canSmelt())
        {
            return;
        }
        ItemStack itemstack = CoinerRecipes.getSmeltingResult(coinerItemStacks[0].getItem().shiftedIndex, coinerItemStacks[1].getItem().shiftedIndex);
        if(coinerItemStacks[3] == null)
        {
            coinerItemStacks[3] = itemstack.copy();
        } else
        if(coinerItemStacks[3].itemID == itemstack.itemID)
        {
            coinerItemStacks[3].stackSize++;
        }
        for(int i = 0; i < 2; i++)
        {
            if(coinerItemStacks[i].getItem().hasContainerItem())
            {
                if(i == 1 && coinerItemStacks[1].getItem().isDamageable() && coinerItemStacks[1].getItem().getMaxDamage() > coinerItemStacks[1].getItemDamage())
                {
                    coinerItemStacks[1].damageItem(1, (EntityLiving)entity);
                } else
                {
                    coinerItemStacks[i] = new ItemStack(coinerItemStacks[i].getItem().getContainerItem());
                }
            } else
            {
                coinerItemStacks[i].stackSize--;
            }
            if(coinerItemStacks[i].stackSize > 0)
            {
                continue;
            }
            if(i == 1 && coinerItemStacks[1].getItem().isDamageable() && coinerItemStacks[1].getItem().getMaxDamage() > coinerItemStacks[1].getItemDamage())
            {
                coinerItemStacks[1].damageItem(1, (EntityLiving)entity);
            } else
            {
                coinerItemStacks[i] = null;
            }
        }

    }

    private int getItemBurnTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().shiftedIndex;
        if(i < 256 && Block.blocksList[i].blockMaterial == Material.wood)
        {
            return 300;
        }
        if(i == Item.stick.shiftedIndex)
        {
            return 100;
        }
        if(i == Item.coal.shiftedIndex)
        {
            return 1600;
        }
        if(i == Item.bucketLava.shiftedIndex)
        {
            return 20000;
        }
        return i != Block.sapling.blockID ? 0 : 100;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        } else
        {
            return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
        }
    }

    public void closeChest()
    {
    }

    public void openChest()
    {
    }

    @Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		
		 return this.coinerItemStacks[var1];
	}
}
