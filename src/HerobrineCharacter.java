public class HerobrineCharacter extends NonPlayableCharacter{

    public HerobrineCharacter(World world, double x, double y, double z, float rotation, float pitch, Item itemInHand){
        super(HeroSettings.name, world, x, y, z, rotation, pitch, itemInHand);
    }

    public void sendMessage(Player player, String message){
        player.sendMessage(String.format("(MSG) <%s%s%s> %s", Colors.Gold, HeroSettings.name, Colors.White, message));
    }

    public void showOnlyToPlayer(Player player){
        this.spawn();
        try {
            for (Player non : etc.getServer().getPlayerList()) {
                if (!non.equals(player)) {
                    hideNPCFrom(non);
                }
            }
        }
        catch (Exception ex) {}
    }

    private void hideNPCFrom(Player player){
        player.getUser().a.b(new OPacket29DestroyEntity(this.getId()));
    }
}
