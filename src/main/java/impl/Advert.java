package impl;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class Advert
{
    private int id;
    private String MediaUrl;
    private AdFormat adFormat;

    public Advert(int id, String mediaUrl, AdFormat adFormat) {
        this.id = id;
        MediaUrl = mediaUrl;
        this.adFormat = adFormat;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMediaUrl() {
        return MediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        MediaUrl = mediaUrl;
    }

    public AdFormat getAdFormat() {
        return adFormat;
    }

    public void setAdFormat(AdFormat adFormat) {
        this.adFormat = adFormat;
    }

    @Override
    public String toString(){
        return this.getId() + "\n" + this.getMediaUrl() + "\n" + this.getAdFormat().toString();
    }
}
