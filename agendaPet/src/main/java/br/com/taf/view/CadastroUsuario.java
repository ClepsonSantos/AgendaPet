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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.taf.controller.UsuarioController;
import br.com.taf.model.Usuario;
import br.com.taf.repository.UsuarioDAO;

public class CadastroUsuario extends JDialog {

	private JPanel contentPane;
	private JTextField tfLogin;
	private JPasswordField tfSenha;
	private JLabel lbMensagem;
	private JTextField tfNome;
	
	private UsuarioController usuarioController = new UsuarioController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setVisible(true);
					// Desabilitar O Maximizar
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initComponents() {

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Coloca um icone no canto superior esquerdo da tela
		URL url = this.getClass().getResource("/br/com/taf/img/logo.png");
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(imagemTitulo);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblLogin.setBounds(116, 85, 46, 14);
		panel.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblSenha.setBounds(111, 115, 46, 14);
		panel.add(lblSenha);

		tfLogin = new JTextField();
		tfLogin.setBounds(166, 82, 131, 20);
		panel.add(tfLogin);
		tfLogin.setColumns(10);

		tfSenha = new JPasswordField();
		tfSenha.setBounds(166, 112, 131, 20);
		panel.add(tfSenha);
		tfSenha.setColumns(10);

		lbMensagem = new JLabel("");

		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(7, 234, 360, 20);
		panel.add(lbMensagem);

		JButton btnSalvar = new JButton("Cadastrar");
		btnSalvar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/cadastro.png")));
		btnSalvar.setBorder(null);
		btnSalvar.setFocusable(false);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfLogin.getText().isEmpty() && !tfSenha.getText().isEmpty()) {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);

					usuarioController.getUsuario().setLogin(tfLogin.getText().toString());
					usuarioController.getUsuario().setSenha(tfSenha.getText().toString());
					usuarioController.getUsuario().setNome(tfNome.getText().toString());

					UsuarioDAO usuarioDAO = new UsuarioDAO();
					// Verifica se o usuario já foi cadastrado
					if (usuarioDAO.existeUsuarioSemelhante(usuarioController.getUsuario())) {
						lbMensagem.setText("Usuario já existe");
						lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
					} else {
						lbMensagem.setText("");
						lbMensagem.setIcon(null);
						if (usuarioController.salvar(usuarioController.getUsuario())) {
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Não Salvo", "Erro", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} else {
					lbMensagem.setText("Campo Login/Senha vazio");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});
		btnSalvar.setBounds(113, 150, 97, 28);
		panel.add(btnSalvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/voltar.png")));
		btnVoltar.setBounds(217, 150, 96, 28);
		panel.add(btnVoltar);
		
		tfNome = new JTextField();
		tfNome.setBounds(166, 51, 131, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblNome.setBounds(113, 54, 51, 14);
		panel.add(lblNome);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBounds(0, 0, 434, 261);
		panel.add(lbImagem);
	}

	public CadastroUsuario() {
		setModal(true);
		setTitle("Cadastro Usuario");
		initComponents();
		usuarioController.getUsuario();
	}

	public CadastroUsuario(Usuario usuario) {
		initComponents();
		usuarioController.setUsuario(usuario);
		tfLogin.setText(usuario.getLogin());
		tfSenha.setText(usuario.getSenha());
		tfNome.setText(usuario.getNome());
	}
}

