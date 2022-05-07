package br.com.unicid.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;

import br.com.unicid.dao.AlunoDao;
import br.com.unicid.dao.BoletimDao;
import br.com.unicid.dao.CampusDao;
import br.com.unicid.dao.CursoDao;
import br.com.unicid.dao.CursoXMateriaDao;
import br.com.unicid.integration.ViaCepIntegration;
import br.com.unicid.model.AlunoModel;
import br.com.unicid.model.BoletimModel;
import br.com.unicid.model.CampusModel;
import br.com.unicid.model.CursoModel;
import br.com.unicid.model.CursoXMateriaModel;
import br.com.unicid.model.EnderecoModel;
import br.com.unicid.model.MateriaModel;
import br.com.unicid.model.SemestreModel;

public class Tela extends JFrame {

	private JPanel contentPane;
	private JTextField txtRGM;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtLogradouro;
	private JTextField txtMunicipio;
	private JTextField txtEstado;

	private ViaCepIntegration via = new ViaCepIntegration();
	private JTextField txtCep;
	private AlunoDao alunoDao;
	private BoletimDao boletimDao;
	final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtRgmNotas;
	private JTextField txtNomeNotas;
	private JTextField txtCursoNotas;
	private JTextField txtFaltas;
	private JTextField txtRgmBoletim;
	private JTextField txtNomeBoletim;
	private JTextField txtFaltaBoletim;
	private JTextField txtNotaBoletim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	public Tela() throws ParseException {
		CampusDao campus = new CampusDao();
		CursoDao dao = new CursoDao();
		CursoXMateriaDao cursoxMateriaDao = new CursoXMateriaDao();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 380);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuAluno = new JMenu("Aluno");
		menuBar.add(menuAluno);

		JMenuItem menuItemSalvar = new JMenuItem("Salvar");
		menuItemSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		menuAluno.add(menuItemSalvar);

		JMenuItem mntmAlterar = new JMenuItem("Alterar");
		menuAluno.add(mntmAlterar);

		JMenuItem mntmListar = new JMenuItem("LIstar");
		menuAluno.add(mntmListar);

		JMenuItem mntmDeletar = new JMenuItem("Deletar");
		menuAluno.add(mntmDeletar);

		JSeparator separator = new JSeparator();
		menuAluno.add(separator);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
		menuAluno.add(mntmSair);

		JMenu menuNotas = new JMenu("Notas e faltas");
		menuBar.add(menuNotas);

		JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
		menuNotas.add(mntmNewMenuItem);

		JMenuItem mntmAlterar_1 = new JMenuItem("Alterar");
		mntmAlterar_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		menuNotas.add(mntmAlterar_1);

		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		menuNotas.add(mntmExcluir);

		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		menuNotas.add(mntmConsultar);

		JMenu mnNewMenu = new JMenu("Ajuda");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sobre");
		mnNewMenu.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 43, 576, 265);
		contentPane.add(tabbedPane);

		JPanel panelDados = new JPanel();
		tabbedPane.addTab("Dados pessoais", null, panelDados, null);
		panelDados.setLayout(null);

		JLabel lblRGM = new JLabel("RGM");
		lblRGM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRGM.setBounds(10, 11, 46, 14);
		panelDados.add(lblRGM);

		txtRGM = new JTextField(8);
		txtRGM.setBounds(54, 8, 128, 25);
		panelDados.add(txtRGM);
		txtRGM.setColumns(10);
		

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(217, 11, 46, 14);
		panelDados.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(270, 8, 236, 25);
		panelDados.add(txtNome);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataDeNascimento.setBounds(10, 47, 158, 22);
		panelDados.add(lblDataDeNascimento);

		JLabel lblNewLabel_1_1 = new JLabel("CPF");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(330, 47, 30, 22);
		panelDados.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(10, 83, 46, 14);
		panelDados.add(lblNewLabel_1_2);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(63, 80, 478, 25);
		panelDados.add(txtEmail);

		JLabel lblNewLabel_1_2_1 = new JLabel("CEP");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(10, 119, 46, 14);
		panelDados.add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Logradouro");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1_1.setBounds(223, 115, 90, 22);
		panelDados.add(lblNewLabel_1_2_1_1);

		txtLogradouro = new JTextField();
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(323, 116, 218, 25);
		panelDados.add(txtLogradouro);

		JLabel lblNewLabel_1_2_1_2 = new JLabel("Munic\u00EDpio");
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1_2.setBounds(10, 155, 74, 22);
		panelDados.add(lblNewLabel_1_2_1_2);

		txtMunicipio = new JTextField();
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(94, 152, 143, 25);
		panelDados.add(txtMunicipio);

		JLabel lblNewLabel_1_2_1_2_1 = new JLabel("Estado");
		lblNewLabel_1_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1_2_1.setBounds(269, 152, 53, 22);
		panelDados.add(lblNewLabel_1_2_1_2_1);

		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(331, 148, 209, 25);
		panelDados.add(txtEstado);

		JLabel lblNewLabel_1_2_1_2_2 = new JLabel("Celular");
		lblNewLabel_1_2_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2_1_2_2.setBounds(10, 191, 74, 22);
		panelDados.add(lblNewLabel_1_2_1_2_2);

		JFormattedTextField fmtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		fmtCpf.setBounds(370, 44, 171, 25);
		panelDados.add(fmtCpf);

		JFormattedTextField fmtCelular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		fmtCelular.setBounds(76, 188, 143, 25);
		panelDados.add(fmtCelular);

		JFormattedTextField fmtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		fmtData.setBounds(189, 46, 128, 25);
		panelDados.add(fmtData);

		txtCep = new JTextField();
		txtCep.setFocusTraversalKeysEnabled(false);
		txtCep.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (txtCep.getText().toString().length() == 8) {
					EnderecoModel adress;
					try {
						adress = via.ConsultarCEP(txtCep.getText());
						txtLogradouro.setText(adress.getLogradouro());
						txtMunicipio.setText(adress.getLocalidade());
						txtEstado.setText(adress.getUf());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		txtCep.setBounds(63, 116, 143, 25);;
		panelDados.add(txtCep);
		txtCep.setColumns(10);

		JPanel panelCurso = new JPanel();
		tabbedPane.addTab("Curso", null, panelCurso, null);
		panelCurso.setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurso.setBounds(17, 21, 45, 22);
		panelCurso.add(lblCurso);
		
		JComboBox cbxCampus = new JComboBox();
		extracted(cbxCampus);
		cbxCampus.setBounds(90, 68, 439, 31);
		panelCurso.add(cbxCampus);

		JComboBox cbxCurso = new JComboBox();
		cbxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CursoModel curso = (CursoModel) cbxCurso.getSelectedItem();
				cbxCampus.removeAllItems();
				extracted(cbxCampus);
				campus.listarCurso().stream().forEach((cam) -> {
				if(curso.getCampus().getIdCampus() == cam.getIdCampus())
						cbxCampus.addItem(cam); 
				});
			}
		});
		extracted(cbxCurso);
		cbxCurso.setBounds(70, 14, 439, 31);
		panelCurso.add(cbxCurso);

		JLabel lblC = new JLabel("Campus");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblC.setBounds(17, 69, 63, 22);
		panelCurso.add(lblC);


		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeriodo.setBounds(17, 117, 63, 22);
		panelCurso.add(lblPeriodo);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Matutino");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNewRadioButton.setBounds(119, 117, 109, 23);
		panelCurso.add(rdbtnNewRadioButton);

		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		buttonGroup.add(rdbtnVespertino);
		rdbtnVespertino.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnVespertino.setBounds(230, 118, 109, 23);
		panelCurso.add(rdbtnVespertino);

		JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
		buttonGroup.add(rdbtnNoturno);
		rdbtnNoturno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdbtnNoturno.setBounds(349, 118, 109, 23);
		panelCurso.add(rdbtnNoturno);

		JButton btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AlunoModel aluno;
				try {
					aluno = new AlunoModel();
					aluno.setRgm(Integer.parseInt(txtRGM.getText()));
					aluno.setNome(txtNome.getText());
					aluno.setDataNascimento(fmtData.getText());
					aluno.setCpf(fmtCpf.getText());
					aluno.setEmail(txtEmail.getText());
					aluno.setCep(txtCep.getText());
					aluno.setLogradouro(txtLogradouro.getText());
					aluno.setMunicipio(txtMunicipio.getText());
					aluno.setEstado(txtEstado.getText());
					aluno.setCelular(fmtCelular.getText());
					
					CursoModel curso = (CursoModel) cbxCurso.getSelectedItem();
					curso.setIdCurso(curso.getIdCurso());
					
					CampusModel campuscbx = (CampusModel) cbxCampus.getSelectedItem();
					curso.setCampus(campuscbx);
					curso.setCurso(curso.getCurso());
					
					if (rdbtnNewRadioButton.isSelected()) {
						aluno.setPeriodo(rdbtnNewRadioButton.getText());
					} else if (rdbtnNoturno.isSelected()) {
						aluno.setPeriodo(rdbtnNoturno.getText());
					}else {
						aluno.setPeriodo(rdbtnVespertino.getText());
					}
					
					aluno.setCurso(curso);
					
					alunoDao = new AlunoDao();
					alunoDao.salvar(aluno);
					JOptionPane.showMessageDialog(null, "CADASTRADO COM SUCESSO!!!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR UM ALUNO :: RGM JÁ CADASTRADO OU CAMPO EM BRANCO");
				}


			}
		});
		btnSalvar.setIcon(new ImageIcon(".\\Images\\icons8-crie-um-novo-64.png"));
		btnSalvar.setBounds(47, 150, 82, 76);
		panelCurso.add(btnSalvar);

		JButton btnSair = new JButton("");
		btnSair.setIcon(new ImageIcon(".\\Images\\icons8-desligar-48.png"));
		btnSair.setBounds(439, 150, 122, 76);
		panelCurso.add(btnSair);

		JButton btnLeitura = new JButton("");
		btnLeitura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					alunoDao = new AlunoDao();
					AlunoModel aluno = alunoDao.getByRgm(Integer.parseInt(txtRGM.getText()));
					txtNome.setText(aluno.getNome());
					fmtData.setText(aluno.getDataNascimento());
					fmtCpf.setText(aluno.getCpf());
					txtEmail.setText(aluno.getEmail());
					txtCep.setText(aluno.getCep());
					txtLogradouro.setText(aluno.getLogradouro());
					txtMunicipio.setText(aluno.getMunicipio());
					txtEstado.setText(aluno.getEstado());
					fmtCelular.setText(aluno.getCelular());
					
					CursoModel curso = new CursoModel();
					curso.setIdCurso(aluno.getCurso().getIdCurso());
					curso.setCurso(aluno.getCurso().getCurso());
					
					CampusModel campus = new CampusModel();
					campus.setIdCampus(aluno.getCurso().getCampus().getIdCampus());
					campus.setNomeCampus(aluno.getCurso().getCampus().getNomeCampus());
					curso.setCampus(campus);

					cbxCurso.setSelectedIndex(aluno.getCurso().getIdCurso());
					cbxCampus.setSelectedIndex(1);
					
					if (aluno.getPeriodo().equals(rdbtnNoturno.getText())) {
						rdbtnNoturno.setSelected(isActive());
					} 
					
					if (aluno.getPeriodo().equals(rdbtnNewRadioButton.getText())) {
						rdbtnNewRadioButton.setSelected(isActive());
					}
					
					if (aluno.getPeriodo().equals(rdbtnVespertino.getText())) {
						rdbtnVespertino.setSelected(isActive());
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao listar :: Aluno não existe");
				}
			}
		});
		btnLeitura.setIcon(new ImageIcon(".\\Images\\icons8-leitura-48.png"));
		btnLeitura.setBounds(139, 150, 82, 76);
		panelCurso.add(btnLeitura);

		JButton btnAtualizar = new JButton("");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunoModel aluno;
				try {
					aluno = new AlunoModel();
					aluno.setRgm(Integer.parseInt(txtRGM.getText()));
					aluno.setNome(txtNome.getText());
					aluno.setDataNascimento(fmtData.getText());
					aluno.setCpf(fmtCpf.getText());
					aluno.setEmail(txtEmail.getText());
					aluno.setCep(txtCep.getText());
					aluno.setLogradouro(txtLogradouro.getText());
					aluno.setMunicipio(txtMunicipio.getText());
					aluno.setEstado(txtEstado.getText());
					aluno.setCelular(fmtCelular.getText());
					
					CursoModel curso = (CursoModel) cbxCurso.getSelectedItem();
					curso.setIdCurso(curso.getIdCurso());
					
					CampusModel campuscbx = (CampusModel) cbxCampus.getSelectedItem();
					curso.setCampus(campuscbx);
					curso.setCurso(curso.getCurso());
					
					if (rdbtnNewRadioButton.isSelected()) {
						aluno.setPeriodo(rdbtnNewRadioButton.getText());
					} else if (rdbtnNoturno.isSelected()) {
						aluno.setPeriodo(rdbtnNoturno.getText());
					}else {
						aluno.setPeriodo(rdbtnVespertino.getText());
					}
					
					aluno.setCurso(curso);
					
					alunoDao = new AlunoDao();
					alunoDao.update(aluno);
					JOptionPane.showMessageDialog(null, "Atualizado COM SUCESSO!!!");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar dados do aluno");
				}
			}
		});
		btnAtualizar.setIcon(new ImageIcon(".\\Images\\icons8-update-60.png"));
		btnAtualizar.setBounds(234, 150, 82, 76);
		panelCurso.add(btnAtualizar);

		JButton btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Integer rgm = Integer.parseInt(txtRGM.getText());
					alunoDao = new AlunoDao();
					AlunoModel aluno = alunoDao.getByRgm(rgm);
					boletimDao = new BoletimDao();
					boletimDao.delete(aluno.getIdAluno());
					alunoDao.delete(rgm);
					JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
				}
				catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "ERRO AO DELETAR UM ALUNO");
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(".\\Images\\icons8-remover-48.png"));
		btnExcluir.setBounds(326, 150, 82, 76);
		panelCurso.add(btnExcluir);
		
		JPanel panelNotas = new JPanel();
		tabbedPane.addTab("Notas e faltas", null, panelNotas, null);
		panelNotas.setLayout(null);
		
		JLabel lblRGM_1 = new JLabel("RGM");
		lblRGM_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRGM_1.setBounds(10, 15, 37, 22);
		panelNotas.add(lblRGM_1);
		
		JComboBox cbxDisciplina = new JComboBox();
		cbxDisciplina.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma disciplina"}));
		cbxDisciplina.setBounds(93, 83, 457, 31);
		panelNotas.add(cbxDisciplina);
			
		
		txtRgmNotas = new JTextField(10);
		txtRgmNotas.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				if (txtRgmNotas.getText().toString().length() == 8 || txtRgmNotas.getText().toString().length() == 10) {
					AlunoModel aluno;
					cbxDisciplina.removeAllItems();
					extractedEmpty(cbxDisciplina);
					try {
						alunoDao = new AlunoDao();
						aluno = alunoDao.getByRgm(Integer.parseInt(txtRgmNotas.getText()));
						txtNomeNotas.setText(aluno.getNome());
						txtCursoNotas.setText(aluno.getCurso().getCurso());
						
						cursoxMateriaDao.listarCursoeMaterias(txtCursoNotas.getText()).stream().forEach((mc) -> {
								cbxDisciplina.addItem(mc);
						});
						
					} catch (Exception e4) {
						JOptionPane.showMessageDialog(null, "ALUNO NÃO ENCONTRADO");
					}
					
				}
			}
		});
		txtRgmNotas.setBounds(52, 13, 205, 25);
		panelNotas.add(txtRgmNotas);
		

		
		txtNomeNotas = new JTextField(10);
		txtNomeNotas.setEditable(false);
		txtNomeNotas.setBounds(303, 13, 247, 25);
		panelNotas.add(txtNomeNotas);
		
		txtCursoNotas = new JTextField(10);
		txtCursoNotas.setEditable(false);
		txtCursoNotas.setBounds(10, 47, 540, 25);
		panelNotas.add(txtCursoNotas);
		
		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDisciplina.setBounds(10, 84, 73, 22);
		panelNotas.add(lblDisciplina);
		
		JLabel Semestre = new JLabel("Semestre");
		Semestre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Semestre.setBounds(10, 130, 73, 22);
		panelNotas.add(Semestre);
		
		JComboBox cbxSemestre = new JComboBox();
		cbxSemestre.setModel(new DefaultComboBoxModel(new String[] {"Selecione um semestre", "2020-1", "2020-2", "2021-1", "2021-2", "2022-1", "2022-2", "2023-1", "2023-2"}));
		cbxSemestre.setBounds(93, 125, 164, 31);
		panelNotas.add(cbxSemestre);
		
		JLabel Notas = new JLabel("Notas");
		Notas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Notas.setBounds(283, 128, 45, 22);
		panelNotas.add(Notas);
		
		JComboBox cbxNotas = new JComboBox();
		cbxNotas.setModel(new DefaultComboBoxModel(new String[] {"0", "0.25", "0.50", "0.75", "1", "1.25", "1.50", "1.75", "2", "2.25", "2.50", "2.75", "3", "3.25", "3.50", "3.75", "4", "4.25", "4.50", "4.75", "5", "5.25", "5.50", "5.75", "6", "6.25", "6.50", "6.75", "7", "7.25", "7.50", "7.75", "8", "8.25", "8.50", "8.75", "9", "9.25", "9.50", "9.75", "10"}));
		cbxNotas.setBounds(338, 125, 59, 31);
		panelNotas.add(cbxNotas);
		
		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaltas.setBounds(409, 130, 45, 22);
		panelNotas.add(lblFaltas);
		
		txtFaltas = new JTextField();
		txtFaltas.setBounds(464, 126, 86, 31);
		panelNotas.add(txtFaltas);
		txtFaltas.setColumns(10);
		
		JButton btnSalvar_1 = new JButton("");
		btnSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoletimModel model;
				try {
					model = new BoletimModel();
					model.setNotas(Double.parseDouble((String) cbxNotas.getSelectedItem()));
					model.setFaltas(Integer.parseInt(txtFaltas.getText()));
					
					alunoDao = new AlunoDao();
					AlunoModel aluno = alunoDao.getByRgm(Integer.parseInt(txtRgmNotas.getText()));
					model.setAluno(aluno);
					
					CursoXMateriaModel materia = (CursoXMateriaModel) cbxDisciplina.getSelectedItem();
					model.setCursoxmateria(materia);
					
					try {
						boletimDao = new BoletimDao();
						boletimDao.salvar(model);
						JOptionPane.showMessageDialog(null, "Nota cadastrada com sucesso!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Erro ao cadastra uma nota");
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnSalvar_1.setIcon(new ImageIcon(".\\Images\\icons8-crie-um-novo-64.png"));
		btnSalvar_1.setBounds(153, 174, 122, 52);
		panelNotas.add(btnSalvar_1);
		
		
		JPanel panelBoletim = 		new JPanel();
		tabbedPane.addTab("Boletim", null, panelBoletim, null);
		panelBoletim.setLayout(null);
		
		JLabel lblRgmBoletim = new JLabel("RGM");
		lblRgmBoletim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRgmBoletim.setBounds(10, 11, 37, 22);
		panelBoletim.add(lblRgmBoletim);
		
		
		JLabel lblNomeBoletim = new JLabel("Nome");
		lblNomeBoletim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeBoletim.setBounds(267, 15, 46, 14);
		panelBoletim.add(lblNomeBoletim);
		
		txtNomeBoletim = new JTextField();
		txtNomeBoletim.setColumns(10);
		txtNomeBoletim.setBounds(323, 11, 238, 20);
		panelBoletim.add(txtNomeBoletim);
		
		JLabel lblCurso_1 = new JLabel("Curso");
		lblCurso_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCurso_1.setBounds(10, 51, 45, 22);
		panelBoletim.add(lblCurso_1);
		
		JComboBox cbxCursoBoletim = new JComboBox();
		extracted(cbxCursoBoletim);
		cbxCursoBoletim.setBounds(91, 47, 468, 25);
		panelBoletim.add(cbxCursoBoletim);
		
		JLabel lblDisciplina_1 = new JLabel("Disciplina");
		lblDisciplina_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDisciplina_1.setBounds(10, 85, 73, 22);
		panelBoletim.add(lblDisciplina_1);
		
		JButton btnSair_1 = new JButton("");
		btnSair_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRgmNotas.setText(null);
				txtNomeNotas.setText(null);
				cbxDisciplina.setSelectedIndex(0);
				cbxCurso.setSelectedIndex(0);
				cbxNotas.setSelectedIndex(0);
				cbxSemestre.setSelectedIndex(0);
				txtFaltas.setText(null);
				
			}
		});
		btnSair_1.setIcon(new ImageIcon(".\\Images\\limpar-limpo.png"));
		btnSair_1.setBounds(285, 174, 122, 52);
		panelNotas.add(btnSair_1);
		
		JComboBox cbxDisciplinaBoletim = new JComboBox();
		cbxDisciplinaBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNotaBoletim.setText(null);
				txtFaltaBoletim.setText(null);
				
				try {
					alunoDao = new AlunoDao();
					AlunoModel aluno = alunoDao.getByRgm(Integer.parseInt(txtRgmBoletim.getText()));
					CursoXMateriaModel materia = (CursoXMateriaModel) cbxDisciplinaBoletim.getSelectedItem();
					
					boletimDao = new BoletimDao();
					BoletimModel boletim = boletimDao.listaNotasFaltas(aluno.getIdAluno(), materia.getIdMateriaXCurso());
					txtNotaBoletim.setText(boletim.getNotas().toString());
					txtFaltaBoletim.setText(boletim.getFaltas().toString());
					
				} catch (Exception e2) {
				}

			}
		});
		extracted(cbxDisciplinaBoletim);
		cbxDisciplinaBoletim.setBounds(93, 84, 468, 25);
		panelBoletim.add(cbxDisciplinaBoletim);
		
		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNota.setBounds(248, 131, 45, 22);
		panelBoletim.add(lblNota);
		
		JLabel lblFaltas_1 = new JLabel("Faltas");
		lblFaltas_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFaltas_1.setBounds(409, 131, 45, 22);
		panelBoletim.add(lblFaltas_1);
		
		txtFaltaBoletim = new JTextField();
		txtFaltaBoletim.setColumns(10);
		txtFaltaBoletim.setBounds(464, 127, 97, 31);
		panelBoletim.add(txtFaltaBoletim);
		
		txtNotaBoletim = new JTextField();
		txtNotaBoletim.setColumns(10);
		txtNotaBoletim.setBounds(302, 126, 97, 31);
		panelBoletim.add(txtNotaBoletim);
		
		
		txtRgmBoletim = new JTextField();
		txtRgmBoletim.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				if (txtRgmBoletim.getText().toString().length() == 8 || txtRgmBoletim.getText().toString().length() == 10) {
					AlunoModel aluno;
					cbxCursoBoletim.removeAllItems();
					extracted(cbxCursoBoletim);
					cbxDisciplinaBoletim.removeAllItems();
					txtNomeBoletim.setText(null);
					extractedEmpty(cbxDisciplinaBoletim);
					try {
						alunoDao = new AlunoDao();
						aluno = alunoDao.getByRgm(Integer.parseInt(txtRgmBoletim.getText()));
						txtNomeBoletim.setText(aluno.getNome());
						
						dao.listarCurso().stream().forEach((c) -> {
							if (aluno.getCurso().getIdCurso().equals(c.getIdCurso())) {
								cbxCursoBoletim.addItem(c);
							} 
						});	
						cbxCursoBoletim.setSelectedIndex(1);
						
						cursoxMateriaDao.listarCursoeMaterias(cbxCursoBoletim.getSelectedItem().toString()).stream().forEach((mc) -> {
							cbxDisciplinaBoletim.addItem(mc);
						});
						
					} catch (Exception e4) {
						JOptionPane.showMessageDialog(null, "ALUNO NÃO ENCONTRADO");
					}
				}
			}});
		txtRgmBoletim.setBounds(91, 11, 166, 20);
		panelBoletim.add(txtRgmBoletim);
		txtRgmBoletim.setColumns(10);
		
		dao.listarCurso().stream().forEach((c) -> {
			cbxCurso.addItem(c);
		});
	}

	private void extracted(JComboBox cbxCurso) {
		cbxCurso.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma op\u00E7\u00E3o"}));
	}
	
	private void extractedEmpty(JComboBox cbxDisciplina) {
		cbxDisciplina.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma disciplina"}));
	}
}
