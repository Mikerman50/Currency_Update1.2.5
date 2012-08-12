
public class SlotCoiner extends vv {

   private vi thePlayer;


   public SlotCoiner(vi entityplayer, de iinventory, int i, int j, int k) {
      super(iinventory, i, j, k);
      this.thePlayer = entityplayer;
   }

   public boolean a(dk itemstack) {
      return false;
   }
}
