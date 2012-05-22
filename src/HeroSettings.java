import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HeroSettings{
    public static ArrayList<Integer> prohibitedBlocks;
    public static double hours;
    public static HashMap<HeroSettings.Event, Boolean> events;
    public static String name;
    public static Language language;
    public static boolean console;

    public static void importSettings(){
        try{
            PropertiesFile file = new PropertiesFile("iLoveYouVanilla.properties");
            file.load();
            prohibitedBlocks = processBlockList(file.getString("blacklistBlocks", "7"));
            language = Language.getLanguage(file.getString("language", "en"));
            file.save();
        } catch (IOException e) {
            System.out.println("[ILY]: Unable to create iLoveYouVanilla.properties");
        }

        try{
            PropertiesFile file = new PropertiesFile("iLoveYou.properties");
            file.load();

            name = file.getString("name", "Herobrine");
            hours = file.getDouble("hours", 40.0D);
            HeroLanguage.loadLanguages();

            events = new HashMap<Event, Boolean>();
            events.put(HeroSettings.Event.DIGGER, Boolean.valueOf(file.getBoolean("digger", true)));
            events.put(HeroSettings.Event.ALTAR, Boolean.valueOf(file.getBoolean("altar", true)));
            events.put(HeroSettings.Event.DIAMONDS, Boolean.valueOf(file.getBoolean("diamonds", true)));
            events.put(HeroSettings.Event.THIEF, Boolean.valueOf(file.getBoolean("thief", true)));
            events.put(HeroSettings.Event.GHOST, Boolean.valueOf(file.getBoolean("ghost", true)));

            console = file.getBoolean("consoleinfo", false);

            file.save();
        } catch (IOException e) {
            System.out.println("[ILY]: Unable to create iLoveYou.properties");
        }
    }

    private static ArrayList<Integer> processBlockList(String string){
        ArrayList<Integer> data = new ArrayList<Integer>();
        if (string == null) {
            return new ArrayList<Integer>();
        }
        String[] list = string.split(",");
        for (String str : list) {
            if (str.equals("")) {
                continue;
            }
            try {
                data.add(Integer.valueOf(Integer.parseInt(str)));
            } catch (Exception e) {
                System.out.println("Invalid block id value: " + str);
            }
        }
        return data;
    }

    public static enum Event{
        DIGGER,
        ALTAR,
        STALKER,
        DIAMONDS,
        BLACKOUT,
        THIEF,
        TREESCRAPER,
        PYRAMID,
        GHOST;
    }
}
