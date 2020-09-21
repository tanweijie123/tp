# CS2103T T13 -3 Notes

###### tags: `public`

## v1.1

### User stories, User persona

[User Stories - Google Sheet](https://docs.google.com/spreadsheets/d/1Wnxh09G_kulcrVm8KdVAO8Plp0QbcuYURpJgDAsRJ8A/edit?usp=sharing)

[User Persona - Google Docs](https://docs.google.com/document/d/10sCbxKIGwXaMuc7OAX-FBAqKdgKGCFyxBUurQ1taiLY/edit?usp=sharing)


### Syntax Help
| Type     | Acronym  | 
| -------- | -------- | 
| Client   | c        | 
| Session  | s        |

### Command Summary 
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



### Feature List
1. add, update, remove clients
2. add, update, remove sessions
3. assign, unassign client to gym session

### Draft UG
![](https://i.imgur.com/6V6yt9b.png)

#### Features / Commands summary

##### navigation
- Viewing help: `help`
- Exiting a program: `exit`



##### clients
- Adding a client: `cadd n/NAME p/PHONE_NUMBER e/EMAIL [t/TAG]`
    - calls "Adding a session" flow (optional)
- Updating a client:  `cedit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]`
- Deleting client: `cdel INDEX`
- Listing all clients: `clist`
- Find client by name: `cfind KEYWORD [MORE_KEYWORDS]`

##### sessions
- Adding a session: `sadd s/SESSIONTYPE dt/DATETIME dur/DURATION g/GYM_NAME`
- Schedule a client to a session: `sched add c/CLIENT_INDEX s/SESSION_INDEX`
- Deschedule a client a session : `sched rm c/CLIENT_INDEX s/SESSION_INDEX` 
- Updating a session: `sedit INDEX [s/SESSIONTYPE] [dt/DATETIME] [dur/DURATION] [g/GYM_NAME]`
- Deleting a session: `sdel INDEX`

### Draft DG
![](https://i.imgur.com/XahU2X1.png)




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


## v1.25 and beyond (Stretch Goals)
- should every object have its own unique identifier, some sort of 5-digit num (?) if add, edit, delete session need session index then yes, need unique id e.g. 00001
- view all session by client
- view all session by date / today: `sfind d/DATE`
- Search client by initials 
- Implement gym object (Name, Address, Phone Number)
- Add gym `gadd n/NAME a/ADDRESS p/PHONE_NUMBER`
- update gym location `gedit`
- delete gym location `gdel`
- list all gym locations `glist`