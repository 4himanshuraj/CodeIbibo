package testCases;

import utility.ObjectMap;
import utility.ReadExcel;
import utility.Screenshot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class test1 {

	public WebDriver driver;
	public ObjectMap objmap;
	
	String baseURL1 = "https://www.goibibo.com/";
    
    @Parameters({ "browser" })
    @BeforeTest
    public void openBrowser(String browser) {
        try {
             if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        "D:\\MyWork\\Eclipse-Workspace\\GoIbiboDemo\\driverFiles\\chromedriver.exe");
                driver = new ChromeDriver();
            } 
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login_TestCase() throws Exception {
    	
    	ReadExcel re = new ReadExcel("D:\\MyWork\\Eclipse-Workspace\\GoIbiboDemo\\Data\\data.xlsx");
    	String from1 = re.getCellData("Sheet1","From",2);
    	String to1 = re.getCellData("Sheet1","To",2);
    	  	
    	
    	
    	//Get current working directory
    	  String workingDir=System.getProperty("user.dir");
    	  //Get object map file
    	  objmap = new ObjectMap (workingDir+"\\ObjectProperties\\objectMap.properties");
    	
    	  System.out.println("From-"+from1);
    	  System.out.println("To-"+to1);
        driver.get(baseURL1);
        driver.manage().window().maximize();
        
        //Task1-Printing all tabs
        printTabText();
        
        //Task2-Navigating to next page after entering the fields
        WebElement header = driver.findElement(objmap.getLocator("goibibo.home.header"));
        
        //Select From textbox and enter the From field from excel file
        WebElement from = driver.findElement(objmap.getLocator("goibibo.home.from"));
        from.click();
        from.sendKeys(from1);
        header.click();
        
        //Select To textbox and enter the To field from excel file
        WebElement to = driver.findElement(objmap.getLocator("goibibo.home.to"));
        to.click();
        to.sendKeys(to1);
        header.click();
        Screenshot.captureScreenshot(driver);   
        
       
        
        //Select Depart Date
        WebDriverWait wait=new WebDriverWait(driver,20); 
        wait.until(ExpectedConditions.visibilityOfElementLocated(objmap.getLocator("goibibo.home.depart.date")));
        WebElement depart_date = driver.findElement(objmap.getLocator("goibibo.home.depart.date"));
        depart_date.click();
        WebDriverWait wait1=new WebDriverWait(driver,20);  
        wait1.until(ExpectedConditions.visibilityOfElementLocated(objmap.getLocator("goibibo.home.depart.date.select")));
        WebElement depart_date_select = driver.findElement(objmap.getLocator("goibibo.home.depart.date.select"));
        depart_date_select.click();
        		
       //Select Return Date        
        WebElement return_date = driver.findElement(objmap.getLocator("goibibo.home.return.date"));
        return_date.click();
        WebDriverWait wait2=new WebDriverWait(driver,20);  
        wait2.until(ExpectedConditions.visibilityOfElementLocated(objmap.getLocator("goibibo.home.return.date.select")));
        WebElement return_date_select = driver.findElement(objmap.getLocator("goibibo.home.return.date.select"));
        return_date_select.click();
        
        
    }

    
    public void printTabText() throws Exception {
    	List<WebElement> links = driver.findElements(objmap.getLocator("goibibo.tab"));
    	System.out.println(links.size());
    	 
		for (int i = 1; i<links.size(); i=i+1)
 
		{
 
			System.out.println(links.get(i).getText());
 
		}
    	
    }
    
   

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}
