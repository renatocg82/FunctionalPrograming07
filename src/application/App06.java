package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class App06 {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner scString = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = scString.nextLine();

		List<Product> prodList = new ArrayList<>();

		try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {

			String line = br.readLine();

			while (line != null) {

				String lineFields[] = line.split(",");
				String tempName = lineFields[0];
				Double tempPrice = Double.parseDouble(lineFields[1]);

				prodList.add(new Product(tempName, tempPrice));

				line = br.readLine();
			}

			Double avgNum;
			Double avg;
			avgNum = prodList.stream().map(x -> x.getPrice()).reduce(0.0, (x, y) -> (x + y));
			avg = avgNum / prodList.size();
			
			System.out.println("Average price: " + String.format("%.2f", avg));
			
			Comparator<String> comp = (string1, string2) -> string1.toUpperCase().compareTo(string2.toUpperCase());
			//creating a Comparator (String) - syntax =  objects to be comparable and function comparable .
			
			List<String> nameList = prodList.stream().filter(x -> x.getPrice() < avg)
			.map(x -> x.getName())
			.sorted(comp.reversed())
			.collect(Collectors.toList());
			
			nameList.forEach(System.out::println);

		} 
		catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());

		} 
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());

		}

		
		scString.close();
		sc.close();
	}

}
