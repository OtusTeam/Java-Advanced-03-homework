package ru.otus.services.filecache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class SoftRefFileCache implements FileCache {
    private final String charset;
    private final Map<String, SoftReference<ByteBuffer>> files = new HashMap<>();

    public SoftRefFileCache() {
        this.charset = "UTF-8";
    }

    @Override
    public void loadContent(String id, ByteBuffer content) {
        files.put(id, new SoftReference<>(content));
    }

    @Override
    public String getContent(String id) {
        String content = null;
        if (files.containsKey(id)) {
            ByteBuffer buffer = files.get(id).get();
            if (buffer != null) {
                CharBuffer charBuffer = Charset.forName(charset).decode(buffer);
                content = charBuffer.toString();
                buffer.flip();
            }
        } else {
            throw new RuntimeException("No file content for " + id);
        }
        return content;
    }

    @Override
    public boolean hasContent(String id) {
        return files.containsKey(id);
    }
}
