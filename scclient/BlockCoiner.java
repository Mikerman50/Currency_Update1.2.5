import java.util.Random;

public class BlockCoiner extends ba {

   private Random coinerRand = new Random();
   private final boolean isActive;
   private static boolean keepCoinerInventory = false;


   protected BlockCoiner(int i, boolean flag) {
      super(i, p.e);
      this.isActive = flag;
      this.bL = 45;
   }

   public int a(int i, Random random, int j) {
      return mod_ServerCurrency.coiner.bM;
   }

   public void a(ry world, int i, int j, int k) {
      super.a(world, i, j, k);
      this.setDefaultDirection(world, i, j, k);
   }

   private void setDefaultDirection(ry world, int i, int j, int k) {
      if(!world.I) {
         int l = world.a(i, j, k - 1);
         int i1 = world.a(i, j, k + 1);
         int j1 = world.a(i - 1, j, k);
         int k1 = world.a(i + 1, j, k);
         byte byte0 = 3;
         if(yy.m[l] && !yy.m[i1]) {
            byte0 = 3;
         }

         if(yy.m[i1] && !yy.m[l]) {
            byte0 = 2;
         }

         if(yy.m[j1] && !yy.m[k1]) {
            byte0 = 5;
         }

         if(yy.m[k1] && !yy.m[j1]) {
            byte0 = 4;
         }

         world.f(i, j, k, byte0);
      }
   }

   public int a(kq iblockaccess, int i, int j, int k, int l) {
      if(l == 1) {
         return this.bL + 17;
      } else if(l == 0) {
         return this.bL + 17;
      } else {
         int i1 = iblockaccess.d(i, j, k);
         return l != i1?this.bL:(this.isActive?this.bL + 16:mod_ServerCurrency.coinfront);
      }
   }

   public int b(int i) {
      return i == 1?this.bL + 17:(i == 0?this.bL + 17:(i == 3?mod_ServerCurrency.coinfront:this.bL));
   }

   public boolean a(ry world, int i, int j, int k, vi entityplayer) {
      if(world.I) {
         return true;
      } else {
         TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.b(i, j, k);
         if(tileentitycoiner != null) {
            ModLoader.OpenGUI(ModLoader.getMinecraftInstance().h, new GuiCoiner(ModLoader.getMinecraftInstance().h.by, tileentitycoiner));
         }

         return true;
      }
   }

   public bq j_() {
      return new TileEntityCoiner();
   }

   public void a(ry world, int i, int j, int k, nq entityliving) {
      int l = me.c((double)(entityliving.y * 4.0F / 360.0F) + 0.5D) & 3;
      if(l == 0) {
         world.f(i, j, k, 2);
      }

      if(l == 1) {
         world.f(i, j, k, 5);
      }

      if(l == 2) {
         world.f(i, j, k, 3);
      }

      if(l == 3) {
         world.f(i, j, k, 4);
      }

   }

   public void d(ry world, int i, int j, int k) {
      if(!keepCoinerInventory) {
         TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.b(i, j, k);
         if(tileentitycoiner != null) {
            for(int l = 0; l < tileentitycoiner.c(); ++l) {
               dk itemstack = tileentitycoiner.d(l);
               if(itemstack != null) {
                  float f = this.coinerRand.nextFloat() * 0.8F + 0.1F;
                  float f1 = this.coinerRand.nextFloat() * 0.8F + 0.1F;
                  float f2 = this.coinerRand.nextFloat() * 0.8F + 0.1F;

                  while(itemstack.a > 0) {
                     int i1 = this.coinerRand.nextInt(21) + 10;
                     if(i1 > itemstack.a) {
                        i1 = itemstack.a;
                     }

                     itemstack.a -= i1;
                     ih entityitem = new ih(world, (double)((float)i + f), (double)((float)j + f1), (double)((float)k + f2), new dk(itemstack.c, i1, itemstack.i()));
                     float f3 = 0.05F;
                     entityitem.v = (double)((float)this.coinerRand.nextGaussian() * f3);
                     entityitem.w = (double)((float)this.coinerRand.nextGaussian() * f3 + 0.2F);
                     entityitem.x = (double)((float)this.coinerRand.nextGaussian() * f3);
                     world.a(entityitem);
                  }
               }
            }
         }
      }

      super.d(world, i, j, k);
   }

}
