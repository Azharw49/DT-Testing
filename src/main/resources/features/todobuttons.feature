Feature: Validate Filter buttons in todos

@filterbutton     
   Scenario: Verify filter buttons visible upon on task added  
     Given I am on todos home page
     When I enter "Go To Gym" as a keyword
     Then I should  be able to see the filters
     | Filters   |
     | All       |
     | Actives    |
     | Completeds |
      