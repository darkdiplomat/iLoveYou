import java.util.ArrayList;
import java.util.LinkedList;

public class ThiefEvent extends HerobrineEvent{
    public static ArrayList<Block> chests = new ArrayList<Block>();
    public HerobrineCharacter herobrine;
    public static LinkedList<Block> blocks = new LinkedList<Block>();
    World world;

    public ThiefEvent(){
        EventName = "ThiefEvent";
    }

    @Override
    public void delete(){
        super.delete();
        if (this.herobrine != null) {
            this.herobrine.destroy();
        }
        restoreBlocks();
    }

    public void restoreBlocks(){
        while (blocks.size() > 0) {
            Block block = blocks.removeFirst();
            world.setBlock(block);
        }
    }

    @Override
    public void trigger(){
        if (chests.size() == 0) {
            delete();
            return;
        }

        for (int i = 0; i < chests.size(); i++) {
            Block block = chests.get(i);
            ComplexBlock possible = world.getComplexBlock(block.getX(), block.getY(), block.getZ());
            if (possible == null || !(possible instanceof Chest)) {
                chests.remove(block);
                i--;
            }
            else {
                if (!world.isChunkLoaded(block)) {
                    world.loadChunk(block);
                }
                if (fartherThan20(block)) {
                    triggerAroundChest((Chest) possible);
                    return;
                }

            }

        }

        delete();
    }

    private boolean fartherThan20(Block block){
        for (Player player : server.getPlayerList()) {
            if (iLoveYou.distance(player.getLocation(), new Location(block.getX(), block.getY(), block.getZ())) < 20.0D) {
                return false;
            }
        }
        return true;
    }

    private void triggerAroundChest(Chest possible){
        if (!iLoveYou.blockIsSafe(possible.getWorld(), possible.getX(), possible.getY() + 1, possible.getZ())) {
            clearBlocks(possible.getX(), possible.getY() + 1, possible.getZ());
        }

        double x = possible.getX() + 0.5D;
        double y = possible.getY() + 1;
        double z = possible.getZ() + 0.5D;

        Player nearest = findNearestPlayer(x, y, z);

        double d1 = x + 0.5D - nearest.getX();
        double d2 = z + 0.5D - nearest.getZ();
        double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
        double d4 = Math.asin((y - nearest.getY()) / iLoveYou.distance(nearest.getLocation(), new Location(x, y, z))) * 45.0D;
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
        this.herobrine = new HerobrineCharacter(nearest.getWorld(), x, y, z, (float) d3, (float) d4, new Item(Item.Type.RedstoneTorchOn, 1));
        this.herobrine.spawn();
        robChest(possible);
    }

    private void robChest(Chest possible){
        Item[] contents = possible.getContents();
        for (int i = 0; i < contents.length; i++) {
            Item item = contents[i];
            if (item != null) {
                double rand = Math.random();
                if (rand < 0.2D) {
                    contents[i] = null;
                    world.dropItem(new Location(possible.getX(), possible.getY(), possible.getZ()), item.getItemId(), item.getAmount());
                }
                else if (rand < 0.5D) {
                    contents[i] = null;
                }
            }
        }
        possible.clearContents();

        for (int i = 0; i < contents.length; i++) {
            if (contents[i] != null) {
                possible.setSlot(contents[i], i);
            }
        }
        possible.update();
    }

    private Player findNearestPlayer(double x, double y, double z){
        Location location = new Location(x, y, z);
        if (server.getPlayerList().size() == 0) {
            return null;
        }
        Player nearest = server.getPlayerList().get(0);
        double distance = iLoveYou.distance(nearest.getLocation(), location);

        for (int i = 1; i < server.getPlayerList().size(); i++) {
            Player current = server.getPlayerList().get(i);
            double newDist = iLoveYou.distance(current.getLocation(), location);
            if (newDist < distance) {
                distance = newDist;
                nearest = current;
            }
        }
        return nearest;
    }

    private void clearBlocks(int x, int y, int z){
        blocks.offer(world.getBlockAt((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z)));
        world.setBlockAt(0, (int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));
        blocks.offer(world.getBlockAt((int) Math.floor(x), (int) Math.floor(y + 1), (int) Math.floor(z)));
        world.setBlockAt(0, (int) Math.floor(x), (int) Math.floor(y + 1), (int) Math.floor(z));
    }

    @Override
    public void sendMove(Player player, Location from, Location to){
        if (iLoveYou.distance(to, this.herobrine.getLocation()) < 10.0D) {
            delete();
        }
        else {
            Player nearest = findNearestPlayer(this.herobrine.getX(), this.herobrine.getY(), this.herobrine.getZ());

            double d1 = this.herobrine.getX() - nearest.getX();
            double d2 = this.herobrine.getZ() - nearest.getZ();
            double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
            double d4 = Math.asin((this.herobrine.getY() - nearest.getY()) / iLoveYou.distance(nearest.getLocation(), new Location(this.herobrine.getX(), this.herobrine.getY(), this.herobrine.getZ()))) * 45.0D;
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
            this.herobrine.spawn();
            this.herobrine.lookat(nearest);
        }
    }

    @Override
    public void sendTeleport(Player player, Location from, Location to){
        if (iLoveYou.distance(to, this.herobrine.getLocation()) < 10.0D) {
            delete();
        }
    }

    public static void logChest(Block blockClicked){
        if (!chests.contains(blockClicked)) {
            chests.add(blockClicked);
        }
    }

    @Override
    public boolean sendCommand(Player player, String[] command){
        return false;
    }
}
