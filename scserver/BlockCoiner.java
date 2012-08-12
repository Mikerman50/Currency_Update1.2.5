import java.util.Random;

public class BlockCoiner extends qx {

   private Random coinerRand = new Random();
   private final boolean isActive;
   private static boolean keepCoinerInventory = false;


   protected BlockCoiner(int i, boolean flag) {
      super(i, lg.e);
      this.isActive = flag;
      this.bN = 45;
   }

   public int a(int i, Random random, int j) {
      return mod_ServerCurrency.coiner.bO;
   }

   public void a(fi world, int i, int j, int k) {
      super.a(world, i, j, k);
      this.setDefaultDirection(world, i, j, k);
   }

   private void setDefaultDirection(fi world, int i, int j, int k) {
      if(!world.I) {
         int l = world.a(i, j, k - 1);
         int i1 = world.a(i, j, k + 1);
         int j1 = world.a(i - 1, j, k);
         int k1 = world.a(i + 1, j, k);
         byte byte0 = 3;
         if(tl.o[l] && !tl.o[i1]) {
            byte0 = 3;
         }

         if(tl.o[i1] && !tl.o[l]) {
            byte0 = 2;
         }

         if(tl.o[j1] && !tl.o[k1]) {
            byte0 = 5;
         }

         if(tl.o[k1] && !tl.o[j1]) {
            byte0 = 4;
         }

         world.c(i, j, k, byte0);
      }
   }

   public int a(int i) {
      return i == 1?this.bN + 17:(i == 0?this.bN + 17:(i == 3?mod_ServerCurrency.coinfront:this.bN));
   }

   public boolean a(fi world, int i, int j, int k, gz entityplayer) {
      if(world.I) {
         return true;
      } else {
         TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.b(i, j, k);
         if(tileentitycoiner != null) {
            ModLoader.OpenGUI(entityplayer, 98, tileentitycoiner, new ContainerCoiner(entityplayer.k, tileentitycoiner));
         }

         return true;
      }
   }

   public oh a_() {
      return new TileEntityCoiner();
   }

   public void a(fi world, int i, int j, int k, lk entityliving) {
      int l = in.b((double)(entityliving.bp * 4.0F / 360.0F) + 0.5D) & 3;
      if(l == 0) {
         world.c(i, j, k, 2);
      }

      if(l == 1) {
         world.c(i, j, k, 5);
      }

      if(l == 2) {
         world.c(i, j, k, 3);
      }

      if(l == 3) {
         world.c(i, j, k, 4);
      }

   }

   public void d(fi world, int i, int j, int k) {
      if(!keepCoinerInventory) {
         TileEntityCoiner tileentitycoiner = (TileEntityCoiner)world.b(i, j, k);
         if(tileentitycoiner != null) {
            for(int l = 0; l < tileentitycoiner.c(); ++l) {
               jb itemstack = tileentitycoiner.c_(l);
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
                     hp entityitem = new hp(world, (double)((float)i + f), (double)((float)j + f1), (double)((float)k + f2), new jb(itemstack.c, i1, itemstack.h()));
                     float f3 = 0.05F;
                     entityitem.bm = (double)((float)this.coinerRand.nextGaussian() * f3);
                     entityitem.bn = (double)((float)this.coinerRand.nextGaussian() * f3 + 0.2F);
                     entityitem.bo = (double)((float)this.coinerRand.nextGaussian() * f3);
                     world.b(entityitem);
                  }
               }
            }
         }
      }

      super.d(world, i, j, k);
   }

}
