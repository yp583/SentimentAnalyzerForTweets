import java.util.ArrayList;
import java.util.Arrays;

public class Tweet {
    private int userID;
    private int sentiment;
    private long tweetID;
    private String date;
    private String username;
    private String text;
    public static final String[] STOPWORDS = new String[]{"i",
            "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers",
            "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves",
            "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is",
            "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do",
            "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because",
            "as", "until", "while", "of", "at", "by", "for", "with", "about", "against",
            "between", "into", "through", "during", "before", "after", "above", "below", "to",
            "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all",
            "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor",
            "not", "only", "own", "same", "so", "than", "too", "very", "s", "t", "can", "will",
            "just", "don", "should", "now"};

    public Tweet(int userID, int sentiment, long tweetID, String date, String username, String text) {
        this.userID = userID;
        this.sentiment = sentiment;
        this.tweetID = tweetID;
        this.date = date;
        this.username = username;
        this.text = text;
    }


    public String toString() {
        String polarity = "";
        if (sentiment == 4) {
            polarity = "\uD83D\uDE00";
        } else if (sentiment == 0) {
            polarity = "\uD83D\uDE41";
        } else {
            polarity = "\uD83D\uDE10";
        }
        return "@" + username + ": " + text + " on " + date + " - " + polarity;
    }
    public ArrayList<String> splitTweet(){
        ArrayList<String> tweetList = contains(text.split("\\W+"), STOPWORDS);
        return tweetList;
    }
    public ArrayList<String> contains(String[] input, String[] things2find){
        ArrayList<String> output = new ArrayList<>();
        boolean[] shouldAdd = new boolean[input.length];
        for (int i = 0; i < things2find.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (!input[j].equalsIgnoreCase(things2find[i])){
                    shouldAdd[j] = true;
                }
                else{
                    shouldAdd[j] = false;
                }
            }
        }
        for (int i = 0; i < input.length; i++) {
            if(shouldAdd[i] == true){
                output.add(input[i]);
            }
        }
        return output;
    }


    public int getSentiment() {
        return sentiment;
    }

}
