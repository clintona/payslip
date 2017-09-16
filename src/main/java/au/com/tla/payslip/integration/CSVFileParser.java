package au.com.tla.payslip.integration;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.Ostermiller.util.CSVParser;
/**
 * Read lines from a CSV file and split into String tokens.
 *  
 * @author Clinton
 *
 */
public class CSVFileParser {

	/**
	 * Parse a CSV file and split it into an array of tokens.
	 * @param filename file to parse
	 * @return array of String tokens
	 * @throws IllegalArgumentException if filename cannot be found
	 */
	public String[][] parseFile(String filename) {
		InputStream in = null;
		Reader reader = null;
		
		try {
			in = new FileInputStream(filename);
			reader = new InputStreamReader(in);
			return CSVParser.parse(reader);
		} catch (IOException e) {
			throw new IllegalArgumentException(e); // convert to runtime exception
		} finally {
			close(in);
			close(reader);
		}
	}
	
	private void close(Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (Exception e) {
			throw new IllegalStateException("close error: " + e);
		}
	}
}
