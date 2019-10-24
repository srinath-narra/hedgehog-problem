package com.hedgehog.traverse;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class HedgeHogTreeTraverseTest {
	
	   @Test
	   public void testHedgeHogTreeTraverse() throws Exception {
		   	File inputFile = new File("input.txt");
	        String status = HedgeHogTreeTraverse.initlizeData(inputFile);
	        assertEquals("Success", status);
	   }

}
