package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Label lblStatus;
	@FXML
	private TextField txtName;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnLogin;
	
	public void Login(ActionEvent event) throws Exception{
		if (txtName.getText().equals("Teacher") && txtPassword.getText().equals("Password")) {
			lblStatus.setText("Login Success!");
			
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/StudentDatabaseFXML.fxml"));
			Scene scene = new Scene(root,900,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Group 3: Student Management System");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} else {
			lblStatus.setText("Login Failed");
		}
	}
}