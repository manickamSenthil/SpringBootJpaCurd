# SpringBootJpaCurd

There are two roles

admin (ALL rights)
user (ALL rights except to update the address)
admin cridencial user name : admin password : admin

user cridencial user name : user, password : user

H2 database: http://localhost:8080/h2-console/,
 JDBC URL : jdbc:h2:mem:tcs,
 username : tcs,
 password :tcstest

GET getting person details : http://localhost:8080/api/v1/persons 
getting single person detail : http://localhost:8080/api/v1/persons/{personid}
getting person detail using fname and lname : http://localhost:8080/api/v1/persons/bothname?firstname=senthil&lastname=manickam 
getting person detail using fname or lname : http://localhost:8080/api/v1/persons/anyname/manickam
getting person detail using fname : http://localhost:8080/api/v1/persons/firstname/manickam 
getting person detail using lname : http://localhost:8080/api/v1/persons/lastname/lastname

getting single pet detail : http://localhost:8080/api/v1/pets/{petid}
getting pet details: http://localhost:8080/api/v1/pets


POST 
inserting new person : http://localhost:8080/api/v1/persons 
{
        "firstName": "senthil",
        "lastName": "manickam",
        "dob": "12/12/1993",
        "address": "madurai"
}


inserting new pet : http://localhost:8080/api/v1/pets
{ "petName": "cat", "petAge": "2" }

PUT update the person details : http://localhost:8080/api/v1/persons/{personid} 
{ "firstName": "senthil", "lastName": "manickam", "dob": "12/12/1995" }

update the pet : http://localhost:8080/api/v1/pets/{petid}
 { "petName": "Tom", "petAge": "9" }

 
DELETE 
http://localhost:8080/api/v1/persons/{personid} 
http://localhost:8080/api/v1/pets/{petid}