package com.example.mock;

import org.junit.Assert;
import org.junit.Test;

import com.example.db.DBHelper;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

/**
 * Mocked test for Greeting class.
 * 
 * JMockit supports (and requires) the use of JUnit (version 4 or 5) or TestNG; specifically, you need to:
 *   1. Add the jmockit dependency/jar to the test classpath.
 *   2. Configure the test execution JVM to start with the "-javaagent:the_proper_path/jmockit.1.x.jar" 
 *      initialization parameter. It can be specified in the build script file for tools such as Maven 
 *      or Gradle, or in a "Run/Debug Configuration" for IntelliJ IDEA or Eclipse.
 */
public class GreetingTest {
	@Mocked
	DBHelper dbh;  // mock to DBHelper, no need to initialize.

	@Test
	public void test_greetById() {
		// prepare the mocked object. 
		new Expectations() {{
			dbh.getUserNameById("001"); result = "Tom";
			dbh.getUserNameById("002"); result = "Mike";
			dbh.getUserNameById("003"); result = "John";
		}};

		// use the mocked object to create the testing class.
		Greeting gt = new Greeting(dbh);
		
		//perform the tests.
		Assert.assertEquals("Good day! Tom.", gt.greetById("001"));
		Assert.assertEquals("Good day! Mike.", gt.greetById("002"));
		Assert.assertEquals("Good day! John.", gt.greetById("003"));
		Assert.assertEquals("Good day! Mike.", gt.greetById("002"));
		
		// verify the mocked instance been called. 
		new Verifications() {{
			gt.greetById("001"); times = 1;
			gt.greetById("002"); times = 2;
			gt.greetById("003"); times = 1;
		}};
	}
}
