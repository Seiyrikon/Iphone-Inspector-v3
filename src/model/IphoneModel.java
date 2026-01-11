package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IphoneModel {
    private String uid;
    private String imei = "";
    private String imei2 = "";
    private String eid;
    private String serialNo = "";
    private String storageType;
    private String version = "";
    private String icc = "";
    private String fcc;
    private String model = "";
    private String region = "";
    private String productName = "";
    private String productType = "";
    private String productColor = "";
    private String productVersion = "";

    private String[] iphone8And8PlusColors = {"Gold", "Red", "Silver", "Space Gray"};
    private String[] iphoneXAndXsAndXsMaxColors = { "Gold", "Silver", "Space Gray"};
    private String[] iphoneXrColors = {"Black", "Blue", "Coral", "Red", "White", "Yellow"};
    private String[] iphone11Colors = {"Black", "Green", "Purple", "Red", "White", "Yellow"};
    private String[] iphone11ProAnd11ProMaxColors = {"Gold", "Midnight Green", "Silver", "Space Gray"};
    private String[] iphone12And12MiniColors = {"Black",  "Blue", "Green", "Purple", "Red", "White"};
    private String[] iphone12ProAnd12ProMaxColors = {"Gold", "Graphite", "Pacific Blue", "Silver"};
    private String[] iphone13Ad13MiniColors = {"Blue", "Green", "Midnight", "Pink", "Red", "Starlight"};
    private String[] iphone13ProAnd13ProMaxColors = {"Alpine Green", "Gold", "Graphite", "Sierra Blue", "Silver"};
    private String[] iphoneSeColors = {"Midnight", "Red", "Starlight"};
    private String[] iphone14And14PlusColors = {"Blue", "Midnight", "Purple", "Red", "Starlight", "Yellow"};
    private String[] iphone14ProAnd14ProMaxColors = {"Deep Purple", "Gold", "Silver", "Space Black"};
    private String[] iphone15And15PlusColors = {"Black", "Blue", "Green", "Pink", "Yellow"};
    private String[] iphone15ProAnd15ProMaxColors = {"Black Titanium", "Blue Titanium", "Natural Titanium", "White Titanium"};
    private String[] iphone16And16PlusColors = {"Black", "Pink", "Teal", "Ultramarine", "White"};
    private String[] iphone16ProAnd16ProMaxColors = {"Black Titanium", "Desert Titanium", "Natural Titanium", "White Titanium"};
    private String[] iphone17Colors = {"Black", "Lavender", "Mist Blue", "Sage", "White"};
    private String[] iphone17ProAnd17ProMaxColors = {"Cosmic Orange", "Deep Blue", "Silver"};

    private String[] storageTypes = {"64GB", "128GB", "256GB", "512GB", "1TB"};

    public boolean isFailed = false;
}
