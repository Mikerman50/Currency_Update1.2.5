// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;

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
            ModLoader.openGUI(ModLoader.getMinecraftInstance().thePlayer, new GuiCoiner(ModLoader.getMinecraftInstance().thePlayer.inventory, tileentitycoiner));
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

    public void onBlockRemoval(World var1, int var2, int var3, int var4)
    {
     TileEntityCoiner var5 = (TileEntityCoiner)var1.getBlockTileEntity(var2, var3, var4);

        if (var5 != null)
        {
            for (int var6 = 0; var6 < var5.getSizeInventory(); ++var6)
            {
                ItemStack var7 = var5.getStackInSlot(var6);

                if (var7 != null)
                {
                    float var8 = this.coinerRand.nextFloat() * 0.8F + 0.1F;
                    float var9 = this.coinerRand.nextFloat() * 0.8F + 0.1F;
                    EntityItem var12;

                    for (float var10 = this.coinerRand.nextFloat() * 0.8F + 0.1F; var7.stackSize > 0; var1.spawnEntityInWorld(var12))
                    {
                        int var11 = this.coinerRand.nextInt(21) + 10;

                        if (var11 > var7.stackSize)
                        {
                            var11 = var7.stackSize;
                        }

                        var7.stackSize -= var11;
                        var12 = new EntityItem(var1, (double)((float)var2 + var8), (double)((float)var3 + var9), (double)((float)var4 + var10), new ItemStack(var7.itemID, var11, var7.getItemDamage()));
                        float var13 = 0.05F;
                        var12.motionX = (double)((float)this.coinerRand.nextGaussian() * var13);
                        var12.motionY = (double)((float)this.coinerRand.nextGaussian() * var13 + 0.2F);
                        var12.motionZ = (double)((float)this.coinerRand.nextGaussian() * var13);

                        if (var7.hasTagCompound())
                        {
                            var12.item.setTagCompound((NBTTagCompound)var7.getTagCompound().copy());
                        }
                    }
                }
            }
        }

        super.onBlockRemoval(var1, var2, var3, var4);
    }}