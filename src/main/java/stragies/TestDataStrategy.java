package stragies;

import java.util.ArrayList;


public interface TestDataStrategy {

	ArrayList<ArrayList<Object>> loadTestData(String filePath) throws Exception;
}