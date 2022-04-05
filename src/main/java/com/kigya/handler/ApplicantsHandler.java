package com.kigya.handler;

import com.kigya.entity.Applicants;
import com.kigya.repository.ApplicantsRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map.Entry;

import static com.kigya.utils.Contstants.SAVING_ERROR_TEXT;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicantsHandler {

    private static final ApplicantsRepository repository = new ApplicantsRepository();
    protected static List<Applicants> applicantsList = repository.getAllApplicants();

    @SneakyThrows
    public static void saveToFileCurrentDatabase() {
        String str = String.valueOf(applicantsList);
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("dbContents.txt"))) {
            writer.write(str.replace("},", "}"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database contents saved to file");
    }

    // поскольку в базе данных студентов без оценок ниже 4 намного меньше
    // мы будем кидать в логи именно их
    @SneakyThrows
    public static void saveToFileApplicantsWithoutUnsatisfactoryMarks() {
        StringBuilder str = new StringBuilder();
        for (Applicants applicant : applicantsList) {
            List<String> marksArrayList = Arrays
                    .stream(applicant.marks().split(", ")).toList();
            boolean checker = true;
            for (String mark : marksArrayList) {
                if (Integer.parseInt(mark) < 4) {
                    checker = false;
                    break;
                }
            }
            if (checker) {
                str.append(applicant);
            }
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("marksSatisFilter.txt"))) {
            writer.write(str.toString().replace("},", "}"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database contents filtered by satis marks saved to file");
    }

    @SneakyThrows
    public static void saveToFileApplicantsWithoutUnsatisfactoryMarksStream() {
        StringBuilder str = new StringBuilder();
        applicantsList.forEach(applicant -> {
            List<String> marksArrayList = Arrays
                    .stream(applicant.marks().split(", ")).toList();
            AtomicBoolean checker = new AtomicBoolean(marksArrayList
                    .stream()
                    .noneMatch(mark -> Integer.parseInt(mark) < 4));
            if (checker.get()) str.append(applicant);
        });

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("marksSatisFilterStream.txt"))) {
            writer.write(str.toString().replace("},", "}"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database contents filtered by satis marks stream API saved to file");
    }

    @SneakyThrows
    public static void saveToFileApplicantsWithAverageMark(double average) {
        StringBuilder str = new StringBuilder();
        for (Applicants applicant : applicantsList) {
            var marksArrayList = Arrays
                    .stream(applicant.marks().split(", ")).toList();
            int sum = 0;
            for (String mark : marksArrayList) {
                sum += Integer.parseInt(mark);
            }
            if (sum * 1f / marksArrayList.size() >= average) {
                str.append(applicant);
            }
        }

        try (
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter("averageMark.txt"))) {
            writer.write(str.toString().replace("},", "}"));
        } catch (
                IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database contents filtered by average marks saved to file");
    }

    @SneakyThrows
    public static void saveToFileApplicantsWithAverageMarkStream(double average) {
        StringBuilder str = new StringBuilder();
        applicantsList.forEach(applicant -> {
            var marksArrayList = Arrays
                    .stream(applicant.marks().split(", ")).toList();
            var sum = marksArrayList.stream().mapToInt(Integer::parseInt).sum();
            if (sum * 1f / marksArrayList.size() >= average) str.append(applicant);
        });

        try (
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter("averageMarkStream.txt"))) {
            writer.write(str.toString().replace("},", "}"));
        } catch (
                IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database contents filtered by average marks Stream API saved to file");
    }

    @SneakyThrows
    public static void saveToFileApplicantsWithMaxAverage(double amount) {
        StringBuilder str = new StringBuilder();
        HashMap<Applicants, Float> hashMapAverage = new HashMap<>();
        for (Applicants applicant : applicantsList) {
            var marksArrayList = Arrays
                    .stream(applicant.marks().split(", ")).toList();
            int sum = 0;
            for (String mark : marksArrayList) {
                sum += Integer.parseInt(mark);
            }
            hashMapAverage.put(applicant, sum * 1f / applicant.marks().length());
        }

        LinkedHashMap<Applicants, Float> sortedMap =
                getApplicantsFloatLinkedHashMap(hashMapAverage);

        Iterator<Applicants> iterator = getApplicantsIterator(sortedMap);
        int i = 0;
        while (i < amount) {
            Applicants key = iterator.next();
            str.append(key);
            i++;
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("topAverageMarks.txt"))) {
            writer.write(str.toString().replace("},", "}"));
        } catch (
                IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database top average applicants saved to file");
    }

    @SneakyThrows
    public static void saveToFileApplicantsWithMaxAverageStream(double amount) {
        StringBuilder str = new StringBuilder();
        var hashMapAverage = new HashMap<Applicants, Float>();
        formHashMap(hashMapAverage);
        LinkedHashMap<Applicants, Float> sortedMap =
                getFloatLinkedHashMapSortedStream(hashMapAverage);
        Set<Applicants> setKeys = sortedMap.keySet();
        LinkedList<Applicants> listKeys
                = new LinkedList<>(setKeys);
        int i = 0;
        while (i < amount) {
            Applicants key = listKeys.descendingIterator().next();
            str.append(key);
            i++;
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("topAverageMarksStream.txt"))) {
            writer.write(str.toString().replace("},", "}"));
        } catch (
                IOException e) {
            e.printStackTrace();
            log.error(SAVING_ERROR_TEXT, e);
        }

        log.info("Current database top average applicants Stream API saved to file");
    }

    @NotNull
    private static LinkedHashMap<Applicants, Float> getFloatLinkedHashMapSortedStream(@NotNull HashMap<Applicants, Float> hashMapAverage) {
        var sortedMap = new LinkedHashMap<Applicants, Float>();
        var list = new ArrayList<Float>();
        hashMapAverage.forEach((key, value) -> list.add(value));
        Collections.sort(list);
        list.forEach(num -> hashMapAverage
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue()
                        .equals(num))
                .forEachOrdered(entry -> sortedMap.
                        put(entry.getKey(), num)));
        return sortedMap;
    }

    private static void formHashMap(HashMap<Applicants, Float> hashMapAverage) {
        for (val applicant : applicantsList) {
            List<String> marksArrayList;
            marksArrayList = Arrays
                    .stream(applicant.marks().split(", ")).toList();
            int sum = marksArrayList.stream().mapToInt(Integer::parseInt).sum();
            hashMapAverage.put(applicant, sum * 1f / applicant.marks().length());
        }
    }

    @NotNull
    private static Iterator<Applicants> getApplicantsIterator(@NotNull LinkedHashMap<Applicants, Float> sortedMap) {
        Set<Applicants> setKeys = sortedMap.keySet();
        LinkedList<Applicants> listKeys
                = new LinkedList<>(setKeys);
        return listKeys.descendingIterator();
    }

    @NotNull
    private static LinkedHashMap<Applicants, Float> getApplicantsFloatLinkedHashMap(@NotNull HashMap<Applicants, Float> hashMapAverage) {
        LinkedHashMap<Applicants, Float> sortedMap = new LinkedHashMap<>();
        ArrayList<Float> list = new ArrayList<>();
        for (Entry<Applicants, Float> entry : hashMapAverage.entrySet()) {
            list.add(entry.getValue());
        }
        Collections.sort(list);
        for (Float num : list) {
            for (Entry<Applicants, Float> entry : hashMapAverage.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return sortedMap;
    }
}
