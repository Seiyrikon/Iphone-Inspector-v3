package model;

import lombok.Data;

@Data
public class IphoneLabelInformation {
    private String imei = "";
    private String imei2 = "";
    private String eid = "";
    private String serialNo = "";
    private String storageType = "";
    private String version = "";
    private String modelRegion = "";
    private String productName = "";
    private String productType = "";
    private String productColor = "";
    private String productVersion = "";

    private boolean is120x80 = false;
    private boolean is30x90 = false;
    private boolean is100x75 = false;
}
