package xyz.ronella.template.app.util;

import org.apache.commons.cli.*;

/**
 * The ArgsMgr class is the class that manages the command line arguments.
 *
 * @since 1.0.0
 */
public class ArgsMgr {

    private String name;

    /**
     * The shouldExit method returns true if the application should exit.
     * @return True if the application should exit.
     */
    public boolean shouldExit() {
        return exit;
    }

    /**
     * The setShouldExit method sets the exit flag.
     * @param exit The exit flag.
     */
    public void setShouldExit(boolean exit) {
        this.exit = exit;
    }

    private transient boolean exit;

    private ArgsMgr() {
    }

    /**
     * The setName method sets the name of a person.
     * @param name The name of a person.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * The getName method returns the name of a person.
     * @return The name of a person.
     */
    public String getName() {
        return this.name;
    }

    private static void addNameOption(final Options options) {
        final var nameOption = new Option("n", "name", true
                , "Name of a person.");
        nameOption.setRequired(true);
        options.addOption(nameOption);
    }

    private static void addHelpOption(final Options options) {
        final var provisionOption = new Option("h", "help", false
                , "Shows the help information.");
        provisionOption.setRequired(false);
        options.addOption(provisionOption);
    }

    private static void addGenericParamOption(final Options options) {
        final var genericParam = new Option("D", true, "Generic Parameter");
        genericParam.setRequired(false);
        genericParam.setArgName("parameter=value");
        genericParam.setArgs(2);
        genericParam.setValueSeparator('=');
        options.addOption(genericParam);
    }

    private static void helpInfo(final ArgsMgr argMgr, final Options options) {
        final var formatter = new HelpFormatter();
        final var appName = AppInfo.INSTANCE.getAppName();
        formatter.printHelp(appName, options);
        argMgr.setShouldExit(true);
    }

    /**
     * Build an instance of ArgsMgr.
     * @param args The command line arguments.
     * @return An instance of ArgsMgr.
     */
    public static ArgsMgr build(String[] args) {
        final var argManager = new ArgsMgr();
        final var options = new Options();

        addNameOption(options);
        addGenericParamOption(options);
        addHelpOption(options);

        final var parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                helpInfo(argManager, options);
            } else {
                argManager.setName(cmd.getOptionValue("name"));
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helpInfo(argManager, options);
        }

        return argManager;
    }
}
