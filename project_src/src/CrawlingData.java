import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
 
public class CrawlingData {

	private static String[][] tableData= {{"","",""},{"","",""}};
	public static String[][] GetCrawlingTable(String locate1, String locate2, String subject) {
		
		String keyword = GetKeword(locate1, locate2, subject);
		
        CrawlingData CrawlingData1 = new CrawlingData();
        CrawlingData CrawlingData2 = new CrawlingData();
        Crawling1 crawling1 = new Crawling1(CrawlingData1, keyword);
        Crawling2 crawling2 = new Crawling2(CrawlingData2, keyword);

        try { crawling1.start(); crawling2.start(); crawling2.join(); } 
        catch (Exception e) { e.printStackTrace(); }
        
		try { Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T"); } 
		catch (IOException e) { e.printStackTrace(); }
		
        return tableData;
    }
    
	private String temp1;
	private String temp2;
    private String base_url;
   
    private WebDriver driver;  
    
    public CrawlingData() {
        super();
        
        String webDriverPath = new File("").getAbsoluteFile()+"/src/library/chromedriver.exe";
        String webDriverID = "webdriver.chrome.driver";

        System.setProperty(webDriverID, webDriverPath);
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        base_url = "https://www.modoodoc.com/";
    }
    
    public static String GetKeword(String locate1, String locate2, String subject) {
    	String keyWord = null;
    	if (locate1 == "서울특별시" || locate1 == "부산광역시" || locate1 == "대구광역시" || 
    		locate1 == "인천광역시" || locate1 == "대전광역시" || locate1 == "울산광역시" ||
    		locate1 == "광주광역시" || locate1 == "세종특별자치시" || locate1 == "제주특별자치도")
    		keyWord = locate1.substring(0,2)+" "+locate2+" "+subject; 		
    	else
    		keyWord = locate1+" "+locate2+" "+subject; 	
    	return keyWord;
    }
    
    public void DoCrawl(String keyword) {
        try {
            driver.get(base_url);
            WebElement element = driver.findElement(By.xpath("//*[@id=\"query-search-box\"]"));
            element.sendKeys(keyword);
            driver.findElement(By.xpath("//*[@id=\"landing-search-button\"]")).click();
            
            if(Thread.currentThread().getName().equals("crawlingData1")) {
            	driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/div[3]/div[1]/a/div/div[2]")).click();
            	try {
            		temp1=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[6]/div[1]")).getText();
                    if(temp1.equals("코로나19 예방접종 위탁의료기관"))
                    	crawlData2();
                    else
                    	crawlData1();
            	}    	
        	    catch(Exception e) {
                    temp1=driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[6]/div[1]")).getText();
                    if(temp1.equals("코로나19 예방접종 위탁의료기관"))
                    	crawlData2_2();
                    else
                    	crawlData1_1();
                }
            }
            else {
            	driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/div[3]/div[2]/a/div/div[2]")).click();
            	try {
            		temp2=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[6]/div[1]")).getText();
            		Thread.sleep(1000);
                    if(temp2.equals("코로나19 예방접종 위탁의료기관"))
                    	crawlData4();
                    else
                    	crawlData3();
            	}    	
        	    catch(Exception e) {
                    temp2=driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[6]/div[1]")).getText();
                    Thread.sleep(1000);
                    if(temp2.equals("코로나19 예방접종 위탁의료기관"))
                    	crawlData4_4();
                    else
                    	crawlData3_3();
                }
            }     
        } 
        catch (Exception e) { e.printStackTrace(); } 
        finally { driver.close(); }
    }
    
    public void crawlData1() {
    	    tableData[0][0]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]")).getText();
    	    tableData[0][1]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[6]/div[@class='color49 mt-2']")).getText();
    	    tableData[0][2]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[6]/div[@class='color49 mt-3']")).getText();
    }
    
    public void crawlData1_1() {
    	try {
    		tableData[0][0]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[2]")).getText();
    		tableData[0][1]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-2']")).getText();
    		tableData[0][2]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-3']")).getText();
           
    	}
    	catch(Exception ex) { return; }
    }
    
    public void crawlData2() {
    	    tableData[0][0]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]")).getText();
    	    tableData[0][1]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[7]/div[@class='color49 mt-2']")).getText();
            tableData[0][2]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[7]/div[@class='color49 mt-3']")).getText();
    }
    
    public void crawlData2_2() {
    	try {
    		tableData[0][0]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[2]")).getText();
    		tableData[0][1]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-2']")).getText();
    		tableData[0][2]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-3']")).getText();
       }
    	catch(Exception ex) { return; }
    }
    
    public void crawlData3() {
	    tableData[1][0]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]")).getText();
	    tableData[1][1]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[6]/div[@class='color49 mt-2']")).getText();
	    tableData[1][2]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[6]/div[@class='color49 mt-3']")).getText();
    }
    
    public void crawlData3_3(){
    	try {
    		tableData[1][0]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[2]")).getText();
    		tableData[1][1]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-2']")).getText();
    		tableData[1][2]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-3']")).getText();
       
    	}
    	catch(Exception ex) { return; }
    }
    
    public void crawlData4() {
	    tableData[1][0]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]")).getText();
	    tableData[1][1]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[7]/div[@class='color49 mt-2']")).getText();
        tableData[1][2]= driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[7]/div[@class='color49 mt-3']")).getText();
    }
    
    public void crawlData4_4() {
	try {
		tableData[1][0]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[2]")).getText();
		tableData[1][1]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-2']")).getText();
		tableData[1][2]= driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]/div[7]/div[@class='color49 mt-3']")).getText();
		}
		catch(Exception ex) { return; }
   }
}

class Crawling1 extends Thread{
	private CrawlingData crawlingData1;
	private String keyword;
	
	public Crawling1(CrawlingData crawlingData1, String keyword) {
		this.crawlingData1 = crawlingData1;
		this.keyword = keyword;
	}
	
	public void run() {
		Thread.currentThread().setName("crawlingData1");
		crawlingData1.DoCrawl(keyword);
	}
}

class Crawling2 extends Thread{
	private CrawlingData crawlingData2;
	private String keyword;
	
	public Crawling2(CrawlingData crawlingData2, String keyword) {
		this.crawlingData2 = crawlingData2;
		this.keyword = keyword;
	}
	
	public void run() {
		Thread.currentThread().setName("crawlingData2");
		
		crawlingData2.DoCrawl(keyword);
	}
}
