# Compose Performance Profiler

![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![Version](https://img.shields.io/badge/version-1.0.0-brightgreen)
[![Maven Central](https://img.shields.io/maven-central/v/com.example.profiler/profiler.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.example.profiler%20AND%20a:profiler)
[![JitPack](https://jitpack.io/v/yourusername/profiler.svg)](https://jitpack.io/#yourusername/profiler)

Compose Performance Profiler is a comprehensive library designed to help Android developers monitor and optimize the performance of Jetpack Compose applications. This tool provides real-time feedback on various performance metrics, enabling you to identify bottlenecks and inefficiencies in your app.

## Features

- **Recomposition Tracking**: Track how often composables are being recomposed to avoid unnecessary renders.
- **Render Time Measurement**: Measure how long it takes for your composables to render.
- **Layout Overdraw Detection**: Identify areas of your UI that are drawing more than necessary.
- **Frame Rate Monitoring**: Monitor the frame rate to detect jank and ensure a smooth user experience.
- **View Hierarchy Profiler**: Analyze the depth and complexity of your composable tree.
- **Network Request Profiling**: Measure the time taken for network requests and log performance.
- **Memory Usage Tracking**: Monitor and log memory usage during runtime.
- **CPU Usage Monitoring**: Track CPU usage and identify spikes during rendering or heavy computations.
- **User Session Profiling**: Profile entire user sessions, including screen transitions, user interactions, and overall performance.
- **Debug Overlay**: Display all profiling data in real-time within the app.

## Table of Contents

- [Installation](#installation)
    - [Maven Central](#maven-central)
    - [JitPack](#jitpack)
- [Usage](#usage)
    - [Basic Setup](#basic-setup)
    - [Profiling Features](#profiling-features)
        - [Recomposition Tracking](#recomposition-tracking)
        - [Render Time Measurement](#render-time-measurement)
        - [Layout Overdraw Detection](#layout-overdraw-detection)
        - [Frame Rate Monitoring](#frame-rate-monitoring)
        - [View Hierarchy Profiler](#view-hierarchy-profiler)
        - [Network Request Profiling](#network-request-profiling)
        - [Memory Usage Tracking](#memory-usage-tracking)
        - [CPU Usage Monitoring](#cpu-usage-monitoring)
        - [User Session Profiling](#user-session-profiling)
        - [Debug Overlay](#debug-overlay)
- [Sample App](#sample-app)
- [Contributing](#contributing)
- [License](#license)
- [FAQ](#faq)

## Installation

You can install the Compose Performance Profiler using either Maven Central or JitPack.

### //TODO

### Maven Central

To include the library in your project via Maven Central, add the following to your `build.gradle.kts` file:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.example.profiler:profiler:1.0.0")
}
