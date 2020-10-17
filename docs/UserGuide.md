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

1. Download the latest `fitego.jar` from [here](https://github.com/AY2021S1-CS2103T-T13-3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`clist`** : Lists all clients.

   * **`cadd`**`cadd n/Jane Doe p/91234567 e/jane@gmail.com` : Adds a client named `Jane Doe` to the clients list.

   * **`cdel`**`3` : Deletes the 3rd client shown in the current list.

   * **`exit`** : Exits the app.

1. Read [How to use](#how-to-use-fitego) for a quick understanding of commands in FitEgo

1. Refer to the [Features](#features) below for more details of each command.

--------------------------------------------------------------------------------------------------------------------

## How to use FitEgo

There are 3 major entities in FitEgo: Clients, Sessions, and Schedules.
There are 5 major verbs in FitEgo: add, edit, delete, view, list

### Clients

Clients are customers that is trained by the user (fitness instructor).

All client's commands using the prefix `c`. 
 
### Sessions

Sessions are a timeslot that is scheduled for a training session. It contains information about the gym, the session's 
main exercise type, start time and the duration of sessions. 

Each session can have more than 1 clients, to simulate a trainer instructing a fitness class.

FitEgo don't allow user to create overlapping sessions. This is to protect users from scheduling overlapping sessions
at different gyms. 

All session's commands have prefix `s`.

### Schedules

Schedules are what defines a user-client interaction. Each schedule contains information about the client and the 
session they attended. 

- User can add in details about the client's weight progress
- User can add exercises done during the session as remark in schedules.
- User can track whether client has paid for the session attended

All schedules' commands have prefix `sch`.

Once you learn the entity, you can now combine it with the verb. For example:
- `cadd`: Add a client, `sadd`: Creates a session, `schadd`: Creates a schedule
- `cedit`: Edit a client's details, `sedit`: Edit a session's details, `schedit`: Edit a schedule's details
- `cdel`: Edit a client's details, `sdel`: Edit a session's details, `schdel`: Edit a schedule's details

Although there are a lot of commands, once you learn the verb and entities, it is so easy to use FitEgo!

--------------------------------------------------------------------------------------------------------------------


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/injured-thigh` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/injured-thigh`, `t/injured-thigh t/allergy-dairy` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a client: `cadd`

Adds a client to the clients list.

Format: `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A Client can have any number of tags (including 0). Each tag can only include
alphanumeric characters or dash (`-`)
</div>

Examples:
* `cadd n/Jane Doe p/91234567 e/jane@gmail.com`
* `cadd n/John Doe p/91231367 e/jojo@gmail.com t/injured-thigh`

### Listing all clients : `clist`

Shows a list of all clients in the clients list.

Format: `clist`

### Editing a Client : `cedit`

Edits an existing Client in the clients list.

Format: `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`

* Edits the Client at the specified `INDEX`. The index refers to the index number shown in the displayed Client list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the Client will be removed i.e adding of tags is not cumulative.
* You can remove all the Client’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `cedit 1 n/Janie Doe` Edits the name of the 1st Client to be `Janie Doe`.

### Locating clients by name: `cfind`

Finds clients whose names contain any of the given keywords.

Format: `cfind KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Partial names will be matched e.g. `Han` will match `Hans`
* Clients matching any substring will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a Client : `cdel`

Deletes the specified client from the clients list.

Format: `cdel INDEX`

* Deletes the Client at the specified `INDEX`.
* The index refers to the index number shown in the displayed Client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `cdel 2` deletes the 2nd Client in the address book.
* `find Betsy` followed by `cdel 1` deletes the 1st Client in the results of the `find` command.

### View a Client : `cview`

View the specified client from the clients list.

Format: `cview INDEX`

* Views the Client at the specified `INDEX`.
* The index refers to the index number shown in the displayed Client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `cview 2` opens the 2nd Client in the address book.
* `find Bernice` followed by `cview 1` opens the 1st Client (Bernice) in the results of the `find` command.

<img src="images/cview_sample.png" alt="result for 'cview 1'" height="500" width="500"/></br>
  > This profile window will be updated when more functionalities are available.

### Adding a Session: `sadd`

Creates a session.

Format: `sadd g/GYM_NAME ex/EXERCISE_TYPE at/START_TIME t/DURATION`

* Start time should be of format "dd/MM/yyyy HHmm"
* Duration is in minutes
* Duration should be a positive integer (larger than 0)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    Session should not overlap with previously created session.
</div>

Examples:
* `sadd g/Machoman Gym ex/Endurance at/29/09/2020 1600 t/120`


### Editing a Session: `sedit`

Edits the details of the Session identified by the index number used in the displayed Session list.

* Edits the Session at the specified `INDEX`. The index refers to the index number shown in the displayed Session list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `sedit 1 g/Machoman at/29/09/2020 1600 t/120 ` Edits the Gym of the 1st Session to be `Machoman`.
*  `sedit 2 at/29/09/2020 1600 t/120 ` Edits the Start Time and Duration of the 2nd Session to be `29/09/2020 1600 with a duration of 120 minutes`.


### Deleting a Session: `sdel`

Deletes the specified session by the index number used in the displayed Session list and all schedules associated with
the specified session

Format: `sdel INDEX [f/ true]`

* Deletes the Session at the specified `INDEX`.
* The index refers to the index number shown in the displayed Session list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* If there are no schedules with the 2nd session in the session List, `list` followed by `sdel 2` will delete the session
* If there are one or more associated schedules associated with the 2nd session in the Session List, 
`list` followed by `sdel 2` will return an error message 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    To force deletion of session (and all associated schedules), pass in the optional force flag followed by
    any non empty string.
</div>

Examples:
* If there are one or more associated schedules associated with the 2nd session in the Session List, 
  `list` followed by `sdel 2 f/ true` will delete all schedules associated with the 2nd session, then delete the session 
  itself
  
  
### Editing a Schedule: `schedit`

Edits the details of the Schedule identified by the client index and session index used in each Schedule in the Schedule list.

Format: `schedit c/CLIENT s/SESSION [us/UPDATED INDEX] [pd/IS PAID]`

* Edits the Schedule at the specified `c/CLIENT s/SESSION`. Both indexes **must be positive integers** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.

Examples:
*  `schedit c/1 s/1 us/2` Edits the Schedule containing client index 1 and session index 1 to be `SESSION 2`.
*  `schedit c/1 s/1 pd/true` Edits the Schedule containing client index 1 and session index 1 to be be paid.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------


## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.


--------------------------------------------------------------------------------------------------------------------


## Command Summary

| Action | Format | Example |
| -------- | -------- | --------- |
| Adding Clients  Info| `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`| `cadd n/Jane Doe p/91234567 e/jane@gmail.com`|
| Update Clients Info | `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`| `cedit 1 n/Janie Doe`|
| Deleting Client Info |`cdel INDEX` |`cdel 1`|
| List All Clients | `clist`  |  `clist`  |
| View a Client's Full Profile | `cview INDEX` | `cview 1`|
| Find Client by Name | `cfind KEYWORD [MORE_KEYWORDS]`| `cfind John Doe`|
| Adding a Session | `sadd g/GYM_NAME ex/EXERCISE_TYPE at/START_TIME t/DURATION` | `sadd g/Machoman Gym ex/Endurance at/29/09/2020 1600 t/120` |
| Update Sessions Info |`sedit INDEX g/GYM_NAME at/START_TIME t/DURATION ` | `sedit 1 g/Machoman at/29/09/2020 1600 t/120`|
| Deleting a Session |`sdel INDEX [f/ true]` | `sdel 1` |
| Assign a Client to Gym Session  |`schadd c/CLIENT_INDEX s/SESSION_INDEX`| `schadd c/1 s/3`|
| Unassign a Client to Gym Session |`schdel c/CLIENT_INDEX s/SESSION_INDEX`  | `schdel c/2 s/3` |
| Edit a Client to Gym Session |`schedit c/CLIENT s/SESSION [us/UPDATED SESSION] [pd/IS PAID]`  | `schedit c/1 s/1 us/1 pd/true`|


### Acknowledgement
Icon made by Freepik from <www.flaticon.com>
