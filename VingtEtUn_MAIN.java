/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vingt.et.un_main;
import java.util.*;
/**
 *
 * @author aliaaramzani
 */
public class VingtEtUn_MAIN {

    
    //USER DETAILS
    private static String name; //name of the user
    private static int cash;    //cash the user bets with
    private static int bet; //how much the user wants to bet
    
    //USER'S CARDS
    private static int AceCounter;  //how many aces are in the user's hand
    //ace = 1 or ace = 11 ---- up to the user
    private static ArrayList<Cards> hand;   //represents the user's hand
    private static int handvalue;   //the value of the user's hand
    
    
    public static void main(String[] args){
        
            //INTRO TO THE GAME -- NAME
            System.out.print("ENTER NAME: ");
            Scanner scan = new Scanner(System.in);
            name = scan.nextLine();
    
            System.out.println("\nHello, "+name+"! Lets play some BlackJack!");
    
            
            
            //SETTING CASH -- USE PRV STAT CASH 
            System.out.println("\nHow much cash do you want to start with?");
            System.out.print(" $");
            Scanner money = new Scanner(System.in);
            cash = money.nextInt();
    
            System.out.println("You start with cash: $" + cash);
            
            while(cash>0){
                Deck deck = new Deck();//initialise deck
                deck.shuffle();         //shuffles deck
                AceCounter=0;           //no aces
                Dealer dealer = new Dealer(deck);   //intialise dealer
                List<Cards> hand = new ArrayList<>();   //initialise cards
                hand.add(deck.drawCard());
                hand.add(deck.drawCard());
            
                
                
                
                //BETTING THE CASH AVAILABLE
                System.out.println("How much would you like to bet?");
                System.out.print("I'd like to bet.. $");
                bet = Bet(cash);
                System.out.println("Money on the table: $"+bet);    //betting how much $?
                
                
                System.out.println("Your hand: ");
                System.out.println(hand);       //shows user's hand of cards
        
                
                //dealer's hand of cards
                int handvalue = calcHandValue(hand);
                System.out.println("The dealer is showing: ");
                dealer.showFirstCard();
            
                
                
                
                //BOTH USER AND DEALER HAVE BLACKJACK?
                if(hasBlackJack(handvalue) && dealer.hasBlackJack())
                    {   Push(); }   //EITHER ONE WILL GET BUSTED (>21)
        
                
                
                
                
                else if(hasBlackJack(handvalue))//check if the user has blackjack
                {   System.out.println("You have BlackJack!");
                    System.out.println("You win 2x your money back!");
                    cash=cash+bet;
                    Win();
                    }
        
            
                else if(dealer.hasBlackJack())//check if the dealer has blackjack
                {   System.out.println("Here is the dealer's hand: ");
                    dealer.showHand();
                    Lose();
                    }
        
            
                else
                {   
                    
                    if(2*bet<cash)//check if the user can double down
                    {   
                        //TTRUE-- user CAN double down
                        System.out.println("Would you like to double down?");
                        Scanner doubledown = new Scanner(System.in);
                        String doubled = doubledown.nextLine();
                        
                        
                        //QUESTION
                        while(!isyesorno(doubled))
                        {   System.out.println("Enter 'yes' or 'no': ");
                            doubled = doubledown.nextLine();
                        }
                        //USER DOUBLES DOWN
                        if(doubled.equals("yes"))
                        {
                            System.out.println("You have opted to double down!");
                            bet=2*bet;
                            System.out.println("Cash:"+(cash-bet));
                            System.out.println("Money on the table:"+bet);
                        }
                    }
            
                
                
                    System.out.println("Would you like to hit or stand?");//ask if the user will hit or stand
                    Scanner hitorstand = new Scanner(System.in);
                    String hitter = hitorstand.nextLine();

                    
                    
                    while(!isHitorStand(hitter))
                    {   System.out.println("Please enter 'hit' or 'stand'.");
                        hitter = hitorstand.nextLine();
                    }


                    
                    
                    while(hitter.equals("hit"))//hits - more cards for user
                    {
                        Hit(deck, hand);
                        System.out.println("Your hand is now:");
                        System.out.println(hand);
                        handvalue = calcHandValue(hand);

                        
                        if(checkBust(handvalue))//checks if the user busted
                        {
                            Lose();
                            break;
                        }

                        if(handvalue<=21 && hand.size()==5)//checks for a five card trick
                        {
                            fivecardtrick();
                            break;  //walau
                        }
                    
                        
                        
                    System.out.println("Would you like to hit or stand?");
                    hitter = hitorstand.nextLine();
                    }
                
                
                    if(hitter.equals("stand"))//lets the user stand
                    {
                        int dealerhand = dealer.takeTurn(deck);//takes the turn for the dealer
                        System.out.println("\nHere is the dealer's hand:");
                        dealer.showHand();


                        if(dealerhand>21)//if the dealer busted, user wins
                        {   Win();  }   //yay you

                        else
                        {
                            int you = 21-handvalue;//check who is closer to 21 and determine winner
                            int deal = 21-dealerhand;
                            
                            //same value of cards -- return money
                            if(you==deal)
                            {   Push(); }
                            
                            if(you<deal)
                            {   Win();  }
                            
                            if(deal<you)
                            {   Lose(); }
                        }
                    }
                }
            
        //GAME OVER - USER WANTS TO PLAY AGAIN? YES NO?
        System.out.println("Would you like to play again?");//ask if the user wants to keep playing
        Scanner yesorno = new Scanner(System.in);
        String answer = yesorno.nextLine();
        
        while(!isyesorno(answer))
            {
                System.out.println("Please answer yes or no.");
                answer = yesorno.nextLine();    //call
            }
        
        
        if(answer.equals("no"))
        {   System.out.println("Thank you for playing!");
            break;  }
            }
            
            
            System.out.println("Your cash is: "+cash);//if user doesn't want to play or runs out of cash
            //either congratulate them or lets them know
            if(cash==0)
            {   System.out.println("You ran out of cash!");
                System.out.println("Tough luck! Try again next time!");}
            else
            {   System.out.println("Enjoy your winnings, " + name + "!");   }
}
    
    
    
    
    
    

//Checks if the user has blackjack
    public static boolean hasBlackJack(int handValue)
    {   if(handValue==21)
        {   return true;    }   //user wins
    
        return false;   //proceed game
    }

    
    
    
    
 //Calculates the value of the player's hand
    public static int calcHandValue(List<Cards> hand)
    {   Cards[] aHand = new Cards[]{};
        aHand = hand.toArray(aHand);
        int handvalue=0;
        for(int i=0; i<aHand.length; i++)
        {   handvalue += aHand[i].getValue();
            if(aHand[i].getValue()==11)
            {   AceCounter++;   }   //ace =11
        
            while(AceCounter>0 && handvalue>21)
            {   handvalue-=10;
                AceCounter--;   }   //ace=1
        }
    return handvalue;
    }

    
    
    
    
    
    
    
//Asks the user how much $$ to bet
public static int Bet(int cash)
{
    Scanner sc=new Scanner(System.in);
    int bet=sc.nextInt();
    while(bet>cash)
    {
        System.out.println("You cannot bet more cash than you have!");
        System.out.println("How much would you like to bet?");
        bet=sc.nextInt();
    }
    return bet;
}




 //Method for if the user wins
public static void Win()
{
    System.out.println("Congratulations, you win!");
    cash=cash+bet;
    System.out.println("Cash: "+cash);
}





//Method -- the user loses
public static void Lose()
{
    System.out.println("Sorry, you lose!");
    cash=cash-bet;
    System.out.println("Cash: "+cash);
}




//Method -- the user pushes
public static void Push()
{
    System.out.println("It's a push!");
    System.out.println("You get your money back!");
    System.out.println("Cash: "+    cash);
}




//Adds a card to user's hand
//calculates the value of that hand
//aces = 1 or ace = 11
public static void Hit(Deck deck, List<Cards> hand)
{
    hand.add(deck.drawCard());
    Cards[] aHand = new Cards[]{};
    aHand = hand.toArray(aHand);
    handvalue = 0;
    for(int i=0; i<aHand.length; i++)
    {
        handvalue += aHand[i].getValue();
        if(aHand[i].getValue()==11)
        {
            AceCounter++;   //ace = 11
        }
        while(AceCounter>0 && handvalue>21)
        {
            handvalue-=10;  //ace = 1
            AceCounter--;
        }
    }
}




//Determines if a user has input hit or stand
public static boolean isHitorStand(String hitter)
{
    if(hitter.equals("hit") || hitter.equals("stand"))
    {
        return true;
    }
    return false;
}





//Determines if a user has busted
public static boolean checkBust(int handvalue)
{
    if(handvalue>21)
    {
        System.out.println("You have busted!");
        return true;
    }
    return false;
}




//Determines if a user has input yes or no
public static boolean isyesorno(String answer)
{
    if(answer.equals("yes") || answer.equals("no"))
    {
        return true;    //yes
    }
    return false;   //no
}



//Called if the user has a five card trick
public static void fivecardtrick()
{
    System.out.println("You have achieved a five card trick!");
    Win();          //walau
}
    }