/**
 * 
 * @author Thales G Moreira
 * The manager class implements the simulator, integrating all other classes.
 * This class is able to call for load search and save, while simulating the hierarchy chain
 */
public class Manager {
	public int corecount;
	public Disc disco;
	public MainMem memp;
	public L2Cache[] l2Cache;
	public Core[] core;
	
	public Manager(int corecount) {
		this.corecount = corecount;
		disco = new Disc();
		memp = new MainMem();
		core = new Core [corecount];
		l2Cache = new L2Cache[corecount/2];
		for(int i = 0; i < corecount; i++){
			core[i] = new Core();
		}
		for(int i = 0; i < corecount/2; i++){
			l2Cache[i] = new L2Cache(); 
		}
	}
	
	/**
	 * Loads the data from a address in the Disc, using the Pages as references.
	 * The loading process follows the hierarchy established for this project, looking in each level.
	 * From L1 to L2 to PriMem to Disc if it keeps missing and stopping on first hit, and getting back to loading to the core.
	 * @param i The numeric position of the core making the request.
	 * @param add Address where the Word is
	 * @return The Data from the Word loaded.
	 */
	public int coreLoad(int i, int add){
		int check = core[i].hitcheck(add);
		if(check >= 0){
			return core[i].block[check].getData(add);
		}
		else{
			cachetocore(i, add);
			check = core[i].hitcheck(add);
			return core[i].block[check].getData(add);
			
		}
		
	}
	/**
	 * Method to load a block from the cache to the core.
	 * @param i Core that made the request
	 * @param add address for verification
	 */
	public void cachetocore(int i, int add){
		int check = l2Cache[i/2].hitcheck(add);
		if(check >= 0){
			core[i].load(l2Cache[i/2].block[check]);
		}
		else{
			memtocache(i, add);
			int check2 = l2Cache[i/2].hitcheck(add);
			core[i].load(l2Cache[i/2].block[check2]);
		}
	}
	
	/**
	 * Method to load from Primary memory to L2 Cache.
	 * @param i Core making the request
	 * @param add Address for verification
	 */
	public void memtocache(int i,int add){
		int check = memp.getPage(add);
		if(check >=0){
			Block b = memp.page[check].getBlock(add);
			l2Cache[i/2].load(b);
		}
		else{
			disctomem(add);
			check = memp.getPage(add);
			Block b = memp.page[check].getBlock(add);
			l2Cache[i/2].load(b);
		}
	}
	/**
	 * Method to load from Disc to Primary Memory.
	 * @param add Address for loading.
	 */
	public void disctomem(int add){
		memp.load(disco.page[add/8]);
	}
	
	/**
	 * Method to save data in a Word.
	 * The process consists in loading the request data position if not found by the Core in its L1 Cache, or directly
	 * updating if it's already there. Also the Word is changed in all levels of the Memory where it's found. 
	 * @param i Core making the request.
	 * @param add Address from the page Tables.
	 * @param data Information for updating.
	 */
	public void coreSave(int i, int add, int data){
		int check = core[i].hitcheck(add);
		if(check >= 0){
			for(int j = 0; j < corecount; j++){
				core[j].update(add, data);
				l2Cache[j/2].update(add, data);
			}
			memp.update(add, data);
			disco.update(add, data);
		}
		else{
			coreLoad(i, add);
			for(int j = 0; j < corecount; j++){
				core[j].update(add, data);
				l2Cache[j/2].update(add, data);
			}
			memp.update(add, data);
			disco.update(add, data);
		}
	}
	public void printL2(){
		for(int i = 0; i < corecount/2; i++){
			System.out.println("Printing Core information for L2 Cache: " + i);
			l2Cache[i].print();
		}
	}
	public void printL1(){
		for(int i = 0; i < corecount; i++){
			System.out.println("Printing Core information for L1 Cache for core: " + i);
			core[i].print();
		}
	}
}