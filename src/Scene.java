//Scene Class
public class Scene extends Room {
   Card card;

   //Constructors
   public Scene(Card card) {
      this.card = card;
   }
   
   //Getters
   public Card getCard(){
      return card;
   }
   //Setters
   
   //Other Methods
   public void getPart(){
   }
   
   public boolean evaluateWork(){
      return false;
   }
}