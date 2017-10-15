package org.vini.diff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.vini.diff.model.DiffResult;
import org.vini.diff.model.EncodedBase64Data;
import org.vini.diff.service.DiffService;

/**
 * This controller handles the requests:
 *  Add left data for the diff;
 *  Add right data for the diff;
 *  Get the diff.
 *  
 *  @author Vinicius Alves
 */
@RestController
@RequestMapping("/v1/diff/{id}")
public class DiffController {

	/** The diff service. */
	@Autowired
	private DiffService diffService;
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(DiffController.class);

	/**
	 * Adds the left data for the diff.
	 *
	 * @param id the diff id
	 * @param encodedData the encoded data (base64)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/left", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addLeft(@PathVariable String id, @RequestBody EncodedBase64Data encodedData) {
		logger.info("Adding left data for [" + id + "]");
		diffService.addLeft(id, encodedData.getBase64Data());
	}

	/**
	 * Adds the right for the diff.
	 *
	 * @param id the diff id
	 * @param encodedData the encoded data (base64)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/right", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addRight(@PathVariable String id, @RequestBody EncodedBase64Data encodedData) {
		logger.info("Adding right data for [" + id + "]");
		diffService.addRight(id,  encodedData.getBase64Data());
	}

	/**
	 * Gets the diff.
	 *
	 * @param id the diff id
	 * @return the diff result
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DiffResult getDiff(@PathVariable String id) {
		logger.info("Getting diff for [" + id + "]");
		return diffService.getDiffResult(id);
	}
}
