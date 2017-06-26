
public class Core {
	public int size = 2;	// Size of the L1 Cache
	public Block[] block;	// Representation of the L1
	public int currentsz;	// Current size of the cache used.
	public int[] usage;		// Usage tracker
	public int countLRU;	// LRU Tracker
	
	public Core(){
		block = new Block[size];
		this.usage = new int[size];
		countLRU = 0;
		this.currentsz = 0;
		for(int i = 0; i < size; i++){
			block[i] = new Block(0);
		}
	}
	
	/**
	 * Method to load a block, usually from the L2Cache. 
	 * @param b Block To be loaded in the Cores L1 cache.
	 */
	public void load(Block b){
		if(currentsz < size){
			block[currentsz].idblock = b.idblock;
			for(int i = 0; i < 4; i++){
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
	 * Method for getting Least Recently Used position of the cache.
	 * @return the value of the LRU position.
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
	 * Method to check if the search for an address results in a hit or a miss.
	 * @param add Address being looked.
	 * @return The position if the address is found(hit) or -1 if it doesn't (miss).
	 */
	public int hitcheck(int add){
		for(int i = 0; i < currentsz; i++){
			if(block[i].search(add)){
				return i;
			}
		}
		return -1;
	}
	public void print(){
		for(int i = 0; i < currentsz; i++){
			block[i].print();
			System.out.println("");
		}
	}
	
	/**
	 * Method to find a Page in the Cache, using a specific world that belongs on that page.
	 * @param add Reference to the world being searched in the memory.
	 * @return A value between 0 and currentsz, that represents a position in the Cache
	 *  if the address is found in the Cache(Hit),
	 *  or -1 if the address isn't found(Miss)
	 */
	public int getBlock(int add){
		for(int i = 0; i < currentsz; i++){
			if(block[i].idblock == add/8){
				if(block[i].search(add)){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Method to Update the data in an Page Table's address.
	 * @param add address to be updated
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