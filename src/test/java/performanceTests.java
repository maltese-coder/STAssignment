
import enums.DimensionType;
import enums.MediaType;
import globalDesc.Global;
import impl.*;
import interfaces.iThreadListener;
import java.util.Objects;

/**
 * Created by matt on 02/01/2017.
 */
public class performanceTests {

    public static void main(String[] args){

        perfTests pt = new perfTests();
        pt.runTest();


//        AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));
//
//        System.out.println(ad.toString());
//
//        System.out.println("----------------------------------------------------------");
//
//        Advert a = adPlatform.serveAdvert(ad);
//
//        System.out.println("\n----------------------------------------------------------");
//
//        System.out.println(a.toString());

        //ap.serveAdvert(ad);




//        for(Affiliate a : adPlatform.getAffiliatesMap().values()){
//
//            AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));
//
//            System.out.println(a.requestAdvert(adPlatform,ad));
//
//        }



    }
}
