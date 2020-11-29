Feature: End to End tests for Online Book store API

Description : The purpose of this tests are to cover Authorized user is able to add and remove a book in list/cart 

Background: User generates token for Authorisation
Given I am an authorized user

Scenario: the Authorized user can Add a book.
Given A list of books are available
When I add a book to my reading list
Then the book is added

Scenario: the Authorized user can Remove a book.
Given A list of books are available
When I add a book to my reading list
When I remove a book from my reading list
Then the book is removed
