import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class L2CacheTest {
	//Creating Blocks to use in methods
	Block bA = new Block(0);
	Block bB = new Block(1);
	Block bC = new Block(2);
	Block bD = new Block(3);
	Block bE = new Block(4);
	Block bF = new Block(5);
	L2Cache l = new L2Cache();
	@Before
	public void setup() {
		for(int i = 0; i < 4; i++){	// Filling the disc.
			bA.word[i].data = i;
			bA.word[i].address = i;
			bB.word[i].data = i+4;
			bB.word[i].address = i+4;
			bC.word[i].data = i+8;
			bC.word[i].address = i+8;
			bD.word[i].data = i+12;
			bD.word[i].address = i+12;
			bE.word[i].data = i+16;
			bE.word[i].address = i+16;
			bF.word[i].data = i+20;
			bF.word[i].address = i+20;
		}
		l.load(bA);
		l.load(bB);
		l.load(bC);
		l.load(bD);
		l.load(bE);
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
		l.load(bE);
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
