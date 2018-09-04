package com.austin.derangements;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import com.austin.exceptions.OverflowException;

/**
 * A class that counts the number of derangements in a set of size n. A derangement is a permutation of elements in a set,
 * such that no element appears in its original position.
 * 
 * Generates a memoization table when this class is first initialized (not that it is necessarily needed).
 * 
 * @author Austin Malmberg
 */
public class DerangementNumber {
	
	public final int MAX_VALUE;
	
	private TreeMap<Integer, Long> memoTable;
	
	public DerangementNumber() {
		memoTable = new TreeMap<>();
		memoTable.put(0, Long.valueOf(1));
		
		int n = 0;
		while(true) {
			
			try {
				
				// populate the memoization table
				createNewMemoEntry(++n);
				
			} catch(OverflowException overflow) {
				break;
			}
			
		}
		
		MAX_VALUE = memoTable.lastKey();
	}
	
	
	public void print(int n) throws NoSuchElementException, OverflowException {
		System.out.println(get(n));
	}
	
	
	/**
	 * Returns the derangement number, given
	 * 
	 * @param n The size of the set
	 * @return The derangement number
	 * @throws NoSuchElementException Thrown when n is negative
	 * @throws OverflowException Thrown when the derangement number exceeds Long.MAX_VALUE
	 */
	public long get(int n) throws NoSuchElementException, OverflowException {
		if(n < 0)
			throw new NoSuchElementException("n must be a positive integer.");
		
		if(!memoTable.containsKey(n))
			throw new OverflowException(String.format("The derangement number of %d exceeds Long.MAX_VALUE.", n));
		
		return memoTable.get(n);
	}
	
	private void createNewMemoEntry(int n) throws OverflowException {
		try {
			memoTable.put(n, calculate(n));
		} catch(OverflowException overflow) {
			throw overflow;
		}
	}
	
	
	/**
	 * 
	 * @param n The size of the set
	 * @return The derangement number of the set containing n elements
	 * @throws OverflowException Thrown when the derangement number exceeds Long.MAX_VALUE 
	 */
	private long calculate(int n) throws OverflowException {
		
		// perform the calculation to find the derangement number
		long ans = n * memoTable.get(n-1) + (long) Math.pow(-1, n);
		
		// check for overflow
		if(ans < 0)
			throw new OverflowException(String.format("The derangement number of %d exceeds Long.MAX_VALUE.", n));
		
		return  ans;
	}
}
