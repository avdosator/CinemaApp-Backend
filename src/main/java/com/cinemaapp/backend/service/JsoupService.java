package com.cinemaapp.backend.service;

import java.io.IOException;

public interface JsoupService {

    String parseHtmlFromUrl(String url) throws IOException;
}
