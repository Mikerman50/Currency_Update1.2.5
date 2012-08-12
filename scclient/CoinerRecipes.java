
public class CoinerRecipes {

   public static dk getSmeltingResult(int i, int j) {
      return getOutput(i, j);
   }

   private static dk getOutput(int i, int j) {
      return i == mod_ServerCurrency.ironBlank.bM && (j == mod_ServerCurrency.woodDie.bM || j == mod_ServerCurrency.stoneDie.bM || j == mod_ServerCurrency.ironDie.bM || j == mod_ServerCurrency.diamondDie.bM)?new dk(mod_ServerCurrency.denarius, 1):(i == mod_ServerCurrency.goldBlank.bM && (j == mod_ServerCurrency.woodDie.bM || j == mod_ServerCurrency.stoneDie.bM || j == mod_ServerCurrency.ironDie.bM || j == mod_ServerCurrency.diamondDie.bM)?new dk(mod_ServerCurrency.aureus, 1):mod_ServerCurrency.getOutPut(i, j));
   }
}
