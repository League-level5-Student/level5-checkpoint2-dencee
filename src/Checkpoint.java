import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Checkpoint {
	static int num = 0;
	
	public static void main(String[] args) {
		/*
		 * The auto-mgp.data file contains the miles-per-gallon data on various different types of cars.
		 * Each line of the file provides the data for one CarMPGEntry object.
		 * (source: http://archive.ics.uci.edu/ml/datasets)
		 * 
		 * Use the method provided to get an ArrayList of CarMPGEntry objects. Convert the ArrayList into a Stream.
		 * Using streams, perform the following:
		 * 
		 * 1. Print the entire list.
		 * 
		 * 
		 * 2. Print the miles per gallon of each entry.
		 * 
		 * 
		 * 3. Print alphabetized car names of the list.
		 * 
		 * 
		 * 4. Print the list with all 8 cylinder cars removed.
		 * 
		 * 
		 * 5. Print only the cars with "toyota" in the name.
		 */

	    Stream<CarMPGEntry> c;
	    
//	    System.out.println("---------- Print entire list ----------------");
//		c = readCarMPGEntryDataFromFile().stream();
//		c.forEach( (CarMPGEntry e) -> System.out.println( e.carName ) );
		
//		
//		c.forEach( new Consumer() {
//
//			@Override
//			public void accept( Object t) {
//				num++;
//				CarMPGEntry t2 = (CarMPGEntry)t;
//				System.out.println( t2.carName );
//			}
//			
//		});
//		
//		System.out.println("----------Sort by mpg ----------------");
//		
//		c = readCarMPGEntryDataFromFile().stream();
//		    c.sorted( ( t1, t2 ) -> (int)( t1.mpg - t2.mpg ) )
//		    .forEach( (CarMPGEntry e)-> System.out.println( e.mpg + " " + e.carName ) );
//		
//		System.out.println("---------- Sort by Car name ----------" );
//		
//		c = readCarMPGEntryDataFromFile().stream();
//		    c.sorted( ( t1, t2 )-> t1.carName.compareToIgnoreCase(t2.carName) )
//		    .forEach( (CarMPGEntry e)-> System.out.println( e.mpg + " " + e.carName ) );
	    
//	    System.out.println("---------- all 8 cylinders removed ----------------");
//	        
//	    c = readCarMPGEntryDataFromFile().stream();
//	        c.filter( ( t1 ) -> t1.cylinders != 8 )
//	        .sorted( ( t1, t2 ) -> t1.cylinders - t2.cylinders )
//	        .forEach( (CarMPGEntry e) -> System.out.println( e.cylinders + " " + e.carName ) );

//	    System.out.println("---------- only Toyota ----------------");
//	    
	    c = readCarMPGEntryDataFromFile().stream();
	    c.filter( ( t1 ) -> t1.carName.contains( "toyota" ) )
	        .sorted( ( t1, t2 ) -> t1.carName.compareTo( t2.carName ) )
	        .forEach( (CarMPGEntry e) -> System.out.println( e.carName ) );
	}
	
	public static ArrayList<CarMPGEntry> readCarMPGEntryDataFromFile(){
		ArrayList<CarMPGEntry> carList = new ArrayList<CarMPGEntry>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("auto-mpg.data"));
			
			String line = br.readLine();
			while(line != null) {
				String entry = "";
				ArrayList<String> splitEntry = new ArrayList<String>();
				for(int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if(c == ' ' || c == '\t') {
						if(entry.length() > 0) {
							splitEntry.add(entry);
							entry = "";
						}
					}else if(c =='\"') {
						i++;
						c = line.charAt(i);
						while(c != '\"') {
							entry += c;
							c = line.charAt(++i);
						}
						splitEntry.add(entry);
					}else {
						entry += c;
					}
				}
				
				CarMPGEntry cmpg = new CarMPGEntry();
				
				cmpg.mpg = Float.parseFloat(splitEntry.get(0));
				cmpg.cylinders = Integer.parseInt(splitEntry.get(1));
				cmpg.displacement = Float.parseFloat(splitEntry.get(2));
				try {
					cmpg.horsePower = Float.parseFloat(splitEntry.get(3));
					
				}catch(NumberFormatException e) {
					cmpg.horsePower = -1;
				}
				cmpg.weight = Float.parseFloat(splitEntry.get(4));
				cmpg.acceleration = Float.parseFloat(splitEntry.get(5));
				cmpg.modelYear  = Integer.parseInt(splitEntry.get(6));
				cmpg.origin = Integer.parseInt(splitEntry.get(7));
				cmpg.carName = splitEntry.get(8);
				carList.add(cmpg);

				line = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return carList;
	}
}
