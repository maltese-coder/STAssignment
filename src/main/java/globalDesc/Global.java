package globalDesc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by matxu on 02-Jan-17.
 */
public class Global {

    // List of keywords that an advert could consist of
    public static List<String> allKeywords = Arrays.asList("wail","rural","aloof","invincible","ghost","swanky","stimulating",
            "acoustics","sheet","part","slimy","brother","high-pitched","stupendous","food","wild","abounding","cows",
            "difficult","acoustic","grateful","destroy","error","royal","brainy","neck","automatic","grandiose",
            "dynamic","shame","enormous","carriage","debt","swift","care","afternoon","scrawny","parallel","annoying",
            "grate","kill","flood","water","bump","dry","mere","fairies","wash","giraffe","symptomatic");

    // Get random Enum value provided an ENUM class is given as a parameter
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);

        return clazz.getEnumConstants()[x];
    }

    // Get 5 random Keywords from the list
    public static List<String> getRandomKeywords(){
        List<String> temp = new ArrayList<String>();

        for(int i = 0; i < 5; i++) {
            //TODO: what if same num is chosen a second time?
           // int randomNum = random.nextInt((allKeywords.size()-1 - 0) + 1) + 0;
           int randomNum = randInt(0,allKeywords.size()-1);
            temp.add(allKeywords.get(randomNum));
        }

        return temp;
    }

    // Get a random int between 2 numbers passed through the parameters
    public static int randInt(int min, int max) {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }
}
