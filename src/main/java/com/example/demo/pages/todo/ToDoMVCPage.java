package com.example.demo.pages.todo;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.annotations.Page;
import com.example.demo.pages.Base;


@Page
public class ToDoMVCPage extends Base{
	
	@Value("${application.url}")
	String applicationURL;
	
	Logger LOG = LoggerFactory.getLogger(ToDoMVCPage.class);
	
	int existingTaskCount = 0;
	
	@FindBy(xpath="//input[@placeholder='What needs to be done?']")
	private WebElement textarea;
	
	@FindBy(xpath="//ul[@class='filters']//a[@class='All']")
	private WebElement filterAll;
	
	@FindBy(xpath="//ul[@class='filters']//a[text()='Active']")
	private WebElement filterActive;
	
	@FindBy(xpath="//ul[@class='filters']//a[text()='Completed']")
	private WebElement filterCompleted;
	
	@FindBy(xpath="//span[@class='todo-count']/strong")
	private WebElement existingCount;
	
	@FindBy(xpath="//div[@class='view'][1]/label")
	private WebElement newtask;
	
	@FindBy(xpath="//button[@class='clear-completed']")
	private WebElement clearCompleted;
	
	public String getNewTaskAdded()
	{
		String webElement = "//li[@class='todo'][" + getexistingTaskCount() + "]//label";
		return this.driver.findElement(By.xpath(webElement)).getAttribute("innerHTML");
		 
		
	}
	
	public int getexistingTaskCount()
	{
		int count = Integer.parseInt(this.existingCount.getAttribute("innerHTML"));
		LOG.info("Existing task Count : {}", count);
		return count;
		
	}
	
	public void deleteLatestTaskAdded()
	{
		String webElement = "//li[@class='todo'][" + getexistingTaskCount() + "]/div/input";
		this.driver.findElement(By.xpath(webElement)).click();
		this.wait.until(d -> this.clearCompleted.isDisplayed());
		this.clearCompleted.click();
	}
	
	public void goTo()
	{
		
		this.driver.get(applicationURL);
		this.driver.manage().window().maximize();
		this.isAt();
		this.existingTaskCount = this.getexistingTaskCount();
		LOG.info("Navigated to the url {}",applicationURL);
		SessionId session = ((ChromeDriver)this.driver).getSessionId();
		System.out.println("Session id: " + session.toString());
	}
	
	public void enterToDoTask(String toDoTask)
	{
		int taskCount =this.getexistingTaskCount();
		this.textarea.sendKeys(toDoTask);
		this.textarea.sendKeys(Keys.ENTER);
		LOG.info("Entered the task : {}", toDoTask);
		this.wait.until(d -> this.getexistingTaskCount()> taskCount);
	}

	@Override
	public boolean isAt() {
		// TODO Auto-generated method stub
		return this.wait.until(d -> this.textarea.isDisplayed());
	}
}
