package practise;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class datepicker {

	public static void main(String[] args) throws InterruptedException {
		String expectedDate = collectDateFromUserAsInput();
		 WebDriver driver=new ChromeDriver();
		 Thread.sleep(2000);
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get("https://jqueryui.com/datepicker/");
		
		 selectedRequiredDateInCalender(driver,expectedDate);
		 
		 
		 
	}
	public static String collectDateFromUserAsInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter date in this format dd/MM/yyyy:");  // "25/11/2027"
		String expectedDate = scanner.nextLine();
		scanner.close();
		return expectedDate;
	}
		 public static void selectedRequiredDateInCalender(WebDriver driver,String expectedDate) throws InterruptedException {
			
			String expectedMonth = getMonth(expectedDate);
			System.out.println(expectedMonth);
			String expectedYear=getYear( expectedDate);
			System.out.println(expectedYear);
			String expectedDay=getDay(expectedDate);
		 System.out.println(expectedDay);
		 
		 
		 WebElement fra = driver.findElement(By.className("demo-frame"));
		 
		 driver.switchTo().frame(fra);
		 Thread.sleep(2000);
		 driver.findElement(By.id("datepicker")).click();
		 
		 
		 String currentMonth = driver.findElement(By.className("ui-datepicker-month")) .getText();
		 
		 String currentYear = driver.findElement(By.className("ui-datepicker-year")) .getText();
		 
		 
		
		 
		 while(!(currentMonth.equalsIgnoreCase(expectedMonth) && currentYear.equals(expectedYear))) {
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//a[@title=\"Next\"]")).click();
			 
			 
		  currentMonth = driver.findElement(By.className("ui-datepicker-month")) .getText();
			 
			  currentYear = driver.findElement(By.className("ui-datepicker-year")) .getText();
		 }
		 Thread.sleep(2000);
		 String xPathText="//td[@data-handler=\"selectDay\"]/a[text()=\'"+expectedDay+"']";
		 
		 WebElement expectedDayEle= driver.findElement(By.xpath(xPathText));
		 expectedDayEle.click();

		 
		 
		 }

	public static String getMonth(String userEnterDate) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld = LocalDate.parse(userEnterDate, dtf);
		
		 Month month=ld.getMonth();
		String expectedMonth=month.toString();
	 return expectedMonth;
	}
	
	
public static String getYear(String userEnterDate) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld = LocalDate.parse(userEnterDate, dtf);
		
		 int year=ld.getYear();
		 String expectedYear1=String.valueOf(year);
		 
		 
	 return expectedYear1;
	}

public static String getDay(String userEnterDate) {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate ld = LocalDate.parse(userEnterDate, dtf);
	
	 int day=ld.getDayOfMonth();
	 String expectedDay=String.valueOf(day);
	 expectedDay=null;
	 if(day<10) 
		 expectedDay="0"+day;
	 else
		 expectedDay=String.valueOf(day); 
	 
    return  expectedDay;
}



	
	
}
