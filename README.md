# Countries app

This is my capstone project for the Udacity Android Kotlin Developer Nanodegree Program.

## About the app

The app idea is fetch data from the countries and display them to the user.
The actions in this app are quite simple, there is a list of all countries,
the user can just tap into a list item to go to another screen, also a country
search based on user's input and finally show the current country based on user's
location, from there is is possible get more details for the country and pick the
country based on the user's current location and display it using Google Maps.

## Design

I am not a good designer, so I made it simple, my choice for simple colors is just
to make the app clean.

The launcher screen has buttons for the actions allowed in the app, also from the 
countries list you can just click on an country to get getails.

## Rest API

The app is retrieving data from:

* [REST Countries](https://restcountries.eu)

## Android components and resources

This app demonstrates the following views and techniques:

* [Retrofit](https://square.github.io/retrofit/) to make api calls to an HTTP web service.
* [Moshi](https://github.com/square/moshi) which handles the deserialization of the returned JSON to Kotlin data objects. 
* [GlideToVectorYou](https://github.com/corouteam/GlideToVectorYou) to load and cache SVG images fetched in the requests.
* [Room](https://developer.android.com/training/data-storage/room) for local database storage.
* [OkHttp](https://square.github.io/okhttp/) to help in the web requests.
* [Service Locator](https://en.wikipedia.org/wiki/Service_locator_pattern) to get the repository in a easey way.
  
It leverages the following components from the Jetpack library:

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [Recycler View](https://developer.android.com/jetpack/androidx/releases/recyclerview) to display the items.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) with binding adapters
* [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) with the SafeArgs plugin for parameter passing between fragments.

## Suggested Workflow

* Import the project in the Android Studio, add your google maps (as explained in the next section) and then run it, in a device or in the emulator.

## Google Maps API Key

* To have the app running with full map capabilities, please add your google map key into the project.
* Locate the file named google_maps_api.xml in the project, in there, there will be a link to generate the Google Maps API key needed.
* Follow the instructions, copy the generated key.
* In the xml file, please change the ADD_YOUR_KEY_HERE for the key you just generated and the project ill be able to show the maps!


## Development helping resources and credits

For this project I used the content presented in the nanodegree videos, picked a few content from previous projects and did some web research to help me finding solutions to some problems. I can quote these:
* ExpressoIdlingResource.kt - got this code from the previous projects available in Udacity.
* Outcome.kt - Sealed class to help me handling the requests and exceptions, got the concept from [here](https://phauer.com/2019/sealed-classes-exceptions-kotlin/)

## Report Issues
Notice any issues with a repository? Please file a github issue in the repository.

## Screenshots

![Launcher](screenshots/launcher.jpg)

![Countries list](screenshots/countries_list.jpg)

![Details](screenshots/details.jpg)

![My Country](screenshots/mycountry.jpg)

![Map](screenshots/map.jpg)

![Countries list](screenshots/countries_list.jpg)

![Asking for user permission](screenshots/permission.jpg)

![Search](screenshots/search.jpg)

![Search ok](screenshots/search_ok.jpg)

![Search error](screenshots/search_error.jpg)





