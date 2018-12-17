import controller.BaseController;
import controller.Controller;
import model.BaseModel;
import model.Model;
import view.MainFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame view = new MainFrame();
                BaseModel model = new Model();
                new Controller(view, model);
            }
        });
    }
}
