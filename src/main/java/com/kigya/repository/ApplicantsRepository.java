package com.kigya.repository;

import com.kigya.database.ApplicantsDaoImpl;
import com.kigya.database.ApplicantsDatabase;
import com.kigya.entity.Applicants;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ApplicantsRepository {

    private final ApplicantsDatabase ad = new ApplicantsDatabase() {
        @Contract(value = " -> new", pure = true)
        @Override
        public @NotNull ApplicantsDaoImpl getApplicantsDao() {
            return new ApplicantsDaoImpl();
        }
    };

    public List<Applicants> getAllApplicants() {
        return ad.getApplicantsDao().findAll();
    }

    public void addApplicant(Applicants applicant) {
        ad.getApplicantsDao().addItem(applicant);
    }

}
