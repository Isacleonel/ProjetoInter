package controller;

import java.net.URL;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Beans.BuscaCadastroContaReceberFiltro;
import model.Beans.ContaReceber;
import model.DAO.ContaReceberDAO;
import model.controllerBean.ControllerContaReceber;
import util.Alerta;
import view.BuscaCadastroContaReceberView;
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



public class BuscaCadastroContaReceberController implements Initializable {
	
	public static int opt =0;

	
	@FXML
	private Button ButtonOk, ButtonCancelar;

	@FXML
	private TextField TextFieldFiltro, TextFieldTotal;

	private ObservableList<BuscaCadastroContaReceberFiltro> itens = FXCollections.observableArrayList();

	@FXML
	TableColumn<BuscaCadastroContaReceberFiltro, Number> TableColumnCodigo, TableColumnValor;

	@FXML
	private TableColumn<BuscaCadastroContaReceberFiltro, String> TableColumnNome, TableColumnServico, TableColumnVencimento, TableColumnSituacao;

	@FXML
	private TableView<BuscaCadastroContaReceberFiltro> TableViewBuscar;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		TableColumnCodigo.setCellValueFactory(cellData -> cellData.getValue().CodigoProperty());
		TableColumnNome.setCellValueFactory(cellData -> cellData.getValue().NomeProperty());
		TableColumnServico.setCellValueFactory(cellData -> cellData.getValue().ServicoProperty());
		//TableColumnVencimento.setCellValueFactory(cellData -> cellData.getValue().StatusProperty());
		TableColumnValor.setCellValueFactory(cellData -> cellData.getValue().ValorProperty());
		TableColumnSituacao.setCellValueFactory(cellData -> cellData.getValue().SituacaoProperty());

		try {
			carregaTabela();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (itens.size() > 0)
			TableViewBuscar.setItems(itens);
		itens.addListener(new ListChangeListener<BuscaCadastroContaReceberFiltro>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends BuscaCadastroContaReceberFiltro> arg0) {
				TextFieldTotal.setText(Integer.toString(TableViewBuscar.getItems().size()));
			}

		});

		FilteredList<BuscaCadastroContaReceberFiltro> filteredData = new FilteredList<>(itens, p -> true);

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

		SortedList<BuscaCadastroContaReceberFiltro> sortedData = new SortedList<>(filteredData);

		sortedData.comparatorProperty().bind(TableViewBuscar.comparatorProperty());

		TableViewBuscar.setItems(sortedData);

		ButtonOk.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (TableViewBuscar.getSelectionModel().getSelectedItems().size() < 1) {
					
					Alerta alerta = new Alerta("Busca", "Conta não selecionada!");
					alerta.Alertar(BuscaCadastroContaReceberView.myStage);
					
					return;
				}
				
				try {
					
					
					//if (opt == 0){	
						CadastroContasReceberController.setcontareceber(ControllerContaReceber.BuscaContaReceber(TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						CadastroContasReceberController.tela.carregaInfo();
						//}else if(opt == 1){
						//CadastroMatriculaController.setaluno(ControllerAluno.BuscaAluno(TableViewBuscar.getSelectionModel().getSelectedItems().get(0).getCodigo()));
						//CadastroMatriculaController.tela.carregaInfoAluno();
						//}
				
					
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
				BuscaCadastroContaReceberView.myStage.close();
			}

		});

		ButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				BuscaCadastroContaReceberView.myStage.close();
			}
		});

	}

	public void carregaTabela() throws ClassNotFoundException {
		try {
			itens.clear();
			List<ContaReceber> cr = new ContaReceberDAO().getListar();
			for (int contador = 0; contador < cr.size(); contador++) {
				BuscaCadastroContaReceberFiltro tab = new BuscaCadastroContaReceberFiltro(cr.get(contador).getCodigo(), cr.get(contador).getNomeAluno(), cr.get(contador).getServico(), cr.get(contador).getValor(), cr.get(contador).getSituacao());
				itens.add(tab);
			}
			TextFieldTotal.setText(Integer.toString(cr.size()));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	


}
