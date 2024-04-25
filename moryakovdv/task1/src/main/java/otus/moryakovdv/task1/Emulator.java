package otus.moryakovdv.task1;

import java.io.IOException;
import java.util.Scanner;
/**Класс UI**/
public class Emulator {

	private String path;
	private Cache<String, FileContent>  cache;
	private boolean running = true;
	public Emulator(String path, Cache<String, FileContent> cache) {

		this.path = path;
		this.cache = cache;
	}
	
	public void start() {
		Thread.currentThread().setName("EmulatorThread");
		
		cache.startReaper();
		
		System.out.println("Using path "+this.path);
		System.out.println("Using cache "+this.cache.getClass().getSimpleName());
		
		System.out.println("USAGE: <filename> - check in cache for <filename> ");
		System.out.println("	   count - see counter of cache hits");
		System.out.println("	   gc - perform GC and see what happens");
		System.out.println("	   quit - time to say goodbye");

		Scanner scanner = new Scanner(System.in);
		while(running) {
			
			String cmd = scanner.nextLine();
			if (cmd.equalsIgnoreCase("GC")) {
				System.out.println("GC Called!!");
				
				System.out.println("CACHE CONTENT BEFORE GC ");
				
				cache.traverse();
				
				System.gc();
				
				System.out.println("CACHE CONTENT AFTER GC ");
				
				cache.traverse();
				
				
			} else if (cmd.equalsIgnoreCase("quit")) {
				System.out.println("BYE!!");
				cache.stopReaper();
				running = false;
				break;
			} 
			else if (cmd.equalsIgnoreCase("count")) {
				System.out.println("Hits: "+cache.cacheHitCount());
				
			} 
			else {
				
				System.out.println("Scanning cache for "+cmd);
				
				FileContent fc = cache.get(cmd);
				if (fc==null || fc.getContent()==null) {
					
					System.out.println(cmd + " NOT found in cache.");
					try {
						fc = FileContent.valueOf(path,cmd);
						cache.put(cmd, fc);
						System.out.println(cmd + " NOW IN CACHE");
						
						
					} catch (IOException e) {
						System.err.printf("Exception on file reading %s\r\n",e);
						System.out.println("Try again");
						
					}
				
					
					
				}
				else {
					
					System.out.println(cmd + " FOUND in cache. See content below");
					System.out.println(fc.getContent());

				}
			}
			
			
		}
		scanner.close();
		
		
		
		
	}
	
	

}
