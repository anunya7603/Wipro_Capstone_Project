Feature: Ecommerce End To End Flow

Scenario: Complete ecommerce workflow

Given user launches browser
When user logs into application
And user searches product
And user applies filter
And user adds products to cart
And user removes one product
Then validate total amount
And proceed to checkout
And user logout


