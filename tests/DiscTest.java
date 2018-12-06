import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiscTest {
	Disc d = new Disc();
	@Before
	public void setup() {
		for(int i = 0; i < 240; i++){	// Filling the disc.
			d.load(i);
		}
	}
	
	@Test
	public void testUpdate() {
		//Testing the update Method
		Assert.assertEquals(0, d.page[0].word[0].data); //Checking original value
		d.update(0, 5);									//Updating
		Assert.assertEquals(5, d.page[0].word[0].data);	//Validating the update.
	}

}
