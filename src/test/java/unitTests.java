import enums.AffiliateType;
import impl.AdPlatform;
import impl.Affiliate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

        affiliate.setBalance(4);

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
}
