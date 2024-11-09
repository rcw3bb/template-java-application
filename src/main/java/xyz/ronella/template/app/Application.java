package xyz.ronella.template.app;

import xyz.ronella.logging.LoggerPlus;
import org.slf4j.LoggerFactory;
import xyz.ronella.template.app.util.AppInfo;
import xyz.ronella.template.app.util.ArgsMgr;
import xyz.ronella.template.app.util.FileMgr;
import xyz.ronella.trivial.handy.PathFinder;

/**
 * The Main class is the entry point of the application.
 *
 * @since 1.0.0
 */
public class Application {

    static {
        final var confDir = FileMgr.getConfDir();
        confDir.ifPresent(___confDir -> {
            final var logPath = PathFinder.getBuilder("logback.xml")
                    .addPaths(".", ___confDir.getAbsolutePath())
                    .build();
            final var optLogFile = logPath.getFile();
            if (optLogFile.isPresent()) {
                final var logFile = optLogFile.get();
                if (logFile.exists()) {
                    System.setProperty("logback.configurationFile", logFile.getAbsolutePath());
                }
            }
        });
    }

    private final static LoggerPlus LOGGER_PLUS = new LoggerPlus(LoggerFactory.getLogger(Application.class));

    /**
     * The main method.
     * @param args The command line arguments.
     */
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

            final var main = new Application();
            mLOG.info(main.hello(argsMgr.getName()));
        }
    }

    /**
     * Sample method.
     * @param name The name of a person.
     * @return The greeting message.
     */
    public String hello(final String name) {
        return String.format("Hello %s", name);
    }

}
