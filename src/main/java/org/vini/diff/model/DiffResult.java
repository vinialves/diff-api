package org.vini.diff.model;

import java.util.List;

/**
 * This class has the information about
 * the result of the diff operation.
 * 
 * @author Vinicius Alves
 */
public class DiffResult {

	/** The id. */
	private String id;
	
	/** The equals. */
	private boolean equals;
	
	/** The equal size. */
	private boolean equalSize;
	
	/** The differences. */
	private List<ByteDiff> differences;
	
	/**
	 * Instantiates a new diff result.
	 */
	public DiffResult() {
	}
	
	/**
	 * Instantiates a new diff result.
	 *
	 * @param id the id
	 * @param equals the equals
	 * @param equalSize the equal size
	 * @param differences the differences
	 */
	public DiffResult(String id, boolean equals, boolean equalSize,  List<ByteDiff> differences) {
		super();
		this.id = id;
		this.equals = equals;
		this.equalSize = equalSize;
		this.differences = differences;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Checks if is equals.
	 *
	 * @return true, if is equals
	 */
	public boolean isEquals() {
		return equals;
	}
	
	/**
	 * Sets the equals.
	 *
	 * @param equals the new equals
	 */
	public void setEquals(boolean equals) {
		this.equals = equals;
	}
	
	/**
	 * Checks if is equal size.
	 *
	 * @return true, if is equal size
	 */
	public boolean isEqualSize() {
		return equalSize;
	}
	
	/**
	 * Sets the equal size.
	 *
	 * @param equalSize the new equal size
	 */
	public void setEqualSize(boolean equalSize) {
		this.equalSize = equalSize;
	}

	/**
	 * Gets the differences.
	 *
	 * @return the differences
	 */
	public List<ByteDiff> getDifferences() {
		return differences;
	}
	
	/**
	 * Sets the differences.
	 *
	 * @param differences the new differences
	 */
	public void setDifferences(List<ByteDiff> differences) {
		this.differences = differences;
	}
	
}
