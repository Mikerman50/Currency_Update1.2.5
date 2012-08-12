
public class mod_ServerCurrency extends BaseModMp {

   @MLProp
   public static int stoneBlankID = 1080;
   @MLProp
   public static int ironBlockID = 1081;
   @MLProp
   public static int goldBlankID = 1082;
   @MLProp
   public static int quincunxID = 1083;
   @MLProp
   public static int denariID = 1084;
   @MLProp
   public static int aureusID = 1085;
   @MLProp
   public static int woodDieID = 1086;
   @MLProp
   public static int stoneDieID = 1087;
   @MLProp
   public static int ironDieID = 1088;
   @MLProp
   public static int diamondDieID = 1089;
   @MLProp
   public static int coinerID = 200;
   public static final gw stoneBlank = (new gw(stoneBlankID + 256)).a("stoneBlank");
   public static final gw ironBlank = (new gw(ironBlockID + 256)).a("ironBlank");
   public static final gw goldBlank = (new gw(goldBlankID + 256)).a("goldBlank");
   public static final gw dupondius = (new gw(quincunxID + 256)).a("dupondius");
   public static final gw denarius = (new gw(denariID + 256)).a("denarius");
   public static final gw aureus = (new gw(aureusID + 256)).a("aureus");
   public static final gw woodDie = (new gw(woodDieID + 256)).a("woodDie").e(1).f(7);
   public static final gw stoneDie = (new gw(stoneDieID + 256)).a("stoneDie").e(1).f(15);
   public static final gw ironDie = (new gw(ironDieID + 256)).a("ironDie").e(1).f(31);
   public static final gw diamondDie = (new gw(diamondDieID + 256)).a("diamondDie").e(1).f(127);
   public static final tl coiner = (new BlockCoiner(200, false)).c(3.0F).b(5.0F).a(tl.h).a("coiner");
   public static final String version = "v0.5.2";
   public static final ni currency = (new ni(3001, "currency", -3, 1, aureus, (ni)null)).c();
   public static int coinfront = ModLoader.addOverride("/terrain.png", "/ServerCurrency/Coiner/coinidle.png");


   public mod_ServerCurrency() {
      ModLoader.RegisterBlock(coiner);
      stoneBlank.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/blankbronze.png");
      ironBlank.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/blankiron.png");
      goldBlank.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/blankgold.png");
      dupondius.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/dupondius.png");
      denarius.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/denarius.png");
      aureus.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/aureus.png");
      woodDie.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/wooddie.png");
      stoneDie.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/stonedie.png");
      ironDie.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/irondie.png");
      diamondDie.bO = ModLoader.addOverride("/gui/items.png", "/ServerCurrency/diamonddie.png");
      ModLoader.AddRecipe(new jb(coiner, 1), new Object[]{"WDW", "IPI", "III", Character.valueOf('I'), gw.n, Character.valueOf('W'), tl.z, Character.valueOf('P'), tl.ab, Character.valueOf('D'), ironDie});
      ModLoader.AddRecipe(new jb(stoneBlank, 1), new Object[]{" C ", "C C", " C ", Character.valueOf('C'), tl.y});
      ModLoader.AddRecipe(new jb(ironBlank, 1), new Object[]{" I ", "I I", " I ", Character.valueOf('I'), gw.n});
      ModLoader.AddRecipe(new jb(goldBlank, 1), new Object[]{" G ", "G G", " G ", Character.valueOf('G'), gw.o});
      ModLoader.AddRecipe(new jb(woodDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), tl.z});
      ModLoader.AddRecipe(new jb(stoneDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), tl.y});
      ModLoader.AddRecipe(new jb(stoneDie, 3), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), tl.v});
      ModLoader.AddRecipe(new jb(ironDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), gw.n});
      ModLoader.AddRecipe(new jb(diamondDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), gw.m});
      ModLoader.AddAchievementDesc(currency, "SMP Currency!", "Get Paid.");
      ModLoader.RegisterTileEntity(TileEntityCoiner.class, "Coining Machine");
   }

   public void OnItemPickup(gz entityplayer, jb itemstack) {
      if(itemstack.c == dupondius.bM || itemstack.c == denarius.bM || itemstack.c == aureus.bM) {
         entityplayer.a(currency, 1);
      }

   }

   public static jb getOutPut(int i, int j) {
      return i == stoneBlank.bM && (j == woodDie.bM || j == stoneDie.bM || j == ironDie.bM || j == diamondDie.bM)?new jb(dupondius, 1):null;
   }

   public String Version() {
      return "v0.5.2";
   }

}
