public class Controller {
    private Model model;
    private View view;
    private UserManager userManager;


    public Controller(Model model, View view, UserManager userManager) {
        this.model = model;
        this.view = view;
        this.userManager = userManager;
    }

    

}
