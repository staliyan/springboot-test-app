# springboot-app
 
## Task Description
You should be able to start the application by executing com.sample.SampleServerApplicantTestApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoint(s).

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring Boot
* REST
* Maven

Assumptions:
-the payment is monthly, then the monthly rate should be used.
-per month annuity is calculated as below
	n = duration(in months)
	r = rate
	p = principal
	
	[r*p(1+r)^n]/[(1+r)^n]