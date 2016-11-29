package impl;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import enums.MediaType;

import java.awt.*;
import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdDescription
{
    private List<String> keywords;
    private Dimension dimensions;
    private MediaType mediaType;

    public AdDescription(List<String> keywords, Dimension dimensions, MediaType mediaType) {
        this.keywords = keywords;
        this.dimensions = dimensions;
        this.mediaType = mediaType;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

}
