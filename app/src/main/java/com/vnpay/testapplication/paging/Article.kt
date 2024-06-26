package com.vnpay.testapplication.paging

data class Article(val id: Int, val name: String, val description: String) {
    companion object {
        fun getList() = listOf(
            Article(1, "Introduction to Kotlin", "Learn the basics of Kotlin programming language."),
            Article(2, "Advanced Kotlin Techniques", "Explore advanced features and techniques in Kotlin."),
            Article(3, "Kotlin for Android Development", "Using Kotlin for Android app development."),
            Article(4, "Understanding Coroutines", "Deep dive into coroutines in Kotlin."),
            Article(5, "Functional Programming in Kotlin", "Functional programming concepts applied in Kotlin."),
            Article(6, "Kotlin vs Java", "Comparison between Kotlin and Java for Android development."),
            Article(7, "Kotlin Multiplatform", "Developing multiplatform applications with Kotlin."),
            Article(8, "Getting Started with Jetpack Compose", "Introduction to Jetpack Compose for UI development."),
            Article(9, "Data Binding in Android", "Using data binding in Android applications."),
            Article(10, "Room Persistence Library", "Using Room for local database storage in Android."),
            Article(11, "Networking in Android", "Making network requests in Android using Retrofit."),
            Article(12, "Dependency Injection with Dagger", "Using Dagger for dependency injection in Android."),
            Article(13, "MVVM Architecture", "Implementing MVVM architecture in Android."),
            Article(14, "Testing in Android", "Unit and UI testing in Android applications."),
            Article(15, "Working with LiveData", "Using LiveData in Android applications."),
            Article(16, "Navigation Component", "Managing app navigation with the Navigation Component."),
            Article(17, "Kotlin Flow", "Using Kotlin Flow for reactive programming."),
            Article(18, "Android Paging Library", "Implementing pagination in Android applications."),
            Article(19, "Firebase Integration", "Integrating Firebase services into Android applications."),
            Article(20, "Animations in Android", "Creating animations in Android applications."),
            Article(21, "Custom Views in Android", "Creating custom views in Android."),
            Article(22, "Handling Permissions", "Managing permissions in Android applications."),
            Article(23, "Localization in Android", "Supporting multiple languages in Android applications."),
            Article(24, "Android App Bundles", "Using Android App Bundles for app distribution."),
            Article(25, "Material Design in Android", "Implementing Material Design principles in Android."),
            Article(26, "Security Best Practices", "Ensuring security in Android applications."),
            Article(27, "Performance Optimization", "Optimizing performance in Android applications."),
            Article(28, "Bluetooth in Android", "Implementing Bluetooth communication in Android."),
            Article(29, "Camera Integration", "Using the camera in Android applications."),
            Article(30, "Geolocation Services", "Implementing geolocation services in Android."),
            Article(31, "Sensor Management", "Working with sensors in Android applications."),
            Article(32, "Background Work with WorkManager", "Using WorkManager for background tasks."),
            Article(33, "Push Notifications", "Implementing push notifications in Android."),
            Article(34, "Wear OS Development", "Developing apps for Wear OS."),
            Article(35, "Android Auto Development", "Developing apps for Android Auto."),
            Article(36, "Android TV Development", "Developing apps for Android TV."),
            Article(37, "ARCore Integration", "Using ARCore for augmented reality in Android."),
            Article(38, "Machine Learning in Android", "Implementing machine learning in Android applications."),
            Article(39, "Voice Assistants", "Integrating voice assistants in Android applications."),
            Article(40, "Accessibility Features", "Implementing accessibility features in Android."),
            Article(41, "Kotlin DSL", "Using Kotlin DSL for build scripts and more."),
            Article(42, "Jetpack WorkManager", "Managing background work with Jetpack WorkManager."),
            Article(43, "Compose Navigation", "Navigating in Jetpack Compose applications."),
            Article(44, "State Management in Compose", "Managing state in Jetpack Compose."),
            Article(45, "Theming in Compose", "Theming and styling in Jetpack Compose."),
            Article(46, "Compose Animation", "Creating animations in Jetpack Compose."),
            Article(47, "Compose Lists and Grids", "Creating lists and grids in Jetpack Compose."),
            Article(48, "Compose Forms", "Building forms in Jetpack Compose."),
            Article(49, "Compose Performance", "Optimizing performance in Jetpack Compose."),
            Article(50, "Compose Testing", "Testing Jetpack Compose applications.")
        )

    }
}
