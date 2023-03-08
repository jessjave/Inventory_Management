package javeed.inventorymanagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;


public class MainScreenController implements Initializable {

    static Stage stage;
    static Parent scene;

    //Parts table view
    @FXML private TableView<Part> partTbl;
    @FXML private TableColumn<Part, Integer> partIdCol;
    @FXML private TableColumn<Part, String> partNameCol;
    @FXML private TableColumn<Part, Integer> partInvCol;
    @FXML private TableColumn<Part, Double> partPriceCol;
    @FXML private TextField partSearchTxt;

    //Products table view
    @FXML private TableView<Product> prodTbl;
    @FXML private TableColumn<Product, Integer> prodIdCol;
    @FXML private TableColumn<Product, String> prodNameCol;
    @FXML private TableColumn<Product, Integer> prodInvCol;
    @FXML private TableColumn<Product, Double> prodPriceCol;
    @FXML private TextField prodSearchTxt;

    //Method to transition to new screen
    public static void goToNextScreen(ActionEvent event, String screen) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(MainScreenController.class.getResource(screen));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //Part actions
    @FXML void onActionSearchPart(ActionEvent event) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchResult = observableArrayList();
        String query = partSearchTxt.getText().toLowerCase().trim();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(query) ||
                    part.getName().toLowerCase().contains(query)) {
                searchResult.add(part);
            }
        }
        partTbl.setItems(searchResult);
    }
    @FXML void onActionAddPart(ActionEvent event) throws IOException {
        goToNextScreen(event, "AddPartView.fxml");
    }
    @FXML void onActionModifyPart(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPartView.fxml"));
        scene = loader.load();
        Scene MainScreenView = new Scene(scene);
        ModifyPartController controller = loader.getController();
        controller.initData(partTbl.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainScreenView);
        window.show();

    }
    @FXML void onActionDeletePart(ActionEvent event) {
        ObservableList<Part> selectedPart;

        selectedPart = partTbl.getSelectionModel().getSelectedItems();
        for (Part part : selectedPart){
            Inventory.deletePart(part);
        }
    }

    //Product actions
    @FXML void onActionSearchProd(ActionEvent event) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> searchResult = observableArrayList();
        String query = prodSearchTxt.getText().toLowerCase().trim();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(query) ||
                    product.getName().toLowerCase().contains(query)) {
                searchResult.add(product);
            }
        }
        prodTbl.setItems(searchResult);
    }
    @FXML void onActionAddProd(ActionEvent event) throws IOException {
        goToNextScreen(event, "AddProductView.fxml");
    }
    @FXML void onActionModifyProd(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyProductView.fxml"));
        scene = loader.load();
        Scene MainScreenView = new Scene(scene);
        ModifyProductController controller = loader.getController();
        controller.initData(prodTbl.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainScreenView);
        window.show();
    }
    @FXML void onActionDeleteProd(ActionEvent event) {
        ObservableList<Product> selectedProd;

        selectedProd = prodTbl.getSelectionModel().getSelectedItems();
        for (Product product : selectedProd){
            Inventory.deleteProduct(product);
        }
    }

    //Closes the program
    @FXML void onActionExit(ActionEvent event) {
        System.exit(0);
    }


    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populates the parts table
        partTbl.setItems(Inventory.getAllParts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populates the products table
        prodTbl.setItems(Inventory.getAllProducts());
        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
