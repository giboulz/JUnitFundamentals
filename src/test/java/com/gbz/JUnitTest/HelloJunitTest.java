package com.gbz.JUnitTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
@Category(GoodTestCategory.class)
public class HelloJunitTest extends TestCase {
	
	@Test
	public void test(){
		//fail("yolo"); 
	}
}
