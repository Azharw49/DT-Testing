Feature: Task Management in todos
@flow
  Scenario Outline: Verify adding tasks to the todos
    Given I am on todos home page
    When I enter "<task>" as a keyword
    Then I should see the task on the todos list
    

    Examples: 
      | task                 | 
      | Learn Selenium       |   
      | Learn Java           |  
      | Learn Cucumber       | 
      
      
@flow     
   Scenario: Verify the number  
     Given I am on todos home page
     When I enter "Learn Python" as a keyword
     And I delete the same task
     Then I should not be able to see the task entered
    