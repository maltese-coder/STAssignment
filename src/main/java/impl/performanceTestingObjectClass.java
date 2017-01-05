package impl;

import enums.DimensionType;
import enums.MediaType;
import globalDesc.Global;
import interfaces.iThreadListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by matt on 02/01/2017.
 */
public class performanceTestingObjectClass implements iThreadListener {

    AdPlatform adPlatform = new AdPlatform(100,1000);

    ArrayList<AffiliateThread> threads = new ArrayList<AffiliateThread>();

    List<Callable<Object>> todo = new ArrayList<Callable<Object>>();

    ExecutorService es;

    boolean check = true;
    int counter = 1;
//    int threadNumber = 0;

    public void runTest() {
        while(check) {

            adPlatform.registerAffiliate(new Affiliate(counter, "Affiliate" + counter));
            System.out.println("AFFILIATE " + counter + " CREATED");

            es = Executors.newCachedThreadPool();

            int threadID = 1;
            //Go through every affiliate stored in the map
            for (Affiliate a : adPlatform.getAffiliatesMap().values()) {
                //Create a new random AdDescription to simulate an affiliate requesting an ad
                AdDescription ad = new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

                AffiliateThread T1 = new AffiliateThread(a, adPlatform, ad, threadID);

                T1.addListener(this);

                todo.add(Executors.callable(T1));
//                System.out.println("Thread " + threadID + "started.");
                threadID++;
            }

            try {
                es.invokeAll(todo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            todo.clear();
            counter++;
        }
    }

    public void setFalse(){
        System.out.println("----------- STOPPED ------------");
        check = false;
        System.out.println("TOTAL AFFILIATES "+ counter);
    }

    public void threadClose(Advert a){
        //System.out.println(a.toString());
        //System.out.println("Thread number " + at.getCount() + " removed.");
    }
}
