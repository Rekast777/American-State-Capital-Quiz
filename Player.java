package Capitals;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Player.java
 * Description  A class represeneting the Player in the State Capitals Quiz.
 * Project      State Capitals Quiz 
 * @author      <i>Regan Kastelie</i>
 *</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Player extends Person implements Comparable {
    private int correct;
    private int totalQuestions;
    
    public Player(){
        this(0, 0);
        
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Player() - overloaded constgructor
     * Description  Assigns parameters 
     * @param       correct int
     * @param       total int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/       
    public Player(int correct, int total){
        this.correct = correct;
        this.totalQuestions = total;
        
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Player() - overloaded constgructor
     * Description  Assigns parameters 
     * @param       name String
     * @param       age int
     * @param       correct int
     * @param       total int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public Player(String name, int age, int correct, int total){
        super(name, age);
        this.correct = correct;
        this.totalQuestions = total;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Player() - copy constgructor
     * Description  Assigns parameters from another Player
     * @param       another Player 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/         
    public Player(Player another){
        this.name = another.name;
        this.age = another.age;
        this.correct = another.correct;
        this.totalQuestions = another.totalQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       calculatePercent()
     * Description  Convert number of correct questions to percentage
     * @return      percentage double
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public double calculatePercent(){
        double score = 0;
        if(totalQuestions == 0)
            return 0;
        else
            score = 100.0*correct / totalQuestions;
            return (double)Math.round(score*100)/100;    
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getCorrect()
     * Description  Get correct number of questions
     * @return      correct answer int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public int getCorrect() {
        return correct;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getTotalQuestions()
     * Description  Get total number of questions
     * @return      total questions number int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public int getTotalQuestions() {
        return totalQuestions;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setCorrect()
     * Description  Set correct number of questions
     * @param       New correct number of questions int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public void setCorrect(int correct) {
        this.correct = correct;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setTotalQuestions()
     * Description  Set total number questions
     * @param       New total number questions int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.correct;
        hash = 37 * hash + this.totalQuestions;
        return hash;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       equals()
     * Description  Check if objects are equal
     * @param       obj Object
     * @reutrn      true or false boolean
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.correct != other.correct) {
            return false;
        }
        if (this.totalQuestions != other.totalQuestions) {
            return false;
        }
        return true;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       toString()
     * Description  Print the value of each variables in a string
     * @return      correct and totalQuestions String      
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString() {
        return super.toString() + "," + correct + "," + totalQuestions;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       compareTo()
     * Description  Compare two Players with exact string
     * @param       o Object
     * @return      -1, 0, 1 int   
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    @Override
    public int compareTo(Object o){
        Player obj = (Player)o;
        if(this.getName().equalsIgnoreCase(obj.getName())){   
            return this.age - obj.age;
        }
        else{
            return this.name.compareToIgnoreCase(obj.name);
        }
    }

    
   
}
