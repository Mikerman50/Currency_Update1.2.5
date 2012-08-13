// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.*;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, Material, mod_ServerCurrency, Block, 
//            World, IBlockAccess, TileEntityCoiner, ModLoader, 
//            GuiCoiner, EntityPlayerSP, EntityLiving, MathHelper, 
//            ItemStack, EntityItem, EntityPlayer, TileEntity

public class BlockCoiner extends BlockContainer
{

    private Random coinerRand;
    private final boolean isActive;
    private static boolean keepCoinerInventory = false;

    protected BlockCoiner(int i, boolean flag)
    {
        super(i, Material.rock);
        coinerRand = new Random();
        isActive = flag;
        blockIndexInTexture = 45;
    }

    public int idDropped(int i, Random random, int j)
    {
        return mod_ServerCurrency.coiner.blockID;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
        if(world.isRemote)
        {
            return;
        }
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
        {
            byte0 = 3;
        }
        if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
        {
            byte0 = 2;
        }
        if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
        {
            byte0 = 5;
        }
        if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
        {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }

    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(l == 1)
        {
            return blockIndexInTexture + 17;
        }
        if(l == 0)
        {
            return blockIndexInTexture + 17;
        }
        int i1 = iblockaccess.getBlockMetadata(i, j, k);
        if(l != i1)
        {
            return blockIndexInTexture;
        }
        if(isActive)
        {
            return blockIndexInTexture + 16;
        } else
        {
            return mod_ServerCurrency.coinfront;
        }
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 1)
        {
            return blockIndexInTexture + 17;
        }
        if(i == 0)
        {
            return blockIndexInTexture + 17;
        }
        if(i == 3)
        {
            return mod_ServerCurrency.coinfront;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.isRemote)
        {
            return true;
        }
        TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.getBlockTileEntity(i, j, k);
        if(tileentitycoiner != null)
        {
        	TileEntityCoiner var6 = (TileEntityCoiner)var1.getBlockTileEntity(var2, var3, var4);

            if (var6 != null)
            {
                var5.openGui(mod_ServerCurrency.instance, 145, var1, var2, var3, var4);
            }

        return true;
        }
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityCoiner();
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        if(!keepCoinerInventory)
        {
            TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.getBlockTileEntity(i, j, k);
            if(tileentitycoiner != null)
            {
label0:
                for(int l = 0; l < tileentitycoiner.getSizeInventory(); l++)
                {
                    ItemStack itemstack = tileentitycoiner.getStackInSlot(l);
                    if(itemstack == null)
                    {
                        continue;
                    }
                    float f = coinerRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = coinerRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = coinerRand.nextFloat() * 0.8F + 0.1F;
                    do
                    {
                        if(itemstack.stackSize <= 0)
                        {
                            continue label0;
                        }
                        int i1 = coinerRand.nextInt(21) + 10;
                        if(i1 > itemstack.stackSize)
                        {
                            i1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (float)coinerRand.nextGaussian() * f3;
                        entityitem.motionY = (float)coinerRand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)coinerRand.nextGaussian() * f3;
                        world.entityJoinedWorld(entityitem);
                    } while(true);
                }

            }
        }
        super.onBlockRemoval(world, i, j, k);
    }

}
