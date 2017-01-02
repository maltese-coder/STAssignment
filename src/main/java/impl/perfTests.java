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
    int counter = 0;

    public void runTest(){
//        for(int i = 0; i <= 512; i++){
//            adPlatform.registerAffiliate(new Affiliate(i,"Affiliate"+i));
//        }
//
//        for(Affiliate a : adPlatform.getAffiliatesMap().values()){
//
//            AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));
//            AffiliateThread T1 = new AffiliateThread(a,adPlatform,ad);
//            T1.addListener(this);
//            T1.start();
//
//        }
        
        while(check){
            adPlatform.registerAffiliate(new Affiliate(counter,"Affiliate"+counter));

            for(Affiliate a : adPlatform.getAffiliatesMap().values()){

                AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));
                AffiliateThread T1 = new AffiliateThread(a,adPlatform,ad);
                T1.addListener(this);
                T1.start();





            }


            counter++;
            System.out.println("COUNT: "+adPlatform.getAffiliateCount());
        }

        System.out.println("COUNT: "+counter);
    }

    public void setFalse(){
        //System.out.println("----------- STOPPED ------------");
        check = false;
    }
}
