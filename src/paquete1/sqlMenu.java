package paquete1;

import java.util.Scanner;

/**
 *
 * @author Ramirez Chero Alejandro
 */


public class sqlMenu extends consultas{

    static String[] menuPrincipal = {
        "Sistema de Atencion Hospitalaria",
        "",
        "",
        "Elija una opcion:",
        "",
        "1) Ver la lista completa de doctores",
        "2) Buscar medico por el ID",
        "3) Cambiar la información de un medico por ID",
        "4) Eliminando un medico por ID",
        "5) Seleccionar horario",
        "6) Ver medicos asistentes",
        "7) Registrar nuevo paciente", // Nueva opción para registrar paciente
        "8) Ver lista completa de pacientes", // Nueva opción para ver lista de pacientes
        "0) Salir",
        "Opcion elegida: "
    };

    static void imprimirMenu(String[] menuToPrint){
        for (int i= 0; i < menuToPrint.length; i++)
            System.out.println(menuToPrint[i]);
    }

    static void eleccion(){
        Scanner entrada = new Scanner(System.in);
        int opcion = entrada.nextInt();
        int id = 0;
        consultas consult = new consultas();
        edicion edit = new edicion();
        GestionPacientes gestionPacientes = new GestionPacientes(); // Instanciamos la clase GestionPacientes
        switch (opcion){
        case 1:
            consult.imprimirListaMedicos();
            break;   
        case 2:
            System.out.println("Digite la ID del medico:");
            id = entrada.nextInt();
            consult.buscarIDMedico(id);
            break;
        case 3:
            System.out.println("Digite la ID del medico:");
            id= entrada.nextInt();
            entrada.nextLine();
            String columna = edit.eleccionDeColumna(entrada);
            entrada.nextLine();
            System.out.println("Ingrese el nuevo dato");
            String nuevoDato = entrada.nextLine();
            edit.cambiarDatoColumna(columna, nuevoDato,id);
            break;
        case 4:
            System.out.println("Digite la ID del medico:");
            id = entrada.nextInt();
            edit.borrarFila(id);
            break;
        case 5:
            edit.actualizarAsistencias(consult.eleccionDeHora(entrada));
            break;
        case 6:
            consult.mostrarDoctoresPresentes();
            break;
        case 7: // Nueva opción para registrar paciente
            gestionPacientes.registrarPaciente();
            break;
        case 8: // Nueva opción para mostrar lista de pacientes
            consult.imprimirListaPaciente();
            break;    
        case 0:
            System.out.println("Gracias por usar el servicio.");
            break;
        }
    }

    public static void main(String[] args) {
        imprimirMenu(menuPrincipal);
        eleccion();
    }
}
