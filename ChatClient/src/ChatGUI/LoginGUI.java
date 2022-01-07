
package ChatGUI;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import ChatSocketClient.DataSocketClient;
import ChatSocketClient.SocketConnectionClient;
import ChatSocketClient.SocketHandlerClient;

public class LoginGUI extends javax.swing.JFrame {
	SocketConnectionClient socket = new SocketConnectionClient();
	DataSocketClient dataSocket = new DataSocketClient();
	private String username = "";
	
    public LoginGUI(String username) {
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
        
        final LoginGUI gui = this;
        socket.startConnection();
        socket.addListenConnection("send_username_response", new SocketHandlerClient() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                boolean isSuccess = data.getBoolean("is_success");
                
                if (isSuccess){
                	WaitRoomGUI cc = new WaitRoomGUI(txtUserName.getText().trim());
            	    cc.setVisible(true);
            	    dispose();
                }
                else{
                	btnLogin.setEnabled(true);
                    String message = data.getString("message");
                    JOptionPane.showMessageDialog(gui, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // set Onclose
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// when [X] is pressed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(gui,
                        "Bạn có chắc muốn thoát ứng dụng?", "Thoát ứng dụng",
                        JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
//                	String dataSend = dataSocket.exportDataExitApp(gui.username);
//                	socket.sendData(dataSend);
                    System.exit(0);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        pnMainLogin = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập ChatApp");
        setResizable(false);

        pnMainLogin.setBackground(new java.awt.Color(0, 51, 51));
        pnMainLogin.setForeground(new java.awt.Color(255, 255, 255));

        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.setToolTipText("Nhập tên đăng nhập");
        txtUserName.setText(this.username);

        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnMainLoginLayout = new javax.swing.GroupLayout(pnMainLogin);
        pnMainLogin.setLayout(pnMainLoginLayout);
        pnMainLoginLayout.setHorizontalGroup(
            pnMainLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMainLoginLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(pnMainLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUserName)
                    .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addGap(99, 99, 99))
        );
        pnMainLoginLayout.setVerticalGroup(
            pnMainLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLoginLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMainLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMainLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {                                      
        btnLogin.setBackground(new java.awt.Color(153, 153, 153));
        
    }                                     

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {                                     
        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
    }                                    

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) { 
    	
    	String nickname = txtUserName.getText().trim();
    	
    	if(nickname.equals("")) {
    		JOptionPane.showMessageDialog(this, "Nickname không được rỗng!", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	this.btnLogin.setEnabled(false);
    	String data = dataSocket.exportDataSendUsername(nickname);
        socket.sendData(data);
        
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
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel pnMainLogin;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration                   
}
