package interfaces;

import impl.AdDescription;
import impl.Advert;

/**
 * Created by Matthew on 19-Nov-16.
 */
public interface iAdProvider
{
    String name = null;

    Advert serveAdvert(AdDescription adDescription);
}
