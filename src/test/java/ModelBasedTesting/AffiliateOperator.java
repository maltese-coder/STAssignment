package ModelBasedTesting;

/**
 * Created by Matthew on 17-Dec-16.
 */
public class AffiliateOperator
{
    public AffiliateOperator()
    {
        bronze = true;
        totalCommision = 0.0;

    }

    private boolean
        bronze = false,
        silver = false,
        gold = false;

    private double totalCommision;

    public boolean isBronze()
    {
        return bronze;
    }

    public boolean isSilver()
    {
        return silver;
    }

    public boolean isGold()
    {
        return gold;
    }

    public double getTotalCommision()
    {
        return totalCommision;
    }

    public void increaseCommision()
    {
        if(totalCommision == 0)
            bronze = true;
        totalCommision+=0.5;
        this.checkPromotion();
    }

    public boolean checkPromotion()
    {
        if(totalCommision >= 50 && isBronze())
        {
            this.promoteToSilver();
            return true;
        }
        else if(totalCommision >= 500 && isSilver())
        {
            this.promoteToGold();
            return true;
        }
        return false;
    }

    public boolean createNewUser()
    {
        if(totalCommision>0 || bronze || silver || gold)
            return false;
        else
        {
            this.totalCommision = 0;
            bronze = true;

            return true;
        }
    }

    public boolean promoteToSilver()
    {
        if(totalCommision<50.0 || silver || gold)
            return false;
        else
        {
            bronze = false;
            silver = true;

            return true;
        }
    }

    public boolean promoteToGold()
    {
        if(totalCommision<500.0 || bronze || gold)
            return false;
        else
        {
            silver = false;
            gold = true;

            return true;
        }
    }

    boolean getInactiveStates() {
        if (bronze) {
            return silver || gold;
        } else {
            return silver && gold;
        }
    }
}
