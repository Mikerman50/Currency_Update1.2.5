import org.lwjgl.opengl.GL11;

public class GuiCoiner extends mg {

   private TileEntityCoiner coinerInventory;


   public GuiCoiner(x inventoryplayer, TileEntityCoiner tileentitycoiner) {
      super(new ContainerCoiner(inventoryplayer, tileentitycoiner));
      this.coinerInventory = tileentitycoiner;
   }

   protected void c() {
      this.q.b("Coining Mint", 60, 6, 4210752);
      this.q.b("Inventory", 8, this.c - 96 + 2, 4210752);
   }

   protected void a(float f, int i, int j) {
      int k = this.l.p.b("/gui/coiner.png");
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.l.p.b(k);
      int l = (this.m - this.b) / 2;
      int i1 = (this.n - this.c) / 2;
      this.b(l, i1, 0, 0, this.b, this.c);
      int k1;
      if(this.coinerInventory.isBurning()) {
         k1 = this.coinerInventory.getBurnTimeRemainingScaled(12);
         this.b(k1 + 56, i1 + 36 + 12 - k1, 176, 12 - k1, 14, k1 + 2);
      }

      k1 = this.coinerInventory.getCookProgressScaled(24);
      this.b(l + 79, i1 + 34, 176, 14, k1 + 1, 16);
   }
}
