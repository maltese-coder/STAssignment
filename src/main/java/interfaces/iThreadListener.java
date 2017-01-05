package interfaces;

import impl.AffiliateThread;

/**
 * Created by matt on 02/01/2017.
 */
public interface iThreadListener {

    void setFalse();

    void threadClose(AffiliateThread at);
}
