import java.util.HashMap;

public class HeroLanguage{
    public static Languagr languages;

    public static void loadLanguages(){
        languages = new Languagr();
        HashMap<Language, String> map = new HashMap<Language, String>();
        
        map.put(Language.EN, "I love you too.");
        map.put(Language.AR, "أنا أحبك جدا");
        map.put(Language.DE, "Ich liebe dich auch.");
        map.put(Language.ES, "Te amo demasiado.");
        map.put(Language.FR, "Je t'aime aussi.");
        map.put(Language.IT, "Anch'io ti amo.");
        map.put(Language.NL, "Ik hou ook van jou.");
        map.put(Language.NO, "Jeg elsker deg også.");
        map.put(Language.PL, "Kocham cię");
        map.put(Language.RU, "Я тоже тебя люблю");
        map.put(Language.SV, "Jag älskar dig också.");
        languages.putKey("ilyt", map.entrySet());
        map.clear();

        map.put(Language.EN, "Stop.");
        map.put(Language.AR, "توقف");
        map.put(Language.DE, "Halt.");
        map.put(Language.ES, "Detener.");
        map.put(Language.FR, "Arrêter.");
        map.put(Language.IT, "Stop.");
        map.put(Language.NL, "Stop.");
        map.put(Language.NO, "Stopp.");
        map.put(Language.RU, "Стоп.");
        map.put(Language.SV, "Stopp.");
        languages.putKey("stop", map.entrySet());
        map.clear();

        map.put(Language.EN, "You should not be here.");
        map.put(Language.AR, "يجب أن لا يكون هنا");
        map.put(Language.DE, "Sie sollten nicht hier sein.");
        map.put(Language.ES, "Usted no debería estar aquí.");
        map.put(Language.FR, "Vous ne devriez pas être ici.");
        map.put(Language.IT, "Non dovresti essere qui.");
        map.put(Language.NL, "Je moet hier niet zijn.");
        map.put(Language.NO, "Du bør ikke være her.");
        map.put(Language.RU, "Вы не должны быть здесь.");
        map.put(Language.SV, "Du ska inte vara här.");
        languages.putKey("ysnbh", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Turn around.");
        map.put(Language.AR, "بدوره حولها");
        map.put(Language.DE, "Dreh dich um.");
        map.put(Language.ES, "Date la vuelta.");
        map.put(Language.FR, "Tournez-vous.");
        map.put(Language.IT, "Girati.");
        map.put(Language.NL, "Draai je om.");
        map.put(Language.NO, "Snu.");
        map.put(Language.RU, "Повернись");
        map.put(Language.SV, "Vänd dig om.");
        languages.putKey("ta", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Stop following me!");
        map.put(Language.AR, "بعد توقف لي");
        map.put(Language.DE, "Höre auf mir zu folgen!");
        map.put(Language.ES, "Deja de seguirme!");
        map.put(Language.FR, "Arrêtez de me suivre!");
        map.put(Language.IT, "Smettere di seguire me!");
        map.put(Language.NL, "Stop met me volgt!");
        map.put(Language.NO, "Slutt å følge meg!");
        map.put(Language.RU, "Прекратите меня преследовать!");
        map.put(Language.SV, "Sluta efter mig!");
        languages.putKey("sfm", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "You can not do that.");
        map.put(Language.AR, "لا يمكنك أن تفعل ذلك");
        map.put(Language.DE, "Sie können das nicht tun.");
        map.put(Language.ES, "Usted no puede hacer eso.");
        map.put(Language.FR, "Vous ne pouvez pas faire cela.");
        map.put(Language.IT, "Non puoi farlo.");
        map.put(Language.NL, "U kunt niet doen.");
        map.put(Language.NO, "Du kan ikke gjøre det.");
        map.put(Language.RU, "Вы не можете это сделать.");
        map.put(Language.SV, "Du kan inte göra det.");
        languages.putKey("ycndt", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Help me!");
        map.put(Language.AR, "مساعدتي");
        map.put(Language.DE, "Helfen Sie mir!");
        map.put(Language.ES, "¡Ayúdame!");
        map.put(Language.FR, "Aidez-moi!");
        map.put(Language.IT, "Aiutami!");
        map.put(Language.NL, "Help mij!");
        map.put(Language.NO, "Hjelp meg!");
        map.put(Language.RU, "Помоги мне!");
        map.put(Language.SV, "Hjälp mig!");
        languages.putKey("hm", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "What are you doing?");
        map.put(Language.AR, "ماذا تفعل");
        map.put(Language.DE, "Was machst du da?");
        map.put(Language.ES, "¿Qué estás haciendo?");
        map.put(Language.FR, "Que faites-vous?");
        map.put(Language.IT, "Cosa stai facendo?");
        map.put(Language.NL, "Wat doe je?");
        map.put(Language.NO, "Hva gjør du?");
        map.put(Language.RU, "Что вы делаете?");
        map.put(Language.SV, "Vad gör du?");
        languages.putKey("wayd", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "I know you are following me.");
        map.put(Language.AR, "وأنا أعلم أنك لي بعد");
        map.put(Language.DE, "Ich weiß, du hinter mir her sind.");
        map.put(Language.ES, "Sé que me están siguiendo.");
        map.put(Language.FR, "Je sais que vous me suivez.");
        map.put(Language.IT, "So che mi stanno seguendo.");
        map.put(Language.NL, "Ik weet dat je me volgt.");
        map.put(Language.NO, "Jeg vet at du følger meg.");
        map.put(Language.RU, "Я знаю, что вы за мной.");
        map.put(Language.SV, "Jag vet att du följer mig.");
        languages.putKey("ikyafm", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "You are so foolish.");
        map.put(Language.AR, "أنت أحمق جدا");
        map.put(Language.DE, "Sie sind so dumm.");
        map.put(Language.ES, "Eres tan tonto.");
        map.put(Language.FR, "Vous êtes si sotte.");
        map.put(Language.IT, "Tu sei così stupido.");
        map.put(Language.NL, "Je bent zo dom.");
        map.put(Language.NO, "Du er så tåpelig.");
        map.put(Language.RU, "Вы так глупо.");
        map.put(Language.SV, "Du är så dumt.");
        languages.putKey("yasf", map.entrySet());
        map.clear();

        map.put(Language.EN, "No.");
        map.put(Language.AR, "رقم");
        map.put(Language.DE, "Nein.");
        map.put(Language.ES, "No.");
        map.put(Language.FR, "Non.");
        map.put(Language.IT, "No.");
        map.put(Language.NL, "Nee.");
        map.put(Language.NO, "Nei.");
        map.put(Language.RU, "Нет.");
        map.put(Language.SV, "Nej.");
        languages.putKey("no", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Stop it.");
        map.put(Language.AR, "وقفه");
        map.put(Language.DE, "Hör auf.");
        map.put(Language.ES, "Basta ya.");
        map.put(Language.FR, "Arrêtez.");
        map.put(Language.IT, "Smettila.");
        map.put(Language.NL, "Stop ermee.");
        map.put(Language.NO, "Stopp den.");
        map.put(Language.RU, "Остановить ее.");
        map.put(Language.SV, "Sluta.");
        languages.putKey("si", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Diamonds");
        map.put(Language.AR, "الماس");
        map.put(Language.DE, "Diamanten");
        map.put(Language.ES, "Diamantes");
        map.put(Language.FR, "Diamants");
        map.put(Language.IT, "Quadri");
        map.put(Language.NL, "Ruiten");
        map.put(Language.NO, "Diamonds");
        map.put(Language.RU, "Бриллианты");
        map.put(Language.SV, "Diamanter");
        languages.putKey("diamonds", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "You can not catch me.");
        map.put(Language.AR, "لا يمكنك اللحاق بي");
        map.put(Language.DE, "Du kannst mich nicht fangen.");
        map.put(Language.ES, "No me pueden atrapar.");
        map.put(Language.FR, "Vous ne pouvez pas me rattraper.");
        map.put(Language.IT, "Non puoi prendere.");
        map.put(Language.NL, "U kunt me niet te pakken.");
        map.put(Language.NO, "Du kan ikke ta meg.");
        map.put(Language.RU, "Вы не можете поймать меня.");
        map.put(Language.SV, "Du kan inte fånga mig.");
        languages.putKey("ycncm", map.entrySet());
        map.clear();

        map.put(Language.EN, "haha");
        map.put(Language.AR, "haha");
        map.put(Language.DE, "haha");
        map.put(Language.ES, "haha");
        map.put(Language.FR, "haha");
        map.put(Language.IT, "haha");
        map.put(Language.NL, "haha");
        map.put(Language.NO, "haha");
        map.put(Language.RU, "haha");
        map.put(Language.SV, "haha");
        languages.putKey("haha", map.entrySet());
        map.clear();

        map.put(Language.EN, "ha");
        map.put(Language.AR, "ha");
        map.put(Language.DE, "ha");
        map.put(Language.ES, "ha");
        map.put(Language.FR, "ha");
        map.put(Language.IT, "ha");
        map.put(Language.NL, "ha");
        map.put(Language.NO, "ha");
        map.put(Language.RU, "hа");
        map.put(Language.SV, "ha");
        languages.putKey("ha", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "I am here.");
        map.put(Language.AR, "وأنا هنا");
        map.put(Language.DE, "Ich bin hier.");
        map.put(Language.ES, "Yo estoy aquí.");
        map.put(Language.FR, "Je suis ici.");
        map.put(Language.IT, "Io sono qui.");
        map.put(Language.NL, "Ik ben hier.");
        map.put(Language.NO, "Jeg er her.");
        map.put(Language.RU, "Я здесь.");
        map.put(Language.SV, "Jag är här.");
        languages.putKey("iah", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Why would you do that?");
        map.put(Language.AR, "لماذا تفعل ذلك");
        map.put(Language.DE, "Warum würden Sie das tun?");
        map.put(Language.ES, "¿Por qué hiciste eso?");
        map.put(Language.FR, "Pourquoi voudriez-vous faire?");
        map.put(Language.IT, "Perché l'hai fatto?");
        map.put(Language.NL, "Waarom zou je dat doen?");
        map.put(Language.NO, "Hvorfor skulle du gjøre det?");
        map.put(Language.RU, "Зачем вы это делаете?");
        map.put(Language.SV, "Varför skulle du göra det?");
        languages.putKey("wwydt", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Only god can help you now.");
        map.put(Language.AR, "فقط يمكن أن تساعدك على الله الآن");
        map.put(Language.DE, "Nur Gott kann dir jetzt helfen.");
        map.put(Language.ES, "Sólo Dios puede ayudar ahora.");
        map.put(Language.FR, "Seul Dieu peut vous aider maintenant.");
        map.put(Language.IT, "Solo Dio può aiutare adesso.");
        map.put(Language.NL, "Alleen God kan nu helpen.");
        map.put(Language.NO, "Bare Gud kan hjelpe deg nå.");
        map.put(Language.RU, "Только Бог может помочь вам.");
        map.put(Language.SV, "Bara Gud kan hjälpa dig nu.");
        languages.putKey("ogchyn", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "This is mine.");
        map.put(Language.AR, "هذا هو لي");
        map.put(Language.DE, "Das ist meins.");
        map.put(Language.ES, "Esta es la mía.");
        map.put(Language.FR, "Ceci est à moi.");
        map.put(Language.IT, "Questo è mio.");
        map.put(Language.NL, "Dit is van mij.");
        map.put(Language.NO, "Dette er mitt.");
        map.put(Language.RU, "Это мое.");
        map.put(Language.SV, "Detta är min.");
        languages.putKey("tim", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "I have something for you.");
        map.put(Language.AR, "لدي شيء لك");
        map.put(Language.DE, "Ich habe etwas für Sie.");
        map.put(Language.ES, "Tengo algo para ti.");
        map.put(Language.FR, "J'ai quelque chose pour vous.");
        map.put(Language.IT, "Ho qualcosa per te.");
        map.put(Language.NL, "Ik heb iets voor je.");
        map.put(Language.NO, "Jeg har noe for deg.");
        map.put(Language.RU, "У меня есть кое-что для вас.");
        map.put(Language.SV, "Jag har något för dig.");
        languages.putKey("ihsfy", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "I have found something!");
        map.put(Language.AR, "لقد وجدت شيئا");
        map.put(Language.DE, "Ich habe etwas gefunden!");
        map.put(Language.ES, "He encontrado algo!");
        map.put(Language.FR, "J'ai trouvé quelque chose!");
        map.put(Language.IT, "Ho trovato qualcosa!");
        map.put(Language.NL, "Ik heb iets gevonden!");
        map.put(Language.NO, "Jeg har funnet noe!");
        map.put(Language.RU, "Я нашел что-то!");
        map.put(Language.SV, "Jag har hittat något!");
        languages.putKey("ihfs", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Follow me.");
        map.put(Language.AR, "يتبعني");
        map.put(Language.DE, "Folgen Sie mir.");
        map.put(Language.ES, "Sígueme.");
        map.put(Language.FR, "Suivez-moi.");
        map.put(Language.IT, "Seguimi.");
        map.put(Language.NL, "Volg mij.");
        map.put(Language.NO, "Følg meg.");
        map.put(Language.RU, "Следуй за мной.");
        map.put(Language.SV, "Följ mig.");
        languages.putKey("fm", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Your greed has betrayed you.");
        map.put(Language.AR, "وقد خانوا الجشع الخاص بك");
        map.put(Language.DE, "Ihre Gier hat dich verraten.");
        map.put(Language.ES, "Su avaricia le ha traicionado.");
        map.put(Language.FR, "Votre cupidité vous a trahi.");
        map.put(Language.IT, "La tua avidità ti ha tradito.");
        map.put(Language.NL, "Uw hebzucht heeft je verraden.");
        map.put(Language.NO, "Din grådighet har forrådt deg.");
        map.put(Language.RU, "Ваша жадность предал вас.");
        map.put(Language.SV, "Din girighet har svikit dig.");
        languages.putKey("yghby", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Did you find what you wanted?");
        map.put(Language.AR, "لم تجد ما تريد");
        map.put(Language.DE, "Haben Sie gefunden, was Sie wollten?");
        map.put(Language.ES, "¿Encontraste lo que buscabas?");
        map.put(Language.FR, "Avez-vous trouvé ce que vous vouliez?");
        map.put(Language.IT, "Hai trovato quello che volevi?");
        map.put(Language.NL, "Heb je gevonden wat je wilde?");
        map.put(Language.NO, "Fant du det du ville?");
        map.put(Language.RU, "Нашли ли вы, что вы хотели?");
        map.put(Language.SV, "Har du hittat det rätta?");
        languages.putKey("dyfwyw", map.entrySet());
        map.clear();
        
        map.put(Language.EN, "Looking for these?");
        map.put(Language.AR, "أبحث عن هؤلاء");
        map.put(Language.DE, "Suchen Sie diese?");
        map.put(Language.ES, "¿Buscas esto?");
        map.put(Language.FR, "Vous recherchez des ceux-ci?");
        map.put(Language.IT, "Alla ricerca di questi?");
        map.put(Language.NL, "Op zoek naar deze?");
        map.put(Language.NO, "Leter du etter disse?");
        map.put(Language.RU, "Глядя на это?");
        map.put(Language.SV, "Letar du efter dessa?");
        languages.putKey("lft", map.entrySet());

        languages.setCurrLanguage(HeroSettings.language);
    }

    public static String getString(String key){
        String keyString = languages.get(key);
        if (keyString == null) {
            return "...";
        }
        return keyString;
    }
}