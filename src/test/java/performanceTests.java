import enums.MediaType;
import globalDesc.Global;

/**
 * Created by matt on 02/01/2017.
 */
public class performanceTests {

    public static void main(String[] args){

        for(int i = 0; i < 50; i++){
            System.out.println(Global.randomEnum(MediaType.class));
        }

    }
}
