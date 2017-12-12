package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class Screenshot {

	public static void captureScreenshot(WebDriver driver) throws IOException
    {
        java.util.Date date= new java.util.Date();
        TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String dest = System.getProperty("user.dir") +"\\Screenshots\\"+date.getTime()+".png";
    File destination = new File(dest);
    FileUtils.copyFile(source, destination);    
    
    }
	
}
