package br.com.taf.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class TelaPrincipal extends JFrame {

	public String teste;
	private JPanel contentPane;
	private TelaLogin tl = new TelaLogin();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		
		// Desabilitar O Maximizarr
		setResizable(false);
		setFont(null);
		setTitle("Petaf\r\n");
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Coloca um icone no canto superior esquerdo da tela
		URL url = this.getClass().getResource("/br/com/taf/img/logo.png");
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		this.setIconImage(imagemTitulo);

		JButton btnCliente = new JButton("Cliente");
		btnCliente.setBounds(17, 266, 98, 26);
		btnCliente.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/cliente.png")));
		contentPane.add(btnCliente);
		btnCliente.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnCliente.setBackground(Color.WHITE);
		btnCliente.setBorder(null);
		btnCliente.setFocusable(false);

		JButton btnAnimal = new JButton("Animal");
		btnAnimal.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnAnimal.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/animal.png")));
		btnAnimal.setBackground(Color.WHITE);
		btnAnimal.setBorder(null);
		btnAnimal.setFocusable(false);
		btnAnimal.setBounds(399, 266, 98, 26);
		contentPane.add(btnAnimal);
		btnAnimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaAnimal listaAnimal = new ListaAnimal();
				listaAnimal.setVisible(true);
			}
		});
		btnAnimal.setBorder(null);
		btnAnimal.setFocusable(false);

		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnAgenda.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/agenda.png")));
		btnAgenda.setBackground(Color.WHITE);
		btnAgenda.setBorder(null);
		btnAgenda.setFocusable(false);
		btnAgenda.setBounds(132, 266, 98, 26);
		contentPane.add(btnAgenda);
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaAgenda ta = new ListaAgenda();
				ta.setVisible(true);
			}
		});
		btnAgenda.setBorder(null);
		btnAgenda.setFocusable(false);

		JLabel label_1 = new JLabel(tl.getNomeUsuario().toUpperCase());
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		label_1.setBounds(368, 28, 135, 16);
		contentPane.add(label_1);

		JLabel lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		lblBemVindo.setBounds(400, 11, 95, 16);
		contentPane.add(lblBemVindo);

		JButton btnHome = new JButton("");
		btnHome.setBorder(null);
		btnHome.setFocusable(false);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				dispose();
			}
		});
		btnHome.setBackground(Color.WHITE);
		btnHome.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/home.png")));
		btnHome.setBounds(10, 11, 52, 28);
		contentPane.add(btnHome);
		
		JButton btnAgendaParaHoje = new JButton("Agenda Para Hoje");
		btnAgendaParaHoje.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/agenda.png")));
		btnAgendaParaHoje.setBorder(null);
		btnAgendaParaHoje.setFocusable(false);
		btnAgendaParaHoje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaAgendaHoje lsh = new ListaAgendaHoje();
				lsh.setVisible(true);
			}
		});
		btnAgendaParaHoje.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnAgendaParaHoje.setBackground(Color.WHITE);
		btnAgendaParaHoje.setBounds(240, 265, 150, 28);
		contentPane.add(btnAgendaParaHoje);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundoPrincipal.png")));
		lbImagem.setBounds(0, 0, 515, 333);
		contentPane.add(lbImagem);
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaCliente listaCliente = new ListaCliente();
				listaCliente.setVisible(true);
			}
		});
	}
}
