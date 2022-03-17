package Capitals;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Class        CapitalsGUI.java
 * Description  A class representing the GUI used in state capitals quiz
 *              application
 * Project      States Capitals Quiz
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * @author	<i>Regan Kastelie</i>
 * @version 	%1% %2%
 * @see     	javax.swing.JFrame
 * @see         java.awt.Toolkit 
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class CapitalsGUI extends javax.swing.JFrame
{
    // class variables
    private final int MAX_QUESTIONS = 50; // the very max number of questions
    private int numberOfQuestions;
    
    //data structures
    private BinarySearchTree playersTree = new BinarySearchTree();
    private List<Capital> statesCapitalList = new LinkedList<Capital>();
    private Map<String, String> capitalsHashMap = new HashMap<String, String>();
    
    // variable for file
    private String capitalsFile = "src/Capitals/Capitals.txt";
    private String playersFile = "src/Capitals/Players.txt";
    
    DefaultListModel<String> playersJListModel = new DefaultListModel<String>();
    //parallel Arrays
    private ArrayList<Boolean> statesUsedArrayList = new ArrayList<Boolean>();
    private ArrayList<Integer> numbersArrayList = new ArrayList<Integer>();
    
    private int currentIndex;
    private String correctCapital = "";
    private int countCorrect = 0;
    private int numberOfStates = MAX_QUESTIONS; 
    
    //takes the number of capitals that have been displayed
    private int count = 0;
    
    
    
 
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  CapitalsGUI()-default constructor
     * Description  Create an instance of the GUI form, set the default
     *              JButton to be submitJButton, set icon image, center form,
     *              read players from external file. 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    public CapitalsGUI()
    {
        initComponents();
        
        playersJList.setModel(playersJListModel);
        //set the display JBUtton as the default key as ENTER:
        this.getRootPane().setDefaultButton(submitJButton);
        //set a icon picture on the Title:
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/State Capitals Small.png"));
        this.setLocationRelativeTo(null);
        readStates(capitalsFile);    
        readPlayers(playersFile);     
        playersJList.setSelectedIndex(0);
  
        submitJButton.setEnabled(false);
        answerJTextField.setEnabled(false);
        nextJButton.setEnabled(false);
        playJButton.setEnabled(false);
        numberJFormattedTextField.requestFocus();
        Random random = new Random();
        displayState(random.nextInt(50));
        
  
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readStates
     * Description  Reads states from a file Capitals.txt.  
     * @author      <i>Regan Kastelie</i>
     * @param       fileName String
     * @see         java.io.File
     * @see         java.util.Scanner
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void readStates(String fileName)
    {   
        
        numberOfStates = 0; 
        numbersArrayList.clear();
        statesUsedArrayList.clear();
        try
        {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String line = "";
            String state = "";
            String city = "";
            int year = 0;
            double area = 0;
            int population = 0;
            int rank = 0;
            
            //read file, creates Capital objects and store information
            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine();
                Capital myCapital = new Capital();
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while(stringTokenizer.hasMoreElements()){
                    state = stringTokenizer.nextElement().toString();
                    city = stringTokenizer.nextElement().toString();
                    // Add keys and values to hash map
                    capitalsHashMap.put(state, city);
                    
                    year = Integer.parseInt(stringTokenizer.nextElement().toString());
                    area = Double.parseDouble(stringTokenizer.nextElement().toString());
                    population = Integer.parseInt(stringTokenizer.nextElement().toString());
                    rank = Integer.parseInt(stringTokenizer.nextElement().toString());
                    myCapital = new Capital(state, city, year, area, population, rank);
                }
               
                //Add Capital objects
                statesCapitalList.add(myCapital);
                numbersArrayList.add(numberOfStates);
                numberOfStates++;
            }
            fileScanner.close();
        }
        catch(FileNotFoundException exp){
            JOptionPane.showMessageDialog(null, fileName + "does not exist",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            //Bring up JFileChooser to select file in current directory
            JFileChooser chooser = new JFileChooser("src/Capitals");
            //Filter only txt files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Txt Files", "txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if(choice == JFileChooser.APPROVE_OPTION){
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/Capitals/" + chosenFile.getName();
                System.out.println("file = " +fileName);
                readStates(fileName);
            }
            else{
                //exp.printStackTrace();
                System.exit(0);
            }
        }
        catch(Exception exp){
            JOptionPane.showMessageDialog(null, "Unable to read file",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       readPlayers
     * Description  Reads players from a file Players.txt and add items to
     *              playersJList.  
     * @author      <i>Regan Kastelie</i>
     * @param       fileName String
     * @see         java.io.File
     * @see         java.util.Scanner
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public void readPlayers(String fileName)
    {   
        playersTree.removeAll();
        
        try
        {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String line = "";
            String name = "";
            int age = 0;
            int correct = 0;
            int totalQuestions = 0;
            
            //read file, creates Player objects and store information
            while(fileScanner.hasNextLine()){
                Player myPlayer = new Player();
                line = fileScanner.nextLine();
                //Player myPlayer = new Player();
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                while(stringTokenizer.hasMoreElements()){
                    
                    name = stringTokenizer.nextElement().toString();
                    // Add playersName to JList
                    playersJListModel.addElement(name);
                    
                    age = Integer.parseInt(stringTokenizer.nextElement().toString());
                    correct = Integer.parseInt(stringTokenizer.nextElement().toString());
                    totalQuestions = Integer.parseInt(stringTokenizer.nextElement().toString());
                    myPlayer = new Player(name, age, correct, totalQuestions);
                      
                }
                //add player to players TreeSet   
                playersTree.insertNode(myPlayer);  
            }
            fileScanner.close();
        }
        catch(FileNotFoundException exp){
            JOptionPane.showMessageDialog(null, fileName + "does not exist",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            //Bring up JFileChooser to select file in current directory
            JFileChooser chooser = new JFileChooser("src/Capitals");
            //Filter only txt files
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Txt Files", "txt");
            chooser.setFileFilter(filter);
            int choice = chooser.showOpenDialog(null);
            if(choice == JFileChooser.APPROVE_OPTION){
                File chosenFile = chooser.getSelectedFile();
                fileName = "src/Capitals/" + chosenFile.getName();
                System.out.println("file = " +fileName);
                readPlayers(fileName);
            }
            else{
                //exp.printStackTrace();
                System.exit(0);
            }
        }
        catch(Exception exp){
            JOptionPane.showMessageDialog(null, "Unable to read file",
                    "File Input Error", JOptionPane.WARNING_MESSAGE);
            playersJListModel.removeAllElements();
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayState
     * Description  Choose a random and unused state and display it in the 
     *              capitalJLabel.
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void displayState()
    {
        currentIndex = getUniqueAlternate();   //get unique unused number
        //System.println("currentIndex = " + currentindex);
        //create the path for file
        String selectedState = statesCapitalList.get(currentIndex).getState();
        correctCapital = capitalsHashMap.get(selectedState).toString();
        randomJLabel.setText(selectedState);
        String statePath = "src/Images/" + correctCapital + ".jpg";
       
        capitalJLabel.setIcon(new ImageIcon(statePath));
        capitalJLabel.setToolTipText(correctCapital); //uncomment for tool tip to display state
     } 

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayState
     * Description  Overloaded method to display art with a specified index.
     * @param       index int
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void displayState(int index)
    {
        currentIndex = index; 
        // create the path for file
        String selectedState = statesCapitalList.get(index).getState();
        correctCapital = capitalsHashMap.get(selectedState).toString();
        randomJLabel.setText(selectedState);
        String statePath = "src/Images/" + correctCapital + ".jpg";
        capitalJLabel.setIcon(new ImageIcon(statePath));
        capitalJLabel.setToolTipText(":D"); //uncomment tool tip to display state
    } 
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getUniqueAlternate
     * Description  A better way to select unique and unused random integer 
     *              for unused sign
     * @return      random int
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int getUniqueAlternate()
    {
        int randomNumber = 0;
        Random rand = new Random();
        //get a random number of available ones
        randomNumber = rand.nextInt(numberOfStates);
        int uniqueRandomNumber = numbersArrayList.get(randomNumber);
        numbersArrayList.set(randomNumber, numbersArrayList.get(numberOfStates - 1));
        numberOfStates--;
        
        return uniqueRandomNumber;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        capitalJLabel = new javax.swing.JLabel();
        controlJPanel = new javax.swing.JPanel();
        selectJPanel = new javax.swing.JPanel();
        submitJButton = new javax.swing.JButton();
        nextJButton = new javax.swing.JButton();
        playJButton = new javax.swing.JButton();
        resultJLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        numberJFormattedTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        randomJLabel = new javax.swing.JLabel();
        answerJTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        playersJList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        playersJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        openJMenuItem = new javax.swing.JMenuItem();
        clearJMenuItem = new javax.swing.JMenuItem();
        printJMenuItem = new javax.swing.JMenuItem();
        printPlayerJMenuItem = new javax.swing.JMenuItem();
        fileJSeparator = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        dataJMenu = new javax.swing.JMenu();
        addJMenuItem = new javax.swing.JMenuItem();
        editJMenuItem = new javax.swing.JMenuItem();
        deleteJMenuItem = new javax.swing.JMenuItem();
        searchJMenuItem = new javax.swing.JMenuItem();
        playerDetailsJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("USA States Capitals Quiz");
        setPreferredSize(new java.awt.Dimension(900, 655));
        setResizable(false);

        capitalJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Albany.jpg"))); // NOI18N
        capitalJLabel.setFocusable(false);

        controlJPanel.setLayout(new java.awt.GridLayout(3, 1, 3, 5));

        selectJPanel.setLayout(new java.awt.GridLayout(3, 1, 3, 5));

        submitJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submitJButton.setMnemonic('S');
        submitJButton.setText("Submit");
        submitJButton.setToolTipText("Click to submit your answer");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        nextJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nextJButton.setMnemonic('N');
        nextJButton.setText("Next State");
        nextJButton.setToolTipText("Click to see next sign");
        nextJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextJButtonActionPerformed(evt);
            }
        });

        playJButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playJButton.setMnemonic('P');
        playJButton.setText("Play Again");
        playJButton.setToolTipText("Play all over again!");
        playJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playJButtonActionPerformed(evt);
            }
        });

        resultJLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        resultJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultJLabel.setToolTipText("Correct or Incorrect based on your answer");
        resultJLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Questions:");

        numberJFormattedTextField.setToolTipText("Enter number of questions between 1 and 50");
        numberJFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberJFormattedTextFieldActionPerformed(evt);
            }
        });
        numberJFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numberJFormattedTextFieldKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Enter Name of Capital:");

        randomJLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        randomJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        randomJLabel.setToolTipText("");

        answerJTextField.setToolTipText("Enter your capital answer based on the displayed state");
        answerJTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                answerJTextFieldActionPerformed(evt);
            }
        });

        playersJList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Players", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 2, 16), new java.awt.Color(204, 0, 51))); // NOI18N
        playersJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        playersJList.setName(""); // NOI18N
        jScrollPane1.setViewportView(playersJList);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("USA States Capitals Quiz");

        fileJMenu.setMnemonic('F');
        fileJMenu.setText("File");
        fileJMenu.setToolTipText("New, Clear, Print form , Print Players and Exit");

        openJMenuItem.setMnemonic('N');
        openJMenuItem.setText("New");
        openJMenuItem.setToolTipText("Display a new list of players in the list");
        openJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(openJMenuItem);

        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear player, start a new quiz");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);

        printJMenuItem.setMnemonic('P');
        printJMenuItem.setText("Print Form");
        printJMenuItem.setToolTipText("Print Form as GUI");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);

        printPlayerJMenuItem.setText("Print Player");
        printPlayerJMenuItem.setToolTipText("Print Player Details");
        printPlayerJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPlayerJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printPlayerJMenuItem);
        fileJMenu.add(fileJSeparator);

        exitJMenuItem.setMnemonic('X');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("End application");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        playersJMenuBar.add(fileJMenu);

        dataJMenu.setMnemonic('D');
        dataJMenu.setText("Players Database");
        dataJMenu.setToolTipText("Add, Edit, Delete, Search and other operations");

        addJMenuItem.setMnemonic('A');
        addJMenuItem.setText("Add");
        addJMenuItem.setToolTipText("Add new player");
        addJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(addJMenuItem);

        editJMenuItem.setMnemonic('E');
        editJMenuItem.setText("Edit");
        editJMenuItem.setToolTipText("Edit selected player");
        editJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(editJMenuItem);

        deleteJMenuItem.setMnemonic('t');
        deleteJMenuItem.setText("Delete");
        deleteJMenuItem.setToolTipText("Delete selected player");
        deleteJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(deleteJMenuItem);

        searchJMenuItem.setMnemonic('S');
        searchJMenuItem.setText("Search");
        searchJMenuItem.setToolTipText("Search for player (resets quiz)");
        searchJMenuItem.setEnabled(false);
        searchJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(searchJMenuItem);

        playerDetailsJMenuItem.setText("Player details");
        playerDetailsJMenuItem.setToolTipText("Displays selected player's name, age, correct and total questions");
        playerDetailsJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerDetailsJMenuItemActionPerformed(evt);
            }
        });
        dataJMenu.add(playerDetailsJMenuItem);

        playersJMenuBar.add(dataJMenu);

        helpJMenu.setText("Help");
        helpJMenu.setToolTipText("About form");

        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.setToolTipText("A short description about the program");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        playersJMenuBar.add(helpJMenu);

        setJMenuBar(playersJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(capitalJLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(submitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(playJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(randomJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(numberJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 1, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(answerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(selectJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                                .addComponent(resultJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(selectJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numberJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(randomJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(339, 339, 339)
                .addComponent(controlJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(answerJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resultJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(capitalJLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nextJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       submitJButtonActionPerformed
     * Description  Event handler to check if the user's answer is correct. The
     *              correct answer is held in class instance variable 
     *              currentIndex.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed

        try{
        count++;
        numberJFormattedTextField.setEnabled(false);
        playersJList.setEnabled(false);
        String correctCapital = capitalsHashMap.get(randomJLabel.getText());
        String userInput = answerJTextField.getText();
        
        //Check if user input is correct or wrong
        if (correctCapital.equalsIgnoreCase(userInput)){
            countCorrect++;
            resultJLabel.setText("Correct! " + countCorrect + "/ " + count);
        }
        else{
            resultJLabel.setText("Incorrect " + countCorrect + "/ " + count);
        }
        // Check if questions already reach total questions
        if(count == numberOfQuestions){
            resultJLabel .setText(countCorrect + "/" + numberOfQuestions + " correct!");
            nextJButton.setEnabled(false);
            submitJButton.setEnabled(false);
            playJButton.setEnabled(true);
            playJButton.requestFocus();
            playersJList.setEnabled(false);
            searchJMenuItem.setEnabled(true);
            answerJTextField.setEnabled(false);
//            addJMenuItem.setEnabled(true);
            deleteJMenuItem.setEnabled(true);
            
            //Updates player's result
            String name = playersJList.getSelectedValue();
            Player playerOutputDetail = searchPlayer(name).data;
            playersTree.remove(playerOutputDetail);
        
            playerOutputDetail.setCorrect(countCorrect);
            playerOutputDetail.setTotalQuestions(count);
        
            playersTree.insertNode(playerOutputDetail);
            savePlayers(playersFile);
        }
        else{   
            submitJButton.setEnabled(false);
            nextJButton.setEnabled(true);
            nextJButton.requestFocus();  //better make this default
            playJButton.setEnabled(false);
            searchJMenuItem.setEnabled(false);
            answerJTextField.setEnabled(false);
//            addJMenuItem.setEnabled(false);
        } 
        }
        catch (NumberFormatException exp){
                    JOptionPane.showMessageDialog(null, "Input must be a String ",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_submitJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       nextJButtonActionPerformed
     * Description  Event handler to select next unused sign randomly by 
     *              calling the displayState() method.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nextJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextJButtonActionPerformed
        displayState();
        // rest GUI components to initial states
        resultJLabel.setText("");
        
        submitJButton.setEnabled(true);
        nextJButton.setEnabled(false);
        answerJTextField.setText("");
        answerJTextField.setEnabled(true);
    }//GEN-LAST:event_nextJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       playJButtonActionPerformed
     * Description  Event handler to start the game all over again.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void playJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playJButtonActionPerformed

        // Start game all over
        countCorrect = 0;
        count = 0;
        numberOfQuestions = 0;
        resultJLabel.setText("");
        submitJButton.setEnabled(false);
        submitJButton.requestFocus();
        nextJButton.setEnabled(false);
        playJButton.setEnabled(false);
        playersJList.setEnabled(true);
        searchJMenuItem.setEnabled(false);
        playersJList.setSelectedIndex(0);
        answerJTextField.setEnabled(false);
//        addJMenuItem.setEnabled(false);
        
        numberJFormattedTextField.setEnabled(true);
        numberJFormattedTextField.setText("");
        numbersArrayList.clear();
        readStates(capitalsFile);
        
        //Takes random capital picture that does not affect getUniqueAlternate()
        Random random = new Random();
        displayState(random.nextInt(50));
        answerJTextField.setText("");
    }//GEN-LAST:event_playJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       openJMenuItemActionPerformed
     * Description  Event handler to chose a separate file of players.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void openJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openJMenuItemActionPerformed
        //Bring up JFileChooser to select file in current directory
        playersJListModel.clear();
        JFileChooser fileJFileChooser = new JFileChooser("src/Capitals");
        // limit to txt files
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Txt Files", "txt");
        fileJFileChooser.setFileFilter(filter);
        int returnVal = fileJFileChooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            File file = fileJFileChooser.getSelectedFile();
            playersFile = file.getName();
            //clear artUsed and numbersUswd ArrayList
            statesUsedArrayList.clear();
            numbersArrayList.clear();
            
            readPlayers("src/Capitals/" + playersFile);
            clearJMenuItemActionPerformed(evt); 
        }
        else{
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_openJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       aboutJMenuItemActionPerformed()
     * Description  Create an About form and show it.  
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     * *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutJMenuItemActionPerformed
    {//GEN-HEADEREND:event_aboutJMenuItemActionPerformed
        About aboutWindow = new About(this, true);
        aboutWindow.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printJMenuItemActionPerformed()
     * Description  Event handler to print the for as a GUI. Calls the
     *              PrintUtilities class printComponent method.
     * @param       evt ActionEvent
     * @author      <i>Regan Kastelie</i>   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_printJMenuItemActionPerformed
    {//GEN-HEADEREND:event_printJMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       clearJMenuItemActionPerformed()
     * Description  Event handler to clear the form and start anew. Calls the
     *              playJButtonActionPerformed event handler.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i> 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_clearJMenuItemActionPerformed
    {//GEN-HEADEREND:event_clearJMenuItemActionPerformed
        playJButtonActionPerformed(evt);
    }//GEN-LAST:event_clearJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       exitJMenuItemActionPerformed()
     * Description  Event handler to end the application.
     * @author      <i>Regan Kastelie</i>
     * @param       evt java.awt.event.ActionEvent   
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_exitJMenuItemActionPerformed
    {//GEN-HEADEREND:event_exitJMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       addJMenuItemActionPerformed()
     * Description  Event handler to add new players do the DB.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i> 
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @SuppressWarnings("unchecked")
    private void addJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addJMenuItemActionPerformed
    {//GEN-HEADEREND:event_addJMenuItemActionPerformed
        try{
            AddPlayer playerDialog = new AddPlayer();
            playerDialog.setVisible(true);
            //The modal dialog takes focus, upon regaining focus
            Player newPlayer = playerDialog.getPlayer();
   
            if (newPlayer != null && !(playersTree.contains(newPlayer))){ 
                 
                // check if there is player in BST, if not then null
                 BinarySearchTreeNode result = searchPlayer(newPlayer.getName());
                if (result == null){
                    playersTree.insertNode(newPlayer);
                    playersJListModel.addElement(newPlayer.getName());
                    savePlayers(playersFile); 
                }
                else{
                    JOptionPane.showMessageDialog(null,
                        "No Duplicates.");
                }
            }    
        }
        catch (NullPointerException exp){
            JOptionPane.showMessageDialog(null, "Player not Added", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            playersJList.setVisible(true);
            playersJList.setSelectedIndex(0);
        }
        
    }//GEN-LAST:event_addJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       savePlayers()
     * Description  Write player to a text file that is pipe (,) delimited.
     * @param       file String
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void savePlayers(String file)
    {
        try{
            FileWriter filePointer = new FileWriter(playersFile, false);
            PrintWriter writeFile = new PrintWriter(filePointer, false);
            BinarySearchTreeNode root = playersTree.getRoot();
            
            playersTree.setBuffer(new StringBuilder());
            
            playersTree.buildBuffer(root);
            String buffer = playersTree.getBuffer().substring(0,
                    playersTree.getBuffer().length() - 1);
            writeFile.print(buffer);
            writeFile.close();
            
            }
        catch (IOException ex){
            JOptionPane.showMessageDialog(this, "Unable to write to file",
                  "Write to File Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
        
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       searchPlayer()
     * Description  Search for player by name and return node that contains it.
     * @param       name String
     * @return      Player node BinarySearchTreeNode
     * @author      <i>Regan Kastelie</i>  
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode searchPlayer(String name)
    {
        return playersTree.nodeWith(name, playersTree.getRoot());
    }
      
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       editJMenuItemActionPerformed()
     * Description  Edit selected player.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void editJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_editJMenuItemActionPerformed
    {//GEN-HEADEREND:event_editJMenuItemActionPerformed
        try{
            //get name of selected composer, but strip year if there 
            String playerName = playersJList.getSelectedValue();
  
            //create a temp composer to populate fields of form
            Player playerToEdit = searchPlayer(playerName).data;
            //pass player info to EditPlayer constructor and view Edit form
            EditPlayer editGUI = new EditPlayer(playerToEdit);
            editGUI.setVisible(true);
            
            //get edited player and add to the TreeSet and array list
            Player editedPlayer = editGUI.getPlayer();
            if (editedPlayer != null && !(playersTree.contains(editedPlayer))){
                // remove old player from BST & ArrayList
                BinarySearchTreeNode result = searchPlayer(editedPlayer.getName());
                // Only can edit players name when there are no duplicates in BST
                if (result == null || playerName.equals(editedPlayer.getName())){   
                         playersTree.remove(playerToEdit);
                        playersJListModel.removeElement(playerName);
                        playersTree.insertNode(editedPlayer);
                        playersJListModel.addElement(editedPlayer.getName());                       
                }
                  //save new players list to file
                savePlayers(playersFile);
            }
        }
        catch(NullPointerException nullex){
            JOptionPane.showMessageDialog(null, "Players not Edited",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            playersJList.setVisible(true);
        }
    }//GEN-LAST:event_editJMenuItemActionPerformed

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       deleteJButtonActionPerformed()
     * Description  Delete an existing player with confirmation dialog
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void deleteJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteJMenuItemActionPerformed
    {//GEN-HEADEREND:event_deleteJMenuItemActionPerformed
        //Delete selected player
        try{
            String playerName = playersJList.getSelectedValue();
            int result = JOptionPane.showConfirmDialog(null,
                "Are you sure you wish to delete " + playerName + "?", "Delete Player",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
            if (result == JOptionPane.OK_OPTION)  {//confirm delete selected Player
                playersJList.getSelectedIndex();
                playersJListModel.removeElement(playerName); // remove from playersJList
            
                //get node from tree that has player name
                BinarySearchTreeNode tempNode = playersTree.nodeWith(playerName, playersTree.getRoot());
            
                Player temp = tempNode.data;
                playersTree.remove(temp);
            
                savePlayers(playersFile);
            }
        } 
        catch (NullPointerException exp){
            JOptionPane.showMessageDialog(null, 
                        " Player not selected.");
        }
    }//GEN-LAST:event_deleteJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       searchJMenuItemActionPerformed()
     * Description  Event handler to search player by name and to display it.
     *              Calls searchPlayer() to do the searching
     * @author      <i>Regan Kastelie</i>
     * @param       evt java.awt.event.ActionEvent
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void searchJMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_searchJMenuItemActionPerformed
    {//GEN-HEADEREND:event_searchJMenuItemActionPerformed
        try{
            String searchName = JOptionPane.showInputDialog(null, "Enter name of player: ",
                "Search player by name", JOptionPane.INFORMATION_MESSAGE);
            if((searchName != null) && (searchName.length() > 0)){
                // get the BinarySearchTreeNode that has player's name
                BinarySearchTreeNode result = searchPlayer(searchName);
                String resultStringForm = playersTree.elementAt(result).getName();
                if(result != null){
                    playJButtonActionPerformed(evt);
                    int index = playersJListModel.indexOf(resultStringForm);
                    playersJList.setSelectedIndex(index);
                }
                else{
                    JOptionPane.showMessageDialog(null, searchName +
                        " is not in the database.");       
                }
            }
        }
        catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, 
                        " The name is not in the database."); 
        }
    }//GEN-LAST:event_searchJMenuItemActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       numberJFormattedTextFieldActionPerfomed()
     * Description  Accepts number of questions in the range from 1 to 50.
     * @author      <i>Regan Kastelie</i>
     * @param       evt java.awt.event.ActionEvent
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void numberJFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberJFormattedTextFieldActionPerformed
        try{
            if(!Validation.isInteger(numberJFormattedTextField.getText(),
                    1, MAX_QUESTIONS))
                throw new NumberFormatException();
            numberOfQuestions = Integer.parseInt(numberJFormattedTextField.getText());
            if (numberOfQuestions < 1 || numberOfQuestions > MAX_QUESTIONS)
                throw new NumberFormatException();
            numberJFormattedTextField.setEnabled(false);
            answerJTextField.setEnabled(true);
            submitJButton.setEnabled(true);
            displayState();
            answerJTextField.requestFocus();
            playersJList.setEnabled(true);
            
        }
        catch (NumberFormatException exp){
            JOptionPane.showMessageDialog(null, "Input must be an integer in " + 
                    "the range [1, " + MAX_QUESTIONS + "]. ",
                    "Input Error", JOptionPane.WARNING_MESSAGE);
            numberJFormattedTextField.requestFocus();
            numberJFormattedTextField.selectAll();
        }
    }//GEN-LAST:event_numberJFormattedTextFieldActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       playerDetailsJMenuItemActionPerformed()
     * Description  Event handler that provides player's name, age, correct,
     *              total number of questions and correct percentage.
     * @author      <i>Regan Kastelie</i> 
     * @param       evt java.awt.event.ActionEvent
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    private void playerDetailsJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerDetailsJMenuItemActionPerformed
        int index = playersJList.getSelectedIndex();
        if (index >= 0){
            String playerName = playersJList.getSelectedValue();
            BinarySearchTreeNode playerNode = searchPlayer(playerName);
            if (playerNode != null && playerNode.data != null){
                PlayerDetails playerQuote = new PlayerDetails(playerNode.data);
                playerQuote.setVisible(true);
            }
        }
    }//GEN-LAST:event_playerDetailsJMenuItemActionPerformed

     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       numberJFormattedTextFieldKeyTyped()
     * Description  Accept only digits, backspace and delete keys. The rest will
     *              will not be entered.       
     * @author      <i>Regan Kastelie</i>
     * @param       evt java.awt.event.KeyEvent
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void numberJFormattedTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numberJFormattedTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) ||
                (c == KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_numberJFormattedTextFieldKeyTyped

    private void answerJTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_answerJTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_answerJTextFieldActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printPlayerJMenuItemActionPerformed()
     * Description  Event handler to print the player details.
     * @author      <i>Regan Kastelie</i>
     * @param       evt java.awt.event.ActionEvent
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void printPlayerJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printPlayerJMenuItemActionPerformed
        JTextArea outputJTextArea = new JTextArea();
        outputJTextArea.setFont(new java.awt.Font("Arial", 0, 20));
        String playerName = playersJList.getSelectedValue();
        BinarySearchTreeNode playerNode = playersTree.nodeWith(playerName, 
                playersTree.getRoot());
        if (playerNode != null){
            DecimalFormat percent = new DecimalFormat("#,##0.0%");
            Player player = new Player(playerNode.data);
            int correct = player.getCorrect();
            int total = player.getTotalQuestions();
            String output = "Player's Name: " + playerName + "\nAge: " +
                    "\nTotal Questions: " + total;
            if (player.getTotalQuestions() != 0){
                output += "\nPercent Correct: " +
                        percent.format((double) correct / total) + ".";
            }
            else{
                output += ".";
            }
            outputJTextArea.setText(output);
            try{
                outputJTextArea.print();
            }
            catch (PrinterException ex){
                JOptionPane.showConfirmDialog(null, "Unable to print", "Print Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_printPlayerJMenuItemActionPerformed
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
     * Method       main()
     * Description  Displays splash screen and the main RoadSign GUI form.
     * @param       args are the command line strings
     * @author      <i>Regan Kastelie</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String args[])
    {
        // Show splash screen
        Splash mySplash = new Splash(5000);     // duration = 4 seconds
        mySplash.showSplash();                  // show splash screen
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run() 
            {
                CapitalsGUI flagQuiz = new CapitalsGUI();                
                flagQuiz.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenuItem addJMenuItem;
    private javax.swing.JTextField answerJTextField;
    private javax.swing.JLabel capitalJLabel;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JPanel controlJPanel;
    private javax.swing.JMenu dataJMenu;
    private javax.swing.JMenuItem deleteJMenuItem;
    private javax.swing.JMenuItem editJMenuItem;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JPopupMenu.Separator fileJSeparator;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nextJButton;
    private javax.swing.JTextField numberJFormattedTextField;
    private javax.swing.JMenuItem openJMenuItem;
    private javax.swing.JButton playJButton;
    private javax.swing.JMenuItem playerDetailsJMenuItem;
    private javax.swing.JList<String> playersJList;
    private javax.swing.JMenuBar playersJMenuBar;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JMenuItem printPlayerJMenuItem;
    private javax.swing.JLabel randomJLabel;
    private javax.swing.JLabel resultJLabel;
    private javax.swing.JMenuItem searchJMenuItem;
    private javax.swing.JPanel selectJPanel;
    private javax.swing.JButton submitJButton;
    // End of variables declaration//GEN-END:variables
}