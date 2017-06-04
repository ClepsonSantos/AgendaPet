package br.com.taf.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import br.com.taf.controller.AgendaController;
import br.com.taf.model.Agenda;
import br.com.taf.model.AgendaModel;
import br.com.taf.repository.AgendaDAO;
import br.com.taf.repository.DAO;

public class ListaAgenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lbMensagem;
	public static int somaLinhaAgenda;
	
	private AgendaController agendaController = new AgendaController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaAgenda frame = new ListaAgenda();
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListaAgenda() {
		
		// Desabilitar O Maximizarr
		setResizable(false);

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setTitle("Lista de Agendas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 300);
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
		table.setBackground(Color.WHITE);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBounds(10, 80, 414, 170);
		contentPane.add(table);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 11, 414, 58);
		contentPane.add(panel);
		panel.setLayout(null);

		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(94, 228, 316, 16);
		contentPane.add(lbMensagem);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbMensagem.setText("");
				lbMensagem.setIcon(null);
				CadastroAgenda cadastroAgenda = new CadastroAgenda();
				cadastroAgenda.setVisible(true);
			}
		});
		btnAdicionar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
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
			public void actionPerformed(ActionEvent arg0) {
				int i = table.getSelectedRow();
				if (i < 0) {
					lbMensagem.setText("Selecione uma linha para Editar");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				} else {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);

					AgendaModel cm = (AgendaModel) table.getModel();
					Agenda agenda = cm.getValueAt(i);
					CadastroAgenda tcc = new CadastroAgenda(agenda);
					tcc.setVisible(true);

					AgendaDAO aDAO = new AgendaDAO();
					preencheTabelaAgenda(aDAO.listar());
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
						AgendaModel cm = (AgendaModel) table.getModel();
						Agenda cliente = cm.getValueAt(i);
						agendaController.excluir(cliente);
						preencheTabelaAgenda(agendaController.lista());
						JOptionPane.showMessageDialog(null, "Agendamento Excluído");
					}
				} else {
					lbMensagem.setText("Selecione uma linha para Excluir");
					lbMensagem.setIcon(new ImageIcon(ListaAgenda.class.getResource("/br/com/taf/img/error.png")));
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
				preencheTabelaAgenda(agendaController.lista());
			}
		});
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/refresh.png")));
		btnRefresh.setBounds(299, 11, 109, 36);
		panel.add(btnRefresh);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 80, 414, 123);
		contentPane.add(scrollPane);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/back.png")));
		btnVoltar.setBounds(11, 217, 77, 36);
		contentPane.add(btnVoltar);
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setBackground(Color.WHITE);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBounds(0, 0, 444, 271);
		contentPane.add(lbImagem);

		preencheTabelaAgenda(agendaController.lista());

	}

	public void preencheTabelaAgenda(List<Agenda> agendas) {
		lbMensagem.setText("");
		lbMensagem.setIcon(null);
		
		// Exclui automaticamente as datas antigas.
		List<Agenda> lista = agendaController.lista();
		Date hj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			String f = sdf.format(hj);
			d = sdf.parse(f);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (Agenda agenda : lista) {
			if (d.after(agenda.getData())) {
				JOptionPane.showMessageDialog(null, "A Consulta de " + agenda.getCliente()
						+ " foi excluida, pois já passou a data.\n De um Refresh na tabela.");
				agendaController.excluir(agenda);
			}
		}
		AgendaModel cm = new AgendaModel(agendas);
		table.setModel(cm);
	}
}
