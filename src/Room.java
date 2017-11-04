import java.util.*;
import java.io.*;

// small to do:
// fix reading name in from commandline
// if distances are equal what to do
// output all within a specified weight


public class almostComplete{
	private static ArrayList<Person> graph = new ArrayList<Person>();

	public static class Person{
		private String name;
		private int distance;
		private int prediction;
		private Person predecessor; //like pi in book
		private ArrayList<Person> adj = new ArrayList<Person>();


		Person(){
			this.name = "";
			this.distance = 0;
			this.prediction = -1;
			this.predecessor = null;
			//System.out.println("New empty person made ");
		}

		Person(String name){
			this.name = name;
			this.distance = 0;
			this.prediction = -1;
			this.predecessor = null;
		}

		Person(String name, int distance){
			this.name = name;
			this.distance = distance;
			this.prediction = -1;
			this.predecessor = null;
		}

		public void name(String name){
			this.name = name;
			//System.out.println("Name set to :"+ this.name );
		}

		public void addFriend(String name, int distance){
			Person neighbor = new Person(name, distance);
			adj.add(neighbor);
			//System.out.println("Friend : "+ neighbor.getName() + " added" );
		}

		public void setPi(Person before){
			this.predecessor = before;
		}

		public void setPrediction(int prediction){
			this.prediction = prediction;
		}

		public String getName(){
			return this.name;
		}

		public Person getFriend(){
			return adj.remove(0);
		}

		public int getDistance(){
			return this.distance;
		}

		public void printFriends(){
			for(Person fri: adj){
				int i = 0;
				System.out.println("friend = " + fri.getName() + " distance = " + fri.getDistance());
			}
		}

		public int getPrediction(){
			return this.prediction;
		}

		public boolean sameAs(Person test){
			if (this == test){
				return true;
			}
			return false;
		}

		public ArrayList<Person> getAllFriends(){
			return adj;
		}
	}

  public static void main(String[] args) throws FileNotFoundException{
				if(args.length > 3 || args.length < 1){
					System.out.println("Need 3 arguments");
					return;
				} else {
					File file = new File(args[0]);
						//String sourcePerson = args[1];
						readFile(file);
						showThingsWork();
						System.out.println("Person source is " + graph.get(0).getName());
						dijkstra(graph.get(0));
				}
	}

	//fix reading name from command line
	public static Person find(String name){
		int index = 0;

		Person current = new Person();
		while(!graph.isEmpty()){
			current = graph.get(index);
			if(name == current.getName()){
				System.out.println("current is found and " + current);
				return current;
			} else {
				index++;

			}
		}
		return current;
	}

	public static void readFile(File file)throws FileNotFoundException{
    Scanner input = new Scanner( file );
		while(input.hasNextLine()){
			String data = input.nextLine();
			//System.out.println("data = " + data);
			addEdge(data);
		}
	}

	public static void addEdge(String data){
		String edge = data;
		boolean alreadyExists = false;
		//System.out.println("edge = " + edge);

		//gets sourcePerson from line of data
		int split = edge.indexOf(' ');
		String source = edge.substring(0, split);
		//System.out.println("source = " + source);

		//removes sourcePerson from line of data
		edge = edge.substring(split + 1, edge.length());
		//System.out.println("edge then = " + edge);
		split = edge.indexOf(' ');

		//gets targetPerson from line of data
		String target = edge.substring(0, split);
		//System.out.println("target = " + target);
		//gets weight from line of data
		edge = edge.substring(split + 1, edge.length());
		//System.out.println("edge then = " + edge);
		int weight = Integer.parseInt(edge);
		//System.out.println("weight then = " + weight);

		//checks if the target already exists in the queue
		if(!graph.isEmpty()){
		for(int i = 0; i < graph.size(); i++){
			Person preExisting = graph.get(i);
			alreadyExists = (preExisting.getName().contains(source));

			//System.out.println("preExisting.getName().contains(source) = " + (preExisting.getName().contains(source)) );

			//if target exists only add friend and weight to existing list
			if(alreadyExists){
				preExisting.addFriend(target, weight);
				i = graph.size();
			}
		}
		}
			//otherwise name the new node
			//System.out.println("alreadyExists = " + alreadyExists);

			if(alreadyExists == false || graph.isEmpty()){
				//System.out.println("alreadyExists = "+ alreadyExists);
				Person node = new Person(source);
				node.addFriend(target, weight);
				graph.add(node);
			}
		}

  public static void showThingsWork(){
		Person test = new Person();
		for(int i = 0; i < graph.size(); i++){
			test = graph.get(i);
			System.out.println();
			System.out.println("Person " + i + " = " + test.getName());

			if(test.getAllFriends() != null){
				test.printFriends();
			}
		}
	}

	/*
	//Dijstras Algorithm
	public static ArrayList dijkstra(ArrayList Graph, Person source, Person target){

		ArrayList<Person> shortestPath = new ArrayList<Person>();
		ArrayList<Person> qToVisit = new ArrayList<Person>();
		int i = 0;

		for(Person p: graph){
			if(graph.get(i).getName() != source.getName()){
				qToVisit.add(graph.get(i));
				i++;
			} else {
				qToVisit.add(0, graph.get(i));
				i++;
			}
		}

		i = 0;

		while(!qToVisit.isEmpty()){
			//u = nearest neighbor to source(or current person)
			Person u = qToVisit.remove(i);

			//add u to list shortestPath
			shortestPath.add(u);
			ArrayList<Person> adj = i.getAllFriends();
			//for each of the neighbors of u
			for(Person adjacent: adj)
				//perform relax(Person u, nearest person, )

		}
		return shortestPath;
	}

	//before passing first here remember to check first.distance
	//also make sure you can change by reference
	public static void relax(Person first, Person second, int distanceTravelled){
		first.setPrediction(distanceTravelled);
			if(second.getPrediction() < 0 || second.getPrediction() > distanceTravelled + second.getDistance()){
				second.setPrediction(first.getPrediction() + second.getDistance());
				second.setPi(first);
			}
			//return something?
	}

*/
	public static ArrayList dijkstra(Person source){
		ArrayList<Person> q = new ArrayList<Person>();
		ArrayList<Person> shortestPath = new ArrayList<Person>();

		Person current = source;
		Person nearest = getNearest(source.getAllFriends());

		for(int i = 0; i < graph.size(); i++){
			q.add(graph.get(i));
		}
		int index = 0;

		while(!q.isEmpty()){


			System.out.println("we've been through the while loop " + index + " times");
			shortestPath.add(current);
			current = getNearest(current.getAllFriends());

			for(int k = 0; k < q.size(); k++){
				if(q.get(k).getName() == current.getName()){
					shortestPath.add(q.remove(k));
				}
			}
			//current = getNearest(current.getAllFriends());
		}
		System.out.println("People in shortestPath");
		for(Person p: shortestPath){
			System.out.println(p.getName());
		}
		return shortestPath;
	}

	//find node with shortest distance
	public static Person getNearest(ArrayList<Person> people){

		int i = 0;
		Person current = people.remove(i);
		int distance = current.getDistance();
		Person closest = new Person();
		for(Person p: people){
			if(p.getDistance() < distance){
				distance = p.getDistance();
				closest = p;
			}
		}
		System.out.println("closest is : " + closest.getName());
		return closest;
	}

}
