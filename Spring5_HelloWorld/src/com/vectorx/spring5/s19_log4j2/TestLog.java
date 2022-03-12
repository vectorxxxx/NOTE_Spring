package com.vectorx.spring5.s19_log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

    public static void main(String[] args) {
        logger.trace("This is trace log test.");
        logger.debug("This is debug log test.");
        logger.info("This is info log test.");
        logger.warn("This is warn log test.");
        logger.error("This is error log test.");
    }
}
