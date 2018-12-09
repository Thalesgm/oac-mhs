
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerTest {
	Manager m = new Manager(4); //Creating a manager object with 4 cores
	@Before
	public void setup() {
		for(int i = 0; i < 240; i++){	// Filling the disc.
			m.disco.load(i);
		}
	}
	
	
	@Test
	public void testDisctoMem() {
		
		//Verification for the first page
		Assert.assertEquals(-1, m.memp.getPage(0));
		m.disctomem(0);
		Assert.assertEquals(0, m.memp.getPage(0));
		
		//Verification for the last page
		Assert.assertEquals(-1, m.memp.getPage(239));
		m.disctomem(239);
		Assert.assertEquals(1, m.memp.getPage(239));
	}
	
	@Test
	public void testMemToCache() {
		
		//Loading from the Disc to MainMem to L2Cache
		Assert.assertEquals(-1, m.l2Cache[0].hitcheck(0));
		Assert.assertEquals(-1, m.memp.getPage(0));
		m.memtocache(0, 0);
		Assert.assertEquals(0, m.l2Cache[0].hitcheck(0));
		Assert.assertEquals(0, m.memp.getPage(0));
		
		//Loading from the MainMem to L2Cache
		Assert.assertEquals(-1, m.l2Cache[1].hitcheck(0));
		Assert.assertEquals(0, m.memp.getPage(0));
		m.memtocache(3, 0);
		Assert.assertEquals(0 , m.l2Cache[1].hitcheck(0));
	}
	
	@Test
	public void testCachetoCore() {
		
		//Loading from MainMem to L2Cache to Core
		Assert.assertEquals(-1, m.l2Cache[0].hitcheck(0));
		Assert.assertEquals(-1, m.core[0].hitcheck(0));
		m.cachetocore(0, 0);
		Assert.assertEquals(0, m.l2Cache[0].hitcheck(0));
		Assert.assertEquals(0, m.core[0].hitcheck(0));
		
		//Loading from L2Cache to Core
		Assert.assertEquals(0, m.l2Cache[0].hitcheck(0));
		Assert.assertEquals(-1, m.core[1].hitcheck(0));
		m.cachetocore(1, 0);
		Assert.assertEquals(0, m.core[1].hitcheck(0));
	}
	
	@Test
	public void testCoreLoad() {
		
		//Loading from out for the core
		Assert.assertEquals(-1, m.core[0].hitcheck(0));
		m.coreLoad(0, 0);
		Assert.assertEquals(0, m.core[0].hitcheck(0));
		
		//Loading block already stored in the core memory
		Assert.assertEquals(0, m.core[0].hitcheck(3));
		m.coreLoad(0, 3);
		Assert.assertEquals(0, m.core[0].hitcheck(3));
	}
	
	@Test
	public void testCoreSave() {
		
		//Updating from the disc
		Assert.assertEquals(0, m.disco.page[0].word[0].data);
		m.coreSave(0, 0, 1000);
		Assert.assertEquals(1000, m.disco.page[0].word[0].data);
		Assert.assertEquals(1000, m.memp.page[0].word[0].data);
		Assert.assertEquals(1000, m.l2Cache[0].block[0].word[0].data);
		Assert.assertEquals(1000, m.core[0].block[0].word[0].data);
		
		//Updating data loaded on all cores
		m.coreLoad(1, 0);
		m.coreLoad(2, 0);
		m.coreLoad(3, 0);
		Assert.assertEquals(1000, m.disco.page[0].word[0].data);
		Assert.assertEquals(1000, m.memp.page[0].word[0].data);
		Assert.assertEquals(1000, m.l2Cache[0].block[0].word[0].data);
		Assert.assertEquals(1000, m.l2Cache[1].block[0].word[0].data);
		Assert.assertEquals(1000, m.core[0].block[0].word[0].data);
		Assert.assertEquals(1000, m.core[1].block[0].word[0].data);
		Assert.assertEquals(1000, m.core[2].block[0].word[0].data);
		Assert.assertEquals(1000, m.core[3].block[0].word[0].data);
		m.coreSave(3, 0, 1500);
		Assert.assertEquals(1500, m.disco.page[0].word[0].data);
		Assert.assertEquals(1500, m.memp.page[0].word[0].data);
		Assert.assertEquals(1500, m.l2Cache[0].block[0].word[0].data);
		Assert.assertEquals(1500, m.l2Cache[1].block[0].word[0].data);
		Assert.assertEquals(1500, m.core[0].block[0].word[0].data);
		Assert.assertEquals(1500, m.core[1].block[0].word[0].data);
		Assert.assertEquals(1500, m.core[2].block[0].word[0].data);
		Assert.assertEquals(1500, m.core[3].block[0].word[0].data);
	}

}
