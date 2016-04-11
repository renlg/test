package com.renlg.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.util.HtmlUtils;

import com.renlg.exception.SysRunException;
import com.renlg.string.StringUtil;

/**
 * 基于Apache POI的Excel工具类
 * 作者:徐承恩
 * 日期:2014-07-23
 * 邮箱:xce@sumpay.cn
 */
public class ExcelUtil {


    /**
     * 单元格默认值
     */
    private static String cellDefVal = "";

    /**
     * 将HSSFSheet封装成Map集合
     * @param hssfSheet 页
     * @param beginRowIndex 起始行（从0开始）
     * @param beginColIndex 起始列（从0开始）
     * @param rowTotal 取多少行
     * @param colTotal 取多少列
     * @return Map
     */
    public static Map<Integer,String[]> getContentMapByHSSF(HSSFSheet hssfSheet,int beginRowIndex,int beginColIndex,int rowTotal,int colTotal){
        Map<Integer, String[]> dataMap = new LinkedHashMap<Integer, String[]>();
        HSSFRow row = null;
        String[] cellVals = null;

        //循环所有有效行
        for(int rowIndex = beginRowIndex; rowIndex <= (beginRowIndex + rowTotal);rowIndex++){
            row = hssfSheet.getRow(rowIndex);
            if(row == null){
              continue;
            }
            cellVals = new String[colTotal];

            //循环列并取值
            for (int currArrIndex = 0, currColIndex = beginColIndex;currColIndex < (beginColIndex + colTotal); currColIndex++, currArrIndex++) {
                cellVals[currArrIndex] = getCellValue(row, currColIndex);
            }
            // 循环整行内容，看所有单元格是否都为空，只要有一个不为空，则返回true
            boolean isNotNull = false;
            for (Object cellVal : cellVals) {
                if (StringUtil.isNotBlank(cellVal.toString())) {
                    isNotNull = true;
                    break;
                }
            }
            // 只要其中一个单元格内有值，则把它加到Map中
            if (isNotNull) {
                dataMap.put(row.getRowNum(), cellVals);
            }
        }
        return dataMap;
    }


    private static String getCellValue(HSSFRow currRow, int currColIndex) {
        HSSFCell cell = currRow.getCell(currColIndex);
        String cellVal = getCellVal(cell);
        return cellVal;
    }

    /**
     * 得到指定单元格的值，返回字符串型。如果单元格式为空，则返回""
     * @param cell
     * @return
     */
    private static String getCellVal(HSSFCell cell) {
        // 注意：这里只能为""，而不能是null，否则在ExcelUtils中可能会抛空指针
        String cellVal = cellDefVal;
        if (cell != null) {
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    cellVal = toString(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_ERROR:
                    cellVal = toString(cell.getErrorCellValue());
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    cellVal = toString(cell.getCellFormula());
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    // 由于日期型与数字型都返回此形式，所以需判断一下：
//                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                        cellVal = toString(DateUtil.formatDate(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"));
//                    } else {
//                        double d = cell.getNumericCellValue();
//                        cellVal = new DecimalFormat("0").format(d);
//                    }
//                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    cellVal = toString(cell.getStringCellValue());
                    break;
            }
        }
        return cellVal;
    }

    /**
     * 转字符串方法并且将字符串左右去空
     * @param obj
     * @return
     */
    private static String toString(Object obj) {
        if (obj == null) {
            return cellDefVal;
        }
        String str = String.valueOf(obj);
        if (StringUtil.isNotBlank(str)) {
            int slen = str.length();
            str = str.trim();
            //没有变化，则非半角空格
            if(str.trim().length() == slen){
                str = StringUtil.trim95(str);
            }
            return HtmlUtils.htmlEscape(str);
        } else {
            return cellDefVal;
        }
    }

    /**
     * 获得HSSFWorkbook
     * @param inputStream 输入流
     * @return HSSFWorkbook
     */
    public static HSSFWorkbook getWorkbookByInputStream(InputStream inputStream){
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new SysRunException("解析XLS文件出现错误！",e);
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new SysRunException("关闭流时出现异常",e);
                }
            }
        }
        return workbook;
    }

    /**
     * 获得HSSFSheet列表
     * @param hssfWorkbook
     * @return list
     */
    public static List<HSSFSheet> getHSSFSheetList(HSSFWorkbook hssfWorkbook){
        List<HSSFSheet> hssfSheets = null;
        for (int i = 0;i < hssfWorkbook.getNumberOfSheets();i++){
            hssfSheets.add(hssfWorkbook.getSheetAt(i));
        }
        return hssfSheets;
    }

}
