package impl;

import com.sun.org.apache.xpath.internal.compiler.Keywords;

import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdDescription
{
    private List<String> keywords;
    private AdFormat format;

    public AdDescription(List<String> keywords, AdFormat format) {
        this.keywords = keywords;
        this.format = format;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public AdFormat getFormat() {
        return format;
    }

    public void setFormat(AdFormat format) {
        this.format = format;
    }
}
