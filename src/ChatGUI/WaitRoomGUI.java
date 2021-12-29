
package ChatGUI;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import ChatSocketClient.DataSocketClient;
import ChatSocketClient.SocketConnectionClient;
import ChatSocketClient.SocketHandlerClient;

public class WaitRoomGUI extends javax.swing.JFrame {
	private static String username = "";
	private static String username2 = "";
	private SocketConnectionClient socket = new SocketConnectionClient();
    private DataSocketClient dataSocket = new DataSocketClient();
	
    public WaitRoomGUI(String username) {
    	this.username = username;
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
        
        final WaitRoomGUI gui = this;
        socket.addListenConnection("send_invitation", new SocketHandlerClient() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                String username2 = data.getString("username");
                gui.username2 = username2;
                lblMessage.setText("Đối tượng: " + username2);
                btnSuccessWaitRoom.setText("Đồng ý");
                btnSuccessWaitRoom.setEnabled(true);
                btnErrorWaitRoom.setText("Từ chối");
            }
        });
        
        
        socket.addListenConnection("start_message", new SocketHandlerClient() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                boolean is_started = data.getBoolean("is_started");
                
                if (is_started){
                	
                	System.out.println(gui.username);
                	MainScreenChatGUI mainScreen = new MainScreenChatGUI(gui.username, gui.username2);
	                mainScreen.setVisible(true);
	                dispose();
                }
                else{
                	gui.btnSuccessWaitRoom.setEnabled(true);
                	lblMessage.setText("Đang ở phòng đợi!");
                	btnSuccessWaitRoom.setText("Bắt đầu ghép đôi");
                	btnErrorWaitRoom.setText("Hủy");
                }
            }
        });
        
        socket.addListenConnection("exit_app_response", new SocketHandlerClient() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
            	
            	gui.dispose();
            	LoginGUI lg = new LoginGUI(gui.username);
                lg.setVisible(true);
                
            }
        });
        
//        socket.addListenConnection("start_message", new SocketHandlerClient() {
//            @Override
//            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
//                boolean is_started = data.getBoolean("is_started");
//                
//                if (is_started){
//                    get_pairing_status_false.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sgu/chat/images/get-pairing/get-pairing-status-true.png"))); // NOI18N
//        
//                    MainScreen mainScreen = new MainScreen(nickname);
//                    mainScreen.setVisible(true);
//                    dispose(); 
//                }
//                else{
//                    WaitingPairing waitingPairing = new WaitingPairing();
//                    waitingPairing.setVisible(true);
//                    dispose(); 
//                }
//            }
//        });
        
        // set onClose
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// when [X] is pressed
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                String dataSend = dataSocket.exportDataExitApp(gui.username);
                socket.sendData(dataSend);
                
//                LoginGUI lg = new LoginGUI();
//                lg.setVisible(true);
//                gui.dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

    	pnMainWaitRoom = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();
        btnSuccessWaitRoom = new javax.swing.JButton();
        btnErrorWaitRoom = new javax.swing.JButton();
        lblUserName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chờ ghép đôi");
        setResizable(false);

        pnMainWaitRoom.setBackground(new java.awt.Color(0, 51, 51));

        lblMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(255, 255, 255));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("Đang ở phòng đợi!");

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

        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUserName.setText("Username : " + this.username);

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
            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnMainWaitRoomLayout.setVerticalGroup(
            pnMainWaitRoomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainWaitRoomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuccessWaitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnErrorWaitRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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
//        MainScreenChatGUI mainScreen = new MainScreenChatGUI();
//        mainScreen.setVisible(true);
//        this.dispose();
        
        switch (btnSuccessWaitRoom.getText()) {
			case "Bắt đầu ghép đôi": {
				btnSuccessWaitRoom.setEnabled(false);
				String username = this.username;
		        String data = dataSocket.exportDataWaitingPairing(username);
		        socket.sendData(data);
		        this.lblMessage.setText("Đang chờ ghép đôi...!");
				break;
			}
			case "Đồng ý": {
				btnSuccessWaitRoom.setEnabled(false);
				String dataSend = dataSocket.exportDataAcceptPairing(this.username, true);     
		        socket.sendData(dataSend);
				break;
			}
		default:
			break;
		}
    }                                                  

    private void btnErrorWaitRoomActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	switch (btnErrorWaitRoom.getText()) {
//			case "Bắt đầu ghép đôi": {
//				String username = this.username;
//		        String data = dataSocket.exportDataGoMatch(username);
//		        socket.sendData(data);
//		        this.lblMessage.setText("Đang chờ ghép đôi...!");
//				break;
//			}
			case "Từ chối": {
				String dataSend = dataSocket.exportDataAcceptPairing(this.username, false);     
		        socket.sendData(dataSend);
				break;
			}
		default:
			break;
		}
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
                new WaitRoomGUI("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnErrorWaitRoom;
    private javax.swing.JButton btnSuccessWaitRoom;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JPanel pnMainWaitRoom;
    private javax.swing.JLabel lblUserName;
    // End of variables declaration                   
}
