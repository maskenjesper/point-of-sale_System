package se.kth.iv1350.pos.data;

import se.kth.iv1350.pos.DTO.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton class that acts as a registry for ItemDTO objects
 */
public class ItemRegistry {
    static private ItemRegistry instance;

    private List<ItemDTO> itemList;

    private ItemRegistry() {
        itemList = new ArrayList<>();
        itemList.add(new ItemDTO(1, 25, "Naturens sportdryck", "Mjölk", "SEK", 0.2));
        itemList.add(new ItemDTO(2, 20, "Nybakat surdegsbröd", "Levain", "SEK", 0.3));
    }

    /**
     * Creates an instance of the class if there is none already and returns it. If there already is an instance it
     * returns that instance
     * @return The one and only instance of this class
     */
    public static ItemRegistry getInstance() {
        if (instance == null)
            instance = new ItemRegistry();
        return instance;
    }

    /**
     * Makes a deepcopy of this instances List of ItemDTOs
     * @return Deepcopy of itemList
     */
    public List<ItemDTO> getItemList() {
        List<ItemDTO> itemListCopy = new ArrayList<>();
        for (ItemDTO item : itemList)
            itemListCopy.add(item);
        return itemListCopy;
    }
}
