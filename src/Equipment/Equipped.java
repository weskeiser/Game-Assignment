package Equipment;

import java.util.ArrayList;
import Interfaces.*;

public class Equipped implements IEquipmentManager {
  private IWeapon equippedWeapon;
  private ArrayList<IArmor> equippedArmor = new ArrayList<IArmor>(10);

  Equipped() {
  }

  public boolean equip(IItem item) {
    return true;
  };

  public boolean unEquip(IItem item) {
    return true;
  };

  public boolean displayItems() {
    return true;
  }

  public boolean displayArmorAttributes() {
    return true;
  }

}
