package otus.moryakovdv.task1;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
/***Реализация кеша на ExtendedWeakReference*/
public class WeakReferenceValueCache implements Cache<String, FileContent> {
	
	private ConcurrentHashMap<String, ExtendedWeakReference<String,FileContent>> innerStorage = new ConcurrentHashMap<>();
	private ReferenceQueue<FileContent> queue = new ReferenceQueue<>();
	
	private Future<?> fResult;
	private ExecutorService reaper = Executors.newSingleThreadExecutor();
	private boolean running = true;
	private AtomicInteger counter = new AtomicInteger();
	private Runnable clearer = new Runnable() {
		
		@Override
		public void run() {
			Thread.currentThread().setName("ReaperThread");

			
			System.out.println("reaper started");
			Reference<?> ref;
			
			while(running) {
				ref = queue.poll();
				if (ref!=null) {
					
					String refKey = ((ExtendedWeakReference<String,FileContent>)ref).getKey();
					if (refKey!=null)
						innerStorage.remove(refKey);
				} 
				
			}
			System.out.println("reaper stopped");

		}
	};
	
	public WeakReferenceValueCache() {
	}
	
	@Override
	public void put(String key, FileContent value) {
		ExtendedWeakReference<String, FileContent> ref = new ExtendedWeakReference<>(key, value, queue);
		innerStorage.put(key, ref);
	}

	@Override
	public synchronized FileContent get(String key) {
		ExtendedWeakReference<String, FileContent> ref = innerStorage.get(key);
		if (ref!=null) {
			if (ref.get()!=null)
				counter.incrementAndGet();

			return ref.get();
		}
		return null;
	}

	@Override
	public FileContent remove(String key) {
		ExtendedWeakReference<String, FileContent> ref = innerStorage.remove(key);
		return ref.get();
	}


	@Override
	public void stopReaper() {

		reaper.shutdownNow();
		running = false;
		fResult.cancel(true);
		
	}
	
	public void traverse() {
		innerStorage.forEach((k,v)->{
			
				System.out.println(String.format("key=%s;ref=%s;value=%s",k,v,v.get()));
			
		});
		
	}

	@Override
	public int cacheHitCount() {
	
		return counter.get();
	}
	
	@Override
	public void startReaper() {
		fResult = reaper.submit(clearer);	
		
	}
}
