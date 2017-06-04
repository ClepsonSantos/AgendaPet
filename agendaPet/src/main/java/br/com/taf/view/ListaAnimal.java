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

import br.com.taf.controller.AnimalController;
import br.com.taf.model.Animal;
import br.com.taf.model.AnimalModel;
import br.com.taf.repository.AnimalDAO;
import br.com.taf.repository.DAO;

public class ListaAnimal extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lbMensagem;
	private JTextField tfFilter;
	
	private AnimalController animalController = new AnimalController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaAnimal frame = new ListaAnimal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListaAnimal() {
		
		// Desabilitar O Maximizarr
		setResizable(false);

		// Essas três linhas de códigos modifica a cor do JOptionPane
		UIManager.put("OptionPane.messageForeground", Color.black);
		UIManager.put("OptionPane.background", Color.WHITE);
		UIManager.put("Panel.background", Color.WHITE);

		setTitle("Lista de Animais");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 438, 290);
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
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBounds(10, 11, 414, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		lbMensagem = new JLabel("");
		lbMensagem.setForeground(Color.RED);
		lbMensagem.setBounds(99, 228, 312, 16);
		contentPane.add(lbMensagem);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbMensagem.setText("");
				lbMensagem.setIcon(null);
				CadastroAnimal cadastroAnimal = new CadastroAnimal();
				cadastroAnimal.setVisible(true);
			}
		});
		btnAdicionar.setBackground(Color.WHITE);
		btnAdicionar.setBorder(null);
		btnAdicionar.setFocusable(false);
		btnAdicionar.setBounds(10, 11, 77, 24);
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
					lbMensagem.setIcon(new ImageIcon(ListaAnimal.class.getResource("/br/com/taf/img/error.png")));
				} else {
					lbMensagem.setText("");
					lbMensagem.setIcon(null);
					AnimalModel cm = (AnimalModel) table.getModel();
					Animal animal = cm.getValueAt(i);
					CadastroAnimal tcc = new CadastroAnimal(animal);
					tcc.setVisible(true);

					preencheTabelaAnimal(animalController.lista());
				}
			}

		});
		btnEditar.setBounds(97, 11, 77, 24);
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
						AnimalModel cm = (AnimalModel) table.getModel();
						Animal animal = cm.getValueAt(i);
						
						animalController.excluir(animal);
						preencheTabelaAnimal(animalController.lista());
						JOptionPane.showMessageDialog(null, "Animal Excluído");
					}
				} else {
					lbMensagem.setText("Selecione uma linha para Excluir");
					lbMensagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/error.png")));
				}
			}
		});
		btnExcluir.setBounds(184, 11, 77, 24);
		panel.add(btnExcluir);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBorder(null);
		btnRefresh.setFocusable(false);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencheTabelaAnimal(animalController.lista());
			}
		});
		btnRefresh.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		// adicionando uma img aparti de um pacote no projeto
		btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/refresh.png")));
		// fim do código de adicionando uma img aparti de um pacote no projeto
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(295, 11, 107, 24);
		panel.add(btnRefresh);

		table = new JTable();
		table.setBounds(10, 83, 414, 133);
		table.setBackground(Color.WHITE);
		contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 83, 414, 123);
		contentPane.add(scrollPane);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/voltar.png")));
		btnVoltar.setFont(new Font("Aero Matics Light", Font.BOLD, 15));
		btnVoltar.setBounds(10, 218, 77, 36);
		contentPane.add(btnVoltar);
		btnVoltar.setBorder(null);
		btnVoltar.setFocusable(false);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setBackground(Color.WHITE);

		JLabel lbImagem = new JLabel("");
		lbImagem.setIcon(new ImageIcon(this.getClass().getResource("/br/com/taf/img/petFundo2.png")));
		lbImagem.setBackground(Color.WHITE);
		lbImagem.setBounds(0, -1, 434, 262);
		contentPane.add(lbImagem);

		preencheTabelaAnimal(animalController.lista());
	}

	public void preencheTabelaAnimal(List<Animal> animals) {
		lbMensagem.setText("");
		lbMensagem.setIcon(null);
		AnimalModel cm = new AnimalModel(animals);
		table.setModel(cm);
	}
}
