package hu.unideb.inf;

import hu.unideb.inf.model.Asztal;
import hu.unideb.inf.model.Foglalas;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    
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
    
    private void getAsztalok() throws SQLException{
        //laci
        if(asztalok.isEmpty() || getLastId("asztalok") > asztalok.size()){
            db.getData("*", "asztalok");
            asztalok.clear();
            while(db.rs.next()){
                int id = db.rs.getInt("id");
                int ferohely = db.rs.getInt("ferohely");
                asztalok.add(new Asztal(id, ferohely));
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
    
    private int dateCompareTo(LocalDateTime d1, LocalDate d2){
        /* laci
        -1 d1 < d2
        0 d1 == d2
        1 d1 > d2
        */
        if(d1.getYear() == d2.getYear()){
            if(d1.getMonthValue() == d2.getMonthValue()){
                if(d1.getDayOfMonth() == d2.getDayOfMonth()){
                    return 0;
                }else if(d1.getDayOfMonth() < d2.getDayOfMonth()){
                    return -1;
                }else{
                    return 1;
                }
            }else if(d1.getMonthValue() < d2.getMonthValue()){
                return -1;
            }else{
                return 1;
            }
        }else if(d1.getYear() < d2.getYear()){
            return -1;
        }else{
            return 1;
        }
    }
    
    @FXML
    void foglalasStartDateOnAction() throws SQLException {
        //laci
        int asztalId = Integer.parseInt(this.deskChoiceBox.getValue().split("\\s")[0]);
        LocalDate date = foglalasStartDate.getValue();
        //System.out.println(asztalId);
        getFoglalasok();
        System.out.println("---");
        //LocalDateTime LocalStartIdopont = LocalDateTime.parse(a, formatter);
        ArrayList<Foglalas> kiVesz = new ArrayList<>();
        
        for(Foglalas item : foglalasok){
            if(this.dateCompareTo(item.getStartIdopont(), date) == 0 && item.getAsztalId() == asztalId){
                LocalDateTime[] intervall = new LocalDateTime[2];
                //intervall[0] = LocalDateTime.parse(String.format("%02d",item.getStartIdopont().getHour()) + ":" + String.format("%02d", item.getStartIdopont().getMinute()), timeFormat);
                //intervall[1] = LocalDateTime.parse(String.format("%02d", item.getEndIdopont().getHour()) + ":" + String.format("%02d", item.getEndIdopont().getMinute()), timeFormat);
                //kiVesz.add(intervall);
                kiVesz.add(item);
            }
        }
        
        idoCsere(kiVesz);
    }
    
    private void idoCsere(ArrayList<Foglalas> ki){
        foglalasStartTime.getItems().clear();
        
        for(int i = 8; i < 22; i++){
            for(int item : perc){
                LocalDateTime ido = LocalDateTime.parse(String.format("%02d", i) + ":" + String.format("%02d", item), timeFormat);
               // for(Foglalas item : ki){
                    //if(ido.compareTo(ido))
                //}
                
                foglalasStartTime.getItems().add(String.format("%02d", i) + ":" + String.format("%02d", item));
            }
        }

    }
        
    @FXML
    void asztalTab() throws SQLException {
        //laci
        getAsztalok();
        deskChoiceBox.getItems().clear();
        for(Asztal item : asztalok){
            deskChoiceBox.getItems().add(item.getId() + " Férőhely: " + item.getFerohely());
        }
        
        if(foglalasStartTime.getItems().isEmpty()){
            for(int i = 8; i < 22; i++){
                for(int item : perc){
                    foglalasStartTime.getItems().add(i + ":" + String.format("%02d", item));
                }
            }
        }
        
        if(foglalasEndTime.getItems().isEmpty()){
            for(int i = 8; i < 22; i++){
                for(int item : perc){
                    foglalasEndTime.getItems().add(i + ":" + String.format("%02d", item));
                }
            }
        }
        
    }

}
