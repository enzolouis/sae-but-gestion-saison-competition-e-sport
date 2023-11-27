package importcsv;

import java.io.IOException;
import java.util.List;

public class importtest {

	public static void main(String[] args) throws IOException {
		List<String[]> s = importcsv.csvimport();
		for (String[] Data : s) {
			for (String Data2 : Data) {
				System.out.print(Data2);
			}
		}
	}
}
