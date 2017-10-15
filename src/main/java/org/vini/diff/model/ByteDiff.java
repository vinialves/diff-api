package org.vini.diff.model;

/**
 * This class contains detailed information about
 * the byte index and the number of different bits
 * for the respective byte index
 * 
 * @author Vinicius Alves
 */
public class ByteDiff {

	/** The byte index. */
	private int byteIndex;
	
	/** The number of different bits. */
	private int numberOfDifferentBits;
	
	/**
	 * Instantiates a new byte diff.
	 */
	public ByteDiff() {
	}
	
	/**
	 * Instantiates a new byte diff.
	 *
	 * @param byteIndex the byte index
	 * @param numberOfDifferentBits the number of different bits
	 */
	public ByteDiff(int byteIndex, int numberOfDifferentBits) {
		super();
		this.byteIndex = byteIndex;
		this.numberOfDifferentBits = numberOfDifferentBits;
	}
	
	/**
	 * Gets the byte index.
	 *
	 * @return the byte index
	 */
	public int getByteIndex() {
		return byteIndex;
	}
	
	/**
	 * Sets the byte index.
	 *
	 * @param byteIndex the new byte index
	 */
	public void setByteIndex(int byteIndex) {
		this.byteIndex = byteIndex;
	}
	
	/**
	 * Gets the number of different bits.
	 *
	 * @return the number of different bits
	 */
	public int getNumberOfDifferentBits() {
		return numberOfDifferentBits;
	}
	
	/**
	 * Sets the number of different bits.
	 *
	 * @param numberOfDifferentBits the new number of different bits
	 */
	public void setNumberOfDifferentBits(int numberOfDifferentBits) {
		this.numberOfDifferentBits = numberOfDifferentBits;
	}
}
