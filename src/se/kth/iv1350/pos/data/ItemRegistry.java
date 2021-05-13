package se.kth.iv1350.pos.data;

import se.kth.iv1350.pos.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    static private ItemRegistry instance;

    private List<ItemDTO> itemList;

    private ItemRegistry() {
        itemList = new ArrayList<>();
        itemList.add(new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 0.2));
        itemList.add(new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 0.3));
    }

    public static ItemRegistry getInstance() {
        if (instance == null)
            instance = new ItemRegistry();
        return instance;
    }

    public List<ItemDTO> getItemList() {
        List<ItemDTO> itemList = new ArrayList<>();
        for (ItemDTO item : this.itemList)
            itemList.add(item);
        return itemList;
    }
}
