package interfaces;

import impl.Advert;
import impl.AffiliateThread;

/**
 * Created by matt on 02/01/2017.
 */
public interface iThreadListener {

    void setFalse();

    void threadClose(Advert a);
}
