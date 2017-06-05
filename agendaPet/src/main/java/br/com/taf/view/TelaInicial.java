package br.com.taf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

public class TelaInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		
		// Desabilitar O Maximizarr
		setResizable(false);

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Coloca um icone no canto superior esquerdo da tela
		URL url = this.getClass().getResource("/br/com/taf/img/logo.png");
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(imagemTitulo);

		JButton btnLogar = new JButton("Logar");
		btnLogar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/login.png")));
		btnLogar.setBackground(Color.WHITE);
		btnLogar.setBorder(null);
		btnLogar.setFocusable(false);
		btnLogar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLogin telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				dispose();
			}
		});
		btnLogar.setBounds(98, 193, 89, 23);
		contentPane.add(btnLogar);

		JButton btnCadastrese = new JButton("Cadastre-se");
		btnCadastrese.setBorder(null);
		btnCadastrese.setFocusable(false);
		btnCadastrese.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/cadastro.png")));
		btnCadastrese.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnCadastrese.setBackground(Color.WHITE);
		btnCadastrese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroUsuario cadastroUsuario = new CadastroUsuario();
				cadastroUsuario.setVisible(true);
			}
		});
		btnCadastrese.setBounds(226, 193, 140, 25);
		contentPane.add(btnCadastrese);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo.png")));
		lbImagem.setBounds(0, 0, 434, 261);
		contentPane.add(lbImagem);
	}
}
