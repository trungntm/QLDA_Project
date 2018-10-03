# QLDA_Project
Project QLDA Spring Boot

#Environment
Backend : Using Spring boot. 
+ required IntelliJ IDEA or Eclipse Java EE (must pluggin tool suite)
+ database has been use in this project is PosgreSQL. Beside that, you can use MySQL, SQL Server,...
Frontend : Angular 

# Library support to handler token jn Angular is '@auth0/angular-jwt'.
Let's import library like : import { JwtHelperService } from '@auth0/angular-jwt'
+ if you have a token string, you wanna decode it, let's use : const decodedToken = this.jwtHelper.decodeToken(token);
+ And you wanna check it is expired yet, let's use : const isExpired = this.jwtHelper.isTokenExpired(token);

# /helper/auth.guard.ts
To define a class to check activation token, name is 'AuthGuard', it's implement from 'canActivate';.
It define a function name is 'canActivate', one token have been active, can redirect to other pages in routing config, else you must redirect login page.
It also define other function name is 'canActivateChild', it help child url of base url have been define in canActivate
# /helper/error-interceptor.ts
Verify token in angular. If you have error status code is 401 or 403, you will be notify. 

