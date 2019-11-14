package com.ibm.homework.external;

public class QuoteTriviaFetcher extends AbstractTriviaFetcher {
    private static final String QUOTE_SOURCE = "https://ron-swanson-quotes.herokuapp.com/v2/quotes";

    public QuoteTriviaFetcher() {
	super(QUOTE_SOURCE);
    }

    @Override
    public String getTrivia() {
	String trivia = fetchTrivia();
	return trivia.substring(2, trivia.length() - 2);
    }
}
