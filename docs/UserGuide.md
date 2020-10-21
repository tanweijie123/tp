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

1. Download the latest `fitego.jar` from [here](https://github.com/AY2021S1-CS2103T-T13-3/tp/releases).

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

1. Refer to the [Keyword](#main-keywords) below for more details of each command.

--------------------------------------------------------------------------------------------------------------------

# UI-orientation

![annotatedUi](images/annotatedUi.png)

From the above image, the GUI is made up of several components. 

| Component | Description |
| --------------- | ---------------------------------------- | 
| Toolbar         | Displays the toolbar for this program. You can access the `exit` and `help` command from here.  | 
| Command Box     | Displays a text box for your input. You can type your command here.          | 
| Result Display  | Displays the result of your command. If the execution is successful, it will display a success message. Otherwise, it will prompt an error message | 
| Client List     | Displays the list of clients in a list view. You can modify this list using [client's command](#Client-related-Keywords) |
| Main Window     | Displays the main window of this program. It consist of the statistics of this program, today's schedule and Quote of the day | 
| Session List    | Displays the list of sessions in a list view. You can modify this list using [session's command](#Session-related-Keywords) | 
| Status Bar Footer | Displays the current date / time of the program. If you notice this is incorrect, your PC might be having a different date / time | 


<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can type input into the Command Box and it will display the command starting with the input. <br/>
<br>
![autocomplete_sample](images/autocomplete_sample.png)
<br>
For example, in the above image, I entered "c" and commands that starts with "c" is displayed. <br>
For advanced users, you may use the "TAB" key and it will auto-complete the first test into the command box, 
   thus increasing your typing speed!   
</div>

--------------------------------------------------------------------------------------------------------------------

## How to use FitEgo

There are 3 major entities in FitEgo: Clients, Sessions, and Schedules.
There are 5 major verbs in FitEgo: add, edit, delete, view, list.

### Clients

Clients are customers that is trained by the user (fitness instructor).

All client's commands using the prefix `c`. 

### Sessions

Sessions are a timeslot that is scheduled for a training session. It contains information about the gym, the session's 
main exercise type, start time and the duration of sessions. 

Each session can have more than 1 clients, to simulate a trainer instructing a fitness class.

FitEgo doesn't allow user to create overlapping sessions. This is to protect users from scheduling overlapping sessions
at different gyms. 

All session's commands have prefix `s`.

### Schedules

Schedules are what defines a you and your client's interaction. Each schedule contains information about the Client and the 
attended Session. 

- You can add in details about your Client's weight progress
- You can add exercises done during the Session as remark in Schedule
- You can track whether your Client has paid for the Session attended

Example of a Schedule:

| Client   | Session                                                      | Weight | Remark                                                       | Payment Status |
| -------- | ------------------------------------------------------------ | ------ | ------------------------------------------------------------ | -------------- |
| John Doe | Endurance training at Machoman Gym (24/10/2020 1200 - 1400)  | 70 kg  | Planks (20 x 30 seconds), body weight squats (5 sets of 25 reps) | paid           |
| Bernice  | Body building training at Getwell Gym (27/10/2020 1300 - 1500) | 85 kg  | Chinup (5 sets of 5 reps), muscle strain after bench press   | unpaid         |

All schedules' commands have prefix `sch`.

Once you learn the entity, you can now combine it with the verb. For example:
- `cadd`: Add a client, `sadd`: Creates a session, `schadd`: Creates a schedule
- `cedit`: Edit a client's details, `sedit`: Edit a session's details, `schedit`: Edit a schedule's details
- `cdel`: Remove a client, `sdel`: Delete a session, `schdel`: Delete a schedule

### General Note
* The program will automatically save after every command execution to guarantee that your data will never disappear. 

Although there are a lot of commands, once you learn the verb and entities, it is so easy to use FitEgo!

--------------------------------------------------------------------------------------------------------------------

# Keyword

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

## Main Keywords

### Viewing home : `home`

Shows / Returns to the home page 

![homepage](images/homepage.png)

The homepage will display the statistics of the program, today's schedule and quote of the day. 

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
By default, you can click enter and a browser will open the User Guide. 
You may also press "ESC" key to close this window. 
</div>

### Clearing all data in the program : `clear` 

You can delete all data (client, session, schedule) using the `clear` keyword. All of the existing data will be removed. 

<div markdown="span" class="alert alert-danger">:warning: **Warning:**
By using this command, it will delete all of your data, and by design of the system, it will be automatically saved. 
You will not be able to retrieve your previous data unless you have backed up the data file into an external location. 
</div>

### Exiting the program : `exit`

Exits the program.

Format: `exit`


### Saving the data

FitEgo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

---
## Client-related Keywords
All of client's related keyword is described in this section. All of the commands here will interact with the client panel
which is located at the left of this programme. 

<img src="images/ClientPanel.png" alt="client_panel" width="250" height="400" />

### Listing all clients : `clist`

Shows a list of all clients in the clients list. The list of clients will be shown at the left panel. 

By default, the software will display all the clients. In case you used [`cfind`](#locating-clients-by-name-cfind) or any filter-typed commands, 
you can use `clist` to view the entire list of clients.  

Format: `clist`

### Adding a client: `cadd`

Adds a client to the clients list.

Format: `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A Client can have any number of tags (including 0). Each tag can include
alphanumeric characters or dash (`-`), but you are not allowed to start or end
a tag with a dash. 
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
* When editing tags, the existing tags of the Client will be removed, i.e. adding of tags is not cumulative
* You can remove all of the Client’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `cedit 1 n/Janie Doe` Edits the name of the 1st Client to be `Janie Doe`
*  `cedit 1 t/`Removes all of the tags of the 1st Client

### Locating clients by name: `cfind`

Finds clients whose names contain any of the given keywords.

Format: `cfind KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g. `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched
* Partial names will be matched e.g. `Han` will match `Hans`
* Clients matching any substring will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `cfind John` returns `john` and `John Doe`
* `cfind alex david` returns `Alex Yeoh`, `David Li` as shown in the image below. <br>

<img src="images/findAlexDavidResult.png" alt="result for 'find alex david'" width="400" height="400" />


### Deleting a Client : `cdel`

Deletes the specified client from the clients list.

Format: `cdel INDEX`

* Deletes the Client at the specified `INDEX`.
* The index refers to the index number shown in the displayed Client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `clist` followed by `cdel 2` deletes the 2nd Client in the address book.
* `cfind Bernice` followed by `cdel 1` deletes the 1st Client in the result of the `cfind` command.

* If the 2nd session in the Client List is not associated with any schedule, `clist` followed by `cdel 2` will delete the session
* If the 2nd session in the Client List is associated with one or more schedules, 
`list` followed by `cdel 2` will return an error message 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    To force delete a client (and his associated schedules), pass in the optional force flag after the `INDEX`.
    Any string after the force flag (`f/`) will be ignored.
</div>

Examples:
* If there are one or more associated schedules associated with the 2nd session in the Client List, 
  `list` followed by `cdel 2 f/` will delete all schedules associated with the 2nd session, then delete the session 
  itself

### Viewing a Client : `cview`

Viewing the specified client from the clients list.

Format: `cview INDEX`

* Views the Client at the specified `INDEX`. The selected client will be displayed in the main window. 
* The index refers to the index number shown in the displayed Client list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `clist` followed by `cview 2` opens the 2nd Client in the address book.
* `cfind Bernice` followed by `cview 1` opens the 1st Client (Bernice) in the results of the `cfind` command.
   The result of these commands is shown in the image below.

<img src="images/cview_sample.png" alt="result for 'cview 1'" width="100%"/></br>
  > This profile window will be updated when more functionalities are available.

---
## Session-related Keywords 
All of session's related keyword is described in this section. All of the commands here will interact with the 
session panel which is located at the right of this programme. 

<img src="images/SessionPanel.png" alt="session_panel" width="250" height="450" />

The `ALL` at the top of this panel represents the current settings of session view. You can change the period 
of session view using [`sview`](#viewing-sessions-within-period-sview) command. 

### Adding a Session: `sadd`

Creates a session.

Format: `sadd g/GYM_NAME ex/EXERCISE_TYPE at/START_TIME t/DURATION`

* Start time should be of format "dd/MM/yyyy HHmm"
* Duration is in minutes
* Duration should be a positive integer (larger than 0)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    Session should not overlap with previously created session. Otherwise, the programme will not allow the
    session to be created. 
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

Format: `sdel INDEX [f/]`

* Deletes the Session at the specified `INDEX`.
* The index refers to the index number shown in the displayed Session list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* If there are no schedules with the 2nd session in the session List, `list` followed by `sdel 2` will delete the session
* If there are one or more associated schedules associated with the 2nd session in the Session List, 
`list` followed by `sdel 2` will return an error message 

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    To force deletion of session (and all associated schedules), pass in the optional force flag after the `INDEX`. 
    Any string after the force flag (`f/`) will be ignored.
</div>

* If there are one or more associated schedules associated with the 2nd session in the Session List, 
  `list` followed by `sdel 2 f/` will delete all schedules associated with the 2nd session, then delete the session 
  itself
  
  
### Viewing Sessions within Period: `sview`
You can filter the Session List to view Sessions within requested period to manage your sessions.

Format: `sview p/PERIOD`
 * Filters the Session List to only display those within the specified period.
 * Right above the Session List, you can find the name of the period you are viewing.
 * The recognized periods are as follows:
 
 | Period | Sessions displayed |
 | -------- | -------- |
 | all| All sessions (including past ones)|
 | future | All sessions that have not started|
 | past | All sessions that have already ended|
 | week | All sessions within the next 7 days|
 | `+[x][unit]` | Sessions within next x time units|
 | `-[x][unit]` | Sessions within past x time units|
 
 * The recognised units are as follows:

 | Unit | Time unit parsed |
 | -------- | -------- |
 | d | day |
 | w | week |
 | m | month |
 | y | year |
 
  * case insensitive
 
<img src="images/sview_sample.png" alt="result for 'sview p/week'" height="500" width="500"/></br>

Sample picture of the result of running `sview p/+2week`

Examples:
 
* `sview p/all` Displays all sessions.
* `sview p/+0D` Display all sessions today.
* `sview p/-1d` Display sessions from the past 1 day to today (yesterday and today).
* `sview p/+2w` Display sessions from today to 2 weeks later. (e.g. If today is Friday,
        display from today to the Friday that falls 2 weeks later).
---

## Schedule-related Keywords 
All of schedule's related keywords are described in this section. All of the commands here will interact with the 
session panel which is located at the right of this programme. To check if you had already scheduled a session with a client, 
you may check if the session contains the client's name. 

<img src="images/SchedulePanel.png" alt="schedule_panel" width="250" height="450" />

If the name is shown in red, it represents that this client had not paid
for his session, otherwise, it would be indicated in green. 

### Adding a Schedule: `schadd`

You can schedule your Client to a Session.

Format: `schadd c/CLIENT_INDEX s/SESSION_INDEX`

* This will create a Schedule associated with the specified Client and Session.
* The Client is specified by `CLIENT_INDEX`, and the Session is specified by `SESSION_INDEX`.
* You can see that on the Session List, the Client's name will be displayed under the specified Session's detail.
* On the Client List, you might also see a change in the Client's "Next Session" depending on the date and time of the Session.
* `CLIENT_INDEX` refers to the index number shown in the displayed Client List. The index **must be a positive integer** 1, 2, 3, … .
* `SESSION_INDEX` refers to the index number shown in the displayed Session List. The index **must be a positive integer** 1, 2, 3, … .

Examples:

* `schadd c/1 s/1` Schedules the first Client in the Client List with the first Session in the Session List

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
    A Schedule should not overlap with a previously created Schedule.
</div>


Examples:

* You cannot invoke `schadd c/1 s/1` right after invoking `schadd c/1 s/1`  since the two Schedules are overlapping.
* It is possible to have multiple Schedules of the same Session time as long as those Schedules are not associated with the same Client. This simply means that there are multiple Clients attending the Session.

### Editing a Schedule: `schedit`

Edits the details of the Schedule identified by the client index and session index.

Format: `schedit c/CLIENT_INDEX s/SESSION_INDEX [us/UPDATED_SESSION_INDEX] [pd/PAYMENT_STATUS] [r/REMARK]`

* Edits the Schedule that consists of the client and session indicated by `CLIENT_INDEX` and `SESSION_INDEX`
* `CLIENT_INDEX` refers to the index number shown in the displayed client list. The index **must be a positive integer** 1, 2, 3, …
* `SESSION_INDEX` and `UPDATED_SESSION_INDEX` refers to the index number shown in the displayed session list. The index **must be a positive integer** 1, 2, 3, …
* `PAYMENT_STATUS` can either be `paid` or `unpaid`
* `REMARK` can be any string
* At least one of the optional fields must be provided
* Existing values will be updated to the input values

Examples:
*  `schedit c/1 s/1 us/2` Edits the Schedule containing client index 1 and session index 1 to be `SESSION 2`.
*  `schedit c/1 s/1 pd/paid` Edits the Schedule containing client index 1 and session index 1 to be paid.
*  `schedit c/1 s/1 r/ did 5 pushups` Edits the Schedule containing client index 1 and session index 1 to have remark 
of doing 5 pushups.
* `schedit c/1 s/1 r/` Clear the Schedule containing client index 1 and session index 1 remarks.

### Deleting a Schedule: `schdel`

You can unschedule a Client from a Session.

Format: `schdel c/CLIENT_INDEX s/SESSION_INDEX`

* This will delete the Schedule associated with the specified Client and Session.
* The Client is identified by `CLIENT_INDEX`, and the Session is identified by `SCHEDULE_INDEX`.
* `CLIENT_INDEX` refers to the index number shown in the displayed Client List. The index **must be a positive integer** 1, 2, 3, … .
* `SESSION_INDEX` refers to the index number shown in the displayed Session List. The index **must be a positive integer** 1, 2, 3, … .

Examples:

* `schdel c/1 s/1` will delete the Schedule associated with the first Client in the Client List and first Session in the Session List.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.


--------------------------------------------------------------------------------------------------------------------


## Command Summary

| Action | Format | Example |
| -------- | -------- | --------- |
| Open Home Page | `home` | `home`| 
| Open Help Window | `help` | `help`|
| Clear all data | `clear` | `clear` |
| Exit this programme | `exit` | `exit`|
| Add Client Info | `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`| `cadd n/Jane Doe p/91234567 e/jane@gmail.com`|
| Edit Client Info | `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`| `cedit 1 n/Janie Doe`|
| Delete Client Info |`cdel INDEX [f/]` |`cdel 1`|
| List All Clients | `clist`  |  `clist`  |
| View a Client's Full Profile | `cview INDEX` | `cview 1`|
| Find Client by Name | `cfind KEYWORD [MORE_KEYWORDS]`| `cfind John Doe`|
| Add a Session | `sadd g/GYM_NAME ex/EXERCISE_TYPE at/START_TIME t/DURATION` | `sadd g/Machoman Gym ex/Endurance at/29/09/2020 1600 t/120` |
| Update Session Info |`sedit INDEX g/GYM_NAME at/START_TIME t/DURATION ` | `sedit 1 g/Machoman at/29/09/2020 1600 t/120`|
| Delete a Session |`sdel INDEX [f/]` | `sdel 1` |
| View Sessions within Period|`sview p/PERIOD ` | `sview p/all`|
| Create a Schedule |`schadd c/CLIENT_INDEX s/SESSION_INDEX`| `schadd c/1 s/3`|
| Edit a Schedule |`schedit c/CLIENT_INDEX s/SESSION_INDEX [us/UPDATED_SESSION_INDEX] [pd/PAYMENT_STATUS]`| `schedit c/1 s/1 us/1 pd/paid |
| Delete a Schedule |`schdel c/CLIENT_INDEX s/SESSION_INDEX`  | `schdel c/2 s/3` |


### Acknowledgement
Icon made by Freepik from [flaticon](www.flaticon.com)
