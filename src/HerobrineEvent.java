public abstract class HerobrineEvent{
    protected static Server server = iLoveYou.server;
    public static WheelSpinner<HeroSettings.Event> wheel = new WheelSpinner<HeroSettings.Event>();
    protected String EventName = "Generic";

    public static void initializeWheel(){
        if (HeroSettings.events.get(HeroSettings.Event.DIGGER)) {
            wheel.add(HeroSettings.Event.DIGGER, 1.0D, 1);
        }
        if (HeroSettings.events.get(HeroSettings.Event.DIAMONDS)) {
            wheel.add(HeroSettings.Event.DIAMONDS, 2.0D, 2);
        }

        if (HeroSettings.events.get(HeroSettings.Event.THIEF)) {
            wheel.add(HeroSettings.Event.THIEF, 1.0D, 3);
        }

        if (HeroSettings.events.get(HeroSettings.Event.GHOST)) {
            wheel.add(HeroSettings.Event.GHOST, 2.0D, 4);
        }

        // if (HeroSettings.events.get(HeroSettings.Event.TREESCRAPER)) {
        // wheel.add(HeroSettings.Event.TREESCRAPER, 2.0D, 5);
        // }
    }

    public String getEventName(){
        return EventName;
    }

    public static HerobrineEvent getEvent(){
        HeroSettings.Event picked = (HeroSettings.Event) wheel.spinAll(server.getPlayerList().size() * 5);

        if (picked == HeroSettings.Event.DIGGER) {
            return new HeroDigger();
        }
        if (picked == HeroSettings.Event.DIAMONDS) {
            return new DiamondEvent();
        }
        if (picked == HeroSettings.Event.BLACKOUT) {
            return new BlackoutEvent();
        }
        if (picked == HeroSettings.Event.STALKER) {
            return new StalkerEvent();
        }
        if (picked == HeroSettings.Event.THIEF) {
            return new ThiefEvent();
        }
        if (picked == HeroSettings.Event.TREESCRAPER) {
            return new TreeScraper();
        }
        if (picked == HeroSettings.Event.PYRAMID) {
            return new PyramidBuilder();
        }
        if (picked == HeroSettings.Event.GHOST) {
            return new GhostEvent();
        }
        return null;
    }

    public abstract void trigger();

    public abstract void sendMove(Player player, Location loc1, Location loc2);

    public abstract void sendTeleport(Player player, Location loc1, Location loc2);

    public abstract boolean sendCommand(Player player, String[] args);

    public void delete(){
        iLoveYou.currEvent = null;
    }
}
