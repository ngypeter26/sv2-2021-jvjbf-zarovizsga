package videos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();


    public void readDataFromFile(Path path){
        try (Scanner scanner = new Scanner(path)) {
            scanner.nextLine();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                channels.add(parseLine(line));
            }
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }

    }

    public List<Channel> getChannels() {
        return channels;
    }

    private Channel  parseLine(String line){
        String[] parts = line.split(";");
        String channelName = parts[0];
        int subscriptions = Integer.parseInt(parts[1]);
        int numberOfVideos = Integer.parseInt(parts[2]);
        return new Channel(channelName,subscriptions,numberOfVideos);
    }

    public int calculateSumOfVideos(){
        int sum = 0;
        for (Channel channel : channels){
            sum += channel.getNumberOfVideos();
        }
        return sum;
    }
}
