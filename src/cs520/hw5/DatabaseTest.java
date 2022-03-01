//Author: Marisa Paone

package cs520.hw5;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;



public class DatabaseTest {

public static Player createPlayer(String number, String name, String position, String year) {
		
		//converts number to an int
		int numb = Integer.parseInt(number);
		
		//instantiates player as a variable and passing the name value
		Player player = new Player(name);
		
		//sets the number, position and year to player with mutator methods
		player.setNumber(numb);
		player.setPosition(position);
		player.setYear(year);
	
		return player;
	}
	
	public static void main(String[] args) {
	
		// the benefit that linkedhashset has over arraylist is elements are stored in a has table and ordered based on the order in which they are inserted. without any dupes.
		Collection<Player> players = new LinkedHashSet<Player>();
		
		Scanner scanner = null;
		
		//reading file
		try {
			File file = new File ("team.txt");
			scanner = new Scanner (file);
		
			//reads five lines from team.txt and passes the first four of five strings to createPlayer()
			while (scanner.hasNextLine()) {
			 
				String number = scanner.nextLine();
				String name = scanner.nextLine();
				String position = scanner.nextLine();
				String year = scanner.nextLine();
			
				//fetches the return value of createPlayer
				Player player = createPlayer(number, name, position, year);
				
				//adds player to arrayList
				players.add(player);
				
				scanner.nextLine();
				//System.out.println ();		
			}
			//instantiated database
			Database db = new Database();
			db.insertPlayers(players);
			Map<String,Player> selectedPlayers = db.selectPlayers();

			//Iterating through the selectedPlayers map
			Iterator<Map.Entry<String, Player>> mapIter = selectedPlayers.entrySet().iterator();
			while (mapIter.hasNext()) {
				Map.Entry<String, Player> entry = mapIter.next();
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			
			
		} catch (Exception e){
			//catches the exceptions if file isn't found
			e.printStackTrace();
			System.out.print("something went terribly wrong");
		
		} finally {
			
			//closes scanner
			if (scanner != null) {
				scanner.close();		
			}
		}
}
	
}
