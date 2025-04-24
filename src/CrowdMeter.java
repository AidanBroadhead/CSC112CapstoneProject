package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrowdMeter {

    private BufferedWriter crowdMeterWriter;

    // constructor
    public CrowdMeter(String filePath) throws IOException {
        // create writer
        this.crowdMeterWriter = new BufferedWriter(new FileWriter(filePath, true));
    }

    // method to write crowd size to the file
    public void logCrowdSize(LocalDateTime currentTime, int gymSize) throws IOException {
        // format time
        String timestamp = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        // prints star to represent person in gym
        String crowdRepresentation = "*".repeat(gymSize);

        // write the timestamp and star to the crown meter file
        crowdMeterWriter.write(timestamp + ": " + crowdRepresentation);
        crowdMeterWriter.newLine();
    }

    // method to close the writer when done
    public void close() throws IOException {
        if (crowdMeterWriter != null) {
            crowdMeterWriter.close();
        }
    }

}
