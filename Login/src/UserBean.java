import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {


    private String nameSessionUser;   // para el Login
    private String registerName;      // para el Registro


    private Long userFiltro;    // para el filtrado de estado de usuarios
    private Long userReceptorMensaje; // usuario a quién se le enivará el mensaje

    private String textMensaje; //mensaje que se enviará
    private List<Usuario> usuariosRegistrados; // Almacena todos los usuarios registrados
    private List<Usuario> temporalUsuarios;    // Almacena los usuarios por filtro
    private List<Filtro> filtrosSelect;

    private Boolean visualizarMensajes;         //para saber si el usuario quiere ver los mensajes
    private List<Mensaje> mensajesRecibidos;    // los mensajes que le han llegado
    private Long idMensajeBorrar;

    private String nombreReceptor;

    public UserBean() {
        iniciarFiltros();
        visualizarMensajes = false;

    }

    @PostConstruct
    public void abc() {
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Carlos", true));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Fabricio", false));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Mauro", true));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Soledad", true));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Manuel", false));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Elena", true));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Socorro", false));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Ricky", true));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Humbeto", false));
        Store.addUsuario(new Usuario(Store.getNextUserId(), "Emanuel", false));
    }

    public String getNameSessionUser() {
        return nameSessionUser;
    }

    public void setNameSessionUser(String nameSessionUser) {
        this.nameSessionUser = nameSessionUser;
        Store.userLogIn(nameSessionUser);
        usuariosRegistrados = Store.getUsuarios(nameSessionUser);
        nombreReceptor="";
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
        Store.addUsuario(new Usuario(Store.getNextUserId(), registerName.toUpperCase(), false));
    }

    public Long getUserFiltro() {
        return userFiltro;
    }

    public void setUserFiltro(Long userFiltro) {
        this.userFiltro = userFiltro;
    }

    public Long getUserReceptorMensaje() {
        return userReceptorMensaje;
    }

    public void setUserReceptorMensaje(Long userReceptorMensaje) {
        this.userReceptorMensaje = userReceptorMensaje;
    }

    public String getTextMensaje() {
        return textMensaje;
    }

    public void setTextMensaje(String textMensaje) {
        this.textMensaje = textMensaje;
    }

    public Boolean getVisualizarMensajes() {
        return visualizarMensajes;
    }

    public void setVisualizarMensajes(Boolean visualizarMensajes) {
        this.visualizarMensajes = visualizarMensajes;
    }

    public List<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public List<Usuario> getTemporalUsuarios() {
        return temporalUsuarios;
    }

    public List<Usuario> getUsuariosRegistrados() {
        usuariosRegistrados = Store.getUsuarios(nameSessionUser);
        return usuariosRegistrados;
    }

    public List<Filtro> getFiltrosSelect() {
        return filtrosSelect;
    }

    public Long getIdMensajeBorrar() {
        return idMensajeBorrar;
    }

    public void setIdMensajeBorrar(Long idMensajeBorrar) {
        this.idMensajeBorrar = idMensajeBorrar;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void validateLogin(FacesContext context, UIComponent component, Object value) {

        if (!Store.existeUsuario((String) value)) {
            FacesMessage message = util.Messages.getMessage("messages", "dontFoundName", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void validateRegister(FacesContext context, UIComponent component, Object value) {

        System.out.println("INFO: El usuario" + (String) value + " existe: " + Store.existeUsuario((String) value));
        if (Store.existeUsuario((String) value)) {
            FacesMessage message = util.Messages.getMessage("messages", "repeatName", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void validateBorrarMensaje(FacesContext context, UIComponent component, Object value) {
        Long aLong = (Long) value;

        if (!Store.existeMensaje(aLong) || value == null || aLong.equals(0L)) {
            FacesMessage message = util.Messages.getMessage("messages", "dontFoundMessage", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void validateMensaje(FacesContext context, UIComponent component, Object value) {
        if(nombreReceptor.isEmpty() || nombreReceptor.equals("")){
            FacesMessage message = util.Messages.getMessage("messages", "dontFoundReceptor", null);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void enviarMensaje() {
        System.out.println("MENSAJE "+textMensaje);
        Store.addMensaje(new Mensaje(Store.getNextMsgId(), new Date(), nameSessionUser, nombreReceptor, textMensaje));
        nombreReceptor="";
        textMensaje = null;

    }

    public void eliminarMensaje() {
        for (Mensaje mensaje : mensajesRecibidos)
            if (mensaje.getIdMensaje().equals(idMensajeBorrar))
                Store.eliminarMensaje(mensaje);

        mensajesRecibidos = Store.getMensajes(nameSessionUser);
    }

    public void iniciarFiltros() {
        this.filtrosSelect = new ArrayList<>();
        this.filtrosSelect.add(new Filtro(1L, "TODOS"));
        this.filtrosSelect.add(new Filtro(2L, "CONECTADOS"));
        this.filtrosSelect.add(new Filtro(3L, "DESCONECTADOS"));
    }

    public void handlerFiltros() {

        temporalUsuarios = new ArrayList<>();
        if (this.userFiltro.equals(1L)) temporalUsuarios = getUsuariosRegistrados();
        if (this.userFiltro.equals(2L)) temporalUsuarios = isusuariosConected(true);
        if (this.userFiltro.equals(3L)) temporalUsuarios = isusuariosConected(false);


    }

    public void handlerReceptor() {

        for (Usuario u : temporalUsuarios)
            if (u.getIdUsuario().equals(userReceptorMensaje))
                nombreReceptor = u.getNombreUsuario();

    }

    public List<Usuario> isusuariosConected(boolean bool) {
        List<Usuario> aux = new ArrayList<>();

        for (Usuario u : getUsuariosRegistrados())
            if (u.getEstadoUsuario() == bool)
                aux.add(u);
        return aux;


    }

    public void cambiarVisible() {

        visualizarMensajes = !visualizarMensajes;

        if (visualizarMensajes)
            verMensajes();

    }

    public void verMensajes() {
        mensajesRecibidos = Store.getMensajes(nameSessionUser);
    }

    public void userLogOut() {

        Store.userLogOut(nameSessionUser);

        //inicializa

        nameSessionUser = null;
        registerName = null;


    }

}
