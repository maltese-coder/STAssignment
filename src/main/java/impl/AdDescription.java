package impl;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import enums.DimensionType;
import enums.MediaType;

import java.awt.*;
import java.util.List;

/**
 * Created by Matthew on 19-Nov-16.
 */
public class AdDescription
{
    private List<String> keywords;
    private DimensionType dimension;
    private MediaType mediaType;

    public AdDescription(List<String> keywords, DimensionType dimension, MediaType mediaType) {
        this.keywords = keywords;
        this.dimension = dimension;
        this.mediaType = mediaType;
    }

    public DimensionType getDimension() {
        return dimension;
    }

    public void setDimension(DimensionType dimension) {
        this.dimension = dimension;
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

    // Display data
    @Override
    public String toString(){
        return this.getMediaType().toString() + " - " + this.getDimension().toString() + " - " + this.getKeywords().toString();
    }


}
