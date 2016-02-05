package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import servidor.Servidor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class register {
	Servidor serv;

	private JFrame frame;
	private JTextField textField_user2;
	private JTextField textField_pass2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
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
	public register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField_user2 = new JTextField();
		textField_user2.setColumns(10);
		
		textField_pass2 = new JTextField();
		textField_pass2.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario");
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		
		JButton btnResgistro2 = new JButton("resgistro");
		btnResgistro2.addActionListener(new ActionListener() {
			 public void mouseClicked(MouseEvent e) {
			 try {
				serv.login(textField_user2.getText(), textField_pass2.getText());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 }
			    
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUsuario)
						.addComponent(lblContrasea))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_pass2)
						.addComponent(textField_user2, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
					.addContainerGap(149, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(116)
					.addComponent(btnResgistro2)
					.addContainerGap(243, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_user2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_pass2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContrasea))
					.addGap(70)
					.addComponent(btnResgistro2)
					.addContainerGap(70, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
