import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class NDimensionalAnalyzer {
    ArrayList<Tweet> tweetData;
    Hashtable<String, Integer> wordsHash;
    int[][] points;
    int[] sentimentPoints;
    public NDimensionalAnalyzer(String filename){
        tweetData = loadData(filename);
    }
    public void getPoints(){
        wordsHash = new Hashtable<String, Integer>();
        int w = 0;
        for (int i = 0; i < tweetData.size(); i++) {
            Tweet tweet = tweetData.get(i);
            ArrayList<String> words = tweet.splitTweet();
            for (int j = 0; j < words.size(); j++) {
                String word = words.get(j);
                if (!wordsHash.containsKey(word)){
                    wordsHash.put(word, w);
                    w++;
                }
            }
        }
        int size = wordsHash.size();
        points = new int[tweetData.size()][size];
        sentimentPoints = new int[tweetData.size()];
        for (int i = 0; i < tweetData.size(); i++) {
            Tweet tweet = tweetData.get(i);
            ArrayList<String> words = tweet.splitTweet();
            for (int j = 0; j < words.size() ; j++) {
                String word = words.get(j);
                points[i][wordsHash.get(word)] += 1;
            }
            sentimentPoints[i] = tweet.getSentiment();
        }
    }
    public int calcSentiment(String input, int amountClosest){
        String[] inputWords = input.split("\\W+");
        int[] inputpoint = new int[wordsHash.size()];
        for (int i = 0; i < inputWords.length; i++) {
            String inputWord = inputWords[i];
            if (wordsHash.containsKey(inputWord)){
                inputpoint[wordsHash.get(inputWord)] += 1;
            }
        }
        float[] shortestDists = new float[amountClosest];
        float shortestDist = Float.MAX_VALUE;
        for (int i = 0; i < shortestDists.length; i++) {
            shortestDists[i] = Float.MAX_VALUE;
        }
        int bestMatchIndex = -1;
        int[] closestMatchIndexes = new int[amountClosest];
        for (int i = 0; i < tweetData.size(); i++) {
            float dist = getDist(points[i], inputpoint);
            if (shortestDist > dist){
                shortestDist = dist;
                bestMatchIndex = i;
            }
            for (int j = 0; j < shortestDists.length; j++) {
                if (shortestDists[j] > dist){
                    shortestDists[j] = dist;
                    closestMatchIndexes[j] = i;
                    break;
                }
            }

        }
        float averageSentiment = 0;
        float totalSumDist = 0;
        for (int i = 0; i < closestMatchIndexes.length; i++) {
            System.out.println(tweetData.get(closestMatchIndexes[i]));
            averageSentiment += (float)sentimentPoints[closestMatchIndexes[i]]/(shortestDists[i] + .0001f);
            totalSumDist += (float)1/(shortestDists[i]+.0001f);
        }
        averageSentiment /= totalSumDist;
        int outSentiment = averageSentiment <= 2 ? 0 : 4;
        return outSentiment;
    }
    public float getDist(int[] a, int[] b){
        float dist = 0;
        for (int i = 0; i < a.length; i++) {
            dist += ((float)((a[i] - b[i])*(a[i] - b[i])));
        }
        return (float)Math.sqrt(dist);
    }



    public static ArrayList<Tweet> loadData(String filename) {
        ArrayList<Tweet> allTweets = new ArrayList<>();

        try {
            File inFile = new File(filename);
            Scanner scan = new Scanner(inFile);
            while (scan.hasNext()) {
                // each line in the data file has the following structure:
                // userID, sentiment, tweetID, date, username, text
                String tmpTweetLine = scan.nextLine();
                String[] tmpTweetLineSplit = tmpTweetLine.split(",");
                int tmpUserID = Integer.parseInt(tmpTweetLineSplit[0]);
                int tmpSentiment = Integer.parseInt(tmpTweetLineSplit[1]);
                long tmpTweetID = Long.parseLong(tmpTweetLineSplit[2]);
                String tmpDate = tmpTweetLineSplit[3];
                String tmpUsername = tmpTweetLineSplit[4];
                String tmpText = tmpTweetLineSplit[5];
                Tweet tmpTweet = new Tweet(tmpUserID, tmpSentiment, tmpTweetID, tmpDate, tmpUsername, tmpText);
                allTweets.add(tmpTweet);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return allTweets;
    }
}
