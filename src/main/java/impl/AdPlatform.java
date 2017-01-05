package impl;

import enums.AffiliateType;
import globalDesc.Global;
import interfaces.iAdProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdPlatform implements iAdProvider
{
    // Map of all affiliates
    private HashMap<Integer,Affiliate> affiliatesMap = new HashMap<Integer, Affiliate>();

    // List of all Ad Providers
    private List<AdProvider> adProvidersList = new ArrayList<AdProvider>();

    public AdPlatform()
    {

    }

    //Create an AdPlatform with an X amount of providers providing Y different adverts each
    public AdPlatform(int providers, int adverts){
        for(int i = 0; i < providers; i++){
            adProvidersList.add(new AdProvider(adverts));
        }
    }

    public HashMap<Integer, Affiliate> getAffiliatesMap() {
        return affiliatesMap;
    }

    //insert new affiliate into map
    public boolean registerAffiliate(Affiliate affiliate)
    {
        affiliatesMap.put(affiliate.getId(),affiliate);

        return true;
    }

    public int getAffiliateCount()
    {
        return affiliatesMap.size();
    }

    public double settleAffiliateBalance(Affiliate affiliate)
    {
        if ((affiliate.getBalance() < 5))
        {
            return -1;
        }

        double commission = getCommission(affiliate);
        double payment = affiliate.getBalance() - commission;

        affiliate.setBalance(0);

        return payment;
    }

    public double getCommission(Affiliate affiliate)
    {
        AffiliateType tempType = affiliate.getType();
        double commission;

        if (tempType == AffiliateType.BRONZE)
        {
            commission = ((10 * affiliate.getBalance())/100);
        }
        else if (tempType == AffiliateType.SILVER)
        {
            commission = ((7.5 * affiliate.getBalance())/100);
        }
        else
        {
            commission = ((5 * affiliate.getBalance())/100);
        }

        return commission;
    }

    public Advert serveAdvert(AdDescription adDescription)
    {
        //This will contain a random advert from each Ad Provider that satisfies the adDescription
        List<Advert> adverts = new ArrayList<Advert>();

        //For every ad provider signed up with the system, get a random ad from each that satisfies the description
        // from the affiliate and add it to the temporary list of "adverts"
        for(AdProvider ap : adProvidersList){
            adverts.add(ap.serveAdvert(adDescription));
        }

        //Return a random ad from the previously made list
        if(adverts.size() > 0){
            return adverts.get(Global.randInt(0, adverts.size() - 1));
        }

        //no ad was found with the necessary description
        return null;
    }

    public void adClicked(int aID)
    {
        Affiliate affiliate = affiliatesMap.get(aID);

        //increase balance by 50c
        affiliate.setBalance(affiliate.getBalance()+ 0.5);
        affiliate.setCumulativeTotal(affiliate.getCumulativeTotal() +0.5);

        boolean check = checkPromotion(affiliate);
    }

    public boolean checkPromotion(Affiliate affiliate)
    {
        //check for promotion
        if(affiliate.getCumulativeTotal() == 50 && (affiliate.getType() == AffiliateType.BRONZE))
        {
            affiliate.setType(AffiliateType.SILVER);
            return true;
        }
        if(affiliate.getCumulativeTotal() == 500 && (affiliate.getType() == AffiliateType.SILVER))
        {
            affiliate.setType(AffiliateType.GOLD);
            return true;
        }
        return false;
    }
}
