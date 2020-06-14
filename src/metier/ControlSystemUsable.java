package metier;

import outils.SystemKeso;
import outils.SystemStanley;

public class ControlSystemUsable implements ControlSystemFactory {

    class UnknowTypeException extends RuntimeException {
        public UnknowTypeException() {
        }

        public UnknowTypeException(String message) {
            super(message);
        }
    }

    @Override
    public boolean activeControlSystem(String type, boolean activated) {
        switch (type) {
            case "Stanley":
                return SystemStanley.lock(!activated);
            case "Keso":
                return activated ? SystemKeso.open() : SystemKeso.close();
//            //  case "Vigik" : return activated ? SystemVigik.ouvre() : SystemVigik.ferme();       // implémentation future éventuelle
        }
        throw new UnknowTypeException();
    }
}
