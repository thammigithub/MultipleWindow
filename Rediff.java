package RediffMiniProjectPak;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Rediff
{
	static WebDriver driver;
	
	// Launching ChromeBrowser
	
	public static void chromeBrowser()
	{
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\vinay\\eclipse-workspace\\Multiple Window\\Drivers\\chromedriver (1).exe");
		driver =new ChromeDriver();
	}

	// Launching  FirefoxBrowser
	
	public static void firefoxBrowser()
	{
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\vinay\\eclipse-workspace\\Multiple Window\\Drivers\\geckodriver.exe");
		driver =new FirefoxDriver();

	}
	

	public static void main(String[] args) throws InterruptedException
	{
			
		// Selecting The Browser
	
		System.out.println("Select Your Browser: 1.Chrome 2.Firefox");
		Scanner sc = new Scanner(System.in);
		int choice =sc.nextInt();
	
		if(choice == 1) 
		{
			chromeBrowser();
		}
		else
		{
			firefoxBrowser();
		}
		
		// Launching The URL
		
		driver.get("https://www.rediff.com/");
				
		// Maximizing The Window       
				
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		// Click on "Create Account " link
		
		driver.findElement(By.linkText("Create Account")).click();
		
		// Validate the "create rediff mail account" Page 

		String actualTitle= driver.getTitle();
		String expectedTitle = "Rediffmail Free Unlimited Storage";
		
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("validation of webpage is success");
			System.out.println("page title is :"+ expectedTitle);
		}
		else
		{
			System.out.println("validation is failed");
		}
		
		// Total no of links and print the links
		
		List<WebElement> linksize = driver.findElements(By.tagName("a"));		
		int linksCount = linksize.size();
		System.out.println("Total no of links Available: "+linksCount);	
		String[] links= new String[linksCount];		
		System.out.println("List of links Available: ");  
		
		// print all the links from webpage 
		for(int i=0;i<linksCount;i++)
		{
			links[i] = linksize.get(i).getAttribute("href");
			System.out.println(linksize.get(i).getAttribute("href"));
			
		} 
		
		// Click on "terms and conditions " link
		
		driver.findElement(By.linkText("terms and conditions")).click();
		
		//child window will open
		  
		String MainWindow = driver.getWindowHandle();
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1=s1.iterator();
		while(i1.hasNext())
		   {
				String ChildWindow = i1.next();
		  
				//if child window is equal to main window
		       if(!MainWindow.equalsIgnoreCase(ChildWindow))
		       {
		    	   driver.switchTo().window(ChildWindow);
		    	   Thread.sleep(5000);
		    	   
		    	// Validate the "Terms and Conditions" Page 

		   		String actual = driver.getTitle();
		   		Thread.sleep(5000);
		   		System.out.println("The Tital is :" + actual);
		   		String expected = "Rediffmail: Terms and Conditions";
		   		
		   		if(actual.equals(expected))
		   		{
		   			System.out.println("validation of webpage is success");
		   			System.out.println("page title is :"+ expected);
		   		}
		   		else
		   		{
		   			System.out.println("validation is failed");
		   		}
		    	   
		    	   // close child window
		    	   driver.close();
		    	}
		   }
		    	  
		   //switch to main window
		    driver.switchTo().window(MainWindow);
		    
		    // close main window
		    driver.quit();
		
	}
				
}

