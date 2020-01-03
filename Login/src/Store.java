import util.Messages;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class Store {

    private static List<Usuario> usuarios = new ArrayList<>();  // Almacena todos los usuarios registrados en la aplicación
    private static List<Mensaje> mensajes = new ArrayList<>();  // Almacena todos los mensajes existentes en la aplicación
    private static Long numeroMensajes = 0L;
    private static Long numeroUsuarios = 0L;

    public Store() {}


    public static boolean existeUsuario(String usr){
        for(Usuario u: usuarios)
            if(u.getNombreUsuario().equalsIgnoreCase(usr)) return true;

        return false;
    }

    public static boolean existeMensaje(Long usr){

        for(Mensaje m: mensajes)
            if(m.getIdMensaje().equals(usr)) return true;

        return false;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(List<Usuario> usuarios) {
        Store.usuarios = usuarios;
    }

    public static List<Usuario> getUsuarios(String strUsr) {
        List<Usuario> aux = new ArrayList<>();

        for(Usuario u:usuarios){
            if(u.getNombreUsuario().equalsIgnoreCase(strUsr))
                continue;
            aux.add(u);
        }

        return aux;
    }

    public static Long getNextUserId(){
        return numeroUsuarios +1;
    }

    public static void addUsuario(Usuario usuario){
        Store.usuarios.add(usuario);
        numeroUsuarios++;
        for(Usuario u: usuarios)
            System.out.println("INFO: agregado usuario: " + u.getNombreUsuario());
    }

    public static void userLogIn(String usr){
        for(Usuario u:usuarios)
            if(u.getNombreUsuario().equalsIgnoreCase(usr))
                u.setEstadoUsuario(true);

        System.out.println("[INFO]- Se ha logeado "+usr);
    }

    public static void userLogOut(String usr){
        for(Usuario u:usuarios)
            if(u.getNombreUsuario().equalsIgnoreCase(usr))
                u.setEstadoUsuario(false);

        System.out.println("[INFO]- Se ha deslogeado "+ usr);
    }

    public static List<Mensaje> getMensajes(String str) {
        testMensajes();

        if (!mensajes.isEmpty()) {
            List<Mensaje> aux = new ArrayList<>();
            for (Mensaje m : mensajes)
                if (m.getReceptorMensaje().equalsIgnoreCase(str))
                    aux.add(m);
            return aux;
        }

        return null;
    }

    public static void setMensajes(List<Mensaje> mensajes) {
        Store.mensajes = mensajes;
    }

    public static void addMensaje(Mensaje mensaje){
        Store.mensajes.add(mensaje);
        numeroMensajes++;
    }

    public static void eliminarMensaje(Mensaje mensaje){
        mensajes.remove(mensaje);
    }

    public static void testMensajes(){
        mensajes.add(new Mensaje( Long.valueOf(String.valueOf("235")), new Date(),"Carlos","Soledad","Mensaje "+235));
        for(int i=17;i > 0; i--)
            mensajes.add(new Mensaje( Long.valueOf(String.valueOf(i)), new Date(),"Pachuco"+i,"CARLOS","Mensaje "+i));
    }

    public static Long getNextMsgId() {
        return numeroMensajes +1;
    }
}
