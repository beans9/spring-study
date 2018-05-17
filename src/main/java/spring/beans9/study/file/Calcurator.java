package spring.beans9.study.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calcurator {
	public Integer calcSum(String filepath) throws Exception {
//		BufferedReaderCallBack sumCallBack = new BufferedReaderCallBack() {
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer sum = 0;
//				String line = null;
//				while((line = br.readLine()) != null ) {
//					sum += Integer.valueOf(line);
//				}
//				return  sum;
//			}
//		};
		LineCallBack sumCallBack = new LineCallBack<Integer>() {
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, sumCallBack, 0);		
	}
	
	public Integer calcMultiply(String filepath) throws Exception {
//		BufferedReaderCallBack sumCallBack = new BufferedReaderCallBack() {
//			public Integer doSomethingWithReader(BufferedReader br) throws IOException {
//				Integer sum = 1;
//				String line = null;
//				while((line = br.readLine()) != null ) {
//					sum *= Integer.valueOf(line);
//				}
//				return  sum;
//			}
//		};
//		return fileReadTemplate(filepath, sumCallBack);
		LineCallBack sumCallBack = new LineCallBack<Integer>() {
			public Integer doSomethingWithLine(String line, Integer value) {
				return value * Integer.valueOf(line);
			}
		};
		return lineReadTemplate(filepath, sumCallBack, 1);
	}
	
	public String concat(String filepath) throws Exception {
		LineCallBack sumCallBack = new LineCallBack<String>() {
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		return lineReadTemplate(filepath, sumCallBack, "");
	}
	
	public <T> T lineReadTemplate(String filepath, LineCallBack<T> callback, T initVal) throws Exception {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomethingWithLine(line, res);
			}
			return  res;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if( br != null) { try { br.close(); }catch (Exception e) { System.out.println(e.getMessage()); }}
		}
	}
	
	public Integer fileReadTemplate(String filepath, BufferedReaderCallBack callback) throws Exception {
		BufferedReader br = null;
		Integer sum = 0;
		try {
			br = new BufferedReader(new FileReader(filepath));
			int ret = callback.doSomethingWithReader(br);
			return  ret;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if( br != null) { try { br.close(); }catch (Exception e) { System.out.println(e.getMessage()); }}
		}
	}

	
}
