//import java.util.ArrayList;

public class BlackoutEvent extends HerobrineEvent{
    //private ArrayList<Integer> placeables = new ArrayList<Integer>();
    Player player;
    Location oldLocation;

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
