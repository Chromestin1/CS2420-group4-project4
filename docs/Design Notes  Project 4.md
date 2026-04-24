## Design Notes  For Project 4: Monopoly Simulation

**Stacks for card decks**  
 We went with Stack\<String\> for both Chance and Community Chest because it resembles how a real deck works. It draws from the top, reshuffle when empty. One thing worth mentioning is that our shuffle is probably more random than a real person shuffling, so results may differ slightly from actual gameplay.

**Money Cards**  
If cards that only handle money are drawn from the decks, they are disregarded. 

**Board Travel**   
Modulus 40 is used to continue traveling through the board array. 

**HashMap for landing counts**   
We used a HashMap\<String, Integer\> keyed by square name to track landings. It's readable and easy to print. 

**Jail vs Just Visiting**   
Index 10 is "Just Visiting/Jail" and getting sent to jail counts as landing here. Index 30 "Go To Jail" never gets counted because the player is immediately moved. 

**Single static Board**   
We only have one Board for the whole simulation and just reset it between runs. It happened organically \- This wasn't a deliberate design decision but it does keep things simple. For running it does mean restartGame() has to be called before every run and for testing purposes the board needs to be set up and taken down. 

**JailStrategy as one class**   
We considered making two separate subclasses but it honestly felt like overkill for two methods. One class with a name field gets the job done.

**Results as a record**    
Results just needs to hold three things and doesn't need any behavior, so a record was the obvious choice. 

**Money not tracked**  
 Money handling logic is out of scope here. If someone wanted to add it down the road it wouldn't be too hard. Board could track property ownership, TurnEngine could handle rent, and JailStrategy could actually charge the $50 instead of just assuming it was paid.  
