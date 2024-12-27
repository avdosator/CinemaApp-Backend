package com.cinemaapp.backend.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsoupService {

    public static String parseHtmlFromUrl(String url) throws IOException {

        // Connect to the URL and fetch the document
        Document document = Jsoup.connect(url).get();
        Element bodyElement = document.body();
        return bodyElement.text();
    }
}