---
layout: page
title: User Guide
---

FitEgo is a **desktop app for fitness instructors to manage their clients and schedules, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, FitEgo can get your client management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`clist`** : Lists all clients.

   * **`cadd`**`cadd n/Jane Doe p/91234567 e/jane@gmail.com` : Adds a client named `Jane Doe` to the Address Book.

   * **`cdel`**`3` : Deletes the 3rd contact shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

##### Clients
- Adding a client: `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`
    - calls "Adding a session" flow (optional)
- Updating a client:  `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`
- Deleting client: `cdel INDEX`
- Listing all clients: `clist`
- Find client by name: `cfind KEYWORD [MORE_KEYWORDS]`

##### Sessions
- Adding a session: `sadd s/SESSIONTYPE dt/DATETIME dur/DURATION g/GYM_NAME`
- Schedule a client to a session: `sched add c/CLIENT_INDEX s/SESSION_INDEX`
- Deschedule a client a session : `sched rm c/CLIENT_INDEX s/SESSION_INDEX` 
- Updating a session: `sedit INDEX [s/SESSIONTYPE] [dt/DATETIME] [dur/DURATION] [g/GYM_NAME]`
- Deleting a session: `sdel INDEX`

- Client (Class)
    - Add clients (**ClientID**, First Name, Last Name, Age, DOB, Weight, Height, Contact)
    - update clients
    - Remove clients

- GymSession (Class)
    - Add, Edit, Delete (session type, **DateTime**: `14/09/2020 1300`, duration, location)

- Schedule (As a collection of sessions)
    - View Allocated Schedule (for today - v1.2)

> Current Constraints: 
> * All Gym Name are unique. 


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary 
| Action | Format | Example | 
| -------| -------| --------| 
|Adding Clients  Info| `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`| `cadd n/Jane Doe p/91234567 e/jane@gmail.com`|
|Update Clients Info | `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`| `cedit 1 n/Janie Doe`|
|Deleting Client Info |`cdel INDEX` |`cdel 1`|
|List All Clients | `clist`  |  `clist`  |
|Find Client by Name | `cfind KEYWORD [MORE_KEYWORDS]`| `cfind John Doe`|
|Adding Gym Session |`sadd s/SESSIONTYPE dt/DATETIME dur/DURATION g/GYM_NAME` | `sadd Upper Body dt/this Thursday 4pm dur/2hr g/UTown Gym`|
|Assign a Client to Gym Session  |`sched add c/CLIENT_INDEX s/SESSION_INDEX`| `sched add c/1 s/3`|
|Unassign a Client to Gym Session |`sched rm c/CLIENT_INDEX s/SESSION_INDEX`  | `sched rm c/2 s/3` |
|Updating Session Info |`sedit INDEX [s/SESSIONTYPE] [dt/DATETIME] [dur/DURATION] [g/GYM_NAME]`| `sedit 1 dt/this Friday 6pm`|
|Deleting a Session |`sdel INDEX` | `sdel 1`|
