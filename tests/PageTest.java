import org.junit.Assert;
import org.junit.Test;

public class PageTest {

	@Test
	public void testSearch() {
		Page p = new Page(0);
		for(int i = 0; i < 8; i++) {
			p.word[i].address = i;
			p.word[i].data = i;
		}
		
		//Testing search Method
		Assert.assertEquals(false, p.search(-1));	//Inferior limit -1
		Assert.assertEquals(true, p.search(0));		//Inferior limit
		Assert.assertEquals(true, p.search(7));		//Superior limit
		Assert.assertEquals(false, p.search(8));	//Superior limit +1
		
		//Testing getPosition Method
		Assert.assertEquals(0, p.getPosition(0));	//Inferior limit
		Assert.assertEquals(7, p.getPosition(7));	//Superior limit
	}
	@Test
	public void testSearchWord() {
		Page p = new Page(0);
		for(int i = 0; i < 8; i++) {
			p.word[i].address = i;
			p.word[i].data = i;
		}
		Assert.assertEquals(null, p.searchWord(-1));		//Inferior limit -1
		Assert.assertEquals(p.word[0], p.searchWord(0));	//Inferior limit
		Assert.assertEquals(p.word[7], p.searchWord(7));	//Superior limit
		Assert.assertEquals(null, p.searchWord(8));			//Superior limit +1
	}
	
	@Test	
	public void testGetBlock() {
		Page p = new Page(0);
		for(int i = 0; i < 8; i++) {
			p.word[i].address = i;
			p.word[i].data = i;
		}
		//Testing getBlock Method
		
		Block br = new Block(0); // Block for Method return storage

		//Running Tests First block
		br = p.getBlock(0); // Getting return for inferior limit on First block
		Assert.assertEquals(p.word[0].data, br.word[0].data);
		Assert.assertEquals(p.word[0].address, br.word[0].address);
		Assert.assertEquals(p.word[1].data, br.word[1].data);
		Assert.assertEquals(p.word[1].address, br.word[1].address);
		Assert.assertEquals(p.word[2].data, br.word[2].data);
		Assert.assertEquals(p.word[2].address, br.word[2].address);
		Assert.assertEquals(p.word[3].data, br.word[3].data);
		Assert.assertEquals(p.word[3].address, br.word[3].address);
		
		br = p.getBlock(3); // Getting return for superior limit on First block
		Assert.assertEquals(p.word[0].data, br.word[0].data);
		Assert.assertEquals(p.word[0].address, br.word[0].address);
		Assert.assertEquals(p.word[1].data, br.word[1].data);
		Assert.assertEquals(p.word[1].address, br.word[1].address);
		Assert.assertEquals(p.word[2].data, br.word[2].data);
		Assert.assertEquals(p.word[2].address, br.word[2].address);
		Assert.assertEquals(p.word[3].data, br.word[3].data);
		Assert.assertEquals(p.word[3].address, br.word[3].address);
		
		//Running Tests Second block
		br = p.getBlock(4); // Getting return for inferior limit on second block
		Assert.assertEquals(p.word[4].data, br.word[0].data);
		Assert.assertEquals(p.word[4].address, br.word[0].address);
		Assert.assertEquals(p.word[5].data, br.word[1].data);
		Assert.assertEquals(p.word[5].address, br.word[1].address);
		Assert.assertEquals(p.word[6].data, br.word[2].data);
		Assert.assertEquals(p.word[6].address, br.word[2].address);
		Assert.assertEquals(p.word[7].data, br.word[3].data);
		Assert.assertEquals(p.word[7].address, br.word[3].address);
				
		br = p.getBlock(7); // Getting return for superior limit on second block
		Assert.assertEquals(p.word[4].data, br.word[0].data);
		Assert.assertEquals(p.word[4].address, br.word[0].address);
		Assert.assertEquals(p.word[5].data, br.word[1].data);
		Assert.assertEquals(p.word[5].address, br.word[1].address);
		Assert.assertEquals(p.word[6].data, br.word[2].data);
		Assert.assertEquals(p.word[6].address, br.word[2].address);
		Assert.assertEquals(p.word[7].data, br.word[3].data);
		Assert.assertEquals(p.word[7].address, br.word[3].address);
	}

}
