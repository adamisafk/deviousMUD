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

' "javax.persistence.jdbc.user" value="root" '
' "javax.persistence.jdbc.password" value="password" '

### Troubleshooting
- If all else fails re-pull down the project directory, set mysql root user password to 'password' and use the root

- run: `mysql -u root -p deviousMUD < sqldump.sql`

### If you wish to run the JPAUtil tests, please re-run `mysql -u <your_username> -p deviousMUD < sqldump.sql`, this ensures that the database is fresh because some of the tests rely on specific data being present.

NOTE: The developers worked on macOS, so this hasn't been tested on windows or linux. Also, they used Intellij!
