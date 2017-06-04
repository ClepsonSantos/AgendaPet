package br.com.taf.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.taf.controller.AgendaController;
import br.com.taf.controller.ClienteController;
import br.com.taf.model.Agenda;
import br.com.taf.model.Cliente;
import br.com.taf.repository.AgendaDAO;
import br.com.taf.repository.ClienteDAO;
import br.com.taf.repository.DAO;

public class CadastroAgenda extends JDialog {

	private JPanel contentPane;
	private JFormattedTextField tfHora;
	private JFormattedTextField tfData;
	private JComboBox cmbCliente;
	private JLabel lbMensagem;
	private Agenda agenda;
	
	private AgendaController agendaController = new AgendaController();
	private ClienteController clienteController = new ClienteController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAgenda frame = new CadastroAgenda();
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
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblCliente.setBounds(36, 41, 61, 14);
		panel.add(lblCliente);

		cmbCliente = new JComboBox();
		cmbCliente.setBounds(93, 40, 229, 20);
		panel.add(cmbCliente);

		try {
			List<Cliente> clientes = clienteController.lista();
			for (Cliente cliente : clientes) {
				cmbCliente.addItem(cliente);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Sem Clientes Cadastrados");
		}

		// Mascara para o campo Data
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblData.setBounds(55, 127, 46, 14);
		panel.add(lblData);
		MaskFormatter mascaraData = null;
		try {
			mascaraData = new MaskFormatter("##/##/####");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		tfData = new JFormattedTextField(mascaraData);
		tfData.setBounds(93, 124, 229, 20);
		panel.add(tfData);

		// Mascara para o campo Hora
		JLabel lblHora = new JLabel("Hora");
		lblHora.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblHora.setBounds(54, 83, 46, 14);
		panel.add(lblHora);
		MaskFormatter mascaraCep = null;
		try {
			mascaraCep = new MaskFormatter("##:##");
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}

		tfHora = new JFormattedTextField(mascaraCep);
		tfHora.setBounds(93, 80, 229, 20);
		panel.add(tfHora);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/salvar.png")));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBorder(null);
		btnSalvar.setFocusable(false);
		btnSalvar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Verifica se o campos hora ta vazio. 
				if (!tfHora.getText().equals("  :  ") && !tfData.getText().equals("  /  /    ")) {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);
					
					agendaController.getAgenda().setCliente((Cliente) cmbCliente.getSelectedItem());

					// Formatando a hora
					Date hora = null;
					try {
						hora = new SimpleDateFormat("HH:mm").parse(tfHora.getText().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					agendaController.getAgenda().setHora(hora);

					// Formantando a data
					Date data = null;
					try {
						data = new SimpleDateFormat("dd/MM/yyyy").parse(tfData.getText().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					agendaController.getAgenda().setData(data);

					int cont = 0;
					List<Agenda> lista = agendaController.lista();
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					for (Agenda agenda : lista) {
						if (format.format(agenda.getData()).equals(tfData.getText().toString())) {
							cont++;
						}
					}
					
					Date hj = new Date();

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					try {						
						// Verifica se a data adiciona não é uma antes da data
						// de hoje
						
						String f = sdf.format(hj);
						Date d = sdf.parse(f);
						//System.out.println("HJ1: "+data);
						//System.out.println("HJ1: "+d);
						if (!d.after(data)) {
							lbMensagem.setText("");
							lbMensagem.setIcon(null);
							// verifica o tanto de agenda marcada em um dia. No
							// caso só pode marca 5 atendimentos por dia
							if (cont <= 4) {
								lbMensagem.setText("");
								lbMensagem.setIcon(null);
								if (agendaController.salvar(agendaController.getAgenda())) {
									ListaAgenda agenda = new ListaAgenda();
									agenda.setVisible(true);
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Não Salvou", "Erro",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								lbMensagem.setText("Erro! Angenda cheia pra esse dia");
								lbMensagem.setIcon(
										new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
							}
						} else {
							lbMensagem.setText("Data Inválida! Essa Data Já Passou");
							lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
						}
					} catch (HeadlessException | ParseException e) {
						e.printStackTrace();
					}
				} else {
					lbMensagem.setText("Erro! Algum campo vazio");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});
		btnSalvar.setBounds(93, 166, 89, 28);
		panel.add(btnSalvar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaAgenda listaAgenda = new ListaAgenda();
				listaAgenda.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/voltar.png")));
		btnVoltar.setBounds(193, 166, 90, 28);
		panel.add(btnVoltar);

		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(17, 230, 326, 14);
		panel.add(lbMensagem);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBounds(0, 0, 434, 261);
		panel.add(lbImagem);
	}

	public CadastroAgenda() {
		setModal(true);
		setTitle("Cadastro Agenda");
		initComponent();
		agendaController.setAgenda(new Agenda());
	}

	public CadastroAgenda(Agenda agenda) {
		initComponent();
		agendaController.setAgenda(agenda);
		SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");

		tfData.setText(String.valueOf(formatData.format(agenda.getData())));
		tfHora.setText(String.valueOf(formatHora.format(agenda.getHora())));
		cmbCliente.setSelectedItem((Cliente) agenda.getCliente());
	}
}
