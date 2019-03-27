package com.regexr;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class ParseWebTest {
    private static final Logger LOG = Logger.getLogger(ParseWeb.class.getName());
    private FileHandler fh;

    @Before
    public void setUp() {
        try {
            fh = new FileHandler("parse.log");
            LOG.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
    }

    @Test
    public void ParseWebTest() {
        ParseWeb mate = new ParseWeb("https://mate.academy");
        LOG.info(mate.getEmail());
        LOG.info(mate.getIp());
        LOG.info(mate.getNumbers());

        ParseWeb wiki = new ParseWeb("https://ru.wikipedia.org/wiki/Java");
        LOG.info(wiki.getDate());
    }
}
