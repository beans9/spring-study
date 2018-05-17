package spring.beans9.study.file;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calcurator calcurator;
	String numFilepath;
	
	@Before
	public void setup() {
		this.calcurator = new Calcurator();
		this.numFilepath = getClass().getResource("number.txt").getPath();
	}
	
	@Test
	public void sumOfNumber() throws Exception {
		assertThat(calcurator.calcSum(numFilepath), equalTo(10));
		System.out.println("done");
	}
	
	@Test 
	public void mulOfNumber() throws Exception {
		assertThat(calcurator.calcMultiply(numFilepath), equalTo(24));
	}
	
	@Test
	public void concat() throws Exception{
		assertThat(calcurator.concat(numFilepath), is("1234"));
		System.out.println(calcurator.concat(numFilepath));
	}
}
