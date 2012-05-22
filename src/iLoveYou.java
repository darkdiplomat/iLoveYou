import java.util.logging.Logger;

public class iLoveYou extends Plugin {
    private final iLoveYou.PlayerListener listener = new iLoveYou.PlayerListener();
    private final String name = "iLoveYou";
    private long delay = 1800000L;
    
    public final static Logger logger = Logger.getLogger("Minecraft");
    public final static Server server = etc.getServer();
    public final static World world = etc.getServer().getDefaultWorld();
    public final static String version = "0.6.1";
    public static boolean enabled;
    public static HerobrineEvent currEvent;
    public static SpawnEvent spawnevent;

    @Override
    public void enable() {
        enabled = true;
        HeroSettings.importSettings();
        HerobrineEvent.initializeWheel();
        server.addToServerQueue(new iLoveYou.HeroCaller(), this.delay);
        logger.info(this.name + " " + version + " enabled");
    }

    @Override
    public void disable() {
        if (currEvent != null) {
            currEvent.delete();
        }
        enabled = false;
        logger.info(this.name + " " + version + " disabled");
    }

    @Override
    public void initialize() {
        etc.getLoader().addListener(PluginLoader.Hook.PLAYER_MOVE, this.listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener(PluginLoader.Hook.TELEPORT, this.listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener(PluginLoader.Hook.COMMAND, this.listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener(PluginLoader.Hook.DAMAGE, this.listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener(PluginLoader.Hook.IGNITE, this.listener, this, PluginListener.Priority.MEDIUM);
        etc.getLoader().addListener(PluginLoader.Hook.BLOCK_RIGHTCLICKED, this.listener, this, PluginListener.Priority.MEDIUM);
    }

    public static double distance(Location loc1, Location loc2) {
        return Math.sqrt(Math.pow(loc1.x - loc2.x, 2.0D) + Math.pow(loc1.y - loc2.y, 2.0D) + Math.pow(loc1.z - loc2.z, 2.0D));
    }

    public static boolean blockIsAboveAir(double x, double y, double z) {
        return world.getBlockAt((int) Math.floor(x), (int) Math.floor(y - 1.0D), (int) Math.floor(z)).getType() == 0;
    }

    public static boolean blockIsSafe(double x, double y, double z) {
        return world.getBlockAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)).getType() == 0 && world.getBlockAt((int) Math.floor(x), (int) Math.floor(y + 1.0D), (int) Math.floor(z)).getType() == 0;
    }

    public static boolean blockIsAvailable(double x, double y, double z) {
        return world.getBlockAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)).getType() == 0;
    }

    private class HeroCaller implements Runnable {
        private HeroCaller() { }

        @Override
        public void run() {
            if (!iLoveYou.enabled) {
                return;
            }
            if (Math.random() < 0.5D / HeroSettings.hours) {
                if (iLoveYou.currEvent != null) {
                    iLoveYou.currEvent.delete();
                }
                iLoveYou.currEvent = HerobrineEvent.getEvent();
                if (iLoveYou.currEvent != null) {
                    iLoveYou.currEvent.trigger();
                }
            }
            server.addToServerQueue(this, iLoveYou.this.delay);
        }
    }
    
    

    private class PlayerListener extends PluginListener {
        private PlayerListener() { }

        @Override
        public boolean onCommand(Player player, String[] command) {
            if (command[0].equalsIgnoreCase("/iloveyou")) {
                player.sendMessage(Colors.Rose+ HeroLanguage.getString("ilyt") + " <3");
                return true;
            }

            if (command[0].equalsIgnoreCase("/spawnHerobrine")) {
                if(iLoveYou.spawnevent == null){
                    iLoveYou.spawnevent = new SpawnEvent(player);
                    iLoveYou.spawnevent.trigger();
                }
                return true;
            }

            if (iLoveYou.currEvent != null) {
                return iLoveYou.currEvent.sendCommand(player, command);
            }
            return false;
        }

        @Override
        public void onPlayerMove(Player player, Location from, Location to) {
            if (iLoveYou.currEvent != null) {
                iLoveYou.currEvent.sendMove(player, from, to);
            }
            if (iLoveYou.spawnevent != null) {
                iLoveYou.spawnevent.sendMove(player, from, to);
            }
        }

        @Override
        public boolean onTeleport(Player player, Location from, Location to) {
            if (iLoveYou.currEvent != null) {
                iLoveYou.currEvent.sendTeleport(player, from, to);
            }
            return false;
        }

        @Override
        public boolean onDamage(PluginLoader.DamageType type, BaseEntity attacker, BaseEntity defender, int amount) {
            return false;
        }

        @Override
        public boolean onIgnite(Block block, Player player) {
            if (HeroSettings.events.get(HeroSettings.Event.ALTAR) && block.getStatus() == 2 && iLoveYou.currEvent == null && AltarEvent.checkForAltar(block)) {
                iLoveYou.currEvent = new AltarEvent(block, player);
                iLoveYou.currEvent.trigger();
            }
            return false;
        }

        @Override
        public void onBlockRightClicked(Player player, Block blockClicked, Item item) {
            if (blockClicked.getType() == 54) {
                ThiefEvent.logChest(blockClicked);
            }
        }
    }
}
