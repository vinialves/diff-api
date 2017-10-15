package org.vini.diff.model;

/**
 * This class contains the encoded base64 data that will
 * be the input for the left/right data for diff.
 * 
 * @author Vinicius Alves
 */
public class EncodedBase64Data {

	/** The base 64 data. */
	private String base64Data;

	/**
	 * Instantiates a new encoded base 64 data.
	 */
	public EncodedBase64Data() {
	}
	
	/**
	 * Instantiates a new encoded base 64 data.
	 *
	 * @param base64Data the base 64 data
	 */
	public EncodedBase64Data(String base64Data) {
		super();
		this.base64Data = base64Data;
	}

	/**
	 * Gets the base 64 data.
	 *
	 * @return the base 64 data
	 */
	public String getBase64Data() {
		return base64Data;
	}

	/**
	 * Sets the base 64 data.
	 *
	 * @param base64Data the new base 64 data
	 */
	public void setBase64Data(String base64Data) {
		this.base64Data = base64Data;
	}
	
}
