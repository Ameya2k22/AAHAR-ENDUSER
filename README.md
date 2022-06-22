# AAHAR-ENDUSER
Application for Endusers

This application is a solution to a major problem that all students in colleges and other migrant workers are facing.
We have kept our UI so simple that even illiterate people can use our app.
Because of a lack of information, they may be facing economic loss. This application basically tracks presence of users and they have to pay only when they eat so very useful.
This application provides the following major functionalities to end users:
1. Register and Login
1.Sign UpActivity:
During the sign-up process, we collected the following information: username, email, password, mobile number, and user image. We have also implemented OTP verification successfully to ensure no unauthorised people can enter our app.
2.Login Activity:
In Login Activity, we have implemented login with email and password as well as providing a Google Sign in option.



MainActivity.
After authentication, the user can enter the main activity. So we have implemented a navigation drawer and fragments for easy rendering in our app.
The main activity mainly consists of 5 fragments; they are: home fragment, explore fragment, attendance fragment, calendar fragment, and notification fragment.
![alt text](https://github.com/Ameya2k22/Images/blob/main/MainActivity.jpeg)

1) Home Fragment:
In the case of an end user, the home fragment appears for the first time when adding a new mess. There is an option to add a mess for the first time. After successfully entering into any mess, the add option will disappear and all the details of the mess that user joined will start to appear there.Mess information includes: mess name, mess owner name, mess address, mess mobile number and email, mess reviews and complaints, and mess rating, which will appear on the home fragment.
The Home fragment also provides a way to pay via a button from which a new Payment Activity opens from which the user can pay mess fees. (The user will be unable to attend sessions in the attendance fragment until the fee is successfully paid.)
![alt text](https://github.com/Ameya2k22/Images/blob/main/user_ticket.jpeg)
   
1)Activity Mess Details:
  From Mess Detail Activity also provides a way to control mess owners through reviews and ratings of mess.
  
  
2) Payment Activity:
  Payment activity provides two ways for payment to users. One is the Razor pay (only available in test mode) option, from which users can use UPI, NetBanking, and Card Payment, while the other is Direct UPI payment (this option checks whether the user has any UPI payment app already or not).
![alt text](https://github.com/Ameya2k22/Images/blob/main/Payment%20Gateway.jpeg)

3)Reviews Activity:
  By clicking on the button Reviews and complaints, the user will be navigated to the Reviews activity where he/she can see reviews of all other users. Also, there is an option for giving their own reviews. They can also edit their previous reviews about the mess.


4)Rating Activity:
  By clicking on the rating button, the user will be navigated to the rating activity where he/she can give his/her rating, see the overall rating of the app, as well as edit their own rating at any time.



2) Explore Fragment:
Explore Fragment will show all registered messes in recyclerview with their minor details (rating, name, and address). After clicking on a particular mess, the user will reach the mess detail activity. From the Mess Detail activity, users can read reviews and can see the rating of that particular mess, so they can take a decision to enter that mess or not. There is a join button in the mess details activity from which a user can join that particular mess.
 
 
 
3. Attendance Fragment:
The Attendance fragment is useful for the Mess Owner to count, check, and authenticate users. When the mess owner initiates an attendance session from its app attendance cardview with the day, date, and time, and the user clicks on the Yes button, the count increases automatically and the student receives a ticket for entering the mess.
![Alt text](https://github.com/Ameya2k22/Images/blob/main/Attendance%20Activity.jpeg)





4. Calendar Fragment:
It is now important to track a user's attendance so that they can pay when they arrive and eat in the mess. It shows a green coloured date when the user was present to eat in that particular mess and shows red if he/she was absent for that day. In addition, the total days count in a given month is displayed to the user so that he can keep track of how many days remain to repay the mess fees and continue membership.
![alt text](https://github.com/Ameya2k22/Images/blob/main/Calendar.jpeg)
![alt text](https://github.com/Ameya2k22/Images/blob/main/Calendar_present.jpeg)



5. Notification Fragment:
All notifications when a user joins the mess, adds reviews, rates, and pays mess fees.
