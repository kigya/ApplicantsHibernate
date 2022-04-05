package com.kigya.database;


import com.kigya.entity.Applicants;

import java.util.List;

public interface ApplicantsDao {

    public List<Applicants> findAll();
    public void addItem(Applicants applicant);

}
