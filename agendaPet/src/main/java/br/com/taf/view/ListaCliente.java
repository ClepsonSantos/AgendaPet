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
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.com.taf.controller.ClienteController;
import br.com.taf.model.Cliente;
import br.com.taf.model.ClienteModel;
import br.com.taf.repository.ClienteDAO;

public class ListaCliente extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JLabel lbMensagem;
	private JTextField tfNomePesquisa;
	
	private ClienteController clienteController = new ClienteController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaCliente frame = new ListaCliente();
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
	public ListaCliente() {
		
		// Desabilitar O Maximizarr
		setResizable(false);

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setTitle("Lista de Clientes");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Centraliza a tela
		setLocationRelativeTo(null);

		// Coloca um icone no canto superior esquerdo da tela
		URL url = this.getClass().getResource("/br/com/taf/img/logo.png");
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(imagemTitulo);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 11, 414, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(96, 228, 289, 16);
		contentPane.add(lbMensagem);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbMensagem.setText("");
				lbMensagem.setIcon(null);
				CadastroCliente cadastroCliente = new CadastroCliente();
				cadastroCliente.setVisible(true);
				dispose();
			}
		});
		btnAdicionar.setBackground(Color.WHITE);
		btnAdicionar.setBorder(null);
		btnAdicionar.setFocusable(false);
		btnAdicionar.setBounds(10, 11, 77, 36);
		panel.add(btnAdicionar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnEditar.setBackground(Color.WHITE);
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
					ClienteModel cm = (ClienteModel) table.getModel();
					Cliente cliente = cm.getValueAt(i);
					CadastroCliente tcc = new CadastroCliente(cliente);
					tcc.setVisible(true);

					preencheTabelaCliente(clienteController.lista());
					
					dispose();
				}
			}

		});
		btnEditar.setBounds(97, 11, 77, 36);
		panel.add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBorder(null);
		btnExcluir.setFocusable(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				if (i >= 0) {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);
					if (JOptionPane.showConfirmDialog(null, "Deseja excluir?", "",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						ClienteModel cm = (ClienteModel) table.getModel();
						Cliente cliente = cm.getValueAt(i);
						
						clienteController.excluir(cliente);
						
						preencheTabelaCliente(clienteController.lista());
						JOptionPane.showMessageDialog(null, "Cliente Excluído");
					}
				} else {
					lbMensagem.setText("Selecione uma linha para Excluir");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/petaf/img/error.png")));
				}
			}
		});
		btnExcluir.setBounds(184, 11, 77, 36);
		panel.add(btnExcluir);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBorder(null);
		btnRefresh.setFocusable(false);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencheTabelaCliente(clienteController.lista());			
			}
		});
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/refresh.png")));
		btnRefresh.setBounds(307, 11, 101, 36);
		panel.add(btnRefresh);

		table = new JTable();
		table.setBounds(10, 83, 414, 133);
		table.setBackground(Color.WHITE);
		contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 105, 414, 101);
		contentPane.add(scrollPane);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/back.png")));
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setBounds(10, 217, 77, 36);
		contentPane.add(btnVoltar);
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBackground(Color.WHITE);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 80, 43, 14);
		contentPane.add(lblNome);
		
		tfNomePesquisa = new JTextField();
		tfNomePesquisa.setBounds(57, 76, 143, 20);
		contentPane.add(tfNomePesquisa);
		tfNomePesquisa.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Dialog", Font.BOLD, 11));
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tfNomePesquisa.getText().isEmpty()){
					ClienteDAO dao = new ClienteDAO();
					List<Cliente> listaPorNome = dao.listarPorNome(tfNomePesquisa.getText().toString());
					preencheTabelaCliente(listaPorNome);
				} else {
					preencheTabelaCliente(clienteController.lista());
					
					lbMensagem.setText("Digite um nome pra pesquisar!");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});
		btnPesquisar.setBounds(208, 75, 107, 23);
		contentPane.add(btnPesquisar);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBackground(Color.WHITE);
		lbImagem.setBounds(0, -1, 434, 262);
		contentPane.add(lbImagem);

		
		preencheTabelaCliente(clienteController.lista());
	}

	public void preencheTabelaCliente(List<Cliente> clientes) {
		lbMensagem.setText("");
		lbMensagem.setIcon(null);
		ClienteModel cm = new ClienteModel(clientes);
		table.setModel(cm);
	}
}
