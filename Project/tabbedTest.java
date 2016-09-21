import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.JSeparator;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;


public class tabbedTest {
	
	/* RSA Fields */
	private JFrame frame;
	private JTextField bitLength;
	private JTextArea pText;
	private JTextArea qText;
	
	private JTextArea eText;
	private JTextArea dText;
	private JTextArea nText1;
	private JTextArea nText2;
	
	private JTextArea plainTextEncrypt;
	private JTextArea plainTextDecrypt;
	private JTextArea cipherTextEncrypt;
	private JTextArea cipherTextDecrypt;
	
	/* El Gamal Fields*/
	private JTextArea alphaText;
	private JTextArea betaText;
	private JTextArea primeText;
	private JTextArea privateKeyA;
	
	private JTextArea elg_plainText;
	private JTextArea cipherEncrypt_y1;
	private JTextArea cipherEncrypt_y2;
	private JTextArea cipherDecrypt_y1;
	private JTextArea cipherDecrypt_y2;
	private JTextArea elg_Decrypted;
	
	/* AES Fields */
	private JTextArea plainText;
	private JTextArea cipherText;
	private JTextArea decryptedText;
	private JTextField decryptPrivateKey;
	//private JTextArea decryptcipherText;
	private JTextField privateKey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tabbedTest window = new tabbedTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tabbedTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setBounds(100, 100, 876, 771);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 846, 713);
		frame.getContentPane().add(tabbedPane);
		
		JPanel RSA_Panel = new JPanel();
		tabbedPane.addTab("RSA", null, RSA_Panel, null);
		RSA_Panel.setLayout(null);
		
		JLabel label = new JLabel("Bit length =");
		label.setBounds(12, 16, 76, 16);
		RSA_Panel.add(label);
		
		bitLength = new JTextField();
		bitLength.setColumns(10);
		bitLength.setBounds(88, 13, 116, 22);
		RSA_Panel.add(bitLength);
		
		JButton button = new JButton("Generate Primes");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int bitLengthInt = Integer.parseInt(bitLength.getText());
					if(bitLengthInt > 1024 || bitLengthInt < 1) {
						String message = "Please enter a number between 1 and 1024";
						JOptionPane.showMessageDialog(null, message);
					}
					else {
						String[] primes = RSA.generatePrimes(bitLengthInt);
						pText.setText(primes[0].toString());
						qText.setText(primes[1].toString());
					}
				} catch (NumberFormatException e1) {
					String message = "Enter only numeric text";
					JOptionPane.showMessageDialog(null, message);
				}
			}
			
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(12, 66, 141, 25);
		RSA_Panel.add(button);
		
		JButton button_1 = new JButton("Generate Keys From P and Q");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] keys = RSA.generateKeys(pText.getText(), qText.getText());
					nText1.setText(keys[0]);
					nText2.setText(keys[0]);
					eText.setText(keys[1]);
					dText.setText(keys[2]);
				} catch (Exception e1) {
					String message = "Please Generate P and Q first";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		button_1.setBounds(12, 106, 218, 25);
		RSA_Panel.add(button_1);
		
		JLabel label_1 = new JLabel("P =");
		label_1.setBounds(307, 72, 30, 16);
		RSA_Panel.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 13, 154, 130);
		RSA_Panel.add(scrollPane);
		
		pText = new JTextArea();
		scrollPane.setViewportView(pText);
		pText.setLineWrap(true);
		pText.setEditable(false);
		
		JLabel label_2 = new JLabel("Q =");
		label_2.setBounds(587, 72, 30, 16);
		RSA_Panel.add(label_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(630, 13, 154, 130);
		RSA_Panel.add(scrollPane_1);
		
		qText = new JTextArea();
		scrollPane_1.setViewportView(qText);
		qText.setLineWrap(true);
		qText.setEditable(false);
		
		JPanel encryptionPanel = new JPanel();
		encryptionPanel.setBorder(new TitledBorder(null, "Encryption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		encryptionPanel.setBounds(12, 154, 395, 529);
		RSA_Panel.add(encryptionPanel);
		encryptionPanel.setLayout(null);
		
		JLabel label_3 = new JLabel("Public Key");
		label_3.setBounds(12, 29, 91, 16);
		encryptionPanel.add(label_3);
		
		JLabel label_4 = new JLabel("e =");
		label_4.setBounds(12, 45, 30, 16);
		encryptionPanel.add(label_4);
		
		JLabel label_5 = new JLabel("N =");
		label_5.setBounds(194, 45, 30, 16);
		encryptionPanel.add(label_5);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(13, 69, 152, 128);
		encryptionPanel.add(scrollPane_2);
		
		eText = new JTextArea();
		scrollPane_2.setViewportView(eText);
		eText.setLineWrap(true);
		eText.setEditable(true);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(195, 69, 152, 128);
		encryptionPanel.add(scrollPane_3);
		
		nText1 = new JTextArea();
		scrollPane_3.setViewportView(nText1);
		nText1.setLineWrap(true);
		nText1.setEditable(true);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(13, 368, 334, 109);
		encryptionPanel.add(scrollPane_5);
		
		cipherTextEncrypt = new JTextArea();
		scrollPane_5.setViewportView(cipherTextEncrypt);
		cipherTextEncrypt.setLineWrap(true);
		cipherTextEncrypt.setEditable(false);
		
		JLabel label_6 = new JLabel("Ciphertext");
		label_6.setBounds(12, 350, 76, 16);
		encryptionPanel.add(label_6);
		
		JLabel label_7 = new JLabel("Plaintext");
		label_7.setBounds(12, 209, 76, 16);
		encryptionPanel.add(label_7);
		
		JLabel lblTimeRequireds = new JLabel("Time Required(\u03BCs)");
		lblTimeRequireds.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequireds.setBounds(165, 495, 120, 17);
		encryptionPanel.add(lblTimeRequireds);
		
		final JLabel label_47 = new JLabel("0000");
		label_47.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_47.setBounds(297, 495, 66, 17);
		encryptionPanel.add(label_47);
		
		JButton button_2 = new JButton("Encrypt Plaintext");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String plainText = plainTextEncrypt.getText();
					String eStr = eText.getText();
					String NStr = nText1.getText();
					final long startTime = System.nanoTime();
					String encryptedData = RSA.encryptMessage(plainText, eStr, NStr);
					cipherTextEncrypt.setText(encryptedData);
					final long duration = System.nanoTime() - startTime;
					label_47.setText(Long.toString(duration / 1000));
				} catch (Exception e1) {
					String message = "Please enter only numeric text";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		button_2.setBounds(12, 491, 141, 25);
		encryptionPanel.add(button_2);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 227, 334, 109);
		encryptionPanel.add(scrollPane_4);
		
		plainTextEncrypt = new JTextArea();
		scrollPane_4.setViewportView(plainTextEncrypt);
		plainTextEncrypt.setLineWrap(true);
		plainTextEncrypt.setEditable(true);
		
		JPanel decryptionPanel = new JPanel();
		decryptionPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Decryption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		decryptionPanel.setBounds(426, 154, 395, 529);
		RSA_Panel.add(decryptionPanel);
		decryptionPanel.setLayout(null);
		
		JLabel label_8 = new JLabel("Private Key");
		label_8.setBounds(12, 29, 91, 16);
		decryptionPanel.add(label_8);
		
		JLabel label_9 = new JLabel("d =");
		label_9.setBounds(12, 45, 30, 16);
		decryptionPanel.add(label_9);
		
		JLabel label_10 = new JLabel("N =");
		label_10.setBounds(194, 45, 30, 16);
		decryptionPanel.add(label_10);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(13, 69, 152, 128);
		decryptionPanel.add(scrollPane_6);
		
		dText = new JTextArea();
		scrollPane_6.setViewportView(dText);
		dText.setLineWrap(true);
		dText.setEditable(true);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(195, 69, 152, 128);
		decryptionPanel.add(scrollPane_7);
		
		nText2 = new JTextArea();
		scrollPane_7.setViewportView(nText2);
		nText2.setLineWrap(true);
		nText2.setEditable(true);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(13, 227, 334, 109);
		decryptionPanel.add(scrollPane_8);
		
		cipherTextDecrypt = new JTextArea();
		scrollPane_8.setViewportView(cipherTextDecrypt);
		cipherTextDecrypt.setLineWrap(true);
		cipherTextDecrypt.setEditable(true);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(13, 368, 334, 109);
		decryptionPanel.add(scrollPane_9);
		
		plainTextDecrypt = new JTextArea();
		scrollPane_9.setViewportView(plainTextDecrypt);
		plainTextDecrypt.setLineWrap(true);
		plainTextDecrypt.setEditable(false);
		
		JLabel lblTimeRequireds_1 = new JLabel("Time Required(\u03BCs)");
		lblTimeRequireds_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequireds_1.setBounds(177, 495, 119, 17);
		decryptionPanel.add(lblTimeRequireds_1);
		
		final JLabel label_48 = new JLabel("0000");
		label_48.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_48.setBounds(296, 495, 66, 17);
		decryptionPanel.add(label_48);
		
		JButton button_3 = new JButton("Decrypt Ciphertext");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cipherText = cipherTextDecrypt.getText();
					String dStr = dText.getText();
					String NStr = nText2.getText();
					final long startTime = System.nanoTime();
					String decryptedData = RSA.decryptMessage(cipherText, dStr, NStr);
					plainTextDecrypt.setText(decryptedData);
					final long duration = System.nanoTime() - startTime;
					label_48.setText(Long.toString(duration / 1000));
				} catch (Exception e1) {
					String message = "Please enter only numeric text";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		button_3.setBounds(12, 491, 153, 25);
		decryptionPanel.add(button_3);
		
		JLabel label_11 = new JLabel("Plaintext");
		label_11.setBounds(12, 350, 76, 16);
		decryptionPanel.add(label_11);
		
		JLabel label_12 = new JLabel("Ciphertext");
		label_12.setBounds(12, 209, 76, 16);
		decryptionPanel.add(label_12);
		
		JPanel elGamal_Panel = new JPanel();
		tabbedPane.addTab("El-Gamal", null, elGamal_Panel, null);
		elGamal_Panel.setLayout(null);
		
		JLabel label_13 = new JLabel("Encryption");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_13.setBounds(12, 6, 86, 19);
		elGamal_Panel.add(label_13);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 424, 767, 2);
		elGamal_Panel.add(separator_3);
		
		JLabel label_26 = new JLabel("Decryption");
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_26.setBounds(12, 466, 86, 19);
		elGamal_Panel.add(label_26);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 490, 76, 2);
		elGamal_Panel.add(separator_4);
		
		JLabel lblTimeRequireds_3 = new JLabel("Time Required(\u03BCs)");
		lblTimeRequireds_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequireds_3.setBounds(474, 649, 122, 19);
		elGamal_Panel.add(lblTimeRequireds_3);
		
		final JLabel label_30 = new JLabel("0000");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_30.setBounds(599, 649, 69, 19);
		elGamal_Panel.add(label_30);
		
		JButton button_7 = new JButton("Decrypt");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					final long startTime = System.nanoTime();
					String allY1 = cipherDecrypt_y1.getText();
					String allY2 = cipherDecrypt_y2.getText();
					String aStr = privateKeyA.getText();
					String pStr = primeText.getText();
					
					String[] y1 = allY1.split(" ");
					String[] y2 = allY2.split(" ");
					
					String[][] encryptedData = {y1, y2};
					
					String decryptedMessage = ElGamal.decryptData(encryptedData, aStr, pStr);
					elg_Decrypted.setText(decryptedMessage);
					final long duration = System.nanoTime() - startTime;
					label_30.setText(Long.toString(duration / 1000));
				} catch (Exception e1) {
					String message = "Please enter only numeric text";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_7.setBounds(332, 647, 89, 23);
		elGamal_Panel.add(button_7);
		
		JLabel label_31 = new JLabel("Decrypted Text");
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_31.setBounds(425, 505, 102, 19);
		elGamal_Panel.add(label_31);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 30, 76, 2);
		elGamal_Panel.add(separator_5);
		
		JLabel label_16 = new JLabel("Private Key");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_16.setBounds(215, 439, 69, 22);
		elGamal_Panel.add(label_16);
		
		JLabel label_21 = new JLabel("a =");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_21.setBounds(341, 442, 30, 16);
		elGamal_Panel.add(label_21);
		
		JButton generateKeysBtn = new JButton("Generate Private and Public Keys");
		generateKeysBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] keys = ElGamal.generateKeys();
				alphaText.setText(keys[0]);
				betaText.setText(keys[1]);
				primeText.setText(keys[2]);
				privateKeyA.setText(keys[3]);
			}
		});
		generateKeysBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		generateKeysBtn.setBounds(12, 189, 257, 23);
		elGamal_Panel.add(generateKeysBtn);
		
		JLabel label_14 = new JLabel("Alpha - \u03B1");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_14.setBounds(12, 45, 62, 22);
		elGamal_Panel.add(label_14);
		
		JScrollPane scrollPane_18 = new JScrollPane();
		scrollPane_18.setBounds(563, 501, 166, 121);
		elGamal_Panel.add(scrollPane_18);
		
		elg_Decrypted = new JTextArea();
		scrollPane_18.setViewportView(elg_Decrypted);
		elg_Decrypted.setEditable(false);
		elg_Decrypted.setLineWrap(true);
		
		JLabel label_15 = new JLabel("Beta - \u03B2");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_15.setBounds(278, 45, 62, 22);
		elGamal_Panel.add(label_15);
		
		JScrollPane scrollPane_19 = new JScrollPane();
		scrollPane_19.setBounds(348, 43, 162, 117);
		elGamal_Panel.add(scrollPane_19);
		
		betaText = new JTextArea();
		scrollPane_19.setViewportView(betaText);
		betaText.setLineWrap(true);
		betaText.setEditable(true);
		
		JLabel label_17 = new JLabel("Prime - P");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_17.setBounds(534, 49, 62, 20);
		elGamal_Panel.add(label_17);
		
		JScrollPane scrollPane_14 = new JScrollPane();
		scrollPane_14.setBounds(608, 43, 162, 117);
		elGamal_Panel.add(scrollPane_14);
		
		primeText = new JTextArea();
		scrollPane_14.setViewportView(primeText);
		primeText.setLineWrap(true);
		primeText.setEditable(true);
		
		JScrollPane scrollPane_20 = new JScrollPane();
		scrollPane_20.setBounds(86, 45, 160, 115);
		elGamal_Panel.add(scrollPane_20);
		
		alphaText = new JTextArea();
		scrollPane_20.setViewportView(alphaText);
		alphaText.setLineWrap(true);
		alphaText.setEditable(true);
		
		JLabel lblPublicKey = new JLabel("Public Key");
		lblPublicKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPublicKey.setBounds(385, 4, 69, 22);
		elGamal_Panel.add(lblPublicKey);
		
		JLabel label_18 = new JLabel("Cipher Text");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_18.setBounds(12, 508, 76, 19);
		elGamal_Panel.add(label_18);
		
		JLabel label_19 = new JLabel("y1");
		label_19.setBounds(114, 510, 15, 16);
		elGamal_Panel.add(label_19);
		
		JLabel label_20 = new JLabel("y2");
		label_20.setBounds(114, 581, 15, 16);
		elGamal_Panel.add(label_20);
		
		JScrollPane scrollPane_21 = new JScrollPane();
		scrollPane_21.setBounds(141, 504, 176, 58);
		elGamal_Panel.add(scrollPane_21);
		
		cipherDecrypt_y1 = new JTextArea();
		scrollPane_21.setViewportView(cipherDecrypt_y1);
		cipherDecrypt_y1.setEditable(true);
		cipherDecrypt_y1.setLineWrap(true);
		
		JScrollPane scrollPane_22 = new JScrollPane();
		scrollPane_22.setBounds(141, 575, 176, 58);
		elGamal_Panel.add(scrollPane_22);
		
		cipherDecrypt_y2 = new JTextArea();
		scrollPane_22.setViewportView(cipherDecrypt_y2);
		cipherDecrypt_y2.setEditable(true);
		cipherDecrypt_y2.setLineWrap(true);
		
		JLabel label_22 = new JLabel("Plain Text");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_22.setBounds(71, 245, 69, 19);
		elGamal_Panel.add(label_22);
		
		JScrollPane scrollPane_15 = new JScrollPane();
		scrollPane_15.setBounds(153, 242, 164, 119);
		elGamal_Panel.add(scrollPane_15);
		
		elg_plainText = new JTextArea();
		scrollPane_15.setViewportView(elg_plainText);
		elg_plainText.setLineWrap(true);
		elg_plainText.setEditable(true);
		
		JLabel label_23 = new JLabel("Cipher Text");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_23.setBounds(425, 237, 76, 19);
		elGamal_Panel.add(label_23);
		
		JLabel label_24 = new JLabel("y1");
		label_24.setBounds(527, 239, 15, 16);
		elGamal_Panel.add(label_24);
		
		JScrollPane scrollPane_16 = new JScrollPane();
		scrollPane_16.setBounds(555, 234, 174, 56);
		elGamal_Panel.add(scrollPane_16);
		
		cipherEncrypt_y1 = new JTextArea();
		scrollPane_16.setViewportView(cipherEncrypt_y1);
		cipherEncrypt_y1.setEditable(false);
		cipherEncrypt_y1.setLineWrap(true);
		
		JLabel label_25 = new JLabel("y2");
		label_25.setBounds(527, 310, 15, 16);
		elGamal_Panel.add(label_25);
		
		JScrollPane scrollPane_17 = new JScrollPane();
		scrollPane_17.setBounds(555, 305, 174, 56);
		elGamal_Panel.add(scrollPane_17);
		
		cipherEncrypt_y2 = new JTextArea();
		scrollPane_17.setViewportView(cipherEncrypt_y2);
		cipherEncrypt_y2.setEditable(false);
		cipherEncrypt_y2.setLineWrap(true);
		
		JLabel lblTimeRequireds_2 = new JLabel("Time Required(\u03BCs)");
		lblTimeRequireds_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequireds_2.setBounds(474, 380, 122, 19);
		elGamal_Panel.add(lblTimeRequireds_2);
		
		final JLabel label_45 = new JLabel("0000");
		label_45.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_45.setBounds(599, 380, 69, 19);
		elGamal_Panel.add(label_45);
		
		JButton button_4 = new JButton("Encrypt");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = elg_plainText.getText();
					String alpha = alphaText.getText();
					String beta = betaText.getText();
					String p = primeText.getText();
					
					final long startTime = System.nanoTime();
					String[][] encryptedData = ElGamal.encryptData(message, alpha, beta, p);
					StringBuffer y1Buffer = new StringBuffer();
					for(String y1 : encryptedData[0]) {
						y1Buffer.append(y1 + " ");
					}
					
					StringBuffer y2Buffer = new StringBuffer();
					for(String y2 : encryptedData[1]) {
						y2Buffer.append(y2 + " ");
					}
					
					cipherEncrypt_y1.setText(y1Buffer.toString());
					cipherEncrypt_y2.setText(y2Buffer.toString());
					final long duration = System.nanoTime() - startTime;
					label_45.setText(Long.toString(duration / 1000));
				} catch (Exception e1) {
					String message = "Please enter only numeric text";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_4.setBounds(332, 378, 89, 23);
		elGamal_Panel.add(button_4);
		
		JScrollPane scrollPane_23 = new JScrollPane();
		scrollPane_23.setBounds(372, 437, 141, 55);
		elGamal_Panel.add(scrollPane_23);
		
		privateKeyA = new JTextArea();
		scrollPane_23.setViewportView(privateKeyA);
		
		JPanel AES_Panel = new JPanel();
		tabbedPane.addTab("AES", null, AES_Panel, null);
		AES_Panel.setLayout(null);
		
		JLabel label_27 = new JLabel("Encryption");
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_27.setBounds(92, 53, 73, 17);
		AES_Panel.add(label_27);
		
		JLabel label_32 = new JLabel("Bit Length");
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_32.setBounds(92, 98, 73, 17);
		AES_Panel.add(label_32);
		
		JLabel label_33 = new JLabel("128");
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_33.setBounds(193, 99, 46, 14);
		AES_Panel.add(label_33);
		
		JLabel label_34 = new JLabel("Private Key");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_34.setBounds(387, 98, 73, 17);
		AES_Panel.add(label_34);
		
		privateKey = new JTextField();
		privateKey.setColumns(10);
		privateKey.setBounds(528, 99, 95, 17);
		AES_Panel.add(privateKey);
		
		JLabel label_35 = new JLabel("Plain Text");
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_35.setBounds(92, 173, 67, 17);
		AES_Panel.add(label_35);
		
		JLabel label_36 = new JLabel("Cipher Text");
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_36.setBounds(387, 173, 73, 17);
		AES_Panel.add(label_36);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(529, 174, 153, 104);
		AES_Panel.add(scrollPane_11);
		
		cipherText = new JTextArea();
		scrollPane_11.setViewportView(cipherText);
		cipherText.setWrapStyleWord(true);
		cipherText.setLineWrap(true);
		cipherText.setColumns(2);
		
		JLabel lblTimeRequireds_4 = new JLabel("Time Required(\u03BCs)");
		lblTimeRequireds_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequireds_4.setBounds(508, 301, 115, 17);
		AES_Panel.add(lblTimeRequireds_4);
		
		final JLabel label_38 = new JLabel("0000");
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_38.setBounds(636, 301, 66, 17);
		AES_Panel.add(label_38);
		
		JButton button_8 = new JButton("Encrypt");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final long startTime = System.nanoTime();
				byte[] enc = AES_Algorithm.encrypt(plainText.getText().getBytes(), privateKey.getText().getBytes());
				final long duration = System.nanoTime() - startTime;
				label_38.setText(Long.toString(duration / 1000));
				cipherText.setText(new String (enc));
			}
		});
		button_8.setBounds(387, 300, 89, 23);
		AES_Panel.add(button_8);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(92, 333, 603, 2);
		AES_Panel.add(separator_6);
		
		JLabel label_39 = new JLabel("Decryption");
		label_39.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_39.setBounds(92, 352, 73, 17);
		AES_Panel.add(label_39);
		
		JLabel label_40 = new JLabel("Private Key");
		label_40.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_40.setBounds(92, 407, 73, 17);
		AES_Panel.add(label_40);
		
		decryptPrivateKey = new JTextField();
		decryptPrivateKey.setColumns(10);
		decryptPrivateKey.setBounds(187, 408, 95, 17);
		AES_Panel.add(decryptPrivateKey);
		
		JLabel label_41 = new JLabel("Cipher Text");
		label_41.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_41.setBounds(92, 454, 73, 17);
		AES_Panel.add(label_41);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(188, 454, 153, 104);
		AES_Panel.add(scrollPane_12);
		
		JTextArea textArea_16 = new JTextArea();
		scrollPane_12.setViewportView(textArea_16);
		textArea_16.setWrapStyleWord(true);
		textArea_16.setLineWrap(true);
		textArea_16.setColumns(2);
		
		JLabel label_42 = new JLabel("Plain Text");
		label_42.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_42.setBounds(387, 454, 73, 17);
		AES_Panel.add(label_42);
		
		JScrollPane scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(529, 455, 153, 104);
		AES_Panel.add(scrollPane_13);
		
		decryptedText = new JTextArea();
		scrollPane_13.setViewportView(decryptedText);
		decryptedText.setWrapStyleWord(true);
		decryptedText.setLineWrap(true);
		decryptedText.setEditable(false);
		decryptedText.setColumns(2);
		
		JLabel lblTimeRequireds_5 = new JLabel("Time Required(\u03BCs)");
		lblTimeRequireds_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequireds_5.setBounds(508, 582, 115, 17);
		AES_Panel.add(lblTimeRequireds_5);
		
		final JLabel label_44 = new JLabel("0000");
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_44.setBounds(636, 582, 66, 17);
		AES_Panel.add(label_44);
	
		JButton button_9 = new JButton("Decrypt");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final long startTime = System.nanoTime();
				byte[] enc = AES_Algorithm.encrypt(plainText.getText().getBytes(), privateKey.getText().getBytes());
				String op = new String(AES_Algorithm.decrypt(enc, decryptPrivateKey.getText().getBytes()));
				final long duration = System.nanoTime() - startTime;
				label_44.setText(Long.toString(duration / 1000));
				decryptedText.setText(op);
			}
		});
		button_9.setBounds(387, 581, 89, 23);
		AES_Panel.add(button_9);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(193, 173, 153, 104);
		AES_Panel.add(scrollPane_10);
		
		plainText = new JTextArea();
		scrollPane_10.setViewportView(plainText);
		plainText.setWrapStyleWord(true);
		plainText.setLineWrap(true);
		plainText.setColumns(2);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(92, 71, 66, 2);
		AES_Panel.add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(92, 371, 66, 2);
		AES_Panel.add(separator_8);
	}
}
