//Author: Marisa Paone

package cs520.hw5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Database {
	
	//to connect
	private static final String URL = "jdbc:mysql://metcs520.mysql.database.azure.com:3306/mpaone";
	private static final String USERNAME = "mpaone@metcs520";
	private static final String PASSWORD = "metcs520";
	
	public void insertPlayers( Collection<Player> players)throws DatabaseException {
		
		Connection conn = null;
		Statement stmt = null;
		//trying to connect to database
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
		//looping through players to delete and then add the players
			Iterator<Player> itr = players.iterator();
			while (itr.hasNext()) {
				Player currentPlayer = itr.next();
				String query = "delete from players where number = '" + currentPlayer.getNumber() + "'";
				stmt.executeUpdate(query);
				
				query = String.format("insert into players values ('%d', '%s', '%s', '%s')", currentPlayer.getNumber(), currentPlayer.getName(), currentPlayer.getPosition(), currentPlayer.getYear());
				
				stmt.executeUpdate(query);
				
				System.out.println ("Inserting player into database: " + currentPlayer);
				
			}
		}
		catch (Exception e) {
			throw new DatabaseException(e);
			
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
		
		public Map<String,Player> selectPlayers() throws DatabaseException{
			
			Map<String,Player> players = new HashMap<String,Player>();
			Connection conn = null;
			Statement stmt = null;
			
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				stmt = conn.createStatement();
				
				//Iterating through resultSet to instantiate a new player and add its data
				ResultSet rs = stmt.executeQuery("Select * from players");
				
				while (rs.next()) {
					
					Player currentPlayer = new Player();
						
					currentPlayer.setNumber(rs.getInt("NUMBER"));
					currentPlayer.setName(rs.getString("NAME"));
					currentPlayer.setPosition(rs.getString("POSITION"));
					currentPlayer.setYear(rs.getString("YEAR"));
					players.put(currentPlayer.getName(), currentPlayer);
				}				
			}
			catch (Exception e3) {
				throw new DatabaseException(e3);			
			}
			finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e4) {
					e4.printStackTrace();
				}
			}
			return players;
			//returning map
		}
		
		
		
}
		


