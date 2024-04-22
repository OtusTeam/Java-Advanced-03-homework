package otus.moryakovdv.task1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
/**Наследник SoftReference. Дополнен ключом для дальнейшего поиска при очистке**/
public class ExtendedSoftReference<K,V> extends SoftReference<V> {

	private K key;
	
	public ExtendedSoftReference(K key, V referent, ReferenceQueue<V> q) {
		super(referent, q);
		this.key=key;
	}
	
	public K getKey() {
		return this.key;
	}
	

}
