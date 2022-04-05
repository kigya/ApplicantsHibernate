package com.kigya.entity;

import com.kigya.exception.*;
import com.kigya.utils.Formatter;
import com.kigya.valid.ValidAddress;
import com.kigya.valid.ValidMarks;
import com.kigya.valid.ValidPhoneNumber;
import com.kigya.valid.ValidProperNoun;
import lombok.*;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Accessors(fluent = true)
@Entity
public class Applicants implements Serializable, Cloneable, Comparable<Applicants> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Basic
    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Basic
    @Column(name = "patronymic", nullable = true, length = 30)
    private String patronymic;

    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Basic
    @Column(name = "phone_number", nullable = true, length = 30)
    private String phoneNumber;

    @Basic
    @Column(name = "marks", nullable = false, length = 30)
    private String marks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicants that = (Applicants) o;
        return id == that.id && Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) && Objects.equals(patronymic, that.patronymic) &&
                Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(marks, that.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, address, phoneNumber, marks);
    }

    @Override
    public int compareTo(@NotNull Applicants o) {
        return (this.id - o.id());
    }

    public Applicants(String name, String surname, String patronymic,
                      String address, String phoneNumber, List<Integer> marks) {
        setName(name);
        setSurname(surname);
        setPatronymic(patronymic);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setMarks(marks);
    }

    public Applicants(String name, String surname, String address,
                      @NotNull String unknownString, List<Integer> marks) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        if (unknownString.matches(".*[0-9].*") ) {
            setPhoneNumber(unknownString);
        } else {
            setPatronymic(unknownString);
        }
        setMarks(marks);
    }

    public Applicants(String name, String surname, String address, List<Integer> marks) {
        setName(name);
        setSurname(surname);
        setPatronymic(patronymic);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setMarks(marks);
    }

    @SneakyThrows
    public void setName(@NotNull String name) {
        String properNoun = Formatter.correctProperNoun(name);
        if (ValidProperNoun.isValidProperNoun(properNoun)) {
            this.name = properNoun;
        } else {
            throw new ApplicantNameException("Incorrect applicant name!");
        }
    }

    @SneakyThrows
    public void setSurname(String surname) {
        String properNoun = Formatter.correctProperNoun(surname);
        if (ValidProperNoun.isValidProperNoun(properNoun)) {
            this.name = properNoun;
        } else {
            throw new ApplicantSurnameException("Incorrect applicant surname!");
        }
    }

    @SneakyThrows
    public void setPatronymic(String patronymic) {
        String properNoun = Formatter.correctProperNoun(patronymic);
        if (ValidProperNoun.isValidProperNoun(properNoun)) {
            this.name = properNoun;
        } else {
            throw new ApplicantPatronymicException("Incorrect applicant patronymic!");
        }
    }

    @SneakyThrows
    public void setAddress(String address) {
        String properAddress = Formatter.capitalizeWords(address);
        if (ValidAddress.isValidAddress(properAddress)) {
            this.name = properAddress;
        } else {
            throw new ApplicantAddressException("Incorrect applicant address!");
        }
    }

    @SneakyThrows
    public void setPhoneNumber(@NotNull String phoneNumber) {
        String properPhone = phoneNumber.trim();
        if (ValidPhoneNumber.isValidPhoneNumber(properPhone)) {
            this.name = properPhone;
        } else {
            throw new ApplicantPhoneNumberException("Incorrect applicant phone number!");
        }
    }

    @SneakyThrows
    public void setMarks(List<Integer> marksList) {
        if (ValidMarks.isValidMarks(marksList)) {
            this.name = Formatter.marksToString(marksList);
        } else {
            throw new ApplicantMarksException("Incorrect applicant marks!");
        }
    }

    @Override
    public String toString() {
        return "\n\nAbiturient{" +
                "id=" + id +
                ", \n name=" + name +
                ", \n surname=" + surname +
                ", \n patronymic=" + patronymic +
                ", \n address=" + address +
                ", \n phoneNumber='" + phoneNumber + '\'' +
                ", \n marks=" + marks +
                '}';
    }

    @Override
    public Applicants clone() {
        try {
            return (Applicants) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
