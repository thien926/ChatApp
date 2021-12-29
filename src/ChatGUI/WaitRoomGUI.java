
package ChatGUI;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WaitRoomGUI extends javax.swing.JFrame {

    public WaitRoomGUI() {
        initComponents();
        
        // set Location
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        
        // set ImageIcon
        try {
            setIconImage((new ImageIcon(ImageIO.read(new File("images/chatIcon.png")))).getImage());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        // set onClose
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// when [X] is pressed
        final WaitRoomGUI gui = this;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                LoginGUI lg = new LoginGUI();
                lg.setVisible(true);
                gui.dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        pnMainWaitRoom = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnSuccessWaitRoom = new javax.swing.JButton();
        btnErrorWaitRoom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chờ ghép đôi");
        setResizable(false);

        pnMainWaitRoom.setBackground(new java.awt.Color(0, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Đang chờ ghép đôi ...");

        btnSuccessWaitRoom.setBackground(new java.awt.Color(204, 204, 204));
        btnSuccessWaitRoom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSuccessWaitRoom.setText("Bắt đầu ghép đôi");
        btnSuccessWaitRoom.setPreferredSize(new java.awt.Dimension(101, 29));
        btnSuccessWaitRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSuccessWaitRoomMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSuccessWaitRoomMouseExited(evt);
            }
        });
        btnSuccessWaitRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuccessWaitRoomActionPerformed(evt);
            }
        });

        btnErrorWaitRoom.setBackground(new java.awt.Color(255, 51, 51));
        btnErrorWaitRoom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnErrorWaitRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnErrorWaitRoom.setText("Hủy");
        btnErrorWaitRoom.setPreferredSize(new java.awt.Dimension(101, 29));
        btnErrorWaitRoom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnErrorWaitRoomMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnErrorWaitRoomMouseExited(evt);
            }
        });
        btnErrorWaitRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnErrorWaitRoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMainWaitRoomLayout = new javax.swing.GroupLayout(pnMainWaitRoom);
        pnMainWaitRoom.setLayout(pnMainWaitRoomLayout);
        pnMainWaitRoomLayout.setHorizontalGroup(
            pnMainWaitRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainWaitRoomLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(pnMainWaitRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnErrorWaitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuccessWaitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnMainWaitRoomLayout.setVerticalGroup(
            pnMainWaitRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainWaitRoomLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnSuccessWaitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnErrorWaitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMainWaitRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMainWaitRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void btnSuccessWaitRoomActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        MainScreenChatGUI mainScreen = new MainScreenChatGUI();
        mainScreen.setVisible(true);
        this.dispose();
    }                                                  

    private void btnErrorWaitRoomActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void btnErrorWaitRoomMouseEntered(java.awt.event.MouseEvent evt) {                                              
        btnErrorWaitRoom.setBackground(new java.awt.Color(250,0,0));
    }                                             

    private void btnErrorWaitRoomMouseExited(java.awt.event.MouseEvent evt) {                                             
        btnErrorWaitRoom.setBackground(new java.awt.Color(255,51,51));
    }                                            

    private void btnSuccessWaitRoomMouseEntered(java.awt.event.MouseEvent evt) {                                                
        btnSuccessWaitRoom.setBackground(new java.awt.Color(153, 153, 153));
    }                                               

    private void btnSuccessWaitRoomMouseExited(java.awt.event.MouseEvent evt) {                                               
        btnSuccessWaitRoom.setBackground(new java.awt.Color(204, 204, 204));
    }                                              

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WaitRoomGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WaitRoomGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WaitRoomGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WaitRoomGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WaitRoomGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnErrorWaitRoom;
    private javax.swing.JButton btnSuccessWaitRoom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnMainWaitRoom;
    // End of variables declaration                   
}
