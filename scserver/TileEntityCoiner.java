
public class TileEntityCoiner extends oh implements lp {

   private jb[] coinerItemStacks = new jb[4];
   public int coinerBurnTime = 0;
   public int currentItemBurnTime = 0;
   public int coinerCookTime = 0;
   public rn entity;


   public int c() {
      return this.coinerItemStacks.length;
   }

   public jb c_(int i) {
      return this.coinerItemStacks[i];
   }

   public jb a(int i, int j) {
      if(this.coinerItemStacks[i] != null) {
         jb itemstack1;
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

   public void a(int i, jb itemstack) {
      this.coinerItemStacks[i] = itemstack;
      if(itemstack != null && itemstack.a > this.a()) {
         itemstack.a = this.a();
      }

   }

   public String e() {
      return "Coiner";
   }

   public void a(ng nbttagcompound) {
      super.a(nbttagcompound);
      rp nbttaglist = nbttagcompound.m("Items");
      this.coinerItemStacks = new jb[this.c()];

      for(int i = 0; i < nbttaglist.d(); ++i) {
         ng nbttagcompound1 = (ng)nbttaglist.a(i);
         byte byte0 = nbttagcompound1.d("Slot");
         if(byte0 >= 0 && byte0 < this.coinerItemStacks.length) {
            this.coinerItemStacks[byte0] = jb.a(nbttagcompound1);
         }
      }

      this.coinerBurnTime = nbttagcompound.e("BurnTime");
      this.coinerCookTime = nbttagcompound.e("CookTime");
      this.currentItemBurnTime = this.getItemBurnTime(this.coinerItemStacks[2]);
   }

   public void b(ng nbttagcompound) {
      super.b(nbttagcompound);
      nbttagcompound.a("BurnTime", (short)this.coinerBurnTime);
      nbttagcompound.a("CookTime", (short)this.coinerCookTime);
      rp nbttaglist = new rp();

      for(int i = 0; i < this.coinerItemStacks.length; ++i) {
         if(this.coinerItemStacks[i] != null) {
            ng nbttagcompound1 = new ng();
            nbttagcompound1.a("Slot", (byte)i);
            this.coinerItemStacks[i].b(nbttagcompound1);
            nbttaglist.a(nbttagcompound1);
         }
      }

      nbttagcompound.a("Items", nbttaglist);
   }

   public int a() {
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

   public void l_() {
      boolean flag = this.coinerBurnTime > 0;
      boolean flag1 = false;
      if(this.coinerBurnTime > 0) {
         --this.coinerBurnTime;
      }

      if(!this.k.I) {
         if(this.coinerBurnTime == 0 && this.canSmelt()) {
            this.currentItemBurnTime = this.coinerBurnTime = this.getItemBurnTime(this.coinerItemStacks[2]);
            if(this.coinerBurnTime > 0) {
               flag1 = true;
               if(this.coinerItemStacks[2] != null) {
                  if(this.coinerItemStacks[2].a().j()) {
                     this.coinerItemStacks[2] = new jb(this.coinerItemStacks[2].a().i());
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
         this.x_();
      }

   }

   private boolean canSmelt() {
      if(this.coinerItemStacks[0] != null && this.coinerItemStacks[1] != null) {
         jb itemstack = CoinerRecipes.getSmeltingResult(this.coinerItemStacks[0].a().bM, this.coinerItemStacks[1].a().bM);
         return itemstack == null?false:(this.coinerItemStacks[3] == null?true:(!this.coinerItemStacks[3].a(itemstack)?false:(this.coinerItemStacks[3].a < this.a() && this.coinerItemStacks[3].a < this.coinerItemStacks[1].b()?true:this.coinerItemStacks[3].a < itemstack.b())));
      } else {
         return false;
      }
   }

   public void smeltItem() {
      if(this.canSmelt()) {
         jb itemstack = CoinerRecipes.getSmeltingResult(this.coinerItemStacks[0].a().bM, this.coinerItemStacks[1].a().bM);
         if(this.coinerItemStacks[3] == null) {
            this.coinerItemStacks[3] = itemstack.j();
         } else if(this.coinerItemStacks[3].c == itemstack.c) {
            ++this.coinerItemStacks[3].a;
         }

         for(int i = 0; i < 2; ++i) {
            if(this.coinerItemStacks[i].a().j()) {
               if(i == 1 && this.coinerItemStacks[1].a().g() && this.coinerItemStacks[1].a().f() > this.coinerItemStacks[1].h()) {
                  this.coinerItemStacks[1].a(1, (lk)this.entity);
               } else {
                  this.coinerItemStacks[i] = new jb(this.coinerItemStacks[i].a().i());
               }
            } else {
               --this.coinerItemStacks[i].a;
            }

            if(this.coinerItemStacks[i].a <= 0) {
               if(i == 1 && this.coinerItemStacks[1].a().g() && this.coinerItemStacks[1].a().f() > this.coinerItemStacks[1].h()) {
                  this.coinerItemStacks[1].a(1, (lk)this.entity);
               } else {
                  this.coinerItemStacks[i] = null;
               }
            }
         }

      }
   }

   private int getItemBurnTime(jb itemstack) {
      if(itemstack == null) {
         return 0;
      } else {
         int i = itemstack.a().bM;
         return i < 256 && tl.m[i].cb == lg.d?300:(i == gw.C.bM?100:(i == gw.l.bM?1600:(i == gw.ax.bM?20000:(i == tl.A.bO?100:0))));
      }
   }

   public boolean a(gz entityplayer) {
      return this.k.b(this.l, this.m, this.n) != this?false:entityplayer.e((double)this.l + 0.5D, (double)this.m + 0.5D, (double)this.n + 0.5D) <= 64.0D;
   }

   public void g() {}

   public void f() {}
}
