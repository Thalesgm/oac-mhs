import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Thales G Moreira
 * This is the main drive for the MHS.
 * To execute this drive you will need a commands file, as described in the README.txt file.
 */
public class Drive {

	public static void main(String[] args) {
		String file1 = args[0];
		Manager m = new Manager(4);

		try {
			Scanner s = new Scanner(new File(file1));
			m = new Manager(s.nextInt());	// Adjustment for Core Number.
			for(int i = 0; i < 240; i++){	// Filling the disc.
				m.disco.load(i);
			}
			while(s.hasNext()){
				String ctrl = s.next();
				if(ctrl.matches("LOAD")){	// Call for Load.
					int core = s.nextInt();
					int add = s.nextInt();
					System.out.println("Calling load on Core: " + core + " for address: " + add);
					m.coreLoad(core, add);
				}
				else if(ctrl.matches("SAVE")){	// Call for Save.
					int core = s.nextInt();
					int add = s.nextInt();
					int data = s.nextInt();
					System.out.println("Calling Save on Core: " + core + " for address: " + add + " Saving: " + data);
					m.coreSave(core, add, data);
				}
				else if(ctrl.matches("PRINTD")){	// Call for Disc print.
					System.out.println("Printing current Disc stored information");
					m.disco.print();
				}
				else if(ctrl.matches("PRINTM")){	// Call for Memory print.
					System.out.println("Printing current Main Memory information");
					m.memp.print();
				}
				else if(ctrl.matches("PRINTC2")){	// Call for L2 Print.
					m.printL2();
				}
				else if(ctrl.matches("PRINTC1")){	//Call for L1 Print.
					m.printL1();
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}