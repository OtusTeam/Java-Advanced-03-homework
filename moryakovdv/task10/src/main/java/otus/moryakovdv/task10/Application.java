package otus.moryakovdv.task10;

/***Запускающий класс*/
public class Application {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Started");
		
		String path = "./testfiles";
		Cache cache = new ByteBufferFileContent();
		
		switch(args.length) {
			
		case 0: default:
			System.out.println("Using default filePath and ByteBuffer");
			
			break;
		case 1:
			System.out.println("One parameter passed. Treat it as filePath");
			path = args[0];
			break;
		case 2:
			System.out.println("Two parameter passed. Treat them as filePath and Buffer type");
			path = args[0];
			if (args[1].equalsIgnoreCase("mapped"))
				cache = new MappedByteBufferFileContent();
			
			break;
		
		}
		Emulator emu = new Emulator(path, cache);
		
		emu.start();
		
		System.out.println("Stopped");

		
	}

}
