package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {

    public static CommandResult run(String... command) {
        CommandResult result = new CommandResult();
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();

        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(false);

            Process process = pb.start();

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;

            while ((line = stdOut.readLine()) != null) {
                output.append(line).append(Constants.SINGLE_QUOTE_INFO_SEPARATOR.get());
            }

            while ((line = stdErr.readLine()) != null) {
                output.append(line).append(Constants.SINGLE_QUOTE_INFO_SEPARATOR.get());
            }

            result.exitCode = process.waitFor();
            result.output = output.toString().trim();
            result.error = error.toString().trim();

        } catch (Exception e) {
            result.exitCode = -1;
            result.error = e.getMessage();
        }

        return result;
    }

    public static CommandResult runTool(String toolName, String... args) {
        List<String> command = new ArrayList<>();

        command.add(Constants.TOOLS_FOLDER.get() + toolName);

        if (args != null) {
            for (String arg : args) {
                if (arg != null && !arg.isEmpty()) {
                    command.add(arg);
                }
            }
        }

        return run(command.toArray(new String[0]));
    }
}
