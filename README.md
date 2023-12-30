# AAHAR-ENDUSER

## Overview

AAHAR-ENDUSER is an application designed to address a major problem faced by college students and migrant workers. The app simplifies the user interface, ensuring that even those with limited literacy can navigate and utilize its features effectively. Aimed at mitigating economic losses caused by a lack of information, the application tracks user presence, allowing them to pay only when they eat.

## Features

1. **Register and Login:**
   - **Sign UpActivity:**
     - Collects user information during sign-up, including username, email, password, mobile number, and user image.
     - Implements OTP verification for added security.
   - **Login Activity:**
     - Allows login with email and password.
     - Provides a Google Sign-in option.

2. **MainActivity:**
   - Features a navigation drawer and fragments for easy navigation.
   - Contains 5 main fragments: Home, Explore, Attendance, Calendar, and Notification.


   - **Home Fragment:**
     - Allows users to add a new mess for the first time.
     - Displays details of the joined mess, including name, owner, address, contact information, reviews, complaints, and rating.
     - Provides a payment button for users to pay mess fees.
       
     - **Activity Mess Details:**
       - Allows users to control mess owners through reviews and ratings.

   - **Payment Activity:**
     - Provides two payment options: Razorpay (test mode) and Direct UPI payment.

   - **Reviews Activity:**
     - Displays reviews from other users.
     - Allows users to submit their own reviews and edit previous ones.

   - **Rating Activity:**
     - Allows users to submit ratings and view overall app ratings.
     - Provides the option to edit user ratings.

3. **Explore Fragment:**
   - Displays all registered messes with their details.
   - Allows users to join a mess after reviewing details.

4. **Attendance Fragment:**
   - Enables mess owners to count, check, and authenticate users.
   - Initiates attendance sessions and issues tickets to users upon verification.

5. **Calendar Fragment:**
   - Tracks user attendance, displaying green for present and red for absent days.
   - Shows the total count of days in a given month.

6. **Notification Fragment:**
   - Provides notifications for user actions, such as joining a mess, adding reviews, rating, and paying mess fees.

MainActivity             |  Home page           |  Payment page           
:-------------------------:|:-------------------------:|:-------------------------:
![MainActivity](https://github.com/Ameya2k22/Images/blob/main/MainActivity.jpeg)  |  ![User Ticket](https://github.com/Ameya2k22/Images/blob/main/user_ticket.jpeg)  |  ![Payment Gateway](https://github.com/Ameya2k22/Images/blob/main/Payment%20Gateway.jpeg)

Attendance Page             |  Calendar page           |  Day page           
:-------------------------:|:-------------------------:|:-------------------------:
![Attendance Fragment](https://github.com/Ameya2k22/Images/blob/main/Attendance%20Activity.jpeg)  |  ![Calendar Fragment](https://github.com/Ameya2k22/Images/blob/main/Calendar.jpeg) |  ![Present Days](https://github.com/Ameya2k22/Images/blob/main/Calendar_present.jpeg)


## Getting Started

To explore AAHAR-ENDUSER:

1. Clone the repository:
    ```bash
    git clone https://github.com/Ameya2k22/AAHAR-ENDUSER.git
    ```

2. Open the project in your preferred IDE.

3. Build and run the application on an emulator or a physical device.

## Contributing

If you're interested in contributing to AAHAR-ENDUSER, feel free to fork the repository and submit a pull request. Contributions of all kinds are welcome!

## Issues

If you encounter any issues or have suggestions for improvement, please [create an issue](https://github.com/Ameya2k22/AAHAR-ENDUSER/issues).

## Acknowledgments

Special thanks to [Ameya2k22](https://github.com/Ameya2k22) for the excellent work and contribution to the project.

Enjoy using AAHAR-ENDUSER! 🍽️📱
