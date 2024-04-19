package otus.moryakovdv.task1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Наследник WeakReference. Дополнен ключом для дальнейшего поиска при очистке
 **/
public class ExtendedWeakReference<K, V> extends WeakReference<V> {

	private K key;

	public ExtendedWeakReference(K key, V referent, ReferenceQueue<V> q) {
		super(referent, q);
		this.key = key;
	}

	public K getKey() {
		return this.key;
	}

}
