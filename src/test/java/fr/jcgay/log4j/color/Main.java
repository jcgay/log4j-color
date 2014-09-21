package fr.jcgay.log4j.color;

import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.trace("a trace message.");
        LOGGER.debug("a debug message.");
        LOGGER.info("an info message.");
        LOGGER.warn("a warn message.");
        LOGGER.error("a error message.");
        LOGGER.fatal("a fatal message.");
    }
}
