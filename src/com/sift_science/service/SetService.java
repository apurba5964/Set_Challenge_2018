/**
 * SetService.java 
 * Purpose: SetService class to simulate the set game.This class generates
 * list of all possible sets and largest disjoint sets collection  
 * and the cards that form the largest disjoint collection			
 *
 * @author Apurba Mahanta
 * @version 1.0 
 */
package com.sift_science.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.sift_science.models.Cards;

public class SetService {
	
	
	/**
	 * This method is used to call the createCardObject method to create card objects from the strings
	 * loaded from input.It also call the countSets method 
	 * 
	 * @param listOfCards ArrayList<Card> - list String loaded from input. 
	 * 
	 */
	public void generateSets(ArrayList<String> cardsInput) {
		
		
		ArrayList<Cards> cardsList = createCardsObject(cardsInput);
		
		countSets(cardsList);
	}

	
	
	
	/**
	 * This method is used to find all possible three card set based on the criteria specified by isSet method.
	 * 
	 * @param listOfCards ArrayList<Card> - list object loaded from input. 
	 * 
	 */
	private void countSets(ArrayList<Cards> cardsList) {
		
		ArrayList<ArrayList<Cards>> sets = new ArrayList<>();
		
		for(int i = 0;i<cardsList.size();i++) {
			for(int j =i+1;j<cardsList.size();j++ ) {
				for(int k=j+1;k<cardsList.size();k++) {
					if(isSet(cardsList.get(i),cardsList.get(j), cardsList.get(k))) {
						
						ArrayList<Cards> setCards = new ArrayList<>();
						setCards.add(cardsList.get(i));
						setCards.add(cardsList.get(j));
						setCards.add(cardsList.get(k));
						sets.add(setCards);
						
					}
				}
			}
		}
	
		
		System.out.println("Number of Sets "+sets.size());
		disJointSets(sets);
		
		
		
		
	}
	
	
	
	
	/**
	 * This method is used to find all maximum possible disjoint set. 
	 * maximum possible disjoint set is a list of sets with same number of distinct disjoint sets. 
	 * function prints the number of such sets and it also prints the cards in those sets one by one. 
	 * 
	 * @param allSets ArrayList<ArrayList<Card>> - list of sets. 
	 * @return None
	 */
	

	public void disJointSets(ArrayList<ArrayList<Cards>> allSets) {

		HashMap<Integer, ArrayList<Integer>> mapOfSetAndItsDisjoints = getSetCardsAndItsDisJointsSets(allSets);

		
		
		
		
		HashMap<Integer, ArrayList<Integer>> sameDisjointCount = new HashMap<Integer, ArrayList<Integer>>();

		for (Map.Entry<Integer, ArrayList<Integer>> entry : mapOfSetAndItsDisjoints.entrySet()) {

			int setIndex = entry.getKey();
			int countOfDisjointSets = entry.getValue().size();
			if (sameDisjointCount.containsKey(countOfDisjointSets))
				sameDisjointCount.get(countOfDisjointSets).add(setIndex);
			else {
				ArrayList<Integer> listOfSets = new ArrayList<Integer>();
				listOfSets.add(setIndex);
				sameDisjointCount.put(countOfDisjointSets, listOfSets);
			}

		}
		
		

		int disJointCount = 0;
		int maxDisjointCount = 0;

		for (Map.Entry<Integer, ArrayList<Integer>> entry : sameDisjointCount.entrySet()) {
			int disJointSetsCount = entry.getKey();
			int numberOfKeysWithDisJointSetsCount = entry.getValue().size();
			if (numberOfKeysWithDisJointSetsCount > maxDisjointCount) {
				disJointCount = disJointSetsCount;
				maxDisjointCount = numberOfKeysWithDisJointSetsCount;
			}

		}

		System.out.println("Largest Disjoint Sets Collection "+maxDisjointCount);
		System.out.println();
		
		
       
        
       
		for (int i : sameDisjointCount.get(disJointCount)) {
			ArrayList<Cards> getSet = allSets.get(i);
			for (Cards card : getSet) {
				System.out.println(card.toString());
			}
			System.out.println();
		}
       }
		

	

	/**
	 * This method is used to get a map of a given set and its disjoint sets. 
	 *  
	 * 
	 * @param allSets ArrayList<ArrayList<Card>> - list of sets. 
	 * @return mapOfSetAndItsDisjoints, HashMap<Integer, ArrayList<Integer>>
	 */
	private HashMap<Integer, ArrayList<Integer>> getSetCardsAndItsDisJointsSets(ArrayList<ArrayList<Cards>> allSets) {
		HashMap<Integer, ArrayList<Integer>> mapOfSetAndItsDisjoints = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < allSets.size(); i++) {
			for (int j = 0; j < allSets.size(); j++) {
				if (i != j) {
					ArrayList<Cards> currentSet = allSets.get(i);
					ArrayList<Cards> comparedSet = allSets.get(j);

					HashSet<String> getObjectNames = new HashSet<String>();
					for (Cards c : currentSet) {
						getObjectNames.add(c.toString());
					}
					for (Cards c2 : comparedSet) {
						getObjectNames.add(c2.toString());
					}

					if (getObjectNames.size() == 6) {
						if (mapOfSetAndItsDisjoints.containsKey(i))
							mapOfSetAndItsDisjoints.get(i).add(j);
						else {
							ArrayList<Integer> disJointSets = new ArrayList<Integer>();
							disJointSets.add(j);
							mapOfSetAndItsDisjoints.put(i, disJointSets);
						}
					}

				}

			}
		}
		return mapOfSetAndItsDisjoints;
	}


		
		
		
	/**
	 * This method is used to create the cards object array
	 *  
	 * 
	 * @param  List of input strings
	 
	 * @return List of Cards object
	 */	
		
	private ArrayList<Cards> createCardsObject(ArrayList<String> cardsInput) {
		ArrayList<Cards> cardsList = new ArrayList<>();
		for (String s : cardsInput) {
			Cards card = new Cards();
			s=s.trim();
			card.setInput(s);
			String[] stringArray = s.split("\\s+");
			
			String color=stringArray[0].trim().toLowerCase();
			String cardsProp =stringArray[1];
			
			card.setColor(color);
			
			if(cardsProp.contains("@") || cardsProp.contains("$") || cardsProp.contains("#")) {
				
				card.setShade("symbol-case");
			}else {
				if(Character.isUpperCase(cardsProp.charAt(0))) {
					
					card.setShade("upper-case");
				}else {
					card.setShade("lower-case");
				}
			}
			
			
			card.setNumber(cardsProp.length());
			
			if(cardsProp.contains("@") || cardsProp.contains("A") || cardsProp.contains("a"))
				card.setSymbol("A");
			
			if(cardsProp.contains("$") || cardsProp.contains("S") || cardsProp.contains("s"))
				card.setSymbol("S");
			
			if(cardsProp.contains("#") || cardsProp.contains("H") || cardsProp.contains("h"))
				card.setSymbol("H");
			
			
			
			cardsList.add(card);
		}
		
		return cardsList;
	}
	
	/**
	 * This method is used find if the triplet cards form a set 
	 *  
	 * 
	 * @param  Card a
	 * @param  Card b
	 * @param  Card c
	 * @return true/false, boolean
	 */
	
	boolean isSet(Cards a, Cards b, Cards c) {
		
        if (!((a.getNumber() == b.getNumber()) && (b.getNumber() == c.getNumber()) ||
                (a.getNumber() != b.getNumber()) && (a.getNumber() != c.getNumber()) && (b.getNumber() != c.getNumber()))) {
        	return false;
        }
        if (!((a.getSymbol() == b.getSymbol()) && (b.getSymbol() == c.getSymbol()) ||
                (a.getSymbol() != b.getSymbol()) && (a.getSymbol() != c.getSymbol()) && (b.getSymbol() != c.getSymbol()))) {
            return false;
        }
        if (!((a.getShade() == b.getShade()) && (b.getShade() == c.getShade()) ||
                (a.getShade() != b.getShade()) && (a.getShade() != c.getShade()) && (b.getShade() != c.getShade()))) {
            return false;
        }
        
        
       if(!(a.getColor().equalsIgnoreCase(b.getColor()) && b.getColor().equalsIgnoreCase(c.getColor())
        		|| !a.getColor().equalsIgnoreCase(b.getColor()) && !b.getColor().equalsIgnoreCase(c.getColor())
        		&& !c.getColor().equalsIgnoreCase(a.getColor())))
        	return false;
        
       
        return true;
    }

}
