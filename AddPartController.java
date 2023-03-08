package javeed.inventorymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    //Toggle group and radio buttons
    @FXML private ToggleGroup inOutTG;
    @FXML private RadioButton partInHouseRBtn;
    @FXML private RadioButton partOutsourcedRBtn;

    //Text fields and MachineId label
    @FXML private TextField partIdTxt;
    @FXML private TextField partNameTxt;
    @FXML private TextField partInvTxt;
    @FXML private TextField partPriceTxt;
    @FXML private TextField partMaxTxt;
    @FXML private TextField partMinTxt;
    @FXML private Label machineIdLabel;
    @FXML private TextField partMachineIdTxt;

    public boolean isInOrOut (){
        if (partInHouseRBtn.isSelected())
            return true;
        else return false;
    }
    @FXML void partInHouseRBtn(ActionEvent event) {
        machineIdLabel.setText("Machine Id");
    }
    @FXML void partOutsourcedRBtn(ActionEvent event) {
        machineIdLabel.setText("Company Name");
    }
    @FXML void onActionCancelAddPart(ActionEvent event) throws IOException {
        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");
    }

    @FXML void onActionSaveAddPart(ActionEvent event) throws IOException {
        int id = Integer.parseInt(partIdTxt.getText());
        String name = partNameTxt.getText();
        int stock = Integer.parseInt(partInvTxt.getText());
        double price = Double.parseDouble(partPriceTxt.getText());
        int max = Integer.parseInt(partMaxTxt.getText());
        int min = Integer.parseInt(partMinTxt.getText());
        String companyName = null;
        int machineId = 0;


        if (partOutsourcedRBtn.isSelected()) {
            companyName = partMachineIdTxt.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
        }
        else if (partInHouseRBtn.isSelected())
            machineId = Integer.parseInt(partMachineIdTxt.getText());
            Inventory.addPart(new InHouse(id,  name,  price,  stock,  min,  max,  machineId));


        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}