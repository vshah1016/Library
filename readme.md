# Algorithm

We have 10 actions total my program needs to perform as well as managing the storage. Secondly my program needs to support a GUI for some outputs for the EC.

```text
0. exit
1. books by author
2. books by title
3. loan book
4. return book
5. borrowed books
6. overdue books
7. total amount books
8. add book
9. remove book
10. all books
```

This is the menu of items that I need in my program. The way I did this was with a Book class and a Library Class, they used a Date class as a helper and a Client Class.

## Book Class
> * title
> * author
> * borrower (optional)
> * borrowed (optional - *date*)

the *isBorrowed* returns true if the book is borrowed.

*borrowedOverTwoWeeks* returns true if the book is overdue.

*loanOut* loans the book out.

*returnBook* returns the book if it is checked out.

*toList* is a helper method I made for the GUI. It makes the book fields into a ArrayList of strings so i can put it in a table.

## Library Class

Maps the stuff in the requirements to a method that can be referenced later.

> * books
> * currentDate

borrowed() returns a list of all the returned books

overdue() returns a list of all of the overdue books

totalbooks() returns the int value of how many books in the library at the moment

addbook() simply adds a book to the books list

removeByTitle() removes any books with a matching title

## Date Class

> * month
> * day
> * year
> * monthAndDay

In the constructor it checks if the date is valid by checking if the year is leap. If it is leap, being divisible by 4 except 100 except 400. Then it makes a hashmap of each month and its corresponding days in it depending if it is leap.

It then error checks the date to make sure it is a thing.

isAfter just checks if *this* is after the given date.

ifAfterBy returns the amount of days that the date given is behind *this* date. 

toString() is an overridden method because I need to display the date on the GUI.

## Client Class

> * Scanner

gatherInput() will just gather the current input, this is good because I use this in all my programs and never experience issues with scanner.

isRunAgain() will just check if the user entered 0 and then know to end the program.

runAgain() presents the list of options for the library and error checks the input.

## Main Class

This is where all of the parts of the program come together. What we do is make our client class and setup our while loop that is going to run. 

In the main method first we gather the current date from the user, this info is used to make a Library object. Then the menu will pop up prompting the user to choose an option.
Once the user chooses an option the loop will start, in the loop there is simply one statement, a switch statement. There are 10 cases, one for each option.

Cases `1, 2, 5, 6, 10` have GUI attached to them so they have the same skeleton basically, what they do is just use the methods in Library and just display them in the GUI.

Case `3`: I simply ask for the name of the book that needs to be lended. Then I search if there is a book for that name that is not borrowed. If there is we continue otherwise you will be forwarded to the menu. Continuing, the program figures out the borrower and the date borrowed. It assigned that to the object and returns to the menu.

Case `4`: Very simple I just use a Java 8 Stream API filter to find all the books with the same title. Once I find those I have to check if those books are checked out, if they are they throw a NotCheckedOut error. Otherwise the book gets returned using the book method.

Case `7, 8, 9`: just take the inputs needed and use the skeleton methods to execute their mission.
