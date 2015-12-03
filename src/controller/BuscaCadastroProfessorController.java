package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.BuscaCadastroProfessorFiltro;
import model.Beans.Professor;
import model.DAO.ProfessorDAO;
import model.controllerBean.ControllerProfessor;
import util.Alerta;
import view.BuscaCadastroProfessorView;
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


public class BuscaCadastroProfessorController implements Initializable {
	
	public static int opt = 0;

	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroProfessorFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroProfessorFiltro, Number> TableColumnCodigo;

	@FXML
	private TableColumn<BuscaCadastroProfessorFiltro, String> TableColumnNome;

	@FXML
	private TableView<BuscaCadastroProfessorFiltro> TableViewBuscar;

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
		itens.addListener(new ListChangeListener<BuscaCadastroProfessorFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroProfessorFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroProfessorFiltro> filteredData = new FilteredList<>(itens, p -> true);

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

		SortedList<BuscaCadastroProfessorFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {

					Alerta alerta = new Alerta("Busca", "Professor não selecionado!");
					alerta.Alertar(BuscaCadastroProfessorView.myStage);

					return;
				}

				try {

					if (opt == 0){
					CadastroProfessorController.setprofessor(ControllerProfessor.BuscaProfessor(TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
					CadastroProfessorController.tela.carregaInfo();
					}else if(opt == 1){
					CadastroMatriculaController.setprofessor(ControllerProfessor.BuscaProfessor(TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
					CadastroMatriculaController.tela.carregaInfoProfessor();
					}

				} catch (ClassNotFoundException | SQLException e1) {

					e1.printStackTrace();
				}
				BuscaCadastroProfessorView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroProfessorView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<Professor> pro = new ProfessorDAO().getListar();
			for (int contador = 0; contador < pro.size(); contador++) {
				BuscaCadastroProfessorFiltro tab = new BuscaCadastroProfessorFiltro(pro.get(contador).getCodigo(),
						pro.get(contador).getNome());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(pro.size()));
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "erro aqui");
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
