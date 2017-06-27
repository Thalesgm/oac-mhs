/**
 * 
 * @author Thales G Moreira
 * This class implements a simulation of the L2 Cache.
 * The cache is composed by 5 blocks, being able to load them, search or update..
 */
public class L2Cache {
	public int size = 5;		// Size of the cache in blocks.
	public int currentsz;		// Current occupied size of the cache
	public int countLRU = 0;	// Tracker for Least Recent Used(LRU)
	public Block block[];		// Cache representation in blocks.
	public int[] usage;			// Usage tracker
	
	public L2Cache(){
		currentsz = 0;
		block = new Block[size];
		usage = new int[size];
		for(int i = 0; i < size; i++){
			block[i] = new Block(0);
		}
	}
	
	/**
	 * Loading method for a incoming block.
	 * If Cache is not filled, loads the block on the first block available.
	 * If Cache is full, loads in the Least Recently Used(LRU) position.
	 * @param b Block that needs to be loaded in the Cache.
	 */
	public void load(Block b){
		if(currentsz < size){
			block[currentsz].idblock = b.idblock;
			for(int i =0; i < 4; i++){
				block[currentsz].word[i].data = b.word[i].data;
				block[currentsz].word[i].address = b.word[i].address;
			}
			usage[currentsz] = countLRU;
			countLRU++;
			currentsz++;
		}
		else{
			int lru = getLRU();
			block[lru].idblock = b.idblock;
			for(int i =0; i < 4; i++){
				block[lru].word[i].data = b.word[i].data;
				block[lru].word[i].address = b.word[i].address;
			}
			usage[lru] = countLRU;
			countLRU++;
		}
	}
	
	/**
	 * Method to determine the Least Recently Used(LRU) Page position.
	 * @return The value of the LRU position 
	 */
	public int getLRU(){
		int last = 0;
		int current = usage[0];
		for(int i = 1; i < size; i++){
			if(current > usage[i]){
				current = usage[i];
				last = i;
			}
		}
		return last;
	}
	
	/**
	 * Printing method of the Cache.
	 * Prints each block referencing the page of origin.
	 */
	public void print(){
		for(int i = 0; i < currentsz; i++){
			block[i].print();
			System.out.println("");
		}
	}
	
	/**
	 * Method to find a Block in the Cache, using a specific world that belongs on that page.
	 * @param add Reference to the world being searched in the memory.
	 * @return A value between 0 and currentsz, that represents a position in the Cache
	 *  if the address is found in the Cache(Hit),
	 *  or -1 if the address isn't found(Miss)
	 */
	public int hitcheck(int add){
		for(int i = 0; i < currentsz; i++){
			if(block[i].search(add)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Method to update the data in a Word if it's present on the cache.
	 * @param add Address of the Word in the Page Table.
	 * @param d Data for updating.
	 */
	public void update(int add, int d){
		Word w = new Word();
		for(int i = 0; i < currentsz; i++){
			if(block[i].search(add)){
				w = block[i].searchWord(add);
				w.data = d;
				usage[i] = countLRU;
				countLRU++;
			}
		}
	}
}