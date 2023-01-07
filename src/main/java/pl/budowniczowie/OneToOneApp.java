package pl.budowniczowie;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.budowniczowie.entity.Company;
import pl.budowniczowie.entity.CompanyDetail;

public class OneToOneApp {
    public static void main(String[] args){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        SessionFactory factory = conf.buildSessionFactory();

        Session session = factory.getCurrentSession();
        Company company = new Company("Strefakursow", 17774458);
        CompanyDetail companyDetail = new CompanyDetail("Poland", 150);
        company.setCompanyDetail(companyDetail);

        session.beginTransaction();

        session.save(companyDetail);
        session.save(company);

        session.getTransaction().commit();
        factory.close();
    }
}
