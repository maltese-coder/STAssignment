package impl;

import enums.DimensionType;
import enums.MediaType;
import globalDesc.Global;
import interfaces.iThreadListener;

/**
 * Created by matt on 02/01/2017.
 */
public class perfTests implements iThreadListener {

    AdPlatform adPlatform = new AdPlatform(50,1000);

    boolean check = true;
    int counter = 1;

    public void runTest(){
        while(check)
        {
            //Add a new affiliate to the performance test
            adPlatform.registerAffiliate(new Affiliate(counter, "Affiliate" + counter));
            System.out.println("AFFILIATE " + counter + " CREATED");

            for (int i = 0; i < counter; i++)
            {
                //Go through every affiliate stored in the map
                for (Affiliate a : adPlatform.getAffiliatesMap().values())
                {
                    //Create a new random AdDescription to simulate an affiliate requesting an ad
                    AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

                    AffiliateThread T1 = new AffiliateThread(a, adPlatform, ad, counter);
                    T1.addListener(this);
                    T1.start();
                }
            }
            counter++;
//            System.out.println("COUNT: "+adPlatform.getAffiliateCount());
        }
//        System.out.println("COUNT: "+counter);
    }

    public void setFalse(){
        System.out.println("----------- STOPPED ------------");
        check = false;
        System.out.println("TOTAL AFFILIATES "+ counter);
    }
}
