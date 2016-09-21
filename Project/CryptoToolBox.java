import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;


public class CryptoToolBox {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CryptoToolBox window = new CryptoToolBox();
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
	public CryptoToolBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 823, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		textField = new JTextField();
		tabbedPane.addTab("New tab", null, textField, null);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		tabbedPane.addTab("New tab", null, textField_1, null);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		tabbedPane.addTab("New tab", null, textField_2, null);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		tabbedPane.addTab("New tab", null, textField_3, null);
		textField_3.setColumns(10);
	}

}
