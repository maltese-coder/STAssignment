import enums.AffiliateType;

import enums.DimensionType;
import enums.MediaType;
import globalDesc.Global;
import impl.AdDescription;
import impl.AdPlatform;
import impl.Advert;
import impl.Affiliate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by matt on 21/11/2016.
 */
public class RunUnitTests
{
    private AdPlatform adPlatform;

    @Before
    public void setup()
    {
        adPlatform = new AdPlatform();
    }

    @Test
    public void testIncreasedAffiliateCount()
    {
        Affiliate affiliate = new Affiliate(1, "Affiliate 1");

        int previousCount = adPlatform.getAffiliateCount();

        adPlatform.registerAffiliate(affiliate);
        assertEquals(previousCount + 1, adPlatform.getAffiliateCount());
    }

    @Test
    public void testSettleAffiliateBalanceWithLessThan5()
    {
        Affiliate affiliate = new Affiliate(2, "Affiliate 2");

        affiliate.setBalance(4.99);

        assertEquals(-1, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testSettleAffiliateBalanceWith5Bronze()
    {
        Affiliate affiliate = new Affiliate(3, "Affiliate 3");

        affiliate.setBalance(5);
        affiliate.setType(AffiliateType.BRONZE);

        assertEquals(4.5, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testSettleAffiliateBalanceWith5Silver()
    {
        Affiliate affiliate = new Affiliate(4, "Affiliate 4");

        affiliate.setBalance(5);
        affiliate.setType(AffiliateType.SILVER);

        assertEquals(4.625, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testSettleAffiliateBalanceWith5Gold()
    {
        Affiliate affiliate = new Affiliate(5, "Affiliate 5");

        affiliate.setBalance(5);
        affiliate.setType(AffiliateType.GOLD);

        assertEquals(4.75, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testSettleAffiliateBalanceWithMoreThan5Bronze()
    {
        Affiliate affiliate = new Affiliate(6, "Affiliate 6");

        affiliate.setBalance(6);
        affiliate.setType(AffiliateType.BRONZE);

        assertEquals(5.4, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testSettleAffiliateBalanceWithMoreThan5Silver()
    {
        Affiliate affiliate = new Affiliate(7, "Affiliate 7");

        affiliate.setBalance(6);
        affiliate.setType(AffiliateType.SILVER);

        assertEquals(5.55, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testSettleAffiliateBalanceWithMoreThan5Gold()
    {
        Affiliate affiliate = new Affiliate(8, "Affiliate 8");

        affiliate.setBalance(6);
        affiliate.setType(AffiliateType.GOLD);

        assertEquals(5.7, adPlatform.settleAffiliateBalance(affiliate), 0.0001);
    }

    @Test
    public void testCorrectCommissionBronze()
    {
        //Setup
        Affiliate affiliate = Mockito.mock(Affiliate.class);
        when(affiliate.getType()).thenReturn(AffiliateType.BRONZE);
        when(affiliate.getBalance()).thenReturn(150.0);

        //Exercise
        double commission = adPlatform.getCommission(affiliate);

        //Verify
        assertEquals(15,commission,0.001);
    }

    @Test
    public void testCorrectCommissionSilver()
    {
        //Setup
        Affiliate affiliate = Mockito.mock(Affiliate.class);
        when(affiliate.getType()).thenReturn(AffiliateType.SILVER);
        when(affiliate.getBalance()).thenReturn(150.0);

        //Exercise
        double commission = adPlatform.getCommission(affiliate);

        //Verify
        assertEquals(11.25,commission,0.001);
    }

    @Test
    public void testCorrectCommissionGold()
    {
        //Setup
        Affiliate affiliate = Mockito.mock(Affiliate.class);
        when(affiliate.getType()).thenReturn(AffiliateType.GOLD);
        when(affiliate.getBalance()).thenReturn(150.0);

        //Exercise
        double commission = adPlatform.getCommission(affiliate);

        //Verify
        assertEquals(7.5,commission,0.001);
    }

    @Test
    public void testIncrementBalance()
    {
        Affiliate affiliate = new Affiliate(1,"Matthew");
        affiliate.setBalance(40);

        adPlatform.registerAffiliate(affiliate);
        adPlatform.adClicked(affiliate.getId());
        double newBalance = affiliate.getBalance();

        assertEquals(40.50,newBalance,0.001);
    }

    @Test
    public void testIncrementTotalBalance()
    {
        Affiliate affiliate = new Affiliate(1,"Matthew");
        affiliate.setCumulativeTotal(50);

        adPlatform.registerAffiliate(affiliate);
        adPlatform.adClicked(affiliate.getId());
        double newBalance = affiliate.getCumulativeTotal();

        assertEquals(50.50,newBalance,0.001);
    }

    @Test
    public void testPromotionToSilver()
    {
        Affiliate affiliate = new Affiliate(1,"Matthew");
        adPlatform.registerAffiliate(affiliate);

        affiliate.setCumulativeTotal(49.50);
        adPlatform.adClicked(affiliate.getId());

        assertEquals(AffiliateType.SILVER,affiliate.getType());
    }

    @Test
    public void testPromotionToGold()
    {
        Affiliate affiliate = new Affiliate(1,"Matthew");
        adPlatform.registerAffiliate(affiliate);

        affiliate.setCumulativeTotal(499.50);
        affiliate.setType(AffiliateType.SILVER);

        adPlatform.adClicked(affiliate.getId());

        assertEquals(AffiliateType.GOLD,affiliate.getType());
    }

    @Test
    public void testNoAdvertReturned(){
        //Setup
        AdPlatform adPlatformWithProviders = new AdPlatform(5,10);
        AdDescription adDescription = Mockito.mock(AdDescription.class);

        //Exercise
        Advert advert = adPlatformWithProviders.serveAdvert(adDescription);

        //Verify
        assertNull(advert);
    }

    @Test
    public void testAdvertReturned(){
        AdPlatform adPlatformWithProviders = new AdPlatform(50,1000);

        AdDescription adDescription =  new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

        Advert advert = null;

        advert = adPlatformWithProviders.serveAdvert(adDescription);

        assertNotNull(advert);
    }

    @Test
    public void testAdvertReturnedMediaType(){
        AdPlatform adPlatformWithProviders = new AdPlatform(50,1000);

        AdDescription adDescription =  new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

        Advert advert =  adPlatformWithProviders.serveAdvert(adDescription);

        assertEquals(adDescription.getMediaType(), advert.getAdFormat().getMediaType());
    }

    @Test
    public void testAdvertReturnedDimensionType(){
        AdPlatform adPlatformWithProviders = new AdPlatform(50,1000);

        AdDescription adDescription =  new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

        Advert advert =  adPlatformWithProviders.serveAdvert(adDescription);

        assertEquals(adDescription.getDimension(),advert.getAdFormat().getDimension());
    }

    @Test
    public void testTwoKeywordMatches(){
        AdPlatform adPlatformWithProviders = new AdPlatform(50,1000);

        AdDescription adDescription =  new AdDescription(Global.getRandomKeywords(), Global.randomEnum(DimensionType.class), Global.randomEnum(MediaType.class));

        Advert advert =  adPlatformWithProviders.serveAdvert(adDescription);

        int check = 0;
        boolean flag = false;

        for(int i = 0; i < adDescription.getKeywords().size(); i++){
            if(advert.getAdFormat().getKeywords().contains(adDescription.getKeywords().get(i))){
                check++;
            }
        }

        if(check >= 2){
            flag = true;
        }

        assertTrue(flag);
    }
}
