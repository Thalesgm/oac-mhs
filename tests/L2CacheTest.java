import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class L2CacheTest {
	Disc d = new Disc();
	MainMem m = new MainMem();
	L2Cache l = new L2Cache();
	@Before
	public void setup() {
		for(int i = 0; i < 240; i++){	// Filling the disc.
			d.load(i);
		}
		for(int i = 0; i < 10; i++) {
			m.load(d.page[i]);
		}
		l.load(m.page[0].getBlock(0));
		l.load(m.page[0].getBlock(4));
		l.load(m.page[1].getBlock(8));
		l.load(m.page[1].getBlock(12));
		l.load(m.page[2].getBlock(16));
	}
	// 0123 4567 891011 12131415 16171819
	@Test
	public void testHitCheck() {
		Assert.assertEquals(0, l.hitcheck(0));
		Assert.assertEquals(-1, l.hitcheck(50));
		Assert.assertEquals(4, l.hitcheck(17));
	}
	@Test
	public void testGetLRU() {
		Assert.assertEquals(0, l.getLRU());
		l.load(m.page[2].getBlock(20));
		Assert.assertEquals(1, l.getLRU());
	}
	
	@Test
	public void testUpdate() {
		Assert.assertEquals(0, l.getLRU());
		l.update(0, 50);
		Assert.assertEquals(1, l.getLRU());
		Assert.assertEquals(50, l.block[0].word[0].data);
	}

}
