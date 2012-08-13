// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            mod_ServerCurrency, Item, ItemStack

public class CoinerRecipes
{

    public CoinerRecipes()
    {
    }

    public static ItemStack getSmeltingResult(int i, int j)
    {
        return getOutput(i, j);
    }

    private static ItemStack getOutput(int i, int j)
    {
        if(i == mod_ServerCurrency.ironBlank.shiftedIndex && (j == mod_ServerCurrency.woodDie.shiftedIndex || j == mod_ServerCurrency.stoneDie.shiftedIndex || j == mod_ServerCurrency.ironDie.shiftedIndex || j == mod_ServerCurrency.diamondDie.shiftedIndex))
        {
            return new ItemStack(mod_ServerCurrency.denarius, 1);
        }
        if(i == mod_ServerCurrency.goldBlank.shiftedIndex && (j == mod_ServerCurrency.woodDie.shiftedIndex || j == mod_ServerCurrency.stoneDie.shiftedIndex || j == mod_ServerCurrency.ironDie.shiftedIndex || j == mod_ServerCurrency.diamondDie.shiftedIndex))
        {
            return new ItemStack(mod_ServerCurrency.aureus, 1);
        } else
        {
            return mod_ServerCurrency.getOutPut(i, j);
        }
    }
}
