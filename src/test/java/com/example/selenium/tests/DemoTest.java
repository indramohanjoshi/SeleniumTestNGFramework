package com.example.selenium.tests;

import static org.testng.Assert.assertEquals;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class DemoTest extends AbstractTest {


	@Test(priority = 1)
	public void demoTest1() {
		performLogin();
		System.out.println("demoTest1");
	}

	@Test(dependsOnMethods = { "demoTest1" })
	public void demoTest2() {
		performLogin();
		System.out.println("demoTest2");
	}

	@Test()
	public void demoTest3() {
		performLogin();
		System.out.println("demoTest3");
		assertEquals(false, true);
	}

	@Test()
	public void demoTest4() {
		performLogin();
		System.out.println("demoTest4");
		throw new SkipException("skipping this test");
	}

}
