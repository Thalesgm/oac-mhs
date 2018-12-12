import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class MainMemTest {
	Page pA = new Page(0);
	Page pB = new Page(1);
	Page pC = new Page(2);
	Page pD = new Page(3);
	Page pE = new Page(4);
	Page pF = new Page(5);
	Page pG = new Page(6);
	Page pH = new Page(7);
	Page pI = new Page(8);
	Page pJ = new Page(9);
	Page pK = new Page(10);
	
	MainMem m = new MainMem();
	@Before
	public void setup() {
		for(int i = 0; i < 8; i++){	// Filling the disc.
			pA.word[i].data = i;
			pA.word[i].address = i;
			pB.word[i].data = i+8;
			pB.word[i].address = i+8;
			pC.word[i].data = i+16;
			pC.word[i].address = i+16;
			pD.word[i].data = i+24;
			pD.word[i].address = i+24;
			pE.word[i].data = i+32;
			pE.word[i].address = i+32;
			pF.word[i].data = i+40;
			pF.word[i].address = i+40;
			pG.word[i].data = i+48;
			pG.word[i].address = i+48;
			pH.word[i].data = i+56;
			pH.word[i].address = i+56;
			pI.word[i].data = i+64;
			pI.word[i].address = i+64;
			pJ.word[i].data = i+72;
			pJ.word[i].address = i+72;
			pK.word[i].data = i+80;
			pK.word[i].address = i+80;
		}
		
		//Filling the memory
		m.load(pA);
		m.load(pB);
		m.load(pC);
		m.load(pD);
		m.load(pE);
		m.load(pF);
		m.load(pG);
		m.load(pH);
		m.load(pI);
		m.load(pJ);
	}
	
	@Test
	public void testGetPage() {
		Assert.assertEquals(0, m.getPage(0));		//Min position on memory
		Assert.assertEquals(9, m.getPage(79));		//Max position on memory
		Assert.assertEquals(-1, m.getPage(300));	//Outside of the memory
	}
	
	@Test
	public void testGetLRU() {
		Assert.assertEquals(0, m.getLRU());			//Before updating
		m.load(pK);									//Updating
		Assert.assertEquals(1, m.getLRU());			//Asserting change
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
