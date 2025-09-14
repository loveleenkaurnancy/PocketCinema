# PocketCinema

PocketCinema is an Android application built with **Jetpack Compose** that allows users to browse movies from **The Movie Database (TMDb)** API. Users can view **Now Playing**, **Top Rated** movies, bookmark favorites, and search for movies in real-time.

---

## Features

* Browse **Now Playing** and **Top Rated** movies.
* Bookmark your favorite movies for quick access.
* Search movies by name using the TMDb search API.
* Smooth **Jetpack Compose UI** with **Material 3** design.
* Offline caching using **Room Database**.
* Handles no internet scenario gracefully with informative messages.

---

## Screens

1. **Home Screen**

   * Displays App logo with bookmarks icon.
   * Sections for "Now Playing" and "Top Rated" movies.
   * Search bar for live movie search.
2. **Movie Details Screen**

   * Displays poster, title, overview, and bookmark option.
3. **Bookmarks Screen**

   * List of bookmarked movies with poster and title.
4. **Search Results**

   * Shows results as user types in the search bar.
5. **Empty States**

   * Informative message if no movies are available (offline or no data).

---

## Tech Stack

* **Kotlin** – Main programming language.
* **Jetpack Compose** – UI toolkit.
* **Material 3** – Modern Android UI components.
* **Hilt** – Dependency injection.
* **Room** – Local database for caching.
* **Retrofit + OkHttp** – Networking.
* **Coil** – Image loading.
* **Coroutines + Flow** – Asynchronous and reactive programming.
* **MVVM + Clean Architecture** – App architecture.

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/yourusername/pocketcinema.git
```

2. Open in **Android Studio**.
3. Make sure you have **Jetpack Compose** and **Kotlin 2.x** configured.
4. Add your **TMDb API Key** in the `TmdbApi` class (Authorization header).
5. Build and run the project on an emulator or real device.

---

## Dependencies

* androidx.compose.material3\:material3
* androidx.compose.ui\:ui
* androidx.compose.ui\:ui-tooling
* androidx.compose.foundation\:foundation
* androidx.lifecycle\:lifecycle-runtime-ktx
* androidx.activity\:activity-compose
* dagger\:hilt-android
* androidx.hilt\:hilt-navigation-compose
* retrofit2\:retrofit
* retrofit2\:converter-gson
* okhttp3\:logging-interceptor
* androidx.room\:room-runtime
* androidx.room\:room-ktx
* coil-kt\:coil-compose

---

## Project Structure

```
com.pocketcinema
│
├── data
│   ├── local         # Room entities and DAO
│   ├── remote        # TMDb API interface and DTOs
│   └── repository    # Data repository
│
├── ui
│   ├── home          # HomeScreen, MovieRow, Search Bar
│   ├── details       # MovieDetailScreen
│   └── bookmark      # BookmarksScreen
│
└── di                # Hilt modules
```

---

## Screenshots

<img width="270" height="600" alt="1" src="https://github.com/user-attachments/assets/ca982767-155b-4794-87d7-28074cd219b7" />
<img width="270" height="600" alt="2" src="https://github.com/user-attachments/assets/6d1a8afd-9394-48a4-b1c3-25e5ae90de7b" />
<img width="270" height="600" alt="3" src="https://github.com/user-attachments/assets/71b26b66-3bfd-42db-b8e0-9fee16c747e9" />
<img width="270" height="600" alt="4" src="https://github.com/user-attachments/assets/a91ae4bd-5f00-4728-ae08-49caf14e1c16" />
<img width="270" height="600" alt="5" src="https://github.com/user-attachments/assets/03faf0ac-28ed-46ca-bc4a-9f9bdb9e61cd" />


---

## Future Improvements

* Add movie trailers using YouTube API.
* Dark/Light theme toggle.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
