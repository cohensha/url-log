import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Comparator.reverseOrder;

public class ReportURL {

    //maps date to inner map of (URL, count for that day)
    private TreeMap<String, Map<String, Integer>> map;
    private String fileName;

    public static void main(String[] args) {
        if(args.length > 0) {
            ReportURL urlLog = new ReportURL(args[0]);
            urlLog.processText();
            urlLog.printReport();
        }
    }

    ReportURL(String fileName) {
        map = new TreeMap<>();
        this.fileName = fileName;
    }

    public void processText() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
        }catch(Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    //makes calls to get date string from unix, and place date+url in map
    public void processLine(String line) {
        String[] parts = line.split("\\|");
       String date = formatDate(parts[0]);
       String website = parts[1];
        placeInMap(date, website);
    }

    public void placeInMap(String date, String website) {
        if(!map.containsKey(date)) {
            HashMap<String, Integer> innerMap = new HashMap<>();
            innerMap.put(website, 1);
            map.put(date, innerMap);
        }
        else {
            int count = map.get(date).containsKey(website) ? map.get(date).get(website) : 0;
            map.get(date).put(website, count + 1);
        }
    }

    public void printReport() {
        for(Map.Entry<String, Map<String,Integer>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            //uses Java 8 lambdas to print map data sorted by descending count value
            Map<String, Integer> innerMap = entry.getValue();
            innerMap.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder()))
                    .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        }
    }

    public String formatDate(String unixStr) {
        long unixSeconds = Long.parseLong(unixStr);
        Date date = new Date(unixSeconds*1000L);
        //set date format
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy z");
        //give reference timezone and format date
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String formattedDate = sdf.format(date);

        return formattedDate;
    }
}



