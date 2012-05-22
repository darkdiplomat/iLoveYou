import java.util.ArrayList;
import java.util.List;

public class HeroDigger extends HerobrineEvent{
    private HerobrineCharacter herobrine;
    private Location originalLocation;
    private Player player;
    private ArrayList<Integer> prohibitedDrops = new ArrayList<Integer>();
    private static World world = etc.getServer().getDefaultWorld();

    @Override
    public void trigger() {
        this.prohibitedDrops.add(Integer.valueOf(7));
        this.prohibitedDrops.add(Integer.valueOf(8));
        this.prohibitedDrops.add(Integer.valueOf(9));
        this.prohibitedDrops.add(Integer.valueOf(10));
        this.prohibitedDrops.add(Integer.valueOf(11));
        this.prohibitedDrops.add(Integer.valueOf(78));

        List<Player> players = server.getPlayerList();
        if (players.size() == 0) {
            return;
        }
        int index = (int) (Math.random() * players.size());
        this.player = players.get(index);

        if (HeroSettings.console) {
            iLoveYou.logger.info("[ILY]: " + this.player.getName() + " has gone on a chase");
        }
        double theta = Math.random() * 3.141592653589793D * 2.0D;

        double y = this.player.getY();
        double x = this.player.getX() + Math.cos(theta) * 17.0D;
        double z = this.player.getZ() + Math.sin(theta) * 17.0D;

        while (iLoveYou.blockIsAboveAir(x, y, z)) {
            y -= 1.0D;
        }
        while (!iLoveYou.blockIsSafe(x, y, z)) {
            y += 1.0D;
        }

        double d1 = x - this.player.getX();
        double d2 = z - this.player.getZ();
        double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
        double d4 = Math.asin((y - this.player.getY()) / iLoveYou.distance(this.player.getLocation(), new Location(x, y, z))) * 45.0D;
        if (d1 >= 0.0D) {
            if (d2 >= 0.0D) {
                d3 = 180.0D - d3;
            }
        } else if (d2 >= 0.0D) {
            d3 += 180.0D;
        } else {
            d3 = 360.0D - d3;
        }
        this.herobrine = new HerobrineCharacter(x, y, z, (float) d3, (float) d4, -1, this.player);
        this.originalLocation = new Location(x, y, z);
        this.herobrine.broadcast(this.player);

        int num = (int) (Math.random() * 3.0D);
        if (num > 1) {
            this.herobrine.sendMessage(this.player, getGreeting());
        }
    }

    @Override
    public void delete() {
        super.delete();
        this.herobrine.delete();
    }

    @Override
    public void sendMove(Player player, Location from, Location to) {
        if (player.equals(this.player) && iLoveYou.distance(from, this.herobrine.getLocation()) > iLoveYou.distance(to, this.herobrine.getLocation())) {
            double deltax = (to.x - from.x) * 1.08D;
            double deltaz = (to.z - from.z) * 1.08D;
            moveHerobrine(deltax, 0.0D, deltaz, to);

            if (iLoveYou.distance(to, this.herobrine.getLocation()) > 50.0D) {
                delete();
            }
            this.herobrine.broadcast(player);
        }
    }

    @Override
    public void sendTeleport(Player player, Location from, Location to){
        if (player.equals(this.player) && iLoveYou.distance(from, to) > 10.0D) {
            delete();
        }
    }

    public String getGreeting() {
        int num = (int) (Math.random() * 40.0D);
        switch (num) {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            return HeroLanguage.getString("stop");
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
            return HeroLanguage.getString("ysnbh");
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
            return HeroLanguage.getString("hm");
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 26:
            return "...";
        case 27:
        case 28:
        case 29:
        case 30:
        case 31:
        case 32:
            return HeroLanguage.getString("wayd");
        case 33:
        case 34:
        case 35:
        case 36:
        case 37:
        case 38:
        case 39:
            return HeroLanguage.getString("ycndt");
        }
        return HeroLanguage.getString("stop");
    }

    public String stopFollowing() {
        int num = (int) (Math.random() * 40.0D);
        switch (num) {
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
            return HeroLanguage.getString("sfm");
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
            return HeroLanguage.getString("ikyafm");
        case 12:
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
            return HeroLanguage.getString("no");
        case 19:
        case 20:
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 26:
            return HeroLanguage.getString("si");
        case 27:
        case 28:
        case 29:
        case 30:
        case 31:
        case 32:
            return HeroLanguage.getString("ycncm");
        case 33:
        case 34:
        case 35:
        case 36:
        case 37:
        case 38:
        case 39:
            return HeroLanguage.getString("haha");
        }
        return HeroLanguage.getString("stop");
    }

    public void moveHerobrine(double dx, double dy, double dz, Location loc) {
        double x = this.herobrine.getX() + dx;
        double z = this.herobrine.getZ() + dz;
        double y = this.herobrine.getY() + dy;

        while (iLoveYou.blockIsAboveAir(x, y, z)) {
            y -= 1.0D;
        }

        y += -0.3D;

        if (Math.floor(y) <= 6.0D) {
            buildHouse();
            y = 6.0D;
        }

        if (Math.floor(x) - Math.floor(this.herobrine.getX()) >= 1.0D && Math.abs(Math.floor(z) - Math.floor(this.herobrine.getZ())) >= 1.0D) {
            x -= 1.0D;
        }

        if (Math.floor(x) - Math.floor(this.herobrine.getX()) <= -1.0D && Math.abs(Math.floor(z) - Math.floor(this.herobrine.getZ())) >= 1.0D) {
            x += 1.0D;
        }

        digBlocks(x, y, z);

        if (Math.floor(x) - Math.floor(this.herobrine.getX()) >= 2.0D) {
            digBlocks(x - 1.0D, y, z);
        } else if (Math.floor(x) - Math.floor(this.herobrine.getX()) <= -2.0D) {
            digBlocks(x + 1.0D, y, z);
        }

        if (Math.floor(z) - Math.floor(this.herobrine.getZ()) >= 2.0D) {
            digBlock(x, y, z - 1.0D);
        } else if (Math.floor(x) - Math.floor(this.herobrine.getX()) <= -2.0D) {
            digBlocks(x, y, z + 1.0D);
        }

        double d1 = x - loc.x;
        double d2 = z - loc.z;
        double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
        double d4 = Math.asin((y - loc.y) / iLoveYou.distance(loc, new Location(x, y, z))) * 45.0D;
        if (d1 >= 0.0D) {
            if (d2 >= 0.0D) {
                d3 = 180.0D - d3;
            }
        } else if (d2 >= 0.0D) {
            d3 += 180.0D;
        } else {
            d3 = 360.0D - d3;
        }
        d3 += 180.0D;

        this.herobrine.teleportTo(x, y, z, (float) d3, (float) d4);

        if (iLoveYou.distance(this.originalLocation, new Location(x, y, z, (float) d3, (float) d4)) > 130.0D) {
            this.herobrine.sendMessage(this.player, stopFollowing());
            this.originalLocation = new Location(x, y, z, (float) d3, (float) d4);
        }
    }

    private void digBlocks(double x, double y, double z) {
        if (canDigBlock(x, y, z)) {
            digBlock(x, y, z);
            if (Math.random() < 0.1D) {
                putTorch(x, y, z);
            }
            if (Math.random() < 0.015D) {
                spawnCreeper(x, y, z);
            }
        }
        if (canDigBlock(x, y + 1.0D, z)) {
            digBlock(x, y + 1.0D, z);
        }
        if (canDigBlock(x, y + 2.0D, z)) {
            digBlock(x, y + 2.0D, z);
        }
    }

    private void putTorch(double x, double y, double z) {
        Block block = new Block(76, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), 5);
        world.setBlock(block);
    }

    private void spawnCreeper(double x, double y, double z) {
        Mob mob = new Mob("Creeper", new Location(x, y, z));
        mob.spawn();
    }

    private void digBlock(double x, double y, double z) {
        int blockType = world.getBlockIdAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
        world.setBlockAt(0, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
        if (blockType > 0 && !this.prohibitedDrops.contains(Integer.valueOf(blockType)) && Math.random() < 0.2D) {
            world.dropItem(x, y, z, blockType);
        }
    }

    private boolean canDigBlock(double x, double y, double z) {
        int blockType = world.getBlockIdAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));

        return !HeroSettings.prohibitedBlocks.contains(Integer.valueOf(blockType));
    }

    private void buildHouse() {
        double x = this.herobrine.getX();
        double z = this.herobrine.getZ();

        for (int i = 0; i < 10; i++) {
            for (int j = -3; j < 4; j++) {
                if (Math.random() < 0.3D) {
                    world.setBlockAt(13, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY() - 1.0D), (int) Math.floor(j + z));
                } else {
                    world.setBlockAt(48, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY() - 1.0D), (int) Math.floor(j + z));
                }

                if (i == 0 || i == 9 || j == -3 || j == 3) {
                    if (Math.random() < 0.5D) {
                        world.setBlockAt(0, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(j + z));
                    }
                } else {
                    world.setBlockAt(0, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(j + z));
                }

                if (Math.random() < 0.2D) {
                    Block block = new Block(76, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(i + z));
                    world.setBlock(block);
                }
                world.setBlockAt(0, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY() + 1.0D), (int) Math.floor(j + z));
                world.setBlockAt(0, (int) Math.floor(i + x), (int) Math.floor(this.herobrine.getY() + 2.0D), (int) Math.floor(j + z));
            }
        }

        int workbench = (int) (Math.random() * 4.0D);
        double xBench = 0.0D;
        double zBench = 0.0D;
        switch (workbench) {
        case 0:
            xBench = 9.0D + x;
            zBench = Math.random() * 7.0D - 3.0D + z;
        case 1:
            xBench = x;
            zBench = Math.random() * 7.0D - 3.0D + z;
        case 2:
            xBench = x + Math.random() * 10.0D;
            zBench = -3.0D + z;
        case 3:
            xBench = x + Math.random() * 10.0D;
            zBench = 3.0D + z;
        }

        world.setBlockAt(58, (int) Math.floor(xBench), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(zBench));
        world.setBlockAt(76, (int) Math.floor(xBench) - 1, (int) Math.floor(this.herobrine.getY()), (int) Math.floor(zBench));

        int chest = (int) (Math.random() * 4.0D);
        double xChest = 0.0D;
        double zChest = 0.0D;
        switch (chest) {
        case 0:
            xChest = 9.0D + x;
            zChest = Math.random() * 7.0D - 3.0D + z;
        case 1:
            xChest = x;
            zChest = Math.random() * 7.0D - 3.0D + z;
        case 2:
            xChest = x + Math.random() * 10.0D;
            zChest = -3.0D + z;
        case 3:
            xChest = x + Math.random() * 10.0D;
            zChest = 3.0D + z;
        }

        world.setBlockAt(54, (int) Math.floor(xChest), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(zChest));
        world.setBlockAt(76, (int) Math.floor(xBench) - 1, (int) Math.floor(this.herobrine.getY()), (int) Math.floor(zBench));

        ComplexBlock blocky = world.getComplexBlock((int) Math.floor(xChest), (int) Math.floor(this.herobrine.getY()), (int) Math.floor(zChest));
        if (blocky != null && blocky instanceof Chest) {
            Chest chesty = (Chest) blocky;
            if (Math.random() < 0.8D) {
                chesty.addItem(new Item(349, (int) (Math.random() * 10.0D + 14.0D)));
            }
            if (Math.random() < 0.6D) {
                chesty.addItem(new Item(15, (int) (Math.random() * 10.0D + 12.0D)));
            }
            if (Math.random() < 0.1D) {
                chesty.addItem(new Item(264, 2));
            }
            if (Math.random() < 0.5D) {
                chesty.addItem(new Item(289, 7));
            }
            if (Math.random() < 0.7D) {
                chesty.addItem(new Item(280, (int) (Math.random() * 50.0D)));
            }
            if (Math.random() < 0.9D) {
                chesty.addItem(new Item(76, (int) (Math.random() * 30.0D + 32.0D)));
            }
            if (Math.random() < 0.8D) {
                chesty.addItem(new Item(16, (int) (Math.random() * 10.0D + 15.0D)));
            }
            if (Math.random() < 0.8D) {
                chesty.addItem(new Item(38, (int) (Math.random() * 7.0D + 10.0D)));
            }
            chesty.update();
        }

        Mob mob = new Mob("Creeper", new Location(x + 5.0D, this.herobrine.getY(), z));
        mob.spawn();
        mob = new Mob("Creeper", new Location(x + 5.0D, this.herobrine.getY(), z));
        mob.spawn();
        delete();
    }

    @Override
    public boolean sendCommand(Player player, String[] command) {
        return false;
    }
}
