package xyz.ronella.template.app.util;

import java.io.File;
import java.util.Optional;

/**
 * The FileMgr class is the class that manages the file operations.
 *
 * @author Ron Webb
 * @since 1.0.0
 */
final public class FileMgr {
    private FileMgr() {}

    /**
     * The getConfDir method returns the configuration directory.
     * @return The configuration directory.
     */
    public static Optional<File> getConfDir() {
        final var appData = Optional.of(System.getProperty("user.dir"));
        return appData.map(___appData -> {
            final var confDir = new File(String.format("%s/conf", ___appData));

            if (!confDir.exists()) {
                confDir.mkdirs();
            }

            return confDir;
        });
    }

}
