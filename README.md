## Films app

[apk file](/app-debug.apk)

This app demonstrates the following views and techniques:

* Retrofit - to make api calls to an HTTP web service
* Moshi - which handles the deserialization of the returned JSON to Kotlin data objects 
* OkHttp - to logging
* Glide - to load and cache images by URL.
* Recycler View - with Diffutils for correct display of elements when they are changed
* Data Binding in XML files
* Binding Adapters
* MVP architectural pattern
* Single activity pattern

It leverages the following components from the Jetpack library:
* Navigation - with the SafeArgs plugin for parameter passing between fragments
* ViewModel - to save the state when the fragment is recreated and when the fragment is overlapped

## MVP
* Model is responsible for handling all business logic, communication with backend server and database operations.
* The View is responsible for displaying data to a user in the form of UI screens.
* The Presenter is acted as a middleman between View and Model. It basically retrieves data from Model and returns it to View for display purpose.

## What can be improved
* Add Dependency Injection
* Add JUnit testing
* Add Kotlin coroutines
* Add sealed class state(Loading, Success, Failure) and observe its states

## Screenshots
![Screenshot 1](screenshots/screen_1.png)
![Screenshot 2](screenshots/screen_2.png)
![Screenshot 3](screenshots/screen_3.png)