package noel.example.com.firebasecrudoperations;

import android.widget.TextView;

public class Details {
    private String personId;
    private String personName;
    private String personAge;
    private String personDOB;




    public Details() {

    }

    public Details(String personId, String personName,String personAge,String personDOB) {
        this.personId = personId;
        this.personName = personName;
        this.personAge = personAge;
        this.personDOB = personDOB;


    }




    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAge() {
        return personAge;
    }

    public void setPersonAge(String personAge) {
        this.personAge = personAge;
    }

    public String getPersonDOB() {
        return personDOB;
    }

    public void setPersonDOB(String personDOB) {
        this.personDOB = personDOB;
    }


}
