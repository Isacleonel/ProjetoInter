package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.BuscaCadastroMatriculaFiltro;
import model.Beans.Matricula;
import model.DAO.MatriculaDAO;
import model.controllerBean.ControllerMatricula;
import util.Alerta;
import view.BuscaCadastroMatriculaView;
import view.PrincipalView;
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

public class BuscaCadastroMatriculaController implements Initializable {

	public static int opt = 0;

	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroMatriculaFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroMatriculaFiltro, Number> TableColumnCodigo;

	@FXML
	private TableColumn<BuscaCadastroMatriculaFiltro, String> TableColumnNome;

	@FXML
	private TableView<BuscaCadastroMatriculaFiltro> TableViewBuscar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		TableColumnCodigo.setCellValueFactory(cellData -> cellData.getValue().CodigoProperty());
		TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().NomeProperty());

		try {
			carregaTabela();
		} catch (ClassNotFoundException e1) {

			JOptionPane.showMessageDialog(null, "erro no carrega tabela");
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		if (itens.size() > 0)
			TableViewBuscar.setItems(itens);
		itens.addListener(new ListChangeListener<BuscaCadastroMatriculaFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroMatriculaFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroMatriculaFiltro> filteredData = new FilteredList<>(itens, p -> true);

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

		SortedList<BuscaCadastroMatriculaFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {

					Alerta alerta = new Alerta("Busca", "Matricula do Aluno não selecionada!");
					alerta.Alertar(BuscaCadastroMatriculaView.myStage);

					return;
				}

				try {

					if (opt == 0) {
						CadastroMatriculaController.setmatricula(ControllerMatricula.BuscaMatricula(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroMatriculaController.tela.carregaInfo();
					} else if (opt == 1) {

						Alerta alerta = new Alerta(null, "É conta referente a mensalidade?");
						if (alerta.Confirm(BuscaCadastroMatriculaView.myStage)) {

							CadastroContasReceberController.setmatricula(ControllerMatricula.BuscaMatricula(
									TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
							CadastroContasReceberController.tela.carregaInfoMatriculaMensal();

						} else {

							CadastroContasReceberController.setmatricula(ControllerMatricula.BuscaMatricula(
									TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
							CadastroContasReceberController.tela.carregaInfoMatricula();

						}

					}
				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
				BuscaCadastroMatriculaView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroMatriculaView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<Matricula> mat = new MatriculaDAO().getListar();
			for (int contador = 0; contador < mat.size(); contador++) {
				BuscaCadastroMatriculaFiltro tab = new BuscaCadastroMatriculaFiltro(mat.get(contador).getCodigo(),
						mat.get(contador).getNomeAluno());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(mat.size()));
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "erro aqui");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
