import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class CoreTest {
	Core c = new Core();
	Block bA = new Block(0);
	Block bB = new Block(1);
	Block bC = new Block(2);
	@Before
	public void setup() {
		for(int i = 0; i < 4; i++) {
			bA.word[i].data = i;
			bA.word[i].address = i;
			bB.word[i].data = i+4;
			bB.word[i].address = i+4;
			bC.word[i].data = i+8;
			bC.word[i].address = i+8;
		}
		c.load(bA);
		c.load(bB);
	}
	
	@Test
	public void testgetLRU() {
		Assert.assertEquals(0, c.getLRU());
		c.load(bC);
		Assert.assertEquals(1, c.getLRU());
	}
	@Test
	public void testHitCheck() {
		Assert.assertEquals(0, c.hitcheck(0));
		Assert.assertEquals(1, c.hitcheck(4));
		Assert.assertEquals(-1, c.hitcheck(14));
	}
	
	@Test
	public void testGetBlock() {
		Assert.assertEquals(0, c.getBlock(0));
		Assert.assertEquals(0, c.getBlock(3));
		Assert.assertEquals(1, c.getBlock(4));
		Assert.assertEquals(1, c.getBlock(7));
		Assert.assertEquals(-1, c.getBlock(8));
	}
	
	@Test
	public void testUpdate() {
		Assert.assertEquals(0, c.getLRU());
		Assert.assertEquals(0, c.block[0].getData(0));
		c.update(0, 50);
		Assert.assertEquals(1, c.getLRU());
		Assert.assertEquals(50, c.block[0].getData(0));
	}
}
