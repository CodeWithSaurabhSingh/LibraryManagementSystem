# LibraryManagementSystem

Requirements
1.Springboot
2 MySql Database  


Assumptions 
1. Manager have UserId and Password as
	UserId- "manager"
	Password - "Password"
2. Manager have authority to add and delete book in the library.
3. Started seassion must be closed by Manager after used.
4. User Registration must be done with distinct EmailId and ContactNo.
5. User get only Library card No after Registerd Succesfully.
6. List of Book can be shown by all Users.
7. User can Issue(borrow) book only nonIssued book.
8. User can Return(submit) book only Issued book.
9. User can Issue(borrow) any number of books.
10.User should Return the book within 30 days otherwise Charged by Rs100/per extra day after 30 days.

Api Description 

Public Apis

1.Use for New User Registration to Get Library card Number.

POST /library/user/add HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 0e8a1b32-09c2-c529-34c6-d9eb516cc479

{
	"userName" :"Abcdef abc" ,
	"userEmailId" : "Abcd.ef12@gmail.com",
	"userContactNo" : 0000000000
}

2.Use for get all books List.

GET /library/book/all-books HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: 5a5d6845-4810-6879-d169-8e7fa0ca218b

3. Issue book to a User by Library card.

GET /library/book/issue?bookid=1&amp;libCardNo=Abc0000000000 HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: 6ca5a1b8-64a1-5123-5504-08681efc57de

4. Submit book by a User by Library card.

GET /library/book/submit?libCardNo=Abc0000000000&amp;bookid=1 HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: 36109dc8-8a23-179c-f52c-a693d21fa854

5. User Details By Library Card Number with issued book List.

GET /library/user/getUserDetails?libCardNo=Abc0000000000 HTTP/1.1
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: b86ea04c-6214-e4bd-285b-d0f1614f209a

Secured Apis
	
	Only Authenticated By Manager

1. Add a New Book 

POST /library/book/add HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Basic bWFuYWdlcjpQYXNzd29yZA==
Cache-Control: no-cache
Postman-Token: 8f5cd151-e0a0-badb-c340-e6632b463761

{
	"bookName" :"Gaban" ,
	"autherName" : "Munsi Prem Chand"
}

2.Delete a book Data by bookId.

DELETE /library/book/delete/1 HTTP/1.1
Host: localhost:8080
Authorization: Basic bWFuYWdlcjpQYXNzd29yZA==
Cache-Control: no-cache
Postman-Token: 983d5863-0025-d007-c08e-1722db2ba0c3
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW
