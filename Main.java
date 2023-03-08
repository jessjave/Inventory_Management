package javeed.inventorymanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainScreenView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Outsourced part1 = new Outsourced(1, "Bob", 100.00, 5, 1, 15, "HBO");
        Outsourced part3 = new Outsourced(2, "Bob", 100.00, 5, 1, 15, "HBO");
        InHouse part2 = new InHouse(3, "Chris", 100.00, 5, 1, 15, 50);
        InHouse part4 = new InHouse(4, "Pop", 100.00, 5, 1, 15, 50);
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);

        var product1 = new Product(6, "Toe", 10.50, 6, 10, 15);
        var product2 = new Product(7, "Fred", 10.50, 6, 10, 15);
        var product3 = new Product(8, "Sunny", 10.50, 6, 10, 15);
        var product4 = new Product(9, "Carlos", 10.50, 6, 10, 15);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch();


        var product = new Product(6, "Joe", 10.50, 6, 10, 15);
        Part newPart = new Outsourced(22, "James", 19.50, 4, 6, 10, "Poorly");
        System.out.println(newPart.getId());

        if (newPart instanceof Part)
            System.out.println("newPart is instance of Part");

        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("Min: " + product.getMin());
        System.out.println("Max: " + product.getMax());
    }
}