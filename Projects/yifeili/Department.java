package yifeili;
import java.util.*;

public class Department{
    private String departName;

    public Department(String departName){
        this.departName = departName;
    }

    public String getDepartName(){
        return this.departName;
    }

    public void setDepartName(String departName){
        this.departName = departName;
    }

    @Override
    public String toString(){
        return "Department {" + this.departName + "}";
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Department that)) return false;
        return this.departName.equals(that.departName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departName);
    }

}