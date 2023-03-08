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

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;
    private Product selectedProduct;
    private ObservableList<Part> allParts = observableArrayList();
    private ObservableList<Part> associatedParts = observableArrayList();
    //Top table view
    @FXML private TextField modProdSearchTxt;
    @FXML private TableView<Part> modProdTbl1;
    @FXML private TableColumn<Part, Integer> modProdIdCol1;
    @FXML private TableColumn<Part, String> modProdNameCol1;
    @FXML private TableColumn<Part, Integer> modProdInvCol1;
    @FXML private TableColumn<Part, Double> modProdPriceCol1;

    //Bottom table view
    @FXML private TableView<Part> modProdTbl2;
    @FXML private TableColumn<Part, Integer> modProdIdCol2;
    @FXML private TableColumn<Part, String> modProdNameCol2;
    @FXML private TableColumn<Part, Integer> modProdInvCol2;
    @FXML private TableColumn<Part, Double> modProdPriceCol2;

    //Text fields
    @FXML private TextField modProdIdTxt;
    @FXML private TextField modProdNameTxt;
    @FXML private TextField modProdInvTxt;
    @FXML private TextField modProdPriceTxt;
    @FXML private TextField modProdMaxTxt;
    @FXML private TextField modProdMinTxt;

    public void initData(Product product){
        selectedProduct = product;
        modProdIdTxt.setText(String.valueOf(selectedProduct.getId()));
        modProdNameTxt.setText(selectedProduct.getName());
        modProdPriceTxt.setText(String.valueOf(selectedProduct.getPrice()));
        modProdInvTxt.setText(String.valueOf(selectedProduct.getStock()));
        modProdMaxTxt.setText(String.valueOf(selectedProduct.getMax()));
        modProdMinTxt.setText(String.valueOf(selectedProduct.getMin()));
    }
    @FXML void onActionSearch(ActionEvent event) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchResult = observableArrayList();
        String query = modProdSearchTxt.getText().toLowerCase().trim();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(query) ||
                    part.getName().toLowerCase().contains(query)) {
                searchResult.add(part);
            }
        }
        modProdTbl1.setItems(searchResult);
    }
    @FXML void onActionAddModProd(ActionEvent event) {
        ObservableList<Part> selectedPart;
        selectedPart = modProdTbl1.getSelectionModel().getSelectedItems();
        for (Part part : selectedPart)
            associatedParts.add(part);
        modProdTbl2.setItems(associatedParts);
    }
    @FXML void onActionRemovePart(ActionEvent event) {
        ObservableList<Part> selectedPart;
        selectedPart = modProdTbl2.getSelectionModel().getSelectedItems();
        for (Part part : selectedPart){
            associatedParts.remove(part);
            modProdTbl2.setItems(associatedParts);
        }
    }
    @FXML void onActionCancelModProd(ActionEvent event) throws IOException {
        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");
    }

    @FXML void onActionSaveModProd(ActionEvent event) throws IOException {

        Product product = new Product(0, "", 0, 0, 0, 0);
        product.setName(this.modProdIdTxt.getText());
        product.setStock(Integer.parseInt(this.modProdInvTxt.getText()));
        product.setPrice(Double.parseDouble(this.modProdPriceTxt.getText()));
        product.setMax(Integer.parseInt(this.modProdMaxTxt.getText()));
        product.setMin(Integer.parseInt(this.modProdMinTxt.getText()));
        //product.addAssociatedPart(associatedParts);
        Inventory.addProduct(product);

        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        //Populates the parts table
        allParts = Inventory.getAllParts();
        modProdTbl1.setItems(allParts);
        modProdIdCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdNameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdInvCol1.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProdPriceCol1.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populates the table with added parts
        modProdTbl2.setItems(associatedParts);
        modProdIdCol2.setCellValueFactory(new PropertyValueFactory<>("id"));
        modProdNameCol2.setCellValueFactory(new PropertyValueFactory<>("name"));
        modProdInvCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modProdPriceCol2.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
