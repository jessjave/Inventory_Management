package javeed.inventorymanagement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;
    private ObservableList<Part> allParts = observableArrayList();
    private ObservableList<Part> associatedParts = observableArrayList();
    //Text fields
    @FXML private TextField addProdIdTxt;
    @FXML private TextField addProdNameTxt;
    @FXML private TextField addProdInvTxt;
    @FXML private TextField addProdPriceTxt;
    @FXML private TextField addProdMaxTxt;
    @FXML private TextField addProdMinTxt;

    //Top table view
    @FXML private TextField addProdSearchTxt;
    @FXML private TableView<Part> addProdTbl1;
    @FXML private TableColumn<Part, Integer> addProdPartIdCol1;
    @FXML private TableColumn<Part, String> addProdNameCol1;
    @FXML private TableColumn<Part, Integer> addProdInvCol1;
    @FXML private TableColumn<Part, Double> addProdPriceCol1;

    //Bottom table view
    @FXML private TableView<Part> addProdTbl2;
    @FXML private TableColumn<Part, Integer> addProdPartIdCol2;
    @FXML private TableColumn<Part, String> addProdNameCol2;
    @FXML private TableColumn<Part, Integer> addProdInvCol2;
    @FXML private TableColumn<Part, Integer> addProdPriceCol2;

    @FXML void onActionSearch(ActionEvent event) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchResult = observableArrayList();
        String query = addProdSearchTxt.getText().toLowerCase().trim();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(query) ||
                    part.getName().toLowerCase().contains(query)) {
                searchResult.add(part);
            }
        }
        addProdTbl1.setItems(searchResult);
    }
    @FXML void onActionAddProd(ActionEvent event) {
        ObservableList<Part> selectedPart;
        selectedPart = addProdTbl1.getSelectionModel().getSelectedItems();
        for (Part part : selectedPart)
            associatedParts.add(part);
        addProdTbl2.setItems(associatedParts);
    }

    @FXML void onActionCancelAddProd(ActionEvent event) throws IOException {
        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");
    }

    @FXML void onActionRemoveProd(ActionEvent event) {
        ObservableList<Part> selectedPart;
        selectedPart = addProdTbl2.getSelectionModel().getSelectedItems();
        for (Part part : selectedPart){
            associatedParts.remove(part);
            addProdTbl2.setItems(associatedParts);
        }
    }

    @FXML void onActionSaveAddProd(ActionEvent event) throws IOException {

        Product product = new Product(0, "", 0, 0, 0, 0);
        product.setName(this.addProdNameTxt.getText());
        product.setStock(Integer.parseInt(this.addProdInvTxt.getText()));
        product.setPrice(Double.parseDouble(this.addProdPriceTxt.getText()));
        product.setMax(Integer.parseInt(this.addProdMaxTxt.getText()));
        product.setMin(Integer.parseInt(this.addProdMinTxt.getText()));
        //product.addAssociatedPart(associatedParts);
        Inventory.addProduct(product);


        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");

    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populates the parts table
        addProdTbl1.setItems(Inventory.getAllParts());
        addProdPartIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProdNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProdInvCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProdPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populates the table with added parts
        addProdTbl2.setItems(associatedParts);
        addProdPartIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProdNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProdInvCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProdPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
