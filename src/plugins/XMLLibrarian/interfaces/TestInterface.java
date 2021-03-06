package plugins.XMLLibrarian.interfaces;


import plugins.XMLLibrarian.*;
import freenet.support.Logger;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class TestInterface{
	static HashMap<String,Request> requests = new HashMap<String,Request>();
	static int requestcount =0;
	
	public static void main(String[] args){
		try{
			Logger.setupStdoutLogging(Logger.MINOR, "Strarting logging");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String command;
			String param;
			String line;
			
			do{
				line = br.readLine();
				command = line.substring(0, 1);
				param = line.substring(1);
				
				if("f".equals(command)){
					requests.put(""+requestcount,Library.findTerm("index", param));
					System.out.println("Started request "+requestcount);
					requestcount++;
				}
				if("s".equals(command)){
					requests.put(""+requestcount,Search.startSearch(param, "index"));
					System.out.println("Started request "+requestcount);
					requestcount++;
				}
				if("p".equals(command))
					System.out.println(requests.get(param).toString());
				if("r".equals(command))
					System.out.println(requests.get(param).getResult());
				if("i".equals(command))
					System.out.println(Library.getIndex("index"));
				if("w".equals(command))
					WebUI.resultNodeGrouped(requests.get(param), true, true);
			}while(!"x".equals(command));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
