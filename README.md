# SentimentAnalyzerForTweets
This project analyzes the sentiment from tweet using two different artificial intelligence algorithms.

## Text Vectorization
The first algorithm is a text vectorization algorithm, which removes irrelevant words from the tweet, such as "the" or "at" (stopwords), and then vectorizates the resulting string with each word represented as a dimension. Then a distance algoritm is applied between the inputted vectorized tweet and the algorithm's database of vectorized tweets (these tweets have an additional dimension representing whether they are happy or sad). If the inputted tweet is closer to a happy tweet then it is also classified has happy. If it is closer to a sad tweet then it is classified as sad as well.

## Naive Bayes
The second algorithm is a simple naive Bayes algorithm. It approached the problem of sentiment analysis in a probabistic way. It takes the training data and sifts through it all, counting how many times various words appear in happy or sad tweets. Notice, this results in a pretty simple way of calculating the probability of a word being in a sad or happy tweet with these two counts. Once the algorithm has this data, it can classify a new tweet by parsing the words in the string and using simple probability formulas to decide whether the entire tweet (just a combination of words, or  probabilities) is happy or sad.
