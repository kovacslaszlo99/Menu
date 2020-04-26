package hu.unideb.inf;

import hu.unideb.inf.model.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {
    
    private ArrayList<Asztal> asztalok = new ArrayList<>();
    private ArrayList<Foglalas> foglalasok = new ArrayList<>();
    private ArrayList<Etel> etelek = new ArrayList<>();
    private ArrayList<EddigiRendeles> eddigirendelesek = new ArrayList<>();

    private Connect db = new Connect();
    
    private static int perc[] = {0, 15, 30, 45};
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
    Alert a = new Alert(AlertType.NONE); 
    
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
    private ListView<String> jelenlegFoglaltAsztalokListView;

    @FXML
    private ChoiceBox<String> deskChoiceBox2;

    @FXML
    private ChoiceBox<String> productChoiceBox;

    @FXML
    private Spinner<Integer> pieceScroller;

    @FXML
    private TableView eddigirendelesekTableView = new TableView();

    @FXML
    private TableColumn<String, EddigiRendeles> etelnev;

    @FXML
    private TableColumn<Integer, EddigiRendeles> mennyiseg;

    @FXML
    private TableColumn<Integer, EddigiRendeles> osszeg;

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
        //dani
        boolean sikerult = db.insertData("etlap","nev, ar","'"+hozzaadandotermekneve.getText()+"', '"+hozzaadandotermekara.getText()+"'");
        if(sikerult){
            //dialógus
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Információ!");
            alert.setHeaderText(null);
            alert.setContentText("A hozzáadás sikeresen megtörtént!");

            alert.showAndWait();
        }else{
            //hiba dialógus
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText(null);
            alert.setContentText("Hiba történt az adatok feltöltése közben, vagy nem megfelelő típust használtál!");

            alert.showAndWait();
        }
    }

    @FXML
    void closingButtonReleased(MouseEvent event) {
        //ez a függvény fogja bezárni az alkalmazást.
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Megerősítés");
        alert.setHeaderText(null);
        alert.setContentText("Biztosan ki szeretnél lépni? Ha igen, kattints az OK gombra.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }


    }

    @FXML
    void fizetesButtonHandle() {
        //ez a függvény a "fizetettosszegTextField"-ből és a "fizetendoosszegLabel"-kiszámolja a visszajárót és egy dialógusban kiirja és majd fizetetté teszi a rendelést.
    }

    @FXML
    void foglalasButtonHandle() throws SQLException {
        //laci
        //ez a függvény a "foglalasStartDate" és "foglalasStartTime" és "foglalasEndDate" és "foglalasEndTime" és "deskChoiceBox" valamint a "nameTextField" adatokból létre hozza a foglalást
        String nev = nameTextField.getText();
        int asztalId = Integer.parseInt(this.deskChoiceBox.getValue().split("\\s")[0]);
        LocalDate date = foglalasStartDate.getValue();
        LocalTime timeStrat = LocalTime.parse(foglalasStartTime.getValue(), timeFormat);
        LocalTime timeEnd = LocalTime.parse(foglalasEndTime.getValue(), timeFormat);
        String startIdopont = date.getYear() + "-"
                + String.format("%02d", date.getMonthValue())
                + "-" + String.format("%02d", date.getDayOfMonth())
                + " " + String.format("%02d", timeStrat.getHour())
                + ":" + String.format("%02d", timeStrat.getMinute())
                + ":00";
        String endIdopont = date.getYear() + "-"
                + String.format("%02d", date.getMonthValue())
                + "-" + String.format("%02d", date.getDayOfMonth())
                + " " + String.format("%02d", timeEnd.getHour())
                + ":" + String.format("%02d", timeEnd.getMinute())
                + ":00";
        boolean siker = this.db.insertData("foglalas", "start_idopont, end_idopont, asztal_id, nev, active", "'" + startIdopont + "', '" + endIdopont + "', '" + asztalId + "', '" + nev + "', '1'");
        this.db.rs.close();
        if(siker){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Információ!");
            alert.setHeaderText(null);
            alert.setContentText("A foglalás megtörtént.");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setHeaderText(null);
            alert.setContentText("Hiba történt az adatok feltöltése közben!");
            alert.showAndWait();
        }
        //event2;
    }

    @FXML
    void hozzadasButtonHandle() throws SQLException {
        //ez a függvény a "productChoiceBox" és "pieceScroller" adatokat hozzá adja a "deskChoiceBox2"-ben megadott asztalnál levő jelenlegi rendeléshez,valamint frissíti az eddigi rendelések táblázatot.
        //dani
        eddigirendelesekTableView.getItems().clear();
        db.getData("id","foglalas","asztal_id = "+deskChoiceBox2.getValue());
        db.rs.next();
        int foglalasid = db.rs.getInt("id");
        db.rs.close();
        db.getData("id","etlap", "nev = '"+productChoiceBox.getValue()+"'");
        db.rs.next();
        int etelid = db.rs.getInt("id");
        db.rs.close();
        db.insertData("rendeles","fogalas_id, etel_id, asztal_id, mennyiseg","'"+foglalasid+"', '"+etelid+"', '"+deskChoiceBox2.getValue()+"', '"+pieceScroller.getValue()+"'");
        db.rs.close();
        int osszesosszeg=0;
        db.getData("*","rendeles","asztal_id = "+deskChoiceBox2.getValue());
        while(db.rs.next()){
            etelid = db.rs.getInt("etel_id");
            int mennyiseg = db.rs.getInt("mennyiseg");
            Connect db2 = new Connect();
            db2.getData("*","etlap","id = "+etelid);
            db2.rs.next();
            String etelnev = db2.rs.getString("nev");
            int ar = db2.rs.getInt("ar");
            db2.rs.close();
            osszesosszeg+=(ar*mennyiseg);
            eddigirendelesekTableView.getItems().add(new EddigiRendeles(etelnev,mennyiseg,ar*mennyiseg));
        }
        eddigirendelesLabel.setText("Fizetendő összeg: "+osszesosszeg+" Ft.");
        db.rs.close();


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

    private void getEtlap() throws SQLException{
        //dani
        if(etelek.isEmpty() || getLastId("etlap") > etelek.size()){
            db.getData("*", "etlap");
            etelek.clear();
            while(db.rs.next()){
                int id = db.rs.getInt("id");
                String nev = db.rs.getString("nev");
                int ar = db.rs.getInt("ar");
                etelek.add(new Etel(id, nev, ar));
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
    
    private LocalTime mostIdo(){
        //laci
         LocalTime re = LocalTime.now(); 
         re.format(timeFormat);
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
        getFoglalasok();
        ArrayList<Foglalas> kiVesz = new ArrayList<>();
        for(Foglalas item : foglalasok){
            if(this.dateCompareTo(item.getStartIdopont(), date) == 0 && item.getAsztalId() == asztalId){
                kiVesz.add(item);
            }
        }
        idoCsere(kiVesz);
    }
    
    private void idoCsere(ArrayList<Foglalas> ki){
        //laci
        foglalasStartTime.getItems().clear();
        foglalasEndTime.getItems().clear();
        LocalDate date = foglalasStartDate.getValue();
        for(int i = 8; i < 22; i++){
            for(int item : perc){
                LocalTime  ido = LocalTime.parse(String.format("%02d", i) + ":" + String.format("%02d", item), timeFormat);
                boolean rossz = false;
                for(Foglalas value : ki){
                    LocalTime idoStart = LocalTime.parse(String.format("%02d", value.getStartIdopont().getHour()) + ":" + String.format("%02d", value.getStartIdopont().getMinute()), timeFormat);
                    LocalTime idoEnd = LocalTime.parse(String.format("%02d", value.getEndIdopont().getHour()) + ":" + String.format("%02d", value.getEndIdopont().getMinute()), timeFormat);
                    if(ido.compareTo(idoStart) >= 0 && ido.compareTo(idoEnd) <= 0){
                        rossz = true;
                        break;
                    }
                }
                if(this.dateCompareTo(this.most(), date) == 0 && ido.compareTo(this.mostIdo()) <= 0){
                    rossz = true;
                }
                if(!rossz){
                    foglalasStartTime.getItems().add(String.format("%02d", i) + ":" + String.format("%02d", item));
                    foglalasEndTime.getItems().add(String.format("%02d", i) + ":" + String.format("%02d", item));
                }
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
    }

    @FXML
    void eddigiLek() throws SQLException {
        //dani
        eddigirendelesekTableView.getItems().clear();
        eddigirendelesLabel.setText("Nincs kiválasztva asztal vagy még nem rendeltek!");
        int osszesosszeg=0;
        db.getData("*","rendeles","asztal_id = "+deskChoiceBox2.getValue());
        while(db.rs.next()){
            int etelid = db.rs.getInt("etel_id");
            int mennyiseg = db.rs.getInt("mennyiseg");
            Connect db2 = new Connect();
            db2.getData("*","etlap","id = "+etelid);
            db2.rs.next();
            String etelnev = db2.rs.getString("nev");
            int ar = db2.rs.getInt("ar");
            db2.rs.close();
            osszesosszeg+=(ar*mennyiseg);
            eddigirendelesekTableView.getItems().add(new EddigiRendeles(etelnev,mennyiseg,ar*mennyiseg));
        }
        eddigirendelesLabel.setText("Fizetendő összeg: "+osszesosszeg+" Ft.");
        db.rs.close();

    }

    @FXML
    void etelitalrendelesTab() throws SQLException {
        //dani
        //Asztal choicebox
        etelnev.setCellValueFactory(new PropertyValueFactory<>("etelnev"));
        mennyiseg.setCellValueFactory(new PropertyValueFactory<>("mennyiseg"));
        osszeg.setCellValueFactory(new PropertyValueFactory<>("osszeg"));

        getFoglalasok();
        deskChoiceBox2.getItems().clear();
        for(Foglalas item : foglalasok){
            if(item.getStartIdopont().compareTo(this.most()) <= 0 && item.getEndIdopont().compareTo(this.most()) >= 0){
                deskChoiceBox2.getItems().add(""+item.getAsztalId());
            }
        }
        if(deskChoiceBox2.getItems().isEmpty()){
            System.out.println("Nincs foglalás most.");
        }


        //Etel choicebox
        getEtlap();
        productChoiceBox.getItems().clear();
        for(Etel item : etelek){
            productChoiceBox.getItems().add(item.getNev());
        }
        if(productChoiceBox.getItems().isEmpty()){
            System.out.println("Nincs étel az adatbázisban.");
        }

        //Darab Spinner
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        pieceScroller.setValueFactory(valueFactory);

        //Tablazat
        eddigirendelesekTableView.getItems().clear();

        //Label
        eddigirendelesLabel.setText("Nincs rendelés kiválasztva!");


    }

}
