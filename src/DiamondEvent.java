import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DiamondEvent extends HerobrineEvent{
    private HerobrineCharacter herobrine;
    private Player player;
    private double yDiamonds;
    private LinkedList<Block> blocks = new LinkedList<Block>();
    private boolean ladder = false;
    private boolean torch = false;
    private boolean waiting = false;
    private boolean finalizing = false;

    private ArrayList<Integer> placeables = new ArrayList<Integer>();

    public DiamondEvent(){
        EventName = "DiamondEvent";
    }

    @Override
    public void delete(){
        super.delete();
        this.herobrine.destroy();
        restoreBlocks();
    }

    public void restoreBlocks(){
        while (this.blocks.size() > 0) {
            Block block = this.blocks.removeFirst();
            herobrine.getWorld().setBlock(block);
        }
    }

    @Override
    public void trigger(){
        List<Player> players = server.getPlayerList();
        if (players.size() == 0) {
            return;
        }

        this.placeables.add(Integer.valueOf(1));
        this.placeables.add(Integer.valueOf(2));
        this.placeables.add(Integer.valueOf(3));
        this.placeables.add(Integer.valueOf(4));
        this.placeables.add(Integer.valueOf(4));
        this.placeables.add(Integer.valueOf(5));
        this.placeables.add(Integer.valueOf(7));
        this.placeables.add(Integer.valueOf(12));
        this.placeables.add(Integer.valueOf(13));
        this.placeables.add(Integer.valueOf(14));
        this.placeables.add(Integer.valueOf(15));
        this.placeables.add(Integer.valueOf(16));
        this.placeables.add(Integer.valueOf(17));
        this.placeables.add(Integer.valueOf(18));
        this.placeables.add(Integer.valueOf(19));
        this.placeables.add(Integer.valueOf(20));
        this.placeables.add(Integer.valueOf(36));
        this.placeables.add(Integer.valueOf(41));
        this.placeables.add(Integer.valueOf(42));
        this.placeables.add(Integer.valueOf(43));
        this.placeables.add(Integer.valueOf(45));
        this.placeables.add(Integer.valueOf(46));
        this.placeables.add(Integer.valueOf(47));
        this.placeables.add(Integer.valueOf(48));
        this.placeables.add(Integer.valueOf(49));
        this.placeables.add(Integer.valueOf(54));
        this.placeables.add(Integer.valueOf(56));
        this.placeables.add(Integer.valueOf(57));
        this.placeables.add(Integer.valueOf(58));
        this.placeables.add(Integer.valueOf(73));
        this.placeables.add(Integer.valueOf(87));
        this.placeables.add(Integer.valueOf(88));
        this.placeables.add(Integer.valueOf(89));

        int index = (int) (Math.random() * players.size());
        this.player = players.get(index);
        int breakcheck = 0;

        while (breakcheck < 100) {
            index = (int) (Math.random() * players.size());
            this.player = players.get(index);
            breakcheck++;
        }
        if (breakcheck == 100) {
            return;
        }

        if (HeroSettings.console) {
            iLoveYou.logger.info("[ILY]: " + this.player.getName() + " has gone searching for diamonds");
        }
        Location location = findDiamondsAround(this.player, 20, 25);
        if (location == null) {
            delete();
            return;
        }
        this.yDiamonds = location.y;
        location = topOf(this.player, location.x, location.z);
        location.x += 3.0D;

        double d1 = location.x - this.player.getX();
        double d2 = location.z - this.player.getZ();
        double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
        double d4 = Math.asin((location.y - this.player.getY()) / iLoveYou.distance(this.player.getLocation(), new Location(location.x, location.y, location.z))) * 45.0D;
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
        this.herobrine = new HerobrineCharacter(this.player.getWorld(), location.x, location.y, location.z, (float) d3, (float) d4, new Item(Item.Type.DiamondPickaxe, 1));
        this.herobrine.showOnlyToPlayer(this.player);
        this.herobrine.sendMessage(this.player, getGreeting());
    }

    public String getGreeting(){
        int num = (int) (Math.random() * 40.0D);
        switch (num) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return HeroLanguage.getString("fm");
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return HeroLanguage.getString("ihfs");
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return HeroLanguage.getString("ihsfy");
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return HeroLanguage.getString("diamonds");
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return HeroLanguage.getString("tim");
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                return HeroLanguage.getString("hm");
        }
        return HeroLanguage.getString("stop");
    }

    private Location findDiamondsAround(Player player, int minRadius, int maxRadius){
        double x = player.getX() - maxRadius;
        for (; x < player.getX() + maxRadius; x += 1.0D) {
            double z = player.getZ() - maxRadius;
            for (; z < player.getZ() + maxRadius; z += 1.0D) {
                if (iLoveYou.distance(player.getLocation(), new Location(x, player.getY(), z)) < minRadius) {
                    continue;
                }
                for (double y = player.getY(); y > 0.0D; y -= 1.0D) {
                    if (player.getWorld().getBlockIdAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)) == 56) {
                        return new Location(x, y, z);
                    }
                }
            }
        }
        return null;
    }

    private Location topOf(Player player, double x, double z){
        double y = player.getY();
        while (!iLoveYou.blockIsAvailable(player.getWorld(), x, y, z)) {
            y += 1.0D;
        }
        while (iLoveYou.blockIsAboveAir(player.getWorld(), x, y, z)) {
            y -= 1.0D;
        }
        return new Location(Math.floor(x) + 0.5D, Math.floor(y) + 0.5D, Math.floor(z) + 0.5D);
    }

    @Override
    public void sendMove(Player player, Location from, Location to){
        if (player.equals(this.player) && !this.finalizing) {
            if (!this.waiting) {
                if (iLoveYou.distance(from, this.herobrine.getLocation()) > iLoveYou.distance(to, this.herobrine.getLocation())) {
                    moveHerobrine();
                    this.herobrine.showOnlyToPlayer(player);
                }
            }
            else {
                double d1 = this.herobrine.getX() - to.x;
                double d2 = this.herobrine.getZ() - to.z;
                double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
                double d4 = Math.asin((this.herobrine.getY() - to.y) / iLoveYou.distance(to, new Location(this.herobrine.getX(), this.herobrine.getY(), this.herobrine.getZ()))) * 45.0D;
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
                this.herobrine.teleportTo(this.herobrine.getX(), this.herobrine.getY(), this.herobrine.getZ(), (float) d3, (float) d4);
                this.herobrine.showOnlyToPlayer(player);
                this.herobrine.lookat(this.player);
                if (iLoveYou.distance(to, this.herobrine.getLocation()) < 3.0D) {
                    this.finalizing = true;
                    this.herobrine.sendMessage(player, getFarewell());
                    server.addToServerQueue(new DiamondEvent.BlockRestorer(), 300L);
                    this.herobrine.destroy();
                }
            }
        }
    }

    public void moveHerobrine(){
        double x = this.herobrine.getX();
        double z = this.herobrine.getZ();
        double y = this.herobrine.getY() - 1.0D;

        while (iLoveYou.blockIsAboveAir(herobrine.getWorld(), x, y, z)) {
            placeLadderAndTorch((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
            y -= 1.0D;
        }

        if (Math.floor(y) <= this.yDiamonds) {
            digAroundDiamonds();
            return;
        }

        digBlocksWithLadder(x, y, z);

        double d1 = x - this.player.getX();
        double d2 = z - this.player.getZ();
        double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
        double d4 = Math.asin((y - this.player.getY()) / iLoveYou.distance(this.player.getLocation(), new Location(x, y, z))) * 45.0D;
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
        d3 += 180.0D;

        this.herobrine.teleportTo(x, y, z, (float) d3, (float) d4);
    }

    private void digAroundDiamonds(){
        for (double z = this.herobrine.getZ() + 1.0D; z < this.herobrine.getZ() + 3.0D; z += 1.0D) {
            for (double x = this.herobrine.getX(); x >= this.herobrine.getX() - 4.0D; x -= 1.0D) {
                if (herobrine.getWorld().getBlockIdAt((int) Math.floor(x), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(z)) != 56) {
                    digBlock(x, this.herobrine.getY(), z);
                }
                if (herobrine.getWorld().getBlockIdAt((int) Math.floor(x), (int) Math.floor(this.herobrine.getY() + 1.0D), (int) Math.floor(z)) == 56) {
                    continue;
                }
                digBlock(x, this.herobrine.getY() + 1.0D, z);
            }
        }

        this.herobrine.teleportTo(this.herobrine.getX() - 4.0D, Math.floor(this.herobrine.getY()), this.herobrine.getZ() + 2.0D, this.herobrine.getRotation(), this.herobrine.getPitch());

        this.waiting = true;
    }

    private void digBlocksWithLadder(double x, double y, double z){
        this.blocks.offer(herobrine.getWorld().getBlockAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)));
        herobrine.getWorld().setBlockAt(0, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
        placeLadderAndTorch((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    private void digBlock(double x, double y, double z){
        this.blocks.offer(herobrine.getWorld().getBlockAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)));
        herobrine.getWorld().setBlockAt(0, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
    }

    private void placeLadderAndTorch(int x, int y, int z){
        if (this.placeables.contains(Integer.valueOf(herobrine.getWorld().getBlockIdAt(x - 1, y, z)))) {
            if (this.ladder) {
                Block ladder = new Block(65, x, y, z, 5);
                herobrine.getWorld().setBlock(ladder);
            }
            else {
                if (this.torch) {
                    Block torch = new Block(76, x, y, z, 1);
                    herobrine.getWorld().setBlock(torch);
                }
                this.torch = !this.torch;
            }
            this.ladder = !this.ladder;
        }
    }

    @Override
    public void sendTeleport(Player player, Location from, Location to){
        if (player.equals(this.player) && iLoveYou.distance(from, to) > 10.0D) {
            delete();
        }
    }

    public String getFarewell(){
        int num = (int) (Math.random() * 40.0D);
        switch (num) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return HeroLanguage.getString("haha");
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return HeroLanguage.getString("yghby");
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return HeroLanguage.getString("dyfwyn");
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
                return HeroLanguage.getString("diamonds");
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                return HeroLanguage.getString("lft");
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                return HeroLanguage.getString("ogchyn");
        }
        return HeroLanguage.getString("stop");
    }

    @Override
    public boolean sendCommand(Player player, String[] command){
        return false;
    }

    private class BlockRestorer implements Runnable{

        private BlockRestorer(){}

        @Override
        public void run(){
            if (DiamondEvent.this.blocks.size() > 0) {
                Block block = DiamondEvent.this.blocks.removeFirst();
                herobrine.getWorld().setBlock(block);
                DiamondEvent.server.addToServerQueue(this, 300L);
            }
            else {
                DiamondEvent.this.delete();
            }
        }
    }
}
