package org.enrollment.mp;

import junit.framework.TestCase;

public class SubjectTest extends TestCase {
    Subject sub= new Subject();
	public void testSetSubjectCode() {
		String[] item=sub.getSubjectCode();
		String theItem=item[3];
		assertEquals("BIT111", theItem);
		String theItems=item[5];
		assertEquals("BIT121", theItems);
	}

	public void testSetSubjectName() {
		// write two asserts here as you like
		
	}
	
	public void testSetSemester() {
		
	}
}
