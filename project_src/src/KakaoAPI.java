
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;;

public class KakaoAPI {
	public static String[][] getHospitalData(String locate1, String locate2, String subject) {
		String[][] tableData 
					= {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		try {
			String keyword = locate1+locate2+subject;
	        String apiURL 
	        		="https://dapi.kakao.com/v2/local/search/keyword.json?page=1&size=10&sort=accuracy&query="
	        		  +URLEncoder.encode(keyword,"UTF-8")+"&category_group_code=HP8";
	        
	        URL url = new URL(apiURL);
	        HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            
	        con.setRequestMethod("GET");
	        con.setRequestProperty("Authorization", "KakaoAK 5808d016ab8ea5d7a8fc9c24c100a082");
	        con.setUseCaches(false);
	        con.setDoInput(true);
	        con.setDoOutput(true);

	        BufferedReader br;
	        br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8")); 

	        String inputLine;
	        StringBuffer res = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) 
	            	res.append(inputLine);
	        br.close();
	            
	        JsonParser Parser = new JsonParser();
	        JsonObject jsonObj = (JsonObject)Parser.parse(res.toString());
	        JsonArray memberArray = (JsonArray)jsonObj.get("documents");
	                  
	        for (int i = 0; i < memberArray.size(); i++) {          
	           	JsonObject object = (JsonObject) memberArray.get(i);
	         	tableData[i][0] = object.get("place_name").toString().replaceAll("\\\"","");;
	           	tableData[i][1] = object.get("phone").toString().replaceAll("\\\"","");;
	           	tableData[i][2] = object.get("road_address_name").toString().replaceAll("\\\"","");;
	        	}  
	        
	        return tableData;
	        } 
		
	        catch (Exception e) { System.out.println(e); return tableData;}
	}
}
