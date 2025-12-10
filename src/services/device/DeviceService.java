package services.device;

import java.util.List;

import model.IphoneModel;
import utils.CommandExecutor;
import utils.CommandResult;
import utils.Constants;

public class DeviceService {

    CommandExecutor commanderService;
    List<String> commands;

    public DeviceService(CommandExecutor commanderService) {
        this.commanderService = commanderService;
    }

    public CommandResult detect() {
        CommandResult result = CommandExecutor.runTool(Constants.IDEVICE_ID.get(),
                Constants.DEVICE_LIST.get());
        return result;
    }

    public IphoneModel extractInfo() {
        IphoneModel iphone = new IphoneModel();
        CommandResult result = CommandExecutor.runTool(Constants.IDEVICE_INFO.get());

        String[] parts = result.output.split(Constants.SINGLE_QUOTE_INFO_SEPARATOR.get());

        for (String part : parts) {
            if (part.contains("InternationalMobileEquipmentIdentity:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());
                iphone.setImei(labels[1]);
                continue;
            }

            if (part.contains("InternationalMobileEquipmentIdentity2:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());
                iphone.setImei2(labels[1]);
                continue;
            }

            if (part.contains("SerialNumber:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());

                if (labels[0].matches("(?i)^SerialNumber:$"))
                    iphone.setSerialNo(labels[1]);
                continue;
            }

            if (part.contains("ModelNumber:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());
                iphone.setModel(labels[1]);
                continue;
            }

            if (part.contains("RegionInfo:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());
                iphone.setRegion(labels[1]);
                continue;
            }

            if (part.contains("ProductName:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());
                iphone.setProductName(labels[1]);
                continue;
            }

            if (part.contains("ProductType:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());

                if ("iPhone10,1".equals(labels[1]) 
                    || "iPhone10,4".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_8.get());
                } else if ("iPhone10,2".equals(labels[1])
                    || "iPhone10,5".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_8_PLUS.get());
                } else if ("iPhone10,3".equals(labels[1]) 
                    || "iPhone10,6".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_X.get());
                } else if ("iPhone11,2".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_XS.get());
                } else if ("iPhone11,4".equals(labels[1]) 
                    || "iPhone11,6".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_XS_MAX.get());
                } else if ("iPhone11,8".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_XR.get());
                } else if ("iPhone12,1".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_11.get());
                } else if ("iPhone12,3".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_11_PRO.get());
                } else if ("iPhone12,5".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_11_PRO_MAX.get());
                } else if ("iPhone13,1".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_12_MINI.get());
                } else if ("iPhone13,2".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_12.get());
                } else if ("iPhone13,3".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_12_PRO.get());
                } else if ("iPhone13,4".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_12_PRO_MAX.get());
                } else if ("iPhone14,2".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_13_PRO.get());
                } else if ("iPhone14,3".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_13_PRO_MAX.get());
                } else if ("iPhone14,4".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_13_MINI.get());
                } else if ("iPhone14,5".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_13.get());
                } else if ("iPhone14,6".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_SE.get());
                } else if ("iPhone14,7".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_14.get());
                } else if ("iPhone14,8".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_14_PLUS.get());
                } else if ("iPhone15,2".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_14_PRO.get());
                } else if ("iPhone15,3".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_14_PRO_MAX.get());
                } else if ("iPhone16,1".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_15.get());
                } else if ("iPhone16,2".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_15_PLUS.get());
                } else if ("iPhone16,3".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_15_PRO.get());
                } else if ("iPhone16,4".equals(labels[1])) {
                    iphone.setProductType(Constants.IPHONE_15_PRO_MAX.get());
                }

                continue;
            }

            if (part.contains("ProductVersion:")) {
                String[] labels = part.split(Constants.SPACE_INFO_SEPARATOR.get());
                iphone.setProductVersion(labels[1]);
                continue;
            }
        }

        if ("iPhone10,1".equals(iphone.getProductType()) 
                    || "iPhone10,4".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_8.get());
                } else if ("iPhone10,2".equals(iphone.getProductType())
                    || "iPhone10,5".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_8_PLUS.get());
                } else if ("iPhone10,3".equals(iphone.getProductType()) 
                    || "iPhone10,6".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_X.get());
                } else if ("iPhone11,2".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_XS.get());
                } else if ("iPhone11,4".equals(iphone.getProductType()) 
                    || "iPhone11,6".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_XS_MAX.get());
                } else if ("iPhone11,8".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_XR.get());
                } else if ("iPhone12,1".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_11.get());
                } else if ("iPhone12,3".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_11_PRO.get());
                } else if ("iPhone12,5".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_11_PRO_MAX.get());
                } else if ("iPhone13,1".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_12_MINI.get());
                } else if ("iPhone13,2".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_12.get());
                } else if ("iPhone13,3".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_12_PRO.get());
                } else if ("iPhone13,4".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_12_PRO_MAX.get());
                } else if ("iPhone14,2".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_13_PRO.get());
                } else if ("iPhone14,3".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_13_PRO_MAX.get());
                } else if ("iPhone14,4".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_13_MINI.get());
                } else if ("iPhone14,5".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_13.get());
                } else if ("iPhone14,6".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_SE.get());
                } else if ("iPhone14,7".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_14.get());
                } else if ("iPhone14,8".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_14_PLUS.get());
                } else if ("iPhone15,2".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_14_PRO.get());
                } else if ("iPhone15,3".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_14_PRO_MAX.get());
                } else if ("iPhone16,1".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_15.get());
                } else if ("iPhone16,2".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_15_PLUS.get());
                } else if ("iPhone16,3".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_15_PRO.get());
                } else if ("iPhone16,4".equals(iphone.getProductType())) {
                    iphone.setProductType(Constants.IPHONE_15_PRO_MAX.get());
                }

        return iphone;
    }
}
