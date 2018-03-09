package edu.knoldus.twitterapplication.operations;

import edu.knoldus.twitterapplication.utilities.TwitterConnection;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TwitterOperations {

    TwitterConnection twitterConnection = new TwitterConnection();
    Twitter twitter = twitterConnection.getTwitterInstance();

    /**
     * returns list of tweets based on hashTag.
     * @param hashTag a string describing a hashTag
     * @return CompletableFuture of list of tweets
     */
    public CompletableFuture<List<Status>> getTweetsOnHashTag(String hashTag) {

        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(hashTag);
            QueryResult result = null;
            try {
                result = twitter.search(query);

            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return result != null ? result.getTweets() : null;
        }).handle((result, ex) -> {
            if (ex != null) {
                return Collections.emptyList();
            }
            return result;
        });

    }

    /**
     * returns count of all tweets based on hashTag.
     * @param hashTag string representing a hashTag
     * @return CompletableFuture of Integer
     */
    public CompletableFuture<Integer> getNumberOfTweets(String hashTag) {
        return getTweetsOnHashTag(hashTag).thenApply(List::size).handle((result, ex) -> {
            if (ex != null) {
                return 0;
            }
            return result;
        });
    }

    /**
     * returns total days between two dates.
     * @param userCreatedDate date user was created
     * @return total days as a double value
     */
    private double findTotalDaysBetweenTwoDates(Date userCreatedDate) {
        Date todayDate = new Date();
        return TimeUnit.DAYS.convert(todayDate.getTime() - userCreatedDate.getTime(), TimeUnit.MILLISECONDS);
    }

    /**
     * calculates average tweets per day of a user.
     * @param user String representing a particular  user
     * @return CompletableFuture of double
     */
    public CompletableFuture<Double> getAverageTweetsPerDay(String user) {
        return CompletableFuture.supplyAsync(() -> {
            Double averageTweetsCount = 0.0;
            try {
                Date userCreatedDate = twitter.showUser(user).getCreatedAt();
                int tweetsCount = twitter.showUser(user).getStatusesCount();
                averageTweetsCount = tweetsCount / findTotalDaysBetweenTwoDates(userCreatedDate);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            return averageTweetsCount;
        }).handle((result, ex) -> {
            if (ex != null) {
                return 0.0;
            }
            return result;
        });
    }

    /**
     * calculates average reTweets per tweet of a user.
     * @param user string representing a user
     * @return Completable future of list of integer
     */
    public CompletableFuture<List<Integer>> getAverageReTweets(String user) {
        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(user);
            List<Integer> averageReTweetsCountList = new ArrayList<>();
            try {
                List<Status> tweetsList = twitter.search(query).getTweets();
                averageReTweetsCountList = tweetsList.stream()
                        .map(tweet -> ((tweet.getRetweetCount()) / (tweetsList.size())))
                        .collect(Collectors.toList());
            } catch (TwitterException ex) {
                ex.printStackTrace();
            }
            return averageReTweetsCountList;
        }).handle((result, ex) -> {
            if (ex != null) {
                return new ArrayList<>();
            }
            return result;
        });
    }

    /**
     * calculates average likes per tweet of a user.
     * @param user string representing a user
     * @return Completable future of list of integer
     */
    public CompletableFuture<List<Integer>> getAverageLikes(String user) {

        return CompletableFuture.supplyAsync(() -> {
            Query query = new Query(user);
            List<Integer> averageReTweetsCountList = new ArrayList<>();
            try {
                List<Status> tweetsList = twitter.search(query).getTweets();
                averageReTweetsCountList = tweetsList.stream()
                        .map(tweet -> ((tweet.getFavoriteCount()) / (tweetsList.size())))
                        .collect(Collectors.toList());
            } catch (TwitterException ex) {
                ex.printStackTrace();
            }
            return averageReTweetsCountList;
        }).handle((result, ex) -> {
            if (ex != null) {
                return new ArrayList<>();
            }
            return result;
        });
    }

}

