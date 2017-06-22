
public class Block {
	private int blocksz = 4;	// Default Block size.
	public Word word[];			// Representation of the block as a vector of words.
	public int idblock;			// Identification of the block as in which Page it belongs.
	
	public Block(int idblock){
		word = new Word [blocksz];
		for(int i = 0; i < blocksz; i++){
			word[i] = new Word();
		}
	}
	
	/**
	 * Method to check if the block contains a Word. 
	 * @param add Address in the disc being searched.
	 * @return True if the block contains the Word(Hit). False if it doesn't(Miss).
	 */
	public boolean search(int add){
		int i = 0;
		while(i < blocksz){
			if(word[i].address == add){
				return true;
			}
			i++;
		}
		return false;
	}
	
	public int getData(int add){
		int i = 0;
		while(i < blocksz){
			if(word[i].address == add){
				return word[i].data;
			}
			i++;
		}
		return -1;
	}
	/**
	 * Method for updating the data in a Word.
	 * @param p Address reference to Page Table.
	 * @param d New value for Word's data.
	 */
	public void updateWord(int p, int d){
		int i =  0;
		while(word[i].address != p && i < blocksz){
			i++;
		}
		word[i].data = d;
	}
	/**
	 * Printing Method for a block, prints the Block's respective page followed by its current data.
	 */
	public void print(){
		System.out.print("Printing block from page " + idblock +": ");
		for(int i = 0; i < blocksz; i++){
			System.out.print(word[i].address + " ");
		}
	}
	
	public Word searchWord(int add){
		int i = 0;
		while(i < blocksz){
			if(word[i].address == add){
				return word[i];
			}
			i++;
		}
		return null;
	}
}