import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;


public class CryptoToolBox1 {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CryptoToolBox1 window = new CryptoToolBox1();
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
	public CryptoToolBox1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 13));
		frame.setBounds(100, 100, 851, 752);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		bitLength = new JTextField();
		
		bitLength.setBounds(88, 24, 116, 22);
		frame.getContentPane().add(bitLength);
		bitLength.setColumns(10);
		
		JLabel lblP = new JLabel("Bit length =");
		lblP.setBounds(12, 27, 76, 16);
		frame.getContentPane().add(lblP);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 21, 156, 132);
		frame.getContentPane().add(scrollPane);
		
		pText = new JTextArea();
		scrollPane.setViewportView(pText);
		pText.setEditable(false);
		pText.setLineWrap(true);
		
		JLabel lblP_1 = new JLabel("P =");
		lblP_1.setBounds(240, 81, 30, 16);
		frame.getContentPane().add(lblP_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(562, 21, 156, 132);
		frame.getContentPane().add(scrollPane_1);
		
		qText = new JTextArea();
		scrollPane_1.setViewportView(qText);
		qText.setEditable(false);
		qText.setLineWrap(true);
		
		JLabel lblQ = new JLabel("Q =");
		lblQ.setBounds(520, 81, 30, 16);
		frame.getContentPane().add(lblQ);
		
		JButton generatePrimes = new JButton("Generate Primes");
		generatePrimes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		generatePrimes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int bitLengthInt = Integer.parseInt(bitLength.getText());
					if(bitLengthInt > 1024 || bitLengthInt < 1) {
						String message = "Please enter a number between 1 and 1024";
						JOptionPane.showMessageDialog(null, message);
					}
					
					String[] primes = RSA.generatePrimes(bitLengthInt);
					
					pText.setText(primes[0].toString());
					qText.setText(primes[1].toString());
				} catch (NumberFormatException e1) {
					String message = "Enter only numeric text";
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		generatePrimes.setBounds(12, 77, 141, 25);
		frame.getContentPane().add(generatePrimes);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(28, 224, 154, 130);
		frame.getContentPane().add(scrollPane_2);
		
		eText = new JTextArea();
		scrollPane_2.setViewportView(eText);
		eText.setLineWrap(true);
		eText.setEditable(true);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(210, 224, 154, 130);
		frame.getContentPane().add(scrollPane_3);
		
		nText1 = new JTextArea();
		scrollPane_3.setViewportView(nText1);
		nText1.setLineWrap(true);
		nText1.setEditable(true);
		
		JLabel lblE = new JLabel("e =");
		lblE.setBounds(28, 201, 30, 16);
		frame.getContentPane().add(lblE);
		
		JLabel lblN = new JLabel("N =");
		lblN.setBounds(210, 201, 30, 16);
		frame.getContentPane().add(lblN);
		
		JButton generateKeys = new JButton("Generate Keys From P and Q");
		generateKeys.addActionListener(new ActionListener() {
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
		
		
		generateKeys.setBounds(12, 117, 197, 25);
		frame.getContentPane().add(generateKeys);
		
		JLabel lblPublicKey = new JLabel("Public Key");
		lblPublicKey.setBounds(28, 185, 91, 16);
		frame.getContentPane().add(lblPublicKey);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(28, 382, 336, 111);
		frame.getContentPane().add(scrollPane_4);
		
		plainTextEncrypt = new JTextArea();
		scrollPane_4.setViewportView(plainTextEncrypt);
		plainTextEncrypt.setLineWrap(true);
		plainTextEncrypt.setEditable(true);
		
		JButton btnNewButton_1 = new JButton("Encrypt Plaintext");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				
				String plainText = plainTextEncrypt.getText();
				String eStr = eText.getText();
				String NStr = nText1.getText();
				
				String encryptedData = RSA.encryptMessage(plainText, eStr, NStr);
				cipherTextEncrypt.setText(encryptedData);
			}
		});
		
		btnNewButton_1.setBounds(28, 647, 141, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblPlaintText = new JLabel("Plaintext");
		lblPlaintText.setBounds(28, 365, 76, 16);
		frame.getContentPane().add(lblPlaintText);
		
		JLabel lblCipherText = new JLabel("Ciphertext");
		lblCipherText.setBounds(28, 506, 76, 16);
		frame.getContentPane().add(lblCipherText);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(28, 523, 336, 111);
		frame.getContentPane().add(scrollPane_5);
		
		cipherTextEncrypt = new JTextArea();
		scrollPane_5.setViewportView(cipherTextEncrypt);
		cipherTextEncrypt.setLineWrap(true);
		cipherTextEncrypt.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Encryption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 165, 395, 529);
		frame.getContentPane().add(panel);
		
		JLabel lblPrivateKey = new JLabel("Private Key");
		lblPrivateKey.setBounds(442, 185, 91, 16);
		frame.getContentPane().add(lblPrivateKey);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(442, 224, 154, 130);
		frame.getContentPane().add(scrollPane_6);
		
		dText = new JTextArea();
		scrollPane_6.setViewportView(dText);
		dText.setLineWrap(true);
		dText.setEditable(true);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(624, 224, 154, 130);
		frame.getContentPane().add(scrollPane_7);
		
		nText2 = new JTextArea();
		scrollPane_7.setViewportView(nText2);
		nText2.setLineWrap(true);
		nText2.setEditable(true);
		
		JLabel label_1 = new JLabel("N =");
		label_1.setBounds(624, 201, 30, 16);
		frame.getContentPane().add(label_1);
		
		JLabel lblD = new JLabel("d =");
		lblD.setBounds(442, 201, 30, 16);
		frame.getContentPane().add(lblD);
		
		JLabel lblCiphertext = new JLabel("Ciphertext");
		lblCiphertext.setBounds(442, 365, 76, 16);
		frame.getContentPane().add(lblCiphertext);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(442, 382, 336, 111);
		frame.getContentPane().add(scrollPane_8);
		
		cipherTextDecrypt = new JTextArea();
		scrollPane_8.setViewportView(cipherTextDecrypt);
		cipherTextDecrypt.setLineWrap(true);
		cipherTextDecrypt.setEditable(true);
		
		JLabel lblPlaintext = new JLabel("Plaintext");
		lblPlaintext.setBounds(442, 506, 76, 16);
		frame.getContentPane().add(lblPlaintext);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(442, 523, 336, 111);
		frame.getContentPane().add(scrollPane_9);
		
		plainTextDecrypt = new JTextArea();
		scrollPane_9.setViewportView(plainTextDecrypt);
		plainTextDecrypt.setLineWrap(true);
		plainTextDecrypt.setEditable(false);
		
		JButton btnDecryptCiphertext = new JButton("Decrypt Ciphertext");
		btnDecryptCiphertext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cipherText = cipherTextDecrypt.getText();
				String dStr = dText.getText();
				String NStr = nText2.getText();
				
				String decryptedData = RSA.decryptMessage(cipherText, dStr, NStr);
				plainTextDecrypt.setText(decryptedData);
			}
		});
		btnDecryptCiphertext.setBounds(442, 647, 141, 25);
		frame.getContentPane().add(btnDecryptCiphertext);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Decryption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(426, 165, 395, 529);
		frame.getContentPane().add(panel_1);
		
		
	}
}
