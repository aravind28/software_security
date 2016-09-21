import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JTextArea;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;


public class AES {

	private JFrame frmAes;
	private JTextField privateKey;
	private JTextField decryptPrivateKey;
	private JTextField cipherText2;
	private JTextArea plainText;
	private JTextArea cipherText;
	private JTextArea decryptedText;
	private JTextArea decryptcipherText;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AES window = new AES();
					window.frmAes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public AES() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAes = new JFrame();
		frmAes.setResizable(false);
		frmAes.setTitle("AES");
		frmAes.setBounds(100, 100, 644, 616);
		frmAes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAes.getContentPane().setLayout(null);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel lblNewLabel = new JLabel("Encryption");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 73, 17);
		frmAes.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(11, 32, 66, 2);
		frmAes.getContentPane().add(separator);
		
		JLabel lblBitLength = new JLabel("Bit Length");
		lblBitLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBitLength.setBounds(10, 56, 73, 17);
		frmAes.getContentPane().add(lblBitLength);
		
		JLabel lblNewLabel_1 = new JLabel("128");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(111, 57, 46, 14);
		frmAes.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Private Key");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(305, 56, 73, 17);
		frmAes.getContentPane().add(lblNewLabel_2);
		
		privateKey = new JTextField();
		privateKey.setBounds(446, 57, 95, 17);
		frmAes.getContentPane().add(privateKey);
		privateKey.setColumns(10);
		privateKey.setBorder(border);
		
		JLabel lblNewLabel_3 = new JLabel("Plain Text");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 131, 67, 17);
		frmAes.getContentPane().add(lblNewLabel_3);
		
		plainText = new JTextArea(5, 1);
		plainText.setColumns(2);
		plainText.setLineWrap(true);
		plainText.setWrapStyleWord(true);
		JScrollPane scr = new JScrollPane(plainText);
		scr.setBounds(105, 130, 155, 106);
		frmAes.getContentPane().add(scr);
		scr.setBorder(border);
        //scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//plainText.setWrapStyleWord(true);
		//plainText.setColumns(2);
		//plainText.setBounds(105, 130, 155, 106);
		//frmAes.getContentPane().add(plainText);
		//plainText.setLineWrap(true);
		//plainText.setBorder(border);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(105, 130, 155, 106);
		frmAes.getContentPane().add(scrollPane);
		
		JLabel lblEncryptedText = new JLabel("Cipher Text");
		lblEncryptedText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEncryptedText.setBounds(305, 131, 73, 17);
		frmAes.getContentPane().add(lblEncryptedText);
		
		cipherText = new JTextArea();
		cipherText.setColumns(2);
		cipherText.setLineWrap(true);
		cipherText.setWrapStyleWord(true);
		JScrollPane cipherSrc = new JScrollPane(cipherText);
		cipherSrc.setBounds(446, 131, 155, 106);
		frmAes.getContentPane().add(cipherSrc);
		cipherSrc.setBorder(border);
//		cipherText.setEditable(false);
//		cipherText.setBounds(446, 131, 155, 106);
//		frmAes.getContentPane().add(cipherText);
//		cipherText.setLineWrap(true);
//		cipherText.setBorder(border);
		
		JLabel lblTimeRequired = new JLabel("Time Required");
		lblTimeRequired.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeRequired.setBounds(452, 259, 89, 17);
		frmAes.getContentPane().add(lblTimeRequired);
		
		JLabel label = new JLabel("00:00:00");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(554, 259, 66, 17);
		frmAes.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Encrypt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 final long startTime = System.nanoTime();
					byte[] enc = AES_Algorithm.encrypt(plainText.getText().getBytes(), privateKey.getText().getBytes());
					cipherText.setText(new String (enc));
					final long duration = System.nanoTime() - startTime;
					//label.setText(Long.toString(duration));
			}
		});
		btnNewButton.setBounds(305, 258, 89, 23);
		frmAes.getContentPane().add(btnNewButton);
		
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 291, 603, 2);
		frmAes.getContentPane().add(separator_1);
		
		JLabel lblDecryption = new JLabel("Decryption");
		lblDecryption.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDecryption.setBounds(10, 310, 73, 17);
		frmAes.getContentPane().add(lblDecryption);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(11, 331, 66, 2);
		frmAes.getContentPane().add(separator_2);
		
		JLabel label_1 = new JLabel("Private Key");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(10, 365, 73, 17);
		frmAes.getContentPane().add(label_1);
		
		decryptPrivateKey = new JTextField();
		decryptPrivateKey.setColumns(10);
		decryptPrivateKey.setBounds(105, 366, 95, 17);
		frmAes.getContentPane().add(decryptPrivateKey);
		decryptPrivateKey.setBorder(border);
		
		JLabel lblCipherText = new JLabel("Cipher Text");
		lblCipherText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCipherText.setBounds(10, 412, 73, 17);
		frmAes.getContentPane().add(lblCipherText);
		
		decryptcipherText = new JTextArea();
		decryptcipherText.setColumns(2);
		decryptcipherText.setLineWrap(true);
		decryptcipherText.setWrapStyleWord(true);
		JScrollPane decryptcipherSrc = new JScrollPane(decryptcipherText);
		decryptcipherSrc.setBounds(105, 411, 155, 106);
		frmAes.getContentPane().add(decryptcipherSrc);
		decryptcipherSrc.setBorder(border);
		//decryptcipherText.setBounds(105, 411, 155, 106);
		//frmAes.getContentPane().add(decryptcipherText);
		//decryptcipherText.setLineWrap(true);
		//decryptcipherText.setBorder(border);
		
		JLabel lblPlainText = new JLabel("Plain Text");
		lblPlainText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlainText.setBounds(305, 412, 73, 17);
		frmAes.getContentPane().add(lblPlainText);
		
//		JTextArea cipherText = new JTextArea();
//		cipherText.setColumns(2);
//		cipherText.setLineWrap(true);
//		cipherText.setWrapStyleWord(true);
//		cipherText.setEditable(false);
//		JScrollPane cipherSrc = new JScrollPane(cipherText);
//		cipherSrc.setBounds(446, 131, 155, 106);
//		frmAes.add(cipherSrc);
//		cipherSrc.setBorder(border);
		
		decryptedText = new JTextArea();
		decryptedText.setColumns(2);
		decryptedText.setLineWrap(true);
		decryptedText.setWrapStyleWord(true);
		decryptedText.setEditable(false);
		JScrollPane decryptSrc = new JScrollPane(decryptedText);
		decryptSrc.setBounds(446, 412, 155, 106);
		frmAes.getContentPane().add(decryptSrc);
		decryptSrc.setBorder(border);
//		decryptedText.setEditable(false);
//		decryptedText.setBounds(446, 412, 155, 106);
//		frmAes.getContentPane().add(decryptedText);
//		decryptedText.setLineWrap(true);
//		decryptedText.setBorder(border);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				byte[] enc = AES_Algorithm.encrypt(plainText.getText().getBytes(), privateKey.getText().getBytes());
				String op = new String(AES_Algorithm.decrypt(enc, decryptPrivateKey.getText().getBytes()));
				decryptedText.setText(op);
			}
		});
		btnDecrypt.setBounds(305, 539, 89, 23);
		frmAes.getContentPane().add(btnDecrypt);
		
		JLabel label_4 = new JLabel("Time Required");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(452, 540, 89, 17);
		frmAes.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("00:00:00");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(554, 540, 66, 17);
		frmAes.getContentPane().add(label_5);
		
	}

}
