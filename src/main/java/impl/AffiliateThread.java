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

    public AffiliateThread(Affiliate affiliate, AdPlatform adPlatform, AdDescription adDescription){
        this.adPlatform = adPlatform;
        this.adDescription = adDescription;
        this.affiliate = affiliate;
    }

    public void run(){
        //Start running time
        long startTime = System.currentTimeMillis();

        System.out.println(affiliate.requestAdvert(adPlatform, adDescription).toString());

        //Stop running time
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time elapsed: " + elapsedTime);

        //Since an affiliate requests an average of 2.5adverts/sec means that a single ad must be sent every 0.4seconds
        if(elapsedTime > 4000){
            //throw new IllegalArgumentException();
            listener.setFalse();
        }

        //TODO: do we check time taken it takes for an adclick?
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
