package br.com.taf.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.com.taf.controller.UsuarioController;
import br.com.taf.model.Usuario;
import br.com.taf.model.UsuarioModel;
import br.com.taf.repository.DAO;
import br.com.taf.repository.UsuarioDAO;

public class ListaUsuario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lbMensagem;
	
	private UsuarioController usuarioController = new UsuarioController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaUsuario frame = new ListaUsuario();
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
	public ListaUsuario() {
		
		// Desabilitar O Maximizarr
		setResizable(false);

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setTitle("Lista de Usuarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Coloca um icone no canto superior esquerdo da tela
		URL url = this.getClass().getResource("/br/com/taf/img/logo.png");
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(imagemTitulo);

		table = new JTable();
		table.setBounds(31, 29, 377, 169);
		contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 83, 414, 123);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 414, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(108, 229, 268, 16);
		contentPane.add(lbMensagem);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBorder(null);
		btnExcluir.setFocusable(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnExcluir.setBounds(168, 11, 73, 36);
		panel.add(btnExcluir);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBorder(null);
		btnAdicionar.setFocusable(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbMensagem.setText("");
				lbMensagem.setIcon(null);
				CadastroUsuario cadastroUsuario = new CadastroUsuario();
				cadastroUsuario.setVisible(true);
			}
		});
		btnAdicionar.setBackground(Color.WHITE);
		btnAdicionar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnAdicionar.setBounds(6, 11, 87, 36);
		panel.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBorder(null);
		btnEditar.setFocusable(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i < 0) {
					lbMensagem.setText("Selecione uma linha para Editar");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				} else {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);
					UsuarioModel cm = (UsuarioModel) table.getModel();
					Usuario usuario = cm.getValueAt(i);
					CadastroUsuario tcu = new CadastroUsuario(usuario);
					tcu.setVisible(true);

					preencheTabelaUsuario(usuarioController.lista());
				}
			}
		});
		btnEditar.setBackground(Color.WHITE);
		btnEditar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnEditar.setBounds(94, 12, 73, 35);
		panel.add(btnEditar);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBorder(null);
		btnRefresh.setFocusable(false);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencheTabelaUsuario(usuarioController.lista());
			}
		});
		btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/refresh.png")));
		btnRefresh.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(306, 12, 101, 34);
		panel.add(btnRefresh);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/back.png")));
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setBounds(6, 223, 90, 28);
		contentPane.add(btnVoltar);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBounds(0, 0, 434, 261);
		contentPane.add(lbImagem);

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);
					if (JOptionPane.showConfirmDialog(null, "Deseja excluir?", "",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						UsuarioModel cm = (UsuarioModel) table.getModel();
						Usuario usuario = cm.getValueAt(i);
						usuarioController.excluir(usuario);
						preencheTabelaUsuario(usuarioController.lista());
						JOptionPane.showMessageDialog(null, "Usuario Excluído");
					}
				} else {
					lbMensagem.setText("Selecione uma linha para Excluir");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});

		preencheTabelaUsuario(usuarioController.lista());
	}

	public void preencheTabelaUsuario(List<Usuario> usuarios) {
		lbMensagem.setText("");
		lbMensagem.setIcon(null);
		UsuarioModel cm = new UsuarioModel(usuarios);
		table.setModel(cm);
	}
}
