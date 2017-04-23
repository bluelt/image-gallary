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

public class GettyImageParser implements ImageParser {
    @Override
    public List<ImageDto> parse(InputStream in, String hostUrl) {
        Document document;
        try {
            document = Jsoup.parse(in, "UTF-8", hostUrl);
        }catch (Exception e) {
            return null;
        }

        List<ImageDto> imageList = new ArrayList<>();
        Elements group = document.getElementsByClass("gallery-item-group exitemrepeater");
        for(Element element : group) {
            String srcPath = element.getElementsByClass("picture").attr("src");
            String linkUrl = element.select("div gallery-item-caption p a").attr("href");
            imageList.add(new ImageDto(hostUrl, linkUrl, srcPath));
        }

        return imageList;
    }
}
