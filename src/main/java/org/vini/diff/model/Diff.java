package org.vini.diff.model;

/**
 * This class has the information 
 * about the requested diff.
 * 
 * @author Vinicius Alves
 */
public class Diff {

	/** The id. */
	private String id;
	
	/** The left. */
	private String left;
	
	/** The right. */
	private String right;

	/**
	 * Instantiates a new diff.
	 *
	 * @param id the id
	 * @param left the left
	 * @param right the right
	 */
	public Diff(String id, String left, String right) {
		super();
		this.id = id;
		this.left = left;
		this.right = right;
	}

	/**
	 * Instantiates a new diff.
	 */
	public Diff() {
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
	 * Gets the left.
	 *
	 * @return the left
	 */
	public String getLeft() {
		return left;
	}
	
	/**
	 * Sets the left.
	 *
	 * @param left the new left
	 */
	public void setLeft(String left) {
		this.left = left;
	}
	
	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public String getRight() {
		return right;
	}
	
	/**
	 * Sets the right.
	 *
	 * @param right the new right
	 */
	public void setRight(String right) {
		this.right = right;
	}
	
}
