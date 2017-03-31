/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_practica_1_busqueda_local;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ale
 */
public class MainWindow extends javax.swing.JFrame {
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        JFrame frame = this;
        initComponents();
        
        jRadioButtonSA.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    jTextFieldMaxIt.setEnabled(true);
                    jTextFieldItStep.setEnabled(true);
                    jTextFieldK.setEnabled(true);
                    jTextFieldLambda.setEnabled(true);
                }
                else {
                    jTextFieldMaxIt.setEnabled(false);
                    jTextFieldItStep.setEnabled(false);
                    jTextFieldK.setEnabled(false);
                    jTextFieldLambda.setEnabled(false);
                }
            }
        });
        
        jButtonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        jButtonExec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!jTextFieldCenters.getText().isEmpty() && !jTextFieldCentersSeed.getText().isEmpty()
                            && !jTextFieldSensors.getText().isEmpty() && !jTextFieldSensorsSeed.getText().isEmpty()) {

                        int c, cSeed, s, sSeed;
                        
                        c = Integer.parseInt(jTextFieldCenters.getText());
                        cSeed = Integer.parseInt(jTextFieldCentersSeed.getText());
                        s = Integer.parseInt(jTextFieldSensors.getText());
                        sSeed = Integer.parseInt(jTextFieldSensorsSeed.getText());
                        
                        if (jRadioButtonHC.isSelected() || (jRadioButtonSA.isSelected() && !jTextFieldMaxIt.getText().isEmpty()
                                && !jTextFieldItStep.getText().isEmpty() && !jTextFieldK.getText().isEmpty()
                                && !jTextFieldLambda.getText().isEmpty())) {

                            DistributionNetwork network = new DistributionNetwork(s, sSeed, c, cSeed);

                            if (jRadioButtonHC.isSelected()) {
                                Problem p = new Problem(network,
                                        new ProblemSuccessorFunction(),
                                        new ProblemGoalTest(),
                                        new ProblemHeuristicFunction());

                                Search alg = new HillClimbingSearch();
                                SearchAgent agent = new SearchAgent(p, alg);
                            }
                            else {
                                int maxIt, it, k, lambda;
                                
                                maxIt = Integer.parseInt(jTextFieldMaxIt.getText());
                                it = Integer.parseInt(jTextFieldItStep.getText());
                                k = Integer.parseInt(jTextFieldK.getText());
                                lambda = Integer.parseInt(jTextFieldLambda.getText());
                            }
                            
                            // Take the solution and draw it!
                            /*((GraphCanvas)graphCanvasResult).addNode("C0", 0, 0, GraphCanvas.NodeType.CENTER);
                            ((GraphCanvas)graphCanvasResult).addNode("S1", 1, 1, GraphCanvas.NodeType.SENSOR);
                            ((GraphCanvas)graphCanvasResult).addNode("C1", 2, 2, GraphCanvas.NodeType.CENTER);
                            ((GraphCanvas)graphCanvasResult).addNode("C2", 50, 50, GraphCanvas.NodeType.CENTER);
                            ((GraphCanvas)graphCanvasResult).addNode("S2", 100, 100, GraphCanvas.NodeType.SENSOR);
                            ((GraphCanvas)graphCanvasResult).addEdge(0, 4); from 0 to 4
                            ((GraphCanvas)graphCanvasResult).addEdge(4, 0); from 4 to 0*/
                        }
                        else {
                            JOptionPane.showMessageDialog(frame, "All input parameters for SA are obligatory:\n\t> Max. amount of Iterations.\n\t> Amount of Iterations per Step.\n\t> K-value.\n\t> Lambda value.",
                                "Error in Simulated Annealing Parameters", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "All input parameters are obligatory:\n\t> Centers.\n\t> Centers Seed.\n\t> Sensors.\n\t> Sensors Seed.",
                                "Error in Input Parameters", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "All the fields must have an integer value!",
                        "Error in Parameters Type", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception gEx) {
                    JOptionPane.showMessageDialog(frame, "The next error has occurred:\n" + gEx.toString(),
                        "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTech = new javax.swing.ButtonGroup();
        jPanelSearchTech = new javax.swing.JPanel();
        jRadioButtonHC = new javax.swing.JRadioButton();
        jRadioButtonSA = new javax.swing.JRadioButton();
        jPanelInput = new javax.swing.JPanel();
        jLabelCenter = new javax.swing.JLabel();
        jLabelCenterSeed = new javax.swing.JLabel();
        jLabelSensorSeed = new javax.swing.JLabel();
        jLabelSensors = new javax.swing.JLabel();
        jTextFieldCenters = new javax.swing.JTextField();
        jTextFieldCentersSeed = new javax.swing.JTextField();
        jTextFieldSensors = new javax.swing.JTextField();
        jTextFieldSensorsSeed = new javax.swing.JTextField();
        jPanelSA = new javax.swing.JPanel();
        jLabelMaxIt = new javax.swing.JLabel();
        jLabelItStep = new javax.swing.JLabel();
        jLabelK = new javax.swing.JLabel();
        jLabelLambda = new javax.swing.JLabel();
        jTextFieldMaxIt = new javax.swing.JTextField();
        jTextFieldItStep = new javax.swing.JTextField();
        jTextFieldK = new javax.swing.JTextField();
        jTextFieldLambda = new javax.swing.JTextField();
        jButtonClose = new javax.swing.JButton();
        jButtonExec = new javax.swing.JButton();
        jPanelResults = new javax.swing.JPanel();
        jPanelCont = new javax.swing.JPanel();
        graphCanvasResult = new GraphCanvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AI - Local Search");
        setName("MainFrame"); // NOI18N

        jPanelSearchTech.setBorder(javax.swing.BorderFactory.createTitledBorder("Local Search Technique"));

        buttonGroupTech.add(jRadioButtonHC);
        jRadioButtonHC.setSelected(true);
        jRadioButtonHC.setLabel("Hill Climbing");
        jRadioButtonHC.setNextFocusableComponent(jRadioButtonSA);
        jRadioButtonHC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHCActionPerformed(evt);
            }
        });

        buttonGroupTech.add(jRadioButtonSA);
        jRadioButtonSA.setLabel("Simulated Annealing");
        jRadioButtonSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSearchTechLayout = new javax.swing.GroupLayout(jPanelSearchTech);
        jPanelSearchTech.setLayout(jPanelSearchTechLayout);
        jPanelSearchTechLayout.setHorizontalGroup(
            jPanelSearchTechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchTechLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSearchTechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonHC)
                    .addComponent(jRadioButtonSA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSearchTechLayout.setVerticalGroup(
            jPanelSearchTechLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchTechLayout.createSequentialGroup()
                .addComponent(jRadioButtonHC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonSA))
        );

        jPanelInput.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Parameters"));

        jLabelCenter.setText("Centers:");

        jLabelCenterSeed.setText("Centers Random Seed:");

        jLabelSensorSeed.setText("Sensors Random Seed:");

        jLabelSensors.setText("Sensors:");

        javax.swing.GroupLayout jPanelInputLayout = new javax.swing.GroupLayout(jPanelInput);
        jPanelInput.setLayout(jPanelInputLayout);
        jPanelInputLayout.setHorizontalGroup(
            jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInputLayout.createSequentialGroup()
                        .addComponent(jLabelCenter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldCenters, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInputLayout.createSequentialGroup()
                        .addComponent(jLabelSensorSeed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldSensorsSeed, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInputLayout.createSequentialGroup()
                        .addComponent(jLabelCenterSeed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldCentersSeed, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInputLayout.createSequentialGroup()
                        .addComponent(jLabelSensors)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldSensors, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelInputLayout.setVerticalGroup(
            jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCenter)
                    .addComponent(jTextFieldCenters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCenterSeed)
                    .addComponent(jTextFieldCentersSeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSensors)
                    .addComponent(jTextFieldSensors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSensorSeed)
                    .addComponent(jTextFieldSensorsSeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelSA.setBorder(javax.swing.BorderFactory.createTitledBorder("Simulated Annealing Parameters"));

        jLabelMaxIt.setText("Max. Iterations");

        jLabelItStep.setText("It. per step:");

        jLabelK.setText("K-value:");

        jLabelLambda.setText("Lambda value:");
        jLabelLambda.setToolTipText("");

        jTextFieldMaxIt.setEnabled(false);

        jTextFieldItStep.setEnabled(false);

        jTextFieldK.setEnabled(false);

        jTextFieldLambda.setEnabled(false);

        javax.swing.GroupLayout jPanelSALayout = new javax.swing.GroupLayout(jPanelSA);
        jPanelSA.setLayout(jPanelSALayout);
        jPanelSALayout.setHorizontalGroup(
            jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMaxIt)
                    .addComponent(jLabelItStep)
                    .addComponent(jLabelK)
                    .addComponent(jLabelLambda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldLambda, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jTextFieldK)
                    .addComponent(jTextFieldMaxIt)
                    .addComponent(jTextFieldItStep))
                .addContainerGap())
        );
        jPanelSALayout.setVerticalGroup(
            jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaxIt)
                    .addComponent(jTextFieldMaxIt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelItStep)
                    .addComponent(jTextFieldItStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelK)
                    .addComponent(jTextFieldK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanelSALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLambda)
                    .addComponent(jTextFieldLambda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonExec.setText("Execute");

        jPanelResults.setBorder(javax.swing.BorderFactory.createTitledBorder("Results"));

        jPanelCont.setBackground(new java.awt.Color(255, 255, 255));

        graphCanvasResult.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout graphCanvasResultLayout = new javax.swing.GroupLayout(graphCanvasResult);
        graphCanvasResult.setLayout(graphCanvasResultLayout);
        graphCanvasResultLayout.setHorizontalGroup(
            graphCanvasResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        graphCanvasResultLayout.setVerticalGroup(
            graphCanvasResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelContLayout = new javax.swing.GroupLayout(jPanelCont);
        jPanelCont.setLayout(jPanelContLayout);
        jPanelContLayout.setHorizontalGroup(
            jPanelContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
            .addGroup(jPanelContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelContLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(graphCanvasResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelContLayout.setVerticalGroup(
            jPanelContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelContLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(graphCanvasResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanelResultsLayout = new javax.swing.GroupLayout(jPanelResults);
        jPanelResults.setLayout(jPanelResultsLayout);
        jPanelResultsLayout.setHorizontalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelResultsLayout.setVerticalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelSA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSearchTech, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jButtonClose)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExec, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelSearchTech, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelSA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonExec)
                            .addComponent(jButtonClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonHCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonHCActionPerformed

    private void jRadioButtonSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSAActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTech;
    private javax.swing.JPanel graphCanvasResult;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonExec;
    private javax.swing.JLabel jLabelCenter;
    private javax.swing.JLabel jLabelCenterSeed;
    private javax.swing.JLabel jLabelItStep;
    private javax.swing.JLabel jLabelK;
    private javax.swing.JLabel jLabelLambda;
    private javax.swing.JLabel jLabelMaxIt;
    private javax.swing.JLabel jLabelSensorSeed;
    private javax.swing.JLabel jLabelSensors;
    private javax.swing.JPanel jPanelCont;
    private javax.swing.JPanel jPanelInput;
    private javax.swing.JPanel jPanelResults;
    private javax.swing.JPanel jPanelSA;
    private javax.swing.JPanel jPanelSearchTech;
    private javax.swing.JRadioButton jRadioButtonHC;
    private javax.swing.JRadioButton jRadioButtonSA;
    private javax.swing.JTextField jTextFieldCenters;
    private javax.swing.JTextField jTextFieldCentersSeed;
    private javax.swing.JTextField jTextFieldItStep;
    private javax.swing.JTextField jTextFieldK;
    private javax.swing.JTextField jTextFieldLambda;
    private javax.swing.JTextField jTextFieldMaxIt;
    private javax.swing.JTextField jTextFieldSensors;
    private javax.swing.JTextField jTextFieldSensorsSeed;
    // End of variables declaration//GEN-END:variables
}
