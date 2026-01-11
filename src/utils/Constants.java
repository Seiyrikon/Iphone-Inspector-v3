package utils;

public enum Constants {
    APP_TITLE("CellWeGo Iphone Inspector"),

    MINIMIZE_BUTTON("_"),
    MAXIMIZE_BUTTON("▢"),
    CLOSE_BUTTON("X"),

    SCAN("Scan"),
    SCAN_ICON("/resources/icons/scan-icon.png"),

    GENERATE("Generate"),
    GENERATE_ICON("/resources/icons/generate-icon.png"),

    PRINT("Print"),
    PRINT_ICON("/resources/icons/print-icon.png"),

    SIZE120X80("120mm X 80mm"),
    SIZE20X80("30mm X 90mm"),
    SIZE20X80_BLACK("30mm X 90mm Black"),
    SIZE_ICON("/resources/icons/size-icon.png"),
    SIZE_ICON_BlACK("/resources/icons/size-icon-black.png"),
    DISCONNECTED_ICON("/resources/icons/disconnected-icon.png"),
    PHONE_FAILED("/resources/icons/phone-failed-icon.png"),
    PRINTER_FAILED("/resources/icons/printer-failed-icon.png"),

    IDEVICE_ID("idevice_id.exe"),
    DEVICE_LIST("-l"),

    IDEVICE_INFO("ideviceinfo.exe"),

    IMEI("IMEI/MEID: "),
    IMEI2("IMEI2: "),
    EID("EID: "),
    SERIAL_NUMBER("(S) Serial No: "),
    STORAGE_TYPE("Storage: "),
    COLOR_TYPE("Color: "),
    MODEL_NUMBER("Model Number: "),
    PRODUCT_NAME("Product Name: "),
    PRODUCT_TYPE("Product Type: "),
    PRODUCT_VERSION("Product Version: "),

    SINGLE_QUOTE_INFO_SEPARATOR("'"),
    SPACE_INFO_SEPARATOR(" "),

    TOOLS_FOLDER("tools/"),

    IPHONE_8("iPhone 8"),
    IPHONE_8_PLUS("iPhone 8 Plus"),
    IPHONE_X("iPhone X"),
    IPHONE_XS("iPhone XS"),
    IPHONE_XS_MAX("iPhone XS Max"),
    IPHONE_XR("iPhone XR"),
    IPHONE_11("iPhone 11"),
    IPHONE_11_PRO("iPhone 11 Pro"),
    IPHONE_11_PRO_MAX("iPhone 11 Pro Max"),
    IPHONE_12_MINI("iPhone 12 mini"),
    IPHONE_12("iPhone 12"),
    IPHONE_12_PRO("iPhone 12 Pro"),
    IPHONE_12_PRO_MAX("iPhone 12 Pro Max"),
    IPHONE_13_PRO("iPhone 13 Pro"),
    IPHONE_13_PRO_MAX("iPhone 13 Pro Max"),
    IPHONE_13_MINI("iPhone 13 Mini"),
    IPHONE_13("iPhone 13"),
    IPHONE_SE("iPhone SE"),
    IPHONE_14("iPhone 14"),
    IPHONE_14_PLUS("iPhone 14 Plus"),
    IPHONE_14_PRO("iPhone 14 Pro"),
    IPHONE_14_PRO_MAX("iPhone 14 Pro Max"),
    IPHONE_15("iPhone 15"),
    IPHONE_15_PLUS("iPhone 15 Plus"),
    IPHONE_15_PRO("iPhone 15 Pro"),
    IPHONE_15_PRO_MAX("iPhone 15 Pro Max"),
    IPHONE_16("iPhone 16"),
    IPHONE_16_PLUS("iPhone 16 Plus"),
    IPHONE_16_PRO("iPhone 16 Pro"),
    IPHONE_16_PRO_MAX("iPhone 16 Pro Max"),
    IPHONE_16_E("iPhone 16e"),
    IPHONE_17("iPhone 17"),
    IPHONE_17_PRO("iPhone 17 Pro"),
    IPHONE_17_PRO_MAX("iPhone 17 Pro Max"),
    
    PDF_VIEWER_TITLE("Iphone Label Viewer"),
    PDF_PATH("src/resources/pdf/"),
    PDF_120X80("pdf_120x80.pdf"),
    PDF_20X80("pdf_20x80.pdf"),
    
    EMPTY_STRING(""),
    NA_STRING("N/A"),
    
    STRING_ZPL2PDF("ZPL2PDF.exe"),
    STRING_ZPL_I("-i"),
    STRING_ZPL_O("-o"),
    STRING_ZPL_N("-n"),
    STRING_ZPL_Z("-z"),

    STRING_DOT_PDF(".pdf"),
    OUTPUT_FOLDER("src/resources/pdf/"),

    CELLWEGO_ICON("/resources/icons/cellwego-icon.png");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
