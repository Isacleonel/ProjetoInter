package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.BuscaCadastroEstadoFiltro;
import model.Beans.Estado;
import model.DAO.EstadoDAO;
import model.controllerBean.ControllerEstado;
import util.Alerta;
import view.BuscaCadastroEstadoView;
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


public class BuscaCadastroEstadoController implements Initializable {
	
	public static int opt = 0;

	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroEstadoFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroEstadoFiltro, Number> TableColumnCodigo;

	@FXML
	private TableColumn<BuscaCadastroEstadoFiltro, String> TableColumnNome;

	@FXML
	private TableView<BuscaCadastroEstadoFiltro> TableViewBuscar;

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
		itens.addListener(new ListChangeListener<BuscaCadastroEstadoFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroEstadoFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroEstadoFiltro> filteredData = new FilteredList<>(itens, p -> true);

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

		SortedList<BuscaCadastroEstadoFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {

					Alerta alerta = new Alerta("Busca", "Estado não selecionado!");
					alerta.Alertar(BuscaCadastroEstadoView.myStage);

					return;
				}

				try {

					if (opt == 0) {
						CadastroEstadoController.setestado(ControllerEstado.BuscaEstado(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroEstadoController.tela.carregaInfo();
					} else if (opt == 1) {
						CadastroCidadeController.setestado(ControllerEstado.BuscaEstado(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroCidadeController.tela.carregaInfoEstado();
					}

				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
				BuscaCadastroEstadoView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroEstadoView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<Estado> est = new EstadoDAO().getListar();
			for (int contador = 0; contador < est.size(); contador++) {
				BuscaCadastroEstadoFiltro tab = new BuscaCadastroEstadoFiltro(est.get(contador).getCodigo(),
						est.get(contador).getNome());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(est.size()));
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "erro aqui");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
