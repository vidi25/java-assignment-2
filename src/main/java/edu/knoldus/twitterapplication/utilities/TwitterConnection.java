package edu.knoldus.twitterapplication.utilities;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Properties;

public class TwitterConnection {

    public Twitter getTwitterInstance() {
        Properties properties = new Properties();
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(properties.getProperty("CONSUMER_KEY"))
                .setOAuthConsumerSecret(properties.getProperty("CONSUMER_SECRET"))
                .setOAuthAccessToken(properties.getProperty("ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(properties.getProperty("ACCESS_TOKEN_SECRET"));

        return new TwitterFactory(configurationBuilder.build()).getInstance();
    }
}
