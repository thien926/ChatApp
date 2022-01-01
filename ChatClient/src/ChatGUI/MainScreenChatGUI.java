
package ChatGUI;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.json.JSONObject;

import ChatSocketClient.DataSocketClient;
import ChatSocketClient.SocketConnectionClient;
import ChatSocketClient.SocketHandlerClient;

public class MainScreenChatGUI extends javax.swing.JFrame {
    
    private StyledDocument doc;
    private SimpleAttributeSet left;
    private SimpleAttributeSet right;
    
    private SocketConnectionClient socket = new SocketConnectionClient();
    private DataSocketClient dataSocket = new DataSocketClient();
    
    private String username = "";
    private String username2 = "";
    
    public MainScreenChatGUI(String username, String username2) {
    	this.username = username;
    	this.username2 = username2;
        initComponents();
        
        // set Location
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2);
        
        // set ImageIcon
        try {
            setIconImage((new ImageIcon(ImageIO.read(new File("images/chatIcon.png")))).getImage());
            lblImageUserCustom.setIcon(new ImageIcon(new ImageIcon("images/UserCustom.png").getImage().getScaledInstance(lblImageUserCustom.getWidth()-10, lblImageUserCustom.getHeight(), Image.SCALE_DEFAULT)));
            lblImageUserMe.setIcon(new ImageIcon(new ImageIcon("images/UserMe.png").getImage().getScaledInstance(lblImageUserMe.getWidth()-10, lblImageUserMe.getHeight(), Image.SCALE_DEFAULT)));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        // set Style TextPane
        doc = txtContentChat.getStyledDocument();
        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(left, Color.RED);
        right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(right, Color.BLUE);
        
        final MainScreenChatGUI gui = this;
        socket.addListenConnection("send_message", new SocketHandlerClient() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
                String username = data.getString("username");
                String message = data.getString("message");
                
                try {
                	if(username.equals(gui.username)) {
                    	doc.setParagraphAttributes(doc.getLength(), 100, right, false);
                    	doc.insertString(doc.getLength(), message + "\n\n", right);
                    }
                    else {
                    	gui.doc.setParagraphAttributes(doc.getLength(), 100, left, false);
                    	gui.doc.insertString(doc.getLength(), "[" + username + "] " + message + "\n\n", left );
                    }
                }catch (Exception e) {
					e.printStackTrace();
				}
            }
        });
        
        socket.addListenConnection("out_room_response", new SocketHandlerClient() {
            @Override
            public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {
            	WaitRoomGUI waitRoom = new WaitRoomGUI(gui.username);
                waitRoom.setVisible(true);
                gui.dispose(); 
            }
        });
        
        // set Onclose
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// when [X] is pressed
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	String dataSend = dataSocket.exportDataOutRoom(gui.username);
            	socket.sendData(dataSend);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        pnMainScreenChat = new javax.swing.JPanel();
        txtInput = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        ScrollPaneTextPane = new javax.swing.JScrollPane();
        txtContentChat = new javax.swing.JTextPane();
        pnUserCustom = new javax.swing.JPanel();
        lblImageUserCustom = new javax.swing.JLabel();
        lblUsernameCustom = new javax.swing.JLabel();
        pnUserMe = new javax.swing.JPanel();
        lblImageUserMe = new javax.swing.JLabel();
        lblUsernameMe = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ChatApp");
        setResizable(false);

        pnMainScreenChat.setBackground(new java.awt.Color(0, 51, 51));

        btnSend.setText("SEND");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtContentChat.setEditable(false);
        txtContentChat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtContentChat.setAlignmentX(1.0F);
        txtContentChat.setAlignmentY(1.0F);
        ScrollPaneTextPane.setViewportView(txtContentChat);

        pnUserCustom.setBackground(new java.awt.Color(255, 255, 255));

        lblImageUserCustom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblUsernameCustom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameCustom.setText(username2 + " (Đối tượng)");

        javax.swing.GroupLayout pnUserCustomLayout = new javax.swing.GroupLayout(pnUserCustom);
        pnUserCustom.setLayout(pnUserCustomLayout);
        pnUserCustomLayout.setHorizontalGroup(
            pnUserCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImageUserCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUsernameCustom, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );
        pnUserCustomLayout.setVerticalGroup(
            pnUserCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUserCustomLayout.createSequentialGroup()
                .addComponent(lblImageUserCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsernameCustom, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        pnUserMe.setBackground(new java.awt.Color(255, 255, 255));

        lblImageUserMe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblUsernameMe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsernameMe.setText(this.username + " (Bạn)");

        javax.swing.GroupLayout pnUserMeLayout = new javax.swing.GroupLayout(pnUserMe);
        pnUserMe.setLayout(pnUserMeLayout);
        pnUserMeLayout.setHorizontalGroup(
            pnUserMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImageUserMe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblUsernameMe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnUserMeLayout.setVerticalGroup(
            pnUserMeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUserMeLayout.createSequentialGroup()
                .addComponent(lblImageUserMe, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsernameMe, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnMainScreenChatLayout = new javax.swing.GroupLayout(pnMainScreenChat);
        pnMainScreenChat.setLayout(pnMainScreenChatLayout);
        pnMainScreenChatLayout.setHorizontalGroup(
            pnMainScreenChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainScreenChatLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnMainScreenChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnUserCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnUserMe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnMainScreenChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPaneTextPane)
                    .addGroup(pnMainScreenChatLayout.createSequentialGroup()
                        .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnMainScreenChatLayout.setVerticalGroup(
            pnMainScreenChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainScreenChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMainScreenChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnMainScreenChatLayout.createSequentialGroup()
                        .addComponent(pnUserCustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(pnUserMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ScrollPaneTextPane))
                .addGap(18, 18, 18)
                .addGroup(pnMainScreenChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtInput)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMainScreenChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMainScreenChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {    
    	String message = txtInput.getText().trim();
    	if(!message.equals("")) {
    		String dataSend = dataSocket.exportDataSendMessage(this.username, message);
            socket.sendData(dataSend);
            txtInput.setText("");
    	}
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
            java.util.logging.Logger.getLogger(MainScreenChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreenChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreenChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreenChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreenChatGUI("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane ScrollPaneTextPane;
    private javax.swing.JButton btnSend;
    private javax.swing.JLabel lblImageUserCustom;
    private javax.swing.JLabel lblImageUserMe;
    private javax.swing.JLabel lblUsernameCustom;
    private javax.swing.JLabel lblUsernameMe;
    private javax.swing.JPanel pnMainScreenChat;
    private javax.swing.JPanel pnUserCustom;
    private javax.swing.JPanel pnUserMe;
    private javax.swing.JTextPane txtContentChat;
    private javax.swing.JTextField txtInput;
    // End of variables declaration                   
}
