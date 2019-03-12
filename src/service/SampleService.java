package service;

import io.github.mapper.excel.ExcelMapper;

import java.awt.image.SampleModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kwerenachi Utosu
 * @date 3/9/2019
 */

//@Service
public class SampleService {

    public List<SampleModel> mapExcelFileToClass(){
        File file = new File("C:/src/documents/samplemodel.xsx");
        List<SampleModel> sampleModelList = new ArrayList();

        try {
            sampleModelList = ExcelMapper.mapFromExcel(file)
                    .toObjectOf(SampleModel.class)
                    .fromSheet(0) // if this method not used , called all sheets
                    .map();

        }catch (Throwable e){
            e.printStackTrace();
        }

        return sampleModelList;

    }

    public List<SampleModel> mapExcelFileToClass(byte[] sample){
        byte[] excelFileByteFormat = getExcelFile(sample);
        List<SampleModel> sampleModelList = new ArrayList();

        try {

            sampleModelList = ExcelMapper.mapFromExcel(excelFileByteFormat)
                .toObjectOf(SampleModel.class)
                .fromSheet(0) // if this method not used , called all sheets
                .map();
        }catch (Throwable e){
            e.printStackTrace();
        }

        return sampleModelList;
    }

    private static byte[] getExcelFile(byte[] data) {
        short len = (short) data.length;
        byte[] newBytes = new byte[len + 2];
        newBytes[0] = (byte) (len / 256);
        newBytes[1] = (byte) (len & 255);
        System.arraycopy(data, 0, newBytes, 2, len);
        return newBytes;
    }

}
