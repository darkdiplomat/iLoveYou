public class SpawnEvent extends HerobrineEvent{
    HerobrineCharacter hero;
    Player player;

    public SpawnEvent(Player player){
        this.player = player;
    }

    @Override
    public void trigger(){
        Location location = player.getLocation();
        this.hero = new HerobrineCharacter(player.getWorld(), location.x, location.y, location.z, location.rotX, location.rotY, new Item(Item.Type.RedstoneTorchOn, 1));
        this.hero.showOnlyToPlayer(player);
        this.hero.lookat(player);
        this.hero.sendMessage(player, "Watch your back");
        server.addToServerQueue(new HeroDespawn(), 3000L);
    }

    @Override
    public void sendMove(Player player, Location loc1, Location loc2){
        this.hero.lookat(this.player);
    }

    @Override
    public void sendTeleport(Player player, Location loc1, Location loc2){}

    @Override
    public boolean sendCommand(Player player, String[] args){
        return false;
    }

    @Override
    public void delete(){
        iLoveYou.spawnevent = null;
        this.hero.destroy();
    }

    /**
     * runnable to despawn Herobrine if called via spawnHerobrine command
     * 
     * @author darkdiplomat
     */
    private class HeroDespawn implements Runnable{
        private HeroDespawn(){}

        @Override
        public void run(){
            SpawnEvent.this.delete();
        }
    }
}
