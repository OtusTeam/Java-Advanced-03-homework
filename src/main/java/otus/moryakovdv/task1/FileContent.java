package otus.moryakovdv.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;
/**Содержимое файла. Статичекий конструктор читает содержимое из ФС по переданному пути или из ресурсов**/
public class FileContent implements CacheValue<String> {

	private LinkedList<String> content;

	private FileContent(LinkedList<String> content) {

		this.content = content;

	}

	@Override
	public LinkedList<String> getContent() {
		return content;
	}

	public static FileContent valueOf(String path, String fileName) throws FileNotFoundException, IOException {

		Path pToFfile = Paths.get(path,fileName);
		if (!Files.exists(pToFfile)) {
			
			System.out.println("Read file from resources...");
			
			InputStream is =  FileContent.class.getResourceAsStream("/"+path+"/"+fileName);
			if (is==null)
					throw new FileNotFoundException();
			
			LinkedList<String> cnt = new BufferedReader(
				      new InputStreamReader(is, StandardCharsets.UTF_8))
				        .lines()
				        .collect(Collectors.toCollection(LinkedList::new));
			
			return new FileContent(cnt);
			
		} else {
			
			System.out.println("Read file from catalog...");
			LinkedList<String> cnt = Files.lines(pToFfile).collect(Collectors.toCollection(LinkedList::new));
			return new FileContent(cnt);
			
		}

	}

	@Override
	public String toString() {

		return this.content.toString();
	}

}
