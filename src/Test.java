import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import htmlElement.ListWebElements;
import htmlElement.ElementStatus;
import htmlElement.ListElementStatus;
import htmlElement.WebElements;
import components.WebdriverCommand;
public class Test {
	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/ConsultingAgency/");
		//WebElements elem = new WebElements(1,"index:txtUsername", "text", null);
		WebElement usr = driver.findElement(By.id("index:txtUsername"));
		WebElement pwd = driver.findElement(By.id("index:txtPasswords"));
		WebElement sbm = driver.findElement(By.id("index:btnSubmit"));
		usr.sendKeys("admin");
		pwd.sendKeys("admin");
		try {
			//sbm.click();
			System.out.println("start");
			WebElement w = driver.findElement(By.xpath("/html/body/form[@id='index']/table/tbody/tr[3]/td[2]/input[@id='index:btnReset']"));
			
			w.sendKeys(Keys.ENTER);
			//String value = driver.getTitle();
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
