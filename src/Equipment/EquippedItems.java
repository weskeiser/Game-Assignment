package Equipment;

import java.util.EnumMap;

import ENUMS.EquipmentSlots;
import Interfaces.*;

public class EquippedItems implements IEquipmentManager {
  private EnumMap<EquipmentSlots, IEquipment> equippedItems = new EnumMap<EquipmentSlots, IEquipment>(
      EquipmentSlots.class);

  public EquippedItems() {
    System.out.println(equippedItems);
  }

  public boolean equip(IEquipment item) {
    return true;
  };

  public boolean unEquip(IEquipment item) {
    return true;
  };

  public boolean displayItems() {
    return true;
  }

  public boolean displayArmorAttributes() {
    return true;
  }

}
