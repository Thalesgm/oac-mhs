
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
	public void disctomem(int add){
		memp.load(disco.page[add/8]);
	}
	
	public void coreSave(int i, int add, int data){
		int check = core[i].hitcheck(add);
		if(check >= 0){
			core[i].update(add, data);
			l2Cache[i/2].update(add, data);
			memp.update(add, data);
			disco.update(add, data);
		}
		else{
			coreLoad(i, add);
			core[i].update(add, data);
			l2Cache[i/2].update(add, data);
			memp.update(add, data);
			disco.update(add, data);
		}
	}
}