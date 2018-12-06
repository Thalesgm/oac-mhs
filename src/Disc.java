/**
 * 
 * @author Thales G Moreira
 * This class simulates the Disc as part of the memory hierarchy.
 * The Disc is composed by 30 pages, while being able to load a word at a time.
 * The Disc is organized and able to find a Word's page just by its individual address,
 * using the page's table.
 */
public class Disc {
	public int capacity = 240;	// Default number of words that can be stored.
	public int pagetotal;		// Total number of pages;
	public int currentsz;		// Current number of Words stored.
	public Page[] page;			// Representation of the disc; Each Page is composed of 8 Words.
	
	public Disc() {
		this.currentsz = 0;
		this.pagetotal = capacity/8;
		page = new Page[pagetotal];
		for(int i = 0; i < pagetotal; i++){
			page[i] = new Page(i);
		}
		labelPages();
	}
	
	/**
	 * Method for setting the label for each Word on the disc pages.
	 */
	public void labelPages(){
		int j = 0;
		int k = 0;
		int t = 1;
		for(int i = 0; i < capacity; i++){
			page[j].word[k].address = i;
			k++;
			if(i == (t*8) -1){
				j++;
				k = 0;
				t++;
			}
		}
		
	}
	
	/**
	 * Printing method for the disc.
	 * Prints the data on the disc, separating each page of the Disc in order.
	 */
	public void print(){
		for(int i = 0; i < pagetotal; i++){
			page[i].print();
			System.out.println("");
		}
	}
	
	/**
	 * Method for updating the data of a Word
	 * @param p - Position on the page that needs to be updated.
	 * @param d - New data value for the Word.
	 */
	public void update(int add, int d){
		int dpos = add/8;
		Word w = new Word();
		w = page[dpos].searchWord(add);
		w.data = d;
	}
	
	/**
	 * Method to load Data into the disc.
	 * @param d - Value to be added to the disc.
	 */
	public void load(int d){
		int pos = page[currentsz/8].getPosition(currentsz);
		page[currentsz/8].word[pos].data = d;
		currentsz++;
	}
}