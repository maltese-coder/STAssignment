package globalDesc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by matxu on 02-Jan-17.
 */
public class Global {

    public static List<String> allKeywords = Arrays.asList("wail","rural","aloof","invincible","ghost","swanky","stimulating",
            "acoustics","sheet","part","slimy","brother","high-pitched","stupendous","food","wild","abounding","cows",
            "difficult","acoustic","grateful","destroy","error","royal","brainy","neck","automatic","grandiose",
            "dynamic","shame","enormous","carriage","debt","swift","care","afternoon","scrawny","parallel","annoying",
            "grate","kill","flood","water","bump","dry","mere","fairies","wash","giraffe","symptomatic");

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static List<String> getRandomKeywords(){
        List<String> temp = new ArrayList<String>();

        Random random = new Random();

        for(int i = 0; i <= 3; i++) {
            int randomNum = random.nextInt((allKeywords.size() - 0) + 1) + 0;
            temp.add(allKeywords.get(randomNum));
        }

        return temp;
    }
}
