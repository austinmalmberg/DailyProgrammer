package com.austin.challenge366;

import java.io.IOException;

import com.austin.challenge366.funnels.WordFunnel_Easy;
import com.austin.challenge366.funnels.WordFunnel_Intermediate;

public class Main {

	public static void main(String[] args) throws IOException {
		WordMap wordMap = new WordMap();
		
		WordFunnel_Easy easy = new WordFunnel_Easy(wordMap);
		easy.printChallenges();

		WordFunnel_Intermediate intermediate = new WordFunnel_Intermediate(wordMap);
		intermediate.printChallenges();
	}
	
}
