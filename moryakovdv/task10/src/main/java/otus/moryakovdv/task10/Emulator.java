package otus.moryakovdv.task10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/**Класс UI**/
public class Emulator {

	private String path;
	private Cache<String, ByteBuffer>  cache;
	private boolean running = true;
	
	public Emulator(String path, Cache<String, ByteBuffer> cache) {

		this.path = path;
		this.cache = cache;
	}
	
	
	
	public void start() throws Exception {
		Thread.currentThread().setName("EmulatorThread");
		
		
		System.out.println("Using path "+this.path);
		System.out.println("Using buffer "+this.cache.getClass().getSimpleName());
		
		System.out.println("USAGE: <filename> - check in cache for <filename> ");
		System.out.println("	   count - see counter of cache hits");
		System.out.println("	   quit - time to say goodbye");

		Scanner scanner = new Scanner(System.in);
		while(running) {
			
			String cmd = scanner.nextLine();
			if (cmd.equalsIgnoreCase("quit")) {
				System.out.println("BYE!!");
				running = false;
				break;
			} 
			else if (cmd.equalsIgnoreCase("count")) {
				System.out.println("Hits: "+cache.cacheHitCount());
				
			} 
			else {
				if (this.path!=null && !this.path.isEmpty())
					cmd =this.path+"/"+cmd;
				
				System.out.println("Scanning cache for "+cmd);
				
				ByteBuffer fc = cache.get(cmd);
				if (fc==null) {
					
					System.out.println(cmd + " NOT found in cache.");
					try {
						fc = cache.readFile(cmd);
						
						cache.put(cmd, fc);
						
						System.out.println(cmd + " NOW IN CACHE");
						
						
					} catch (IOException e) {
						System.err.printf("Exception on file reading %s\r\n",e);
						System.out.println("Try again");
						
					}
				
					
					
				}
				else {
					
					System.out.println(cmd + " FOUND in cache. See content below");
					cache.printContent(fc);

				}
			}
			
			
		}
		scanner.close();
		
		
		
		
	}
	
	

}
