import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DriveOpt {

	public static void main(String[] args) {
		String file1 = args[0];
		String file2 = args[1];
		Manager m = new Manager(4);
		try {
			Scanner s = new Scanner(new File(file1));
			m = new Manager(s.nextInt());
			while(s.hasNextInt()){
				m.disco.load(s.nextInt());
			}
			s.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		m.disco.print();
		try {
			Scanner s = new Scanner(new File(file2));
			while(s.hasNext()){
				String ctrl = s.next();
				if(ctrl.matches("LOAD")){
					int core = s.nextInt();
					int add = s.nextInt();
					System.out.println("Calling load on Core: " + core + "for address: " + add);
					m.coreLoad(core, add);
				}
				else if(ctrl.matches("SAVE")){
					int core = s.nextInt();
					int add = s.nextInt();
					int data = s.nextInt();
					System.out.println("Calling Save on Core: " + core + "for address: " + add);
					m.coreSave(core, add, data);
				}
				else if(ctrl.matches("PRINTD")){
					System.out.println("Printing current Disc stored information");
					m.disco.print();
				}
				else if(ctrl.matches("PRINTM")){
					System.out.println("Printing current Memory information");
					m.memp.print();
				}
				else if(ctrl.matches("PRINTC2")){
					m.printL2();
				}
				else if(ctrl.matches("PRINTC1")){
					m.printL1();
				}
			}
			s.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
}