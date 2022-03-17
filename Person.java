
package Capitals;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        Person.java
 * Description  A class represeneting the Person in the State Capitals Quiz.
 * Project      State Capitals Quiz
 * @author      <i>Regan Kastelie</i>
 *</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public abstract class Person {
    protected String name;
    protected int age;
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Person()-default constructor
     * Description  Assigns default values to variables.
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Person(){
        name = "";
        age = 0;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  Person()-overloaded constructor
     * Description  Assigns values to parameter.
     * @param       name String
     * @param       age int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getName()
     * Description  Get name of person
     * @return      name String 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getName() {
        return name;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getAge()
     * Description  Get age of person
     * @return      age int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getAge() {
        return age;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setName()
     * Description  Set name of person
     * @param       name String
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setName(String name) {
        this.name = name;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setAge()
     * Description  Set age of person
     * @param       age int
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setAge(int age) {
        this.age = age;
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
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
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
        return  name + ","+ age;
    }
    

}
