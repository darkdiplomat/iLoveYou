/**
 * The Altar Event
 */
public final class AltarEvent extends HerobrineEvent{
    private final int x, y, z;
    private final Player player;
    private HerobrineCharacter hero;
    private boolean cursed = false;
    private boolean precurse = false;
    public boolean stopped;

    public AltarEvent(Block block, Player p){
        EventName = "AltarEvent";
        if (HeroSettings.console) {
            iLoveYou.logger.info("[ILY]: " + p.getName() + " built an altar for " + HeroSettings.name);
        }
        this.stopped = false;
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.player = p;
    }

    @Override
    public final void delete(){
        super.delete();
        this.hero.destroy();
        this.stopped = true;
    }

    public final static boolean checkForAltar(Block block){
        int x = block.getX();
        int y = block.getY() - 1;
        int z = block.getZ();

        if (block.getWorld().getBlockIdAt(x, y, z) != 87) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x, y - 1, z) != 48) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x - 1, y - 1, z) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x + 1, y - 1, z) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x, y - 1, z - 1) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x, y - 1, z + 1) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x - 1, y - 1, z - 1) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x + 1, y - 1, z - 1) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x - 1, y - 1, z + 1) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x + 1, y - 1, z + 1) != 41) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x - 1, y, z) != 76) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x + 1, y, z) != 76) {
            return false;
        }
        if (block.getWorld().getBlockIdAt(x, y, z - 1) != 76) {
            return false;
        }

        return block.getWorld().getBlockIdAt(x, y, z + 1) == 76;
    }

    @Override
    public final void trigger(){
        this.precurse = true;
        server.addToServerQueue(new AltarEvent.AltarEvents(), 5000L);
    }

    @Override
    public final void sendMove(Player player, Location from, Location to){
        if (player.equals(this.player) && this.hero != null) {
            stalkPlayer(to);
        }
    }

    @Override
    public final void sendTeleport(Player player, Location from, Location to){
        if (player.equals(this.player) && this.hero != null) {
            stalkPlayer(to);
        }
    }

    public final void stalkPlayer(Location to){
        if (this.player.equals(this.player)) {
            double d1 = this.hero.getX() - to.x;
            double d2 = this.hero.getZ() - to.z;
            double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
            double d4 = Math.asin((this.hero.getY() - to.y) / iLoveYou.distance(to, new Location(this.hero.getX(), this.hero.getY(), this.hero.getZ()))) * 45.0D;
            if (d1 >= 0.0D) {
                if (d2 >= 0.0D) {
                    d3 = 180.0D - d3;
                }
            }
            else if (d2 >= 0.0D) {
                d3 += 180.0D;
            }
            else {
                d3 = 360.0D - d3;
            }
            this.hero.teleportTo(this.hero.getX(), this.hero.getY(), this.hero.getZ(), (float) d3, (float) d4);
            this.hero.spawn();
            this.hero.lookat(this.player);
            if (this.cursed) {
                // world.setBlockAt(51, (int) Math.floor(to.x), (int) Math.floor(to.y), (int) Math.floor(to.z));
                AltarEvent.this.player.setFireTicks(200);
            }
        }
    }

    public final void destroyTorches(int radius){
        for (int x = this.x - radius; x < this.x + radius; x++) {
            for (int y = this.y - radius; y < this.y + radius; y++) {
                for (int z = this.z - radius; z < this.z + radius; z++) {
                    if (player.getWorld().getBlockIdAt(x, y, z) == 50) {
                        breakTorch(x, y, z);
                    }
                }
            }
        }
    }

    public final void breakTorch(int x, int y, int z){
        player.getWorld().setBlockAt(0, x, y, z);
        player.getWorld().dropItem(x, y, z, 50);
    }

    public final void deleteAltar(){
        player.getWorld().setBlockAt(0, this.x, this.y, this.z);
        player.getWorld().setBlockAt(0, this.x, this.y - 1, this.z);
        player.getWorld().setBlockAt(0, this.x - 1, this.y - 1, this.z);
        player.getWorld().setBlockAt(0, this.x + 1, this.y - 1, this.z);
        player.getWorld().setBlockAt(0, this.x, this.y - 1, this.z - 1);
        player.getWorld().setBlockAt(0, this.x, this.y - 1, this.z + 1);
        player.getWorld().setBlockAt(0, this.x, this.y - 2, this.z);
        player.getWorld().setBlockAt(0, this.x - 1, this.y - 2, this.z);
        player.getWorld().setBlockAt(0, this.x + 1, this.y - 2, this.z);
        player.getWorld().setBlockAt(0, this.x, this.y - 2, this.z - 1);
        player.getWorld().setBlockAt(0, this.x, this.y - 2, this.z + 1);
        player.getWorld().setBlockAt(0, this.x - 1, this.y - 2, this.z + 1);
        player.getWorld().setBlockAt(0, this.x + 1, this.y - 2, this.z - 1);
        player.getWorld().setBlockAt(0, this.x - 1, this.y - 2, this.z - 1);
        player.getWorld().setBlockAt(0, this.x + 1, this.y - 2, this.z + 1);
    }

    public final String appearance(){
        int num = (int) (Math.random() * 40.0D);
        switch (num) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return HeroLanguage.getString("iah");
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return HeroLanguage.getString("wwydt");
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return HeroLanguage.getString("yasf");
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return HeroLanguage.getString("ogchyn");
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return HeroLanguage.getString("ha");
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                return HeroLanguage.getString("tim");
            default:
                return HeroLanguage.getString("stop");
        }
    }

    @Override
    public final boolean sendCommand(Player player, String[] command){
        if (this.precurse) {
            player.sendMessage(Colors.Red + getRandomString(7) + " " + getRandomString(10));
            return true;
        }
        return false;
    }

    public final static String getRandomString(int length){
        String ret = "";
        for (int i = 0; i < length; i++) {
            ret = ret + (char) (int) (Math.random() * 128.0D + 128.0D);
        }
        return ret;
    }

    private final class AltarEvents implements Runnable{
        int step = 0;

        public AltarEvents(){}

        @Override
        public void run(){
            if (!iLoveYou.enabled || AltarEvent.this.stopped) {
                return;
            }
            if (step == 10) {
                return;
            }
            int delay = 1000;

            switch (step) {
                case 0:
                    player.getWorld().setRelativeTime(14000L);
                    delay = 2000;
                    break;
                case 1:
                    AltarEvent.this.destroyTorches(5);
                    delay = 1000;
                    break;
                case 2:
                    AltarEvent.this.destroyTorches(10);
                    delay = 1000;
                    break;
                case 3:
                    AltarEvent.this.destroyTorches(20);
                    delay = 3000;
                    break;
                case 4:
                    double d1 = AltarEvent.this.x + 0.5D - AltarEvent.this.player.getX();
                    double d2 = AltarEvent.this.z + 0.5D - AltarEvent.this.player.getZ();
                    double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
                    double d4 = Math.asin((AltarEvent.this.y - AltarEvent.this.player.getY()) / iLoveYou.distance(AltarEvent.this.player.getLocation(), new Location(AltarEvent.this.x + 0.5D, AltarEvent.this.y + 0.5D, AltarEvent.this.z + 0.5D))) * 45.0D;
                    if (d1 >= 0.0D) {
                        if (d2 >= 0.0D) {
                            d3 = 180.0D - d3;
                        }
                    }
                    else if (d2 >= 0.0D) {
                        d3 += 180.0D;
                    }
                    else {
                        d3 = 360.0D - d3;
                    }
                    AltarEvent.this.hero = new HerobrineCharacter(AltarEvent.this.player.getWorld(), AltarEvent.this.x + 0.5D, AltarEvent.this.y + 0.5D, AltarEvent.this.z + 0.5D, (float) d3, (float) d4, new Item(Item.Type.RedstoneTorchOn, 1));
                    AltarEvent.this.hero.spawn();
                    AltarEvent.this.hero.sendMessage(AltarEvent.this.player, AltarEvent.this.appearance());
                    delay = 5000;
                    break;
                case 5:
                    AltarEvent.this.cursed = true;
                    AltarEvent.this.player.setFireTicks(200);
                    delay = 10000;
                    break;
                case 6:
                    AltarEvent.this.deleteAltar();
                    AltarEvent.this.delete();
            }
            step++;

            server.addToServerQueue(this, delay);
        }
    }
}
