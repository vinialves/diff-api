package org.vini.diff.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.vini.diff.model.ByteDiff;
import org.vini.diff.model.Diff;
import org.vini.diff.model.DiffResult;

public class DiffServiceTest {
	
	private DiffService diffService;
	private Diff diff;
	
	@Before
	public void setUp() {
		diffService = new DiffService();
		diff = new Diff();
		diff.setId("123");
		diff.setLeft("dmluaWNpdXM=");
		diff.setRight("dmluaWNpdXM=");
	}
	
	@Test
	public void testDiffServiceEquals(){
		diffService.addLeft(diff.getId(), diff.getLeft());
		diffService.addRight(diff.getId(), diff.getRight());
		DiffResult diffResult = diffService.getDiffResult(diff.getId());
		assertEquals(diff.getId(), diffResult.getId());
		assertTrue(diffResult.isEquals());
		assertTrue(diffResult.isEqualSize());
		assertTrue(diffResult.getDifferences().isEmpty());
	}
	
	@Test
	public void testDiffServiceNotEqualsDifferentSize(){
		diff.setRight("YW5hbmRh");
		diffService.addLeft(diff.getId(), diff.getLeft());
		diffService.addRight(diff.getId(), diff.getRight());
		DiffResult diffResult = diffService.getDiffResult(diff.getId());
		assertEquals(diff.getId(),diffResult.getId());
		assertFalse(diffResult.isEquals());
		assertFalse(diffResult.isEqualSize());
		assertTrue(diffResult.getDifferences().isEmpty());
	}
	
	@Test
	public void testDiffServiceOnlySizeEquals(){
		diff.setRight("aXNhYmVsbGU=");
		diffService.addLeft(diff.getId(), diff.getLeft());
		diffService.addRight(diff.getId(), diff.getRight());
		DiffResult diffResult = diffService.getDiffResult(diff.getId());
		assertEquals(diff.getId(),diffResult.getId());
		assertFalse(diffResult.isEquals());
		assertTrue(diffResult.isEqualSize());
		List<ByteDiff> differences = diffResult.getDifferences();
		assertFalse(differences.isEmpty());
		assertEquals(8, differences.size());
		assertEquals(0,differences.get(0).getByteIndex());
		assertEquals(5,differences.get(0).getNumberOfDifferentBits());
		assertEquals(1,differences.get(1).getByteIndex());
		assertEquals(3,differences.get(1).getNumberOfDifferentBits());
		assertEquals(2,differences.get(2).getByteIndex());
		assertEquals(4,differences.get(2).getNumberOfDifferentBits());
		assertEquals(3,differences.get(3).getByteIndex());
		assertEquals(3,differences.get(3).getNumberOfDifferentBits());
		assertEquals(4,differences.get(4).getByteIndex());
		assertEquals(2,differences.get(4).getNumberOfDifferentBits());
		assertEquals(5,differences.get(5).getByteIndex());
		assertEquals(2,differences.get(5).getNumberOfDifferentBits());
		assertEquals(6,differences.get(6).getByteIndex());
		assertEquals(3,differences.get(6).getNumberOfDifferentBits());
		assertEquals(7,differences.get(7).getByteIndex());
		assertEquals(3,differences.get(7).getNumberOfDifferentBits());
	}
	
	@Test
	public void testDiffServiceOnlySizeEqualsOnlyOneByteDiff(){
		diff.setRight("dmluaWNpdXU=");
		diffService.addLeft(diff.getId(), diff.getLeft());
		diffService.addRight(diff.getId(), diff.getRight());
		
		ByteDiff expectedByteDiff = new ByteDiff();
		expectedByteDiff.setByteIndex(7);
		expectedByteDiff.setNumberOfDifferentBits(2);
		
		List<ByteDiff> expectedByteDiffList = new ArrayList<ByteDiff>();
		expectedByteDiffList.add(expectedByteDiff);
		
		DiffResult expectedDiffResult = new DiffResult();
		expectedDiffResult.setDifferences(expectedByteDiffList);
		expectedDiffResult.setId(diff.getId());
		expectedDiffResult.setEquals(false);
		expectedDiffResult.setEqualSize(true);
		
		DiffResult diffResult = diffService.getDiffResult(diff.getId());
		assertEquals(expectedDiffResult.getId(), diff.getId());
		assertTrue(expectedDiffResult.isEquals() == diffResult.isEquals());
		assertTrue(expectedDiffResult.isEqualSize() == diffResult.isEqualSize());
		List<ByteDiff> differences = diffResult.getDifferences();
		assertFalse(differences.isEmpty());
		assertEquals(1, differences.size());
		assertEquals(expectedDiffResult.getDifferences().get(0).getByteIndex(), differences.get(0).getByteIndex());
		assertEquals(expectedDiffResult.getDifferences().get(0).getNumberOfDifferentBits(), differences.get(0).getNumberOfDifferentBits());
	}
	
	@Test
	public void testDiffServiceNotExists(){
		DiffResult diffResult = diffService.getDiffResult(diff.getId());
		assertEquals(diff.getId(),diffResult.getId());
		assertFalse(diffResult.isEquals());
		assertFalse(diffResult.isEqualSize());
		assertTrue(diffResult.getDifferences().isEmpty());
	}

}
