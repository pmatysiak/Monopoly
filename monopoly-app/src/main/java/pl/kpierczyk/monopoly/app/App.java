import pl.kpierczyk.monopoly.model.*;
import pl.kpierczyk.monopoly.view.*;
import pl.kpierczyk.monopoly.controller.*;

package pl.kpierczyk.monopoly.app;

// Starting Module

public class App 
{
    public static void main( String[] args )
    {
        /*!!!!!!!!!!!!!!!!!ZMIEŃ ŚCIEŻKĘ NA WZGLĘDNĄ!!!!!!!!!!!!!!!!!!*/
        Model model  = new Model("F:\\VS Code Projects\\Java\\Monopoly\\monopoly-model\\src\\main\\java\\pl\\sources\\config.txt");
        Viewer view = new View(model);
        Controller controller = new Controller(model, view);
    }
}
