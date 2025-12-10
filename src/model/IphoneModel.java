package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IphoneModel {
    private String uid;
    private String imei = "Sample imei";
    private String imei2 = "Sample imei2";
    private String eid;
    private String serialNo = "Sample serialNo";
    private String storageType;
    private String version = "Sample version";
    private String icc;
    private String fcc;
    private String model = "model";
    private String region = "region";
    private String productName = "Sample productName";
    private String productType = "iPhone16,4";
    private String productColor = "Sample productColor";
    private String productVersion = "Sample productVersion";

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

    private String[] storageTypes = {"64 GB", "128 GB", "256 GB", "512 GB", "1 TB"};
}
