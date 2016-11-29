package impl;

import enums.AffiliateType;
import interfaces.iAdProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdPlatform implements iAdProvider
{
    //private List<impl.Affiliate> affiliatesMap = new ArrayList<impl.Affiliate>();
    private HashMap<Integer,Affiliate> affiliatesMap = new HashMap<Integer, Affiliate>();
    private List<iAdProvider> adProvidersList = new ArrayList<iAdProvider>();

    public boolean registerAffiliate(Affiliate affiliate)
    {
        affiliatesMap.put(affiliate.getId(),affiliate);

        return true;
    }


    public boolean settleAffiliateBalance(Affiliate affiliate)
    {
        if (!(affiliate.getBalance() < 5))
        {
            return false;
        }

        double commission = getCommission(affiliate);
        double payment = affiliate.getBalance() - commission;

        affiliate.setBalance(0);

        return true;
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
        return null;
    }

    public void adClicked(int aID)
    {
        Affiliate affiliate = affiliatesMap.get(aID);

        //increase pay by 50c
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
