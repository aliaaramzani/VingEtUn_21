/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vingt.et.un_main;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author aliaaramzani
 */
public class Deck {

//creates an array of 104 playing cards (2 decks)
    private final ArrayList<Cards> deck;
    Deck()
    {
        deck = new ArrayList<>();
        for(int suit=0; suit<4; suit++)      
        {   for(int cardFace=1; cardFace<=13; cardFace++)
            {   deck.add(new Cards(suit,cardFace));   }
        }
    }

    
    
    
    
    
    
//Shuffles the deck -- changes the indexes of 200 random pairs of cards in the deck
    public void shuffle()
    {   Random random = new Random();
        Cards temp;
        for(int i=0; i<200; i++)
        {   
            //swaps 2 random values of 2 diff index
            int index1 = random.nextInt(deck.size()-1);
            int index2 = random.nextInt(deck.size()-1);
            
            temp = deck.get(index2);
            deck.set(index2, deck.get(index1));
            deck.set(index1, temp);
        }
    }

    
    
    
    
//Draws a card from the deck
    public Cards drawCard()
    {   return deck.remove(0);  }

}