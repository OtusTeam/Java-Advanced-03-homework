package otus.moryakovdv.task1;

/***Запускающий класс*/
public class Application {
	
	public static void main(String[] args) {
		
		System.out.println("Started");
		
		String path = "testfiles";
		Cache<String,FileContent> cache = new WeakReferenceValueCache();
		
		switch(args.length) {
			
		case 0: default:
			System.out.println("Using default filePath and WeakReferenceCache");
			
			break;
		case 1:
			System.out.println("One parameter passed. Treat it as filePath");
			path = args[0];
			break;
		case 2:
			System.out.println("Two parameter passed. Treat them as filePath and cache type");
			path = args[0];
			if (args[1].equalsIgnoreCase("soft"))
				cache = new SoftReferenceValueCache();
			
			break;
		
		}
		Emulator emu = new Emulator(path, cache);
		
		emu.start();
		
		System.out.println("Stopped");

		
	}

}
