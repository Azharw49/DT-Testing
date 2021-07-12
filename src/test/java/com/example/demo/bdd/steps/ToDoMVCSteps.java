package com.example.demo.bdd.steps;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.testng.Assert;

import com.example.demo.annotations.LazyAutowired;
import com.example.demo.pages.todo.ToDoMVCPage;

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
	public void i_am_on_todos_home_page() {
		todosPage.goTo();
		todosPage.isAt();
	}
	@When("I enter {string} as a keyword")
	public void i_enter_as_a_keyword(String task) {
		this.taskName = task;
		todosPage.enterToDoTask(task);
	}
	
	@Then("I should see the task on the todos list")
	public void i_should_see_the_task_on_the_todos_list() {
	    Assert.assertEquals(todosPage.getNewTaskAdded(), taskName);
	}
	
	@When("I delete the same task")
	public void i_delete_the_same_task() {
		todosPage.deleteLatestTaskAdded();
	}
	@Then("I should not be able to see the task entered")
	public void i_should_not_be_able_to_see_the_task_entered() {
		Assert.assertNotEquals(todosPage.getNewTaskAdded(), taskName);
	}

	


}
