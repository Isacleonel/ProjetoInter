package controller;

import java.net.URL;


import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.BuscaCadastroCidadeFiltro;
import model.Beans.Cidade;
import model.DAO.CidadeDAO;
import model.controllerBean.ControllerCidade;
import util.Alerta;
import view.BuscaCadastroCidadeView;
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


public class BuscaCadastroCidadeController implements Initializable {

	public static int opt = 0;

	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroCidadeFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroCidadeFiltro, Number> TableColumnCodigo;

	@FXML
	private TableColumn<BuscaCadastroCidadeFiltro, String> TableColumnNome, TableColumnEstado;

	@FXML
	private TableView<BuscaCadastroCidadeFiltro> TableViewBuscar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		TableColumnCodigo.setCellValueFactory(cellData -> cellData.getValue().CodigoProperty());
		TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().NomeProperty());
		TableColumnEstado.setCellValueFactory(cellData -> cellData.getValue().EstadoProperty());

		try {
			carregaTabela();
		} catch (ClassNotFoundException e1) {

			JOptionPane.showMessageDialog(null, "erro no carrega tabela");
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		if (itens.size() > 0)
			TableViewBuscar.setItems(itens);
		itens.addListener(new ListChangeListener<BuscaCadastroCidadeFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroCidadeFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroCidadeFiltro> filteredData = new FilteredList<>(itens, p -> true);

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

		SortedList<BuscaCadastroCidadeFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {

					
						Alerta alerta = new Alerta("Busca", "Cidade não selecionada!");
						alerta.Alertar(BuscaCadastroCidadeView.myStage);
						return;
					
				}

				try {

					if (opt == 0) {
						CadastroCidadeController.setcidade(ControllerCidade.BuscaCidade(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroCidadeController.tela.carregaInfo();
					} else if (opt == 1) {
						CadastroProfessorController.setcidade(ControllerCidade.BuscaCidade(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroProfessorController.tela.carregaInfoCidade();
					} else if (opt == 2) {
						CadastroAlunosController.setcidade(ControllerCidade.BuscaCidade(
								TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroAlunosController.tela.carregaInfoCidade();
					}

				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
				BuscaCadastroCidadeView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroCidadeView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<Cidade> cid = new CidadeDAO().getListar();
			for (int contador = 0; contador < cid.size(); contador++) {
				BuscaCadastroCidadeFiltro tab = new BuscaCadastroCidadeFiltro(cid.get(contador).getCodigo(),cid.get(contador).getNome(),cid.get(contador).getNomeEstado());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(cid.size()));
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "erro aqui");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
