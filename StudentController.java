package application;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
//import javafx.scene.control.TableColumn.CellDataFeatures;
//import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
//import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
//import java.util.regex.Pattern;

public class StudentController implements Initializable {
	
	//-----------------------------------------Use this area to declare variables-----------------------------------------------------//

	Boolean bool;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML // fx:id="firstNameCol"
    private TableColumn<Student, String> firstNameCol;

    @FXML // fx:id="lastNameCol"
    private TableColumn<Student, String> lastNameCol;

    @FXML // fx:id="ageCol"
    private TableColumn<Student, Number> ageCol;

    @FXML // fx:id="gpaCol"
    private TableColumn<Student, Number> gpaCol;

    @FXML // fx:id="yearCol"
    private TableColumn<Student, String> yearCol;

    @FXML // fx:id="genderCol"
    private TableColumn<Student, String> genderCol;
    
    @FXML // fx:id="totalCol"
    private TableColumn<Student, String> totalCol;
    
    @FXML // fx:id="courseCol"
    private TableColumn<Student, String> courseCol;
    
    @FXML // fx:id="firstNameField"
    private TextField firstNameField;

    @FXML // fx:id="lastNameField"
    private TextField lastNameField;

    @FXML // fx:id="ageField"
    private TextField ageField;

    @FXML // fx:id="gpaField"
    private TextField gpaField;

    @FXML // fx:id="yearBox"
    private ComboBox<String> yearBox;
    ObservableList<String> yearBoxData = FXCollections.observableArrayList();

    @FXML // fx:id="totalBox"
    private ComboBox<String> totalBox;
    ObservableList<String> totalBoxData = FXCollections.observableArrayList();
    
    @FXML // fx:id="courseBox"
    private ComboBox<String> courseBox;
    ObservableList<String> courseBoxData = FXCollections.observableArrayList();
    
    @FXML
    private TextField filterInput;

    @FXML // fx:id="genderBox"
    private ComboBox<String> genderBox;
    ObservableList<String> genderBoxData = FXCollections.observableArrayList();

    @FXML // fx:id="addBtn"
    private Button addBtn;

    @FXML // fx:id="deleteBtn"
    private Button deleteBtn;

    @FXML
    private MenuBar fileMenu;
    
    @FXML
    private TableView<Student> studentTable;

    ObservableList<Student> observableStudentList = FXCollections.observableArrayList();
    
    // --------------------------------------------Initialize table and controllers-----------------------------------------------//

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //add Listener to filterField
        filterInput.textProperty().addListener(new ChangeListener<Object>() {
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
                filterStudentList((String) oldValue, (String) newValue);

            }
        });
        
        //----------------------------------------Try Catch Statements-------------------------------------//
        //initialize editable attributes
        studentTable.setEditable(true);
        firstNameCol.setOnEditCommit(e -> firstNameCol_OnEditCommit(e));
        lastNameCol.setOnEditCommit(e -> lastNameCol_OnEditCommit(e));
        yearCol.setOnEditCommit(e -> yearCol_OnEditCommit(e));
        totalCol.setOnEditCommit(e -> totalCol_OnEditCommit(e));
        ageCol.setOnEditCommit(e -> ageCol_OnEditCommit(e));
        gpaCol.setOnEditCommit(e -> genderCol_OnEditCommit(e));
        genderCol.setOnEditCommit(e -> genderCol_OnEditCommit(e));
        courseCol.setOnEditCommit(e -> courseCol_OnEditCommit(e));

        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        gpaCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        genderCol.setCellFactory(TextFieldTableCell.forTableColumn());
        totalCol.setCellFactory(TextFieldTableCell.forTableColumn());
        courseCol.setCellFactory(TextFieldTableCell.forTableColumn());

        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        gpaCol.setCellValueFactory(cellData -> cellData.getValue().gradepointProperty());
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        genderCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        totalCol.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
        courseCol.setCellValueFactory(cellData -> cellData.getValue().courseProperty());

        //initialize total ComboBox
        totalBoxData.add(new String("Present"));
        totalBoxData.add(new String("Absent"));
        
        totalBox.setItems(totalBoxData);
        
        //initialize year ComboBox
        yearBoxData.add(new String("Pre K"));
        yearBoxData.add(new String("First"));
        yearBoxData.add(new String("Second"));
        yearBoxData.add(new String("Third"));
        yearBoxData.add(new String("Fourth"));
        yearBoxData.add(new String("Fifth"));
        yearBoxData.add(new String("Sixth"));
        yearBoxData.add(new String("Seventh"));
        yearBoxData.add(new String("Eighth"));
        yearBoxData.add(new String("Freshmen"));
        yearBoxData.add(new String("Sophmore"));
        yearBoxData.add(new String("Junior"));
        yearBoxData.add(new String("Senior"));
        
        yearBox.setItems(yearBoxData);
        
        //initialize course ComboBox
        courseBoxData.add(new String("English"));
        courseBoxData.add(new String("Math"));
        courseBoxData.add(new String("History"));
        courseBoxData.add(new String("Science"));
        courseBoxData.add(new String("P.E."));
        courseBoxData.add(new String("Computer Science"));
        
        courseBox.setItems(courseBoxData);
		
        
        //initialize gender ComboBox
        genderBoxData.add(new String("Male"));
        genderBoxData.add(new String("Female"));

        genderBox.setItems(genderBoxData);


        addBtn.setDisable(true);
        deleteBtn.setDisable(true);
        studentTable.setItems(observableStudentList);
        studentTable.setEditable(true);
        studentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        studentTable.setPlaceholder(new Label("Your Table is Empty"));

        firstNameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
        	//Add and Delete Buttons
        	
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (firstNameField.isFocused()) {
                    addBtn.setDisable(false);
                }
            }
        });
        studentTable.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (studentTable.isFocused()) {
                    deleteBtn.setDisable(false);
                }
            }
        });
    }//end initialize

    
    //----------------------------------------------Control handlers---------------------------------------------//
     

    
     // Add Button
    public void handleAddButtonClick(ActionEvent event) throws Exception {
    		
    	
        if (observableStudentList.size() < 50) {
            if (isValidInput(event)) {
                
            	/* Use Patter.matches to check for numbers
				 * If it is not alphabetical it will return false*/
				
				if (genderBox.getValue().equals("Male") ) {
                    Student student = new Student();
                    student.setFirstName(firstNameField.getText());
                    student.setLastName(lastNameField.getText());
                    student.setAge(Integer.parseInt(ageField.getText()));
                    student.setGradepoint(Double.parseDouble(gpaField.getText()));
                    student.setGender(genderBox.getValue());
                    student.setYear(yearBox.getValue());
                    student.setCourse(courseBox.getValue());
                    student.setTotal(totalBox.getValue());
                    observableStudentList.add(student);
                    System.out.println(student.toString());
                    firstNameField.clear();
                    lastNameField.clear();
                    yearBox.setValue("Year in School");
                    ageField.clear();
                    gpaField.clear();
                    genderBox.setValue("Gender");
                    courseBox.setValue("Course");
                    totalBox.setValue("Attendance");

                }
                if (genderBox.getValue().equals("Female")) {
                    Student student = new Student();
                    student.setFirstName(firstNameField.getText());
                    student.setLastName(lastNameField.getText());
                    student.setAge(Integer.parseInt(ageField.getText()));
                    student.setYear(yearBox.getValue());
                    student.setCourse(courseBox.getValue());
                    student.setTotal(totalBox.getValue());
                    student.setGradepoint(Double.parseDouble(gpaField.getText()));
                    student.setGender(genderBox.getValue());
                    observableStudentList.add(student);
                    System.out.println(student.toString());
                    firstNameField.clear();
                    lastNameField.clear();
                    ageField.clear();
                    gpaField.clear();
                    genderBox.setValue("Gender");
                    courseBox.setValue("Course");
                    yearBox.setValue("Year in school");
                    totalBox.setValue("Attendance");
                }
            }
        } 
    }
        	
  
    	
    
    //------------In case of empty fields. Gives alert for respective empty field and requests focus on that field-------------//
    private boolean isValidInput(Event e) {

        Boolean validInput = true;

        //First Name Field
        if(firstNameField == null || firstNameField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyFirstName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyFirstName.setContentText("First name is EMPTY");
            emptyFirstName.initModality(Modality.APPLICATION_MODAL);
            emptyFirstName.initOwner(owner);
            emptyFirstName.showAndWait();
            if(emptyFirstName.getResult() == ButtonType.OK) {
                emptyFirstName.close();
                firstNameField.requestFocus();
            }
        }
        
        //Last Name Field
        if(lastNameField == null || lastNameField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyLastName = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyLastName.setContentText("ERROR: Last Name is Empty!");
            emptyLastName.initModality(Modality.APPLICATION_MODAL);
            emptyLastName.initOwner(owner);
            emptyLastName.showAndWait();
            if (emptyLastName.getResult() == ButtonType.OK) {
                emptyLastName.close();
                lastNameField.requestFocus();
            }
        }

        
        //Attendance Box
        if(totalBox == null || totalBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyTotal = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyTotal.setContentText("ERROR: Attendance Empty!");
            emptyTotal.initModality(Modality.APPLICATION_MODAL);
            emptyTotal.initOwner(owner);
            emptyTotal.showAndWait();
            if (emptyTotal.getResult() == ButtonType.OK) {
            	emptyTotal.close();
            	totalBox.requestFocus();
            }
        }
        
        //Age Field
        if(ageField == null || ageField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyAge = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyAge.setContentText("Age is EMPTY");
            emptyAge.initModality(Modality.APPLICATION_MODAL);
            emptyAge.initOwner(owner);
            emptyAge.showAndWait();
            if (emptyAge.getResult() == ButtonType.OK) {
                emptyAge.close();
                ageField.requestFocus();
            }
        }
        
        //GPA Field

        if(gpaField == null || gpaField.getText().trim().isEmpty()) {
            validInput = false;
            Alert emptyGPA = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyGPA.setContentText("GPA is EMPTY");
            emptyGPA.initModality(Modality.APPLICATION_MODAL);
            emptyGPA.initOwner(owner);
            emptyGPA.showAndWait();
            if (emptyGPA.getResult() == ButtonType.OK) {
                emptyGPA.close();
                gpaField.requestFocus();
            }
        }
        
        //Gender Field
        if(genderBox == null || genderBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyGender = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyGender.setContentText("Gender is not selected");
            emptyGender.initModality(Modality.APPLICATION_MODAL);
            emptyGender.initOwner(owner);
            emptyGender.showAndWait();
            if (emptyGender.getResult() == ButtonType.OK) {
                emptyGender.close();
                genderBox.requestFocus();
            }
        }
        
        //Year Field
        if(yearBox == null || yearBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyYear = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyYear.setContentText("Year is not selected");
            emptyYear.initModality(Modality.APPLICATION_MODAL);
            emptyYear.initOwner(owner);
            emptyYear.showAndWait();
            if (emptyYear.getResult() == ButtonType.OK) {
                emptyYear.close();
                yearBox.requestFocus();
            }
        }
        
        //Course Field
        if(courseBox == null || courseBox.getValue().isEmpty()) {
            validInput = false;
            Alert emptyCourse = new Alert(Alert.AlertType.WARNING, "Warning", ButtonType.OK);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            emptyCourse.setContentText("You must select a course");
            emptyCourse.initModality(Modality.APPLICATION_MODAL);
            emptyCourse.initOwner(owner);
            emptyCourse.showAndWait();
            if (emptyCourse.getResult() == ButtonType.OK) {
            	emptyCourse.close();
                courseBox.requestFocus();
            }
        }
        return validInput;
    }
    
    //------------------------------------handle column edits----------------------------------------------------//
     
    @SuppressWarnings("unchecked")
	public void firstNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, String>) e;
        Student student = cellEditEvent.getRowValue();
        student.setFirstName(cellEditEvent.getNewValue());
    }
    @SuppressWarnings("unchecked")
	public void lastNameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, String>) e;
        Student student = cellEditEvent.getRowValue();
        student.setLastName(cellEditEvent.getNewValue());
    }
    @SuppressWarnings("unchecked")
	public void yearCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, String>) e;
        Student student = cellEditEvent.getRowValue();
        student.setYear(cellEditEvent.getNewValue());
    }
    
    @SuppressWarnings("unchecked")
	public void ageCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, Integer> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, Integer>) e;
        Student student = cellEditEvent.getRowValue();
        student.setAge(cellEditEvent.getNewValue());
    }
    @SuppressWarnings("unchecked")
	public void gpaCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, Double> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, Double>) e;
        Student student = cellEditEvent.getRowValue();
        student.setGradepoint(cellEditEvent.getNewValue());
    }
    @SuppressWarnings("unchecked")
	public void genderCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, String>) e;
        Student student = cellEditEvent.getRowValue();
        student.setGender(cellEditEvent.getNewValue());
    }
    
    
    @SuppressWarnings("unchecked")
	public void courseCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, String>) e;
        Student student = cellEditEvent.getRowValue();
        student.setCourse(cellEditEvent.getNewValue());
    }
    
    @SuppressWarnings("unchecked")
	public void totalCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Student, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Student, String>) e;
        Student student = cellEditEvent.getRowValue();
        student.setTotal(cellEditEvent.getNewValue());
    }
    
    //----------------------------------------------Handle button clicks-------------------------------------------------//
    
    
    //Delete Button
    public void handleDeleteButtonClick(Event e) {
        if(!observableStudentList.isEmpty()) {
            System.out.println("Delete button clicked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) e.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                observableStudentList.removeAll(studentTable.getSelectionModel().getSelectedItems());
                studentTable.getSelectionModel().clearSelection();
            }
            else {
                deleteAlert.close();
            }
        }
    }
    
    //Clear Field Button
    public void handleClearButtonClick(ActionEvent event) {
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();
        courseBox.setValue("Course");
        gpaField.clear();
        genderBox.setValue("Gender");
        yearBox.setValue("Year in school");
        totalBox.setValue("Attendance");
       
    }
    
    //filter table by first or last name
    public void filterStudentList(String oldValue, String newValue) {
        ObservableList<Student> filteredList = FXCollections.observableArrayList();
        if(filterInput == null || (newValue.length() < oldValue.length()) || newValue == null) {
            studentTable.setItems(observableStudentList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(Student students : studentTable.getItems()) {
                String filterFirstName = students.getFirstName();
                String filterLastName = students.getLastName();
                if(filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filteredList.add(students);
                }
            }
            studentTable.setItems(filteredList);
        }
    }
    
    //----------------------------------------------Save File-----------------------------------//
    public void handleSave(ActionEvent event) {
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Student Table");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(observableStudentList.isEmpty()) {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert emptyTableAlert = new Alert(Alert.AlertType.ERROR, "EMPTY TABLE", ButtonType.OK);
            emptyTableAlert.setContentText("You have nothing to save");
            emptyTableAlert.initModality(Modality.APPLICATION_MODAL);
            emptyTableAlert.initOwner(this.fileMenu.getScene().getWindow());
            emptyTableAlert.showAndWait();
            if(emptyTableAlert.getResult() == ButtonType.OK) {
                emptyTableAlert.close();
            }
        }
        else {
            File file = fileChooser.showSaveDialog(secondaryStage);
            if(file != null) {
                saveFile(studentTable.getItems(), file);
            }
        }
    }
    

    
    
    public void saveFile(ObservableList<Student> observableStudentList, File file) {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(Student students : observableStudentList) {
                outWriter.write(students.toString());
                outWriter.newLine();
            }
            System.out.println(observableStudentList.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
        }
    }
    
    //-----------------------------------------------------Close application----------------------------------------//
    public void closeApp(Event e) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm", ButtonType.OK, ButtonType.CANCEL);
        Stage stage = (Stage) fileMenu.getScene().getWindow();
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.initModality(Modality.APPLICATION_MODAL);
        exitAlert.initOwner(stage);
        exitAlert.showAndWait();

        if(exitAlert.getResult() == ButtonType.OK) {
            Platform.exit();
        }
        else {
            exitAlert.close();
        }
    }
	

}
