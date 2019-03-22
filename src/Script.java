import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Script {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Started");
		
		String commonPath = "D:\\Users\\1418690\\Downloads\\19th Mar\\19th Mar\\";
		String rfFilePath = commonPath+"Ring_Stock.txt";
		File rfFile = new File(rfFilePath);
		String scriptFilePath = commonPath+"Insert_Scripts.txt";
		File scriptFile = new File(scriptFilePath);
		
		String resourcePath = "Resource\\";
		String mapFilePath = resourcePath+"GLN_Map.txt";
		File mapFile = new File(mapFilePath);
		String uptMapFilePath = resourcePath+"UPT_Map.txt";
		File uptMapFile = new File(uptMapFilePath);
		
		Scanner rfFileObj = new Scanner(rfFile);
		ArrayList<List<String>> al = new ArrayList<List<String>>();
		while(rfFileObj.hasNextLine())
		{
			String s = rfFileObj.nextLine();
			//System.out.println(s);
			al.add(Arrays.asList(s.split(",", -1)));
		}
		
		//GLNCode-Sap-Site code map
		Scanner obs = new Scanner(mapFile);
		HashMap<String, String> hm = new HashMap<String, String>();
		String a;
		String b;
		while(obs.hasNextLine())
		{
			a = obs.next();
			b = obs.next();
			hm.put(a, b);
			//System.out.println(a+" "+b);
		}
		
		//UptMap
		Scanner scn = new Scanner(uptMapFile);
		HashMap<String, String> hmUpt = new HashMap<String, String>();
		String upc;
		String upt;
		while(scn.hasNextLine())
		{
			upc = scn.next();
			upt = scn.next();
			hmUpt.put(upc, upt);
			//System.out.println(upc+" "+upt);
		}
		
		//Create File for the Script
		BufferedWriter writer = new BufferedWriter(new FileWriter(scriptFile));
		String upcValue = "";
		String depotValue = "5012068097712"; //Clonshaugh
		String glnCodeValue = "";
		String date = "";
		String uptValue = "";
		String qtyValue = "";
		String qtyUpdtValue = "";
		String mfdValue = "";
		
		for(List st: al)
		{
			upcValue = st.get(1).toString();
			glnCodeValue = hm.get(st.get(2).toString());
			date = st.get(3).toString();
			uptValue = hmUpt.get(upcValue);
			qtyValue = st.get(4).toString();
			qtyUpdtValue = qtyValue;
			mfdValue = st.get(7).toString();
		
			if(uptValue.equals(""))
			{
				uptValue="0";
			}
			if(qtyValue.equals(""))
			{
				qtyValue="0";
			}
			if(qtyUpdtValue.equals(""))
			{
				qtyUpdtValue="0";
			}
			if(mfdValue.equals(""))
			{
				mfdValue="0";
			}
			
			//System.out.println(x);
			String dateValue = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8)+" 00:00:00.000";
			String query = "insert into oscs.RINGFENCED_STOCK_TEMP values ('"+upcValue+"',"+depotValue+","+glnCodeValue+""+",'"+dateValue+"',"+uptValue+","+qtyValue+","+qtyUpdtValue+","+mfdValue+");";
			writer.write(query);
			writer.append('\n');
		}
		writer.close();
		System.out.println("Completed");
	}

}
