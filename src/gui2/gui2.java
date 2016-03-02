/**Glitch-Art
*
* @author Khalil Stemmler
* October 10th, 2015
* The gui2 class is not used in the execution of the project. This class was used as a reference to build the GUI for GuiAndListener.
*/


package gui2;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gui2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui2 window = new gui2();
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
	public gui2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton glitchButton = new JButton("Glitch");
		
		JTextPane glitchRate = new JTextPane();
		
		JLabel lblSpecifyGlitchRate = new JLabel("Specify glitch rate:");
		
		JLabel picDisplay = new JLabel("Picture");
		
		JButton undoButton = new JButton("Undo");
		
		JButton redoButton = new JButton("Redo");
		redoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel logo = new JLabel("New label");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(picDisplay)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblSpecifyGlitchRate)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(glitchRate, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
										.addComponent(undoButton))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(redoButton)
										.addComponent(glitchButton)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(81)
							.addComponent(logo)))
					.addContainerGap(158, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(logo)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(glitchButton)
						.addComponent(lblSpecifyGlitchRate)
						.addComponent(glitchRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addComponent(picDisplay)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(redoButton)
						.addComponent(undoButton))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
