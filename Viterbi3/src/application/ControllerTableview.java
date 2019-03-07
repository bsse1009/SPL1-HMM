package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class ControllerTableview  implements  Initializable{
	
	@FXML private TableView<application.TableView>tableview;
	@FXML private TableColumn<application.TableView, String> state1;
	@FXML private TableColumn<application.TableView, String> state2;
	@FXML private TableColumn<application.TableView, Double> probability;
	Controller c;
	//double start_p [] = new double [100];				//** store starting probability of state
    static double  trans_p[][] = new double [100][100];		//** store transition table
	
	@FXML private TextField s1;
	@FXML private TextField s2;
	@FXML private TextField p;
	
	@FXML
	public void previousWindow(ActionEvent event) throws IOException
	{
		Parent parent1 = FXMLLoader.load(getClass().getResource("Sample1.fxml"));
    	Scene scene1 = new Scene (parent1);
    	
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene1);
    	window.show();
	}

	@FXML
	public void NextWindow(ActionEvent event) throws IOException
	{
		Parent parent1 = FXMLLoader.load(getClass().getResource("Samole3.fxml"));
    	Scene scene1 = new Scene (parent1);
    	
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene1);
    	window.show();
	}
	
	@FXML
	public void ChangedCell1 (CellEditEvent edittedCell)
	{
		application.TableView collSelected  = tableview.getSelectionModel().getSelectedItem();
		collSelected.setState1(edittedCell.getNewValue().toString());
	}
	
	@FXML
	public void ChangedCell2 (CellEditEvent edittedCell)
	{
		application.TableView collSelected  = tableview.getSelectionModel().getSelectedItem();
		collSelected.setState2(edittedCell.getNewValue().toString());
	}

	@FXML
	public void ChangedCell3 (CellEditEvent edittedCell)
	{
		application.TableView collSelected  = tableview.getSelectionModel().getSelectedItem();
		collSelected.setProbability(Double.valueOf((String) edittedCell.getNewValue()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		state1.setCellValueFactory(new PropertyValueFactory<application.TableView,String>("state1"));
		state2.setCellValueFactory(new PropertyValueFactory<application.TableView,String>("state2"));
		probability.setCellValueFactory(new PropertyValueFactory<application.TableView,Double>("probability"));
		
		//tableview.setItems(getTable());
		tableview.setEditable(true);
		state1.setCellFactory(TextFieldTableCell.forTableColumn());
		state2.setCellFactory(TextFieldTableCell.forTableColumn());
		//probability.setCellFactory(TextFieldTableCell.forTableColumn().toString());
		
	}
	
	@FXML
	public void newRowPushed(ActionEvent event)
	{
		application.TableView addRow = new application.TableView (s1.getText(),s2.getText(),Double.valueOf(p.getText()));
		
		tableview.getItems().add(addRow);
		
		c = new Controller();
		ArrayList<String> states = new ArrayList <String> ();
		
		states = c.getList();
		
		int i = states.indexOf(s1.getText());
		int j = states.indexOf(s2.getText());
		//trans_p.add(Double.valueOf(p.getText()));
		trans_p [i][j] = Double.valueOf(p.getText());
		//System.out.println(trans_p[0][0]);
		s1.setText("");
		s2.setText("");
		p.setText("");
	}
	/*
	public ObservableList<application.TableView> getTable()
	{
		ObservableList<application.TableView> table = FXCollections.observableArrayList();
		
		c = new Controller();
		ArrayList<String> states = new ArrayList <String> ();
		
		states = c.getList();
		
		for (String s1 : states)
		{
			for (String s2 : states)
			{
				table.add (new application.TableView(s1,s2, 0.0));
			}
			//System.out.println(table);
		}
		
		//table.add (new application.TableView("rr","ss", 0.0));

		return table;
	}
	*/
	
}
