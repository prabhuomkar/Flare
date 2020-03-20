<h1 align="center">
 <img src="art/pytorch_logo.png" width="50%">
</h1>
<p align="center">
 Android application with collection of machine learning experiments using PyTorch Android API
 <br />
 <br />
 <img src="https://img.shields.io/github/license/prabhuomkar/pytorch-android?style=flat-square"
    alt="License">
 <img src="https://img.shields.io/badge/pytorch_android-1.4-%23ee4c2c?style=flat-square"
    alt="PyTorch Android">
</p>

<!-- Table of Contents !-->
* [About PyTorch Mobile](#about-pytorch-mobile)
  * [PyTorch Android API](#pytorch-android-api)
* [About](#about)
  * [Built With](#built-with)
  * [Project Structure](#project-structure)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Contributing](#contributing)
* [License](#license)

## About PyTorch Mobile
PyTorch Mobile is an experiment release that provides APIs to build an end-to-end workflow
from Python to deployment on iOS and Android. _[Read more](https://pytorch.org/mobile/home/)_

<p align="center">
	<img src="art/flow.jpg" width="75%">
</p>

### PyTorch Android API
PyTorch Mobile provides Java APIs for integration of Machine Learning models built with Python
into an Android application. It is as simple as importing and using any other Android library. You
can add PyTorch's Gradle dependencies and can run inferences. _[Read more]
(https://pytorch.org/mobile/android/)_

## About
PyTorch Android is an android application which contains collection of machine learning experiments
built using PyTorch and its Android API. This application will allow you to try tasks like Image
Classification, Sentiment Analysis, Object Detection and many more with famous datasets _(like
MNIST, ImageNet, etc.)_ and State-Of-The-Art models _(like AlexNet, ResNet, etc.)_.

### Built With
* [PyTorch](https://pytorch.org) & [PyTorch Android API](https://pytorch.org/mobile/android/)
* [Python 3](https://www.python.org/download/releases/3.0/)
* [Java 1.8](https://www.java.com/en/download/)
* [Android Studio](https://developer.android.com/studio)

### Project Structure
* [activities](app/src/main/java/org/pytorch/pytorchandroid/activities) - Contains all android
activities created
* [adapters](app/src/main/java/org/pytorch/pytorchandroid/adapters) - Contains all list adapters
* [fragments](app/src/main/java/org/pytorch/pytorchandroid/fragments) - Contains all android
fragments used
* [helpers](app/src/main/java/org/pytorch/pytorchandroid/helpers) - Contains constants, helper
functions for actions like switching fragments, loading images, etc.
* [models](app/src/main/java/org/pytorch/pytorchandroid/models) - Contains data models for several
lists, fragments to display on the UI
* [tasks](app/src/main/java/org/pytorch/pytorchandroid/models) - Contains all classifier code which
converts image to tensors and runs the classifier, target classes for several datasets

## Getting Started

### Prerequisites
TODO: Add better prerequisites which are required for an Android application
TODO: Describe about usage of Machine Learning models

### Installation
TODO: Add step by step installation guide to download, install dependencies and run the app

## Usage
TODO: Add demos to play with the tasks and its models

## Contributing
We appreciate all contributions. If you are planning to contribute back bug-fixes, please do so
without any further discussion.

If you plan to contribute new features, utility functions or any application upgrades, please first
open an issue and discuss the feature with us. Sending a PR without discussion might end up
resulting in a rejected PR, because we might be taking the project in a different direction
than you might be aware of.

Please refer our [Contribution Guide](CONTRIBUTING.md) for more details.

## License
