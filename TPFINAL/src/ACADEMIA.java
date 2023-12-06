import Model.Aluno;
import Model.FuncDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ACADEMIA extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JFormattedTextField textIdade;
	private JFormattedTextField textPeso;
	private JFormattedTextField textAltura;
	private JTextField textObjetivo;
	private JLabel lblNewLabel_4;

	FuncDAO func = new FuncDAO();
	private ResultSet result;

	public Void resul(String nome){
		result = func.getFunc(nome);
		return null;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ACADEMIA frame = new ACADEMIA();
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
	public ACADEMIA() throws ParseException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(25, 24, 59, 14);
		contentPane.add(lblNewLabel);
		
		textNome = new JTextField();
		textNome.setBounds(94, 21, 160, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		MaskFormatter ssnFormatter = new MaskFormatter("###");
		ssnFormatter.setPlaceholderCharacter('0');
		textIdade = new JFormattedTextField(ssnFormatter);
		textIdade.setColumns(10);
		textIdade.setBounds(94, 66, 160, 20);
		contentPane.add(textIdade);

		ssnFormatter = new MaskFormatter("###.##");
		ssnFormatter.setPlaceholderCharacter('0');
		textPeso = new JFormattedTextField(ssnFormatter);
		textPeso.setColumns(10);
		textPeso.setBounds(94, 112, 160, 20);
		contentPane.add(textPeso);

		ssnFormatter = new MaskFormatter("#.##");
		ssnFormatter.setPlaceholderCharacter('0');
		textAltura = new JFormattedTextField(ssnFormatter);
		textAltura.setColumns(10);
		textAltura.setBounds(94, 159, 160, 20);
		contentPane.add(textAltura);
		
		textObjetivo = new JTextField();
		textObjetivo.setColumns(10);
		textObjetivo.setBounds(94, 208, 160, 20);
		contentPane.add(textObjetivo);
		
		JLabel lblNewLabel_1 = new JLabel("Idade:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(25, 69, 59, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Peso:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_2.setBounds(25, 115, 59, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Altura:");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_3.setBounds(25, 162, 59, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Objetivo:");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_4.setBounds(25, 211, 59, 14);
		contentPane.add(lblNewLabel_4);

		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno aluno = new Aluno();
				if(textNome.getText().isEmpty() || textIdade.getText().isEmpty() || textAltura.getText().isEmpty() || textPeso.getText().isEmpty() || textObjetivo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
				}else{
					aluno.nome = textNome.getText();
					aluno.idade = Integer.parseInt(textIdade.getText());
					aluno.altura = Float.parseFloat(textAltura.getText());
					aluno.peso = Float.parseFloat(textPeso.getText());
					aluno.objetivo = textObjetivo.getText();
					func.setAluno(aluno);
				}
			}
		});
		btnIncluir.setBounds(25, 258, 113, 43);
		contentPane.add(btnIncluir);
		
		JButton btnApresentarDados = new JButton("<html><center>Apresentar<br>Dados</center>");
		btnApresentarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet alu = func.getAlunos();
					ArrayList<Aluno> alunos = new ArrayList<Aluno>();
					Gson gson = new GsonBuilder().setPrettyPrinting().create();
					while (alu.next()) {
						Aluno aluno = new Aluno();
						aluno.Id = alu.getInt("id");
						aluno.nome = alu.getString("nome");
						aluno.idade = alu.getInt("idade");
						aluno.peso = alu.getFloat("peso");
						aluno.altura = alu.getFloat("altura");
						aluno.objetivo = alu.getString("objetivo");
						alunos.add(aluno);
					}
					String aux = gson.toJson(alunos);
					if (aux != null){
						JTextArea messageJson = new JTextArea(6, 25);
						messageJson.setText(aux);
						messageJson.setEditable(false);

						JScrollPane scrollPane = new JScrollPane(messageJson);
						scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
						JOptionPane.showMessageDialog(null, scrollPane);
					}else {
						JOptionPane.showMessageDialog(null, "SEM RESULTADOS");
					}
				} catch (Exception erro){
					JOptionPane.showMessageDialog(null, erro);
				}

			}
		});
		
		btnApresentarDados.setBounds(25, 307, 113, 43);
		contentPane.add(btnApresentarDados);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNome.setText("");
				textIdade.setText("");
				textAltura.setText("");
				textPeso.setText("");
				textObjetivo.setText("");
			}
		});
		btnLimpar.setBounds(143, 258, 113, 43);
		contentPane.add(btnLimpar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(143, 307, 113, 43);
		contentPane.add(btnSair);
	}
}
