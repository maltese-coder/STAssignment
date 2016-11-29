package impl;

import enums.MediaType;

import java.awt.*;
import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdFormat
{
    private MediaType mediaType;
    private Dimension dimensions;
    private List<String> keywords;

    public AdFormat(MediaType mediaType, Dimension dimensions, List<String> keywords)
    {
        this.mediaType = mediaType;
        this.dimensions = dimensions;
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

    public Dimension getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions)
    {
        this.dimensions = dimensions;
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
