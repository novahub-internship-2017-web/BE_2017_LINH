//This class contain all method of Menu1 except return previous menu
package com.linhtran.menu;
import com.linhtran.employee.Employee;
import java.util.List;

public class EmployeeAdder {


    //1.1 Add employee at the last of the list
    public void addLastEmployee(List<Employee> list, Employee employee) {
        list.add(employee);
    }

    //1.2 Add employee at the first of the list
    public void addFirstEmployee(List<Employee> list, Employee employee) {
        list.add(0, employee);
    }

    //1.3 Add employee at the index of k
    public void addEmployee(List<Employee> list, Employee employee, int index) {
        if(index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException();
        } else if (index == list.size() - 1) {
            list.add(employee);
        }
        list.add(index+1, employee);
    }

}
