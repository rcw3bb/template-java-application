package xyz.ronella.template.app;

import org.apache.logging.log4j.LogManager;
import xyz.ronella.template.app.commons.ArgsMgr;
import xyz.ronella.template.app.commons.LoggerPlus;

public class Main {

    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LogManager.getLogger(Main.class));

    public static void main(String[] args) {
        try (var mLOG = LOGGER_PLUS.logByMethodCall("main")) {
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
