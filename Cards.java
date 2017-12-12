/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vingt.et.un_main;

/**
 *
 * @author aliaaramzani
 */
public class Cards {

        private int rank;//rank of a card
        private int suit;//suit of a card
        private int value;//value of a card
        
        
        
        private int currentCard;    //index of card next to be dealt with
        
        //CARDS
        private static String[] ranks = {"Joker","Ace","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Jack","Queen","King"};
        private static String[] suits = {"\u2663Clubs\u2663","\u2666Diamonds\u2666","\u2665Hearts\u2665","\u2660Spades\u2660"};
        
        
        
        
        /*Created with an integer that represents a spot in the String array ranks and the String array suits. This represents
 the rank and suit of an individual card*/

        Cards(int suit, int values)
        {
            this.rank=values;
            this.suit=suit;
        }

        
        
        
        
//Returns the string version of a card

        public String toString()
        {
            return ranks[rank]+" of "+suits[suit];
        }



//Returns the rank of a card

public int getRank()
        {
            return rank;
        }



//Returns the suit of a card

public int getSuit()
        {
            return suit;
        }




//Returns the value of a card
//jack, queen, or king the value is ten
//Aces are set to 11; altho it can be 1 too

public int getValue()
        {
            if(rank>10)
            {   value=10;   }
            
            else if(rank==1)
            {   value=11;   }
            
            else    {   value=rank; }
    
            return value;
        }




//Sets the value of a card

public void setValue(int set)
        {   value = set;    }

}
