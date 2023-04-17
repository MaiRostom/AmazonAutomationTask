Feature: Filter and Sort All Video Games

  Scenario: User filters and sorts all video games
    Given the user clicks on the 'All' button in the navigation bar
    And the user selects 'See All'
    And the user selects 'Video Games' and then selects 'All Video Games'
    When the user applies the 'Free Shipping' filter and adds the 'Condition: New' filter
    Then the user selects the 'Sort By' list and selects a type of sorting
    And the user adds all products that are less than 15000 EGP
    And the user fills in the shipping address and payment method details as follows:
      | Full Name      | Phone        | Street Address | Building Number | City      | District   | Land Mark |
      | Mai Rostom     | 01145640184  | Tarablos       | 100             | NasrCity  | almanteqah | elsalab   |
