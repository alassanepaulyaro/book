# MajesticReader

An elegant Android document and book reader application built with modern Android development practices and Clean Architecture principles.

## Overview

MajesticReader is a feature-rich document reading application designed to provide users with a seamless reading experience. The app supports document management, bookmark functionality, and an intuitive reader interface with smooth navigation.

## Features

- **Document Library**: Browse and manage your collection of documents
- **Advanced Reader**: Read documents with smooth page navigation and zoom capabilities
- **Bookmark System**: Save and manage bookmarks for quick access to important pages
- **Intuitive Navigation**: Easy-to-use navigation drawer for switching between library and reader views
- **Modern UI**: Clean, Material Design-based user interface
- **Offline Support**: All documents stored locally with Room database persistence

## Architecture

This project follows **Clean Architecture** principles, ensuring separation of concerns and maintainability:

### Layers

1. **Core Layer** (`core/`)
   - **Domain**: Business entities (Document, Bookmark)
   - **Data**: Repository interfaces and data source contracts
   - **Interactors**: Use cases for business logic
     - Document use cases (Add, Remove, Get, SetOpen)
     - Bookmark use cases (Add, Remove, Get)

2. **Framework Layer** (`framework/`)
   - **Presentation**: Activities, Fragments, ViewModels, and Adapters
   - **Data**: Room database implementation and data sources
   - **Utils**: Helper utilities for file and intent handling

### Architecture Pattern

- **MVVM (Model-View-ViewModel)**: Separates UI from business logic
- **Repository Pattern**: Abstracts data sources
- **Use Case Pattern**: Encapsulates business logic

## Tech Stack

### Core Technologies
- **Language**: Kotlin
- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 32 (Android 12L)
- **Build System**: Gradle with Kotlin DSL support

### Libraries & Dependencies

#### Android Jetpack
- **AndroidX Core KTX**: Kotlin extensions for Android
- **AppCompat**: Backward compatibility support
- **Material Components**: Material Design UI components
- **ConstraintLayout**: Flexible layout system
- **Room**: Local database (2.4.3)
  - Room Runtime
  - Room KTX (Coroutines support)
  - Room Compiler (KAPT)
- **Lifecycle Components**: ViewModel, LiveData, and lifecycle-aware components

#### Concurrency
- **Kotlin Coroutines**: Asynchronous programming (1.6.1)

#### Image Handling
- **Glide**: Image loading and caching (4.13.0)
- **PhotoView**: Zoomable image views (2.3.0)

#### Testing
- **JUnit**: Unit testing (4.13.2)
- **Espresso**: UI testing (3.4.0)
- **AndroidX Test**: Testing framework

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/paulyaro/majesticreader/
│   │   │   ├── core/
│   │   │   │   ├── data/
│   │   │   │   │   ├── bookmarksdData/
│   │   │   │   │   └── documentData/
│   │   │   │   ├── domain/
│   │   │   │   │   ├── Bookmark.kt
│   │   │   │   │   └── Document.kt
│   │   │   │   └── interactors/
│   │   │   │       ├── bookmarksUseCase/
│   │   │   │       └── documentsUseCase/
│   │   │   ├── framework/
│   │   │   │   ├── data/
│   │   │   │   │   ├── bookmarks/
│   │   │   │   │   ├── documents/
│   │   │   │   │   └── MajesticReaderDatabase.kt
│   │   │   │   ├── presentation/
│   │   │   │   │   ├── library/
│   │   │   │   │   ├── reader/
│   │   │   │   │   ├── utils/
│   │   │   │   │   └── MainActivity.kt
│   │   │   │   ├── utils/
│   │   │   │   ├── viewModel/
│   │   │   │   └── MajesticReaderApplication.kt
│   │   │   └── Interactors.kt
│   │   ├── res/
│   │   └── AndroidManifest.xml
│   └── test/
└── build.gradle
```

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- JDK 8 or higher
- Android SDK with API level 32
- Gradle 7.2.2 or compatible version

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd book
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Gradle**
   - Android Studio will automatically prompt to sync Gradle
   - Click "Sync Now" to download dependencies

4. **Build the project**
   ```bash
   ./gradlew build
   ```

5. **Run the application**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

## Building

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Running Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

## Database Schema

The app uses Room database with the following entities:

- **DocumentEntity**: Stores document metadata (url, name, size, thumbnail)
- **BookmarkEntity**: Stores bookmark information (id, page number)

Room schema exports are configured at: `app/schemas/`

## Key Components

### Main Features

1. **Library Fragment**: Displays all documents in a grid/list view
2. **Reader Fragment**: Document reading interface with page navigation
3. **Bookmarks**: Quick access to saved pages
4. **Navigation Drawer**: Switch between library and reader views

### ViewModels

- **LibraryViewModel**: Manages document library state
- **ReaderViewModel**: Handles reader state and bookmark operations

### Use Cases

- Document management (add, remove, retrieve)
- Bookmark management (add, remove, list)
- Open document tracking

## Configuration

### Package Name
```
com.paulyaro.majesticreader
```

### Version
- **Version Code**: 1
- **Version Name**: 1.0

## Permissions

The app currently requires standard Android permissions. Check `AndroidManifest.xml` for the complete list.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Write unit tests for new features

## License

This project is licensed under the terms specified in the LICENSE file.

## Acknowledgments

- Built with Android Jetpack libraries
- Uses Material Design components
- Image handling powered by Glide and PhotoView

## Contact

For questions or support, please open an issue in the repository.

---

**Built with ❤️ using Kotlin and Android Jetpack**
