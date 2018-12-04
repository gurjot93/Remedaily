# GROUP 2 - REMEDAILY

**Devanshu Srivastava (Leader)** - B00810667  
A student of Master of Applied Computer Science at Dalhousie University with three years of experience of multiple projects. He is a good team player with good interpersonal skills. Languages he worked on are C#, JAVA, C++, and JavaScript. He has also developed several Android projects which covered much of the knowledge and skills of Android Development.
 
**Eugene Shishlannikov** - B00806895  
Master of Applied Computer Science student. Eugene possess BSc in Physics from Technion - Israel Institute of Technology. Eugene possess 3 years of experience in the high and low level programming. He has developed in C/C++ and Python languages.
 
**Gurjot Singh** - B00811724  
Gurjot is pursuing Master of Applied Computer Science at Dalhousie University. He has his bachelor’s in computer science from Guru Gobind Singh Indraprastha University, New Delhi, India. He has 3 years industrial experience in Python, Java, SQL, and Artificial Intelligence technology.
 
**Aditya Gadhvi** - B00809664  
Aditya is pursuing Master of Applied Computer Science at Dalhousie University. He has pursued his bachelor’s in computer engineering from Gujarat Technological University, Gujarat, India. He has created a website as his final engineering project. He is most familiar with Html, Css, JavaScript, Ajax, Jquery, Bootstrap, php and C#. His major interest is in web-development.
 
**Alex Dunn** - B00636250  
In his final semester of the Bachelor of Computer Science, Alex is looking towards a bright future in the IT industry. Most of his knowledge lies within web development but he has some professional experience developing cross platform applications with Xamarin and React-Native.
 
**Deep Prakash Singh** - B00792279  
Deep is pursuing Masters of Applied Computer Science at Dalhousie University. He has an enriched experience in developing software products ranging from lo-fi prototypes to full scale software engineering. He has pursued his bachelor’s in Computer Science from India.

## INTRODUCTION
Remedaily is an Android application which helps to remind users of their daily (weekly, monthly) intake of medicines. This application is mostly targeted at an elderly audience with a focus on accessibility and usability. The user will be able to enter their medical information and prescriptions and the application will send a push notification when it is time for the user to take their inputted medication. We have kept in mind the common isolation of senior citizens while creating Remedaily.

## *PURPOSE*
The purpose of the application is to serve as a medication administration reminder. The primary audience is people aged 55+ and the secondary audience is anyone who has many medications to keep track of.

## *SCOPE*
The application has been designed in such a way that it would be easy to use since the majority of the users will be older people. Thus, this application will act as an assistant that helps the users to remember medication times. The users will also be able to enter personal and prescription details in the application. The users will be reminded of their medications through sound, vibration and even flashlight. The main benefit of using this application is that the users will not need to track times for taking regularly scheduled medicines.        

We have designed our application with keeping in mind the scalability. Hence, we are going to release it on android play store as beta version soon.

## *ENVIRONMENT*
The Remedaily application can be efficiently used in an environment which is calm and noise-free. The noise free environment would allow the users of the application to hear all of the medicine notifications of the application. The interface will be calm and free of clutter to allow the users of the applications to easily see the medicines on the screen. The main environment where the application can be used are homes, hospitals, and at any place which is calm and noise-free.

## *MEDIUM*
The primary medium for using the application is mobile phones, but currently it has been designed just to be used on mobile phones and not on tablets and wearable devices. If in future, the app gets a good recognition and user-base, then we would implement the app for other types of devices as well. The app has been designed only for the Android smartphone users. The other operating system users would not be able to use this app.

The app would use the flashlight and different ringtones to notify the user about the medication. The app is also implemented with an email feature, so that an email can be sent to their family members in the case where the users forget to take their medications.

## *MODE*
The application has been designed for users which are seated on chair or sofa, relaxing at their home, or on-the-go. As Long as the user has their medication on them, which they should, they will be able to use the applciation effectively. If the user’s state of mind is busy, bored or angry, then such users will not enjoy the application. This application is expected to be used when the user receives a notification about their medication, when the user wants to enter medication information, or when they would like to see when they need to administer their next medication.

## *SWOT ANALYSIS*
**Strengths**
- Accessible design
- No interenet required
- Advanced notification system

**Weaknesses**
- Medical industries are slow to adapt to new technologies so external support for our application may not be available
- Android only
- Requires user input

**Opportunities**
- Recommendations from medical industry

**Threats**
- Variable user-base
- Similar applciations exist

## Discussion
In this section, we will discuss what all the technologies that can be used to implement the application.

### *TOOLS*
Android Studio. Android Studio is the official integrated development environment (IDE) for Google's Android operating system, built on JetBrains' IntelliJ IDEA software and designed specifically for Android development. It is available for download on Windows, macOS and Linux based operating systems. We have used this IDE as per university requirements.
 
Git is a version control system for tracking changes in computer files and coordinating work on those files among multiple people. It is primarily used for source-code management in software development, but it can be used to keep track of changes in any set of files. For our project, we have used GITLAB.


### *MODEL-VIEW-CONTROLLER (MVC) FRAMEWORK*
Model-View-Controller (MVC) is an architectural pattern that separates an application into three main logical components: the model, the view, and the controller. Each of these components is built to handle specific development aspects of an application. MVC is one of the most frequently used industry-standard web development frameworks to create scalable and extensible projects.
 
We developed Remedaily’s modules keeping in mind that we don’t violate the MVC framework. As you can see in the above fig, we have modularized our code and optimized it.

### *TESTING*
>“Testing is an infinite process of comparing the invisible to the ambiguous in order to avoid the unthinkable happening to the anonymous.” — James Bach[^fn].

Testing provides developers a gain in confidence on the modules developed and ease in maintaining them. In our project, we have divided the testing into three modules- Unit Testing, Integration Testing, and System Testing. Unit testing was done before the compilation of the unit modules with the main project. Integration Testing was performed after every time the modules were integrated. System Testing provided us the assurance that our application is ready to be merged in the Master branch.

#### *Unit Testing*
It is the first level of software testing where developers test their components after development. The purpose for this testing type is to validate that each module performs as designed. We developed several test cases for each unit. The individual units were java classes, functions, xml modules and database modules. The unit test cases are displayed below.

**Use Case:**  
**Expected Outcome:**  
**Actual Outcome:**  

**Use Case:** Splash Screen  
**Expected Outcome:** The splash screen should open immediately after the application starts.   
**Actual Outcome:** The splash screen opens immediately after the application starts.   

**Use Case:** Hamburger Menu  
**Expected Outcome:** The Hamburger menu should open immediately after sliding the screen or clicking on the hamburger icon and should not affect the current activity present on the screen.   
**Actual Outcome:** The Hamburger menu opens immediately after sliding the screen or clicking on the hamburger icon and does not affect the current activity present on the screen.  

**Use Case:** Add user activity  
**Expected Outcome:** The details entered should be validated and no invalid entries should be accepted from the users.  
**Actual Outcome:** The users receive and prompt error displaying the invalid detail entered by them and are asked to re-enter the value before saving the details.  

**Use Case:** Edit User Activity  
**Expected Outcome:** The activity should display the current details of the user and re-validate them once user requests to edit them.  
**Actual Outcome:** The activity displays the current details of the user and re-validates them once user requests to edit them.

**Use Case:** Add Medicine Activity - Medicine Details  
**Expected Outcome:** The users can enter the medicine details and dosage along with the image for the medicines.  
**Actual Outcome:** The users can easily enter the medicine details and dosage along with the image for the medicines.

**Use Case:** Add Medicine Activity - Calendar Details  
**Expected Outcome:** The users can enter the start and end date for the respective medicines and the end date should not be less than the start date.   
**Actual Outcome:** The users can enter the start and end date for the respective medicines and will be prompted to re-enter the dates if the end date is less than the start date.  

**Use Case:** Add Medicine Activity - Time Duration  
**Expected Outcome:** The time duration should be of two types- daily and weekly. Weekly time duration should display the week details so that user can select a particular day and Daily should display the time which is to be reminded daily.  
**Actual Outcome:** The time duration displays the two types- daily and weekly. “Weekly” time duration displays the week details so that user can select a particular day and “Daily” displays the time which is to be reminded daily.  

**Use Case:** Home Page Activity  
**Expected Outcome:** The Home page should display the schedule of the next medicines to be reminded and the missed medicines which the users did not take in the past. Also, the application should have an add medicine details button which will call the “Add Medicine Activity”  
**Actual Outcome:** The Home page displays the schedule of the next medicines to be reminded and the missed medicines which the users did not take in the past. Also, the application has an add medicine details button which calls the “Add Medicine Activity”  

**Use Case:** Calendar Activity  
**Expected Outcome:** This activity should display all the details of the medicines categorized on the basis of day.  
**Actual Outcome:** This activity displays all the details of the medicines categorized on the basis of day.  

**Use Case:** Settings Activity- Enable/Disable Splash Screen  
**Expected Outcome:** This activity should help the user to enable or disable the splash screen of the application.  
**Actual Outcome:** This activity helps the user to enable or disable the splash screen of the application.  

**Use Case:** Settings Activity - Turn On/Off Notifications  
**Expected Outcome:** This activity should help the user to turn on/off the notifications.  
**Actual Outcome:** This activity helps the user to turn on/off the notifications  

**Use Case:** Settings Activity - Delete User Profile  
**Expected Outcome:** This activity should help the user to delete the user profile.  
**Actual Outcome:** This activity helps the user to delete the user profile.  

**Use Case:** Settings Activity - Change User Details  
**Expected Outcome:** This activity should call the edit user details activity.  
**Actual Outcome:** This activity calls the edit user details activity.  

**Use Case:** Settings Activity - Reset Settings  
**Expected Outcome:** The user should be prompted again before deleting all the data of the application and once user agrees, should delete all the data of the application.  
**Actual Outcome:** The user is prompted again before deleting all the data of the application and once user agrees, deletes all the data of the application.  

**Use Case:** Settings Activity - Delete Medication  
**Expected Outcome:** The user can easily delete all the details of a specific medicine.  
**Actual Outcome:** The user can easily delete all the details of a specific medicine and the changes will be updated in the database.  

**Use Case:** Notification Use Case  
**Expected Outcome:** The application notifications should contain sound, vibration, flashlight and screen flickering. It should work on all the android versions and should handle all the exceptions i.e. if user has a faulty hardware device like vibration, flashlight etc.  
**Actual Outcome:** The application notifications contain sound, vibration, flashlight and screen flickering. It works on all the android versions(Android Oreo and above have a channel) and should handles all the exceptions i.e. if user has a faulty hardware device like vibration, flashlight etc.  

**Use Case:** Database Creation - User  
**Expected Outcome:** Complete Details of the user should be stored in the database. Also, the application should update and delete the stored details.  
**Actual Outcome:** Complete Details of the user are saved in the database. Also, the application will update and delete the stored details.  

**Use Case:** Database Creation - Medicines  
**Expected Outcome:** The medicine table should store have all the details of the medicines along with the time and date. The users can easily modify and delete the details from the database table.  
**Actual Outcome:** The medicine table stores all the details of the medicines along with the time and date. The users can easily modify and delete the details from the database table.  

#### *Integration Testing*
This is the second level of testing after the unit testing. Once the unit testing has been successfully performed, many modules were merged together and then tested together as a group in this category. The purpose of this testing was to expose faults in the interaction between integrated units. The integration test cases are displayed below.

**Use Case:** Add Medicine and Home Activity Integration  
**Expected Outcome:** The add medicine activity details should be displayed on the home page.  
**Actual Outcome:** The add medicine activity details are displayed on the home page.  

**Use Case:** Calendar and Database Integration  
**Expected Outcome:** The calendar should easily display all the details of the medicines stored in the database.  
**Actual Outcome:** The calendar displays all the details stored in the database.  

**Use Case:** Hamburger Integration  
**Expected Outcome:** The Hamburger menu should help the user to call all the modules of the application and should contain all the exceptions.  
**Actual Outcome:** The Hamburger menu will help the user to call all the modules of the application and contains all the exceptions.  

**Use Case:** Add User and Edit User Integration  
**Expected Outcome:** The details stores in the add user activity should be displayed in the edit user activity and the add user activity should be disabled after the user has been added.  
**Actual Outcome:** The details stores in the add user activity are displayed in the edit user activity and the add user activity is disabled after the user has been added.  

**Use Case:** Settings Activity Integration  
**Expected Outcome:** All the functions in the settings activity should easily call the respective activities and functions  
**Actual Outcome:** All the functions in the settings activity easily call the respective activities and functions  

**Use Case:** Notification Integration  
**Expected Outcome:** The notifications should be called from the background functions to remind medicines.  
**Actual Outcome:** The notifications are called from the background functions to remind medicines.  

#### *System Testing*
This is the level of testing where the completely integrated application was tested. The purpose of this testing was to evaluate the complete application as a whole and meets the specified requirements for the users. The system test cases are displayed below.

**Use Case:** Data Entered by Users  
**Expected Outcome:** The data entered by users should easily stored the data should be used by the all the functions present is the application.  
**Actual Outcome:** The data entered by users is easily stored the data will be used by the all the functions present is the application.  

**Use Case:** Medicine Reminder  
**Expected Outcome:** The application should provide the reminder notification for all the medicines at the specified time.  
**Actual Outcome:** The application provides the reminder notification for all the medicines at the specified time.  

**Use Case:** Missed Medication  
**Expected Outcome:** The application should handle the missed medicine notifications.  
**Actual Outcome:** The application stores the data for the missed medicines and the specified functions evaluate the results  

**Use Case:** Stability Check  
**Expected Outcome:** The application should never crash and all the test cases should pass the results.  
**Actual Outcome:** All the exceptions handled and all the bugs were fixed in the application.  

## DATABASE
We have used the [ROOM persistence library](https://developer.android.com/topic/libraries/architecture/room) for constructing the database schema. We have designed two tables for our project. The first one is User table that contains all the user details and the second one is the Med table that contains the data and metadata provided by the User also we have constructed a converter table that helped us in converting primitive data types to objects and vice-versa.

## DESIGN
For our design process, we first designed a low-fidelity prototype on a whiteboard. After iterating on that we designed wireframes for each screen using Adobe XD. One feature of XD allows you to create an interactive prototype using your wireframes, this allowed us to define an interactive clickstream. The aforementioned interactive prototype is accessible [here](https://xd.adobe.com/view/5be3ca48-e474-4606-60a2-d7cdbf8ddbb1-2dd2/).

## FEATURE ROADMAP
In our project proposal we defined the three sets of the functionalities to be implemented in course of the application development - the minimum, the expected, and the bonus sets. We have assigned two or three features to each member of the team and successfully implemented all of them, hence achieving the implementation of functionalities’ sets. By the time we have implemented the minimum functionalities, the underlying base structure of the application was completed, so passing to the expected and the bonus functionalities implementation was smooth.
 
The set of the basic functionalities consists of the following two:
- ✔️ User can set his and medicine details
- ✔️ User can set the medicine schedule.

The set of the expected functionalities is comprised of the following:
- ❌ Push notification for the medication administration reminder
- ✔️ Reminder for the pill through sound, vibration, and flashlight and screen flickering
- ✔️ Medication expiry notification (users will be notified ahead of the medication expiry date). Note that the users need to input expiry date manually

The set of the bonus functionalities contains the following:
- ✔️ Add family notification of missed dose via SMS and phone call API (if one of the family members misses the medicine then the family will be notified)
- ❌ Establishing Doctor and patient relationship by means of the external server
- ✔️ Add doctor’s reports
- ✔️ Add medicine expiry notification via email for that particular user
- ❌ Add medicine cross reaction track. There are medications that are incompatible. For example, pain relievers and anti-fever medicine may both contain Paracetamol which is liver-toxic

## REFERENCES
[1]https://www.testingexcellence.com/best-software-testing-quotes/  
[2]http://softwaretestingfundamentals.com/wp-content/uploads/2010/12/system_testing.jpg  
[3]https://www.flaticon.com/free-icon/hands_838597#term=medical&page=1&position=46  
[4]https://developer.android.com/topic/libraries/architecture/room  



























