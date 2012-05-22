public class HerobrineCharacter extends NonPlayerCharacter{
    //private Player player;

    public HerobrineCharacter(double x, double y, double z, float rotation, float pitch, int itemInHand, Player player){
        super(HeroSettings.name, x, y, z, rotation, pitch, itemInHand);
        //this.player = player;
    }

    public Location getLocation() {
        return new Location(getX(), getY(), getZ());
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(String.format("(MSG) <%s%s%s> %s", Colors.Gold, HeroSettings.name, Colors.White, message ));
    }
}
