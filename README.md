# 📰 News Shots - Offline-First Android App

News Shots is a sample Android app that demonstrates the offline-first & multi module approach and
uses the clean architecture pattern. The app fetches news articles from a remote API and saves them
to a local database for offline use. The app's UI is built using Jetpack Compose, and it uses the
following libraries:

- Ktor client for network requests
- kotlinx serialization for JSON parsing
- Ktor client resources for static resources
- WorkManager for background work
- RoomDB for local data storage
- Material Design 3 for UI components

## 🏗️ Architecture

The app is designed using the Clean Architecture pattern, which separates the app into three layers:
presentation, and data. The presentation layer contains the UI components and handles user
interactions. The data layer handles data storage and retrieval.

The app also uses the Repository pattern to abstract data sources and provides a single entry point
for data access.

## 💡 Offline-first Approach

The app uses an offline-first approach, which means that it prioritizes local data over remote data.
When the app is opened, it first fetches data from the local database and displays it to the user.
In the background, the app also fetches the latest data from the remote API and updates the local
database. This ensures that the user always has access to the latest news articles, even when
offline.

## 🎨 Jetpack Compose

The app's UI is built using Jetpack Compose, which is a modern UI toolkit for building Android apps.
Compose uses a declarative programming model, which makes it easy to build UI components and handle
user interactions.

## 📚 Libraries Used

The following libraries are used in the app:

- Ktor client: A Kotlin-based HTTP client for making network requests.
- kotlinx serialization: A Kotlin library for parsing JSON data.
- Ktor client resources: A Ktor plugin for loading static resources.
- WorkManager: A library for running background tasks.
- RoomDB: An Android library for local data storage.
- Material Design 3: Google's latest design system for UI components.

## 📄 License

This project is licensed under the MIT License.
