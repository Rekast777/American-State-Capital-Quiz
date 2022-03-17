package Capitals;
import java.awt.Color;
import java.awt.Toolkit;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        EditPlayer
 * File         EditPlayer.java
 * Description  A class used to edit existing player.
 * @author	<i>Regan Kastelie</i>
 * @see         java.awt.Color
 * @see         java.awt.Toolkit
 * @see         javax.swing.JOptionPane
 * @version	1.2.0
 * Environment  PC, Windows 10, jdk1.8.0_214, NetBeans 11.3
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class EditPlayer extends javax.swing.JDialog
{
    private Player newPlayer;                   // Player to be created
    private boolean error = false;              // Checking for errors in input
    private String errorMessage = "";           // Error message
    private final int MAX_AGE = 120;          // Maximum age year for player
    private final int MIN_AGE = 0;             // Minimum age for player 
    private final Color white = Color.WHITE;    // Default background color for input textfield
    private final Color pink = Color.PINK;      // Background color for bad input textfield
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor   EditPlayer()-default constructor
     * Description   Create an instance of the GUI form, set the default
     *               JButton to be saveJButton, set icon image, center form.
     * @author       <i>Regan Kastelie</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public EditPlayer()
    {
        initComponents();
        //set saveJButton as default button
        this.getRootPane().setDefaultButton(saveJButton);
        //set icon
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Images/State Capitals Small.png"));
        this.setLocationRelativeTo(null);       //center form
        ageJTextField.requestFocus();
        newPlayer = null;		       
        setAlwaysOnTop(true);
        setModal(true);
        nameJTextField.requestFocus();
    }
     /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  EditComposer()-overloaded copy constructor
     * Description  Calls the default constructor to create an instance of the
     *              GUI form and displays information of selected player.
     * @param       player Player
     * @author      <i>Regan Kastelie</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public EditPlayer(Player player)
    {
        this();     //call default constructor
        nameJTextField.setText(player.getName());
        ageJTextField.setText(String.valueOf(player.getAge()));
        correctJTextField.setText(String.valueOf(player.getCorrect()));  
        totalQuestionsJTextField.setText(String.valueOf(player.getTotalQuestions()));  
        nameJTextField.requestFocus();
        nameJTextField.selectAll();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleJPanel = new javax.swing.JPanel();
        addEditJLabel = new javax.swing.JLabel();
        logoJLabel = new javax.swing.JLabel();
        displayJPanel = new javax.swing.JPanel();
        nameJLabel = new javax.swing.JLabel();
        nameJTextField = new javax.swing.JTextField();
        ageJLabel = new javax.swing.JLabel();
        ageJTextField = new javax.swing.JTextField();
        balanceJLabel = new javax.swing.JLabel();
        correctJTextField = new javax.swing.JTextField();
        balanceJLabel1 = new javax.swing.JLabel();
        totalQuestionsJTextField = new javax.swing.JTextField();
        controlPanel = new javax.swing.JPanel();
        saveJButton = new javax.swing.JButton();
        cancelJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit New Player");
        setResizable(false);

        addEditJLabel.setFont(new java.awt.Font("Tempus Sans ITC", 2, 36)); // NOI18N
        addEditJLabel.setForeground(new java.awt.Color(51, 0, 0));
        addEditJLabel.setText("Edit Player");

        logoJLabel.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        logoJLabel.setForeground(new java.awt.Color(51, 0, 0));
        logoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/State Capitals Small.png"))); // NOI18N

        javax.swing.GroupLayout titleJPanelLayout = new javax.swing.GroupLayout(titleJPanel);
        titleJPanel.setLayout(titleJPanelLayout);
        titleJPanelLayout.setHorizontalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titleJPanelLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(logoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addEditJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        titleJPanelLayout.setVerticalGroup(
            titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleJPanelLayout.createSequentialGroup()
                .addGroup(titleJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEditJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoJLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        displayJPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 15)));
        displayJPanel.setPreferredSize(new java.awt.Dimension(341, 201));
        displayJPanel.setLayout(new java.awt.GridLayout(4, 2, 5, 5));

        nameJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        nameJLabel.setText("Name:");
        displayJPanel.add(nameJLabel);

        nameJTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        nameJTextField.setToolTipText("2 to 40 letters and spaces only");
        nameJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(nameJTextField);

        ageJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        ageJLabel.setText("Age:");
        displayJPanel.add(ageJLabel);

        ageJTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        ageJTextField.setToolTipText("Integer between 0 to 120");
        ageJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ageJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(ageJTextField);

        balanceJLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        balanceJLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        balanceJLabel.setText("Correct Answers:");
        displayJPanel.add(balanceJLabel);

        correctJTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        correctJTextField.setToolTipText("Integer between 0 to 50");
        correctJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correctJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(correctJTextField);

        balanceJLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        balanceJLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        balanceJLabel1.setText("Total Questions:");
        displayJPanel.add(balanceJLabel1);

        totalQuestionsJTextField.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalQuestionsJTextField.setToolTipText("Integer between 0 to 50");
        totalQuestionsJTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                totalQuestionsJTextFieldFocusLost(evt);
            }
        });
        displayJPanel.add(totalQuestionsJTextField);

        controlPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setLayout(new java.awt.GridLayout(1, 2, 5, 5));

        saveJButton.setBackground(new java.awt.Color(204, 255, 204));
        saveJButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        saveJButton.setMnemonic('S');
        saveJButton.setText("Save");
        saveJButton.setMinimumSize(new java.awt.Dimension(57, 45));
        saveJButton.setPreferredSize(new java.awt.Dimension(57, 35));
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(saveJButton);

        cancelJButton.setBackground(new java.awt.Color(204, 255, 204));
        cancelJButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cancelJButton.setMnemonic('n');
        cancelJButton.setText("Cancel");
        cancelJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelJButtonActionPerformed(evt);
            }
        });
        controlPanel.add(cancelJButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(titleJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202)
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addComponent(displayJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       saveJButtonActionPerformed()
     * Description  Create new player if all fields are valid.
     * @param       evt java.awt.event.ActionEvent
     * @author      <i>Regan Kastelie</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_saveJButtonActionPerformed
    {//GEN-HEADEREND:event_saveJButtonActionPerformed
        // Read all inputs as strings
        String playerName = nameJTextField.getText();
        String ageString = ageJTextField.getText();
        String correctString = correctJTextField.getText();
        String totalQuestionString = totalQuestionsJTextField.getText();
        // Validate all inputs
        if(!Validation.isValidName(playerName))
        {
            errorMessage += "Invalid Playert Name\n";
            nameJTextField.requestFocus();
            error = true;
            nameJTextField.setToolTipText(nameJTextField.getToolTipText() + 
                    "--Invalid Player Name");
        }
        else if(!Validation.isInteger(ageString, MIN_AGE, MAX_AGE))
        {
            errorMessage += "Invalid Age\n";
            ageJTextField.requestFocus();
            error = true;
            ageJTextField.setToolTipText(ageJTextField.getToolTipText() + 
                    "--Invalid Age");
        }
        else if(!Validation.isInteger(correctString, 0, 50))
        {
            errorMessage += "Invalid Correct Questions\n";
            correctJTextField.requestFocus();
            error = true;
            correctJTextField.setToolTipText(correctJTextField.getToolTipText() 
                + "--Invalid Correct Questions");
        }
        else if(!Validation.isInteger(totalQuestionString, 0, 50))
        {
            errorMessage += "Invalid Total Questions\n";
            totalQuestionsJTextField.requestFocus();
            error = true;
            totalQuestionsJTextField.setToolTipText(totalQuestionsJTextField.getToolTipText() 
                + "--Invalid Total Questions");
        }
        else
            error = false;
       
        if (!error)     // all fields are valid, create a newPlayer
        {
            try
            {
                playerName = nameJTextField.getText();
                int age = Integer.parseInt(ageJTextField.getText());
                int correct = Integer.parseInt(correctJTextField.getText());
                int totalQuestions = Integer.parseInt(totalQuestionsJTextField.getText());
                newPlayer = new Player(playerName, age, correct, totalQuestions);                
                this.dispose();     //close form
            }
            catch(NumberFormatException ex)
            {
                //We should never get here, throw runtime exception
                throw new RuntimeException("Error parsing input");
            }
        }
    }//GEN-LAST:event_saveJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getPlayer()
     * Description  Getter method to return newPlayer
     * @author      <i>Regan Kastelie</i>
     * @return      newPlayer Player
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public Player getPlayer()
    {
        return newPlayer;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method        cancelJButtonActionPerformed()
     * Description   Cancel the operation of edit. newPlayer remains null.
     * @param        java.awt.event.ActionEvent 
     * @author       <i>Regan Kastelie</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/       
    private void cancelJButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cancelJButtonActionPerformed
    {//GEN-HEADEREND:event_cancelJButtonActionPerformed
        this.dispose();        
    }//GEN-LAST:event_cancelJButtonActionPerformed
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       nameJTextFieldFocusLost()
     * Description  Validate name.
     * @parem       java.awt.event.FocusEvent 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void nameJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_nameJTextFieldFocusLost
    {//GEN-HEADEREND:event_nameJTextFieldFocusLost
        // Set background textfield color
        String input = nameJTextField.getText();
        if(Validation.isValidName(input))
            nameJTextField.setBackground(white);
        else
            nameJTextField.setBackground(pink);
    }//GEN-LAST:event_nameJTextFieldFocusLost
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       ageJTextFieldFocusLost()
     * Description  Validate age of player.
     * @parem       java.awt.event.FocusEvent 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void ageJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_ageJTextFieldFocusLost
    {//GEN-HEADEREND:event_ageJTextFieldFocusLost
        // Set background textfield color
        String input = ageJTextField.getText();
        if(Validation.isInteger(input, MIN_AGE, MAX_AGE))
            ageJTextField.setBackground(white);
        else
            ageJTextField.setBackground(pink);
    }//GEN-LAST:event_ageJTextFieldFocusLost
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       correctJTextFieldFocusLost()
     * Description  Validate numbers of correct questions.
     * @parem       java.awt.event.FocusEvent 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void correctJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_correctJTextFieldFocusLost
    {//GEN-HEADEREND:event_correctJTextFieldFocusLost
        // Set background textfield color
        String input = correctJTextField.getText();
        if(Validation.isInteger(input, 0, 50))
            correctJTextField.setBackground(white);
        else
            correctJTextField.setBackground(pink);
    }//GEN-LAST:event_correctJTextFieldFocusLost
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       totalQuestionsJTextFieldFocusLost()
     * Description  Validate total number of questions.
     * @parem       java.awt.event.FocusEvent 
     * @author      <i>Regan Kastelie</i>
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void totalQuestionsJTextFieldFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_totalQuestionsJTextFieldFocusLost
    {//GEN-HEADEREND:event_totalQuestionsJTextFieldFocusLost
        // Set background textfield color     
        String input = totalQuestionsJTextField.getText();
        if(Validation.isInteger(input, 0, 50))
            totalQuestionsJTextField.setBackground(white);
        else
            totalQuestionsJTextField.setBackground(pink);
    }//GEN-LAST:event_totalQuestionsJTextFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addEditJLabel;
    private javax.swing.JLabel ageJLabel;
    private javax.swing.JTextField ageJTextField;
    private javax.swing.JLabel balanceJLabel;
    private javax.swing.JLabel balanceJLabel1;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JTextField correctJTextField;
    private javax.swing.JPanel displayJPanel;
    private javax.swing.JLabel logoJLabel;
    private javax.swing.JLabel nameJLabel;
    private javax.swing.JTextField nameJTextField;
    private javax.swing.JButton saveJButton;
    private javax.swing.JPanel titleJPanel;
    private javax.swing.JTextField totalQuestionsJTextField;
    // End of variables declaration//GEN-END:variables
}
