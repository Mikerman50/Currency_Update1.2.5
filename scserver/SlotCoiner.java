
public class SlotCoiner extends gy {

   private gz thePlayer;


   public SlotCoiner(gz entityplayer, lp iinventory, int i, int j, int k) {
      super(iinventory, i, j, k);
      this.thePlayer = entityplayer;
   }

   public boolean a(jb itemstack) {
      return false;
   }
}
