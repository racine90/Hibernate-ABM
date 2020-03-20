package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import hibernate.test.HibernateABMUtil;
import hibernate.test.dto.PersonaEntity;

public class personaDAO {
	public static void saveOrUpdatePersona(PersonaEntity per) {

		Session session = HibernateABMUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(per);
		session.getTransaction().commit();
		HibernateABMUtil.shutdown();
	}

	public static PersonaEntity getPerId(int id) {

		Session session = HibernateABMUtil.getSessionFactory().openSession();
		session.beginTransaction();
		PersonaEntity per = (PersonaEntity) session.get(PersonaEntity.class, id);
		HibernateABMUtil.shutdown();

		return per;

	}

	public static void deletePersona(PersonaEntity per) {

		Session session = HibernateABMUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(per);
		session.getTransaction().commit();
		HibernateABMUtil.shutdown();
	}
		
		public static List getPernombre(String busqueda) {

			Session session = HibernateABMUtil.getSessionFactory().openSession();
			Criteria cr = session.createCriteria(PersonaEntity.class);
			cr.add(Restrictions.like("nombre", busqueda +"%"));
			List results = cr.list();
			HibernateABMUtil.shutdown();

			return results;

		}
	
		public static List<PersonaEntity> getAllPersona() {
			Session session = HibernateABMUtil.getSessionFactory().openSession();	
			List<PersonaEntity> listadoPer = new ArrayList<PersonaEntity>();
			listadoPer = session.createQuery("From PersonaEntity").list();
			session.close();
			HibernateABMUtil.shutdown();
			return listadoPer;

		}



	}

