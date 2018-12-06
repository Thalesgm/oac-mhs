import org.junit.Test;

import org.junit.Assert;

public class BlockTest {

	@Test
	public void testSearch() {
		Block b = new Block(0); // Initializing a Block object for testing.
		for(int i = 0; i < 4; i++) {
			b.word[i].address = i;
			b.word[i].data = i;
		}
		
		//Testing Search Method
		Assert.assertEquals(false, b.search(-1));	//Inferior Limit -1
		Assert.assertEquals(true, b.search(0)); 	//Inferior Limit
		Assert.assertEquals(true, b.search(3));		//Superior Limit
		Assert.assertEquals(false, b.search(4));	//Superior Limit+1
	}
	
	@Test
	public void testGetData() {
		Block b = new Block(0); // Initializing a Block object for testing.
		for(int i = 0; i < 4; i++) {
			b.word[i].address = i;
			b.word[i].data = i;
		}
		//Testing getData Method
		Assert.assertEquals(-1, b.getData(-1));		//Inferior Limit -1 
		Assert.assertEquals(0, b.getData(0));		//Inferior Limit
		Assert.assertEquals(3, b.getData(3));		//Superior Limit
		Assert.assertEquals(-1, b.getData(4));  	//Inferior Limit +1
	}
	
	@Test
	public void testSearchWord() {
		Block b = new Block(0); // Initializing a Block object for testing.
		for(int i = 0; i < 4; i++) {
			b.word[i].address = i;
			b.word[i].data = i;
		}
		//Testing searchWord
		Assert.assertEquals(null, b.searchWord(-1));  		//Inferior Limit -1
		Assert.assertEquals(b.word[0], b.searchWord(0));  	//Inferior Limit
		Assert.assertEquals(b.word[3], b.searchWord(3));  	//Superior Limit
		Assert.assertEquals(null, b.searchWord(4));  		//Superior Limit +1
	}
	@Test
	public void testUpdateWord() {
		Block b = new Block(0); // Initializing a Block object for testing.
		for(int i = 0; i < 4; i++) {
			b.word[i].address = i;
			b.word[i].data = i;
		}

		//Testing updateWord
		b.updateWord(0, 5); 					//Updated position 0 data to 5
		Assert.assertEquals(5, b.getData(0));	//Verifying change on position 0
		b.updateWord(3, 6); 					//Updated position 3 data to 6
		Assert.assertEquals(6, b.getData(3));	//Verifying change on position 3
		//b.updateWord(4, 0);						//Position not part of the Block Should throw IndexOutOfBoundsException
	}

}
