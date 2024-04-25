package ru.otus.evgrez.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Objects;

public class SoftRefCache<K, V> extends AbstractRefCache<K, V> implements Cache<K, V> {

    public SoftRefCache() {
        super(new HashMap<>(), new ReferenceQueue<>());
    }

    @Override
    protected Reference<K> getRefKey(K key, ReferenceQueue<K> referenceQueue) {
        return new SoftRefKey<>(key, referenceQueue);
    }
    private static class SoftRefKey<K> extends SoftReference<K> {

        private final int hashCode;

        public SoftRefKey(K referent, ReferenceQueue<? super K> referenceQueue) {
            super(referent, referenceQueue);
            hashCode = referent.hashCode();
        }

        @Override
        public int hashCode() {
            return hashCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SoftRefKey<?> that = (SoftRefKey<?>) o;
            return Objects.equals(this.get(), that.get());
        }
    }
}
