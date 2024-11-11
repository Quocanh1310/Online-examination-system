
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

public class Test extends javax.swing.JFrame {

    public Test() {
        initComponents();
        Connect();
        LoadQuestions();
    }
    
    //int answerCheck = 0;
    int marks = 0;
    
    String cor = null;
    String answer = null;
    Statement stat;
    
    public boolean answerCheck() {       
        String answerAnswer = "";
        
        if (r1.isSelected()) {
            answerAnswer = r1.getText();
            
        } else if(r2.isSelected()) {
            answerAnswer = r2.getText();
            
        } else if(r3.isSelected()) {  
            answerAnswer = r3.getText();
            
        } else if(r4.isSelected()) {     
            answerAnswer = r4.getText();       
        }
        
        if(answerAnswer.equals(cor)) {  
            marks = marks + 1;
            txtmarks.setText(String.valueOf(marks));
            
        } else if(!answerAnswer.equals(cor) && answer != null) {
            // Only deduct if marks greater than 0
            if(marks > 0) {
                
                marks = marks - 1;
                txtmarks.setText(String.valueOf(marks));     
            }
            
        } else {
            // User hasn't inputted an answer, decrease marks
            if(marks > 0) {
                
                marks = marks - 1;
                txtmarks.setText(String.valueOf(marks));
                
            }
        }
        //Use to store answers
        if(!answerAnswer.equals("")) {
            try {
                String query = "UPDATE question SET givenanswer = ? WHERE question = ?";
                pst = con.prepareStatement(query);
                pst.setString(1, answerAnswer);
                pst.setString(2, jLabel2.getText());
                pst.execute();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return true;    
    }
    
    private void NullAllGivenAnswers() {
        try {
            String query = "UPDATE question SET givenanswer = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, null);
            pst.execute();
                
        } catch (SQLException ex) {
            String errorMessage = "An error occurred while accessing the database: " + ex.getMessage();
            System.err.println(errorMessage);
        }
    }
    
    private boolean AlreadyAnswered() {
        try {
            String query = "SELECT givenanswer FROM question WHERE question = '"+jLabel2.getText()+"'";
            stat = con.prepareStatement(query);
            
            ResultSet res = stat.executeQuery(query);
            while(res.next()) {

                answer = res.getString("givenanswer");
                if(answer == null) {
                    return false;
                }
                break;
            }

            if(r1.getText().equals(answer)) {

                r1.setSelected(true);

            } else if(r2.getText().equals(answer)) {

                r2.setSelected(true);

            } else if(r3.getText().equals(answer)) {

                r3.setSelected(true);

            } else if(r4.getText().equals(answer)) {

                r4.setSelected(true);
            }
                
        } catch (SQLException ex) {
            System.out.println("Exception Caught");
            ex.printStackTrace();
        }
        return true;  
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/onlineexam","root","root");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void LoadQuestions() {
        String query = "select * from question";
        Statement stat = null;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(query);
        
            while(rs.next()) {
                jLabel3.setText(rs.getString(1));
                jLabel2.setText(rs.getString(2));
                r1.setText(rs.getString(3));
                r2.setText(rs.getString(4));
                r3.setText(rs.getString(5));
                r4.setText(rs.getString(6));
                cor = rs.getString(7);
                //For 1 row only
                break;                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtmarks = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtmarks1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(960, 540));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 204));
        jLabel1.setText("Online Test");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Questions");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(r1);
        r1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        r1.setText("jRadioButton1");
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(r2);
        r2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        r2.setText("jRadioButton2");

        buttonGroup1.add(r3);
        r3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        r3.setText("jRadioButton3");

        buttonGroup1.add(r4);
        r4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        r4.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r4)
                    .addComponent(r3)
                    .addComponent(r2)
                    .addComponent(r1))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(r1)
                .addGap(30, 30, 30)
                .addComponent(r2)
                .addGap(30, 30, 30)
                .addComponent(r3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(r4)
                .addGap(41, 41, 41))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("No");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtmarks.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtmarks.setForeground(new java.awt.Color(255, 51, 51));
        txtmarks.setText("0");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Question No.");

        txtmarks1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtmarks1.setForeground(new java.awt.Color(255, 51, 51));
        txtmarks1.setText("Marks:");

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Return to Menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmarks1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmarks))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(278, 278, 278)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmarks)
                    .addComponent(txtmarks1))
                .addGap(36, 36, 36))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(answerCheck()) {
            try {
                if(rs.next()) {
                    jLabel3.setText(rs.getString(1));
                    jLabel2.setText(rs.getString("question"));
                    r1.setText(rs.getString(3));
                    r2.setText(rs.getString(4));
                    r3.setText(rs.getString(5));
                    r4.setText(rs.getString(6));
                    
                    cor = rs.getString(7);
                
                    if(AlreadyAnswered()) {
                        //Clear all buttons when proceed to next question
                        buttonGroup1.clearSelection();
                    }   
                } else {
                    JOptionPane.showMessageDialog(this, "You finished the test!");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Mental".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Test().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r4;
    private javax.swing.JLabel txtmarks;
    private javax.swing.JLabel txtmarks1;
    // End of variables declaration//GEN-END:variables
}
