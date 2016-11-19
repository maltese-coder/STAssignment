/**
 * Created by Matthew on 19-Nov-16.
 */
public class Affiliate
{
    private int id;
    private String name;
    private AffiliateType type;
    private long balance;

    public Affiliate(int id, String name, AffiliateType type, long balance)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.balance = balance;
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

    public long getBalance()
    {
        return balance;
    }

    public void setBalance(long balance)
    {
        this.balance = balance;
    }
}
