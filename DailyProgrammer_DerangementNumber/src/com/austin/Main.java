package com.austin;

import java.util.stream.IntStream;

import com.austin.derangements.DerangementNumber;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/9cvo0f/20180904_challenge_367_easy_subfactorials_another/
 * 
 * @author Austin Malmberg
 *
 */

public class Main {

	public static void main(String[] args) {
		DerangementNumber derangementNumber = new DerangementNumber();
		
		int[] challenges = {0, 5, 6, 9, 14, 23};
//		Arrays.stream(challenges).forEach(derangementNumber::print);
		
		IntStream.rangeClosed(0, derangementNumber.MAX_VALUE).forEach(i -> 
				System.out.printf("%2d => %d%n", i, derangementNumber.get(i)));
	}
}
