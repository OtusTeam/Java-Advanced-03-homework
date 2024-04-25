package ru.otus.evgrez.cache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Objects;

public class WeakRefCache<K, V> extends AbstractRefCache<K, V> implements Cache<K, V> {

    public WeakRefCache() {
        super(new HashMap<>(), new ReferenceQueue<>());
    }

    @Override
    protected Reference<K> getRefKey(K key, ReferenceQueue<K> referenceQueue) {
        return new WeakRefKey<>(key, referenceQueue);
    }

    private static class WeakRefKey<K> extends WeakReference<K> {

        private final int hashCode;

        public WeakRefKey(K referent) {
            super(referent);
            hashCode = referent.hashCode();
        }

        public WeakRefKey(K referent, ReferenceQueue<? super K> referenceQueue) {
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
            WeakRefKey<?> that = (WeakRefKey<?>) o;
            return Objects.equals(this.get(), that.get());
        }

        @Override
        public String toString() {
            return "WeakRefKey{" +
                    "hashCode=" + hashCode + "," +
                    "referent=" + super.get() +
                    "} ";
        }
    }
}
