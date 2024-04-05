# Team-102-8



## Project Description
TasteBud is an application that teaches beginner cooks how to cook recipes from different cuisines, but are unsure where to start. This project is being developed for the CS 346 course project in Winter 2024. It differs from other similar applications by using a progression learning method where users can learn the basics of cooking a specific cuisine and gradually increase their expertise. It solves the problem of overwhelming online cooking information and recipes, which often assume prior knowledge of cooking and have limited information on each of the steps for preparing a dish. Youtube videos and other social media platforms are also often too fast-paced and vague. Users are searching for a quick & convenient, user friendly, and fun way to learn cooking!

## Essential Product Features
1. Developed for the Android platform
2. Progression story and search
3. Random recipe generator 
4. Select ingredients generator
5. User profile 
6. Information flashcards
7. Transition animations
8. Visuals of cooking steps
9. Post and share pictures of food
10. Dietary restrictions on user profile
11. List of ingredient substitutions
12. Trending and seasonal food page

## Team Members
- Ayush Shah, a248shah@uwaterloo.ca
- Julie Ngo, j33ngo@uwaterloo.ca
- Lavan Nithianandarajah, lnithian@uwaterloo.ca
- Neel Shah, n79shah@uwaterloo.ca

## Project Proposal
https://git.uwaterloo.ca/j33ngo/team-102-8/-/wikis/Project-Proposal

## Release Notes
16-Feb-2023
V1.0.0

- Researched databases and chose MongoDB as our noSQL database to be used for our users in the app
- Created Cluster and Collection to store user data
- Created db in MongoDB
- Searched test user on the Database
- Attempted connecting the DB to MongoDB compass and the app, could not finish as we ran into a few issues documented within the issue itself and the respective development journals 
- Created a variety of high-fidelity Figma diagrams, including the user profile, cuisine progression screen, sign-in page, home screen, menu bar, and flash cards for individual recipes
- Explored different recipe API's and selected the Spoonacular API
- Able to retreive the data when calling the endpoints on Postman

Link to issues list: https://git.uwaterloo.ca/j33ngo/team-102-8/-/issues

- [Taste Bud Installer](./TasteBud.apk)

3-Mar-2023
V1.1.0

- Designed and implemented flashcard to display recipes
- Created the schema for the recipe
- Animation to swtich between instructions
- Navigation of the app from the home screen to recipe
- Loaded recipes to database for each cuisine

Link to issues list: https://git.uwaterloo.ca/j33ngo/team-102-8/-/issues

22-Mar-2023
V1.2.0

- Designed and implemented Progression Screen for Indian Cuisine
- Designed and implemented Random Recipe Geerator
- Sign-up and Sign-in page
- Navigation Bar changes
- Storing Users in database

Link to issues list: https://git.uwaterloo.ca/j33ngo/team-102-8/-/issues

- [Taste Bud Installer](./TasteBud.apk)

5-April-2023
V2.0.0
### Login and Register
- New look for Sign-in/Sign-up page
- Notification banners for invalid emails, weak password, and email already used
- When an account gets created, the Users collection DB gets initialized and set
- User can set their dietary restrictions after registering

### Recipe Road
- New Recipe Roads for India, Italy, China, and America!
- Each Recipe Road is ordered by increasing level of difficulty in star format

### Recipe Roulette
- User can decide to get a random recipe to cook!
- New look for the Recipe Roulette Page

### Home Screen
- New UI for the Home Screen
- Users can see TasteBud’s weekly picks
- Users can continue their Recipe Road journey by clicking the Recipe Road Card on the Home Page

### Select Ingredients Screen
- New UI for the Select Ingredients Screen
- User can select ingredients they have in their fridge to make a recipe with
- If we find a recipe that you can use we send it your way, otherwise display a message recommending changing your selected ingredients

### User
- Users can track their completed recipes, recipes they started, and the total cooking minutes
- When the user completes a recipe, these changes can be seen in the Profile Screen

### Profile
- In the profile screen, users can keep track of their key stats including, cooking minutes and recipes completed
- Users can see their dietary restrictions
- Profile screen can be navigated by clicking on the Account icon in the top right corner of the app

### Other
- New colours and Fonts! Let’s show off our TasteBud Style
- New look for Recipe Details Screen
    - Link to our common substitutions screen 
    - Start Recipe with the check mark
    - Go back and choose new recipe with the “X” mark
- New TasteBud logo design
- Expanded our collection of recipes! 




