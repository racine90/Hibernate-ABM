package hibernate.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DAO.personaDAO;
import DAO.ventaDAO;
import hibernate.test.dto.PersonaEntity;
import hibernate.test.dto.VentaEntity;

public class AppHibernateABM {

	public static void main(String[] args) {

		System.out.println(" ABM de personas ");
		Scanner sc = new Scanner(System.in);
		int opcion = mostrarMenu(sc);
		switch (opcion) {

		case 1:
			alta(sc);
			break;
		case 2:
			modificacion(sc);
			break;
		case 3:
			baja(sc);
			break;
		case 4:
			mostrarListadoPersona();
			break;
		case 5:
			buscar(sc);
			break;
		case 6:
			cargarVenta(sc);
			break;
		case 0:
			break;
		default:
			break;
		}
		opcion = mostrarMenu(sc);

	}

	private static void buscar(Scanner sc) {
		System.out.println("BUSCAR POR NOMBRE");
		System.out.println("Ingrese nombre exacto");
		String busqueda = sc.next();
		List<PersonaEntity> resultadoBusqueda = personaDAO.getPernombre(busqueda);

		System.out.println("ID|NOMBRE|EDAD|FECHA DE NACIMIENTO");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (PersonaEntity per : resultadoBusqueda) {

			String fechaNacimiento = sdf.format(per.getFechaNacimiento());

			System.out

					.println(per.getPersonaId() + " " + per.getNombre() + " " + per.getEdad() + " " + fechaNacimiento);

		}

	}

	private static void baja(Scanner sc) {
		System.out.println("BAJA");
		System.out.println("Ingrese ID");
		int id = sc.nextInt();

		PersonaEntity per = new PersonaEntity();

		per = personaDAO.getPerId(id);

		if (per == null) {
			System.out.println("No se encontro, ingrese otro ID");
			modificacion(sc);

		} else {
			mostrarPersona(per);
			System.out.println("¿ Esta seguro ?");
			System.out.println("1.SI \n 2.NO");
			int op = sc.nextInt();
			if (op == 1) {

				personaDAO.deletePersona(per);
				System.out.println("Se elimino resgitro exitosamente");

			} else {

				mostrarMenu(sc);

			}

		}

	}

	private static void mostrarPersona(PersonaEntity per) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String fechaNacimiento = sdf.format(per.getFechaNacimiento());
		System.out.println(per.getPersonaId() + " " + per.getNombre() + " " + per.getEdad() + " " + fechaNacimiento);

	}

	private static void cargarVenta(Scanner sc) {

		System.out.println("BIENVENIDO AL SISTEMA DE VENTAS");
		System.out.println("Ingrese ID_PERSONA");
		int idPersona = sc.nextInt();
		PersonaEntity per = new PersonaEntity();
		per = personaDAO.getPerId(idPersona);

		if (per == null) {
			System.out.println("No se encontro, Ingrese ID_PERSONA");
			modificacion(sc);
		} else {

			mostrarPersona(per);
			System.out.println("Ingrese importe:");
			int importe = sc.nextInt();
			Date fechaVenta = new Date();
			long ahoraLong = System.currentTimeMillis();
			fechaVenta.setTime(ahoraLong);
			VentaEntity venta = new VentaEntity();
			venta.setPersonaEntity(per);
			venta.setImporte(importe);
			venta.setFechaVenta(fechaVenta);

			ventaDAO.saveOrUpdateVenta(venta);

			System.out.println("Venta ingresada exitosamente");

		
		}

	}

	private static void mostrarListadoPersona() {

		List<PersonaEntity> listaper = personaDAO.getAllPersona();

		System.out.println("ID|NOMBRE|EDAD|F.NACIM");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (PersonaEntity per : listaper) {

			String fechaNacimiento = sdf.format(per.getFechaNacimiento());

			System.out

					.println(per.getPersonaId() + " " + per.getNombre() + " " + per.getEdad() + " " + fechaNacimiento);

		}

	}

	private static void modificacion(Scanner sc) {
		System.out.println("MODIFICACION");
		System.out.println("Ingrese el ID que desea modificar:");
		int id = sc.nextInt();
		PersonaEntity per = new PersonaEntity();

		per = personaDAO.getPerId(id);
		if (per == null) {

			System.out.println("No se encontro, Ingrese ID");
			modificacion(sc);

		} else {

			mostrarPersona(per);

			System.out.println("¿ Qué columna desea modificar ?");
			System.out.println("1.Nombre \n2.Fecha de nacimiento \n3.Salir");
			int option = sc.nextInt();
			while (option != 3) {

				try {
					switch (option) {
					case 1:
						System.out.println("Ingrese Nombre");
						String nomNew = sc.next();
						per.setNombre(nomNew);

						personaDAO.saveOrUpdatePersona(per);
						break;

					case 2:
						System.out.println("Ingrese fecha nacimiento (aaaa-mm-dd):");
						String fechaNew = sc.next();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date fechaNacimiento = sdf.parse(fechaNew);
						per.setFechaNacimiento(fechaNacimiento);

						int edad = HibernateABMUtil.calcularEdad(fechaNacimiento);
						per.setEdad(edad);
						personaDAO.saveOrUpdatePersona(per);

					}

				} catch (ParseException e) {

					System.out.println("Fecha ingresada de manera incorrecta");

					break;
				}

				System.out.println("Modificado exitosamente");
				mostrarPersona(per);
			}

		}

	}

	private static void alta(Scanner sc) {
		System.out.println("ALTA");
		System.out.println("Ingrese nombre:");
		String nombre = sc.next();

		System.out.println("Ingrese fecha de nacimiento (aaaa-mm-dd):");

		String fechaNacimientoStng = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaNacimiento = null;
		try {
			fechaNacimiento = sdf.parse(fechaNacimientoStng);
			int edad = HibernateABMUtil.calcularEdad(fechaNacimiento);
			PersonaEntity per = new PersonaEntity();
			per.setNombre(nombre);
			per.setEdad(edad);
			per.setFechaNacimiento(fechaNacimiento);

			personaDAO.saveOrUpdatePersona(per);
			System.out.println("Registro ingresado correctamente");
			mostrarPersona(per);
		} catch (ParseException e) {

			System.out.println("Verifique la fecha ingresada");

		}

	}

	private static int mostrarMenu(Scanner sc) {
		System.out.println(

				"Menu opciones: \n1. Alta \n2. Modificacion \n3. Baja \n4. Listado Personas \n5. Buscar por nombre \n6. Cargar venta \n0. Salir");

		int opcion = sc.nextInt();

		return opcion;

	}

}
