package importcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class importcsv {
	
	public static List<String[]> csvimport () throws IOException {
		File file;
		FileReader fr;
		BufferedReader bfr;
		
		String FILEPATH = "src/importcsv/import.csv";
		file = new File(FILEPATH);
		fr = new FileReader(file);
		bfr= new BufferedReader(fr);
		
		
		List<String[] > data = new ArrayList<String[] >();

		String nextLine = null;
		while ((nextLine = bfr.readLine()) != null) {
		    String s = nextLine;
		    data.add(s.split(","));
		}
		bfr.close();
		return data;
	}
}
