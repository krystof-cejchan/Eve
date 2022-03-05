package uncatagorisedAlgorithms;

import java.util.ArrayList;

public class AreTwoArrayValuesEqual_Algo {
	public static boolean areTwoArrayValuesEqual(ArrayList<?> a, ArrayList<?> b) {
		for (Object objectB : b) {
			for (Object objectA : a) {
				if (objectB == objectA || objectB.equals(objectA))
					return true;
			}
		}

		return false;
	}
}
