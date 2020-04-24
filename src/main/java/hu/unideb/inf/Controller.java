package hu.unideb.inf;

import hu.unideb.inf.model.Asztal;
import hu.unideb.inf.model.Foglalas;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
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
    private ArrayList<Foglalas> foglalasok = new ArrayList<>();
    
    private Connect db = new Connect();
    
    private static int perc[] = {0, 15, 30, 45};
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
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
    private ListView<String> jelenlegFoglaltAsztalokListView;

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
    void lekerdezButtonHandle() throws SQLException, ParseException {
        //laci
        //ez a függvény fogja feltölteni a "jelenlegFoglaltAsztalokListView" listát.
        
        getFoglalasok();
        
        jelenlegFoglaltAsztalokListView.getItems().clear();
        for(Foglalas item : foglalasok){
            if(item.getStartIdopont().compareTo(this.most()) <= 0 && item.getEndIdopont().compareTo(this.most()) >= 0){
                jelenlegFoglaltAsztalokListView.getItems().add("Asztal szám: " + item.getAsztalId() + " Név: " + item.getNev());
            }
        }
    }
    
    private void getFoglalasok() throws SQLException{
        //laci
        if(foglalasok.isEmpty() || getLastId("foglalas") > foglalasok.size()){
            db.getData("*", "foglalas");
            foglalasok.clear();
            while(db.rs.next()){
                int id = db.rs.getInt("id");
                String startIdopont = db.rs.getString("start_idopont");
                String endIdopont = db.rs.getString("end_idopont");
                int asztalId = db.rs.getInt("asztal_id");
                String nev = db.rs.getString("nev");
                boolean active = db.rs.getInt("active") == 1;
                LocalDateTime LocalStartIdopont = LocalDateTime.parse(startIdopont.split("\\.")[0], formatter);
                LocalDateTime LocalEndIdopont = LocalDateTime.parse(endIdopont.split("\\.")[0], formatter);
                foglalasok.add(new Foglalas(id, LocalStartIdopont, LocalEndIdopont, asztalId, nev, active));
            }
            db.rs.close();
        }
    }
    
    private LocalDateTime most(){
        //laci
         LocalDateTime re = LocalDateTime.now(); 
         re.format(formatter);
         return re;
    }
    
    private int getLastId(String table) throws SQLException{
        //laci
        db.getSQL("select nvl(max(id),0) max_id from " + table);
        int lastId = -1;
        if (db.rs.next()) {
            lastId = db.rs.getInt("max_id");  
        }
        db.rs.close();
        return lastId;
    }
    
    @FXML
    void asztalTab() throws SQLException {
        //laci
        Connect db = new Connect();
        if(asztalok.size() == 0){
            String[] rs_s = db.getData("*", "asztalok");
            while(db.rs.next()){
                    int id = db.rs.getInt("id");
                    int ferohely = db.rs.getInt("ferohely");
                    asztalok.add(new Asztal(id, ferohely));
            }

            for(Asztal item : asztalok){
                deskChoiceBox.getItems().add(item.getId() + " Férőhely: " + item.getFerohely());
            }
        }
        
        if(foglalasStartTime.getItems().size() == 0){
            for(int i = 8; i < 22; i++){
                for(int item : perc){
                    foglalasStartTime.getItems().add(i + ":" + String.format("%02d", item));
                }
            }
        }
        
        if(foglalasEndTime.getItems().size() == 0){
            for(int i = 8; i < 22; i++){
                for(int item : perc){
                    foglalasEndTime.getItems().add(i + ":" + String.format("%02d", item));
                }
            }
        }
        
    }

}
