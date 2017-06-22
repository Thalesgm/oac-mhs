
public class Teste {

	public static void main(String[] args) {
		Manager m = new Manager(4);
		for(int i = 1; i <= 240; i++){
			System.out.println(i);
			m.disco.load(i);
		}
		m.disco.print();
		m.coreSave(0, 50, 24);
		System.out.println(m.coreLoad(2, 50));
		
		/*Page p = new Page(30);
		p.word[1].address = 5;
		//p.print();
		MainMem m = new MainMem();
		for(int i = 0; i <= 10; i++){
			System.out.println(i);
			m.load(d.page[i]);
		}
		m.print();
		Block b = new Block(0);
		b = d.page[0].getBlock(2);
		b.print();*/
	}
}