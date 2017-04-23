package com.example.imagesgallery;

import com.example.imagesgallery.Parser.GettyImageParser;
import com.example.imagesgallery.Parser.ImageParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public class Util {
    public static final Map<String, ImageParser> IMAGE_PARSER_MAP;
    public static final String GETTYBASEURL = "http://gettyimagesgallery.com/";
    public static final String BASEURL = GETTYBASEURL;

    static {
        Map<String, ImageParser> parserMap = new HashMap<>();
        parserMap.put(GETTYBASEURL, new GettyImageParser());
        IMAGE_PARSER_MAP = Collections.unmodifiableMap(parserMap);
    }
}
