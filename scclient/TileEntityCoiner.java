
public class TileEntityCoiner extends bq implements de {

   private dk[] coinerItemStacks = new dk[4];
   public int coinerBurnTime = 0;
   public int currentItemBurnTime = 0;
   public int coinerCookTime = 0;
   public ia entity;


   public int c() {
      return this.coinerItemStacks.length;
   }

   public dk d(int i) {
      return this.coinerItemStacks[i];
   }

   public dk a(int i, int j) {
      if(this.coinerItemStacks[i] != null) {
         dk itemstack1;
         if(this.coinerItemStacks[i].a <= j) {
            itemstack1 = this.coinerItemStacks[i];
            this.coinerItemStacks[i] = null;
            return itemstack1;
         } else {
            itemstack1 = this.coinerItemStacks[i].a(j);
            if(this.coinerItemStacks[i].a == 0) {
               this.coinerItemStacks[i] = null;
            }

            return itemstack1;
         }
      } else {
         return null;
      }
   }

   public void a(int i, dk itemstack) {
      this.coinerItemStacks[i] = itemstack;
      if(itemstack != null && itemstack.a > this.e()) {
         itemstack.a = this.e();
      }

   }

   public String d() {
      return "Coiner";
   }

   public void b(ik nbttagcompound) {
      super.b(nbttagcompound);
      yi nbttaglist = nbttagcompound.l("Items");
      this.coinerItemStacks = new dk[this.c()];

      for(int i = 0; i < nbttaglist.c(); ++i) {
         ik nbttagcompound1 = (ik)nbttaglist.a(i);
         byte byte0 = nbttagcompound1.c("Slot");
         if(byte0 >= 0 && byte0 < this.coinerItemStacks.length) {
            this.coinerItemStacks[byte0] = dk.a(nbttagcompound1);
         }
      }

      this.coinerBurnTime = nbttagcompound.d("BurnTime");
      this.coinerCookTime = nbttagcompound.d("CookTime");
      this.currentItemBurnTime = this.getItemBurnTime(this.coinerItemStacks[2]);
   }

   public void a(ik nbttagcompound) {
      super.a(nbttagcompound);
      nbttagcompound.a("BurnTime", (short)this.coinerBurnTime);
      nbttagcompound.a("CookTime", (short)this.coinerCookTime);
      yi nbttaglist = new yi();

      for(int i = 0; i < this.coinerItemStacks.length; ++i) {
         if(this.coinerItemStacks[i] != null) {
            ik nbttagcompound1 = new ik();
            nbttagcompound1.a("Slot", (byte)i);
            this.coinerItemStacks[i].b(nbttagcompound1);
            nbttaglist.a(nbttagcompound1);
         }
      }

      nbttagcompound.a("Items", nbttaglist);
   }

   public int e() {
      return 64;
   }

   public int getCookProgressScaled(int i) {
      return this.coinerCookTime * i / 200;
   }

   public int getBurnTimeRemainingScaled(int i) {
      if(this.currentItemBurnTime == 0) {
         this.currentItemBurnTime = 200;
      }

      return this.coinerBurnTime * i / this.currentItemBurnTime;
   }

   public boolean isBurning() {
      return this.coinerBurnTime > 0;
   }

   public void b() {
      boolean flag = this.coinerBurnTime > 0;
      boolean flag1 = false;
      if(this.coinerBurnTime > 0) {
         --this.coinerBurnTime;
      }

      if(!this.c.I) {
         if(this.coinerBurnTime == 0 && this.canSmelt()) {
            this.currentItemBurnTime = this.coinerBurnTime = this.getItemBurnTime(this.coinerItemStacks[2]);
            if(this.coinerBurnTime > 0) {
               flag1 = true;
               if(this.coinerItemStacks[2] != null) {
                  if(this.coinerItemStacks[2].a().k()) {
                     this.coinerItemStacks[2] = new dk(this.coinerItemStacks[2].a().j());
                  } else {
                     --this.coinerItemStacks[2].a;
                  }

                  if(this.coinerItemStacks[2].a == 0) {
                     this.coinerItemStacks[2] = null;
                  }
               }
            }
         }

         if(this.isBurning() && this.canSmelt()) {
            ++this.coinerCookTime;
            if(this.coinerCookTime == 200) {
               this.coinerCookTime = 0;
               this.smeltItem();
               flag1 = true;
            }
         } else {
            this.coinerCookTime = 0;
         }

         if(flag != this.coinerBurnTime > 0) {
            flag1 = true;
         }
      }

      if(flag1) {
         this.h();
      }

   }

   private boolean canSmelt() {
      if(this.coinerItemStacks[0] != null && this.coinerItemStacks[1] != null) {
         dk itemstack = CoinerRecipes.getSmeltingResult(this.coinerItemStacks[0].a().bM, this.coinerItemStacks[1].a().bM);
         return itemstack == null?false:(this.coinerItemStacks[3] == null?true:(!this.coinerItemStacks[3].a(itemstack)?false:(this.coinerItemStacks[3].a < this.e() && this.coinerItemStacks[3].a < this.coinerItemStacks[1].c()?true:this.coinerItemStacks[3].a < itemstack.c())));
      } else {
         return false;
      }
   }

   public void smeltItem() {
      if(this.canSmelt()) {
         dk itemstack = CoinerRecipes.getSmeltingResult(this.coinerItemStacks[0].a().bM, this.coinerItemStacks[1].a().bM);
         if(this.coinerItemStacks[3] == null) {
            this.coinerItemStacks[3] = itemstack.k();
         } else if(this.coinerItemStacks[3].c == itemstack.c) {
            ++this.coinerItemStacks[3].a;
         }

         for(int i = 0; i < 2; ++i) {
            if(this.coinerItemStacks[i].a().k()) {
               if(i == 1 && this.coinerItemStacks[1].a().h() && this.coinerItemStacks[1].a().g() > this.coinerItemStacks[1].i()) {
                  this.coinerItemStacks[1].a(1, (nq)this.entity);
               } else {
                  this.coinerItemStacks[i] = new dk(this.coinerItemStacks[i].a().j());
               }
            } else {
               --this.coinerItemStacks[i].a;
            }

            if(this.coinerItemStacks[i].a <= 0) {
               if(i == 1 && this.coinerItemStacks[1].a().h() && this.coinerItemStacks[1].a().g() > this.coinerItemStacks[1].i()) {
                  this.coinerItemStacks[1].a(1, (nq)this.entity);
               } else {
                  this.coinerItemStacks[i] = null;
               }
            }
         }

      }
   }

   private int getItemBurnTime(dk itemstack) {
      if(itemstack == null) {
         return 0;
      } else {
         int i = itemstack.a().bM;
         return i < 256 && yy.k[i].bZ == p.d?300:(i == acy.C.bM?100:(i == acy.l.bM?1600:(i == acy.ax.bM?20000:(i == yy.y.bM?100:0))));
      }
   }

   public boolean b_(vi entityplayer) {
      return this.c.b(this.d, this.e, this.f) != this?false:entityplayer.f((double)this.d + 0.5D, (double)this.e + 0.5D, (double)this.f + 0.5D) <= 64.0D;
   }

   public void j() {}

   public void k() {}
}
