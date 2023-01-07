package pl.budowniczowie;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import pl.budowniczowie.entity.Company;
import pl.budowniczowie.entity.CompanyDetail;

import java.util.List;

public class OneToOneHqlApp {
    public static void main(String[] args){
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Company.class);
        conf.addAnnotatedClass(CompanyDetail.class);
        SessionFactory factory = conf.buildSessionFactory();
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        String select = "select c from Company c";
        String where = "select c from Company c join c.companyDetail cd where cd.residence='italy'";
        String sum = "select sum(c.value) from Company c join c.companyDetail cd where cd.residence='Poland'";

        Query query = session.createQuery(sum) ;

        Long result = (Long) query.getSingleResult();

        session.getTransaction().commit();

            System.out.println(result);

        factory.close();
    }
}
