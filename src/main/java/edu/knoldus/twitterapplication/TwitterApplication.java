package edu.knoldus.twitterapplication;

import edu.knoldus.twitterapplication.operations.TwitterOperations;

public class TwitterApplication {

    public static void main(String[] args) {

        String hashTag = "#main";
        String user = "@rajat";

        TwitterOperations twitterOperation = new TwitterOperations();
        twitterOperation.getTweetsOnHashTag(hashTag).thenAccept(tweets -> tweets.forEach(System.out::println));
        twitterOperation.getNumberOfTweets(hashTag).thenAccept(System.out::println);
        twitterOperation.getAverageTweetsPerDay(user).thenAccept(System.out::println);
        twitterOperation.getAverageReTweets(user)
                .thenAccept(listOfReTweetCount -> listOfReTweetCount.forEach(System.out::println));
        twitterOperation.getAverageLikes(user)
                .thenAccept(listOfReTweetCount -> listOfReTweetCount.forEach(System.out::println));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
