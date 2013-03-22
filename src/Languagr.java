import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Languagr{
    public HashMap<Language, HashMap<String, String>> langMap = new HashMap<Language, HashMap<String, String>>();
    public HashMap<String, String> currentMap;
    public Language currentLanguage;

    public void putKey(String key, Map.Entry<Language, String>[] languages){
        for (Map.Entry<Language,String> languageEntry : languages) {
            if (this.langMap.containsKey(languageEntry.getKey())) {
                HashMap<String, String> languageMap = this.langMap.get(languageEntry.getKey());
                languageMap.put(key, (String) languageEntry.getValue());
            } else {
                HashMap<String, String> languageMap = new HashMap<String, String>();
                languageMap.put(key, languageEntry.getValue());
                this.langMap.put(languageEntry.getKey(), languageMap);
            }
        }
    }

    public void putKey(String key, Collection<Map.Entry<Language, String>> languages){
        for (Map.Entry<Language,String> languageEntry : languages) {
            if (this.langMap.containsKey(languageEntry.getKey())) {
                HashMap<String, String> languageMap = this.langMap.get(languageEntry.getKey());
                languageMap.put(key, languageEntry.getValue());
            } else {
                HashMap<String, String> languageMap = new HashMap<String, String>();
                languageMap.put(key, languageEntry.getValue());
                this.langMap.put(languageEntry.getKey(), languageMap);
            }
        }
    }

    public void setCurrLanguage(Language language){
        if (this.langMap.containsKey(language)) {
            this.currentLanguage = language;
            this.currentMap = this.langMap.get(language);
        } else {
            this.currentLanguage = null;
            this.currentMap = null;
        }
    }

    public String get(String key) {
        if (this.currentMap == null) {
            return null;
        }
        return this.currentMap.get(key);
    }

    public String get(String key, Language language) {
        HashMap<String, String> currentMap = this.langMap.get(language);
        if (currentMap == null) {
            return null;
        }
        return currentMap.get(key);
    }
}
