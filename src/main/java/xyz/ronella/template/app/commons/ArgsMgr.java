package xyz.ronella.template.app.commons;

import org.apache.commons.cli.*;
import java.util.function.BiConsumer;

public class ArgsMgr {

    private String name;

    public boolean shouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    private boolean shouldExit;

    private ArgsMgr() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private static void addNameOption(Options options) {
        Option nameOption = new Option("n", "name", true
                , "Name of a person.");
        nameOption.setRequired(true);
        options.addOption(nameOption);
    }

    private static void addHelpOption(Options options) {
        Option provisionOption = new Option("h", "help", false
                , "Shows the help information.");
        provisionOption.setRequired(false);
        options.addOption(provisionOption);
    }

    private static void addGenericParamOption(Options options) {
        if (!PackageMgr.isPackaged()) {
            Option genericParam = new Option("D", true, "Generic Parameter");
            genericParam.setRequired(false);
            genericParam.setArgName("parameter=value");
            genericParam.setArgs(2);
            genericParam.setValueSeparator('=');
            options.addOption(genericParam);
        }
    }

    public static ArgsMgr build(String[] args) {
        var argManager = new ArgsMgr();
        Options options = new Options();

        addNameOption(options);
        addGenericParamOption(options);
        addHelpOption(options);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        BiConsumer<ArgsMgr, Options> showHelpInfo = (___argManager, ___options) -> {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("template-java-application", ___options);
            ___argManager.setShouldExit(true);
        };

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("help")) {
                showHelpInfo.accept(argManager, options);
            } else {
                argManager.setName(cmd.getOptionValue("name"));
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            showHelpInfo.accept(argManager, options);
        }

        return argManager;
    }
}
