package impl;

import enums.DimensionType;
import enums.MediaType;
import globalDesc.Global;
import interfaces.iAdProvider;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by matt on 02/01/2017.
 */
public class AdProvider implements iAdProvider {

    private     SecureRandom random     = new SecureRandom();

    public      ArrayList adverts       = new ArrayList();
    private     ArrayList mAdverts      = new ArrayList();

    public AdProvider(int maxAdverts){
        for (int i = 0; i <= maxAdverts; i++){
            AdFormat tempFormat = new AdFormat(Global.randomEnum(MediaType.class), Global.randomEnum(DimensionType.class), Global.getRandomKeywords());

            Advert temp = new Advert(i,generateRandomURL(),tempFormat);

            adverts.add(temp);
        }
    }

    public Advert serveAdvert(AdDescription adDescription) {
        for(Object o : adverts){
            Advert a = (Advert)o;

            if(a.getAdFormat().getMediaType() == adDescription.getMediaType()){
                if(a.getAdFormat().getDimension() == adDescription.getDimension()){
                    int check = 0;

                    for(int i = 0; i < adDescription.getKeywords().size(); i++){
                        if(a.getAdFormat().getKeywords().contains(adDescription.getKeywords().get(i))){
                            check++;
                        }

                        if(check == 2){
                            break;
                        }
                    }

                    if(check == 2){
                        //System.out.println(a.toString());
                        mAdverts.add(a);
                    }
                }
            }
        }

        if(mAdverts.size() > 0){
            return (Advert)mAdverts.get(Global.randInt(0,mAdverts.size()-1));
        }

        return null;
    }

    public String generateRandomURL() {
        return new BigInteger(64, random).toString(32);
    }
}
