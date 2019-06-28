# code-challenge
# App Design / Choices
When users open the trivia app, they will see the Welcome message on the top, followed by the first question. <br />
Since the app has a series of ture/false questions, each question has two choices to choose from, either True or False. <br />
When  a choice is made, users then click the Confirm button to submit the answer. <br/>
If there is no answer selected, a message is displayed to inform the user to select an answer.<br/>
If there is an answer is selected, it will let the user know if the selected answer is correct.<br/>
And the user are not able to redo the question.<br/>
Users can swipe right to the next question, or swipe left to the previous question.
# Object Oriented Analysis and Design
MainActity: LinearLayout, ViewPager.
ViewPager single row components
1) TextView: displays the welcome message, visible when the ViewPager position is 0, invisible otherwise.
2) TextView: displays the question
3) RadioGroup
   RadioButton: True
   RadioButton: False
   Button: to check the answer
   TextView: displays whether the selected answer is correct or wrong
You can swipe right to the next position of the ViewPager, and swipe left to the previous position of the ViewPager.
Data model class: constructor, getters setters for the API response.
Restful API: Volley is used to get the API response.
When the button is clicked, it checks if a RadioButton is clicked, if not it displays a Toast, and you have to select a RadioButton.<br/>
Click the button again, it will save either "Ture" or "False" to a String called answer based on the RadioButton selected, and compare with the correct_answer we get from the API response.<br />
If there is a match, TextView displays "Correct", if not, the TextView displays "Wrong". <br />
The RadioButtons and Button are then disabled. <br />
The ViewPager goes to the next position after a 5 second delay.
# Local environment setup instructions
The app is written in Java in Android Atudio. <br />
Android Studio is needed to run the app. <br />
The minSdkVersion is 24.<br />
An emulator or Android phone running Android 7.0 Nugget or above is required to run the app.
