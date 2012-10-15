import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class JsonGenerator {
	private static List<String> list = new ArrayList<String>();
	
	private static final String heading = "public class ";
	private static final String end = "}";
	private static final String field = "private ";

	public static void main(String[] args) throws Exception {
		list.add("{");
		list.add("}");
		list.add(":");
		list.add(",");
		list.add("\"");
		list.add("[");
		list.add("]");
		
		String className = args[0];

		String jsonString = readJsonFromFile();
		Queue<String> jsonList = createListOfJsonClass(jsonString);
		//System.out.println(jsonList);
		createClass(jsonList, className);
		/*String[] str = jsonString.split(",");
		for(String s : str){
			System.out.println(s);
		}*/
		
		//createClassFile(ele);
	}
	
	private static String readJsonFromFile() {
		try {
			FileReader input = new FileReader("src/main/resources/schema/bulkgetjson.json");
			BufferedReader bufRead = new BufferedReader(input);
			StringBuffer str = new StringBuffer("");
			String s = null;
			while ((s = bufRead.readLine()) != null) {
				str.append(s.trim());
			}
			return str.toString().trim();
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			
		}
		
		return "";
	}
	
	
	private static void createClass(Queue<String> jsonList, String className){
		StringBuffer classEle = new StringBuffer(heading);
		classEle.append(className + " "  + "{");
		//remove "{" from list
		jsonList.poll();
		while(true){
			String fieldType = null;
			//remove "
			jsonList.poll();
			String fieldName = changeToCamelCase(jsonList.poll());

			//remove "
			jsonList.poll();
			//remove :
			jsonList.poll();
			
			
			if("{".equals(jsonList.peek())){
				fieldType = changeToClassNameFormat(fieldName);
				createClass(jsonList, fieldType);
			}else if("[".equals(jsonList.peek())){
				fieldType = changeToClassNameFormat(fieldName);
				//remove [
				jsonList.poll();
				if("\"".equals(jsonList.peek())){
					fieldType = "List<String>";
				}else if(isNumber(jsonList.peek())){
					fieldType = "List<Long>";
				}else if(isDecimal(jsonList.peek())){
					fieldType = "List<Double>";
				}else if("{".equals(jsonList.peek())){
					createClass(jsonList, fieldType);
					fieldType = "List<" + fieldType + ">";
					//return;
				}
				/*while(!"]".equals(jsonList.poll())){
					//jsonList.poll();
				}*/
				
				while(true){
					String s = jsonList.peek();
					jsonList.poll();
					if("[".equals(s)){
						while(!"]".equals(jsonList.poll()));
					}else if("]".equals(s)){
						break;
					}
				}
				

			}else{
				if("\"".equals(jsonList.peek())){
					fieldType = "String";
					//remove "
					jsonList.poll();
					//remove element value
					jsonList.poll();
					//remove "
					jsonList.poll();
				}else if(isNumber(jsonList.peek())){
					fieldType = "Long";
					//remove element value
					jsonList.poll();
				}else if(isDecimal(jsonList.peek())){
					fieldType = "Double";
					//remove element value
					jsonList.poll();
				}else if(isBoolean(jsonList.peek())){
					fieldType = "Boolean";
					//remove element value
					jsonList.poll();
				}
				
			}
			
			
			
			classEle.append(field);
			classEle.append(fieldType);
			classEle.append(" ");
			classEle.append(fieldName);
			classEle.append(";");
			classEle.append(generateGetters(fieldType, fieldName));
			classEle.append(generateSetters(fieldType, fieldName));
			
			if("}".equals(jsonList.peek())){
				//remove }
				jsonList.poll();
				break;
			}
			//remove ,
			jsonList.poll();
			
		}
		/*while(!"{".equals(jsonList.peek()) && !"}".equals(jsonList.peek())){
			
		}*/
		//return null;
		classEle.append(end);
		System.out.println(classEle);
		writeToFile(classEle.toString(), className);
		
	}
	
	private static boolean isNumber(String str){
		return str.trim().matches("\\d*");
	}
	private static boolean isDecimal(String str){
		return str.trim().matches("\\d+.\\d+");
	}
	private static boolean isBoolean(String str){
		return "TRUE".matches(str.trim().toUpperCase()) || "FALSE".matches(str.trim().toUpperCase());
	}
	
	private static String generateGetters(String fieldType, String fieldName){
		return "public " + fieldType + " get" + changeToClassNameFormat(fieldName) + "() {return " + fieldName + ";}";
	}
	
	private static String generateSetters(String fieldType, String fieldName){
		return "public void set" + changeToClassNameFormat(fieldName) + "(" + fieldType + " " + fieldName +  "){this." + fieldName + "=" + fieldName + ";}";
	}
	
	private static String changeToCamelCase(String str){
		return str.substring(0, 1).toLowerCase().concat(str.substring(1));
	}
	
	private static String changeToClassNameFormat(String str){
		return str.substring(0, 1).toUpperCase().concat(str.substring(1));
	}
	
	private static void writeToFile(String classEle, String className){
		try {
			File file = new File( className + ".java");
			FileWriter output = new FileWriter(file);
			BufferedWriter bufWriter = new BufferedWriter(output);
			if(classEle.contains("List")){
				bufWriter.write("import java.util.List;");
			}
			bufWriter.write(classEle);
			bufWriter.flush();
			bufWriter.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			
		}
	}

	private static Queue<String> createListOfJsonClass(String str) {
		Queue<String> jsonList = new LinkedList<String>();
		long quotesCount = 0;
		long length = str.length();
		StringBuffer temp = new StringBuffer("");
		String lastEle = null;
		for (int i = 0; i < length; i++) {
			String tempStr = str.substring(i, i + 1);
			if("\"".equals(tempStr)){
				quotesCount++;
			}
			if (list.contains(tempStr) && (quotesCount%2==0 || "\"".equals(tempStr)) ) {
				if (!"".equals(temp.toString().trim())) {
					jsonList.add(temp.toString());
				}

				temp = new StringBuffer("");
				if("\"".equals(tempStr) && "\"".equals(lastEle)){
					jsonList.add("");
				}
				jsonList.add(tempStr);
			} else {
				temp.append(tempStr);
			}
			lastEle = tempStr;
		}

		return jsonList;
	}

}
