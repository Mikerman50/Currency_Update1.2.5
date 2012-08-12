
public class ContainerCoiner extends pj {

   private TileEntityCoiner coiner;
   private int lastCookTime = 0;
   private int lastBurnTime = 0;
   private int lastItemBurnTime = 0;


   public ContainerCoiner(x inventoryplayer, TileEntityCoiner tileentitycoiner) {
      this.coiner = tileentitycoiner;
      this.a(new vv(tileentitycoiner, 0, 32, 35));
      this.a(new vv(tileentitycoiner, 1, 56, 16));
      this.a(new vv(tileentitycoiner, 2, 56, 54));
      this.a(new SlotCoiner(inventoryplayer.d, tileentitycoiner, 3, 116, 35));

      int j;
      for(j = 0; j < 3; ++j) {
         for(int k = 0; k < 9; ++k) {
            this.a(new vv(inventoryplayer, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
         }
      }

      for(j = 0; j < 9; ++j) {
         this.a(new vv(inventoryplayer, j, 8 + j * 18, 142));
      }

   }

   public void a() {
      super.a();

      for(int i = 0; i < this.g.size(); ++i) {
         abd icrafting = (abd)this.g.get(i);
         if(this.lastCookTime != this.coiner.coinerCookTime) {
            icrafting.a(this, 0, this.coiner.coinerCookTime);
         }

         if(this.lastBurnTime != this.coiner.coinerBurnTime) {
            icrafting.a(this, 1, this.coiner.coinerBurnTime);
         }

         if(this.lastItemBurnTime != this.coiner.currentItemBurnTime) {
            icrafting.a(this, 2, this.coiner.currentItemBurnTime);
         }
      }

      this.lastCookTime = this.coiner.coinerCookTime;
      this.lastBurnTime = this.coiner.coinerBurnTime;
      this.lastItemBurnTime = this.coiner.currentItemBurnTime;
   }

   public void a(int i, int j) {
      if(i == 0) {
         this.coiner.coinerCookTime = j;
      }

      if(i == 1) {
         this.coiner.coinerBurnTime = j;
      }

      if(i == 2) {
         this.coiner.currentItemBurnTime = j;
      }

   }

   public boolean a(vi entityplayer) {
      return this.coiner.b_(entityplayer);
   }

   public dk a(int i) {
      dk itemstack = null;
      vv slot = (vv)this.e.get(i);
      if(slot != null && slot.c()) {
         dk itemstack1 = slot.b();
         itemstack = itemstack1.k();
         if(i == 2) {
            if(!this.a(itemstack1, 3, 39, true)) {
               return null;
            }
         } else if(i >= 3 && i < 30) {
            if(!this.a(itemstack1, 30, 39, false)) {
               return null;
            }
         } else if(i >= 30 && i < 39) {
            if(!this.a(itemstack1, 3, 30, false)) {
               return null;
            }
         } else if(!this.a(itemstack1, 3, 39, false)) {
            return null;
         }

         if(itemstack1.a == 0) {
            slot.c((dk)null);
         } else {
            slot.d();
         }

         if(itemstack1.a == itemstack.a) {
            return null;
         }

         slot.b(itemstack1);
      }

      return itemstack;
   }
}
