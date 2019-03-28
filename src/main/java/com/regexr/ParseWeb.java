package com.regexr;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


public class ParseWeb {
    private static final Logger LOG = Logger.getLogger(ParseWeb.class);
    private static final String IP_ADDRESS_PATTERN = "((2[0-5][0-5]|1?[0-9]?[0-9])\\.){3}(2[0-5][0-5]|1?[0-9]?[0-9])";
    private static final String EMAIL_PATTERN = "(\\b[\\w.-]+@[-.\\w]+\\b)";
    private static final String NUM_PATTERN = "\\+?(\\d{2})?(0\\d{9})";
    private static final String DATE_PATTERN = "(\\d{1,2}\\D{3,7}я\\d{4})";

    private String url;
    private String numbers;
    private String email;
    private String ip;
    private String date;
    private Document doc;

    public ParseWeb(String url) {
        this.url = url;
        parseData();
    }

    public String getIp() {
        return ip;
    }

    public String getEmail() {
        return email;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getDate() {
        return date;
    }

    private void parseData() {
        try {
            doc = Jsoup.connect(url).get();
            parseData();
        } catch (IOException e) {
            LOG.error("Connection error");
        }
        String content = clearString(doc.html().toString());
        numbers = searchReg(content, NUM_PATTERN);
        email = searchReg(content, EMAIL_PATTERN);
        ip = searchReg(content, IP_ADDRESS_PATTERN);
        date = searchReg(content, DATE_PATTERN);
    }

    private String searchReg(String context, String regex) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(context);
        while (matcher.find()) {
            stringBuilder.append("\n" + matcher.group());
        }
        return stringBuilder.toString();
    }

    private String clearString(String string) {
        return string.replaceAll("\\-|\\s|\\(|\\)", "");
    }
}
