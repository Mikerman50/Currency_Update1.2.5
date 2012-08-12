
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
   public static int coinerGUI = 98;
   public static final acy stoneBlank = (new acy(stoneBlankID + 256)).a("stoneBlank");
   public static final acy ironBlank = (new acy(ironBlockID + 256)).a("ironBlank");
   public static final acy goldBlank = (new acy(goldBlankID + 256)).a("goldBlank");
   public static final acy dupondius = (new acy(quincunxID + 256)).a("dupondius");
   public static final acy denarius = (new acy(denariID + 256)).a("denarius");
   public static final acy aureus = (new acy(aureusID + 256)).a("aureus");
   public static final acy woodDie = (new acy(woodDieID + 256)).a("woodDie").h(1).i(7);
   public static final acy stoneDie = (new acy(stoneDieID + 256)).a("stoneDie").h(1).i(15);
   public static final acy ironDie = (new acy(ironDieID + 256)).a("ironDie").h(1).i(31);
   public static final acy diamondDie = (new acy(diamondDieID + 256)).a("diamondDie").h(1).i(127);
   public static final yy coiner = (new BlockCoiner(200, false)).c(3.0F).b(5.0F).a(yy.f).a("coiner");
   public static final String version = "v0.5.2";
   public static final ws currency = (new ws(3001, "currency", -3, 1, aureus, (ws)null)).h();
   public static int coinfront = ModLoader.addOverride("/terrain.png", "/ServerCurrency/Coiner/coinidle.png");


   public void load() {
      ModLoader.RegisterBlock(coiner);
      ModLoader.AddName(dupondius, "Quincunx");
      ModLoader.AddName(denarius, "Denarii");
      ModLoader.AddName(aureus, "Aureus");
      ModLoader.AddName(stoneBlank, "Stone Blank");
      ModLoader.AddName(ironBlank, "Iron Blank");
      ModLoader.AddName(goldBlank, "Gold Blank");
      ModLoader.AddName(woodDie, "Wooden Die");
      ModLoader.AddName(stoneDie, "Stone Die");
      ModLoader.AddName(ironDie, "Iron Die");
      ModLoader.AddName(diamondDie, "Diamond Die");
      ModLoader.AddName(coiner, "Coining Mint");
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
      ModLoader.AddRecipe(new dk(coiner, 1), new Object[]{"WDW", "IPI", "III", Character.valueOf('I'), acy.n, Character.valueOf('W'), yy.x, Character.valueOf('P'), yy.Z, Character.valueOf('D'), ironDie});
      ModLoader.AddRecipe(new dk(stoneBlank, 1), new Object[]{" C ", "C C", " C ", Character.valueOf('C'), yy.w});
      ModLoader.AddRecipe(new dk(ironBlank, 1), new Object[]{" I ", "I I", " I ", Character.valueOf('I'), acy.n});
      ModLoader.AddRecipe(new dk(goldBlank, 1), new Object[]{" G ", "G G", " G ", Character.valueOf('G'), acy.o});
      ModLoader.AddRecipe(new dk(woodDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), yy.x});
      ModLoader.AddRecipe(new dk(stoneDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), yy.w});
      ModLoader.AddRecipe(new dk(stoneDie, 3), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), yy.t});
      ModLoader.AddRecipe(new dk(ironDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), acy.n});
      ModLoader.AddRecipe(new dk(diamondDie, 1), new Object[]{"# #", " # ", "# #", Character.valueOf('#'), acy.m});
      ModLoader.AddAchievementDesc(currency, "SMP Currency!", "Get Paid.");
      ModLoader.RegisterTileEntity(TileEntityCoiner.class, "Coiner");
      ModLoaderMp.RegisterGUI(this, coinerGUI);
   }

   public void OnItemPickup(vi entityplayer, dk itemstack) {
      if(itemstack.c == dupondius.bM || itemstack.c == denarius.bM || itemstack.c == aureus.bM) {
         entityplayer.a(currency, 1);
      }

   }

   public static dk getOutPut(int i, int j) {
      return i == stoneBlank.bM && (j == woodDie.bM || j == stoneDie.bM || j == ironDie.bM || j == diamondDie.bM)?new dk(dupondius, 1):null;
   }

   public xe HandleGUI(int inventoryType) {
      return inventoryType == coinerGUI?new GuiCoiner(ModLoader.getMinecraftInstance().h.by, new TileEntityCoiner()):null;
   }

   public String getVersion() {
      return "v0.5.2";
   }

}
