/**
 * Created by Matthew on 19-Nov-16.
 */
public class Affiliate
{
    private int id;
    private String name;
    private AffiliateType type;
    private double balance;
    private double cumulativeTotal;

    public Affiliate(int id, String name)
    {
        this.id = id;
        this.name = name;
        this.type = AffiliateType.BRONZE;
        this.balance = 0;
        this.cumulativeTotal = 0;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public AffiliateType getType()
    {
        return type;
    }

    public void setType(AffiliateType type)
    {
        this.type = type;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public double getCumulativeTotal()
    {
        return cumulativeTotal;
    }

    public void setCumulativeTotal(double cumulativeTotal)
    {
        this.cumulativeTotal = cumulativeTotal;
    }
}
