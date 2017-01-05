package impl;

import enums.DimensionType;
import enums.MediaType;
import globalDesc.Global;
import interfaces.iThreadListener;

import java.util.ArrayList;

/**
 * Created by matt on 02/01/2017.
 */
public class performanceTestingObjectClass implements iThreadListener {

    AdPlatform adPlatform = new AdPlatform(50,1000);

    ArrayList<AffiliateThread> threads = new ArrayList<AffiliateThread>();

    boolean check = true;
    int counter = 1;

    public void runTest() {
        while(check)
        {
                //for (int i = 0; i < counter; i++) {
                //Add a new affiliate to the performance test
            adPlatform.registerAffiliate(new Affiliate(counter, "Affiliate" + counter));
            System.out.println("AFFILIATE " + counter + " CREATED");
                //}

            int threadID = 1;
                //Go through every affiliate stored in the map
            for (Affiliate a : adPlatform.getAffiliatesMap().values()) {
                //Create a new random AdDescription to simulate an affiliate requesting an ad
                AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

                AffiliateThread T1 = new AffiliateThread(a, adPlatform, ad, threadID);

                T1.addListener(this);
                threads.add(T1);
                T1.start();

                try {
                    T1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                threadID++;
            }

//            for(Thread thread : threads){
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }


            System.out.println("ALL THREADS FINISHED.");

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

    public void threadClose(AffiliateThread at){
        //threads.remove(at);
        System.out.println("Thread number " + at.getCount() + " removed.");
    }
}
