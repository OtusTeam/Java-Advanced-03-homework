package otus.moryakovdv.task10;

import java.nio.file.Path;

/***Запускающий класс*/
public class Application {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Started");
		
		String path = "./Readme.md";
		
		int bufferSize = 256;
		int offset = 0;
		
		OffHeapContent content = new ByteBufferContent();
		
		switch(args.length) {
			
		case 0: default:
			System.out.println("Using default filePath and ByteBuffer");
			
			
			
			break;
		case 4:
			System.out.println("Using ");
			path = args[0];
			System.out.println("Path "+path);
			if (args[1].equalsIgnoreCase("mapped")) {
				System.out.println("MappedByteBuffer "+path);
			}
			
			bufferSize = Integer.parseInt(args[2]);
			offset =  Integer.parseInt(args[3]);
			
			
			System.out.println("with size "+bufferSize);
			System.out.println("with offset "+offset);
			
			content = new MappedByteBufferContent();
			

			break;
		
		}
		
		
		Path p = content.getFilePath(path);
		content.readFile(p, bufferSize, offset);
		
		System.out.println("Stopped");

		
	}

}
