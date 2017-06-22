
public class Page {
	private int pagesz = 8;	// Number of Words contained in each page
	public Word[] word;		// Representation of the page
	public int idpag = 0;	// Identification of the page in the table.

	public Page(int idpag){
		word = new Word[pagesz];
		this.idpag = idpag;
		for(int i = 0; i < pagesz; i++){
			word[i] = new Word();
	
		}
	}
	
	/**
	 * Method for searching a Word in the page.
	 * @param add Position for reference in the Page Table
	 * @return The Word searched or null if not found. 
	 */
	public Word searchWord(int add){
		int i = 0;
		while(i < pagesz){
			if(word[i].address == add){
				return word[i];
			}
			i++;
		}
		return null;
	}
	
	public void print(){
		System.out.print("Page " + idpag + ": ");
		for(int i = 0; i < pagesz; i++){
			System.out.print(word[i].data + " ");
		}
		System.out.print("|");
	}
	
	public Block getBlock(int add){
		Block b = new Block(idpag);
		b.idblock = idpag;
		if(add - word[0].address < 4){
			for(int i = 0; i < 4; i++){
				b.word[i].address = word[i].address;
				b.word[i].data = word[i].data;
			}
			return b;
		}
		else{
			int k = 0;
			for(int i = 4; i < 8; i++){
				b.word[k].address = word[i].address;
				b.word[k].data = word[i].data;
				k++;
			}
			return b;
		}
	}
	
	public int getPosition(int add){
		int i = 0;
		while(word[i].address != add){
			i++;
		}
		return i;
	}
	public boolean search(int add){
		int i = 0;
		while(i < pagesz){
			if(word[i].address == add){
				return true;
			}
			i++;
		}
		return false;
	}
}