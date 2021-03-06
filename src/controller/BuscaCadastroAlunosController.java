package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.Aluno;
import model.Beans.BuscaCadastroAlunosFiltro;
import model.DAO.AlunoDAO;
import model.controllerBean.ControllerAluno;
import util.Alerta;
import view.BuscaCadastroAlunosView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class BuscaCadastroAlunosController implements Initializable {
	
	public static int opt =0;

	
	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroAlunosFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroAlunosFiltro, Number> TableColumnCodigo;

	@FXML
	private TableColumn<BuscaCadastroAlunosFiltro, String> TableColumnNome, TableColumnStatus;

	@FXML
	private TableView<BuscaCadastroAlunosFiltro> TableViewBuscar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		TableColumnCodigo.setCellValueFactory(cellData -> cellData.getValue().CodigoProperty());
		TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().NomeProperty());
		TableColumnStatus.setCellValueFactory(cellData -> cellData.getValue().StatusProperty());

		try {
			carregaTabela();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (itens.size() > 0)
			TableViewBuscar.setItems(itens);
		itens.addListener(new ListChangeListener<BuscaCadastroAlunosFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroAlunosFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroAlunosFiltro> filteredData = new FilteredList<>(itens, p -> true);

		TextFieldFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
			TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			filteredData.setPredicate(person -> {

				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				return false;
			});
		});

		SortedList<BuscaCadastroAlunosFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {
					
					Alerta alerta = new Alerta("Busca", "Aluno n�o selecionado!");
					alerta.Alertar(BuscaCadastroAlunosView.myStage);
					
					return;
				}
				
				try {
					
					
					if (opt == 0){	
						CadastroAlunosController.setaluno( ControllerAluno.BuscaAluno(TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroAlunosController.tela.carregaInfo();
						}else if(opt == 1){
						CadastroMatriculaController.setaluno(ControllerAluno.BuscaAluno(TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroMatriculaController.tela.carregaInfoAluno();
						}
				
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
				BuscaCadastroAlunosView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroAlunosView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<Aluno> alunos = new AlunoDAO().getListar();
			for (int contador = 0; contador < alunos.size(); contador++) {
				BuscaCadastroAlunosFiltro tab = new BuscaCadastroAlunosFiltro(alunos.get(contador).getCodigo(), alunos.get(contador).getNome(), alunos.get(contador).getStatus());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(alunos.size()));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	


}
