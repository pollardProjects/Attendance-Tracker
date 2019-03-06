package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

	//First Name Info
	
    private StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    public String getFirstName() {
        return firstName.get();
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    //Last Name Info
    private StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    public String getLastName() {return lastName.get();}
    public StringProperty lastNameProperty() {return lastName;}
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    //GPA Info
    private DoubleProperty gradepoint = new SimpleDoubleProperty(this, "gradePoint", 0.0);
    public double getGradepoint() {
        return gradepoint.get();
    }
    public DoubleProperty gradepointProperty() {
        return gradepoint;
    }
    public void setGradepoint(double gradepoint) {
        this.gradepoint.set(gradepoint);
    }

    //Year in School Info
    private StringProperty year = new SimpleStringProperty(this, "year", "");
    public String getYear() {
        return year.get();
    }
    public StringProperty yearProperty() {
        return year;
    }
    public void setYear(String year) {
        this.year.set(year);
    }

    
    //Age Info
    private IntegerProperty age = new SimpleIntegerProperty(this, "age", 0);
    public int getAge() {
        return age.get();
    }
    public IntegerProperty ageProperty() {
        return age;
    }
    public void setAge(int age) {
        this.age.set(age);
    }

    
    //Gender Info
    private StringProperty gender = new SimpleStringProperty(this, "gender", "");
    public String getGender() {
        return gender.get();
    }
    public StringProperty genderProperty() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }
    
    //Total Info
    
	private StringProperty total = new SimpleStringProperty(this, "total", "");
	
    public String getTotal() {
        return total.get();
    }
    public StringProperty totalProperty() {
        return total;
    }
    public void setTotal(String total) {
        this.total.set(total);
    }
	
	//for console printing purposes
    public String toString() {

        return "First Name: " + getFirstName() + " | Last Name: " + getLastName() + " | Year: " + getYear() +  " | Age: " + getAge() + " | GPA: " + getGradepoint() + " | Gender: " + getGender() + " | Total Days: " + getTotal();


    }
    
}
