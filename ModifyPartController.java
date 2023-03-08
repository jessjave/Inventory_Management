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

public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    private int partIndex;
    private Part selectedPart;
    private InHouse selectedInHouse;
    private Outsourced selectedOutsourced;
    @FXML private Label machineIdLbl;
    @FXML private ToggleGroup inOutTG;
    @FXML private RadioButton modPartInHouseRBtn;
    @FXML private RadioButton modPartOutsourcedRBtn;
    @FXML private TextField modPartIdTxt;
    @FXML private TextField modPartNameTxt;
    @FXML private TextField modPartPriceTxt;
    @FXML private TextField modPartInvTxt;
    @FXML private TextField modPartMaxTxt;
    @FXML private TextField modPartMinTxt;
    @FXML private TextField modPartMachineIdTxt;

    public void initData(Part part){
        selectedPart = part;
        modPartIdTxt.setText(String.valueOf(selectedPart.getId()));
        modPartNameTxt.setText(selectedPart.getName());
        modPartPriceTxt.setText(String.valueOf(selectedPart.getPrice()));
        modPartInvTxt.setText(String.valueOf(selectedPart.getStock()));
        modPartMaxTxt.setText(String.valueOf(selectedPart.getMax()));
        modPartMinTxt.setText(String.valueOf(selectedPart.getMin()));
        partIndex = Inventory.getAllParts().indexOf(selectedPart);

        if (part instanceof Outsourced){
            machineIdLbl.setText("Company Name");
            selectedOutsourced = (Outsourced) part;
            modPartOutsourcedRBtn.setSelected(true);
            modPartMachineIdTxt.setText(String.valueOf(selectedOutsourced.getCompanyName()));
        }
        else if (part instanceof InHouse){
            selectedInHouse = (InHouse) part;
            modPartInHouseRBtn.setSelected(true);
            modPartMachineIdTxt.setText(String.valueOf(selectedInHouse.getMachineId()));
        }

    }
    @FXML void onActionInHouseRBtn(ActionEvent event) {
        machineIdLbl.setText("Machine Id");
    }

    @FXML void onActionOutsourcedRBtn(ActionEvent event) {
        machineIdLbl.setText("Company Name");
    }
    @FXML void onActionCancelModPart(ActionEvent event) throws IOException {
        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");
    }

    @FXML void onActionSaveModPart(ActionEvent event) throws IOException {
        int position = Inventory.getAllParts().indexOf(selectedPart);
        Inventory.updatePart(position, selectedPart);

        int id = Integer.parseInt(modPartIdTxt.getText());
        String name = modPartNameTxt.getText();
        int stock = Integer.parseInt(modPartInvTxt.getText());
        double price = Double.parseDouble(modPartPriceTxt.getText());
        int max = Integer.parseInt(modPartMaxTxt.getText());
        int min = Integer.parseInt(modPartMinTxt.getText());
        String companyName = null;
        int machineId = 0;

        if (modPartOutsourcedRBtn.isSelected()) {
            companyName = modPartMachineIdTxt.getText();
            Outsourced temp = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.updatePart(partIndex, temp);
        }
        else if (modPartInHouseRBtn.isSelected()) {
            machineId = Integer.parseInt(modPartMachineIdTxt.getText());
            InHouse temp = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.updatePart(partIndex, temp);
        }
        MainScreenController.goToNextScreen(event, "MainScreenView.fxml");
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
