package utils;

public class CommandResult {
    public int exitCode;
    public String output;
    public String error;

    public boolean isSuccess() {
        return exitCode == 0;
    }
}
