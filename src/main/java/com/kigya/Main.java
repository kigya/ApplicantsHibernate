package com.kigya;

import com.kigya.handler.ApplicantsHandler;

import static com.kigya.handler.ApplicantsHandler.*;
import static com.kigya.handler.ApplicantsHandler.saveToFileCurrentDatabase;

public class Main {
    public static void main(String[] args) {
        saveToFileCurrentDatabase();
        saveToFileApplicantsWithoutUnsatisfactoryMarks();
        saveToFileApplicantsWithoutUnsatisfactoryMarksStream();
        saveToFileApplicantsWithAverageMark(5);
        saveToFileApplicantsWithAverageMarkStream(5);
        saveToFileApplicantsWithMaxAverage(10);
        saveToFileApplicantsWithMaxAverageStream(10);
    }
}
