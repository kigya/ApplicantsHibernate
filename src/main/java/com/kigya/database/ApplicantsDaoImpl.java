package com.kigya.database;

import com.kigya.entity.Applicants;
import com.kigya.exception.RepositoryException;
import com.kigya.utils.HibernateUtil;
import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Transient;
import java.util.List;

public class ApplicantsDaoImpl implements ApplicantsDao {

    @Override
    @SneakyThrows
    @Transient
    public List<Applicants> findAll() {
        Transaction tx = null;
        List<Applicants> applicants;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                tx = session.beginTransaction();
                applicants = session.createQuery("FROM Applicants").list();
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new RepositoryException(e, "Initialization transaction error!");
            } catch (Exception e) {
                throw new RepositoryException(e, "Applicant Repository error!");
            }
        }
        return applicants;
    }

    @Override
    @SneakyThrows
    public void addItem(Applicants applicant) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(applicant);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RepositoryException(e, "Initialization transaction error!");
        } catch (Exception e) {
            throw new RepositoryException(e, "Applicant Repository error!");
        }
    }
}
