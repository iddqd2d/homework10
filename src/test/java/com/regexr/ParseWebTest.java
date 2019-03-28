package com.regexr;

import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;


public class ParseWebTest {
    private static final Logger LOG = Logger.getLogger(ParseWeb.class.getName());

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
