---
layout: page
title: kelvinvin's Project Portfolio Page
---

## Project: FitEgo

FitEgo is a desktop application used to help fitness instructors with organizing their schedule and monitoring of clients' progress and payment status. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to change view of Session List.
  * What it does: allows the user to filter the Session List to only those that start within the requested period.
  * Justification: This feature improves the product significantly because a user can now re-prioritise and make changes to their schedule much more conveniently.
  * Highlights: This enhancement affects existing session-related and schedule-related commands. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to the Logic component.
  * Credits: *{Credits to teammates Tan Wei Jie for adding variable range ability and Maguire Ong for assisting with Ui-related matters of the Session List}*

* **New Feature**: Added a weight unit utility class to facilitate conversion of units.
  * What it does: allows the user to both input and output weight in terms of kilogram or pounds.
  * Justification: This feature improves the usability of the product significantly because it widens the scope of users to include both categories of people who use Metric and Imperial systems.
  * Highlights: This enhancement affects the existing weight-related commands and graph. 
    It also necessitated the implementation of an additional user preference field that users can set via a Settings window. 
    The implementation was challenging because it was built to reduce coupling with other classes and required usage of Observer pattern to enable dynamic updates.
  * Credits: *{Reused code from the Help window to implement the Settings window}*

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=kelvinvin&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=kelvinvin&tabRepo=AY2021S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code)

* **Project management**:
  * Managed timely updates of demo for tutorials

* **Enhancements to existing features**:
  * Added wrap text to increase text readability for small resolutions. [\#218](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/218)
  * Refactored Delete and List commands to equivalent Client commands. [\#56](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/56), [\#55](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/55)
  
* **Documentation**:
  * User Guide:
    * Added documentation for the features `sview`, `cdel` and `settings` [\#229](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/229), [\#171](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/171), [\#159](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/159) 
    * Made cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#157](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/157)
    * Ensured accurate grammar and consistency of commonly used terms [\#157](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/157), [\#23](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/23)
    * Created an annotated diagram of Ui components.

  * Developer Guide:
    * Added implementation details of the `cdel` and `sview` features.
    * Updated diagrams and description of the Logic component.
    * Increased readability by rephrasing sections and improved consistency in punctuation and grammar. [\#227](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/227), [\#218](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/218)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#233](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/233), [\#159](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/159), [\#149](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/149), [\#153](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/153)
  * Reported bugs and suggestions for other teams in the class (examples: [138](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/138))
