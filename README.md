# Anime List App

## Description
This is a fully functional Android application built using Kotlin. 
The app fetches the list of Anime from Jikan API, displays them in a user-friendly manner, and allows users to check more details of that particular anime by clicking on it. 
It follows Android best practices and uses a modular architecture.

**Features**

Fetches and displays the multiple anime, title, episodes and images.

Allow user to get details of particular anime by clicking on that.

User can also see trailer of anime if available.

Clean and modern UI using RecyclerView.

**Tech Stack**

Kotlin: Programming language.

Retrofit: For making API calls.

Picasso: For image loading.

[//]: # (Coroutines: For asynchronous operations.)

RecyclerView: For displaying a scrollable list of news articles.

ViewModel and LiveData: For managing UI-related data in a lifecycle-conscious way.

**Prerequisites**

Android Studio with proper setup installed on your machine.

If you run the project and get this error "The project is using an incompatible version (AGP 8.7.2) of the Android Gradle plugin. Latest supported version is AGP 8.5.0"
Upgrade your Android Studio to latest version.

**Setup Instructions**

Clone the repository:

git clone 

open the cloned folder in Android Studio

Sync the project with Gradle.

Run the application on an emulator or physical device.

**Code Architecture**

The app follows the MVVM (Model-View-ViewModel) architecture:

Model:

Data classes for API responses.

ViewModel:

Manages UI-related data.

Handles API and database operations via a Repository.

View:

Fragments for UI screens .

RecyclerView adapters for dynamic lists.

**Key Components**

1. API Integration

Retrofit is used to fetch data from NewsAPI.

API responses are mapped to data classes.

2. RecyclerView

Displays news articles in a scrollable list.

Custom adapters for dynamic content.

How to Use

The home screen displays the list of anime with their poster, name, title and number of episodes .

View Full Detail:

Click on any anime to view it in a detail.

Now on detail screen you can also watch trailer of anime if available and also read the synopsis of that anime.

**Future Enhancements**

Implement pagination for large datasets.

Add a "Search" feature to find specific anime.

Introduce user preferences such as dark mode.

Optimize offline-first behavior.

**Contributing**

Fork the repository.

Create a new branch for your feature: git checkout -b feature-name.

Commit your changes: git commit -m 'Add new feature'.

Push to your branch: git push origin feature-name.

Create a pull request.

**License**

This project is licensed under the MIT License.



