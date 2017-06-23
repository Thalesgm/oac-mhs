
public class MainMem {
	public Page[] page;			// Memory's representation.
	public int size = 10;		// Memory's size, in Pages.
	public int[] usage;			// Tracker of Least Recently Used(LRU).
	public int currentsz;		// Current occupied area of the memory
	public int countLRU = 0;	// LRU tracker

	public MainMem() {
		this.page = new Page[size];
		this.usage = new int[size];
		this.currentsz = 0;
		for(int i = 0; i < size; i++){
			page[i] = new Page(0);
		}
	}
	
	/**
	 * Method for loading a page from the disc. 
	 * If memory is not filled. Loads the page to the fist open position.
	 * If memory is full, loads the page to the Least recently used(LRU) position.
	 * @param p
	 */
	public void load(Page p){
		if(currentsz < size){
			page[currentsz].idpag = p.idpag;
			for(int i =0; i < 8; i++){
				page[currentsz].word[i].data = p.word[i].data;
				page[currentsz].word[i].address = p.word[i].address;
			}
			usage[currentsz] = countLRU;
			countLRU++;
			currentsz++;
		}
		else{
			int lru = getLRU();
			page[lru].idpag = p.idpag;
			for(int i =0; i < 8; i++){
				page[lru].word[i].data = p.word[i].data;
				page[lru].word[i].address = p.word[i].address;
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
		for(int i = 1; i < size; i++){
			if(usage[i] < usage[i-1]){
				last = i;
			}
		}
		return last;
	}
	
	/**
	 * Printing method for the Main Memory. Prints each page, in order, but referencing the Disc page where it belongs.
	 */
	public void print(){
		for(int i = 0; i < size; i++){
			page[i].print();
			System.out.println("");
		}
	}
	
	/**
	 * Method to find a Page in the Memory, using a specific world that belongs on that page.
	 * @param add Reference to the world being searched in the memory.
	 * @return A value between 0 and currentsz, that represents a position in the memory
	 *  if the address is found in the memory(Hit),
	 *  or -1 if the address isn't found(Miss)
	 */
	public int getPage(int add){
		for(int i = 0; i < currentsz; i++){
			if(page[i].idpag == add/8){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Method to update a Word's data in the main memory.
	 * @param add Address of the word to be updated.
	 * @param d Data to be used for update.
	 */
	public void update(int add, int d){
		Word w = new Word();
		for(int i = 0; i < currentsz; i++){
			if(page[i].search(add)){
				w = page[i].searchWord(add);
				w.data = d;
				usage[i]++;
			}
		}
	}
}