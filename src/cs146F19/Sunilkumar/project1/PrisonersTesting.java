package cs146F19.Sunilkumar.project1;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class PrisonersTesting extends TestCase {
	
	@Test
	public void test() {
		Prisoners prisoners=new Prisoners(6);
		
		assertEquals(1, prisoners.play(6, 2));
		assertEquals(1, prisoners.play(1, 9)); 
		assertEquals(4, prisoners.play(7, 7)); 
		assertEquals(2, prisoners.play(12, 8)); 
		assertEquals(3, prisoners.play(5, 1)); 
	}

}
