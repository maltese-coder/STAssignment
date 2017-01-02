package impl;


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
        long startTime = System.currentTimeMillis();

        System.out.println(affiliate.requestAdvert(adPlatform, adDescription).toString());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Time elapsed: " + elapsedTime);



        if(elapsedTime > 4000){
            //throw new IllegalArgumentException();
            listener.setFalse();
        }
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
