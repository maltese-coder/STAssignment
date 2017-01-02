package impl;

import enums.DimensionType;
import enums.MediaType;

import java.awt.*;
import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdFormat
{
    private MediaType mediaType;
    private DimensionType dimension;
    private List<String> keywords;

    public AdFormat(MediaType mediaType, DimensionType dimension, List<String> keywords) {
        this.mediaType = mediaType;
        this.dimension = dimension;
        this.keywords = keywords;
    }

    public MediaType getMediaType()
    {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
    }

    public DimensionType getDimension() {
        return dimension;
    }

    public void setDimension(DimensionType dimension) {
        this.dimension = dimension;
    }

    public List<String> getKeywords()
    {
        return keywords;
    }

    public void setKeywords(List<String> keywords)
    {
        this.keywords = keywords;
    }
}
