import enums.AffiliateType;
import impl.AdPlatform;
import impl.Affiliate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by matt on 21/11/2016.
 */
public class unitTests
{
    AdPlatform adPlatform;

    @Before
    public void setup()
    {
        adPlatform = new AdPlatform();
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
        affiliate.setCumulativeTotal(50);

        adPlatform.registerAffiliate(affiliate);
        adPlatform.checkPromotion(affiliate);

        assertEquals(AffiliateType.SILVER,affiliate.getType());
    }

    @Test
    public void testPromotionToGold()
    {
        Affiliate affiliate = new Affiliate(1,"Matthew");
        affiliate.setCumulativeTotal(500);
        affiliate.setType(AffiliateType.SILVER);

        adPlatform.registerAffiliate(affiliate);
        adPlatform.checkPromotion(affiliate);

        assertEquals(AffiliateType.GOLD,affiliate.getType());

    }


}
