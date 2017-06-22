
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
	public int getLRU(){
		int last = 0;
		for(int i = 1; i < size; i++){
			if(usage[i] < usage[i-1]){
				last = i;
			}
		}
		return last;
	}
	public int hitcheck(int add){
		for(int i = 0; i < currentsz; i++){
			if(block[i].search(add)){
				return i;
			}
		}
		return -1;
	}
	public void print(){
		for(int i = 0; i < size; i++){
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
	
	public void update(int add, int d){
		Word w = new Word();
		for(int i = 0; i < currentsz; i++){
			if(block[i].search(add)){
				w = block[i].searchWord(add);
				w.data = d;
				usage[i]++;
			}
		}
	}
}