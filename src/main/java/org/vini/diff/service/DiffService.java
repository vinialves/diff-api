package org.vini.diff.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.vini.diff.model.ByteDiff;
import org.vini.diff.model.Diff;
import org.vini.diff.model.DiffResult;

/**
 * The Diff service.
 * 
 * @author Vinicius Alves
 */
@Service
public class DiffService {

	/** The diffs. 
	 * In memory for now, but if it becomes a requirement, 
	 * it can easily be persisted in any desired database. 
	 */
	private List<Diff> diffs = new ArrayList<>();
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(DiffService.class);

	/**
	 * Adds the left data for the diff.
	 *
	 * @param id the diff id
	 * @param left the left data (base64)
	 */
	public void addLeft(String id, String left) {
		Diff d = getDiff(id);
		if (d != null){
			logger.debug("Adding left data for existing diff [" + id + "]");
			d.setLeft(left);
		}else{
			logger.debug("Adding left data for new diff [" + id + "]");
			d = new Diff(id, left, "");
			diffs.add(d);
		}
	}

	/**
	 * Adds the right data for the diff.
	 *
	 * @param id the diff id
	 * @param right the right data (base64)
	 */
	public void addRight(String id, String right) {
		Diff d = getDiff(id);
		if (d != null){
			logger.debug("Adding right data for existing diff [" + id + "]");
			d.setRight(right);
		}else{
			logger.debug("Adding right data for new diff [" + id + "]");
			d = new Diff(id, "", right);
			diffs.add(d);
		}
	}

	/**
	 * Gets the diff from the list in memory.
	 *
	 * @param id the diff id
	 * @return the diff
	 */
	private Diff getDiff(String id) {
		for (int i = 0; i < diffs.size(); i++) {
			Diff d = diffs.get(i);
			if (d.getId().equals(id)) {
				logger.debug("Diff [" + id + "] found.");
				return d;
			}
		}
		logger.debug("Diff [" + id + "] was not found.");
		return null;
	}
	
	/**
	 * Gets the diff result.
	 *
	 * @param id the diff id
	 * @return the diff result
	 */
	public DiffResult getDiffResult(String id) {
		logger.debug("Get diff result for diff [" + id + "].");
		
		Diff d = getDiff(id);
		boolean equals = false;
		boolean equalSize = false;
		List<ByteDiff> differences = new ArrayList<ByteDiff>();
		
		if (d != null){
		
			byte[] firstBytes = Base64.decodeBase64(d.getLeft());
			byte[] secondBytes = Base64.decodeBase64(d.getRight());
			
			//Verify if data have same size.
			equalSize = firstBytes.length == secondBytes.length;
			
			if (equalSize){
				//Data have same size, maybe they are equal.
				equals = Arrays.equals(firstBytes, secondBytes);
			}
			
			//If not equals, but data have same size,
			//provide insight in where the diffs are.
			if (!equals && equalSize){
				byte different = 0;
				for (int index = 0; index < firstBytes.length; index++) {
					int totalDiffBits = 0;
					different = (byte)(firstBytes[index] ^ secondBytes[index]);
					if (different != 0){
						while (different != 0) {
							totalDiffBits++;
							different &= different - 1;
						}
						differences.add(new ByteDiff(index, totalDiffBits));
					}
				}
			}
		}
		
		logger.debug("Diff result for diff [" + id + "] equals ["+ equals + 
				"] equalSize [" + equalSize + "] number of different bytes [" 
				+ differences.size() + "].");
		
		return new DiffResult(id, equals, equalSize, differences);
	}
	
}