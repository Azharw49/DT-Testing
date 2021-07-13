package com.example.demo.bdd.steps;

import java.util.List;
import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.example.demo.annotations.LazyAutowired;
import com.example.demo.pages.todo.ToDoMVCPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
public class ToDoMVCSteps {
	
	String taskName = "";
	
	@LazyAutowired
    private ToDoMVCPage todosPage;
	
	@Given("I am on todos home page")
	public void loadHomePage() {
		todosPage.goTo();
		todosPage.isAt();
	}
	@When("I enter {string} as a keyword")
	public void enterTask(String task) {
		this.taskName = task;
		todosPage.enterToDoTask(task);
	}
	
	@Then("I should see the task on the todos list")
	public void validateTaskOnTheList() {
	    Assert.assertEquals(todosPage.getNewTaskAdded(), taskName);
	}
	
	@When("I delete the same task")
	public void deleteSameTask() {
		todosPage.deleteLatestTaskAdded();
	}
	@Then("I should not be able to see the task entered")
	public void verifyTaskDeletion() {
		Assert.assertNotEquals(todosPage.getNewTaskAdded(), taskName);
	}
	
	@Then("I should  be able to see the filters")
    public void filterValidation(List<String> data) {
		SoftAssert softAssert = new SoftAssert();
		for(int i=1;i<data.size();i++)
		{		
			softAssert.assertEquals(todosPage.getFilterButton(data.get(i)),data.get(i));			
		}
	        
		softAssert.assertAll(); 
        
    }

	


}
