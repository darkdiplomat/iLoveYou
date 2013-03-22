import java.util.List;

public class GhostEvent extends HerobrineEvent{
    private HerobrineCharacter herobrine;
    private Player player;

    public GhostEvent(){
        EventName = "GhostEvent";
    }

    @Override
    public void trigger(){
        List<Player> players = server.getPlayerList();
        if (players.size() == 0) {
            return;
        }
        int index = (int) (Math.random() * players.size());
        this.player = players.get(index);

        if (HeroSettings.console) {
            iLoveYou.logger.info("[ILY]: " + this.player.getName() + " has been ghosted");
        }

        double theta = Math.random() * 3.141592653589793D * 2.0D;

        double y = this.player.getY();
        double x = this.player.getX() + Math.cos(theta) * 17.0D;
        double z = this.player.getZ() + Math.sin(theta) * 17.0D;

        while (iLoveYou.blockIsAboveAir(player.getWorld(), x, y, z)) {
            y -= 1.0D;
        }
        while (!iLoveYou.blockIsSafe(player.getWorld(), x, y, z)) {
            y += 1.0D;
        }

        double d1 = x - this.player.getX();
        double d2 = z - this.player.getZ();
        double d3 = Math.toDegrees(Math.atan(Math.abs(d1 / d2)));
        double d4 =
                Math.asin((y - this.player.getY()) /
                        iLoveYou.distance(this.player.getLocation(),
                                new Location(x, y, z))) * 45.0D;
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
        this.herobrine = new HerobrineCharacter(this.player.getWorld(), x, y, z, (float) d3, (float) d4, new Item(Item.Type.RedstoneTorchOn, 1));
        this.herobrine.showOnlyToPlayer(this.player);
    }

    @Override
    public void delete(){
        super.delete();
        this.herobrine.destroy();
    }

    @Override
    public void sendMove(Player player, Location from, Location to){
        if (player.equals(this.player)) {
            if (iLoveYou.distance(from, this.herobrine.getLocation()) > iLoveYou.distance(to, this.herobrine.getLocation())) {
                delete();
                return;
            }

            if (iLoveYou.distance(to, this.herobrine.getLocation()) > 50.0D) {
                delete();
                return;
            }
            this.herobrine.showOnlyToPlayer(player);
            this.herobrine.lookat(this.player);
        }
    }

    @Override
    public void sendTeleport(Player player, Location from, Location to){
        if (player.equals(this.player) && iLoveYou.distance(from, to) > 10.0D) {
            delete();
        }
    }

    @Override
    public boolean sendCommand(Player player, String[] command){
        return false;
    }
}
