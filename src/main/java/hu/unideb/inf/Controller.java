package hu.unideb.inf;

import hu.unideb.inf.model.Asztal;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {
    
    private ArrayList<Asztal> asztalok = new ArrayList<>();
    
    @FXML
    private DatePicker foglalasStartDate;

    @FXML
    private DatePicker foglalasEndDate;

    @FXML
    private ChoiceBox<String> foglalasStartTime;

    @FXML
    private ChoiceBox<String> foglalasEndTime;

    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<String> deskChoiceBox;

    @FXML
    private Label ferohelyLabel;

    @FXML
    private ListView<?> jelenlegFoglaltAsztalokListView;

    @FXML
    private ChoiceBox<?> deskChoiceBox2;

    @FXML
    private ChoiceBox<?> productChoiceBox;

    @FXML
    private Spinner<?> pieceScroller;

    @FXML
    private TableView<?> eddigirendelesekTableView;

    @FXML
    private Label eddigirendelesLabel;

    @FXML
    private ChoiceBox<?> deskChoiceBox3;

    @FXML
    private ChoiceBox<?> guestnameChoiceBox;

    @FXML
    private TableView<?> rendelesTableView;

    @FXML
    private Label fizetendoOsszegLabel;

    @FXML
    private TextField fizetettOsszegLabel;

    @FXML
    private TextField hozzaadandotermekneve;

    @FXML
    private TextField hozzaadandotermekara;

    @FXML
    private ImageView closeButton;


    @FXML
    void adatbazishozhozzadasButtonHandle() {
        //ez a függvény fogja hozzáadni a "hozzaadandotermekneve" és a "hozzaadandotermekara" textfieldekben megadott adatokat az adatbázishoz.

    }

    @FXML
    void closingButtonReleased(MouseEvent event) {
        //ez a függvény fogja bezárni az alkalmazást.
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void fizetesButtonHandle() {
        //ez a függvény a "fizetettosszegTextField"-ből és a "fizetendoosszegLabel"-kiszámolja a visszajárót és egy dialógusban kiirja és majd fizetetté teszi a rendelést.
    }

    @FXML
    void foglalasButtonHandle() {
        //ez a függvény a "foglalasStartDate" és "foglalasStartTime" és "foglalasEndDate" és "foglalasEndTime" és "deskChoiceBox" valamint a "nameTextField" adatokból létre hozza a foglalást

    }

    @FXML
    void hozzadasButtonHandle() {
        //ez a függvény a "productChoiceBox" és "pieceScroller" adatokat hozzá adja a "deskChoiceBox2"-ben megadott asztalnál levő jelenlegi rendeléshez,valamint frissíti az eddigi rendelések táblázatot.


    }

    @FXML
    void kivalasztButtonHandle() {
        //ez a függvény a "deskChoiceBox3" vagy a "guestnameChoiceBox" alapján feltölti a táblázatot az általuk rendelt termékekkel majd kiszámolja a fizetendő összeget és a "fizetendoosszegLabel"-ben kiirja

    }

    @FXML
    void lekerdezButtonHandle() {
        //ez a függvény fogja feltölteni a "jelenlegFoglaltAsztalokListView" listát.

    }
    
    @FXML
    void asztalTab() throws SQLException {
        Connect valami = new Connect();
        if(asztalok.size() == 0){
            String[] rs_s = valami.getData("*", "asztalok");
            while(valami.rs.next()){
                    int id = valami.rs.getInt("id");
                    int ferohely = valami.rs.getInt("ferohely");
                    asztalok.add(new Asztal(id, ferohely));
            }

            for(Asztal item : asztalok){
                deskChoiceBox.getItems().add(item.getId() + " Férőhely: " + item.getFerohely());
            }
        }
        
        // ora = 8 - 22
        //perc = 00, 15, 30, 45
        
        int perc[] = {0, 15, 30, 45};
        if(foglalasStartTime.getItems().size() == 0){
            for(int i = 8; i < 22; i++){
                for(int item : perc){
                    foglalasStartTime.getItems().add(i + ":" + item);
                }
            }
        }
        
        if(foglalasEndTime.getItems().size() == 0){
            for(int i = 8; i < 22; i++){
                for(int item : perc){
                    foglalasEndTime.getItems().add(i + ":" + item);
                }
            }
        }
        
    }

}
