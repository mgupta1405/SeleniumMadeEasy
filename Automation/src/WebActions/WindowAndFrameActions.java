package WebActions;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WindowAndFrameActions {
	
	WebDriver driver;
	FindWebElement findElement;
	
	public WindowAndFrameActions(WebDriver driver) {
		this.driver = driver;
		findElement = new FindWebElement(this.driver);
	}
	
	
	public void switchToWindowUsingExactTitle(String title) throws Exception {
		try {
			Set<String> windows = driver.getWindowHandles();
			Boolean windowFound = false;
			if(windows.size() > 0) {
				for(String window : windows) {
					if(window.equals(title)) {
						driver.switchTo().window(window);
						windowFound = true;
						break;
					}
				}
				if(!windowFound) {
					throw new NoSuchWindowException("Browser window with title " + title + " is not present");
				}
			}
			else {
				throw new Exception("No browser window is present");
			}
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		
	}
	

	public void switchToWindowContainingTextUsingTitle(String title) throws Exception {
		try {
			Set<String> windows = driver.getWindowHandles();
			Boolean windowFound = false;
			if(windows.size() > 0) {
				for(String window : windows) {
					if(window.contains(title)) {
						driver.switchTo().window(window);
						windowFound = true;
						break;
					}
				}
				if(!windowFound) {
					throw new NoSuchWindowException("Browser window containing title " + title + " is not present");
				}
			}
			else {
				throw new Exception("No browser window is present");
			}
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	
	public void switchToWindowUsingIndex(int index) throws Exception {
		try {
			Set<String> windows = driver.getWindowHandles();
			String window;
			if(windows.size() > 0) {
				if(windows.size() >= index && index > 0) {
					for(int i = 0; i < windows.size(); i++) {
						window = driver.getTitle();
						if(i == (index-1)) {
							driver.switchTo().window(window);
							break;
						}		
					}
				}
				else {
					throw new NoSuchWindowException("Index: " + index + " given is out of range");
				}
			}
			else {
				throw new Exception("No browser window is present");
			}
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	
	public Set<String> getAllWindowsTitles() throws Exception {
		Set<String> windows;
		try {
			windows = driver.getWindowHandles();
			if(windows.size() <= 0) {
				throw new Exception("No browser window is present");
			}
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
		return windows;
	}
	
	
	public void switchToFrameUsingIndex(int index) throws Exception {
		try {
			driver.switchTo().frame(index-1);
		}
		catch(NoSuchFrameException e) {
			throw new NoSuchFrameException("Frame with index: " + index + " is not present");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	public void switchToFrameUsingFrameName(String frameName) throws Exception {
		try {
			driver.switchTo().frame(frameName);
		}
		catch(NoSuchFrameException e) {
			throw new NoSuchFrameException("Frame with name: " + frameName + " is not present");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	public void switchToFrameUsingBy(By byLocator) throws Exception {
		try {
			WebElement element = findElement.findWebElement(byLocator);
			driver.switchTo().frame(element);
		}
		catch(NoSuchFrameException e) {
			throw new NoSuchFrameException("Frame with locator " + byLocator + " is not present");
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	public void switchToFrameUsingWebElement(WebElement elementLocator) throws Exception {
		try {
			driver.switchTo().frame(elementLocator);
		}
		catch(NoSuchFrameException e) {
			throw new NoSuchFrameException(e.getCause().toString());
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	public void switchFromFrame() throws Exception {
		try {
			driver.switchTo().defaultContent();
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	public void switchFromFrameToParent() throws Exception {
		try {
			driver.switchTo().parentFrame();
		}
		catch(Exception e) {
			throw new Exception(e.getCause().toString());
		}
	}
	
	public void maximizeBrowserWindow() {
		driver.manage().window().maximize();
	}
	
	public void setBrowserSize(int x, int y) {
		Dimension d = new Dimension(x,y);
		driver.manage().window().setSize(d);
	}
}
