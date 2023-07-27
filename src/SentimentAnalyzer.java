import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class SentimentAnalyzer {
    Hashtable<String, Float> scoresHappy;
    Hashtable<String, Float> scoresSad;
    int totalHappyTweets;
    int totalSadTweets;
    int totalTweets;
    String filename;
    public SentimentAnalyzer(String filename){
        scoresHappy = new Hashtable<>();
        scoresSad = new Hashtable<>();
        this.filename = filename;
    }
    public void TrainSentiment() {
        ArrayList<Tweet> tweetData = loadData(filename);
        Hashtable<String, Integer> occursInHappy = new Hashtable<>();
        Hashtable<String, Integer> occursInSad = new Hashtable<>();
        totalHappyTweets = 0;
        totalSadTweets = 0;
        totalTweets = tweetData.size();
        for (int i = 0; i < tweetData.size(); i++) {
            Tweet currTweet = tweetData.get(i);
            ArrayList<String> words = currTweet.splitTweet();
            if (currTweet.getSentiment() == 0) {
                totalSadTweets += 1;
                for (int j = 0; j < words.size(); j++) {
                    String word = words.get(j);
                    if (occursInSad.containsKey(word)) {
                        occursInSad.put(word, occursInSad.get(word) + 1);
                    } else {
                        occursInSad.put(word, 1);
                    }
                }
            } else if (currTweet.getSentiment() == 4) {
                totalHappyTweets += 1;
                for (int j = 0; j < words.size(); j++) {
                    String word = words.get(j);
                    if (occursInHappy.containsKey(word)) {
                        occursInHappy.put(word, occursInHappy.get(word) + 1);
                    } else {
                        occursInHappy.put(word, 1);
                    }
                }
            }
        }
        Enumeration<String> eHappy = occursInHappy.keys();
        Enumeration<String> eSad = occursInSad.keys();

        while (eHappy.hasMoreElements()) {
            String key = eHappy.nextElement();
            float score = ((float) (occursInHappy.get(key) + 1)) / ((float) (totalHappyTweets + 1));
            scoresHappy.put(key, score);
        }
        while (eSad.hasMoreElements()) {
            String key = eSad.nextElement();
            float score = ((float) (occursInSad.get(key) + 1)) / ((float) (totalSadTweets + 1));
            scoresSad.put(key, score);
        }
    }
    public int calcSentiment(String input){
        String[] inputWords = input.split("\\W+");
        float inputHappyScore = ((float)totalHappyTweets)/((float)totalTweets);
        float inputSadScore = ((float)totalSadTweets)/((float)totalTweets);
        for (int i = 0; i < inputWords.length; i++) {
            String inputWord = inputWords[i];
            if(scoresHappy.containsKey(inputWord)){
                inputHappyScore *= scoresHappy.get(inputWord);
            }
            if(scoresSad.containsKey(inputWord)){
                inputSadScore *= scoresSad.get(inputWord);
            }
        }
        return (inputHappyScore==inputSadScore ? 2 : (inputHappyScore > inputSadScore ? 4 : 0));
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
