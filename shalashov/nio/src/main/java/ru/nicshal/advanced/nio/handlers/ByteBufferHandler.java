package ru.nicshal.advanced.nio.handlers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static ru.nicshal.advanced.nio.utils.ApplicationConstant.COPY_POSTFIX;

public class ByteBufferHandler implements FileContentHandler {

    private final int bufferLength;

    public ByteBufferHandler(int bufferLength) {
        this.bufferLength = bufferLength;
    }

    public int getBufferLength() {
        return bufferLength;
    }

    @Override
    public void copyFileContents(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(filePath + COPY_POSTFIX);
             FileChannel fcin = fis.getChannel();
             FileChannel fcout = fos.getChannel()) {

            ByteBuffer buf = ByteBuffer.allocateDirect(bufferLength);

            long size = fcin.size();
            long n = 0;
            while (n < size) {
                buf.clear();
                if (fcin.read(buf) < 0) {
                    break;
                }
                buf.flip();
                n += fcout.write(buf);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ByteBufferHandler{" +
                "bufferLength=" + bufferLength +
                '}';
    }

}
