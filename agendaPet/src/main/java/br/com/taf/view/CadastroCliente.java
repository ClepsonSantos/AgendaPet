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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.taf.controller.ClienteController;
import br.com.taf.model.Cliente;;

public class CadastroCliente extends JDialog {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JFormattedTextField tfCPF;
	private JFormattedTextField tfRG;
	private JFormattedTextField tfCelular;
	private JFormattedTextField tfTelefone;
	private JLabel lbMensagem;
	private Cliente cliente;
	
	private ClienteController clienteController = new ClienteController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCliente frame = new CadastroCliente();
					frame.setVisible(true);
					// Desabilitar O Maximizar
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public void initComponent() {

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 286);
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

		// Adiciona Uma Mensagem de Erro
		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBackground(Color.RED);
		lbMensagem.setBounds(12, 236, 378, 14);
		panel.add(lbMensagem);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblNome.setBounds(60, 15, 46, 14);
		panel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(105, 12, 282, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblCpf.setBounds(70, 49, 55, 16);
		panel.add(lblCpf);

		// Adicionando Mascara ao CPF
		MaskFormatter mascaraCPF = null;
		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
		}
		tfCPF = new JFormattedTextField(mascaraCPF);
		tfCPF.setBounds(105, 45, 170, 20);
		panel.add(tfCPF);

		JLabel lblRg = new JLabel("RG");
		lblRg.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblRg.setBounds(76, 82, 55, 16);
		panel.add(lblRg);

		// Adicionando Mascara ao RG
		MaskFormatter mascaraRG = null;
		try {
			mascaraRG = new MaskFormatter("#############");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
		}
		tfRG = new JFormattedTextField(mascaraRG);
		tfRG.setBounds(105, 77, 170, 20);
		panel.add(tfRG);

		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblEndereo.setBounds(34, 111, 76, 16);
		panel.add(lblEndereo);

		tfEndereco = new JTextField();
		tfEndereco.setBounds(105, 108, 280, 20);
		panel.add(tfEndereco);
		tfEndereco.setColumns(10);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblCelular.setBounds(56, 140, 55, 16);
		panel.add(lblCelular);

		MaskFormatter mascaraCelular = null;
		try {
			mascaraCelular = new MaskFormatter("(##)# ####-####");
		} catch (Exception e) {
			e.getMessage();
		}
		tfCelular = new JFormattedTextField(mascaraCelular);
		tfCelular.setBounds(105, 138, 170, 20);
		panel.add(tfCelular);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblTelefone.setBounds(39, 169, 69, 16);
		panel.add(lblTelefone);

		MaskFormatter mascaraTelefone = null;
		try {
			mascaraTelefone = new MaskFormatter("(##) ####-####");
		} catch (Exception e) {
			e.getMessage();
		}
		tfTelefone = new JFormattedTextField(mascaraTelefone);
		tfTelefone.setBounds(105, 167, 170, 20);
		panel.add(tfTelefone);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/salvar.png")));
		btnSalvar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBorder(null);
		btnSalvar.setFocusable(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfNome.getText().isEmpty()) {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);
					
					
					clienteController.getCliente().setNome(tfNome.getText().toString());
					clienteController.getCliente().setCpf(tfCPF.getText().toString());
					clienteController.getCliente().setRg(tfRG.getText().toString());
					clienteController.getCliente().setEndereco(tfEndereco.getText().toString());
					clienteController.getCliente().setCelular(tfCelular.getText().toString());
					clienteController.getCliente().setTelefone(tfTelefone.getText().toString());
					
					if (clienteController.salvar(clienteController.getCliente())) {
						ListaCliente listaCliente = new ListaCliente();
						listaCliente.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao Salvar", "Falha", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					lbMensagem.setText("Algum Campo Vazio ");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});
		btnSalvar.setBounds(105, 194, 89, 23);
		panel.add(btnSalvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/back.png")));
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaCliente listaCliente = new ListaCliente();
				listaCliente.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(201, 194, 89, 23);
		panel.add(btnVoltar);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBounds(0, 0, 434, 261);
		panel.add(lbImagem);
	}

	public CadastroCliente() {
		initComponent();
		clienteController.getCliente();
		setTitle("Cadastro Cliente");
	}

	public CadastroCliente(Cliente cliente) {
		initComponent();
		clienteController.setCliente(cliente);
		tfNome.setText(cliente.getNome());
		tfCPF.setText(cliente.getCpf());
		tfRG.setText(cliente.getRg());
		tfEndereco.setText(cliente.getEndereco());
		tfCelular.setText(cliente.getCelular());
		tfTelefone.setText(cliente.getTelefone());
	}
}
