package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.BuscaCadastroModalidadeFiltro;
import model.Beans.Modalidade;
import model.DAO.ModalidadeDAO;
import model.controllerBean.ControllerModalidade;
import util.Alerta;
import view.BuscaCadastroModalidadeView;
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


public class BuscaCadastroModalidadeController implements Initializable {
	
	public static int opt = 0;

	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroModalidadeFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroModalidadeFiltro, Number> TableColumnCodigo;

	@FXML
	private TableColumn<BuscaCadastroModalidadeFiltro, String> TableColumnNome;

	@FXML
	private TableView<BuscaCadastroModalidadeFiltro> TableViewBuscar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		TableColumnCodigo.setCellValueFactory(cellData -> cellData.getValue().CodigoProperty());
		TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().NomeProperty());

		try {
			carregaTabela();
		} catch (ClassNotFoundException e1) {

			JOptionPane.showMessageDialog(null, "erro noc carrega tabela");
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		if (itens.size() > 0)
			TableViewBuscar.setItems(itens);
		itens.addListener(new ListChangeListener<BuscaCadastroModalidadeFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroModalidadeFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroModalidadeFiltro> filteredData = new FilteredList<>(itens, p -> true);

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

		SortedList<BuscaCadastroModalidadeFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {

					Alerta alerta = new Alerta("Busca", "Modalidade não selecionada!");
					alerta.Alertar(BuscaCadastroModalidadeView.myStage);

					return;
				}

				try {

					if (opt == 0) {
						CadastroModalidadeController.setmodalidade(ControllerModalidade.BuscaModalidade(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroModalidadeController.tela.carregaInfo();
					} else if (opt == 1) {
						CadastroProfessorController.setmodalidade(ControllerModalidade.BuscaModalidade(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroProfessorController.tela.carregaInfoModalidade();
					}else if (opt == 2) {
						CadastroMatriculaController.setmodalidade(ControllerModalidade.BuscaModalidade(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroMatriculaController.tela.carregaInfoModalidade();
					}

				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
				BuscaCadastroModalidadeView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroModalidadeView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<Modalidade> mod = new ModalidadeDAO().getListar();
			for (int contador = 0; contador < mod.size(); contador++) {
				BuscaCadastroModalidadeFiltro tab = new BuscaCadastroModalidadeFiltro(mod.get(contador).getCodigo(),
						mod.get(contador).getNome());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(mod.size()));
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "erro aqui");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
