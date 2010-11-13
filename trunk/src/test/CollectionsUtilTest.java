package test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import test.model.Person;
import test.model.PreData;

public class CollectionsUtilTest {
	private List<Person> list;
	
	@Before
	public void setup(){
		PreData data = new PreData();
		list = data.getSampleList();
	}
	
	@Test
	public void testFilter() {
		fail("Not yet implemented");
	}

	@Test
	public void testFirstIndexOfListOfTFilterOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastIndexOfListOfTFilterOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testFirstIndexOfTArrayFilterOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastIndexOfTArrayFilterOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testAllMatchIndex() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrim() {
		fail("Not yet implemented");
	}

	@Test
	public void testAggregateFuncOfTListOfT() {
		
	}

	@Test
	public void testAggregateFuncOfTListOfQString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSplit() {
		fail("Not yet implemented");
	}

}
