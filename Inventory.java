package javeed.inventorymanagement;
import javafx.collections.ObservableList;
import static javafx.collections.FXCollections.observableArrayList;

public class Inventory {

    private static ObservableList<Part> allParts = observableArrayList();
    private static ObservableList<Product> allProducts = observableArrayList();

    public static void addPart(Part newPart) {
        if (newPart != null)
            allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        if (newProduct != null)
            allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        Part partSearch = null;
            for (Part part : allParts) {
                if (part.getId() == partId)
                    partSearch = part;
            }
        return partSearch;
    }
    public static Product lookupProduct(int productId) {
        Product productSearch = null;
            for (Product product : allProducts) {
                if (product.getId() == productId)
                    productSearch = product;
            }
        return productSearch;
    }
    public static ObservableList<Part> lookupPart(String searchPartName){
        ObservableList<Part> searchParts = observableArrayList();

        if(searchPartName.length() == 0) {
            searchParts = allParts;
        }
        else if (!allParts.isEmpty()){
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toLowerCase().trim().contains(searchPartName.toLowerCase()))
                    searchParts.add(allParts.get(i));
            }
        }
        return searchParts;
    }
    public static ObservableList<Product> lookupProduct(String searchProductName){
        ObservableList<Product> searchProducts = observableArrayList();

        if(searchProductName.length() == 0) {
            searchProducts = allProducts;
        }
        else if (!allProducts.isEmpty()){
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toLowerCase().trim().contains(searchProductName.toLowerCase()))
                    searchProducts.add(allProducts.get(i));
            }
        }
        return searchProducts;
    }
    public static void updatePart (int index, Part selectedPart) {
        if (!allParts.isEmpty())
            allParts.set(index, selectedPart);
    }
    public static void updateProduct (int index, Product selectedProduct) {
        if (!allProducts.isEmpty())
            allProducts.set(index, selectedProduct);
    }
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else
            return false;
    }
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else
            return false;
    }
    public static ObservableList<Part> getAllParts(){return allParts;}
    public static ObservableList<Product> getAllProducts(){return allProducts;}
}
