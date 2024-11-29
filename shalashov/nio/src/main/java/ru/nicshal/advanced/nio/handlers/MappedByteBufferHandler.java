package ru.nicshal.advanced.nio.handlers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import static ru.nicshal.advanced.nio.utils.ApplicationConstant.COPY_POSTFIX;

public class MappedByteBufferHandler implements FileContentHandler {

    @Override
    public void copyFileContents(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             FileOutputStream fos = new FileOutputStream(filePath + COPY_POSTFIX);
             FileChannel fcin = fis.getChannel();
             FileChannel fcout = fos.getChannel()) {

            MappedByteBuffer mbb = fcin.map(FileChannel.MapMode.READ_ONLY, 0, fcin.size());
            fcout.write(mbb);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reverseFileContents(String filePath) {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
             FileChannel fc = raf.getChannel();) {

            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size());

            int len = (int) fc.size();
            for (int i = 0, j = len - 1; i < j; i++, j--) {
                byte b = mbb.get(i);
                mbb.put(i, mbb.get(j));
                mbb.put(j, b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
