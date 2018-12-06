import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class MainMemTest {
	Disc d = new Disc();
	MainMem m = new MainMem();
	@Before
	public void setup() {
		for(int i = 0; i < 240; i++){	// Filling the disc.
			d.load(i);
		}
		for(int i = 0; i < 10; i++) {
			m.load(d.page[i]);
		}
	}
	
	@Test
	public void testGetPage() {
		Assert.assertEquals(0, m.getPage(0));
		Assert.assertEquals(9, m.getPage(79));
		Assert.assertEquals(-1, m.getPage(300));
	}
	
	@Test
	public void testGetLRU() {
		Assert.assertEquals(0, m.getLRU());
		m.load(d.page[10]);
		Assert.assertEquals(1, m.getLRU());
	}
	
	@Test
	public void testUpdate() {
		Assert.assertEquals(0, m.getLRU());
		Assert.assertEquals(0, m.page[0].word[0].data );
		m.update(0, 50);
		Assert.assertEquals(1, m.getLRU());
		Assert.assertEquals(50, m.page[0].word[0].data );
	}

}
