<h1 align="center">Postpone</h1>
<p align="center">  
A useful note-keeping and task tracking app  for teaching how to use Jetpack Compose (State, Navigation, Tabs etc..) with Clean Architecture and shows how to write Unit test </p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/halitsen"><img alt="Profile" src="https://img.shields.io/badge/github-halitsen-blue"/></a> 
  <a href="https://play.google.com/store/apps/details?id=halit.sen.postpone"><img alt="Profile" src="https://img.shields.io/badge/Google_Play-Postpone-F3745F.svg"/></a> 
</p>

## Screeshots
<p align="center">
<img src="/art/note.jpg" width="20%"/>
<img src="/art/task.jpg" width="20%"/>
<img src="/art/new_task.jpg" width="20%"/>
<img src="/art/delete_note.jpg" width="20%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) and [Flow](https://developer.android.com/kotlin/flow)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
    -  A single-activity and multi-module architecture, using the [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) to manage composable transactions.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
    - [UseCases](https://developer.android.com/topic/architecture/domain-layer) - Located domain layer that sits between the UI layer and the data layer.
    - [Repository](https://developer.android.com/topic/architecture/data-layer) - Located in data layer that contains application data and business logic.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs
- [Room Database](https://developer.android.com/jetpack/androidx/releases/room?hl=en) - is a persistence library, part of the Android Jetpack.Room provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
- [Android Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency Injection Library
- [Accompanist](https://google.github.io/accompanist/pager/) - A library which provides paging layouts for Jetpack Compose. If you've used Android's ViewPager before, it has similar properties.
- Testing
    - [Mockito](https://site.mockito.org/) A mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API
    - [Truth](https://truth.dev/) A library for performing assertions in tests
    - [Turbine](https://github.com/cashapp/turbine) A small testing library for kotlinx.coroutines Flow
- [Material Design 3](https://m3.material.io/) is the latest version of Google’s open-source design system.

## Dependency graph
<p align="center">
<img src="/art/postpone_architecture" width="80%" height="500px"/>
</p>

## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture![](https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png)


## Download
[<img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" alt="" height="100">](https://play.google.com/store/apps/details?id=halit.sen.postpone)



# License
```xml
Designed and developed by 2023 halitsen (Halit Şen)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```



