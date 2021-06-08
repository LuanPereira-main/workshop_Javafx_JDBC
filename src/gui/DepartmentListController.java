package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id")); /* determinar o comportamento da coluna (padrão de utilizar no javafx) */
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name")); /* determinar o comportamento da coluna (padrão de utilizar no javafx) */
		
		Stage stage = (Stage) Main.getMainScene().getWindow(); /* pegar as propriedades da window do stage atual da tela (obs: vale ressaltar que o stage não é a msm coisa que o scene) */
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); /* deixar o tableview do mesmo tamanho que o stage atual da tela (para acompanhar o tamanho) */
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null!");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
	}

}
