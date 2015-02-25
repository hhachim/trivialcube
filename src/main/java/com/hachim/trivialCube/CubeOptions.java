package com.hachim.trivialCube;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author hachim
 */
public class CubeOptions {

    public String inputFile;
    public String aggregateFunction;
    public String ouputDirectory;
    public String outputDimensions;
    public int columnValuesId;

    public CubeOptions(String[] args) {
        Options options = getOptions();
        CommandLine cmd = null;
        try {
            cmd = new GnuParser().parse(options, args);
            inputFile = cmd.getOptionValue("i");
            aggregateFunction = cmd.getOptionValue("f");
            ouputDirectory = cmd.getOptionValue("o");

            if (cmd.hasOption("c")) {
                columnValuesId = Integer.parseInt(cmd.getOptionValue("c"));
            }

            if (cmd.hasOption("d")) {
                outputDimensions = cmd.getOptionValue("d");
            }
        } catch (ParseException e) {
            System.err.println("Wrong parameters: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(Cube.class.getSimpleName(), options);
            System.exit(-1);
        }
    }

    /**
     * Get the job options, passed in the args
     *
     * @return
     */
    private Options getOptions() {
        // Create options
        Options options = new Options();
        /* REQUIRED */
        Option fileOpt = new Option("i", "input-file", true, "The input file containing the data to compute");
        fileOpt.setRequired(true);
        options.addOption(fileOpt);

        Option funcOpt = new Option("f", "aggregate-function", true, "The aggregate function to compute(possible values : sum or count)");
        funcOpt.setRequired(true);
        options.addOption(funcOpt);

        Option outOpt = new Option("o", "ouput-directory", true, "The ouput directory to store the cube");
        outOpt.setRequired(true);
        options.addOption(outOpt);

        Option columnOpt = new Option("c", "column", true, "(Optional) The column position (first column position is 0) containing the values to compute for the sum aggregate function");
        columnOpt.setRequired(false);
        options.addOption(columnOpt);

        Option dimOpt = new Option("d", "output-dimensions", true, "(Optional) "
                + "The dimensions to compute (defaut : all dimensions), "
                + "ex -d='1,0-1-3,3-2' to compute only theses three dimensions");
        dimOpt.setRequired(false);
        options.addOption(dimOpt);

        /* NOT REQUIRED */
        /*options.addOption("r", "reducers", true, "Number of reducers, default is 50").getOption("r")
         .setType(Integer.class);
         options.addOption("e", "environment", true, "Environment <prod|dev|local>, default is prod");
         options.addOption("tkn", "api_token", true, "Makazi API Token");
         options.addOption("srctbl", "sourceTable", true, "Define the table source (default is cube_db_raw)");
         options.addOption("fconf", "file-conf", true, "Configuration file");
         options.addOption("explain", "explain", false, "Explain mode");
         options.addOption("debug", "debug", false, "Debug mode");
         options.addOption("filterdatedim", "filter_on_date_dimension", false, "Filter entry on date dimension");*/
        return options;
    }
}
