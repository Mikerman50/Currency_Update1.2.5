// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockContainer, Material, mod_ServerCurrency, Block, 
//            World, TileEntityCoiner, ContainerCoiner, EntityPlayer, 
//            ModLoader, EntityLiving, MathHelper, ItemStack, 
//            EntityItem, TileEntity

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
         }
        return true;
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
     TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.getBlockTileEntity(i, j, k);

        if (tileentitycoiner != null)
        {
            for (int l = 0; l < tileentitycoiner.getSizeInventory(); ++l)
            {
                ItemStack itemstack = tileentitycoiner.getStackInSlot(l);

                if (itemstack != null)
                {
                    float f = this.coinerRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.coinerRand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = this.coinerRand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int i1 = this.coinerRand.nextInt(21) + 10;

                        if (i1 > itemstack.stackSize)
                        {
                        	i1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= i1;
                        entityitem = new EntityItem(world, (double)((float)i + f), (double)((float)j + f1), (double)((float)k + f2), new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.coinerRand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.coinerRand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.coinerRand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                        	entityitem.item.setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }
        }

        super.onBlockRemoval(world, i, j, k);
    }}