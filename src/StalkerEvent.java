public class StalkerEvent extends HerobrineEvent{
    
    @Override
    public void trigger(){
    }

    @Override
    public void sendMove(Player player, Location from, Location to){
    }

    @Override
    public void sendTeleport(Player player, Location from, Location to){
    }

    @Override
    public boolean sendCommand(Player player, String[] command){
        return false;
    }
}
