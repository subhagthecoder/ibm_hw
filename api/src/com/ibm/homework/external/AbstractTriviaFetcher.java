package com.ibm.homework.external;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * The abstract trivia fetcher class that fetches trivia from the provided source URL.
 */
public abstract class AbstractTriviaFetcher {

	private final String triviaSource;

	protected AbstractTriviaFetcher(String triviaSource) {
		this.triviaSource = triviaSource;
	}

	protected String fetchTrivia() {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(triviaSource);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ triviaSource, e);
		} 

		return sb.toString();
	}

	/**
	 * Abstract get trivia method. 
	 * Concrete implementations will handle the parsing 
	 * to transform the trivia into a presentable form.
	 * 
	 * @return trivia
	 */
	abstract public String getTrivia();
}
