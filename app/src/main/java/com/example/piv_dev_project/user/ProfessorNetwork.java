package com.example.piv_dev_project.user;

import java.util.List;

public class ProfessorNetwork extends ProfessorClass{
    String  id;
    List<Integer> groups;
    public ProfessorNetwork(String name, String surname, String id, List<Integer> groups) {
        super(name, surname);
        this.id = id;
        this.groups = groups;
    }


}
