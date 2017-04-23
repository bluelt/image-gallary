package com.example.imagesgallery.Parser;

import com.example.imagesgallery.dto.ImageDto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyemi on 2017. 4. 23..
 */

public interface ImageParser {
    public List<ImageDto> parse(InputStream in, String hostUrl);
}
