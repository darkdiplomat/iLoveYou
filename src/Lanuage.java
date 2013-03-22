enum Language{
    
    AR, //Arabic
    DE, //German
    EN, //English
    ES, //Spanish
    FI, //Finnish
    FR, //French
    IT, //Italian
    NL, //Dutch
    NO, //Norwegian
    PL, //Polish
    RU, //Russian
    SV; //Swedish

    public static Language getLanguage(String lang) {
        if (lang.toLowerCase().matches("arabic|ar|ara")) {
            return AR;
        }
        if (lang.toLowerCase().matches("german|de|deu|ger")){
            return DE;
        }
        if (lang.toLowerCase().matches("english|en|eng")) {
            return EN;
        }
        if (lang.toLowerCase().matches("spanish|es|esp|spa")) {
            return ES;
        }
        if (lang.toLowerCase().matches("finnish|fi|fin")) {
            return FI;
        }
        if (lang.toLowerCase().matches("french|fr|fra|fre")) {
            return FR;
        }
        if (lang.toLowerCase().matches("italian|it|ita")) {
            return IT;
        }
        if (lang.toLowerCase().matches("dutch|nl|nld|dut")) {
            return NL;
        }
        if (lang.toLowerCase().matches("norwegian|no|nor")) {
            return NO;
        }
        if (lang.toLowerCase().matches("polish|pl|pol")){
            return PL;
        }
        if (lang.toLowerCase().matches("russian|ru|rus")) {
            return RU;
        }
        if (lang.toLowerCase().matches("swedish|sv|swe")) {
            return SV;
        }
        return EN;
    }
}
