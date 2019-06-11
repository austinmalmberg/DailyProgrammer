package com.austin.challenge366.funnels;

import java.io.IOException;

import com.austin.challenge366.WordMap;

public abstract class WordFunnel {

	public final String DICT_FILENAME = "enable1.txt";
	public final WordMap wordMap;
	
	public WordFunnel() throws IOException  {
		wordMap = new WordMap();
	}
	
	public WordFunnel(WordMap wordMap) {
		this.wordMap = wordMap;
	}
	
	public abstract void printChallenges();
}
