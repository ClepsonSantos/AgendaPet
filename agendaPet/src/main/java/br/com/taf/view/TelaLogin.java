package br.com.taf.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.taf.model.Usuario;
import br.com.taf.repository.UsuarioDAO;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField tfSenha;
	private JLabel lbMensagem;
	private static String nomeUsuario;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		
		// Desabilitar O Maximizarr
		setResizable(false);

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 349, 231);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Coloca um icone no canto superior esquerdo da tela
		URL url = this.getClass().getResource("/br/com/taf/img/logo.png");
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(imagemTitulo);

		tfLogin = new JTextField();
		tfLogin.setBounds(121, 33, 135, 20);
		contentPane.add(tfLogin);
		tfLogin.setColumns(10);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setBounds(74, 36, 46, 14);
		contentPane.add(lblLogin);
		lblLogin.setFont(new Font("Aero Matics Light", Font.BOLD, 15));

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setBounds(70, 74, 46, 14);
		contentPane.add(lblSenha);

		tfSenha = new JPasswordField();
		tfSenha.setBounds(121, 71, 135, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);

		JButton btnLogar = new JButton("Logar");
		btnLogar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/login.png")));
		btnLogar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnLogar.setForeground(Color.BLACK);
		btnLogar.setBounds(74, 116, 89, 26);
		contentPane.add(btnLogar);
		btnLogar.setBackground(Color.WHITE);
		btnLogar.setBorder(null);
		btnLogar.setFocusable(false);

		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(10, 162, 298, 16);
		contentPane.add(lbMensagem);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/back.png")));
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(175, 115, 90, 28);
		contentPane.add(btnVoltar);

		JLabel lImagem = new JLabel("");
		lImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundoLogin.png")));
		lImagem.setBounds(-17, -24, 360, 226);
		contentPane.add(lImagem);
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfLogin.getText().isEmpty() && !tfSenha.getText().isEmpty()) {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);

					UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();
					Usuario usuario = null;
					
					// Aqui verifica se a senha e o login existem no banco
					try {
						usuario = usuarioDAO.buscaLogin(tfLogin.getText().toString(), tfSenha.getText().toString());
						nomeUsuario = usuario.getNome();
						
						// A variavel de instancia teste da classe TelaPrincipal vai receber o nome do usuario que
						// ira logar, para mostra-lá na tela principal
						TelaPrincipal ti = new TelaPrincipal();
						ti.teste = nomeUsuario;
						
					} catch (Exception e) {
						System.out.println("Erro " + e.getMessage());
						e.printStackTrace();
					}
					
					// Se a senha e o login existem no banco, entra na tela pricipal
					if (usuario != null) {
						lbMensagem.setText("");
						lbMensagem.setIcon(null);
						
						if (tfLogin.getText().toString().equalsIgnoreCase("petaf")
								&& tfSenha.getText().toString().equalsIgnoreCase("petaf")) {
							TelaInicial telaInicial = new TelaInicial();
							telaInicial.setVisible(false);
							TelaPrincipalAdmin telaPrincipalAdmin = new TelaPrincipalAdmin();
							telaPrincipalAdmin.setVisible(true);
							dispose();
						} else {
							TelaInicial telaInicial = new TelaInicial();
							telaInicial.setVisible(false);
							TelaPrincipal telaPrincipal = new TelaPrincipal();
							telaPrincipal.setVisible(true);
							dispose();
						}
					} else {
						lbMensagem.setText("Erro! Senha ou Login errado");
						lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
					}
				} else {
					lbMensagem.setText("Algum Campo Vazio ");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}
}
