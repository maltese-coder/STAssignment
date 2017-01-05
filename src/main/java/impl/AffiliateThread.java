package impl;


import globalDesc.Global;
import interfaces.iThreadListener;

/**
 * Created by matt on 02/01/2017.
 */
public class AffiliateThread implements Runnable {

    private Thread thread;
    private AdPlatform adPlatform;
    private Affiliate affiliate;
    private AdDescription adDescription;
    private int count;

    public AffiliateThread(Affiliate affiliate, AdPlatform adPlatform, AdDescription adDescription, int count){
        this.adPlatform = adPlatform;
        this.adDescription = adDescription;
        this.affiliate = affiliate;
        this.count = count;
    }

    public void run(){
        //Start running time
        long startTime = System.currentTimeMillis();

        //System.out.println(affiliate.requestAdvert(adPlatform, adDescription).toString());
        affiliate.requestAdvert(adPlatform, adDescription);
        //Stop running time
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time elapsed for affiliate " + affiliate.getId() + " in thread " + this.count + " : " + elapsedTime);

        //Since an affiliate requests an average of 2.5adverts/sec means that a single ad must be sent every 0.4seconds
        if(elapsedTime > 400){
            //throw new IllegalArgumentException();
            listener.setFalse();
        }

        //End user has a 10% probability of getting an adClick oon one of his adverts
        int probability = Global.randInt(1,10);
        if(probability == 1)
            adPlatform.adClicked(affiliate.getId());
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    private iThreadListener listener = null;

    public void addListener(iThreadListener listener) {
        this.listener = listener;
    }

}
