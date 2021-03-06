package selenium;



import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Practise 
{

	public static void main(String[] args) throws Exception
	{
	//reading uid & pwd from Excel file
		File f=new File("G:\\goindigo.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("sheet1");
		int r=sh.getPhysicalNumberOfRows();
		int c=sh.getRow(0).getLastCellNum();
		System.out.println("no of used rows    "+r+"no of used cols   "+c);
		
//		for(int i=1;i<r;i++)
//		{
//			int X= (int) sh.getRow(i).getCell(0).getNumericCellValue();
//		
		for(int j=1;j<c;j++)
		{
			String Y=sh.getRow(j).getCell(1).getStringCellValue();
		
		
		WebDriver driver;
	//webdriver manager
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	//launch site
		driver.get("https://www.goindigo.in/");
	//maximize window
		driver.manage().window().maximize();
	//click login
		driver.findElement(By.xpath("(//*[text()='Login'])[1]")).click();
	//send uid & pwd
		driver.findElement(By.id("memberId")).sendKeys("9603634253");
		driver.findElement(By.xpath("//*[@type='password']")).sendKeys(Y);
	//click
		driver.findElement(By.xpath("(//*[text()='Login'])[3]")).click();
		Thread.sleep(5000);
	//click on from destination using actions
		WebElement e=driver.findElement(By.xpath("//*[@class='form-control or-src-city']"));
		Actions a=new Actions(driver);
		a.click(e).perform();
		try 
		{
			driver.switchTo().alert().dismiss();
		}
		catch( Exception b)
		{
	//Explicit wait
		WebDriverWait w=new WebDriverWait(driver,20);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-name='Delhi'])[1]")));
	//select delhi by using actions class
		WebElement e1=driver.findElement(By.xpath("(//*[@data-name='Delhi'])[1]"));
		Actions a1=new Actions(driver);
		a1.click(e1).perform();
		}
	//waiting for to destination to perform click operation
		WebDriverWait w=new WebDriverWait(driver,20);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='or-dest']")));
		WebElement e2=driver.findElement(By.xpath("//*[@name='or-dest']"));
	//clicking to destination using java Script Executor
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",e2);
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='or-dest']")));
    	WebElement e3=driver.findElement(By.xpath("//*[@name='or-dest']"));
		a.click(e3).click();
	
	//clicking on to destination
			js.executeScript("arguments[0].click()",driver.findElement(By.xpath("(//*[text()='Rajiv Gandhi International Airport'])[3]")));

			try
			{
				driver.switchTo().alert().dismiss();
			}
			catch (Exception e4)
			{
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[text()='27'])[1]")));
			//Scrolling window down
			 js.executeScript("window.scrollBy(0,400)");
			 Thread.sleep(3000);
			 //clicking on date
		     WebElement e5=driver.findElement(By.xpath("(//*[text()='27'])[1]"));
		     js.executeScript("arguments[0].click()",e5);
		     //scrolling window up
		     js.executeScript("window.scrollBy(0,-400)");
		     w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='icon-close close-pass-tooltip']")));
		     driver.findElement(By.xpath("//*[@class='icon-close close-pass-tooltip']")).click();
		    driver.findElement(By.xpath("(//*[@placeholder='Return Date'])[1]")).click();
		    //scrolling window down
		    js.executeScript("window.scrollBy(0,400)");
		    driver.findElement(By.xpath("(//*[text()='27'])[2]")).click();
			}
	}

}
	}

