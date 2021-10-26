/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.JdbiDaoFactory;
import dao.ProductDAO;
import gui.MainMenu;
/**
 *
 * @author benstacey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductDAO dao = JdbiDaoFactory.getProductDAO();
        MainMenu frame = new MainMenu(dao);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
