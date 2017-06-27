/**
 * 
 * @author Thales G Moreira
 * This class implements a representation of a Word.
 * For the use on this program, the Word is composed by its contained data,
 * but also by an address tag.
 */
public class Word {
	public int data;	// Actual Data
	public int address;	// Position in the Disc, used for reference for PageTable.
}