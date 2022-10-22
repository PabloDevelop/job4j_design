package srp.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVOutput implements Output {

    private final String path;

    public CSVOutput(String path) {
        this.path = path;
    }

    @Override
    public void output(String report) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path))) {
            out.write(report);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
