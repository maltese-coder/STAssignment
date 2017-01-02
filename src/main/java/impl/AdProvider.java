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

    //Generate a "maxAdverts" amount of different adverts
    public AdProvider(int maxAdverts){
        for (int i = 0; i <= maxAdverts; i++){
            //Create new AdFormat class
            AdFormat tempFormat = new AdFormat(Global.randomEnum(MediaType.class), Global.randomEnum(DimensionType.class), Global.getRandomKeywords());

            //Create new Advert class
            Advert temp = new Advert(i,generateRandomURL(),tempFormat);

            //Add Advert to list
            adverts.add(temp);
        }
    }

    public Advert serveAdvert(AdDescription adDescription) {
        //for every advert item in Array List "adverts"
        for(Object o : adverts){
            Advert a = (Advert)o;

            //Check if advert matches the media type requested by description
            if(a.getAdFormat().getMediaType() == adDescription.getMediaType()){
                //Check if advert matches dimension type requested by description
                if(a.getAdFormat().getDimension() == adDescription.getDimension()){

                    //to check that the advert has at least 2 keywords in common with
                    // the description
                    int check = 0;

                    for(int i = 0; i < adDescription.getKeywords().size(); i++){
                        if(a.getAdFormat().getKeywords().contains(adDescription.getKeywords().get(i))){
                            check++;
                        }

                        //Advert has already reached the necessary amount of keywords so it doesn't have to
                        // keep searching
                        if(check == 2){
                            break;
                        }
                    }

                    if(check == 2){
                        //Current advert has met the necessary requirements to match the description advert

                        //System.out.println(a.toString());
                        mAdverts.add(a);
                    }
                }
            }
        }


        if(mAdverts.size() > 0){
            // Return a random advert from the list
            return (Advert)mAdverts.get(Global.randInt(0,mAdverts.size()-1));
        }

        // No advert was found that matched the description
        return null;
    }

    public String generateRandomURL() {
        return new BigInteger(64, random).toString(32);
    }
}
