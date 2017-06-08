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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.taf.controller.AnimalController;
import br.com.taf.model.Animal;
import br.com.taf.model.Cliente;
import br.com.taf.model.TipoAnimal;
import br.com.taf.repository.AnimalDAO;
import br.com.taf.repository.ClienteDAO;

public class CadastroAnimal extends JDialog {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfRaca;
	private JTextField tfIdade;
	private JComboBox cmbCliente;
	private JComboBox comboBox;
	private JLabel lbMensagem;

	private AnimalController  animalController = new AnimalController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAnimal frame = new CadastroAnimal();
					frame.setVisible(true);
					// Desabilitar O Maximizar
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initComponent() {

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
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setBounds(100, 129, 272, 20);
		panel.add(comboBox);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(64, 132, 46, 14);
		panel.add(lblTipo);

		try {
			TipoAnimal[] animals = TipoAnimal.values();
			for (TipoAnimal tipo : animals) {
//				String animal = tipo.toString();
				comboBox.addItem(tipo);
			}
		} catch (Exception e) {
			e.getMessage();
			JOptionPane.showMessageDialog(null, "Erro ao carregar tipo animal");
		}

		cmbCliente = new JComboBox();
		cmbCliente.setBounds(100, 160, 272, 20);
		panel.add(cmbCliente);

		JLabel lblDono = new JLabel("Dono");
		lblDono.setBounds(64, 163, 46, 14);
		panel.add(lblDono);

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			List<Cliente> clientes = clienteDAO.listar();
			for (Cliente cliente : clientes) {
				cmbCliente.addItem(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Sem Clientes Cadastrados");
		}

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(54, 30, 46, 14);
		panel.add(lblNome);

		tfNome = new JTextField();
		tfNome.setBounds(100, 27, 272, 20);
		panel.add(tfNome);
		tfNome.setColumns(10);

		tfRaca = new JTextField();
		tfRaca.setBounds(100, 58, 272, 20);
		panel.add(tfRaca);
		tfRaca.setColumns(10);

		JLabel lblRaa = new JLabel("Raça");
		lblRaa.setBounds(54, 61, 57, 14);
		panel.add(lblRaa);

		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(54, 98, 46, 14);
		panel.add(lblIdade);

		tfIdade = new JTextField();
		tfIdade.setBounds(100, 95, 272, 20);
		panel.add(tfIdade);
		tfIdade.setColumns(10);
		
		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(15, 233, 314, 16);
		panel.add(lbMensagem);
		
				JButton btnSalvar = new JButton("Salvar");
				btnSalvar.setBackground(Color.WHITE);
				btnSalvar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
				btnSalvar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/salvar.png")));
				btnSalvar.setBorder(null);
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!tfNome.getText().isEmpty()) {
							lbMensagem.setText("");
							lbMensagem.setIcon(null);
							
							animalController.getAnimal().setNome(tfNome.getText().toString());
							animalController.getAnimal().setRaca(tfRaca.getText().toString());
							animalController.getAnimal().setIdade(Integer.parseInt(tfIdade.getText()));
							//String ani = animal.getTipo().toString();
							animalController.getAnimal().setTipo((TipoAnimal) comboBox.getSelectedItem());
							animalController.getAnimal().setCliente((Cliente) cmbCliente.getSelectedItem());

							AnimalDAO animalDAO = new AnimalDAO();
							if (animalDAO.salvar(animalController.getAnimal())) {
								ListaAnimal listaAnimal = new ListaAnimal();
								listaAnimal.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Não Salvou", "Falha", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							lbMensagem.setText("Erro! Algum campo vazio");
							lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
						}
					}
				});
				btnSalvar.setBounds(100, 194, 89, 23);
				panel.add(btnSalvar);
		
				JButton btnCancelar = new JButton("Voltar");
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
				btnCancelar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/voltar.png")));
				btnCancelar.setBorder(null);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ListaAnimal listaAnimal = new ListaAnimal();
						listaAnimal.setVisible(true);
						dispose();
					}
				});
				btnCancelar.setBounds(193, 194, 89, 23);
				panel.add(btnCancelar);
		
		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBounds(0, 0, 434, 261);
		panel.add(lbImagem);
		setModal(true);

	}

	public CadastroAnimal() {
		setTitle("Cadastro Animal");
		initComponent();
		animalController.getAnimal();
	}

	public CadastroAnimal(Animal animal) {
		initComponent();
		animalController.setAnimal(animal);
		tfNome.setText(animal.getNome());
		tfRaca.setText(animal.getRaca());
		tfIdade.setText(String.valueOf(animal.getIdade()));
		comboBox.setSelectedItem(animal.getTipo());
		cmbCliente.setSelectedItem((Cliente) animal.getCliente());
	}
}
