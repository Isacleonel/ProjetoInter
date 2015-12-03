package controller;

import java.io.File;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import model.Beans.Aluno;
import model.Beans.Cidade;
import model.controllerBean.ControllerAluno;
import util.Alerta;
import util.Convert;
import util.Mascara;
import view.BuscaCadastroAlunosView;
import view.BuscaCadastroCidadeView;
import view.CadastroAlunosView;
import view.CadastroMatriculaView;
import view.PrincipalView;
import model.Beans.Matricula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class CadastroAlunosController implements Initializable {
	
	public static CadastroAlunosController tela;
	public static Aluno alu;
	public static Cidade cid;
	public static Matricula mat;

	private Scene scene;

	private FileChooser fileChooser = new FileChooser();

	@FXML
	private TextField TextFieldCodigo, TextFieldNome, TextFieldCpf, TextFieldIdentidade, TextFieldProfissao,
			TextFieldNomeCid, TextFieldCodigoCid, TextFieldNomeEst, TextFieldBairro, TextFieldEndereco,
			TextFieldCelular, TextFieldFone, TextFieldEmail;

	@FXML
	private ComboBox<String> ComboBoxSexo, ComboBoxStatus;

	ObservableList<String> listSexo = FXCollections.observableArrayList("Masculino", "Feminino");
	ObservableList<String> listStatus = FXCollections.observableArrayList("Ativo", "Inativo");

	@FXML
	private DatePicker DatePickerNascimento, DatePickerCadastro, DatePickerCancelamento;

	@FXML
	private Button ButtonSalvar, ButtonBuscar, ButtonCancelar, ButtonNovo, ButtonModificar, ButtonExcluir, ButtonFechar,
			ButtonAdd, ButtonDel, ButtonBuscarCid;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	
		
		CadastroAlunosController.tela = this;

		ComboBoxSexo.setItems(listSexo);
		ComboBoxStatus.setItems(listStatus);

		
		
		try {
			
			if(ControllerAluno.verificaTabela()==true){
			
			this.carregaInfo(ControllerAluno.BuscarUltimoID());
			
			}else{
				
				Alerta alerta = new Alerta(null, "Nenhum Aluno cadastrado");
				alerta.Mensagem(PrincipalView.myStage);
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}

		Mascara.colocaBarraData(DatePickerNascimento);
		Mascara.colocaBarraData(DatePickerCadastro);
		Mascara.colocaBarraData(DatePickerCancelamento);

		this.DisableCampos(true);

		TextFieldCodigo.setDisable(true);
		ButtonSalvar.setDisable(true);
		ButtonCancelar.setDisable(true);
		TextFieldCodigoCid.setEditable(false);
		TextFieldNomeCid.setEditable(false);
		TextFieldNomeEst.setEditable(false);

		fileChooser.setTitle("Selecione uma Imagem");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

		ButtonBuscarCid.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroCidadeView view = new BuscaCadastroCidadeView();
				view.initBuscaCad(scene);
				BuscaCadastroCidadeController.opt = 2;
			}
		});

		ButtonNovo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent g) {

				LimpaCampos();

				DisableCampos(false);

				ButtonNovo.setDisable(true);
				ButtonSalvar.setDisable(false);
				ButtonModificar.setDisable(true);
				ButtonCancelar.setDisable(false);
				ButtonExcluir.setDisable(true);
				ButtonBuscar.setDisable(true);

			}
		});

		ButtonSalvar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent d) {

				Alerta alerta = new Alerta("Salvar", "Deseja salvar?");

				if (alerta.Confirm(CadastroAlunosView.myStage)) {

					if (TextFieldNome.getText() == null || TextFieldNome.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Nome não preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;
					} else if (ComboBoxSexo.getValue() == null || ComboBoxSexo.getValue().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Sexo não preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;
					} else if (DatePickerNascimento.getValue() == null) {
						Alerta alert = new Alerta(null, "Campo Data de Nascimento não preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;
					} else if (TextFieldCpf.getText() == null || TextFieldCpf.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo CPF não preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;
					} else if (TextFieldNomeCid.getText() == null || TextFieldNomeCid.getText().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Cidade não preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;
					} else if (DatePickerCadastro.getValue() == null) {
						Alerta alert = new Alerta(null, "Campo Data de cadastro não preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;
					} else if (ComboBoxStatus.getValue() == null || ComboBoxStatus.getValue().isEmpty()) {
						Alerta alert = new Alerta(null, "Campo Status deve ser preenchido!");
						alert.Alertar(CadastroAlunosView.myStage);
						return;

					} else {

						try {
							Aluno alu = new Aluno();

							alu.setNome(TextFieldNome.getText());
							alu.setSexo(ComboBoxSexo.getValue().toString());
							alu.setNascimento(Convert.toDate(DatePickerNascimento.getValue()));
							alu.setCpf(TextFieldCpf.getText());
							alu.setIdentidade(TextFieldIdentidade.getText());
							alu.setProfissao(TextFieldProfissao.getText());
							alu.setCidade(Integer.parseInt(TextFieldCodigoCid.getText()));
							alu.setBairro(TextFieldBairro.getText());
							alu.setEndereco(TextFieldEndereco.getText());
							alu.setCelular(TextFieldCelular.getText());
							alu.setFone(TextFieldFone.getText());
							alu.setEmail(TextFieldEmail.getText());
							alu.setCadastro(Convert.toDate(DatePickerCadastro.getValue()));
							alu.setStatus(ComboBoxStatus.getValue().toString());
							if (DatePickerCancelamento.getValue() != null) {
								alu.setCancelamento(Convert.toDate(DatePickerCancelamento.getValue()));
							}

							if (TextFieldCodigo.getText() == null) {
								if (ControllerAluno.salvarAluno(alu) == true) {
									return;
								}
								carregaInfo(ControllerAluno.BuscarUltimoID());

							} else {
								alu.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
								if (ControllerAluno.UpdateAluno(alu) == true) {
									return;
								}
								;

							}

						} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}

					DisableCampos(true);
					ButtonNovo.setDisable(false);
					ButtonSalvar.setDisable(true);
					ButtonModificar.setDisable(false);
					ButtonCancelar.setDisable(true);
					ButtonExcluir.setDisable(false);
					ButtonBuscar.setDisable(false);

					
					Alerta alert = new Alerta(null, "Deseja cadastrar a Matricula do Aluno?");
					if (alert.Confirm(CadastroAlunosView.myStage)) {
						CadastroMatriculaView view = new CadastroMatriculaView();
						view.initMatricula(scene);
						
					}
					
					
					
					
				}
			}
		});

		ButtonModificar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				DisableCampos(false);

				ButtonNovo.setDisable(true);
				ButtonSalvar.setDisable(false);
				ButtonModificar.setDisable(true);
				ButtonCancelar.setDisable(false);
				ButtonExcluir.setDisable(true);
				ButtonBuscar.setDisable(true);

			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				try {
					if(ControllerAluno.verificaTabela()==true){
					carregaInfo(ControllerAluno.BuscarUltimoID());
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

				DisableCampos(true);

				ButtonNovo.setDisable(false);
				ButtonSalvar.setDisable(true);
				ButtonModificar.setDisable(false);
				ButtonCancelar.setDisable(true);
				ButtonExcluir.setDisable(false);
				ButtonBuscar.setDisable(false);

			}
		});

		ButtonExcluir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Alerta alerta = new Alerta("Excluir", "Confirma exclusão?");

				if (alerta.Confirm(CadastroAlunosView.myStage)) {

					
					
					try {
						
						
						Aluno aluno = new Aluno();
						aluno.setCodigo(Integer.parseInt(TextFieldCodigo.getText()));
						if (ControllerAluno.verificaAlunoMatricula(aluno) == true) {
							return;
						}
						
						

						ControllerAluno.ExcluirAluno(TextFieldCodigo.getText());

						if(ControllerAluno.verificaTabela()==true){
						carregaInfo(ControllerAluno.BuscarUltimoID());
						}
						
						ButtonNovo.setDisable(false);
						ButtonSalvar.setDisable(true);
						ButtonModificar.setDisable(false);
						ButtonCancelar.setDisable(true);
						ButtonExcluir.setDisable(false);
						ButtonBuscar.setDisable(false);

					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		ButtonBuscar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroAlunosView view = new BuscaCadastroAlunosView();
				view.initBuscaCad(scene);
				BuscaCadastroAlunosController.opt = 0;
			}
		});

		ButtonFechar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				CadastroAlunosView.myStage.close();
			}
		});

		ButtonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				File file = fileChooser.showOpenDialog(CadastroAlunosView.myStage);

				if (file != null) {

					try {

						copy(file, new File("C:\\" + file.getName()));

					} catch (IOException e1) {

						e1.printStackTrace();
					}

				}

			}
		});

	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public void carregaInfo(int ID) throws ClassNotFoundException, SQLException {
		Aluno a = ControllerAluno.BuscaAluno(ID);
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		ComboBoxSexo.setValue(a.getSexo() + "");
		DatePickerNascimento.setValue(Convert.toLocalDate(a.getNascimento()));
		TextFieldCpf.setText(a.getCpf() + "");
		TextFieldIdentidade.setText(a.getIdentidade() + "");
		TextFieldProfissao.setText(a.getProfissao() + "");
		TextFieldCodigoCid.setText(a.getCidade() + "");
		TextFieldNomeCid.setText(a.getNomeCidade() + "");
		TextFieldNomeEst.setText(a.getEstado() + "");
		TextFieldBairro.setText(a.getBairro() + "");
		TextFieldEndereco.setText(a.getEndereco() + "");
		TextFieldCelular.setText(a.getCelular() + "");
		TextFieldFone.setText(a.getFone() + "");
		TextFieldEmail.setText(a.getEmail() + "");
		DatePickerCadastro.setValue(Convert.toLocalDate(a.getCadastro()));
		ComboBoxStatus.setValue(a.getStatus() + "");
		if (a.getCancelamento() != null) {
			DatePickerCancelamento.setValue(Convert.toLocalDate(a.getCancelamento()));
		} else {
			DatePickerCancelamento.setValue(null);
		}
	};

	public void carregaInfo() throws ClassNotFoundException, SQLException {

		DisableCampos(true);

		Aluno a = CadastroAlunosController.alu;
		TextFieldCodigo.setText(a.getCodigo() + "");
		TextFieldNome.setText(a.getNome() + "");
		ComboBoxSexo.setValue(a.getSexo() + "");
		DatePickerNascimento.setValue(Convert.toLocalDate(a.getNascimento()));
		TextFieldCpf.setText(a.getCpf() + "");
		TextFieldIdentidade.setText(a.getIdentidade() + "");
		TextFieldProfissao.setText(a.getProfissao() + "");
		TextFieldCodigoCid.setText(a.getCidade() + "");
		TextFieldNomeCid.setText(a.getNomeCidade() + "");
		TextFieldNomeEst.setText(a.getEstado() + "");
		TextFieldBairro.setText(a.getBairro() + "");
		TextFieldEndereco.setText(a.getEndereco() + "");
		TextFieldCelular.setText(a.getCelular() + "");
		TextFieldFone.setText(a.getFone() + "");
		TextFieldEmail.setText(a.getEmail() + "");
		DatePickerCadastro.setValue(Convert.toLocalDate(a.getCadastro()));
		ComboBoxStatus.setValue(a.getStatus() + "");
		if (a.getCancelamento() != null) {
			DatePickerCancelamento.setValue(Convert.toLocalDate(a.getCancelamento()));
		} else {
			DatePickerCancelamento.setValue(null);
		}
	};

	public void DisableCampos(Boolean statusCampos) {

		TextFieldNome.setDisable(statusCampos);
		ComboBoxSexo.setDisable(statusCampos);
		DatePickerNascimento.setDisable(statusCampos);
		TextFieldCpf.setDisable(statusCampos);
		TextFieldIdentidade.setDisable(statusCampos);
		TextFieldProfissao.setDisable(statusCampos);
		TextFieldCodigoCid.setDisable(statusCampos);
		ButtonBuscarCid.setDisable(statusCampos);
		TextFieldNomeCid.setDisable(statusCampos);
		TextFieldNomeEst.setDisable(statusCampos);
		TextFieldBairro.setDisable(statusCampos);
		TextFieldEndereco.setDisable(statusCampos);
		TextFieldCelular.setDisable(statusCampos);
		TextFieldFone.setDisable(statusCampos);
		TextFieldEmail.setDisable(statusCampos);
		DatePickerCadastro.setDisable(statusCampos);
		ComboBoxStatus.setDisable(statusCampos);
		DatePickerCancelamento.setDisable(statusCampos);
		ButtonAdd.setDisable(statusCampos);
		ButtonDel.setDisable(statusCampos);
	};

	public void LimpaCampos() {
		TextFieldCodigo.setText(null);
		TextFieldNome.setText(null);
		ComboBoxSexo.setValue(null);
		DatePickerNascimento.setValue(null);
		TextFieldCpf.setText(null);
		TextFieldIdentidade.setText(null);
		TextFieldProfissao.setText(null);
		TextFieldCodigoCid.setText(null);
		TextFieldNomeCid.setText(null);
		TextFieldNomeEst.setText(null);
		TextFieldBairro.setText(null);
		TextFieldEndereco.setText(null);
		TextFieldCelular.setText(null);
		TextFieldFone.setText(null);
		TextFieldEmail.setText(null);
		DatePickerCadastro.setValue(null);
		ComboBoxStatus.setValue(null);
		DatePickerCancelamento.setValue(null);
	};

	public void carregaInfoCidade() throws ClassNotFoundException, SQLException {

		TextFieldCodigoCid.setText(cid.getCodigo() + "");
		TextFieldNomeCid.setText(cid.getNome() + "");
		TextFieldNomeEst.setText(cid.getNomeEstado() + "");

	}

	public static void setaluno(Aluno aluno) throws ClassNotFoundException, SQLException {

		alu = aluno;
	};

	public static void setcidade(Cidade ci) throws ClassNotFoundException, SQLException {
		cid = ci;
	};

	public void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);
		byte[] buf = new byte[10240];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}
	
	
	
	

}