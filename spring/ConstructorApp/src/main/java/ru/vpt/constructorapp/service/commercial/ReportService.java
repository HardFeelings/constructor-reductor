package ru.vpt.constructorapp.service.commercial;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.vpt.constructorapp.api.exception.BadRequestException;
import ru.vpt.constructorapp.service.util.Tuple;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropEntity;
import ru.vpt.constructorapp.store.entities.commercial.CommercialPropItemEntity;
import ru.vpt.constructorapp.store.entities.commercial.ManagerEntity;
import ru.vpt.constructorapp.store.entities.motor.MotorEntity;
import ru.vpt.constructorapp.store.entities.product.ProductOptionEntity;
import ru.vpt.constructorapp.store.entities.reducer.ReducerEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@Service
public class ReportService {

    private XSSFWorkbook workbook;
    private int cursor;
    private final String SHEET_NAME = "КП";
    private List<Tuple<Integer, Short>> heightList;


    public ByteArrayInputStream report(CommercialPropEntity entity) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook = new XSSFWorkbook(Objects.requireNonNull(Model.class.getClassLoader().getResourceAsStream("reportTemplate.xlsx")));
            if (Objects.isNull(entity.getCommercialPropItems()) || entity.getCommercialPropItems().isEmpty())
                throw new BadRequestException("Невозможно сформировать отчет, отсутствуют элементы", 400);
            cursor = 56;
            fillHeader(entity.getManager(), entity.getNumber(), entity.getPartner(), entity.getTimestamp());
            fillCommItems(entity.getCommercialPropItems());
            //deleteRow(workbook.getSheet(SHEET_NAME), 14, 55);
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillHeader(ManagerEntity manager, String number, String partner, String timestamp) {
        printCell(number, 10, 5);
        printCell(timestamp, 10, 7);
        if (!Objects.isNull(manager))
            printCell(manager.getShortName(), 8, 9);
        printCell(partner, 9, 9);
    }

    private void fillAdditionData(String commNumber, String partner, String date, ManagerEntity manager) {

    }

    private void fillCommItems(List<CommercialPropItemEntity> items) {
        int count = 1;
        for (CommercialPropItemEntity item : items) {
            if (item.getProduct().getProductType().getIdProductType().equals(1L))
                fillMotor(item, count);
            if (item.getProduct().getProductType().getIdProductType().equals(2L))
                fillReducer(item, count);
            if (item.getProduct().getProductType().getIdProductType().equals(3L))
                fillMotorReducer(item, count);
            count++;
        }
    }

    private void fillReducer(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        copyRows(44, 55, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 12;
        printCell(String.valueOf(count), startRow, 1);
        if (!Objects.isNull(item.getProduct())) {
            ReducerEntity reducer = item.getProduct().getReducer();
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(String.valueOf(item.getAmount()), startRow, 4);
            printCell(String.valueOf(item.getProduct().getPrice()), startRow, 7);
            printCell(String.valueOf(item.getProduct().getPrice() + item.getAmount()), startRow, 8);
            printCell(reducer.getReducerType().getReducerTypeName(), startRow + 2, 3);
            printCell(String.valueOf(reducer.getRatio()), startRow + 3, 3);
            printCell(reducer.getReducerMounting().getReducerMountingValue(), startRow + 4, 3);
            printCell(
                    reducer.getReducerInstallationType().getReducerInstallationTypeValue()
                            .toLowerCase().contains("флан") ? "Да" : "Нет", startRow + 5, 3);
            printCell(String.valueOf(reducer.getReducerOutputShaftType().getReducerOutputShaftTypeValue()), startRow + 7, 3);
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 8, 3);
            if (item.getProduct().getOptions() != null && !item.getProduct().getOptions().isEmpty()) {
                for (ProductOptionEntity option : item.getProduct().getOptions()) {
                    copyRows(startRow + 9, startRow + 10, cursor, workbook.getSheet(SHEET_NAME));
                    printCell(option.getProductOptionValue(), startRow + 10, 3);
                    cursor++;
                }
            }


        }

    }

    private void fillMotor(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        copyRows(32, 43, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 12;
        printCell(String.valueOf(count), startRow, 1);
        if (!Objects.isNull(item.getProduct())) {
            MotorEntity motor = item.getProduct().getMotor();
            printCell(item.getProduct().getName(), startRow, 2);
            printCell(String.valueOf(item.getAmount()), startRow, 4);
            printCell(String.valueOf(item.getProduct().getPrice()), startRow, 7);
            printCell(String.valueOf(item.getProduct().getPrice() + item.getAmount()), startRow, 8);
            printCell(String.valueOf(item.getProduct().getRpm()), startRow + 2, 3);
            printCell(String.valueOf(motor.getPower()), startRow + 3, 3);
            printCell(String.valueOf(motor.getEfficiency()), startRow + 4, 3);
            printCell(String.valueOf(motor.getRatedCurrent()), startRow + 5, 3);
            printCell(String.valueOf(motor.getMomentOfInertia()), startRow + 8, 3);
            printCell(String.valueOf(item.getProduct().getWeight()), startRow + 9, 3);
            if (item.getProduct().getOptions() != null && !item.getProduct().getOptions().isEmpty()) {
                if (item.getProduct().getOptions().size() > 1) {
                    cancelMerged(workbook.getSheet(SHEET_NAME),startRow, cursor - 1, 1, 1);
                    cancelMerged(workbook.getSheet(SHEET_NAME),startRow, cursor - 1, 4, 6);
                    cancelMerged(workbook.getSheet(SHEET_NAME),startRow, cursor - 1, 7, 7);
                    cancelMerged(workbook.getSheet(SHEET_NAME),startRow, cursor - 1, 8, 9);
                    List<ProductOptionEntity> options = item.getProduct().getOptions().stream().toList();
                    for (int i = 0; i < options.size(); i++) {
                        if(i != options.size() - 1)
                            copyRows(startRow + 11 + i, startRow + 11 + i, cursor, workbook.getSheet(SHEET_NAME));
                        printCell(options.get(i).getProductOptionValue(), startRow + 11 + i, 3);
                        cursor++;
                    }
                    workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 1, 1));
                    workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 4, 6));
                    workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 7, 7));
                    workbook.getSheet(SHEET_NAME).addMergedRegion(new CellRangeAddress(startRow, cursor - 1, 8, 9));
                } else {
                    ProductOptionEntity option = item.getProduct().getOptions().stream().findFirst().get();
                    printCell(option.getProductOptionValue(), startRow + 11, 3);
                }


            }

        }

    }

    private void fillMotorReducer(CommercialPropItemEntity item, int count) {
        int startRow = cursor;
        copyRows(14, 31, cursor, workbook.getSheet(SHEET_NAME));
        cursor += 18;
        printCell(String.valueOf(count), startRow, 1);
    }

    private void printCell(String value, int rowNumber, int cellNumber) {
        if (value == null)
            value = "";
        XSSFRow row = workbook.getSheet(SHEET_NAME).getRow(rowNumber);
        if (row == null) {
            for (int i = rowNumber; i < workbook.getSheet(SHEET_NAME).getLastRowNum(); i++) {
                row = workbook.getSheet(SHEET_NAME).getRow(i);
                cursor++;
                if (row != null)
                    break;
            }
            row.getCell(cellNumber).setCellValue(value);
        }
        row.getCell(cellNumber).setCellValue(value);
    }

    public static void copyRows(int startRow, int endRow, int pPosition, XSSFSheet sheet) {
        int pStartRow = startRow;
        int pEndRow = endRow;
        int targetRowFrom;
        int targetRowTo;
        int columnCount;
        CellRangeAddress region = null;
        int i;
        int j;
        if (pStartRow == -1 || pEndRow == -1) {
            return;
        }
        for (i = 0; i < sheet.getNumMergedRegions(); i++) {
            region = sheet.getMergedRegion(i);
            if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                targetRowTo = region.getLastRow() - pStartRow + pPosition;
                CellRangeAddress newRegion = region.copy();
                newRegion.setFirstRow(targetRowFrom);
                newRegion.setFirstColumn(region.getFirstColumn());
                newRegion.setLastRow(targetRowTo);
                newRegion.setLastColumn(region.getLastColumn());
                sheet.addMergedRegion(newRegion);
            }
        }
        for (i = pStartRow; i <= pEndRow; i++) {
            XSSFRow sourceRow = sheet.getRow(i);
            columnCount = sourceRow.getLastCellNum();
            if (sourceRow != null) {
                XSSFRow newRow = sheet.createRow(pPosition - pStartRow + i);
                newRow.setHeight(sourceRow.getHeight());
                for (j = 0; j < columnCount; j++) {
                    XSSFCell templateCell = sourceRow.getCell(j);
                    if (templateCell != null) {
                        XSSFCell newCell = newRow.createCell(j);
                        copyCell(templateCell, newCell);
                    }
                }
            }
        }
    }

    public static void copyCell(XSSFCell srcCell, XSSFCell distCell) {
        distCell.setCellStyle(srcCell.getCellStyle());
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        CellType srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (srcCellType.equals(CellType.NUMERIC)) {
            distCell.setCellValue(srcCell.getNumericCellValue());
        } else if (srcCellType.equals(CellType.STRING)) {
            distCell.setCellValue(srcCell.getRichStringCellValue());
        } else if (srcCellType.equals(CellType.BLANK)) {
            // nothing21
        } else if (srcCellType.equals(CellType.BOOLEAN)) {
            distCell.setCellValue(srcCell.getBooleanCellValue());
        } else if (srcCellType.equals(CellType.ERROR)) {
            distCell.setCellErrorValue(srcCell.getErrorCellValue());
        } else if (srcCellType.equals(CellType.FORMULA)) {
            distCell.setCellFormula(srcCell.getCellFormula());
        } else { // nothing29

        }
    }

    public XSSFRow insertRow(XSSFSheet sheet, int rowIndex) {
        XSSFRow row = null;
        if (sheet.getRow(rowIndex) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex, lastRowNo, 1);
        }
        row = sheet.createRow(rowIndex);
        return row;
    }

    public void cancelMerged(XSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        List<CellRangeAddress> listRange = sheet.getMergedRegions();

        List<Integer> removeRangeNums = new ArrayList<Integer>();
        CellRangeAddress r = null;
        for (int i = 0; i < listRange.size(); i++) {
            r = listRange.get(i);
            if (r.getFirstRow() >= firstRow && r.getLastRow() <= lastRow && r.getFirstColumn() >= firstCol && r.getLastColumn() <= lastCol) {
                removeRangeNums.add(i);
            }
        }
        sheet.removeMergedRegions(removeRangeNums);
    }

    public void deleteRow(XSSFSheet sheet, int startRow, int endRow) {
        int maxColNum = 0;
        Iterator<Row> rowIt = sheet.rowIterator();
        while (rowIt.hasNext()) {
            maxColNum = Math.max(maxColNum, rowIt.next().getLastCellNum());
        }

        //Move Downward
        int startNumDown = endRow + 1;
        int endNumDown = sheet.getLastRowNum();
        int moveNumDown = endNumDown - startNumDown + 1;
        sheet.shiftRows(startNumDown, endNumDown, moveNumDown, true, true);


        // The blank part cancel the merger processing
        cancelMerged(sheet, startRow, endRow + moveNumDown, 0, maxColNum);
        // Reset this part
        for (int i = startRow; i < endRow + moveNumDown; i++) {
            sheet.createRow(i);
            sheet.removeRow(sheet.getRow(i));
        }

        // Move back up again
        int startNumUp = endNumDown + 1;
        int endNumUp = sheet.getLastRowNum();
        int moveNumUp = moveNumDown + (endRow - startRow) + 1;
        sheet.shiftRows(startNumUp, endNumUp, -1 * moveNumUp, true, true);
    }

}
