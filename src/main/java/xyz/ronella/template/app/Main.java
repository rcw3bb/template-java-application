package xyz.ronella.template.app;

import xyz.ronella.logging.LoggerPlus;
import org.slf4j.LoggerFactory;

public class Main {

    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LoggerFactory.getLogger(Main.class));

    public static void main(String[] args) {
        try (var mLOG = LOGGER_PLUS.groupLog("main")) {
            final var appInfo = AppInfo.INSTANCE;
            final var header = String.format("%s v%s (%s)"
                    , appInfo.getAppName()
                    , appInfo.getAppVersion()
                    , appInfo.getBuildDate()
            );
            mLOG.info(header);

            final var argsMgr = ArgsMgr.build(args);

            if (argsMgr.shouldExit()) {
                return;
            }

            final var main = new Main();
            mLOG.info(main.hello(argsMgr.getName()));
        }
    }

    public String hello(final String name) {
        return String.format("Hello %s", name);
    }

}
