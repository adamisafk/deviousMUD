# deviousMUD
A simple text-based dungeon crawler, with database integration. The database is used to store rooms, items, chests, npcs and scores. 

There are currently 5 rooms. Each of the rooms have randomly assigned NPCS and CHests, which carry items predefined in the database.
The player can move around the rooms and interact with NPCs, this includes fighting the NPCs. If a player defeats an NPC, they obtain the gold belonging to that npc. Players can take items from chests, and then equip different items from their inventory.

The aim of the game is to find the boss room and defeat all of the npcs contained within it. The player then has to option to leave the dungeon, and save their score to the database. The player's score is governed by the amount of gold they've obtained by killing NPCs.
                                                                                                                                                             

## Database Install
Ensure you have mysql installed and running on your machine and generate an empty database called 'deviousMUD'.

In the root directory of the project, run:
`mysql -u <your_username> -p deviousMUD < sqldump.sql`

(If this doesnt work type ls and make sure sqldump.sql is in the current directory)

THEN (in project directory):

- Go to src -> resources -> META-INF -> persistence.xml :
- set the username to <your_username>
- set password to <your_password>

HINT find this:
' "javax.persistence.jdbc.user" value="root" '
' "javax.persistence.jdbc.password" value="password" '

### Troubleshooting
- If all else fails re-pull down the project directory, set mysql root user password to 'password' and use the root

- run: `mysql -u root -p deviousMUD < sqldump.sql`

### If you wish to run the JPAUtil tests, please re-run `mysql -u <your_username> -p deviousMUD < sqldump.sql`, this ensures that the database is fresh because some of the tests rely on specific data being present.

NOTE: The developers worked on macOS, so this hasn't been tested on windows or linux. Also, they used Intellij!

## About this Repository
To view current sprints (and closed sprints) please go to the projects tab. At the time of writing you will find one closed project (our first 'sprint'). In the menu you will find the sprint goal for that sprint.
To view the sprint backlog board follow the hyperlink on the project name. This will take you to a kanban-esq board where user stories and tasks are stored. Most user stories have sub tasks attached to them (written in the comments), an estimation and acceptance criteria.

The wiki is a useful place to look, it contains useful documentation including uml diagrams, database diagrams, descriptive text for rooms, chests and items, and other useful information.

## !SPOILER ALERT!
To win the game, after starting a new game type the following commands:

'move east', 'move south', 'move east'

- you will now find yourself in the 'boss' room (you should recieve a message alerting you of this)-

Next you must defeat all npcs in the room, type:
'attack'
select an npc to kill, and repeat until all npcs in the room are dead. (Note: spawning of npcs is random)

you can now type 'leave' and you'll be prompted to save your highscore to the database.

Note: if this becomes too tedious, type 'quit' at any time to leave the game, you'll then be prompted to save your highscore.

## Inconsistencies 
The room, item, chest and npc descriptions may not always be accurate. For example, as the chests in the room are randomly generated a room may have have a "wooden chest" one play-through and a "wooden barrel" another play-through. However the room descriptions mention objects in the room, these are purely descriptive and are not accurately describing what's actually in the room. This is the same for doors, some room descriptions will not mention that there is a door in a place where there is. To get around this just try to move in any direction and you will have a prompt that tells you whether or not you successfully get through a door.
