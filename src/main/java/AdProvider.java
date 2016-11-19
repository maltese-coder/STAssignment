/**
 * Created by Matthew on 19-Nov-16.
 */
public interface AdProvider
{
    String name = null;

    Advert serveAdvert(AdDescription adDescription);
}
