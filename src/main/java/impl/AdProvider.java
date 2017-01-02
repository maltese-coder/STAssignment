package impl;

import globalDesc.Global;
import interfaces.iAdProvider;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by matt on 02/01/2017.
 */
public class AdProvider implements iAdProvider {

    private SecureRandom random = new SecureRandom();

    ArrayList adverts;

    public AdProvider(int maxAdverts){
        for (int i = 0; i <= maxAdverts; i++){
            //AdFormat tempFormat = Global.randomEnum()

            //Advert temp = new Advert(i,generateRandomURL(),
        }
    }


    public Advert serveAdvert(AdDescription adDescription) {
        return null;
    }



    public String generateRandomURL() {
        return new BigInteger(64, random).toString(32);
    }
}
