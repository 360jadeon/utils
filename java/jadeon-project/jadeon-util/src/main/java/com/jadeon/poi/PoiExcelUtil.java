package com.jadeon.poi;

import com.jadeon.json.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoiExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 获取EXCEL 所有sheet数据 String 类型
     * @param fileSrc: Excel详细路径
     * @return
     */
    public static List<HashMap<String, String>> getPoiExcelStringData(String fileSrc){
        try{
            if(StringUtils.isNotEmpty(fileSrc)){
                File file = new File(fileSrc);
                if(file.exists())
                    return getExcelSheetsStringData(file);
            }
        }
        catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    /**
     * 功能：获取EXCEL 所有sheet数据 String 类型
     * @param file  文件
     * @return HashMap<String, String>
     */
    public static  List<HashMap<String, String>> getExcelSheetsStringData(File file) {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        try{
            Workbook wb = getWorkbook(file);
            int sheetSize = wb.getNumberOfSheets();
            for(int i = 0; i<sheetSize; i++){
                Sheet sheet = wb.getSheetAt(i);
                map = getSheetData(sheet);
                list.add(map);
            }
            return list;
        }
        catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    /**
     * 导入Excel表格
     * @param file
     * @return
     */
    public static Workbook getWorkbook(File file){
        try{
            if(file.getName().endsWith(".xlsx")){
                XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
                return xwb;
            }else if(file.getName().endsWith(".xls")){
                HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
                return hwb;
            }
            return null;
        } catch (FileNotFoundException e) {
            LOGGER.error(e.toString());
        } catch (IOException e) {
            LOGGER.error(e.toString());
        } catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    /**
     * 逐个读取sheet数据
     * @param sheet
     * @return
     */
    public static HashMap<String, String> getSheetData(Sheet sheet){
        HashMap<String, String> map = new HashMap<String, String>();
        try{
            int rownum = 0;//从第一行开始读取数据
            while (true){
                Row row = sheet.getRow(rownum);
                if(null != row){
                    int cellnum = 0;//从第一列开始读取数据
                    while (true){
                        Cell cell = row.getCell(cellnum);
                        if(null != cell){
                            String key = rownum+"/"+ cellnum;
                            String value = getCellStringValue(cell);
                            map.put(key, value);
                            cellnum++;
                        }else
                            break;
                    }
                    rownum++;
                }else{
                    map.put("rowSize", rownum+1+"");
                    break;
                }
            }
            return map;
        }
        catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    /**
     * 把cell值转换为    字符串
     * @param cell
     * @return
     */
    public static String getCellStringValue(Cell cell){
        String data = null;
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC:// 数字日期类型
                if(HSSFDateUtil.isCellDateFormatted(cell)){//日期格式
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    data = format.format(cell.getDateCellValue());
                }else{
                    data = cell.getNumericCellValue()+"";
                    BigDecimal bd = new BigDecimal(data);
                    data = bd.toPlainString();
                }
                break;
            case Cell.CELL_TYPE_STRING://字符串
                data = cell.getStringCellValue().trim();
                data = StringUtils.isNotEmpty(data)?data:"";
                break;
            case Cell.CELL_TYPE_FORMULA://公式
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                data = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_BLANK://空白
                data = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN://布尔类型
                data = cell.getBooleanCellValue()+"";
                break;
            case Cell.CELL_TYPE_ERROR:
                data = "";
                break;
        }
        return data;
    }

}

