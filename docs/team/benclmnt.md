---
layout: page
title: Bennett Clement's Project Portfolio Page
---

## Project: FitEgo

FitEgo is a desktop application for fitness instructors to schedule, and keep track of his/her customers' progress in one place.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add / delete sessions.
  * What it does: allows the user to create and delete fitness sessions
  * Justification: This feature is core to the product because the user needs to create a session before they can schedule a client.
  * Highlights: This enhancement required an in-depth analysis of design alternatives.
  The implementation was challenging as it deals with dates, the notion of "overlapping" dates, and handling schedules related to the session

* **New Feature**: Added table to view a list of client's schedules
  * What it does: List client's schedules (with remarks, payment status and exercise type)
  * Justification: This feature makes it easy for the user to keep track of client's progress in one page
  * Highlights: The implementation was challenging as it required the table to be resizable and to show updates as soon as user changes any details about the client / schedules.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=benclmnt&tabRepo=AY2021S1-CS2103T-T13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)

* **Project management**:
  * Managed all releases `v1.1` - `v1.3` (4 releases) on GitHub
  * Setting up the GitHub team org/repo
  * Setting up tools (Gradle)
  * Maintained issue tracker by setting up milestones, label and triaging bugs

* **Enhancements to existing features**:
  * Wrote tests for Session model (Pull requests [\#140](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/140))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sadd` and `sdel` [\#139](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/139)
    * Added explanation on common terminologies used in FitEgo User Guide [\#139](https://github.com/AY2021S1-CS2103T-T13-3/tp/pull/139)
  * Developer Guide:
    * Added implementation details of the `sdel` feature.

* **Review/mentoring contributions**:
  * Regularly discuss implementation details in our group's communication channel.
  * Help others debug and find solution during team meetings.
  * Post issues and discussions on issue tracker. (Some examples: 
  [\#98](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/98), [\#107](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/107), [\#223](https://github.com/AY2021S1-CS2103T-T13-3/tp/issues/223))

* **Tools**:
  * Integrated a new Github plugin (PlantUML) to the team repo

