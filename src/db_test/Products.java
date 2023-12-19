/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db_test;

import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hp
 */
 public class Products {
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty quantity;
    private SimpleStringProperty uniqueId;
    
    public Products(String name, String uniqueId, String description, String quantity){
        this.name = new SimpleStringProperty(name);
        this.uniqueId = new SimpleStringProperty(uniqueId);
        this.description = new SimpleStringProperty(description);
        this.quantity = new SimpleStringProperty(quantity);
        
    }
    
    public String getUniqueId() {
        return uniqueId.get();
    }

    public SimpleStringProperty uniqueIdProperty() {
        return uniqueId;
    }
       public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }
}

