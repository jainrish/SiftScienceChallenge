package com.sift.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.sift.model.Card;

public class Application {

	public static void main(String[] args) {
		Map<String, Card> cardMap = new HashMap<>();
		Set<String> cardsSet = new HashSet<>();
		Card[] cards = null;
		Application application = new Application();
		int setsCount=0;
		
		
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter the number of cards to enter:");
			int n = sc.nextInt();
			sc.nextLine();
			cards = new Card[n];
			
			for(int i=0;i<n;i++) {
				System.out.println("Enter card:");
				String[] line = sc.nextLine().split(" ");
				Card card = new Card(line[0], line[1]);
				cardMap.put(card.getCardID(), card);
				cards[i] = card;
			}
			
		}
		
		for(int i=0;i<cards.length-1;i++) {
			for(int j=i+1;j<cards.length;j++) {
				
				String thirdCardID = cards[i].getThirdCard(cards[j]);
				if(cardMap.containsKey(thirdCardID)) {
					String cardsCombinationID = String.valueOf(cards[i].getCardID() + cards[j].getCardID()+thirdCardID);
					if(!cardsSet.contains(cardsCombinationID)) {
						application.addAllSixCombinationsToSet(cardsSet, cards[i].getCardID(), cards[j].getCardID(), thirdCardID);
						setsCount++;
					}
				}
			}
		}
		List<String> cardsList = new ArrayList<>();
		cardsList.addAll(cardsSet);
		
		System.out.println(setsCount);
		List<String> maxDisjoint = new ArrayList<>();
		
		application.findMaxDisjointSets(cardsList, cardMap, new ArrayList<>(), 0, maxDisjoint);
		System.out.println(maxDisjoint.size());
		
		for(int i=0;i<maxDisjoint.size();i++) {
			String cardsCombinationID = maxDisjoint.get(i);
			System.out.println(cardMap.get(cardsCombinationID.substring(0, 4)));
			System.out.println(cardMap.get(cardsCombinationID.substring(4, 8)));
			System.out.println(cardMap.get(cardsCombinationID.substring(8, 12)));
			System.out.println();
		}
		
	}
	
	
	//recursion with backtracking to find maximum number of disjoint sets 
	public void findMaxDisjointSets(List<String> cardsList, Map<String, Card> cardsMap, List<String> disjoint, int idx, List<String> maxDisjoint) {
		if(cardsList.size()==idx) {
			if(disjoint.size()>maxDisjoint.size()) {
				maxDisjoint.clear();
				maxDisjoint.addAll(disjoint);
			}
			return;
		}
		
		if(maxDisjoint.size()>=disjoint.size()+cardsList.size()-idx) {
			return;
		}
		
		if(maxDisjoint.size()==cardsList.size()/3) {
			return;
		}
		
		String cardsCombinationID = cardsList.get(idx);
		
		Card card1 = cardsMap.get(cardsCombinationID.substring(0, 4));
		Card card2 = cardsMap.get(cardsCombinationID.substring(4, 8));
		Card card3 = cardsMap.get(cardsCombinationID.substring(8, 12));
		
		
		if(card1!=null && card2!=null && card3!=null) {
			cardsMap.put(card1.getCardID(), null);
			cardsMap.put(card2.getCardID(), null);
			cardsMap.put(card3.getCardID(), null);
			disjoint.add(cardsCombinationID);
			
			findMaxDisjointSets(cardsList, cardsMap, disjoint, idx+1, maxDisjoint);
			
			cardsMap.put(card1.getCardID(), card1);
			cardsMap.put(card2.getCardID(), card2);
			cardsMap.put(card3.getCardID(), card3);
			disjoint.remove(cardsCombinationID);
		}
		
		findMaxDisjointSets(cardsList, cardsMap, disjoint, idx+1, maxDisjoint);
	}
	
	//add all six combinations of three cardIDs to the set
	public void addAllSixCombinationsToSet(Set<String> cardsSet, String cardID1, String cardID2, String cardID3) {
		cardsSet.add(cardID1 + cardID2 + cardID3);
		cardsSet.add(cardID1 + cardID3 + cardID2);
		cardsSet.add(cardID2 + cardID1 + cardID3);
		cardsSet.add(cardID2 + cardID3 + cardID1);
		cardsSet.add(cardID3 + cardID1 + cardID2);
		cardsSet.add(cardID3 + cardID2 + cardID1);
	}
}
