import metier.CardReaderManager;
import org.w3c.dom.ls.LSOutput;

public class TestApplic {

    public static void main(String[] args) {
        CardReaderManager manager = new CardReaderManager();
        boolean ok;
        System.out.print("On active tout...");
        ok = manager.activateAll(true);
        System.out.println(ok);
        System.out.println("1");
        System.out.print("Désactive 112233...");
        ok = manager.activate(112233, false);
        System.out.println(ok);
        System.out.print("Désactive 224466...");
        ok = manager.activate(224466, false);
        System.out.println(ok);
        System.out.println("2");
        System.out.print("ActiveNoWait 224466...");
        manager.activateNoWait(224466, true);

//        System.out.println("Printer B4.18: true ==> false");    // doit être affiché chaque fois qu'un cardReader a été activé ou désactivé
        System.out.println(manager);                            // affiche l'état de tous les cardReaders
    }
}