package com.ibm.homework.external;

/**
 * Joke fetcher class.
 *
 */
public class JokeTriviaFetcher extends AbstractTriviaFetcher {
	private static final String JOKE_SOURCE = "https://official-joke-api.appspot.com/jokes/random";

	public JokeTriviaFetcher() {
		super(JOKE_SOURCE);
	}

	@Override
	public String getTrivia() {
		String trivia = fetchTrivia();
		int setupIndex = trivia.indexOf("\"setup");
		return trivia.substring(setupIndex, trivia.length() - 2);
	}
}
