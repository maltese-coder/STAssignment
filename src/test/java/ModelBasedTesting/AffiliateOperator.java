package ModelBasedTesting;

/**
 * Created by Matthew on 17-Dec-16.
 */
public class AffiliateOperator
{
    private boolean
        bronze = true,
        silver = false,
        gold = false;

    private double totalBalance = 0;
    private double currentBalance = 0;

    public double getCurrentBalance()
    {
        return currentBalance;
    }

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

    public double getTotalBalance()
    {
        return totalBalance;
    }

    public void withdrawCurrBalance()
    {
        currentBalance = 0;
    }

    public void increaseBalance()
    {
        if(totalBalance == 0)
            bronze = true;
        totalBalance +=0.5;
        currentBalance +=0.5;
        this.checkPromotion();
    }

    public boolean checkPromotion()
    {
        if(totalBalance >= 50 && isBronze())
        {
            this.promoteToSilver();
            return true;
        }
        else if(totalBalance >= 500 && isSilver())
        {
            this.promoteToGold();
            return true;
        }
        return false;
    }

    public boolean promoteToSilver()
    {
        if(totalBalance <50.0 || silver || gold)
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
        if(totalBalance <500.0 || bronze || gold)
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
