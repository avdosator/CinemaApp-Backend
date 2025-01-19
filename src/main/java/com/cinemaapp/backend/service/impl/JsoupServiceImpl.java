package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.JsoupService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupServiceImpl implements JsoupService {

    @Override
    public String parseHtmlFromUrl(String url) throws IOException {
        // Connect to the URL and fetch the document
        Document document = Jsoup.connect(url).get();
        Element bodyElement = document.body();
        return bodyElement.text();
    }

}