package DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

import hibernate.test.HibernateABMUtil;
import hibernate.test.dto.VentaEntity;

public class ventaDAO {

	public static void saveOrUpdateVenta(VentaEntity venta) {

		Session session = HibernateABMUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(venta);
		session.getTransaction().commit();
		HibernateABMUtil.shutdown();

	}

	public static List<VentaEntity> getAllVentas() {

		Session session = HibernateABMUtil.getSessionFactory().openSession();
		List<VentaEntity> listadoVentas = new ArrayList<VentaEntity>();
		listadoVentas = session.createQuery("From VentaEntity").list();
		session.close();
		HibernateABMUtil.shutdown();
		return listadoVentas;

	}

}
