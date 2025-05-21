package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class Sesion {

    private User usuario;
    private static Sesion instance;
    private final InvitadoTemplate invitadoTemplate;
    private final AdminTemplate adminTemplate;
    private final ClinteTemplate clienteTemplate;
    private StrategyPush smsGuess = new StrategyPush();
    private Invitado invitado;

    private Sesion() {
        this.invitadoTemplate = new InvitadoTemplate();
        this.adminTemplate = new AdminTemplate();
        this.clienteTemplate = new ClinteTemplate();
        this.invitado = new Invitado("Invitado", "No@email", "No phone", invitadoTemplate, smsGuess);
    }

    public static Sesion getInstance() {
        if (instance == null) {
            synchronized (Sesion.class) {

                if (instance == null) {
                    instance = new Sesion();
                }
            }
        }
        return instance;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public InvitadoTemplate getInvitadoTemplate() {
        return invitadoTemplate;
    }

    public StrategyPush getSmsGuess() {
        return smsGuess;
    }

    public Invitado getInvitado() {
        return invitado;
    }

    public AdminTemplate getAdminTemplate() {
        return adminTemplate;
    }

    public ClinteTemplate getClienteTemplate() {
        return clienteTemplate;
    }
}
