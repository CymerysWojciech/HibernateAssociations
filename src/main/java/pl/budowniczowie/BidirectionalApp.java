package pl.budowniczowie;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.budowniczowie.entity.Company;
import pl.budowniczowie.entity.CompanyDetail;

public class BidirectionalApp {
    public static void main(String[] args){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        Company company = new Company("Orlen", 66000);
        CompanyDetail companyDetail = new CompanyDetail("Arabia", 25000);
        company.setCompanyDetail(companyDetail);
        companyDetail.setCompany(company);


        session.beginTransaction();

        session.persist(companyDetail);

        session.getTransaction().commit();

        factory.close();
    }
}
