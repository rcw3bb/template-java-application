package xyz.ronella.template.app;

import xyz.ronella.logging.LoggerPlus;
import xyz.ronella.template.app.commons.ArgsMgr;
import org.slf4j.LoggerFactory;

public class Main {

    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LoggerFactory.getLogger(Main.class));

    public static void main(String[] args) {
        try (var mLOG = LOGGER_PLUS.groupLog("main")) {
            ArgsMgr argsMgr = ArgsMgr.build(args);

            if (argsMgr.shouldExit()) {
                return;
            }

            Main main = new Main();
            mLOG.info(main.hello(argsMgr.getName()));
        }
    }

    public String hello(String name) {
        return String.format("Hello %s", name);
    }

}
