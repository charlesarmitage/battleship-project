Backlog
--------

Deployment
----------

Player 1 Placing ships
----------------------

- Feature - Empty sea
    - ~~Display empty sea and text "Available: Patrol Boat, Destroyer, Battleship, Carrier"~~ - Done
    - ~~Tiny thin slice through application for deployment~~

- Feature - Placing battleship
    - ~~Given empty square sea grid (10*10) with borders~~
    - ~~When user touches in empty square to mark four points in a straight line~~
    - ~~A place battleship option is displayed~~

    - ~~Given four marked points in line in grid~~
    - ~~When a user selects to place battleship~~
    - ~~A battleship is displayed on top of the four marked points~~
    
- Feature - Offering to start game
 	- ~~Once all ships are placed offer to start the game~~

- Feature - Reset the grid
	- ~~When the reset button is pressed the sea is cleaned~~

Player 2 Placing ships
----------------------

- Feature - Offer player 2 to place ships
	- Once player 1 has placed ships.
		- Visually reset grid
		- Allow player to to place ships.
		- Allow player 2 to reset their deployment
		- Allow player 2 to start the game.

Playing game
------------

- Feature - Can target a cell 
	- An empty targetting grid is displayed
	- Pressing a cell selects it to be targeted.
	- Pressing cell again deselects it
	- Button displayed allowing player to fire on cell

- Feature - Can hit an empty cell
	- If a cell is empty the shot is marked on the targetting grid
	- The turn is over

- Feature - Can hit a ship cell
	- If the cell is occupied the shot is marked as a hit on the targetting grid
	- If the ship is destroyed the player is notified
	- If all the ships are destroyed the player has won the game.

- Feature - Can view own fleet status
	- Player can swipe to view their own fleet.
	- Fleet is displayed with targeted cells marked, ships marked and destroyed ship cells marked.
