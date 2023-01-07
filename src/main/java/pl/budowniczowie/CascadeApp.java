package pl.budowniczowie;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.budowniczowie.entity.Company;
import pl.budowniczowie.entity.CompanyDetail;

public class CascadeApp {
    public static void main(String[] args){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();
        Company company = new Company("KGHM", 1777445800);
        CompanyDetail companyDetail = new CompanyDetail("Poland", 1570);
        company.setCompanyDetail(companyDetail);

        session.beginTransaction();

        session.persist(company);

        session.getTransaction().commit();
        factory.close();
    }
}
