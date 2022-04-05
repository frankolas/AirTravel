package airportGraphing;

import edu.princeton.cs.algs4.*;

/**Reads in csv file that depicts a map of major train connections.
 * Determines the fastest connections from the departure to the destination.
 * 
 * @author Frank Garcia
 *
 */
public class Airports {

	public static void main(String[] args) {
		String fileName = "src/airportGraphing/Resources/TrainConnections.csv";
		String delimiter = ",";
		SymbolEdgeWeightedGraph connections = new SymbolEdgeWeightedGraph(fileName, delimiter);
		
		StdOut.println("Part A:");
		StdOut.println("=======");
		StdOut.print("Departure: ");
		String departure = StdIn.readLine();
		StdOut.print("Destination: ");
		String destination = StdIn.readLine();
		if (!connections.contains(departure) && !connections.contains(destination)) {
			StdOut.printf("There is no connection to %s nor to %s.", departure, destination);
		} else if (!connections.contains(departure) || !connections.contains(destination) || 
				(connections.contains(departure)) && connections.contains(destination)) {
			if (!connections.contains(departure)){
				replaceDeparture(connections, departure); 
			} else {
				replaceDestination(connections, destination);
			}
			StdOut.println();
			StdOut.println("Part B:");
			StdOut.println("=======");
			StdOut.println("Total Distance Traveled:");
			distanceTraveled(connections, departure, destination);
		} 
	}

	/**Asks the user to replace the departure input that does not exist on the graph.
	 * 
	 * @param connections	graph used for checking if train station exists
	 * @param station	station being replaced
	 */
	private static void replaceDeparture(SymbolEdgeWeightedGraph connections, String station) {
		while(!connections.contains(station)) {
			StdOut.printf("There is no train connection to %s.%n", station);
			StdOut.print("Departure: ");
			station = StdIn.readLine();
		}
	}
	
	/**Asks the user to replace the destination input that does not exist on the graph.
	 * 
	 * @param connections	graph used for checking if train station exists
	 * @param station	station being replaced
	 */
	private static void replaceDestination(SymbolEdgeWeightedGraph connections, String station) {
		while(!connections.contains(station)) {
			StdOut.printf("There is no train connection to %s.%n", station);
			StdOut.print("Destination: ");
			station = StdIn.readLine();
		}
	}
	
	/**Provides the total distance of the travel time between all connections.
	 * 
	 * @param connections	symbol edge weighted graph with graph of train connections
	 * @param departure		train connection the user is departing from
	 * @param destination	train destination the user is traveling to
	 */
	private static void distanceTraveled(SymbolEdgeWeightedGraph connections, String departure, String destination) {
		double totalDistance = 0.0;
		DijkstraUndirectedSP finder = new DijkstraUndirectedSP(connections.graph(), connections.indexOf(departure));
		totalDistance =+ finder.distTo(connections.indexOf(destination));
		
		StdOut.println();
		StdOut.printf("Total amount of time from %s to %s: %.01f KM", departure, destination, totalDistance);
	}
}
