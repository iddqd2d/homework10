package com.regexr;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParseWeb {
    private static final Logger LOG = Logger.getLogger(ParseWeb.class.getName());
    private static final String IP_ADDRESS_PATTERN = "((2[0-5][0-5]|1?[0-9]?[0-9])\\.){3}(2[0-5][0-5]|1?[0-9]?[0-9])";
    private static final String EMAIL_PATTERN = "(\\b[\\w.-]+@[-.\\w]+\\b)";
    private static final String NUM_PATTERN = "\\+?(\\d{2})?(0\\d{9})";
    private static final String DATE_PATTERN = "(\\d{1,2}\\D{3,7}я\\d{4})";
    private String numbers;
    private String email;
    private String ip;
    private String date;
    private Document doc;

    public ParseWeb(String url) {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            LOG.info("Connection error");
        }
        String content = clearStr(doc.html().toString());
        numbers = seachReg(content, NUM_PATTERN);
        email = seachReg(content, EMAIL_PATTERN);
        ip = seachReg(content, IP_ADDRESS_PATTERN);
        date = seachReg(content, DATE_PATTERN);
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

    private String seachReg(String str, String ptrn) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile(ptrn);
        Matcher temp = p.matcher(str);
        while (temp.find()) {
            stringBuilder.append("\n" + temp.group());
        }
        return stringBuilder.toString();
    }

    private String clearStr(String str) {
        return str.replaceAll("\\-|\\s|\\(|\\)", "");
    }
}
