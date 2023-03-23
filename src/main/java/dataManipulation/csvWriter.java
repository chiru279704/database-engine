package dataManipulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import com.opencsv.CSVWriter;
import storage.Table;

public class csvWriter {

	CSVWriter writer;

	public csvWriter() {
		try {
			this.writer = new CSVWriter(new FileWriter("Metadata//metadata.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(Table table){
		for (Entry<String, String> e : table.getColNameType().entrySet()) {
			writeRecord(table.getName(), e.getKey(), e.getValue(), e.getKey().equals(table.getPKColumn()) + "", "null",
					"null", table.getColNameMin().get(e.getKey()), table.getColNameMax().get(e.getKey()));
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void writeRecord(String tableName, String colName, String colType, String isClusteringKey, String indexName,
			String indexType, String minValue, String maxValue) {
		String[] record = { tableName, colName, colType, isClusteringKey, indexName, indexType, minValue, maxValue };
		writer.writeNext(record);
	}
}
